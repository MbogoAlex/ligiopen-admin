package com.admin.ligiopen.data.network.models.match.fixture

import com.admin.ligiopen.data.network.models.club.ClubData
import kotlinx.serialization.Serializable

@Serializable
data class FixtureData(
    val matchFixtureId: Int,
    val matchLocationId: Int,
    val postMatchAnalysisId: Int,
    val homeClub: ClubData,
    val awayClub: ClubData
)
