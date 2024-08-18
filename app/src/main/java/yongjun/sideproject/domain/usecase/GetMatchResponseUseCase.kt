package yongjun.sideproject.domain.usecase

import org.koin.core.annotation.Factory
import yongjun.sideproject.domain.internal.FootballRepository

@Factory
class GetMatchResponseUseCase(
    private val footballRepository: FootballRepository,
) {
    suspend operator fun invoke(competitionId: Int, matchDay: Int) =
        footballRepository.getMatchResponse(competitionId, matchDay)
}
