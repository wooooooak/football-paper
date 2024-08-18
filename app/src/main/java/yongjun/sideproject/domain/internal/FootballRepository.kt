package yongjun.sideproject.domain.internal

import yongjun.sideproject.domain.model.MatchResponse
import yongjun.sideproject.domain.model.StandingResponse

interface FootballRepository {
    suspend fun getStandingsResponse(competitionCode: String): StandingResponse

    suspend fun getMatchResponse(competitionId: Int, matchDay: Int): MatchResponse
}
