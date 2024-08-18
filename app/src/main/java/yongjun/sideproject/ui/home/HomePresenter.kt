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
import yongjun.sideproject.ui.utils.Success
import yongjun.sideproject.ui.utils.Uninitialized
import yongjun.sideproject.ui.utils.execute
import yongjun.sideproject.ui.utils.presenterFactory
import java.time.LocalDateTime

@Composable
fun HomePresenter(
    navigator: Navigator,
    getStandingsResponseListUseCase: GetStandingsResponseListUseCase = koinInject(),
): HomeScreen.State {
    val coroutineScope = rememberCoroutineScope()
    var getStandingResponsesAsync: Async<List<StandingResponse>> by rememberRetained {
        mutableStateOf(Uninitialized)
    }

    var lastUpdatedAt: LocalDateTime? by rememberRetained {
        mutableStateOf(null)
    }

    fun fetch() {
        if (getStandingResponsesAsync is Loading) return
        suspend { getStandingsResponseListUseCase() }
            .execute(
                coroutineScope = coroutineScope,
                getRetainedValue = { getStandingResponsesAsync() },
            ) { async ->
                getStandingResponsesAsync = async
                if (async is Success) {
                    lastUpdatedAt = LocalDateTime.now()
                }
            }
    }

    LaunchedEffect(Unit) {
        fetch()
    }

    return HomeScreen.State(
        lastUpdatedAt = lastUpdatedAt,
        getStandingResponsesAsync = getStandingResponsesAsync,
    ) { event ->
        when (event) {
            HomeScreen.Event.RetryClick -> fetch()
        }
    }
}

@Named("Home")
@Factory
fun provideHomePresenterFactory(): Presenter.Factory =
    presenterFactory<HomeScreen, HomeScreen.State> { _, _, navigator ->
        HomePresenter(navigator)
    }
