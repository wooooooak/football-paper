package yongjun.sideproject.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import yongjun.sideproject.domain.model.Area
import yongjun.sideproject.domain.model.Competition
import yongjun.sideproject.domain.model.Filters
import yongjun.sideproject.domain.model.Season
import yongjun.sideproject.domain.model.Standing
import yongjun.sideproject.domain.model.StandingResponse
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
            matchday = null,
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
            Standing(
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
