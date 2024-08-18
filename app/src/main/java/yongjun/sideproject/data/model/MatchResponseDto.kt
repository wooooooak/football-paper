package yongjun.sideproject.data.model

import kotlinx.serialization.Serializable
import yongjun.sideproject.domain.model.Area
import yongjun.sideproject.domain.model.Competition
import yongjun.sideproject.domain.model.Filters
import yongjun.sideproject.domain.model.FullTime
import yongjun.sideproject.domain.model.HalfTime
import yongjun.sideproject.domain.model.Match
import yongjun.sideproject.domain.model.MatchResponse
import yongjun.sideproject.domain.model.Odds
import yongjun.sideproject.domain.model.Referee
import yongjun.sideproject.domain.model.ResultSet
import yongjun.sideproject.domain.model.Score
import yongjun.sideproject.domain.model.Season
import yongjun.sideproject.domain.model.Team

@Serializable
data class MatchResponseDto(
    val filters: FiltersDto,
    val resultSet: ResultSetDto,
    val competition: CompetitionDto,
    val matches: List<MatchDto>,
)

@Serializable
data class ResultSetDto(
    val count: Long,
    val first: String,
    val last: String,
    val played: Long,
)

@Serializable
data class MatchDto(
    val area: AreaDto,
    val competition: CompetitionDto,
    val season: SeasonDto,
    val id: Long,
    val utcDate: String,
    val status: String,
    val matchday: Int,
    val stage: String,
    val lastUpdated: String,
    val homeTeam: TeamDto,
    val awayTeam: TeamDto,
    val score: ScoreDto,
    val odds: OddsDto,
    val referees: List<RefereeDto>,
)

@Serializable
data class ScoreDto(
    val winner: String?,
    val duration: String,
    val fullTime: FullTimeDto,
    val halfTime: HalfTimeDto,
)

@Serializable
data class FullTimeDto(
    val home: Long?,
    val away: Long?,
)

@Serializable
data class HalfTimeDto(
    val home: Long?,
    val away: Long?,
)

@Serializable
data class OddsDto(
    val msg: String,
)

@Serializable
data class RefereeDto(
    val id: Long,
    val name: String,
    val type: String,
    val nationality: String,
)

fun MatchResponseDto.toDomain() = MatchResponse(
    filters = Filters(season = filters.season, matchday = filters.matchday),
    resultSet = ResultSet(
        count = resultSet.count,
        first = resultSet.first,
        last = resultSet.last,
        played = resultSet.played,
    ),
    competition = Competition(
        id = competition.id,
        name = competition.name,
        code = competition.code,
        type = competition.type,
        emblem = competition.emblem,
    ),
    matches = matches.map {
        Match(
            area = Area(
                id = it.area.id,
                name = it.area.name,
                code = it.area.code,
                flag = it.area.flag,
            ),
            competition = Competition(
                id = it.competition.id,
                name = it.competition.name,
                code = it.competition.code,
                type = it.competition.type,
                emblem = it.competition.emblem,
            ),
            season = Season(
                id = it.season.id,
                startDate = it.season.startDate,
                endDate = it.season.endDate,
                currentMatchday = it.season.currentMatchday,
                winner = it.season.winner,
                stages = listOf(it.stage),
            ),
            id = it.id,
            utcDate = it.utcDate,
            status = it.status,
            matchday = it.matchday,
            stage = it.stage,
            group = null,
            lastUpdated = it.lastUpdated,
            homeTeam = Team(
                id = it.homeTeam.id,
                name = it.homeTeam.name,
                shortName = it.homeTeam.shortName,
                tla = it.homeTeam.tla,
                crest = it.homeTeam.crest,
            ),
            awayTeam = Team(
                id = it.awayTeam.id,
                name = it.awayTeam.name,
                shortName = it.awayTeam.shortName,
                tla = it.awayTeam.tla,
                crest = it.awayTeam.crest,
            ),
            score = Score(
                winner = it.score.winner,
                duration = it.score.duration,
                fullTime = FullTime(home = it.score.fullTime.home, away = it.score.fullTime.away),
                halfTime = HalfTime(home = it.score.halfTime.home, away = it.score.halfTime.away),
            ),
            odds = Odds(msg = it.odds.msg),
            referees = it.referees.map { referee ->
                Referee(
                    id = referee.id,
                    name = referee.name,
                    type = referee.type,
                    nationality = referee.nationality,
                )
            },
        )
    },
)
