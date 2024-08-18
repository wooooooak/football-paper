package yongjun.sideproject.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import yongjun.sideproject.domain.model.Area
import yongjun.sideproject.domain.model.Competition
import yongjun.sideproject.domain.model.Filters
import yongjun.sideproject.domain.model.Season
import yongjun.sideproject.domain.model.StandingResponse
import yongjun.sideproject.domain.model.Standings
import yongjun.sideproject.domain.model.Table
import yongjun.sideproject.domain.model.Team

@Serializable
data class StandingResponseDto(
    @SerialName("filters") val filters: FiltersDto,
    @SerialName("area") val area: AreaDto,
    @SerialName("competition") val competition: CompetitionDto,
    @SerialName("season") val season: SeasonDto,
    @SerialName("standings") val standings: List<StandingsDto>,
)

@Serializable
data class FiltersDto(
    @SerialName("season") val season: String,
)

@Serializable
data class AreaDto(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("code") val code: String,
    // 국기 png url
    @SerialName("flag") val flag: String,
)

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

@Serializable
data class SeasonDto(
    @SerialName("id") val id: Int,
    @SerialName("startDate") val startDate: String,
    @SerialName("endDate") val endDate: String,
    @SerialName("currentMatchday") val currentMatchday: Int,
    @SerialName("winner") val winner: String?,
    @SerialName("stages") val stages: List<String>? = emptyList(),
)

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

@Serializable
data class TableDto(
    // 순위
    @SerialName("position") val position: Int,
    @SerialName("team") val team: TeamDto,
    @SerialName("playedGames") val playedGames: Int,
    // 최근 5경기 승패 유무. 예) D, W, W, W, W
    @SerialName("form") val form: String?,
    @SerialName("won") val won: Int,
    @SerialName("draw") val draw: Int,
    @SerialName("lost") val lost: Int,
    @SerialName("points") val points: Int,
    @SerialName("goalsFor") val goalsFor: Int,
    @SerialName("goalsAgainst") val goalsAgainst: Int,
    @SerialName("goalDifference") val goalDifference: Int,
)

@Serializable
data class StandingsDto(
    @SerialName("stage") val stage: String,
    // TOTAL, HOME, AWAY
    @SerialName("type") val type: String,
    @SerialName("table") val tables: List<TableDto>,
)

fun StandingResponseDto.toDomain(): StandingResponse =
    StandingResponse(
        filters = Filters(
            season = filters.season,
        ),
        area = Area(id = area.id, name = area.name, code = area.code, flag = area.flag),
        competition = Competition(
            id = competition.id,
            name = competition.name,
            code = competition.code,
            type = competition.type,
            emblem = competition.emblem,
        ),
        season = Season(
            id = season.id,
            startDate = season.startDate,
            endDate = season.endDate,
            currentMatchday = season.currentMatchday,
            winner = season.winner,
            stages = season.stages,
        ),
        standings = standings.map {
            Standings(
                stage = it.stage,
                type = it.type,
                tables = it.tables.map { table ->
                    Table(
                        position = table.position,
                        team = Team(
                            id = table.team.id,
                            name = table.team.name,
                            shortName = table.team.shortName,
                            tla = table.team.tla,
                            crest = table.team.crest,
                        ),
                        playedGames = table.playedGames,
                        form = table.form,
                        won = table.won,
                        draw = table.draw,
                        lost = table.lost,
                        points = table.points,
                        goalsFor = table.goalsFor,
                        goalsAgainst = table.goalsAgainst,
                        goalDifference = table.goalDifference,
                    )
                },
            )
        },
    )
