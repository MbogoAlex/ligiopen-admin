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

interface ApiService {
    @POST("auth/login")
    suspend fun login(
        @Body userLoginRequestBody: UserLoginRequestBody
    ): Response<UserLoginResponseBody>

//    Create match location

    @POST("match-location")
    suspend fun createMatchLocation(
        @Header("Authorization") token: String,
        @Body locationCreation: LocationCreation
    ): Response<MatchLocationResponseBody>

//    Update match location

    @PUT("match-location")
    suspend fun updateMatchLocation(
        @Header("Authorization") token: String,
        @Body locationUpdate: LocationUpdate
    ): Response<MatchLocationResponseBody>

//    Upload match location photos

    @Multipart
    @PUT("match-location/files/{locationId}")
    suspend fun uploadMatchLocationPhotos(
        @Header("Authorization") token: String,
        @Path("locationId") locationId: Int,
        @Part files: List<MultipartBody.Part>,
    ): Response<MatchLocationResponseBody>

//    Get match location

    @GET("match-location/{locationId}")
    suspend fun getMatchLocation(
        @Header("Authorization") token: String,
        @Path("locationId") locationId: Int,
    ): Response<MatchLocationResponseBody>

//    Get match locations

    @GET("match-location/all")
    suspend fun getMatchLocations(
        @Header("Authorization") token: String,
    ): Response<MatchLocationsResponseBody>

}