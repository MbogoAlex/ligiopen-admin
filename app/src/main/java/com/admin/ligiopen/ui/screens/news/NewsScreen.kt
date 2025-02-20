package com.admin.ligiopen.ui.screens.news

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.admin.ligiopen.ui.theme.LigiopenadminTheme

@Composable
fun NewsScreenComposable(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        NewsScreen()
    }
}

@Composable
fun NewsScreen(

    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(text = "News")
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NewsScreenPreview() {
    LigiopenadminTheme {
        NewsScreen()
    }
}