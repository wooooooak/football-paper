package yongjun.sideproject.ui.common

import kotlinx.serialization.Serializable

@Serializable
data class ErrorPayload(
    val message: String,
    val errorCode: Int,
)
