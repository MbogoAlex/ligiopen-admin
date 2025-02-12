package com.admin.ligiopen.data.network.repository

import com.admin.ligiopen.data.network.models.auth.UserLoginRequestBody
import com.admin.ligiopen.data.network.models.auth.UserLoginResponseBody
import retrofit2.Response

class ApiRepositoryImpl(private val apiService: ApiService): ApiRepository {
    override suspend fun login(userLoginRequestBody: UserLoginRequestBody): Response<UserLoginResponseBody> =
        apiService.login(userLoginRequestBody)
}