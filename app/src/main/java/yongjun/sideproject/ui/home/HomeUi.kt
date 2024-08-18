package yongjun.sideproject.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import kotlinx.coroutines.launch
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Named
import yongjun.sideproject.domain.mock.StandingResponseMock
import yongjun.sideproject.domain.model.StandingResponse
import yongjun.sideproject.domain.model.Table
import yongjun.sideproject.ui.utils.Success
import yongjun.sideproject.ui.utils.uiFactory

@Composable
fun HomeUi(
    state: HomeScreen.State,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
    ) { innerPadding ->
        val standingResponses = state.getStandingResponsesAsync()
        if (standingResponses != null) {
            StandingsSection(
                standingsResponses = standingResponses,
                lastUpdatedAt = state.lastUpdatedAt,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
            )
        } else {
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .statusBarsPadding(),
            ) {
                Button(onClick = { state.eventSink(HomeScreen.Event.RetryClick) }) {
                    Text(text = "retry!!!!!")
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun StandingsSection(
    lastUpdatedAt: String?,
    standingsResponses: List<StandingResponse>,
    modifier: Modifier = Modifier,
) {
    val coroutineScope = rememberCoroutineScope()
    val primaryColor = Color(0xffff8787)
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(primaryColor),
    ) {
        val selectedTabIndex = remember { mutableIntStateOf(0) }
        val competitions = standingsResponses.map { it.competition }
        val pagerState = rememberPagerState(
            initialPage = 0,
            pageCount = { standingsResponses.size },
        )
        LaunchedEffect(pagerState.targetPage) {
            selectedTabIndex.intValue = pagerState.targetPage
        }
        TabRow(
            modifier = Modifier
                .statusBarsPadding()
                .fillMaxWidth()
                .heightIn(min = 50.dp),
            backgroundColor = primaryColor,
            selectedTabIndex = selectedTabIndex.intValue,
        ) {
            competitions.forEachIndexed { index, competition ->
                Tab(
                    selected = selectedTabIndex.intValue == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.scrollToPage(index)
                            selectedTabIndex.intValue = index
                        }
                    },
                ) {
                    Text(text = competition.code)
                }
            }
        }

        lastUpdatedAt?.let {
            LastUpdateSection(lastUpdatedAt = it)
        }

        HorizontalPager(
            modifier = Modifier.fillMaxSize(),
            state = pagerState,
        ) { page ->
            TablesSection(
                tables = standingsResponses[page].totalStanding.tables,
            )
        }
    }
}

@Composable
private fun LastUpdateSection(
    lastUpdatedAt: String,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.surface)
            .padding(top = 4.dp, end = 8.dp),
        text = lastUpdatedAt,
        textAlign = TextAlign.End,
    )
}

@Composable
private fun TablesSection(
    tables: List<Table>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(top = 8.dp, bottom = 20.dp),
    ) {
        items(
            items = tables,
            key = { it.team.id },
        ) { table ->
            Table(table)
        }
        item {
            Spacer(modifier = Modifier.navigationBarsPadding())
        }
    }
}

@Composable
private fun Table(
    table: Table,
    modifier: Modifier = Modifier,
) {
    val backgroundColor = when (table.position) {
        1 -> Color(0xfffd7e14)
        2 -> Color(0xffffa94d)
        3 -> Color(0xffffc078)
        4 -> Color(0xffffe8cc)
        else -> MaterialTheme.colors.surface
    }
    Card(
        modifier = modifier.padding(horizontal = 16.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 8.dp,
        backgroundColor = backgroundColor,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            // 랭킹 숫자
            Text(
                text = table.position.toString(),
                fontWeight = FontWeight.ExtraBold,
                fontSize = 30.sp,
            )
            AsyncImage(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .size(35.dp),
                model = table.team.crest,
                contentDescription = "logo",
            )
            Column(
                modifier = Modifier
                    .weight(1f, true)
                    .padding(horizontal = 8.dp),
            ) {
                Text(
                    text = table.team.shortName,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                if (table.form != null) {
                    Text(text = table.form, fontSize = 12.sp)
                }
            }
            PointsRow(table = table)
        }
    }
}

@Composable
private fun PointsRow(
    table: Table,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(3.dp)) {
        PointColumn(title = "P", value = table.playedGames)
        PointColumn(title = "W", value = table.won)
        PointColumn(title = "D", value = table.draw)
        PointColumn(title = "L", value = table.lost)
        PointColumn(
            title = "Pts",
            value = table.points,
            titleFontWeight = FontWeight.Bold,
            valueFontWeight = FontWeight.Bold,
        )
    }
}

@Composable
private fun PointColumn(
    title: String,
    value: Int,
    modifier: Modifier = Modifier,
    titleFontWeight: FontWeight = FontWeight.Medium,
    valueFontWeight: FontWeight = FontWeight.Normal,
) {
    Column(
        modifier = modifier.widthIn(min = 18.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Text(text = title, fontSize = 14.sp, fontWeight = titleFontWeight, maxLines = 1)
        Text(
            text = value.toString(),
            fontSize = 13.sp,
            fontWeight = valueFontWeight,
        )
    }
}

@Preview
@Composable
private fun Preview() {
    MaterialTheme {
        HomeUi(
            state = HomeScreen.State(
                getStandingResponsesAsync = Success(StandingResponseMock.standingResponsesMock),
                lastUpdatedAt = "dd일 HH시 mm분",
                eventSink = {},
            ),
        )
    }
}

@Named("Home")
@Factory
fun provideHomeUiFactory(): Ui.Factory =
    uiFactory<HomeScreen, HomeScreen.State> { _, state, modifier ->
        HomeUi(state, modifier)
    }
