package com.admin.ligiopen.data.network.models.club

import com.admin.ligiopen.data.network.models.file.fileData
import com.admin.ligiopen.data.network.models.player.players

val club = ClubData(
    clubId = 1,
    clubLogo = fileData,
    clubMainPhoto = fileData,
    name = "AFC Leopards",
    description = "This is AFC Leopards team",
    country = "Kenya",
    county = "Kakamega",
    town = "Kakamega town",
    startedOn = "2020-09-17",
    createdAt = "2025-02-07T08:02:01.878284",
    archived = false,
    archivedAt = null,
    players = players
)

val clubs = List(10) {index ->
    ClubData(
        clubId = 1 + index,
        clubLogo = fileData,
        clubMainPhoto = fileData,
        name = "AFC Leopards",
        description = "This is AFC Leopards team",
        country = "Kenya",
        county = "Kakamega",
        town = "Kakamega town",
        startedOn = "2020-09-17",
        createdAt = "2025-02-07T08:02:01.878284",
        archived = false,
        archivedAt = null,
        players = players
    )
}