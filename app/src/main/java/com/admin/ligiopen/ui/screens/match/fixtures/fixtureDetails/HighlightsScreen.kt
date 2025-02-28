package com.admin.ligiopen.ui.screens.match.fixtures.fixtureDetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.admin.ligiopen.AppViewModelFactory
import com.admin.ligiopen.R
import com.admin.ligiopen.data.network.enums.LoadingStatus
import com.admin.ligiopen.ui.nav.AppNavigation
import com.admin.ligiopen.ui.screens.match.fixtures.fixtureDetails.lineup.PlayersLineupScreenComposable
import com.admin.ligiopen.ui.screens.match.fixtures.fixtureDetails.stats.MatchStatisticsScreenComposable
import com.admin.ligiopen.ui.screens.match.fixtures.fixtureDetails.summary.MatchSummaryComposable
import com.admin.ligiopen.ui.screens.match.fixtures.fixtureDetails.timeline.MatchTimelineScreenComposable
import com.admin.ligiopen.ui.theme.LigiopenadminTheme
import com.admin.ligiopen.utils.screenFontSize
import com.admin.ligiopen.utils.screenWidth

object HighlightsScreenDestination: AppNavigation {
    override val route: String = "highlights-screen"
    override val title: String = "Highlights screen"
    val postMatchId: String = "postMatchId"
    val fixtureId: String = "fixtureId"
    val routeWithPostMatchIdAndFixtureId = "$route/{$postMatchId}/{$fixtureId}"
}

@Composable
fun HighlightsScreenComposable(
    navigateToEventUploadScreen: (fixtureId: String) -> Unit,
    navigateToPreviousScreen: () -> Unit,
    modifier: Modifier = Modifier
) {
    val viewModel: HighlightsScreenViewModel = viewModel(factory = AppViewModelFactory.Factory)
    val uiState by viewModel.uiState.collectAsState()

    val lifecycleOwner = LocalLifecycleOwner.current
    val lifecycleState by lifecycleOwner.lifecycle.currentStateFlow.collectAsState()

    LaunchedEffect(lifecycleState) {
        when(lifecycleState) {
            Lifecycle.State.DESTROYED -> {}
            Lifecycle.State.INITIALIZED -> {}
            Lifecycle.State.CREATED -> {}
            Lifecycle.State.STARTED -> {}
            Lifecycle.State.RESUMED -> {
                viewModel.getInitialData()
            }
        }
    }

    val tabs = listOf(
        HighlightsScreenTabItem(
            name = "Summary",
            tab = HighlightsScreenTabs.SUMMARY,
            icon = R.drawable.full_time2
        ),
        HighlightsScreenTabItem(
            name = "Timeline",
            tab = HighlightsScreenTabs.TIMELINE,
            icon = R.drawable.timeline
        ),
        HighlightsScreenTabItem(
            name = "Lineups",
            tab = HighlightsScreenTabs.LINEUPS,
            icon = R.drawable.lineup_2
        ),
        HighlightsScreenTabItem(
            name = "Stats",
            tab = HighlightsScreenTabs.STATS,
            icon = R.drawable.stats
        ),
//        HighlightsScreenTabItem(
//            name = "Edit",
//            tab = HighlightsScreenTabs.EDIT,
//            icon = R.drawable.edit
//        ),
    )
    var currentTab by rememberSaveable {
        mutableStateOf(HighlightsScreenTabs.SUMMARY)
    }
    ElevatedCard(
        shape = RoundedCornerShape(0.dp),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .safeDrawingPadding()
        ) {
            HighlightsScreen(
                fixtureId = uiState.fixtureId,
                tabs = tabs,
                currentTab = currentTab,
                onChangeTab = {
                    currentTab = it
                },
                navigateToEventUploadScreen = navigateToEventUploadScreen,
                navigateToPreviousScreen = navigateToPreviousScreen
            )
        }
    }

}

