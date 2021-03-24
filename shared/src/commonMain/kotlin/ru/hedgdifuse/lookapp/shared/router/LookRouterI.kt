package shared.router

import io.ktor.client.statement.*
import shared.model.payload.ProfileResponse
import shared.model.response.BaseResponse

/**
 * [LookRouterI] - interface for based on ktor router.
 */
interface LookRouterI {
    suspend fun code(code: Int): HttpResponse
    suspend fun ping(): HttpResponse
    suspend fun profile(): BaseResponse<ProfileResponse, Any>
}