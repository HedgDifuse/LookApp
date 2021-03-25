package ru.hedgdifuse.lookapp.shared.presentation.profile.impl

import com.russhwolf.settings.Settings
import com.russhwolf.settings.set
import io.ktor.client.features.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.core.component.inject
import ru.hedgdifuse.lookapp.shared.base.pingable.PingablePresenter
import ru.hedgdifuse.lookapp.shared.base.render.RenderState
import ru.hedgdifuse.lookapp.shared.presentation.profile.ProfilePresenterI
import ru.hedgdifuse.lookapp.shared.presentation.profile.ProfileViewI
import ru.hedgdifuse.lookapp.shared.usecase.impl.ProfileUseCase

class ProfilePresenter(view: ProfileViewI) : PingablePresenter<ProfileViewI>(view), ProfilePresenterI {

    private val profileUseCase: ProfileUseCase by inject()
    private val settings: Settings by inject()
    private var isLoopActive = true

    override fun onStart() {
        super.onStart()

        isLoopActive = true

        view.saveCall {
            renderState = RenderState.LOADING

            presenterScope.launch {
                while (isLoopActive) {
                    val response = profileUseCase.get()

                    // If screen removed
                    if(response.result == null && response.error == null) {
                        isLoopActive = false
                        settings["sid"] = null
                        return@launch goToMainView()
                    }

                    if(!isServerAvailable()) {
                        renderState = RenderState.ERROR
                        isLoopActive = false
                    }

                    response.result?.title?.let {
                        submitTitle(it)
                        renderState = RenderState.LOADED
                    }

                    delay(DELAY)
                }
            }
        }
    }

    companion object {
        const val DELAY = 5000L
    }
}