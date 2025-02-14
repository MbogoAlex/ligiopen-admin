package com.admin.ligiopen.data.network.models.player

import com.admin.ligiopen.data.network.models.file.FileData
import kotlinx.serialization.Serializable

@Serializable
data class PlayerData(
    val playerId: Int,
    val mainPhoto: FileData?,
    val username: String,
    val number: Int,
    val playerPosition: PlayerPosition,
    val age: Int,
    val height: Double,
    val weight: Double,
    val country: String,
    val county: String,
    val town: String,
    val clubId: Int?,
    val files: List<FileData>,
    val playerState: PlayerState
)
