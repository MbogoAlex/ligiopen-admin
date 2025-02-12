package com.admin.ligiopen.data.network.repository

import com.admin.ligiopen.data.network.models.auth.UserLoginRequestBody
import com.admin.ligiopen.data.network.models.auth.UserLoginResponseBody
import com.admin.ligiopen.data.network.models.match.location.LocationCreation
import com.admin.ligiopen.data.network.models.match.location.LocationUpdate
import com.admin.ligiopen.data.network.models.match.location.MatchLocationResponseBody
import com.admin.ligiopen.data.network.models.match.location.MatchLocationsResponseBody
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface ApiRepository {

    suspend fun login(userLoginRequestBody: UserLoginRequestBody): Response<UserLoginResponseBody>

    //    Create match location
    suspend fun createMatchLocation(
        token: String,
        locationCreation: LocationCreation
    ): Response<MatchLocationResponseBody>

//    Update match location
    suspend fun updateMatchLocation(
        token: String,
        locationUpdate: LocationUpdate
    ): Response<MatchLocationResponseBody>

//    Upload match location photos
    suspend fun uploadMatchLocationPhotos(
        token: String,
        locationId: Int,
        files: List<MultipartBody.Part>,
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


}