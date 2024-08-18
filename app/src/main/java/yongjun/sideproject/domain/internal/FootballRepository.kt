package yongjun.sideproject.domain.internal

import yongjun.sideproject.domain.model.StandingResponse

interface FootballRepository {
    suspend fun getStandingsResponse(competitionCode: String): StandingResponse
}
