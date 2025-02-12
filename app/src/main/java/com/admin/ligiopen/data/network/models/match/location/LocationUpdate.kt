package com.admin.ligiopen.data.network.models.match.location

import kotlinx.serialization.Serializable

@Serializable
data class LocationUpdate(
    val locationId: Int,
    val venueName: String,
    val country: String,
    val county: String,
    val town: String
)
