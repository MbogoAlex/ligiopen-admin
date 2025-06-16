package com.admin.ligiopen.ui.screens.users

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.admin.ligiopen.AppViewModelFactory
import com.admin.ligiopen.data.network.models.user.UserDto
import com.admin.ligiopen.data.network.models.user.users
import com.admin.ligiopen.ui.nav.AppNavigation
import com.admin.ligiopen.ui.theme.LigiopenadminTheme
import com.admin.ligiopen.utils.TextFieldComposable
import com.admin.ligiopen.utils.screenFontSize
import com.admin.ligiopen.utils.screenHeight
import com.admin.ligiopen.utils.screenWidth

object UserSearchScreenDestination: AppNavigation {
    override val title: String = "User search screen"
    override val route: String = "user-search-screen"
    val adminType: String = "adminType"
    val routeWithAdminType: String = "$route/{$adminType}"
}

@Composable
fun UserSearchScreenComposable(
    navigateToClubSearchScreen: () -> Unit,
    navigateToPreviousScreen: () -> Unit,
    modifier: Modifier = Modifier
) {
    val viewModel: UserSearchViewModel = viewModel(factory = AppViewModelFactory.Factory)
    val uiState by viewModel.uiState.collectAsState()

    var showSetAdminTypeDialog by rememberSaveable {
        mutableStateOf(false)
    }

    if(showSetAdminTypeDialog) {
        AlertDialog(
            title = {
                Text(
                    text = "${uiState.adminType} set",
                    fontSize = screenFontSize(16.0).sp
                )
            },
            text = {
                Text(
                    text = "Confirm ${uiState.selectedUsername} to be ${uiState.adminType}",
                    fontSize = screenFontSize(14.0).sp
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.setAdmin()
                        showSetAdminTypeDialog = false
                    }
                ) {
                    Text(
                        text = "Confirm",
                        fontSize = screenFontSize(14.0).sp
                    )
                }
            },
            onDismissRequest = {
                showSetAdminTypeDialog = false
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showSetAdminTypeDialog = false
                    }
                ) {
                    Text(
                        text = "Cancel",
                        fontSize = screenFontSize(14.0).sp
                    )
                }
            }
        )
    }

    Box(
        modifier = Modifier
            .safeDrawingPadding()
    ) {
        UserSearchScreen(
            adminType = uiState.adminType ?: "",
            users = uiState.users,
            username = uiState.username,
            selectedUserId = uiState.selectedUserId,
            onSelectUser = { userId, username ->
                viewModel.selectUser(userId, username)
            },
            onChangeName = viewModel::changeUsername,
            onContinue = {
                if (uiState.adminType != "team-admin") {
                    showSetAdminTypeDialog = true
                } else {
                    navigateToClubSearchScreen()
                }
            },
            navigateToPreviousScreen = navigateToPreviousScreen,
            modifier = modifier
        )
    }
}

@Composable
fun UserSearchScreen(
    adminType: String,
    users: List<UserDto>,
    username: String,
    selectedUserId: Int,
    onChangeName: (name: String) -> Unit,
    onContinue: () -> Unit,
    onSelectUser: (userId: Int, username: String) -> Unit,
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
            .then(modifier)
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
                text = "Select user",
                fontSize = screenFontSize(16.0).sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextFieldComposable(
            label = "Search user",
            value = username,
            onValueChange = onChangeName,
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

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Available Users",
            fontSize = screenFontSize(14.0).sp,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            items(users) { user ->
                if (user.role != adminType) {
                    UserItem(
                        user = user,
                        isSelected = user.id == selectedUserId,
                        onSelect = { onSelectUser(user.id, user.username) },
                        modifier = Modifier
                            .padding(vertical = 4.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onContinue,
            enabled = selectedUserId != -1,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "Continue",
                fontSize = screenFontSize(14.0).sp
            )
        }
    }
}

@Composable
private fun UserItem(
    user: UserDto,
    isSelected: Boolean,
    onSelect: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .clickable(onClick = onSelect)
            .background(
                if (isSelected) MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                else MaterialTheme.colorScheme.surface
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Column {
                Text(
                    text = user.username,
                    fontSize = screenFontSize(14.0).sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = user.email,
                    fontSize = screenFontSize(12.0).sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
                Text(
                    text = "Role: ${user.role}",
                    fontSize = screenFontSize(12.0).sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            }

            Checkbox(
                checked = isSelected,
                onCheckedChange = { if (it) onSelect() }
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun UserSearchScreenPreview() {
    LigiopenadminTheme {
        UserSearchScreen(
            adminType = "club-admin",
            users = users,
            username = "test",
            selectedUserId = 1,
            onChangeName = {},
            onContinue = {},
            onSelectUser = { _, _ -> },
            navigateToPreviousScreen = {}
        )
    }
}