package yongjun.sideproject.domain.mock

import yongjun.sideproject.domain.model.Area
import yongjun.sideproject.domain.model.Competition
import yongjun.sideproject.domain.model.Filters
import yongjun.sideproject.domain.model.Season
import yongjun.sideproject.domain.model.Standing
import yongjun.sideproject.domain.model.StandingResponse
import yongjun.sideproject.domain.model.Table
import yongjun.sideproject.domain.model.Team

object StandingResponseMock {
    val standingResponsesMock: List<StandingResponse> by lazy {
        listOf(
            StandingResponse(
                filters = Filters(season = "2024"),
                area = Area(
                    id = 1763,
                    name = "England",
                    code = "ENG",
                    flag = "https://crests.football-data.org/770.svg",
                ),
                competition = Competition(
                    id = 2021,
                    name = "Premier League",
                    code = "PL",
                    type = "LEAGUE",
                    emblem = "https://crests.football-data.org/PL.png",
                ),
                season = Season(
                    id = 9821,
                    startDate = "2021-08-13",
                    endDate = "2022-05-22",
                    currentMatchday = 37,
                    winner = null,
                    stages = listOf(),
                ),
                standings = listOf(
                    Standing(
                        stage = "REGULAR_SEASON", type = "TOTAL",
                        tables = listOf(
                            Table(
                                position = 1,
                                team = Team(
                                    id = 1917,
                                    name = "Manchester City FC",
                                    shortName = "Man City",
                                    tla = "MCI",
                                    crest = "https://crests.football-data.org/65.png",
                                ),
                                playedGames = 37,
                                form = "D,W,W,W,W",
                                won = 28,
                                draw = 6,
                                lost = 3,
                                points = 90,
                                goalsFor = 96,
                                goalsAgainst = 24,
                                goalDifference = 72,
                            ),
                            Table(
                                position = 2,
                                team = Team(
                                    id = 9090,
                                    name = "Jack Wilkerson",
                                    shortName = "Alexis Weaver fe efsf sef ses f",
                                    tla = "ultricies",
                                    crest = "in",
                                ),
                                playedGames = 32,
                                form = null,
                                won = 2,
                                draw = 12,
                                lost = 12,
                                points = 12,
                                goalsFor = 2,
                                goalsAgainst = 2,
                                goalDifference = 12,
                            ),
                            Table(
                                position = 3,
                                team = Team(
                                    id = 416,
                                    name = "Jack Wilkerson",
                                    shortName = "Alexis Weaver",
                                    tla = "ultricies",
                                    crest = "in",
                                ),
                                playedGames = 32,
                                form = null,
                                won = 22,
                                draw = 2,
                                lost = 2,
                                points = 12,
                                goalsFor = 2,
                                goalsAgainst = 2,
                                goalDifference = 2,
                            ),
                            Table(
                                position = 4,
                                team = Team(
                                    id = 699,
                                    name = "Chelsea FC",
                                    shortName = "Chelsea",
                                    tla = "CHE",
                                    crest = "https://crests.football-data.org/61.png",
                                ),
                                playedGames = 37,
                                form = "D,W,L,W,W",
                                won = 28,
                                draw = 6,
                                lost = 3,
                                points = 90,
                                goalsFor = 96,
                                goalsAgainst = 24,
                                goalDifference = 72,
                            ),
                        ),
                    ),
                    Standing(
                        stage = "REGULAR_SEASON", type = "HOME", tables = listOf(),
                    ),
                    Standing(
                        stage = "REGULAR_SEASON", type = "AWAY", tables = listOf(),
                    ),
                ),
            ),
            StandingResponse(
                filters = Filters(season = "2024"),
                area = Area(
                    id = 1763,
                    name = "England",
                    code = "ENG",
                    flag = "https://crests.football-data.org/770.svg",
                ),
                competition = Competition(
                    id = 2021,
                    name = "Premier League",
                    code = "PL",
                    type = "LEAGUE",
                    emblem = "https://crests.football-data.org/PL.png",
                ),
                season = Season(
                    id = 9821,
                    startDate = "2021-08-13",
                    endDate = "2022-05-22",
                    currentMatchday = 37,
                    winner = null,
                    stages = listOf(),
                ),
                standings = listOf(
                    Standing(
                        stage = "REGULAR_SEASON", type = "TOTAL",
                        tables = listOf(
                            Table(
                                position = 1,
                                team = Team(
                                    id = 238,
                                    name = "Manchester City FC",
                                    shortName = "Man City",
                                    tla = "MCI",
                                    crest = "https://crests.football-data.org/65.png",
                                ),
                                playedGames = 37,
                                form = "D,W,W,W,W",
                                won = 28,
                                draw = 6,
                                lost = 3,
                                points = 90,
                                goalsFor = 96,
                                goalsAgainst = 24,
                                goalDifference = 72,
                            ),
                            Table(
                                position = 2,
                                team = Team(
                                    id = 1220,
                                    name = "Jack Wilkerson",
                                    shortName = "Alexis Weaver",
                                    tla = "ultricies",
                                    crest = "in",
                                ),
                                playedGames = 22,
                                form = null,
                                won = 12,
                                draw = 23,
                                lost = 32,
                                points = 32,
                                goalsFor = 23,
                                goalsAgainst = 11,
                                goalDifference = 23,
                            ),
                            Table(
                                position = 3,
                                team = Team(
                                    id = 532,
                                    name = "Jack Wilkerson",
                                    shortName = "Alexis Weaver",
                                    tla = "ultricies",
                                    crest = "in",
                                ),
                                playedGames = 22,
                                form = null,
                                won = 2,
                                draw = 32,
                                lost = 32,
                                points = 12,
                                goalsFor = 12,
                                goalsAgainst = 12,
                                goalDifference = 11,
                            ),
                        ),
                    ),
                    Standing(
                        stage = "REGULAR_SEASON", type = "HOME", tables = listOf(),
                    ),
                    Standing(
                        stage = "REGULAR_SEASON", type = "AWAY", tables = listOf(),
                    ),
                ),
            ),
            StandingResponse(
                filters = Filters(season = "2024"),
                area = Area(
                    id = 1233,
                    name = "Gemani",
                    code = "ENG",
                    flag = "https://crests.football-data.org/770.svg",
                ),
                competition = Competition(
                    id = 2933,
                    name = "분데스리가",
                    code = "BL1",
                    type = "LEAGUE",
                    emblem = "https://crests.football-data.org/PL.png",
                ),
                season = Season(
                    id = 9821,
                    startDate = "2021-08-13",
                    endDate = "2022-05-22",
                    currentMatchday = 37,
                    winner = null,
                    stages = listOf(),
                ),
                standings = listOf(
                    Standing(
                        stage = "REGULAR_SEASON", type = "TOTAL",
                        tables = listOf(
                            Table(
                                position = 1,
                                team = Team(
                                    id = 654,
                                    name = "Manchester City FC",
                                    shortName = "Man City",
                                    tla = "MCI",
                                    crest = "https://crests.football-data.org/65.png",
                                ),
                                playedGames = 37,
                                form = "D,W,W,W,W",
                                won = 28,
                                draw = 6,
                                lost = 3,
                                points = 90,
                                goalsFor = 96,
                                goalsAgainst = 24,
                                goalDifference = 72,
                            ),
                            Table(
                                position = 2,
                                team = Team(
                                    id = 88,
                                    name = "Jack Wilkerson",
                                    shortName = "Alexis Weaver",
                                    tla = "ultricies",
                                    crest = "in",
                                ),
                                playedGames = 1,
                                form = null,
                                won = 1,
                                draw = 1,
                                lost = 11,
                                points = 22,
                                goalsFor = 23,
                                goalsAgainst = 23,
                                goalDifference = 23,
                            ),
                            Table(
                                position = 3,
                                team = Team(
                                    id = 77,
                                    name = "Jack Wilkerson",
                                    shortName = "Alexis Weaver",
                                    tla = "ultricies",
                                    crest = "in",
                                ),
                                playedGames = 23,
                                form = null,
                                won = 32,
                                draw = 33,
                                lost = 23,
                                points = 23,
                                goalsFor = 12,
                                goalsAgainst = 44,
                                goalDifference = 23,
                            ),
                        ),
                    ),
                    Standing(
                        stage = "REGULAR_SEASON", type = "HOME", tables = listOf(),
                    ),
                    Standing(
                        stage = "REGULAR_SEASON", type = "AWAY", tables = listOf(),
                    ),
                ),
            ),
        )
    }
}
