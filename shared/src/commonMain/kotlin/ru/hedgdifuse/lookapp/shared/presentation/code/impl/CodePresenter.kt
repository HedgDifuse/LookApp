package ru.hedgdifuse.lookapp.shared.presentation.code.impl

import kotlinx.coroutines.launch
import org.koin.core.component.inject
import ru.hedgdifuse.lookapp.shared.base.impl.BasePresenter
import ru.hedgdifuse.lookapp.shared.presentation.code.CodeViewI
import ru.hedgdifuse.lookapp.shared.tools.RandomCodeGenerator
import ru.hedgdifuse.lookapp.shared.usecase.SendCodeUseCase

/**
 * [CodePresenter] - presenter, used for add this screen to panel
 */
class CodePresenter(view: CodeViewI): BasePresenter<CodeViewI>(view) {

    private var isLoopActive = true
    private var code = RandomCodeGenerator.generate()

    private val codeUseCase: SendCodeUseCase by inject()

    override fun onStart() {
        super.onStart()

        view.saveCall {
            submitCode(code)
        }

        presenterScope.launch {
            while (isLoopActive) {
                codeUseCase.get(code).result?.let { result ->
                    isLoopActive = result.status.value == 204
                }
            }
        }
    }
}