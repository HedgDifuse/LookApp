package ru.hedgdifuse.lookapp.shared.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class CodeResponse(
    @SerialName("_id") val id: String
)