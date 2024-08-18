package yongjun.sideproject.ui.match

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.slack.circuit.runtime.ui.Ui
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Named
import yongjun.sideproject.domain.mock.ResponseMock
import yongjun.sideproject.domain.model.MatchResponse
import yongjun.sideproject.ui.utils.Fail
import yongjun.sideproject.ui.utils.Loading
import yongjun.sideproject.ui.utils.Success
import yongjun.sideproject.ui.utils.Uninitialized
import yongjun.sideproject.ui.utils.uiFactory

@Composable
fun MatchUi(
    state: MatchScreen.State,
    modifier: Modifier = Modifier,
) {
    val matchResponse = state.getMatchResponseAsync()
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                matchDay = state.matchDay,
                onClick = { state.eventSink(MatchScreen.Event.Pop) },
            )
        },
    ) { innerPadding ->
        val primaryColor = Color(0xffff8787)
        when (state.getMatchResponseAsync) {
            is Fail -> {
                Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = state.getMatchResponseAsync.error.toString())
                }
            }

            is Loading, Uninitialized -> {
                Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = primaryColor)
                }
            }

            is Success -> {
                MatchBodySection(
                    matchResponse = requireNotNull(matchResponse),
                    modifier = Modifier.padding(innerPadding),
                )
            }
        }
    }
}

@Composable
private fun TopAppBar(
    matchDay: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Row(
        modifier = modifier.statusBarsPadding(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(onClick = onClick) {
            Icon(Icons.Rounded.ArrowBack, contentDescription = "back")
        }
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 40.dp),
            text = "Match day $matchDay",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
private fun MatchBodySection(
    matchResponse: MatchResponse,
    modifier: Modifier = Modifier,
) {

}

@Preview
@Composable
private fun Preview() {
    MaterialTheme {
        MatchUi(
            state = MatchScreen.State(
                getMatchResponseAsync = Success(ResponseMock.matchResponseMock),
                matchDay = 1,
            ),
        )
    }
}

@Named("Match")
@Factory
fun provideMatchUiFactory(): Ui.Factory =
    uiFactory<MatchScreen, MatchScreen.State> { _, state, modifier ->
        MatchUi(state, modifier)
    }
