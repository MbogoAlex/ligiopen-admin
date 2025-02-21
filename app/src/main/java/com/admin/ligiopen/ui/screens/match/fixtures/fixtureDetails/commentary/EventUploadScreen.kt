package com.admin.ligiopen.ui.screens.match.fixtures.fixtureDetails.commentary

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.admin.ligiopen.R
import com.admin.ligiopen.data.network.enums.LoadingStatus
import com.admin.ligiopen.ui.theme.LigiopenadminTheme
import com.admin.ligiopen.utils.composables.OutlinedTextFieldComposable
import com.admin.ligiopen.utils.screenFontSize
import com.admin.ligiopen.utils.screenHeight
import com.admin.ligiopen.utils.screenWidth

@Composable
fun EventUploadScreenComposable(
    modifier: Modifier = Modifier
) {
    val eventTypes = listOf(
        "Goal",
        "Own goal",
        "Substitution",
        "Foul",
        "Yellow card",
        "Red card",
        "Offside",
        "Corner kick",
        "Free kick",
        "Penalty",
        "Penalty missed",
        "Injury",
        "Throw in",
        "Goal kick",
        "Kick off",
        "Half time",
        "Full time"
    )


}

@Composable
fun EventUploadScreen(
    eventTypes: List<String>,
    expanded: Boolean,
    selectedEventType: String?,
    file: Uri?,
    onUploadFile: () -> Unit,
    onRemoveFile: () -> Unit,
    onSelectEvent: (event: String) -> Unit,
    onExpand: () -> Unit,
    buttonEnabled: Boolean,
    onAddEvent: () -> Unit,
    minute: String,
    onChangeMinute: (minute: String) -> Unit,
    title: String,
    onChangeTitle: (title: String) -> Unit,
    summary: String,
    onChangeSummary: (summary: String) -> Unit,
    loadingStatus: LoadingStatus,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                vertical = screenHeight(x = 16.0),
                horizontal = screenWidth(x = 16.0)
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Previous screen"
                )
            }
            Text(
                text = "Minute commentary",
                fontSize = screenFontSize(x = 16.0).sp,
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextFieldComposable(
                label = "Minute",
                value = "",
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Text
                ),
                shape = RoundedCornerShape(screenWidth(x = 8.0)),
                onValueChange = {},
                modifier = Modifier.weight(0.1f)
            )

            Spacer(modifier = Modifier.width(screenWidth(x = 8.0)))

            Box(
                modifier = Modifier
                    .weight(0.1f)
                    .clickable {
                        onExpand()
                    }
            ) {
                Column(
                    modifier = Modifier
                        .padding(
                            vertical = screenHeight(x = 16.0),
                            horizontal = screenWidth(x = 8.0)
                        )
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = selectedEventType ?: "Select event type")
                        Spacer(modifier = Modifier.width(screenWidth(x = 4.0)))
                        Icon(
                            imageVector = if(expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                            contentDescription = "Select an event"
                        )
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = onExpand
                    ) {
                        eventTypes.forEach { type ->
                            DropdownMenuItem(
                                text = { Text(type) },
                                onClick = {
                                    onSelectEvent(type)
                                    onExpand()
                                }
                            )
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(screenHeight(x = 8.0)))
        OutlinedTextFieldComposable(
            label = "Title",
            value = "",
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Text
            ),
            onValueChange = {},
            shape = RoundedCornerShape(screenWidth(x = 8.0)),
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(screenHeight(x = 8.0)))
        OutlinedTextFieldComposable(
            label = "Summary",
            value = "",
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Text
            ),
            onValueChange = {},
            shape = RoundedCornerShape(screenWidth(x = 8.0)),
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(screenHeight(x = 16.0)))
        Text(
            text = "Upload photo/video",
            fontSize = screenFontSize(x = 14.0).sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(screenHeight(x = 8.0)))
        if(file != null) {
            Box {
                AsyncImage(
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(file)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(id = R.drawable.loading_img),
                    error = painterResource(id = R.drawable.ic_broken_image),
                    contentScale = ContentScale.Crop,
                    contentDescription = "Front ID",
                    modifier = Modifier
                        .height(screenHeight(x = 250.0))
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(screenWidth(x = 10.0)))
                )
                IconButton(
                    modifier = Modifier
                        .alpha(0.5f)
                        .background(Color.Black)
                        .padding(
                            start = screenWidth(x = 5.0),
                            end = screenWidth(x = 5.0),
                        )
                        .align(Alignment.TopEnd),
                    onClick = onRemoveFile
                ) {
                    Icon(
                        tint = Color.White,
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Remove front id"
                    )
                }
            }
        } else {
            EventFileUpload(
                onUploadFile = onUploadFile
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(
            enabled = buttonEnabled && loadingStatus != LoadingStatus.LOADING,
            onClick = onAddEvent,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            if(loadingStatus == LoadingStatus.LOADING) {
                Text(
                    text = "Loading...",
                    fontSize = screenFontSize(x = 14.0).sp
                )
            } else {
                Text(
                    text = "Add club",
                    fontSize = screenFontSize(x = 14.0).sp
                )

            }

        }
    }
}

@Composable
fun EventFileUpload(
    onUploadFile: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .height(screenHeight(x = 250.0))
                .fillMaxWidth()
                .border(
                    width = screenWidth(x = 1.0),
                    color = Color.LightGray,
                    shape = RoundedCornerShape(screenWidth(x = 10.0))
                )
        ) {
            IconButton(
//                enabled = verificationStatus != VerificationStatus.PENDING_VERIFICATION && verificationStatus != VerificationStatus.VERIFIED,
                onClick = onUploadFile
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.photo_upload),
                    contentDescription = "Upload logo"
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EventUploadScreenPreview() {
    LigiopenadminTheme {
        val eventTypes = listOf(
            "Goal",
            "Own goal",
            "Substitution",
            "Foul",
            "Yellow card",
            "Red card",
            "Offside",
            "Corner kick",
            "Free kick",
            "Penalty",
            "Penalty missed",
            "Injury",
            "Throw in",
            "Goal kick",
            "Kick off",
            "Half time",
            "Full time"
        )
        var expanded by rememberSaveable {
            mutableStateOf(false)
        }

        var selectedEventType by rememberSaveable {
            mutableStateOf<String?>(null)
        }

        EventUploadScreen(
            eventTypes = eventTypes,
            expanded = expanded,
            selectedEventType = selectedEventType,
            onSelectEvent = {
                selectedEventType = it
            },
            onExpand = {
                expanded = !expanded
            },
            file = null,
            onUploadFile = {},
            onRemoveFile = {},
            buttonEnabled = false,
            onAddEvent = {},
            minute = "",
            onChangeMinute = {},
            title = "",
            onChangeTitle = {},
            summary = "",
            onChangeSummary = {},
            loadingStatus = LoadingStatus.INITIAL
        )
    }
}