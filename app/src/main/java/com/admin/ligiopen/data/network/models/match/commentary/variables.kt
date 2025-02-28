package com.admin.ligiopen.data.network.models.match.commentary

import com.admin.ligiopen.data.network.models.match.events.MatchEventType

val commentary = MatchCommentaryData(
    matchCommentaryId = 0,
    postMatchAnalysisId = 0,
    files = emptyList(),
    minute = "22",
    createdAt = "2025-02-27T16:06:31.587932642",
    updatedAt = null,
    archivedAt = null,
    archived = false,
    matchEventType = MatchEventType.GOAL,
    cornerEvent = null,
    foulEvent = null,
    freeKickEvent = null,
    fullTimeEvent = null,
    halfTimeEvent = null,
    goalEvent = null,
    goalKickEvent = null,
    injuryEvent = null,
    kickOffEvent = null
)

val matchCommentaries = List(10) {
    MatchCommentaryData(
        matchCommentaryId = 0,
        postMatchAnalysisId = 0,
        files = emptyList(),
        minute = "22",
        createdAt = "2025-02-27T16:06:31.587932642",
        updatedAt = null,
        archivedAt = null,
        archived = false,
        matchEventType = MatchEventType.GOAL,
        cornerEvent = null,
        foulEvent = null,
        freeKickEvent = null,
        fullTimeEvent = null,
        halfTimeEvent = null,
        goalEvent = null,
        goalKickEvent = null,
        injuryEvent = null,
        kickOffEvent = null
    )
}