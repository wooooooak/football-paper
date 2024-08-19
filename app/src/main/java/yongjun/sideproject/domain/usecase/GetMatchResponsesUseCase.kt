package yongjun.sideproject.domain.usecase

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import org.koin.core.annotation.Factory
import yongjun.sideproject.domain.internal.FootballRepository
import yongjun.sideproject.domain.model.MatchResponse

/**
 * match 정보를 가져온다.
 * 인자로 받은 match와 다음 매치까지 가져온다.
 */
@Factory
class GetMatchResponsesUseCase(
    private val footballRepository: FootballRepository,
) {
    suspend operator fun invoke(competitionId: Int, matchDay: Int): List<MatchResponse> =
        coroutineScope {
            val deferredList = listOf(matchDay, matchDay + 1).map { targetMatchDay ->
                async { footballRepository.getMatchResponse(competitionId, targetMatchDay) }
            }
            deferredList.awaitAll()
        }
}
