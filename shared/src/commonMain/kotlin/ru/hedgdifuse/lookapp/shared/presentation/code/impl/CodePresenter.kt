package ru.hedgdifuse.lookapp.shared.presentation.code.impl

import com.russhwolf.settings.Settings
import com.russhwolf.settings.set
import io.ktor.client.features.*
import io.ktor.utils.io.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import org.koin.core.component.inject
import ru.hedgdifuse.lookapp.shared.base.pingable.PingablePresenter
import ru.hedgdifuse.lookapp.shared.base.render.RenderState
import ru.hedgdifuse.lookapp.shared.model.response.BaseResponse
import ru.hedgdifuse.lookapp.shared.model.response.CodeResponse
import ru.hedgdifuse.lookapp.shared.presentation.code.CodePresenterI
import ru.hedgdifuse.lookapp.shared.presentation.code.CodeViewI
import ru.hedgdifuse.lookapp.shared.tools.RandomCodeGenerator
import ru.hedgdifuse.lookapp.shared.usecase.impl.SendCodeUseCase

/**
 * [CodePresenter] - presenter, used for add this screen to panel
 */
class CodePresenter(view: CodeViewI) : PingablePresenter<CodeViewI>(view), CodePresenterI {

    private var isLoopActive = true
    private var code = RandomCodeGenerator.generate()

    private val codeUseCase: SendCodeUseCase by inject()
    private val settings: Settings by inject()

    override fun onStart() {
        super.onStart()

        isLoopActive = true

        view.saveCall {
            renderState = RenderState.LOADED
            submitCode(code)

            presenterScope.launch {
                while (isLoopActive) {
                    if(!isServerAvailable()) {
                        renderState = RenderState.ERROR
                        isLoopActive = false
                    }

                    val result = codeUseCase.get(code)
                    isLoopActive = result.result?.status?.value != 200

                    // Handle "code in use" error
                    if(result.error is ClientRequestException &&
                        result.error.response.status.value == 422
                    ) {
                        code = RandomCodeGenerator.generate()
                        submitCode(code)
                    } else {
                        delay(DELAY)
                    }

                    // If screen is added
                    if(result.result?.status?.value == 200) {

                        // Get _id from result
                        val codeResponse = result.result.content.readUTF8Line()
                        codeResponse?.let {
                            val content = Json.decodeFromString<BaseResponse<CodeResponse>>(it)
                            content.result?.id?.let { id ->
                                settings["_id"] = id
                            }
                        }

                        val cookies = result.result.headers["Set-Cookie"]
                            ?.split(Regex("; *"))
                            ?.map { Regex(COOKIES_PATTERN).find(it) }

                        val sidCookie = cookies?.getOrNull(0)?.groupValues

                        sidCookie?.let {
                            settings[it[1]] = it[2]
                            goToProfileScreen()
                        }
                    }
                }
            }
        }
    }

    companion object {
        const val DELAY = 10000L
        const val COOKIES_PATTERN = "(.*)=(.*)"
    }
}