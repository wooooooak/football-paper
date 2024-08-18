package yongjun.sideproject.data.provide

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.url
import org.koin.core.annotation.Single
import yongjun.sideproject.BuildConfig
import yongjun.sideproject.data.model.StandingResponseDto
import yongjun.sideproject.data.model.toDomain
import yongjun.sideproject.domain.internal.FootballRepository
import yongjun.sideproject.domain.model.StandingResponse

@Single
class FootballRepositoryImpl(
    private val httpClient: HttpClient,
) : FootballRepository {
    override suspend fun getStandingsResponse(competitionCode: String): StandingResponse {
        return httpClient.get {
            url("competitions/$competitionCode/standings")
            header("X-Auth-Token", BuildConfig.FOOTBALL_API_KEY)
        }.body<StandingResponseDto>().toDomain()
    }
}
