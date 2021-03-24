package shared.router.impl

import com.russhwolf.settings.Settings
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import shared.custom.withStartUrl
import shared.model.payload.CodePayload
import shared.model.payload.ProfileResponse
import shared.model.response.BaseResponse
import shared.router.LookRouterI

/**
 * [LookRouter] - implementation of [LookRouterI]
 */
class LookRouter(
    private val client: HttpClient,
    private val settings: Settings
): LookRouterI {
    override suspend fun code(code: Int) =
        client.post<HttpResponse>(withStartUrl("/code")) {
            body = defaultSerializer().write(CodePayload(code))
        }

    override suspend fun ping() =
        client.get<HttpResponse>(withStartUrl("/ping"))

    override suspend fun profile() =
        client.get<BaseResponse<ProfileResponse, Any>>(withStartUrl("/profile")) {
            settings.getStringOrNull("sid")
                ?.let { cookie("sid", it) }
        }
}