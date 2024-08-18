package yongjun.sideproject.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FiltersDto(
    @SerialName("season") val season: String,
    @SerialName("matchday") val matchday: String? = null,
)
