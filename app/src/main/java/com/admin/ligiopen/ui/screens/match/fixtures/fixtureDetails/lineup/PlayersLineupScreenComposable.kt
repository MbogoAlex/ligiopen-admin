package com.admin.ligiopen.ui.screens.match.fixtures.fixtureDetails.lineup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.admin.ligiopen.R
import com.admin.ligiopen.ui.theme.LigiopenadminTheme
import com.admin.ligiopen.utils.screenFontSize
import com.admin.ligiopen.utils.screenHeight
import com.admin.ligiopen.utils.screenWidth

@Composable
fun PlayersLineupScreenComposable(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .safeDrawingPadding()
    ) {
        PlayersLineupScreen(playersInLineup = playersInlineup)
    }
}

@Composable
fun PlayersLineupScreen(
    playersInLineup: List<PlayerInLineup>,
    modifier: Modifier = Modifier
) {
    // Separate players by position
    val groupedPlayers = playersInLineup.groupBy { it.position }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(
                vertical = screenHeight(x = 14.0),
                horizontal = screenWidth(x = 14.0)
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "ORL",
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = screenFontSize(x = 18.0).sp,
                fontWeight = FontWeight.Bold
            )
            Image(
                painter = painterResource(id = R.drawable.club1),
                contentDescription = null,
                modifier = Modifier
                    .size(screenWidth(x = 24.0))
            )
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(id = R.drawable.club2),
                contentDescription = null,
                modifier = Modifier
                    .size(screenWidth(x = 24.0))
            )
            Text(
                text = "ALT",
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = screenFontSize(x = 18.0).sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(screenHeight(x = 8.0)))
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {
            // Iterate through positions and display players side by side
            groupedPlayers.forEach { (position, players) ->
                val homeTeamPlayers = players.filter { it.home }
                val awayTeamPlayers = players.filter { !it.home }

                Text(
                    text = position.name.lowercase().replaceFirstChar { it.uppercase() },
                    fontWeight = FontWeight.Bold,
                    fontSize = screenFontSize(x = 14.0).sp,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(vertical = screenHeight(x = 8.0))
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = screenHeight(x = 4.0)),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Display home team players
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = screenWidth(x = 8.0))
                    ) {
                        homeTeamPlayers.forEach { player ->
                            PlayerLineupCell(playerInLineup = player)
                        }
                    }

                    // Display away team players
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = screenWidth(x = 8.0))
                    ) {
                        awayTeamPlayers.forEach { player ->
                            PlayerLineupCell(playerInLineup = player)
                        }
                    }
                }
            }
        }

    }
}

@Composable
fun PlayerLineupCell(
    playerInLineup: PlayerInLineup,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = screenHeight(x = 4.0))
            .background(if (playerInLineup.bench) Color.Gray else Color.Transparent)
    ) {
        Text(
            text = "${playerInLineup.number}. ${playerInLineup.name}",
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = screenWidth(x = 8.0))
        )

        if (playerInLineup.yellowCard) {
            Text(
                text = "🟨",
                modifier = Modifier.padding(horizontal = screenWidth(x = 4.0))
            )
        }

        if (playerInLineup.redCard) {
            Text(
                text = "🟥",
                modifier = Modifier.padding(horizontal = screenWidth(x = 4.0))
            )
        }

        if (playerInLineup.scored) {
            Text(
                text = "⚽",
                modifier = Modifier.padding(horizontal = screenWidth(x = 4.0))
            )
        }

        if(playerInLineup.substituted) {
            Text(
                text = "🔄",
                modifier = Modifier.padding(horizontal = screenWidth(x = 4.0))
            )
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PlayersLineupScreenPreview() {
    LigiopenadminTheme {
        PlayersLineupScreen(
            playersInLineup = playersInlineup
        )
    }
}