package yongjun.sideproject.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SeasonDto(
    @SerialName("id") val id: Int,
    @SerialName("startDate") val startDate: String,
    @SerialName("endDate") val endDate: String,
    @SerialName("currentMatchday") val currentMatchday: Int,
    @SerialName("winner") val winner: String?,
    @SerialName("stages") val stages: List<String>? = emptyList(),
)
