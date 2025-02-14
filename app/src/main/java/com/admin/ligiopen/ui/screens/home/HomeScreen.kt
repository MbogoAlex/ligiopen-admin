package com.admin.ligiopen.ui.screens.home

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.admin.ligiopen.R
import com.admin.ligiopen.ui.nav.AppNavigation
import com.admin.ligiopen.ui.screens.match.clubs.ClubsScreenComposable
import com.admin.ligiopen.ui.screens.match.fixtures.FixturesScreenComposable
import com.admin.ligiopen.ui.screens.match.location.LocationsScreenComposable
import com.admin.ligiopen.ui.screens.news.NewsScreenComposable
import com.admin.ligiopen.ui.theme.LigiopenadminTheme
import com.admin.ligiopen.utils.screenFontSize

object HomeScreenDestination: AppNavigation {
    override val title: String = "Home screen"
    override val route: String = "home-screen"
}

@Composable
fun HomeScreenComposable(
    navigateToLoginScreenWithArgs: (email: String, password: String) -> Unit,
    navigateToLocationAdditionScreen: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    BackHandler(onBack = {(context as Activity).finish()})

    val tabs = listOf(
        HomeTab(
            title = "Clubs",
            icon = R.drawable.football_club,
            tab = HomeTabs.CLUBS
        ),
        HomeTab(
            title = "Fixtures",
            icon = R.drawable.fixtures,
            tab = HomeTabs.FIXTURES
        ),
        HomeTab(
            title = "Venues",
            icon = R.drawable.stadium,
            tab = HomeTabs.VENUES
        ),
        HomeTab(
            title = "News",
            icon = R.drawable.news,
            tab = HomeTabs.NEWS
        ),

    )

    var selectedTab by rememberSaveable {
        mutableStateOf(HomeTabs.CLUBS)
    }

    Box(
        modifier = Modifier
            .safeDrawingPadding()
    ) {
        HomeScreen(
            selectedTab = selectedTab,
            tabs = tabs,
            onChangeTab = {
                selectedTab = it
            },
            navigateToLoginScreenWithArgs = navigateToLoginScreenWithArgs,
            navigateToLocationAdditionScreen = navigateToLocationAdditionScreen
        )
    }
}

@Composable
fun HomeScreen(
    selectedTab: HomeTabs,
    tabs: List<HomeTab>,
    onChangeTab: (tab: HomeTabs) -> Unit,
    navigateToLoginScreenWithArgs: (email: String, password: String) -> Unit,
    navigateToLocationAdditionScreen: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        when(selectedTab) {
            HomeTabs.CLUBS -> {
                ClubsScreenComposable(
                    navigateToLoginScreenWithArgs = navigateToLoginScreenWithArgs,
                    modifier = Modifier
                        .weight(1f)
                )
            }
            HomeTabs.FIXTURES -> {
                FixturesScreenComposable(
                    modifier = Modifier
                        .weight(1f)
                )
            }
            HomeTabs.VENUES -> {
                LocationsScreenComposable(
                    navigateToLoginScreenWithArgs = navigateToLoginScreenWithArgs,
                    navigateToLocationAdditionScreen = navigateToLocationAdditionScreen,
                    modifier = Modifier
                        .weight(1f)
                )
            }

            HomeTabs.NEWS -> {
                NewsScreenComposable(
                    modifier = Modifier
                        .weight(1f)
                )
            }
        }
        HomeBottomNavBar(
            selectedTab = selectedTab,
            tabs = tabs,
            onChangeTab = onChangeTab
        )
    }
}

@Composable
fun HomeBottomNavBar(
    selectedTab: HomeTabs,
    tabs: List<HomeTab>,
    onChangeTab: (tab: HomeTabs) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier
    ) {
        for(tab in tabs) {
            NavigationBarItem(
                label = {
                    Text(
                        text = tab.title,
                        fontSize = screenFontSize(x = 14.0).sp
                    )
                },
                selected = selectedTab == tab.tab,
                onClick = {
                    onChangeTab(tab.tab)
                },
                icon = {
                    Icon(
                        painter = painterResource(id = tab.icon),
                        contentDescription = tab.title
                    )
                }
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    LigiopenadminTheme {
        val tabs = listOf(
            HomeTab(
                title = "Clubs",
                icon = R.drawable.football_club,
                tab = HomeTabs.CLUBS
            ),
            HomeTab(
                title = "Fixtures",
                icon = R.drawable.fixtures,
                tab = HomeTabs.FIXTURES
            ),
            HomeTab(
                title = "Venues",
                icon = R.drawable.stadium,
                tab = HomeTabs.VENUES
            ),

            )

        var selectedTab by rememberSaveable {
            mutableStateOf(HomeTabs.CLUBS)
        }
        HomeScreen(
            selectedTab = selectedTab,
            tabs = tabs,
            onChangeTab = {
                selectedTab = it
            },
            navigateToLoginScreenWithArgs = {email, password ->  },
            navigateToLocationAdditionScreen = { /*TODO*/ }
        )
    }
}