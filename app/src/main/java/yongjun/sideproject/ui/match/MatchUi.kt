package yongjun.sideproject.ui.match

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.slack.circuit.runtime.ui.Ui
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Named
import yongjun.sideproject.domain.mock.ResponseMock
import yongjun.sideproject.domain.model.Match
import yongjun.sideproject.domain.model.MatchResponse
import yongjun.sideproject.domain.model.MatchStatus
import yongjun.sideproject.ui.utils.Fail
import yongjun.sideproject.ui.utils.Loading
import yongjun.sideproject.ui.utils.Success
import yongjun.sideproject.ui.utils.Uninitialized
import yongjun.sideproject.ui.utils.uiFactory
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@Composable
fun MatchUi(
    state: MatchScreen.State,
    modifier: Modifier = Modifier,
) {
    val matchResponse = state.getMatchResponsesAsync()
    Scaffold(
        backgroundColor = Color(0xfff1f3f5),
        modifier = modifier,
        topBar = {
            TopAppBar(
                onClick = { state.eventSink(MatchScreen.Event.Pop) },
            )
        },
    ) { innerPadding ->
        val primaryColor = Color(0xffff8787)
        when (state.getMatchResponsesAsync) {
            is Fail -> {
                Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = state.getMatchResponsesAsync.error.toString())
                }
            }

            is Loading, Uninitialized -> {
                Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = primaryColor)
                }
            }

            is Success -> {
                MatchBodySection(
                    matchResponses = requireNotNull(matchResponse),
                    modifier = Modifier.padding(innerPadding),
                )
            }
        }
    }
}

@Composable
private fun TopAppBar(
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
                .weight(1f, true)
                .padding(start = 36.dp),
            text = "Matches",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
        )

        val today = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM.dd"))
        Text(
            text = "today: $today",
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(end = 12.dp),
        )
    }
}

@Composable
private fun MatchBodySection(
    matchResponses: List<MatchResponse>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        matchResponses.forEach { response ->
            // Match N
            item {
                MatchTitle(response = response)
            }
            response.matches.forEachIndexed { index, match ->
                val prevMatch = response.matches.getOrNull(index - 1)
                // 이전 경기가 없는 첫 경기이거나, 이전 경기와의 날짜 차이가 있다면 날짜 노출
                if (prevMatch == null ||
                    isDateDifference(prevMatch.kstDateTime, match.kstDateTime)
                ) {
                    item(key = match.kstDateTime.toString()) {
                        // Match
                        Date(match.kstDateTime)
                    }
                }
                item(key = match.id) {
                    MatchItem(
                        match = match,
                    )
                    if (index == response.matches.size - 1) {
                        Spacer(modifier = Modifier.height(30.dp))
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.height(30.dp))
            }
        }
    }
}

fun isDateDifference(dateTime1: ZonedDateTime, dateTime2: ZonedDateTime): Boolean {
    // ZonedDateTime에서 LocalDate 부분만 추출하여 비교
    val localDate1 = dateTime1.toLocalDate()
    val localDate2 = dateTime2.toLocalDate()

    // 두 날짜가 다른지 확인
    return !localDate1.isEqual(localDate2)
}

@Composable
private fun Date(
    dateTime: ZonedDateTime,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 20.dp, top = 12.dp),
        text = dateTime.format("yyyy년 MM월 dd일 (E)"),
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
    )
}

fun ZonedDateTime.format(pattern: String): String {
    val formatter = DateTimeFormatter.ofPattern(pattern)
    return format(formatter)
}

@Composable
private fun MatchItem(
    match: Match,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = 10.dp,
        shape = RoundedCornerShape(16.dp),
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
                .padding(vertical = 22.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                modifier = Modifier
                    .weight(1f, true)
                    .padding(horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
            ) {
                TeamName(
                    modifier = Modifier.weight(1f, true),
                    name = match.homeTeam.shortName,
                    textAlign = TextAlign.End,
                )
                Spacer(modifier = Modifier.width(6.dp))
                AsyncImage(
                    model = match.homeTeam.crest,
                    contentDescription = "crest",
                    modifier = Modifier.size(40.dp),
                )
            }
            MatchCenterSection(match = match)
            Row(
                modifier = Modifier
                    .weight(1f, true)
                    .padding(horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
            ) {
                AsyncImage(
                    model = match.awayTeam.crest,
                    contentDescription = "crest",
                    modifier = Modifier.size(40.dp),
                )
                Spacer(modifier = Modifier.width(6.dp))
                TeamName(
                    name = match.awayTeam.shortName,
                    modifier = Modifier.weight(1f, true),
                    textAlign = TextAlign.Start,
                )
            }
        }
    }
}

@Composable
private fun MatchCenterSection(
    match: Match,
    modifier: Modifier = Modifier,
) {
    Box(contentAlignment = Alignment.Center, modifier = modifier.fillMaxHeight()) {
        when (match.matchStatus) {
            MatchStatus.Finished -> {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "${match.score.fullTime.home} : ${match.score.fullTime.away}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                    )
                    Text(
                        text = "경기종료",
                        fontSize = 12.sp,
                    )
                }
            }

            MatchStatus.Scheduled -> {
                Text(
                    text = match.kstDateTime.format("HH:mm"),
                    fontWeight = FontWeight.Medium,
                    fontSize = 24.sp,
                )
            }

            MatchStatus.InPlay -> {
                val score =
                    if (match.score.fullTime.home != null && match.score.fullTime.away != null) {
                        "${match.score.fullTime.home} : ${match.score.fullTime.away}"
                    } else if (match.score.halfTime.home != null && match.score.halfTime.away != null) {
                        "${match.score.halfTime.home} : ${match.score.halfTime.away}"
                    } else {
                        null
                    }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    if (score != null) {
                        Text(
                            text = score,
                            fontWeight = FontWeight.Medium,
                            fontSize = 24.sp,
                        )
                    }
                    Text(
                        text = "In Play!",
                        fontWeight = FontWeight.Medium,
                        fontSize = if (score == null) 24.sp else 12.sp,
                        color = Color.Red,
                    )
                }
            }

            else -> {}
        }
    }
}

@Composable
private fun TeamName(
    name: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Unspecified,
) {
    Text(
        text = name,
        modifier = modifier,
        fontWeight = FontWeight.Medium,
        textAlign = textAlign,
        fontSize = 15.sp,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
    )
}

@Composable
private fun MatchTitle(
    response: MatchResponse,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        AsyncImage(
            model = response.competition.emblem,
            contentDescription = "emblem",
            modifier = Modifier.size(30.dp),
        )
        Text(
            modifier = Modifier
                .padding(start = 8.dp),
            text = "${response.competition.name} Match ${response.filters.matchday}",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Preview
@Composable
private fun Preview() {
    MaterialTheme {
        MatchUi(
            state = MatchScreen.State(
                getMatchResponsesAsync = Success(listOf(ResponseMock.matchResponseMock)),
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
