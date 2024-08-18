package yongjun.sideproject.domain.model

/**
 * 대회 정보
 */
data class Competition(
    val id: Int,
    // 대회 이름. 예) Premier League
    val name: String,
    // 대회 코드. 예) PL
    val code: String,
    // 리그 or 컵대회 등등
    val type: String,
    // 대회 엠블런 png url
    val emblem: String,
)
