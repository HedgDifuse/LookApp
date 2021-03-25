package ru.hedgdifuse.lookapp.shared.model.response

import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<R>(
    val requestId: String,
    val path: String,
    val result: R?,
    val serverVersion: String,
    val date: String
)