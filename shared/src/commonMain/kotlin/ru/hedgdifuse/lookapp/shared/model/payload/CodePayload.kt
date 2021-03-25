package ru.hedgdifuse.lookapp.shared.model.payload

import kotlinx.serialization.Serializable

@Serializable
data class CodePayload(
    val code: Int
)