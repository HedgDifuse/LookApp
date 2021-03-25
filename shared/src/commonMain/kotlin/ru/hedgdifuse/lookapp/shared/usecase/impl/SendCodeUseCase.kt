package ru.hedgdifuse.lookapp.shared.usecase.impl

import ru.hedgdifuse.lookapp.shared.usecase.UseCaseResult
import ru.hedgdifuse.lookapp.shared.router.LookRouterI

/**
 * [SendCodeUseCase] - usecase for sending code to server
 */
class SendCodeUseCase(
    private val router: LookRouterI
) {
    suspend fun get(code: Int) = try {
        UseCaseResult(router.code(code), null)
    } catch (e: Exception) {
        println("error: ${e.message}")
        UseCaseResult(null, e)
    }
}