package yongjun.sideproject.domain.model

data class MatchResponse(
    val filters: Filters,
    val resultSet: ResultSet,
    val competition: Competition,
    val matches: List<Match>,
)

/**
 * 해당 match에 대한 정보
 *
 * 총 count 경기가 있는고, 기간은 first~last까지.
 * 현재 진행된 경기는 played.
 */
data class ResultSet(
    val count: Long,
    val first: String,
    val last: String,
    val played: Long,
)

data class Match(
    val area: Area,
    val competition: Competition,
    val season: Season,
    val id: Long,
    val utcDate: String,
    // SCHEDULED | LIVE | IN_PLAY | PAUSED | FINISHED | POSTPONED | SUSPENDED | CANCELLED | TIMED
    val status: String,
    val matchday: Int,
    val stage: String,
    val group: Any?,
    val lastUpdated: String,
    val homeTeam: Team,
    val awayTeam: Team,
    val score: Score,
    val odds: Odds,
    val referees: List<Referee>,
)

data class Score(
    // AWAY_TEAM, HOME_TEAM, DRAW
    val winner: String?,
    val duration: String,
    val fullTime: FullTime,
    val halfTime: HalfTime,
)

data class FullTime(
    val home: Long?,
    val away: Long?,
)

data class HalfTime(
    val home: Long?,
    val away: Long?,
)

data class Odds(
    val msg: String,
)

data class Referee(
    val id: Long,
    val name: String,
    val type: String,
    val nationality: String,
)
