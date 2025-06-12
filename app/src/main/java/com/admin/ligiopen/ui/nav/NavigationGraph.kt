package com.admin.ligiopen.ui.nav

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.admin.ligiopen.ui.screens.auth.LoginScreenComposable
import com.admin.ligiopen.ui.screens.auth.LoginScreenDestination
import com.admin.ligiopen.ui.screens.home.HomeScreenComposable
import com.admin.ligiopen.ui.screens.home.HomeScreenDestination
import com.admin.ligiopen.ui.screens.match.clubs.ClubAdditionScreenComposable
import com.admin.ligiopen.ui.screens.match.clubs.ClubAdditionScreenDestination
import com.admin.ligiopen.ui.screens.match.clubs.ClubDetailsScreenComposable
import com.admin.ligiopen.ui.screens.match.clubs.ClubDetailsScreenDestination
import com.admin.ligiopen.ui.screens.match.clubs.ClubUpdateScreenComposable
import com.admin.ligiopen.ui.screens.match.clubs.ClubUpdateScreenDestination
import com.admin.ligiopen.ui.screens.match.fixtures.fixtureDetails.HighlightsScreenComposable
import com.admin.ligiopen.ui.screens.match.fixtures.fixtureDetails.HighlightsScreenDestination
import com.admin.ligiopen.ui.screens.match.fixtures.fixtureDetails.commentary.EventUploadScreenComposable
import com.admin.ligiopen.ui.screens.match.fixtures.fixtureDetails.commentary.EventUploadScreenDestination
import com.admin.ligiopen.ui.screens.match.location.LocationAdditionScreenComposable
import com.admin.ligiopen.ui.screens.match.location.LocationAdditionScreenDestination
import com.admin.ligiopen.ui.screens.news.NewsDetailsScreenComposable
import com.admin.ligiopen.ui.screens.news.NewsDetailsScreenDestination
import com.admin.ligiopen.ui.screens.news.newsManagement.NewsAdditionScreenComposable
import com.admin.ligiopen.ui.screens.news.newsManagement.NewsAdditionScreenDestination
import com.admin.ligiopen.ui.screens.news.newsManagement.NewsItemAdditionScreenComposable
import com.admin.ligiopen.ui.screens.news.newsManagement.NewsItemAdditionScreenDestination
import com.admin.ligiopen.ui.screens.start.SplashScreenComposable
import com.admin.ligiopen.ui.screens.start.SplashScreenDestination

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationGraph(
    navController: NavHostController,
    onSwitchTheme: () -> Unit,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = SplashScreenDestination.route,
    ) {
        composable(SplashScreenDestination.route) {
            SplashScreenComposable(
                navigateToHomeScreen = {
                    navController.navigate(HomeScreenDestination.route)
                },
                navigateToLoginScreen = {
                    navController.navigate(LoginScreenDestination.route)
                }
            )
        }

        composable(LoginScreenDestination.route) {
            LoginScreenComposable(
                navigateToHomeScreen = {
                    navController.navigate(HomeScreenDestination.route)
                },
            )
        }
        composable(
            LoginScreenDestination.routeWithArgs,
            arguments = listOf(
                navArgument(LoginScreenDestination.email) {
                    type = NavType.StringType
                },
                navArgument(LoginScreenDestination.password) {
                    type = NavType.StringType
                }

            )
        ) {
            LoginScreenComposable(
                navigateToHomeScreen = {
                    navController.navigate(HomeScreenDestination.route)
                },
            )
        }
        composable(HomeScreenDestination.route) {
            HomeScreenComposable(
                navigateToLoginScreenWithArgs = {email, password ->
                    navController.navigate("${LoginScreenDestination.route}/$email/$password")
                },
                navigateToLocationAdditionScreen = {
                    navController.navigate(LocationAdditionScreenDestination.route)
                },
                navigateToClubAdditionScreen = {
                    navController.navigate(ClubAdditionScreenDestination.route)
                },
                navigateToPostMatchScreen = {postMatchId, fixtureId, locationId ->
                    navController.navigate("${HighlightsScreenDestination.route}/${postMatchId}/${fixtureId}/${locationId}")
                },
                navigateToNewsDetailsScreen = {
                    navController.navigate("${NewsDetailsScreenDestination.route}/${it}")
                },
                navigateToNewsAdditionScreen = {
                    navController.navigate(NewsAdditionScreenDestination.route)
                },
                navigateToClubDetailsScreen = {
                    navController.navigate("${ClubDetailsScreenDestination.route}/${it}")
                }
            )
        }
        composable(LocationAdditionScreenDestination.route) {
            LocationAdditionScreenComposable(
                navigateToPreviousScreen = {
                    navController.navigateUp()
                }
            )
        }

        composable(ClubAdditionScreenDestination.route) {
            ClubAdditionScreenComposable(
                navigateToPreviousScreen = {
                    navController.navigateUp()
                }
            )
        }

        composable(
            HighlightsScreenDestination.routeWithPostMatchIdAndFixtureIdAndLocationId,
            arguments = listOf(
                navArgument(HighlightsScreenDestination.postMatchId) {
                    type = NavType.StringType
                },
                navArgument(HighlightsScreenDestination.fixtureId) {
                    type = NavType.StringType
                }
            )
        ) {
            HighlightsScreenComposable(
                navigateToEventUploadScreen = {
                    navController.navigate("${EventUploadScreenDestination.route}/${it}")
                },
                navigateToPreviousScreen = {
                    navController.navigateUp()
                }
            )
        }
        composable(
            EventUploadScreenDestination.routeWithFixtureId,
            arguments = listOf(
                navArgument(EventUploadScreenDestination.matchFixtureId) {
                    type = NavType.StringType
                }
            )
        ) {
            EventUploadScreenComposable(
                navigateToPreviousScreen = {
                    navController.navigateUp()
                }
            )
        }
        composable(
            NewsDetailsScreenDestination.routeWithNewsId,
            arguments = listOf(
                navArgument(NewsDetailsScreenDestination.newsId) {
                    type = NavType.StringType
                }
            )
        ) {
            NewsDetailsScreenComposable(
                navigateToPreviousScreen = {
                    navController.navigateUp()
                },
                navigateToNewsItemAdditionScreen = {
                    navController.navigate("${NewsItemAdditionScreenDestination.route}/${it}")
                }
            )
        }
        composable(NewsAdditionScreenDestination.route) {
            NewsAdditionScreenComposable(
                navigateToNewsDetailsScreen = {
                    navController.navigate("${NewsDetailsScreenDestination.route}/${it}")
                },
                navigateToPreviousScreen = {
                    navController.navigateUp()
                }
            )
        }
        composable(
            NewsItemAdditionScreenDestination.routeWithNewsId,
            arguments = listOf(
                navArgument(NewsItemAdditionScreenDestination.newsId) {
                    type = NavType.StringType
                }
            )
        ) {
            NewsItemAdditionScreenComposable(
                navigateToPreviousScreen = {
                    navController.navigateUp()
                }
            )
        }

        composable(
            ClubDetailsScreenDestination.routeWithClubId,
            arguments = listOf(
                navArgument(ClubDetailsScreenDestination.clubId) {
                    type = NavType.StringType
                }
            )
        ) {
            ClubDetailsScreenComposable(
                navigateToPreviousScreen = {
                    navController.navigateUp()
                },
                navigateToClubUpdateScreen = {
                    navController.navigate("${ClubUpdateScreenDestination.route}/${it}")
                }
            )
        }

        composable(
            ClubUpdateScreenDestination.routeWithClubId,
            arguments = listOf(
                navArgument(ClubUpdateScreenDestination.clubId) {
                    type = NavType.StringType
                }
            )
        ) {
            ClubUpdateScreenComposable(
                navigateToPreviousScreen = {
                    navController.navigateUp()
                }
            )
        }
    }
}