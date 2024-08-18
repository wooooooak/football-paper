package yongjun.sideproject.domain.model

import kotlinx.serialization.Serializable

data class StandingResponse(
    val filters: Filters,
    val area: Area,
    val competition: Competition,
    val season: Season,
    val standings: List<Standing>,
) {
    val totalStanding = standings.first { it.type == "TOTAL" }
}

@Serializable
data class Filters(
    val season: String,
)

@Serializable
data class Area(
    val id: Int,
    val name: String,
    val code: String,
    // 국기 png url
    val flag: String,
)

/**
 * 대회 정보
 */
@Serializable
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

@Serializable
data class Season(
    val id: Int,
    val startDate: String,
    val endDate: String,
    val currentMatchday: Int,
    val winner: String?,
    val stages: List<String>?,
)

@Serializable
data class Team(
    val id: Int,
    val name: String,
    val shortName: String,
    // 팀 명 약자. 예) MCI, LIV,
    val tla: String,
    // 팀 로고 png url
    val crest: String,
)

@Serializable
data class Table(
    // 순위
    val position: Int,
    val team: Team,
    val playedGames: Int,
    // 최근 5경기 승패 유무. 예) D, W, W, W, W
    val form: String?,
    val won: Int,
    val draw: Int,
    val lost: Int,
    val points: Int,
    val goalsFor: Int,
    val goalsAgainst: Int,
    val goalDifference: Int,
)

@Serializable
data class Standing(
    val stage: String,
    // TOTAL, HOME, AWAY
    val type: String,
    val tables: List<Table>,
)

