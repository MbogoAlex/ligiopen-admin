package com.admin.ligiopen.ui.screens.match.fixtures.fixtureDetails.timeline

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.admin.ligiopen.R
import com.admin.ligiopen.ui.theme.LigiopenadminTheme

@Composable
fun MatchTimelineScreenComposable(
    fixtureId: String?,
    navigateToEventUploadScreen: (fixtureId: String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        MatchTimelineScreen(
            fixtureId = fixtureId,
            navigateToEventUploadScreen = navigateToEventUploadScreen
        )
    }
}

@Composable
fun MatchTimelineScreen(
    fixtureId: String?,
    navigateToEventUploadScreen: (fixtureId: String) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { navigateToEventUploadScreen(fixtureId!!) }) {
                Icon(
                    painter = painterResource(id = R.drawable.edit),
                    contentDescription = "Edit commentary"
                )
            }
        }
    ) {
        Box(
            modifier = Modifier
                .padding(it)
        ) {

        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MatchTimelineScreenPreview() {
    LigiopenadminTheme {
        MatchTimelineScreen(
            fixtureId = null,
            navigateToEventUploadScreen = {}
        )
    }
}