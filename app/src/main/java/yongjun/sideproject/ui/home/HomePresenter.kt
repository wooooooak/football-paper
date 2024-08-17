package yongjun.sideproject.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import com.slack.circuit.retained.rememberRetained
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Named
import yongjun.sideproject.ui.presenterFactory

@Composable
fun HomePresenter(navigator: Navigator): HomeScreen.State {
    val standings by rememberRetained {
        mutableStateOf(
            listOf(
                "a",
                "b",
                "c",
                "d",
            ),
        )
    }
    return HomeScreen.State(
        standings = standings,
    )
}

@Named("Home")
@Factory
fun provideHomePresenterFactory(): Presenter.Factory =
    presenterFactory<HomeScreen, HomeScreen.State> { _, _, navigator ->
        HomePresenter(navigator)
    }
