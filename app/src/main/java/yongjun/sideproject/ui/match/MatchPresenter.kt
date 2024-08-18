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
import yongjun.sideproject.domain.usecase.GetMatchResponseUseCase
import yongjun.sideproject.ui.utils.Async
import yongjun.sideproject.ui.utils.Loading
import yongjun.sideproject.ui.utils.Uninitialized
import yongjun.sideproject.ui.utils.execute
import yongjun.sideproject.ui.utils.presenterFactory

@Composable
fun MatchPresenter(
    screen: MatchScreen,
    navigator: Navigator,
    getMatchResponseUseCase: GetMatchResponseUseCase = koinInject(),
): MatchScreen.State {
    val coroutineScope = rememberCoroutineScope()
    var getMatchResponseAsync: Async<MatchResponse> by rememberRetained {
        mutableStateOf(Uninitialized)
    }

    LaunchedEffect(Unit) {
        if (getMatchResponseAsync is Loading) return@LaunchedEffect
        suspend { getMatchResponseUseCase(screen.competitionId, screen.matchDay) }
            .execute(coroutineScope) { async ->
                getMatchResponseAsync = async
            }
    }

    return MatchScreen.State(
        getMatchResponseAsync = getMatchResponseAsync,
        matchDay = screen.matchDay,
    ) { event ->
        when (event) {
            MatchScreen.Event.Pop -> navigator.pop()
        }
    }
}

@Named("Match")
@Factory
fun provideMatchPresenterFactory(): Presenter.Factory =
    presenterFactory<MatchScreen, MatchScreen.State> { screen, _, navigator ->
        MatchPresenter(screen, navigator)
    }
