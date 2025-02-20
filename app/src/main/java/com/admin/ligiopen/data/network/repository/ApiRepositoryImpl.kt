package com.admin.ligiopen.data.network.repository

import com.admin.ligiopen.data.network.models.auth.UserLoginRequestBody
import com.admin.ligiopen.data.network.models.auth.UserLoginResponseBody
import com.admin.ligiopen.data.network.models.club.ClubAdditionRequest
import com.admin.ligiopen.data.network.models.club.ClubResponseBody
import com.admin.ligiopen.data.network.models.club.ClubsResponseBody
import com.admin.ligiopen.data.network.models.match.commentary.CommentaryCreationRequest
import com.admin.ligiopen.data.network.models.match.commentary.CommentaryUpdateRequest
import com.admin.ligiopen.data.network.models.match.commentary.FullMatchResponseBody
import com.admin.ligiopen.data.network.models.match.commentary.MatchCommentaryResponseBody
import com.admin.ligiopen.data.network.models.match.fixture.FixtureCreationRequest
import com.admin.ligiopen.data.network.models.match.fixture.FixtureResponseBody
import com.admin.ligiopen.data.network.models.match.fixture.FixtureUpdateRequest
import com.admin.ligiopen.data.network.models.match.location.LocationCreationRequest
import com.admin.ligiopen.data.network.models.match.location.LocationUpdateRequest
import com.admin.ligiopen.data.network.models.match.location.MatchLocationResponseBody
import com.admin.ligiopen.data.network.models.match.location.MatchLocationsResponseBody
import okhttp3.MultipartBody
import retrofit2.Response

class ApiRepositoryImpl(private val apiService: ApiService): ApiRepository {
    override suspend fun login(userLoginRequestBody: UserLoginRequestBody): Response<UserLoginResponseBody> =
        apiService.login(userLoginRequestBody)

    override suspend fun createMatchLocation(
        token: String,
        locationCreationRequest: LocationCreationRequest
    ): Response<MatchLocationResponseBody> =
        apiService.createMatchLocation(
            token = "Bearer $token",
            locationCreation = locationCreationRequest
        )

    override suspend fun updateMatchLocation(
        token: String,
        locationUpdate: LocationUpdateRequest
    ): Response<MatchLocationResponseBody> =
        apiService.updateMatchLocation(
            token = "Bearer $token",
            locationUpdate = locationUpdate
        )

    override suspend fun uploadMatchLocationPhotos(
        token: String,
        locationId: Int,
        files: List<MultipartBody.Part>
    ): Response<MatchLocationResponseBody> =
        apiService.uploadMatchLocationPhotos(
            token = "Bearer $token",
            locationId = locationId,
            files = files
        )

    override suspend fun removeMatchLocationFile(
        token: String,
        locationId: Int,
        fileId: Int
    ): Response<MatchLocationResponseBody> =
        apiService.removeMatchLocationFile(
            token = "Bearer $token",
            locationId = locationId,
            fileId = fileId
        )

    override suspend fun getMatchLocation(
        token: String,
        locationId: Int
    ): Response<MatchLocationResponseBody> =
        apiService.getMatchLocation(
            token = "Bearer $token",
            locationId = locationId
        )

    override suspend fun getMatchLocations(token: String): Response<MatchLocationsResponseBody> =
        apiService.getMatchLocations(
            token = "Bearer $token"
        )

    override suspend fun createMatchFixture(
        token: String,
        fixtureCreation: FixtureCreationRequest
    ): Response<FixtureResponseBody> =
        apiService.createMatchFixture(
            token = "Bearer $token",
            fixtureCreation = fixtureCreation
        )

    override suspend fun updateMatchFixture(
        token: String,
        fixtureUpdate: FixtureUpdateRequest
    ): Response<FixtureResponseBody> =
        apiService.updateMatchFixture(
            token = "Bearer $token",
            fixtureUpdate = fixtureUpdate
        )

    override suspend fun getMatchFixture(
        token: String,
        fixtureId: Int
    ): Response<FixtureResponseBody> =
        apiService.getMatchFixture(
            token = "Bearer $token",
            fixtureId = fixtureId
        )

    override suspend fun uploadMatchCommentary(
        token: String,
        commentaryCreationRequest: CommentaryCreationRequest
    ): Response<MatchCommentaryResponseBody> =
        apiService.uploadMatchCommentary(
            token = "Bearer $token",
            commentaryCreationRequest = commentaryCreationRequest
        )

    override suspend fun updateMatchCommentary(
        token: String,
        commentaryUpdateRequest: CommentaryUpdateRequest
    ): Response<MatchCommentaryResponseBody> =
        apiService.updateMatchCommentary(
            token = "Bearer $token",
            commentaryUpdateRequest = commentaryUpdateRequest
        )

    override suspend fun uploadMatchCommentaryFiles(
        token: String,
        commentaryId: Int,
        files: List<MultipartBody.Part>
    ): Response<MatchCommentaryResponseBody> =
        apiService.uploadMatchCommentaryFiles(
            token = "Bearer $token",
            commentaryId = commentaryId,
            files = files
        )

    override suspend fun getMatchCommentary(
        token: String,
        commentaryId: Int
    ): Response<MatchCommentaryResponseBody> =
        apiService.getMatchCommentary(
            token = "Bearer $token",
            commentaryId = commentaryId
        )

    override suspend fun getFullMatchDetails(
        token: String,
        postMatchAnalysisId: Int
    ): Response<FullMatchResponseBody> =
        apiService.getFullMatchDetails(
            token = "Bearer $token",
            postMatchAnalysisId = postMatchAnalysisId
        )

    override suspend fun getClubs(token: String): Response<ClubsResponseBody> =
        apiService.getClubs(
            token = "Bearer $token"
        )

    override suspend fun getClub(token: String, clubId: Int): Response<ClubsResponseBody> =
        apiService.getClub(
            token = "Bearer $token",
            clubId = clubId
        )

    override suspend fun addClub(
        token: String,
        clubAdditionRequest: ClubAdditionRequest,
        logo: MultipartBody.Part
    ): Response<ClubResponseBody> =
        apiService.addClub(
            token = "Bearer $token",
            clubAdditionRequest = clubAdditionRequest,
            logo = logo
        )
}