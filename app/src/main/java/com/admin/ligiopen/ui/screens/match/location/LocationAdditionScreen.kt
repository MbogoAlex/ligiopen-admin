package com.admin.ligiopen.ui.screens.match.location

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.admin.ligiopen.R
import com.admin.ligiopen.ui.theme.LigiopenadminTheme
import com.admin.ligiopen.utils.TextFieldComposable
import com.admin.ligiopen.utils.screenFontSize
import com.admin.ligiopen.utils.screenHeight
import com.admin.ligiopen.utils.screenWidth

@Composable
fun LocationAdditionScreenComposable(
    modifier: Modifier = Modifier
) {

}

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun LocationAdditionScreen(
    modifier: Modifier = Modifier
) {

    // County Selection Dropdown
    val counties = remember {
        listOf(
            "Nairobi",
            "Mombasa", "Kisumu", "Nakuru", "Eldoret",
            "Kiambu", "Machakos", "Kakamega", // Add all 47 counties
        )
    }

    var expanded by remember { mutableStateOf(false) }
    var selectedCounty by remember { mutableStateOf("Nairobi") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                vertical = screenHeight(x = 16.0),
                horizontal = screenWidth(x = 16.0)
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Previous screen"
                )
            }
            Spacer(modifier = Modifier.width(screenWidth(x = 4.0)))
            Text(
                text = "Add match location",
                fontSize = screenFontSize(x = 14.0).sp
            )
        }
        Spacer(modifier = Modifier.height(screenHeight(x = 8.0)))
        TextField(
            label = {
                Text(
                    text = "Stadium / venue",
                    fontSize = screenFontSize(x = 14.0).sp
                )
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.stadium),
                    contentDescription = null
                )
            },
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            value = "",
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(screenHeight(x = 16.0)))

        // Country Selection (Fixed to Kenya)
        TextFieldComposable(
            label = "Country",
            value = "Kenya",
            onValueChange = {},
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Text
            ),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier.fillMaxWidth(),
            enabled = false
        )
        Spacer(modifier = Modifier.height(screenHeight(x = 16.0)))

        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = it },
                modifier = Modifier
                    .weight(1f)
            ) {
                TextField(
                    modifier = Modifier
                         .menuAnchor(),
                    readOnly = true,
                    value = selectedCounty,
                    onValueChange = {},
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    label = { Text("County") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = expanded
                        )
                    }
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    counties.forEach { county ->
                        DropdownMenuItem(
                            text = { Text(county) },
                            onClick = {
                                selectedCounty = county
                                expanded = false
                            }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.width(screenWidth(x = 8.0)))
            TextFieldComposable(
                label = "Town",
                value = "",
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Text
                ),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                onValueChange = {},
                modifier = Modifier
                    .weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(screenHeight(x = 16.0)))
        Text(
            text = "Upload stadium / venue photos",
            fontSize = screenFontSize(x = 16.0).sp
        )
        Spacer(modifier = Modifier.height(screenHeight(x = 16.0)))
        PhotosSelection(
            onUploadPhoto = {},
            onRemovePhoto = {},
            photos = emptyList()
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "Add location",
                fontSize = screenFontSize(x = 14.0).sp
            )
        }
    }
}

@Composable
fun PhotosSelection(
    onUploadPhoto: (image: Uri) -> Unit,
    onRemovePhoto: (index: Int) -> Unit,
    photos: List<Uri>,
    modifier: Modifier = Modifier
) {
    val images = remember { mutableStateListOf<Uri>() }

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetMultipleContents(),
        onResult = {uriList ->
            if(uriList.isNotEmpty()) {
                for(uri in uriList) {
                    onUploadPhoto(uri)
                }
            }

        }
    )

    Column {
        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
        ) {
            photos.forEachIndexed { index, uri ->
                Row {
                    Image(
                        rememberImagePainter(data = uri),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(
                                top = screenHeight(x = 5.0),
                                end = screenWidth(x = 3.0),
                                bottom = screenHeight(x = 5.0)
                            )
                            .size(screenWidth(x = 100.0))
                    )
                    IconButton(onClick = {
                        onRemovePhoto(index)
                    }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = null
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(screenWidth(x = 20.0)))
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent
            ),
            modifier = Modifier
                .border(
                    width = screenWidth(x = 1.0),
                    color = Color.Gray,
                    shape = RoundedCornerShape(screenWidth(x = 10.0))
                )
                .clickable {
                    galleryLauncher.launch("image/*")
                }
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(screenWidth(x = 20.0))

            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.photo_upload),
                        contentDescription = null
                    )
                    if(images.isEmpty()) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Click to upload images"
                            )
                            Spacer(modifier = Modifier.width(screenWidth(x = 3.0)))
                            Text(
                                text = "*",
                                color = Color.Red
                            )
                        }
                    } else {
                        Text(
                            text = "Click to upload images"
                        )
                    }

                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LocationAdditionScreenPreview() {
    LigiopenadminTheme {
        LocationAdditionScreen()
    }
}