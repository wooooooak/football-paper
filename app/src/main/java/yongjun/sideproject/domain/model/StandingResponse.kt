package yongjun.sideproject.domain.model


data class StandingResponse(
    val filters: Filters,
    val area: Area,
    val competition: Competition,
    val season: Season,
    val standings: List<Standing>,
) {
    val totalStanding = standings.first { it.type == "TOTAL" }
}

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

data class Standing(
    val stage: String,
    // TOTAL, HOME, AWAY
    val type: String,
    val tables: List<Table>,
)

