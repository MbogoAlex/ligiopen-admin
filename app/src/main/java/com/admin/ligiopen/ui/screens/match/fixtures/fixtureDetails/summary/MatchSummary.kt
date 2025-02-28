package com.admin.ligiopen.ui.screens.match.fixtures.fixtureDetails.summary

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.admin.ligiopen.AppViewModelFactory
import com.admin.ligiopen.R
import com.admin.ligiopen.ui.screens.match.fixtures.fixtureDetails.HighlightsScreenViewModel
import com.admin.ligiopen.ui.theme.LigiopenadminTheme
import com.admin.ligiopen.utils.screenFontSize
import com.admin.ligiopen.utils.screenHeight
import com.admin.ligiopen.utils.screenWidth

@Composable
fun MatchSummaryComposable(
    modifier: Modifier = Modifier
) {
    val viewModel: HighlightsScreenViewModel = viewModel(factory = AppViewModelFactory.Factory)
    val uiState by viewModel.uiState.collectAsState()

    Box(modifier = modifier) {
        MatchSummary()
    }
}

@Composable
fun MatchSummary(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
    ) {
        Box {
            Image(
                painter = painterResource(id = R.drawable.players),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .background(Color.Black)
                    .alpha(0.3f)
                    .fillMaxWidth()
                    .height(screenWidth(x = 250.0))
            )
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "ORL",
                        color = Color.White,
                        fontSize = screenFontSize(x = 18.0).sp,
                        fontWeight = FontWeight.Bold
                    )
                    Image(
                        painter = painterResource(id = R.drawable.club1),
                        contentDescription = null,
                        modifier = Modifier
                            .size(screenWidth(x = 60.0))
                    )
                    Card(
                        shape = RoundedCornerShape(
                            topStart = screenWidth(x = 5.0),
                            bottomStart = screenWidth(x = 5.0)
                        )
                    ) {
                        Text(
                            text = "0",
                            fontSize = screenFontSize(x = 18.0).sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(screenWidth(x = 12.0))
                        )
                    }
                    ElevatedCard(
                        shape = RoundedCornerShape(
                            topEnd = screenWidth(x = 5.0),
                            bottomEnd = screenWidth(x = 5.0)
                        )
                    ) {
                        Text(
                            text = "2",
                            fontSize = screenFontSize(x = 18.0).sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(screenWidth(x = 12.0))
                        )
                    }
                    Image(
                        painter = painterResource(id = R.drawable.club2),
                        contentDescription = null,
                        modifier = Modifier
                            .size(screenWidth(x = 60.0))
                    )
                    Text(
                        text = "ALT",
                        color = Color.White,
                        fontSize = screenFontSize(x = 18.0).sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
        Column(
            modifier = Modifier
                .padding(
                    horizontal = screenWidth(x = 16.0),
                    vertical = screenHeight(x = 16.0)
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
            Spacer(modifier = Modifier.height(screenHeight(x = 16.0)))
            matchEvents.forEach {
                MatchEventCell(event = it)
                HorizontalDivider()
            }
        }
    }
}



@Composable
fun MatchEventCell(
    event: GameEvent,
    modifier: Modifier = Modifier
) {
    if(event.home) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(screenWidth(x = 8.0))
        ) {
            Text(
                text = "${event.minute}'",
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = screenFontSize(x = 14.0).sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(screenWidth(x = 16.0)))
            when(event.eventType) {
                EventType.GOAL -> {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.goal),
                            contentDescription = null,
                            modifier = Modifier
                                .size(screenWidth(x = 24.0))
                        )
                        Spacer(modifier = Modifier.width(screenWidth(x = 8.0)))
                        Text(
                            text = event.player!!,
                            color = MaterialTheme.colorScheme.onBackground,
                            fontSize = screenFontSize(x = 14.0).sp,
                        )
                    }
                }
                EventType.OWN_GOAL -> {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.goal),
                            contentDescription = null,
                            modifier = Modifier
                                .size(screenWidth(x = 24.0))
                        )
                        Spacer(modifier = Modifier.width(screenWidth(x = 8.0)))
                        Text(
                            text = "OG: ${event.player!!}",
                            color = MaterialTheme.colorScheme.onBackground,
                            fontSize = screenFontSize(x = 14.0).sp,
                        )
                    }
                }
                EventType.YELLOW_CARD -> {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.yellow_card),
                            contentDescription = null,
                            modifier = Modifier
                                .size(screenWidth(x = 24.0))
                        )
                        Spacer(modifier = Modifier.width(screenWidth(x = 8.0)))
                        Text(
                            text = event.player!!,
                            color = MaterialTheme.colorScheme.onBackground,
                            fontSize = screenFontSize(x = 14.0).sp,
                        )
                    }
                }
                EventType.RED_CARD -> {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.red_card),
                            contentDescription = null,
                            modifier = Modifier
                                .size(screenWidth(x = 24.0))
                        )
                        Spacer(modifier = Modifier.width(screenWidth(x = 8.0)))
                        Text(
                            text = event.player!!,
                            color = MaterialTheme.colorScheme.onBackground,
                            fontSize = screenFontSize(x = 14.0).sp,
                        )
                    }
                }
                EventType.SUBSTITUTION -> {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.substitution),
                            contentDescription = null,
                            modifier = Modifier
                                .size(screenWidth(x = 24.0))
                        )
                        Spacer(modifier = Modifier.width(screenWidth(x = 8.0)))
                        Column {
                            Text(
                                text = "In: ${event.substitution.playerIn!!}",
                                color = MaterialTheme.colorScheme.onBackground,
                                fontSize = screenFontSize(x = 14.0).sp,
                            )
                            Text(
                                text = "Out: ${event.substitution.playerOut!!}",
                                color = MaterialTheme.colorScheme.onBackground,
                                fontSize = screenFontSize(x = 14.0).sp,
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    } else {
        Row(
            modifier = Modifier
                .padding(screenWidth(x = 8.0))
        ) {
            Spacer(modifier = Modifier.weight(1f))
            when(event.eventType) {
                EventType.GOAL -> {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = event.player!!,
                            color = MaterialTheme.colorScheme.onBackground,
                            fontSize = screenFontSize(x = 14.0).sp,
                        )
                        Spacer(modifier = Modifier.width(screenWidth(x = 8.0)))
                        Image(
                            painter = painterResource(id = R.drawable.goal),
                            contentDescription = null,
                            modifier = Modifier
                                .size(screenWidth(x = 24.0))
                        )
                    }
                }
                EventType.OWN_GOAL -> {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "OG: ${event.player!!}",
                            color = MaterialTheme.colorScheme.onBackground,
                            fontSize = screenFontSize(x = 14.0).sp,
                        )
                        Spacer(modifier = Modifier.width(screenWidth(x = 8.0)))
                        Image(
                            painter = painterResource(id = R.drawable.goal),
                            contentDescription = null,
                            modifier = Modifier
                                .size(screenWidth(x = 24.0))
                        )
                    }
                }
                EventType.YELLOW_CARD -> {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = event.player!!,
                            color = MaterialTheme.colorScheme.onBackground,
                            fontSize = screenFontSize(x = 14.0).sp,
                        )
                        Spacer(modifier = Modifier.width(screenWidth(x = 8.0)))
                        Image(
                            painter = painterResource(id = R.drawable.yellow_card),
                            contentDescription = null,
                            modifier = Modifier
                                .size(screenWidth(x = 24.0))
                        )
                    }
                }
                EventType.RED_CARD -> {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = event.player!!,
                            color = MaterialTheme.colorScheme.onBackground,
                            fontSize = screenFontSize(x = 14.0).sp,
                        )
                        Spacer(modifier = Modifier.width(screenWidth(x = 8.0)))
                        Image(
                            painter = painterResource(id = R.drawable.red_card),
                            contentDescription = null,
                            modifier = Modifier
                                .size(screenWidth(x = 24.0))
                        )
                    }
                }
                EventType.SUBSTITUTION -> {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "In: ${event.substitution.playerIn!!}",
                                color = MaterialTheme.colorScheme.onBackground,
                                fontSize = screenFontSize(x = 14.0).sp,
                            )
                            Text(
                                text = "Out: ${event.substitution.playerOut!!}",
                                color = MaterialTheme.colorScheme.onBackground,
                                fontSize = screenFontSize(x = 14.0).sp,
                            )
                        }
                        Spacer(modifier = Modifier.width(screenWidth(x = 8.0)))
                        Image(
                            painter = painterResource(id = R.drawable.substitution),
                            contentDescription = null,
                            modifier = Modifier
                                .size(screenWidth(x = 24.0))
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.width(screenWidth(x = 16.0)))
            Text(
                text = "${event.minute}'",
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = screenFontSize(x = 14.0).sp,
                fontWeight = FontWeight.Bold
            )
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MatchSummaryPreview() {
    LigiopenadminTheme {
        MatchSummary()
    }
}