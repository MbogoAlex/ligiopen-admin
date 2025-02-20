package com.admin.ligiopen.ui.nav

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
import com.admin.ligiopen.ui.screens.match.location.LocationAdditionScreenComposable
import com.admin.ligiopen.ui.screens.match.location.LocationAdditionScreenDestination
import com.admin.ligiopen.ui.screens.start.SplashScreenComposable
import com.admin.ligiopen.ui.screens.start.SplashScreenDestination

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
    }
}