@Composable
fun HighlightsScreen(
    fixtureId: String?,
    tabs: List<HighlightsScreenTabItem>,
    currentTab: HighlightsScreenTabs,
    onChangeTab: (tab: HighlightsScreenTabs) -> Unit,
    navigateToEventUploadScreen: (fixtureId: String) -> Unit,
    navigateToPreviousScreen: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        ElevatedCard(
            shape = RoundedCornerShape(0),
        ) {
            Row(
//            horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(screenWidth(x = 8.0))

            ) {
                IconButton(onClick = navigateToPreviousScreen) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Previous screen"
                    )
                }
                Icon(
                    painter = painterResource(id = R.drawable.ligiopen_icon),
                    contentDescription = null,
                )
                Spacer(modifier = Modifier.width(screenWidth(x = 3.0)))
                Text(
                    text = currentTab.name,
                    fontSize = screenFontSize(x = 16.0).sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.weight(1f))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.club1),
                        contentDescription = null,
                        modifier = Modifier
                            .size(screenWidth(x = 24.0))
                    )
                    Text(
                        text = "0",
                        fontSize = screenFontSize(x = 16.0).sp
                    )
                    Spacer(modifier = Modifier.width(screenWidth(x = 4.0)))
                    Text(
                        text = ":",
                        fontSize = screenFontSize(x = 16.0).sp
                    )
                    Spacer(modifier = Modifier.width(screenWidth(x = 4.0)))
                    Text(
                        text = "2",
                        fontSize = screenFontSize(x = 16.0).sp
                    )
                    Spacer(modifier = Modifier.width(screenWidth(x = 4.0)))
                    Image(
                        painter = painterResource(id = R.drawable.club2),
                        contentDescription = null,
                        modifier = Modifier
                            .size(screenWidth(x = 24.0))
                    )
                }
            }
            when(currentTab) {
                HighlightsScreenTabs.SUMMARY -> {
                    MatchSummaryComposable(
                        modifier = Modifier
                            .weight(1f)
                    )
                }
                HighlightsScreenTabs.TIMELINE -> {
                    MatchTimelineScreenComposable(
                        navigateToEventUploadScreen = navigateToEventUploadScreen,
                        fixtureId = fixtureId,
                        modifier = Modifier
                            .weight(1f)
                    )
                }
                HighlightsScreenTabs.LINEUPS -> {
                    PlayersLineupScreenComposable(
                        modifier = Modifier
                            .weight(1f)
                    )
                }
                HighlightsScreenTabs.STATS -> {
                    MatchStatisticsScreenComposable(
                        modifier = Modifier
                            .weight(1f)
                    )
                }

                HighlightsScreenTabs.EDIT -> {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f)
                    ) {
                        Text(text = "Edit")
                    }   
                }
            }
            HighlightScreenBottomBar(
                tabs = tabs,
                currentTab = currentTab,
                onChangeTab = onChangeTab
            )
        }
    }
}

@Composable
fun HighlightScreenBottomBar(
    tabs: List<HighlightsScreenTabItem>,
    currentTab: HighlightsScreenTabs,
    onChangeTab: (tab: HighlightsScreenTabs) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationBar {
        for(tab in tabs) {
            NavigationBarItem(
                label = {
                    Text(
                        text = tab.name,
                        fontSize = screenFontSize(x = 14.0).sp
                    )
                },
                selected = currentTab == tab.tab,
                onClick = {
                    onChangeTab(tab.tab)
                },
                icon = {
                    Icon(
                        painter = painterResource(id = tab.icon),
                        contentDescription = tab.name,
                        modifier = Modifier
                            .size(screenWidth(x = 20.0))
                    )
                }
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HighlightsScreenPreview() {
    val tabs = listOf(
        HighlightsScreenTabItem(
            name = "Summary",
            tab = HighlightsScreenTabs.SUMMARY,
            icon = R.drawable.ball_summary
        ),
        HighlightsScreenTabItem(
            name = "Timeline",
            tab = HighlightsScreenTabs.TIMELINE,
            icon = R.drawable.timeline
        ),
        HighlightsScreenTabItem(
            name = "Lineups",
            tab = HighlightsScreenTabs.LINEUPS,
            icon = R.drawable.lineup
        ),
        HighlightsScreenTabItem(
            name = "Stats",
            tab = HighlightsScreenTabs.STATS,
            icon = R.drawable.stats
        ),
    )
    var currentTab by rememberSaveable {
        mutableStateOf(HighlightsScreenTabs.SUMMARY)
    }
    LigiopenadminTheme {
        HighlightsScreen(
            fixtureId = null,
            tabs = tabs,
            currentTab = currentTab,
            onChangeTab = {
                currentTab = it
            },
            navigateToEventUploadScreen = {},
            navigateToPreviousScreen = {}
        )
    }
}