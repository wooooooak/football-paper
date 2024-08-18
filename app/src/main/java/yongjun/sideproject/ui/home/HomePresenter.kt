package yongjun.sideproject.ui.home

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
import yongjun.sideproject.domain.model.StandingResponse
import yongjun.sideproject.domain.usecase.GetStandingsResponseListUseCase
import yongjun.sideproject.ui.utils.Async
import yongjun.sideproject.ui.utils.Loading
import yongjun.sideproject.ui.utils.Uninitialized
import yongjun.sideproject.ui.utils.execute
import yongjun.sideproject.ui.utils.presenterFactory

@Composable
fun HomePresenter(
    navigator: Navigator,
    getStandingsResponseListUseCase: GetStandingsResponseListUseCase = koinInject(),
): HomeScreen.State {
    val coroutineScope = rememberCoroutineScope()
    var getStandingResponsesAsync: Async<List<StandingResponse>> by rememberRetained {
        mutableStateOf(Uninitialized)
    }

    LaunchedEffect(Unit) {
        if (getStandingResponsesAsync is Loading) return@LaunchedEffect
        suspend { getStandingsResponseListUseCase() }
            .execute(coroutineScope) { async ->
                getStandingResponsesAsync = async
            }
    }

    return HomeScreen.State(
        getStandingResponsesAsync = getStandingResponsesAsync,
    )
}

@Named("Home")
@Factory
fun provideHomePresenterFactory(): Presenter.Factory =
    presenterFactory<HomeScreen, HomeScreen.State> { _, _, navigator ->
        HomePresenter(navigator)
    }
