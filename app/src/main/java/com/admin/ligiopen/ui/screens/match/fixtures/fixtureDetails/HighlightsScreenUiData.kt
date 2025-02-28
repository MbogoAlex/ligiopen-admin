package com.admin.ligiopen.ui.screens.match.fixtures.fixtureDetails

import com.admin.ligiopen.data.network.enums.LoadingStatus
import com.admin.ligiopen.data.network.models.match.commentary.MatchCommentaryData
import com.admin.ligiopen.data.room.db.userAccountDt
import com.admin.ligiopen.data.room.models.UserAccount

data class HighlightsScreenUiData(
    val userAccount: UserAccount = userAccountDt,
    val postMatchId: String? = null,
    val fixtureId: String? = null,
    val commentaries: List<MatchCommentaryData> = emptyList(),
    val loadingStatus: LoadingStatus = LoadingStatus.LOADING,
)
