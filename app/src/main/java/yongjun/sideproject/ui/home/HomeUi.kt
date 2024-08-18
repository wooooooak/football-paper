package yongjun.sideproject.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.slack.circuit.runtime.ui.Ui
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Named
import yongjun.sideproject.ui.utils.uiFactory

@Composable
fun HomeUi(
    state: HomeScreen.State,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .statusBarsPadding(),
        ) {
            state.getStandingResponsesAsync()?.forEach {
                Text(text = it.toString())
            }
        }
    }
}

@Named("Home")
@Factory
fun provideHomeUiFactory(): Ui.Factory =
    uiFactory<HomeScreen, HomeScreen.State> { _, state, modifier ->
        HomeUi(state, modifier)
    }
