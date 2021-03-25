package ru.hedgdifuse.lookapp.shared.model.response

import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<R>(
    val method: String? = null,
    val requestId: String,
    val path: String,
    val result: R?,
    val serverVersion: String,
    val date: String
)