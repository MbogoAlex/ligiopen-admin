package com.admin.ligiopen.ui.screens.users

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.admin.ligiopen.ui.nav.AppNavigation
import com.admin.ligiopen.ui.theme.LigiopenadminTheme
import com.admin.ligiopen.utils.TextFieldComposable
import com.admin.ligiopen.utils.screenFontSize
import com.admin.ligiopen.utils.screenHeight
import com.admin.ligiopen.utils.screenWidth

object ClubSearchScreenDestination: AppNavigation {
    override val title: String = "Club search screen"
    override val route: String = "club-search-screen"
    val userId: String = "userId"
    val routeWithUserId: String = "$route/{$userId}"
}

@Composable
fun ClubSearchScreenComposable(
    navigateToPreviousScreen: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .safeDrawingPadding()
    ) {
        ClubSearchScreen(
            navigateToPreviousScreen = navigateToPreviousScreen
        )
    }
}

@Composable
fun ClubSearchScreen(
    navigateToPreviousScreen: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                vertical = screenHeight(16.0),
                horizontal = screenWidth(16.0)
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            IconButton(
                onClick = navigateToPreviousScreen
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Previous screen"
                )
            }
            Spacer(modifier = Modifier.width(screenWidth(4.0)))
            Text(
                text = "Select club",
                fontSize = screenFontSize(16.0).sp
            )
        }
        TextFieldComposable(
            label = "Search club",
            value = "",
            onValueChange = {},
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Text
            ),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "Continue"
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ClubSearchScreenPreview() {
    LigiopenadminTheme {
        ClubSearchScreen(
            navigateToPreviousScreen = {}
        )
    }
}