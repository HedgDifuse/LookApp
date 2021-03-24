package shared.model.response

import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<R, E>(
    val requestId: String,
    val path: String,
    val result: R?,
    val error: E?,
    val serverVersion: String,
    val date: String
)