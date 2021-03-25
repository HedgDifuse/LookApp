package ru.hedgdifuse.lookapp.shared.usecase.impl

import ru.hedgdifuse.lookapp.shared.usecase.UseCaseResult
import ru.hedgdifuse.lookapp.shared.router.LookRouterI

/**
 * [ProfileUseCase] - usecase for getting profile response
 */
class ProfileUseCase(
    private val router: LookRouterI
) {
    suspend fun get() = try {
        UseCaseResult(router.profile().result, null)
    } catch (e: Exception) {
        println("error: ${e.message}")
        UseCaseResult(null, e)
    }
}