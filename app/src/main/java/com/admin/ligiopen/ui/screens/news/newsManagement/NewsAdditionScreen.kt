package com.admin.ligiopen.ui.screens.news.newsManagement

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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.admin.ligiopen.R
import com.admin.ligiopen.data.network.models.club.ClubData
import com.admin.ligiopen.data.network.models.club.clubs
import com.admin.ligiopen.ui.theme.LigiopenadminTheme
import com.admin.ligiopen.utils.screenFontSize
import com.admin.ligiopen.utils.screenHeight
import com.admin.ligiopen.utils.screenWidth

@Composable
fun NewsAdditionScreenComposable() {
    Box(
        modifier = Modifier
            .safeDrawingPadding()
    ) {
        NewsAdditionScreen(
            clubs = clubs,
            selectedClubIds = listOf(1, 2, 5),
            onSelectClub = {},
            onRemoveClub = {},
            newsAdditionScreenStage = NewsAdditionScreenStage.CLUB_SELECTION_SECTION
        )
    }
}

@Composable
fun NewsAdditionScreen(
    clubs: List<ClubData>,
    selectedClubIds: List<Int>,
    onSelectClub: (clubId: Int) -> Unit,
    onRemoveClub: (clubId: Int) -> Unit,
    newsAdditionScreenStage: NewsAdditionScreenStage,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = screenWidth(x = 16.0),
                vertical = screenHeight(x = 16.0)
            )
    ) {
        when(newsAdditionScreenStage) {
            NewsAdditionScreenStage.CLUB_SELECTION_SECTION -> {
                ClubSelectionScreen(
                    selectedClubIds = selectedClubIds,
                    onSelectClub = onSelectClub,
                    onRemoveClub = onRemoveClub,
                    clubs = clubs
                )
            }
            NewsAdditionScreenStage.HEADING_SECTION -> {}
            NewsAdditionScreenStage.ITEMS_SECTION -> {}
        }
    }
}

@Composable
fun ClubSelectionScreen(
    clubs: List<ClubData>,
    selectedClubIds: List<Int>,
    onSelectClub: (clubId: Int) -> Unit,
    onRemoveClub: (clubId: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
        ) {
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = { /*TODO*/ }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Previous screen"
                        )
                    }
                    Spacer(modifier = Modifier.width(screenWidth(x = 4.0)))
                    Text(
                        text = "News",
                        fontSize = screenFontSize(x = 16.0).sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(screenHeight(x = 16.0)))
                Text(
                    text = "Select the club(s) involved",
                    fontSize = screenFontSize(x = 14.0).sp
                )
                Spacer(modifier = Modifier.height(screenHeight(x = 8.0)))
                LazyColumn {
                    items(clubs) {club ->
                        SelectableClubCard(
                            club = club,
                            selectedClubIds = selectedClubIds,
                            onSelectClub = onSelectClub,
                            onRemoveClub = onRemoveClub
                        )
                        Spacer(modifier = Modifier.height(screenHeight(x = 8.0)))
                    }
                }
            }
        }
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = if(selectedClubIds.isEmpty()) "Skip" else "Next",
                fontSize = screenFontSize(x = 14.0).sp
            )
        }
    }
}

@Composable
fun SelectableClubCard(
    club: ClubData,
    selectedClubIds: List<Int>,
    onSelectClub: (clubId: Int) -> Unit,
    onRemoveClub: (clubId: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            model = club.clubLogo.link,
            contentDescription = "Image of ${club.name}",
            modifier = Modifier
                .size(screenWidth(x = 48.0))
                .clip(RoundedCornerShape(screenWidth(x = 8.0))),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(screenWidth(x = 8.0)))
        Text(
            text = club.name,
            fontSize = screenFontSize(x = 14.0).sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                painter = painterResource(id = if(selectedClubIds.contains(club.clubId)) R.drawable.checked_box else R.drawable.unchecked_box),
                contentDescription = "Select club"
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NewsAdditionScreenPreview() {
    LigiopenadminTheme {
        NewsAdditionScreen(
            clubs = clubs,
            selectedClubIds = listOf(1, 2, 5),
            onSelectClub = {},
            onRemoveClub = {},
            newsAdditionScreenStage = NewsAdditionScreenStage.CLUB_SELECTION_SECTION
        )
    }
}