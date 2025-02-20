package com.admin.ligiopen.ui.screens.match.fixtures.fixtureDetails.stats

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.admin.ligiopen.R
import com.admin.ligiopen.ui.theme.LigiopenadminTheme
import com.admin.ligiopen.utils.screenFontSize
import com.admin.ligiopen.utils.screenHeight
import com.admin.ligiopen.utils.screenWidth

@Composable
fun MatchStatisticsScreenComposable(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .safeDrawingPadding()
    ) {
        MatchStatisticsScreen(matchStats = sampleStats)
    }
}

@Composable
fun MatchStatisticsScreen(
    matchStats: MatchStatistics,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(
                vertical = screenHeight(x = 16.0),
                horizontal = screenWidth(x = 16.0)
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = matchStats.homeTeamName,
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
//                    .padding(horizontal = screenWidth(x = 4.0))
            )
            Text(
                text = matchStats.awayTeamName,
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = screenFontSize(x = 18.0).sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(screenHeight(x = 16.0)))

        // Statistics rows
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            matchStats.statistics.forEach { stat ->
                MatchStatisticRow(
                    label = stat.label,
                    homeValue = stat.homeValue,
                    awayValue = stat.awayValue
                )
            }
        }
    }
}

@Composable
fun MatchStatisticRow(
    label: String,
    homeValue: String,
    awayValue: String,
    modifier: Modifier = Modifier
) {

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
//            .background(Color.Yellow)
            .padding(
                vertical = screenHeight(x = 8.0),
                horizontal = screenWidth(x = 16.0)
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Home team value
        Text(
            text = homeValue,
            textAlign = TextAlign.Center,
//            modifier = Modifier.weight(1f),
            fontSize = screenFontSize(x = 16.0).sp,
            fontWeight = FontWeight.Bold
        )

        // Statistic label
        Text(
            text = label,
            textAlign = TextAlign.Center,
//            modifier = Modifier.weight(1f),
            fontSize = screenFontSize(x = 14.0).sp
        )

        // Away team value
        Text(
            text = awayValue,
            textAlign = TextAlign.Center,
//            modifier = Modifier.weight(1f),
            fontSize = screenFontSize(x = 16.0).sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MatchStatisticsScreenPreview() {
    val sampleStats = MatchStatistics(
        homeTeamName = "ORL",
        awayTeamName = "ALT",
        statistics = listOf(
            MatchStatistic("Shots", "10", "12"),
            MatchStatistic("Shots on Target", "6", "7"),
            MatchStatistic("Possession", "55%", "45%"),
            MatchStatistic("Passing Accuracy", "82%", "79%"),
            MatchStatistic("Corners", "5", "4"),
            MatchStatistic("Fouls", "12", "15"),
            MatchStatistic("Offsides", "3", "2"),
            MatchStatistic("Yellow Cards", "2", "3"),
            MatchStatistic("Red Cards", "0", "1")
        )
    )

    LigiopenadminTheme {
        MatchStatisticsScreen(matchStats = sampleStats)
    }
}
