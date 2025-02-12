package com.admin.ligiopen.data.network.models.match.commentary

import com.admin.ligiopen.data.network.models.file.FileData
import com.admin.ligiopen.data.network.models.match.events.CornerEventData
import com.admin.ligiopen.data.network.models.match.events.FoulEventData
import com.admin.ligiopen.data.network.models.match.events.FreeKickEventData
import com.admin.ligiopen.data.network.models.match.events.FullTimeEventData
import com.admin.ligiopen.data.network.models.match.events.GoalEventData
import com.admin.ligiopen.data.network.models.match.events.GoalKickEventData
import com.admin.ligiopen.data.network.models.match.events.HalfTimeEventData
import com.admin.ligiopen.data.network.models.match.events.InjuryEventData
import com.admin.ligiopen.data.network.models.match.events.KickOffEventData
import com.admin.ligiopen.data.network.models.match.events.MatchEventType
import kotlinx.serialization.Serializable

@Serializable
data class MatchCommentaryData(
    val matchCommentaryId: Int,
    val postMatchAnalysisId: Int,
    val files: List<FileData>,
    val minute: String?,
    val createdAt: String,
    val updatedAt: String?,
    val archived: Boolean,
    val archivedAt: String?,
    val matchEventType: MatchEventType,
    val cornerEvent: CornerEventData?,
    val foulEvent: FoulEventData?,
    val freeKickEvent: FreeKickEventData?,
    val fullTimeEvent: FullTimeEventData?,
    val halfTimeEvent: HalfTimeEventData?,
    val goalEvent: GoalEventData?,
    val goalKickEvent: GoalKickEventData?,
    val injuryEvent: InjuryEventData?,
    val kickOffEvent: KickOffEventData?,
)
