package com.admin.ligiopen.ui.screens.home

import android.app.Activity
import android.os.Build
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.admin.ligiopen.R
import com.admin.ligiopen.ui.nav.AppNavigation
import com.admin.ligiopen.ui.screens.match.clubs.ClubsScreenComposable
import com.admin.ligiopen.ui.screens.match.fixtures.FixturesScreenComposable
import com.admin.ligiopen.ui.screens.match.location.LocationsScreenComposable
import com.admin.ligiopen.ui.screens.news.NewsScreenComposable
import com.admin.ligiopen.ui.screens.review.ContentReviewDashboardScreenComposable
import com.admin.ligiopen.ui.theme.LigiopenadminTheme
import com.admin.ligiopen.utils.screenFontSize
import com.admin.ligiopen.utils.screenHeight
import com.admin.ligiopen.utils.screenWidth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

object HomeScreenDestination: AppNavigation {
    override val title: String = "Home screen"
    override val route: String = "home-screen"
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreenComposable(
    navigateToLoginScreenWithArgs: (email: String, password: String) -> Unit,
    navigateToLocationAdditionScreen: () -> Unit,
    navigateToClubAdditionScreen: () -> Unit,
    navigateToPostMatchScreen: (postMatchId: String, fixtureId: String, locationId: String) -> Unit,
    navigateToNewsDetailsScreen: (newsId: String) -> Unit,
    navigateToNewsAdditionScreen: () -> Unit,
    navigateToClubDetailsScreen: (clubId: String) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    BackHandler(onBack = {(context as Activity).finish()})

    val role = UserRole.TEAM_ADMIN

    val tabs = when(role) {
        UserRole.SUPER_ADMIN -> {
            listOf(
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
                HomeTab(
                    title = "Users management",
                    icon = R.drawable.content_review,
                    tab = HomeTabs.USERS
                ),

            )
        }
        UserRole.TEAM_ADMIN -> {

            listOf(
                HomeTab(
                    title = "My Clubs",
                    icon = R.drawable.football_club,
                    tab = HomeTabs.CLUBS
                ),
                HomeTab(
                    title = "Fixtures",
                    icon = R.drawable.fixtures,
                    tab = HomeTabs.FIXTURES
                ),
            )

        }
        UserRole.CONTENT_ADMIN -> {
            listOf(
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
        }
    }

    var selectedTab by rememberSaveable {
        mutableStateOf(HomeTabs.CLUBS)
    }

    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    Box(
        modifier = Modifier
            .safeDrawingPadding()
    ) {
        HomeScreen(
            drawerState = drawerState,
            scope = scope,
            selectedTab = selectedTab,
            tabs = tabs,
            onChangeTab = {
                selectedTab = it
            },
            navigateToLoginScreenWithArgs = navigateToLoginScreenWithArgs,
            navigateToLocationAdditionScreen = navigateToLocationAdditionScreen,
            navigateToClubAdditionScreen = navigateToClubAdditionScreen,
            navigateToPostMatchScreen = navigateToPostMatchScreen,
            navigateToNewsDetailsScreen = navigateToNewsDetailsScreen,
            navigateToNewsAdditionScreen = navigateToNewsAdditionScreen,
            navigateToClubDetailsScreen = navigateToClubDetailsScreen
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    drawerState: DrawerState?,
    scope: CoroutineScope?,
    selectedTab: HomeTabs,
    tabs: List<HomeTab>,
    onChangeTab: (tab: HomeTabs) -> Unit,
    navigateToLoginScreenWithArgs: (email: String, password: String) -> Unit,
    navigateToLocationAdditionScreen: () -> Unit,
    navigateToClubAdditionScreen: () -> Unit,
    navigateToPostMatchScreen: (postMatchId: String, fixtureId: String, locationId: String) -> Unit,
    navigateToNewsDetailsScreen: (newsId: String) -> Unit,
    navigateToNewsAdditionScreen: () -> Unit,
    navigateToClubDetailsScreen: (clubId: String) -> Unit,
    modifier: Modifier = Modifier
) {
    ModalNavigationDrawer(
        drawerState = drawerState!!,
        drawerContent = {
            ModalDrawerSheet {
                Column(
                    modifier = Modifier
                        .padding(screenWidth(x = 10.0))
                ) {
                    Spacer(modifier = Modifier.height(screenHeight(x = 10.0)))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(
                                horizontal = screenWidth(x = 16.0)
                            )
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.account_circle),
                            contentDescription = null,
                            modifier = Modifier
                                .size(screenWidth(x = 40.0))
                        )
                        Spacer(modifier = Modifier.width(screenWidth(x = 5.0)))
                        Text(
                            text = "Ligiopen: Admin",
                            fontSize = screenFontSize(x = 14.0).sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.weight(1f))
//                        ThemeSwitcher(
//                            darkTheme = darkTheme,
//                            size = screenWidth(x = 30.0),
//                            padding = screenWidth(x = 5.0),
//                            onClick = onSwitchTheme,
//                            modifier = Modifier
//                                .padding(
//                                    end = screenWidth(x = 8.0)
//                                )
//                        )
                    }
                    Spacer(modifier = Modifier.height(screenHeight(x = 15.0)))
                    HorizontalDivider()
                    Spacer(modifier = Modifier.height(screenHeight(x = 15.0)))
                    Column(
                        modifier = Modifier
                            .verticalScroll(rememberScrollState())
                    ) {
                        for(tab in tabs) {
                            NavigationDrawerItem(
                                label = {
                                    Row {
                                        Icon(
                                            painter = painterResource(id = tab.icon),
                                            contentDescription = tab.title,
                                            modifier = Modifier
                                                .size(screenWidth(x = 24.0))
                                        )
                                        Spacer(modifier = Modifier.width(screenWidth(x = 5.0)))
                                        Text(
                                            text = tab.title,
                                            fontSize = screenFontSize(x = 14.0).sp,
                                        )
                                    }
                                },
                                selected = selectedTab == tab.tab,
                                onClick = {
                                    onChangeTab(tab.tab)
                                    scope!!.launch {
                                        drawerState.close()
                                    }
                                }
                            )
                        }

                        Spacer(modifier = Modifier.height(screenHeight(x = 8.0)))
                    }
                }
            }
        }
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = screenWidth(x = 16.0)
                    )
            ) {
                IconButton(onClick = {
                    scope!!.launch {
                        if(drawerState.isClosed) drawerState.open() else drawerState.close()
                    }
                }) {
                    Icon(
                        tint = Color.Gray,
                        painter = painterResource(id = R.drawable.menu_icon),
                        contentDescription = "Menu",
                        modifier = Modifier
                            .size(screenWidth(x = 24.0))
                    )
                }
                Spacer(modifier = Modifier.width(screenWidth(4.0)))
                Text(
                    text = "ADMIN / ${selectedTab.name.replace("_", " ").replaceFirstChar { it.uppercase() }}",
                    fontSize = screenFontSize(x = 14.0).sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = {}) {
                    Icon(
                        painter = painterResource(id = R.drawable.account_circle),
                        contentDescription = "Account info",
                        modifier = Modifier
                            .size(screenWidth(x = 24.0))
                    )
                }
            }
            when(selectedTab) {
                HomeTabs.CLUBS -> {
                    ClubsScreenComposable(
                        navigateToLoginScreenWithArgs = navigateToLoginScreenWithArgs,
                        navigateToClubAdditionScreen = navigateToClubAdditionScreen,
                        navigateToClubDetailsScreen = navigateToClubDetailsScreen,
                        modifier = Modifier
                            .weight(1f)
                    )
                }
                HomeTabs.FIXTURES -> {
                    FixturesScreenComposable(
                        navigateToLoginScreenWithArgs = navigateToLoginScreenWithArgs,
                        navigateToPostMatchScreen = navigateToPostMatchScreen,
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
                        navigateToNewsDetailsScreen = navigateToNewsDetailsScreen,
                        navigateToNewsAdditionScreen = navigateToNewsAdditionScreen,
                        modifier = Modifier
                            .weight(1f)
                    )
                }

                HomeTabs.USERS -> {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f)
                    ) {
                        Text(
                            text = "Users management"
                        )
                    }
                }
            }
        }

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
            drawerState = null,
            scope = null,
            selectedTab = selectedTab,
            tabs = tabs,
            onChangeTab = {
                selectedTab = it
            },
            navigateToLoginScreenWithArgs = {email, password ->  },
            navigateToClubAdditionScreen = {},
            navigateToLocationAdditionScreen = { /*TODO*/ },
            navigateToPostMatchScreen = {postMatchId, fixtureId, locationId ->  },
            navigateToNewsDetailsScreen = {},
            navigateToNewsAdditionScreen = {},
            navigateToClubDetailsScreen = {}
        )
    }
}