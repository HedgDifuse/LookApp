package ru.hedgdifuse.lookapp.shared.router.impl

import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import ru.hedgdifuse.lookapp.shared.model.payload.CodePayload
import ru.hedgdifuse.lookapp.shared.model.response.BaseResponse
import ru.hedgdifuse.lookapp.shared.router.LookRouterI
import ru.hedgdifuse.lookapp.shared.custom.withStartUrl
import ru.hedgdifuse.lookapp.shared.model.response.CodeResponse
import ru.hedgdifuse.lookapp.shared.model.response.ProfileResponse

/**
 * [LookRouter] - implementation of [LookRouterI]
 */
class LookRouter(
    private val client: HttpClient
): LookRouterI {
    override suspend fun code(code: Int) =
        client.post<HttpResponse>(withStartUrl("/code")) {
            body = defaultSerializer().write(CodePayload(code))
        }

    override suspend fun ping() =
        client.get<HttpResponse>(withStartUrl("/ping"))

    override suspend fun profile() =
        client.get<BaseResponse<ProfileResponse>>(withStartUrl("/profile"))
}