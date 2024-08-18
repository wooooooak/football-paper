package yongjun.sideproject.domain.usecase

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import org.koin.core.annotation.Factory
import yongjun.sideproject.domain.internal.FootballRepository
import yongjun.sideproject.domain.model.StandingResponse

/**
 * 4개 대회의 standing resopnse를 합쳐서 리턴한다.
 *
 * 4개 대회 : PL, PD, BL1, FL1
 */
@Factory
class GetStandingsResponseListUseCase(
    private val footballRepository: FootballRepository,
) {
    suspend operator fun invoke(): List<StandingResponse> = coroutineScope {
        val deferreds = COMPETITION_CODES.map { code ->
            async { footballRepository.getStandingsResponse(code) }
        }
        val standingResponses = deferreds.awaitAll()
        standingResponses
    }
}

private val COMPETITION_CODES = listOf("PL")
//private val COMPETITION_CODES = listOf("PL", "BL1", "PD", "FL1")
