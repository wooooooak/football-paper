package yongjun.sideproject.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 대회 정보
 */
@Serializable
data class CompetitionDto(
    @SerialName("id") val id: Int,
    // 대회 이름. 예) Premier League
    @SerialName("name") val name: String,
    // 대회 코드. 예) PL
    @SerialName("code") val code: String,
    // 리그 or 컵대회 등등
    @SerialName("type") val type: String,
    // 대회 엠블런 png url
    @SerialName("emblem") val emblem: String,
)
