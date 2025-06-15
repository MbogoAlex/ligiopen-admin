package com.admin.ligiopen.ui.screens.users

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.admin.ligiopen.ui.theme.LigiopenadminTheme
import com.admin.ligiopen.utils.screenHeight
import com.admin.ligiopen.utils.screenWidth
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Tab
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import com.admin.ligiopen.data.room.models.UserAccount
import kotlinx.coroutines.launch

@Composable
fun UsersManagementScreenComposable(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .safeDrawingPadding()
    ) {
        UsersManagementScreen()
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UsersManagementScreen(
    users: List<UserAccount>,
    modifier: Modifier = Modifier
) {
    val roles = listOf("Super Admin", "Team Admin", "Content Admin")
    val pagerState = rememberPagerState(pageCount = {roles.size})

    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                vertical = screenHeight(16.0),
                horizontal = screenWidth(16.0)
            )
    ) {
        ScrollableTabRow(
            selectedTabIndex = pagerState.currentPage,
            containerColor = Color.Transparent,
            edgePadding = screenWidth(16.0),
            contentColor = MaterialTheme.colorScheme.primary,
            divider = {},
            indicator = {tabPositions ->
                Box(
                    modifier = Modifier
                        .tabIndicatorOffset(tabPositions[pagerState.currentPage])
                        .fillMaxWidth()
                        .height(screenHeight(3.0))
                        .background(
                            color = MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(screenWidth(4.0))
                        )
                )
            }
        ) {
            roles.forEachIndexed { index, title ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    modifier = Modifier
                        .padding(horizontal = screenWidth(8.0)),
                    text = {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.labelLarge,
                            maxLines = 1,
                            color = if (pagerState.currentPage == index) {
                                MaterialTheme.colorScheme.primary
                            } else {
                                MaterialTheme.colorScheme.onSurfaceVariant
                            }
                        )
                    }
                )
            }
        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun UsersManagementScreenPreview() {
    LigiopenadminTheme {
        UsersManagementScreen()
    }
}
