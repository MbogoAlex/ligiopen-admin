package com.admin.ligiopen.ui.screens.match.clubs

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.admin.ligiopen.AppViewModelFactory
import com.admin.ligiopen.R
import com.admin.ligiopen.data.network.enums.LoadingStatus
import com.admin.ligiopen.data.network.models.club.ClubData
import com.admin.ligiopen.data.network.models.club.clubs
import com.admin.ligiopen.ui.theme.LigiopenadminTheme
import com.admin.ligiopen.utils.screenFontSize
import com.admin.ligiopen.utils.screenHeight
import com.admin.ligiopen.utils.screenWidth

@Composable
fun ClubsScreenComposable(
    navigateToLoginScreenWithArgs: (email: String, password: String) -> Unit,
    navigateToClubAdditionScreen: () -> Unit,
    modifier: Modifier = Modifier
) {

    val viewModel: ClubsViewModel = viewModel(factory = AppViewModelFactory.Factory)
    val uiState by viewModel.uiState.collectAsState()

    val lifecycleOwner = LocalLifecycleOwner.current
    val lifecycleState by lifecycleOwner.lifecycle.currentStateFlow.collectAsState()

    LaunchedEffect(lifecycleState) {
        when(lifecycleState) {
            Lifecycle.State.DESTROYED -> {}
            Lifecycle.State.INITIALIZED -> {}
            Lifecycle.State.CREATED -> {}
            Lifecycle.State.STARTED -> {}
            Lifecycle.State.RESUMED -> {
                viewModel.getInitialData()
            }
        }
    }

    if(uiState.loadingStatus == LoadingStatus.FAIL) {
        if(uiState.unauthorized) {
            val email = uiState.userAccount.email
            val password = uiState.userAccount.password

            navigateToLoginScreenWithArgs(email, password)
        }
        viewModel.resetStatus()
    }

    Box(
        modifier = modifier
            .safeDrawingPadding()
    ) {
        ClubsScreen(
            clubs = uiState.clubs,
            loadingStatus = uiState.loadingStatus,
            navigateToClubAdditionScreen = navigateToClubAdditionScreen
        )
    }
}

@Composable
fun ClubsScreen(
    clubs: List<ClubData>,
    loadingStatus: LoadingStatus,
    navigateToClubAdditionScreen: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = navigateToClubAdditionScreen) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add new club"
                )
            }
        }
    ) {
        Box(
            modifier = Modifier
                .padding(it)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        vertical = screenHeight(x = 16.0),
                        horizontal = screenWidth(x = 16.0)
                    )
            ) {
                TextField(
                    label = {
                        Text(
                            text = "Club name",
                            fontSize = screenFontSize(x = 14.0).sp
                        )
                    },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.search),
                            contentDescription = null
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    value = "",
                    shape = RoundedCornerShape(
                        screenWidth(x = 10.0)
                    ),
                    onValueChange = {},
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(screenHeight(x = 16.0)))
                Column {
                    Card(
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                    .padding(screenWidth(x = 8.0))
                        ) {
                            Text(text = "Nairobi")
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowDown,
                                contentDescription = null
                            )
                        }

                    }
                }
                Spacer(modifier = Modifier.height(screenHeight(x = 16.0)))
                if(loadingStatus == LoadingStatus.LOADING) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        CircularProgressIndicator()
                    }
                } else {
                    LazyColumn {
                        items(clubs) { club ->
                            ClubCard(club = club)
                            Spacer(modifier = Modifier.height(screenHeight(x = 16.0)))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ClubCard(
    club: ClubData,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        AsyncImage(
            model = club.clubLogo!!.link,
            contentDescription = "Image of ${club.name}",
            modifier = Modifier
                .size(screenWidth(x = 72.0))
                .clip(RoundedCornerShape(screenWidth(x = 8.0))),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(screenWidth(x = 8.0)))
        Column {
            Text(
                text = club.name,
                fontSize = screenFontSize(x = 14.0).sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(screenHeight(x = 4.0)))
            Text(
                text = club.country,
                fontSize = screenFontSize(x = 14.0).sp,
            )
//            Spacer(modifier = Modifier.height(screenHeight(x = 4.0)))
            Text(
                text = "${club.county}, ${club.town}",
                fontSize = screenFontSize(x = 14.0).sp,
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ClubsScreenPreview() {
    LigiopenadminTheme {
        ClubsScreen(
            clubs = clubs,
            loadingStatus = LoadingStatus.INITIAL,
            navigateToClubAdditionScreen = {}
        )
    }
}