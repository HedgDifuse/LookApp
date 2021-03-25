package ru.hedgdifuse.lookapp.shared.usecase.impl

import io.ktor.http.*
import ru.hedgdifuse.lookapp.shared.usecase.UseCaseResult
import ru.hedgdifuse.lookapp.shared.router.LookRouterI

/**
 * [PingUseCase] - usecase for ping server status
 */
class PingUseCase(
    private val router: LookRouterI
) {
    suspend fun get() = try {
        UseCaseResult(router.ping().status.isSuccess(), null)
    } catch (e: Exception) {
        println("error: ${e.message}")
        UseCaseResult(false, e)
    }
}