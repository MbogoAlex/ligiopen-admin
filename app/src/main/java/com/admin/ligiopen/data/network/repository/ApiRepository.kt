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
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiRepository {

    suspend fun login(userLoginRequestBody: UserLoginRequestBody): Response<UserLoginResponseBody>

    //    Create match location
    suspend fun createMatchLocation(
        token: String,
        locationCreationRequest: LocationCreationRequest
    ): Response<MatchLocationResponseBody>

//    Update match location
    suspend fun updateMatchLocation(
        token: String,
        locationUpdate: LocationUpdateRequest
    ): Response<MatchLocationResponseBody>

//    Upload match location photos
    suspend fun uploadMatchLocationPhotos(
        token: String,
        locationId: Int,
        files: List<MultipartBody.Part>,
    ): Response<MatchLocationResponseBody>

    //    Remove match location file
    suspend fun removeMatchLocationFile(
        token: String,
        locationId: Int,
        fileId: Int,
    ): Response<MatchLocationResponseBody>

//    Get match location
    suspend fun getMatchLocation(
        token: String,
        locationId: Int,
    ): Response<MatchLocationResponseBody>

//    Get match locations
    suspend fun getMatchLocations(
        token: String,
    ): Response<MatchLocationsResponseBody>

    //    Create match fixture
    suspend fun createMatchFixture(
        token: String,
        fixtureCreation: FixtureCreationRequest
    ): Response<FixtureResponseBody>

    //    Update match fixture
    suspend fun updateMatchFixture(
        token: String,
        fixtureUpdate: FixtureUpdateRequest
    ): Response<FixtureResponseBody>

    //    Get match fixture
    suspend fun getMatchFixture(
        token: String,
        fixtureId: Int,
    ): Response<FixtureResponseBody>

    //    Upload match commentary
    suspend fun uploadMatchCommentary(
        token: String,
        commentaryCreationRequest: CommentaryCreationRequest
    ): Response<MatchCommentaryResponseBody>

    //    Update match commentary
    suspend fun updateMatchCommentary(
        token: String,
        commentaryUpdateRequest: CommentaryUpdateRequest
    ): Response<MatchCommentaryResponseBody>

    //    Upload commentary files
    suspend fun uploadMatchCommentaryFiles(
        token: String,
        commentaryId: Int,
        files: List<MultipartBody.Part>,
    ): Response<MatchCommentaryResponseBody>

    //    Get match commentary
    suspend fun getMatchCommentary(
        token: String,
        commentaryId: Int,
    ): Response<MatchCommentaryResponseBody>

    //    Get full match details
    suspend fun getFullMatchDetails(
        token: String,
        postMatchAnalysisId: Int,
    ): Response<FullMatchResponseBody>

    //    Get clubs
    suspend fun getClubs(
        token: String
    ): Response<ClubsResponseBody>

    //    Get club
    suspend fun getClub(
        token: String,
        clubId: Int,
    ): Response<ClubsResponseBody>

    //    Add club
    suspend fun addClub(
        token: String,
        clubAdditionRequest: ClubAdditionRequest,
        logo: MultipartBody.Part
    ): Response<ClubResponseBody>

}