package com.admin.ligiopen.data.network.repository

import com.admin.ligiopen.data.network.models.auth.UserLoginRequestBody
import com.admin.ligiopen.data.network.models.auth.UserLoginResponseBody
import com.admin.ligiopen.data.network.models.match.location.LocationCreation
import com.admin.ligiopen.data.network.models.match.location.LocationUpdate
import com.admin.ligiopen.data.network.models.match.location.MatchLocationResponseBody
import com.admin.ligiopen.data.network.models.match.location.MatchLocationsResponseBody
import okhttp3.MultipartBody
import retrofit2.Response

class ApiRepositoryImpl(private val apiService: ApiService): ApiRepository {
    override suspend fun login(userLoginRequestBody: UserLoginRequestBody): Response<UserLoginResponseBody> =
        apiService.login(userLoginRequestBody)

    override suspend fun createMatchLocation(
        token: String,
        locationCreation: LocationCreation
    ): Response<MatchLocationResponseBody> =
        apiService.createMatchLocation(
            token = "Bearer $token",
            locationCreation = locationCreation
        )

    override suspend fun updateMatchLocation(
        token: String,
        locationUpdate: LocationUpdate
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
}