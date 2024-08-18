package yongjun.sideproject.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TeamDto(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("shortName") val shortName: String,
    // 팀 명 약자. 예) MCI, LIV,
    @SerialName("tla") val tla: String,
    // 팀 로고 png url
    @SerialName("crest") val crest: String,
)
