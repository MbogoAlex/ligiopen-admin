package com.admin.ligiopen.ui.screens.match.fixtures

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.admin.ligiopen.ui.theme.LigiopenadminTheme

@Composable
fun FixturesScreenComposable(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .safeDrawingPadding()
    ) {
        FixturesScreen()
    }
}

@Composable
fun FixturesScreen(
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(text = "Fixtures")
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FixturesScreenPreview() {
    LigiopenadminTheme {
        FixturesScreen()
    }
}