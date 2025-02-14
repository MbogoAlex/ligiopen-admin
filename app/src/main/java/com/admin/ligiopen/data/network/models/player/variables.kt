package com.admin.ligiopen.data.network.models.player

import com.admin.ligiopen.data.network.models.file.fileData
import com.admin.ligiopen.data.network.models.file.fileDts

val player = PlayerData(
    playerId = 2,
    mainPhoto = fileData,
    username = "Alexis M",
    number = 1,
    playerPosition = PlayerPosition.FORWARD,
    age = 22,
    height = 54.4,
    weight = 60.2,
    country = "Kenya",
    county = "Nairobi",
    town = "Nairobi",
    clubId = 1,
    files = fileDts,
    playerState = PlayerState.ACTIVE
)

val players = List(10) { index ->
    PlayerData(
        playerId = 2 + index,
        mainPhoto = fileData,
        username = "Alexis M",
        number = 1 + index,
        playerPosition = PlayerPosition.FORWARD,
        age = 22,
        height = 54.4,
        weight = 60.2,
        country = "Kenya",
        county = "Nairobi",
        town = "Nairobi",
        clubId = 1,
        files = fileDts,
        playerState = PlayerState.ACTIVE
    )
}