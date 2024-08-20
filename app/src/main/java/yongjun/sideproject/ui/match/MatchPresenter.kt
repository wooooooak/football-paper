package yongjun.sideproject.ui.match

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.slack.circuit.retained.rememberRetained
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import org.koin.compose.koinInject
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Named
import yongjun.sideproject.domain.model.MatchResponse
import yongjun.sideproject.domain.usecase.GetMatchResponsesUseCase
import yongjun.sideproject.ui.utils.Async
import yongjun.sideproject.ui.utils.Loading
import yongjun.sideproject.ui.utils.Uninitialized
import yongjun.sideproject.ui.utils.execute
import yongjun.sideproject.ui.utils.presenterFactory

@Composable
fun MatchPresenter(
    screen: MatchScreen,
    navigator: Navigator,
    getMatchResponsesUseCase: GetMatchResponsesUseCase = koinInject(),
): MatchScreen.State {
    val coroutineScope = rememberCoroutineScope()
    var getMatchResponsesAsync: Async<List<MatchResponse>> by rememberRetained {
        mutableStateOf(Uninitialized)
    }

    fun fetchMatch() {
        if (getMatchResponsesAsync is Loading) return
        suspend { getMatchResponsesUseCase(screen.competitionId, screen.matchDay) }
            .execute(coroutineScope) { async ->
                getMatchResponsesAsync = async
            }
    }

    LaunchedEffect(Unit) {
        fetchMatch()
    }

    return MatchScreen.State(
        getMatchResponsesAsync = getMatchResponsesAsync,
        matchDay = screen.matchDay,
    ) { event ->
        when (event) {
            MatchScreen.Event.Pop -> navigator.pop()
            MatchScreen.Event.FetchMatch -> fetchMatch()
        }
    }
}

@Named("Match")
@Factory
fun provideMatchPresenterFactory(): Presenter.Factory =
    presenterFactory<MatchScreen, MatchScreen.State> { screen, _, navigator ->
        MatchPresenter(screen, navigator)
    }
