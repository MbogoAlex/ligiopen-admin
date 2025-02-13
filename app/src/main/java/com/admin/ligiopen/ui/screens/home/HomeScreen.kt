package com.admin.ligiopen.ui.screens.home

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.admin.ligiopen.ui.nav.AppNavigation
import com.admin.ligiopen.ui.screens.match.location.LocationsScreenComposable
import com.admin.ligiopen.ui.theme.LigiopenadminTheme

object HomeScreenDestination: AppNavigation {
    override val title: String = "Home screen"
    override val route: String = "home-screen"
}

@Composable
fun HomeScreenComposable(
    navigateToLoginScreenWithArgs: (email: String, password: String) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    BackHandler(onBack = {(context as Activity).finish()})

    Box(
        modifier = Modifier
            .safeDrawingPadding()
    ) {
        HomeScreen(
            navigateToLoginScreenWithArgs = navigateToLoginScreenWithArgs
        )
    }
}

@Composable
fun HomeScreen(
    navigateToLoginScreenWithArgs: (email: String, password: String) -> Unit,
    modifier: Modifier = Modifier
) {
    LocationsScreenComposable(navigateToLoginScreenWithArgs = navigateToLoginScreenWithArgs)
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    LigiopenadminTheme {
        HomeScreen(
            navigateToLoginScreenWithArgs = { email, password ->}
        )
    }
}