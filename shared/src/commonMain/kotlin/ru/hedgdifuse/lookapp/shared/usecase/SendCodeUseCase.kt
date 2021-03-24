package ru.hedgdifuse.lookapp.shared.usecase

import shared.router.LookRouterI

/**
 * [SendCodeUseCase] - usecase for sending code to server
 */
class SendCodeUseCase(
    private val router: LookRouterI
) {
    suspend fun get(code: Int) = try {
        UseCaseResult(router.code(code), null)
    } catch (e: Exception) {
        UseCaseResult(null, e)
    }
}