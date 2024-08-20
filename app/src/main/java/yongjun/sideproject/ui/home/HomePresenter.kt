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
import kotlinx.coroutines.delay
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
import java.time.Duration
import java.time.LocalDateTime
import kotlin.time.Duration.Companion.minutes

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

    /**
     * Home 화면 진입시마다 LaunchedEffect는 수행된다.
     * 단, 매번 API를 호출하는건 API 호출수에 악영향을 미치므로
     * lastUpdate 후 30분 뒤에만 재호출을 하도록 한다.
     */
    LaunchedEffect(lastUpdatedAt) {
        if (lastUpdatedAt == null) {
            fetch()
        } else {
            while (true) {
                delay(1.minutes)
                val current = LocalDateTime.now()
                val minDifference = Duration.between(lastUpdatedAt, current).toMinutes()
                if (minDifference >= 30) {
                    fetch()
                }
            }
        }
    }

    return HomeScreen.State(
        lastUpdatedAt = lastUpdatedAt,
        getStandingResponsesAsync = getStandingResponsesAsync,
    ) { event ->
        when (event) {
            HomeScreen.Event.FetchStandings -> fetch()
            is HomeScreen.Event.GoTo -> navigator.goTo(event.screen)
            HomeScreen.Event.Pop -> navigator.pop()
        }
    }
}

@Named("Home")
@Factory
fun provideHomePresenterFactory(): Presenter.Factory =
    presenterFactory<HomeScreen, HomeScreen.State> { _, _, navigator ->
        HomePresenter(navigator)
    }
