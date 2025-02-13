package com.admin.ligiopen.container

import android.content.Context
import com.admin.ligiopen.data.network.repository.ApiRepository
import com.admin.ligiopen.data.network.repository.ApiRepositoryImpl
import com.admin.ligiopen.data.network.repository.ApiService
import com.admin.ligiopen.data.room.db.AppDatabase
import com.admin.ligiopen.data.room.repository.DBRepository
import com.admin.ligiopen.data.room.repository.DBRepositoryImpl
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

class AppContainerImpl(context: Context) : AppContainer {
    private val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }
    private val baseUrl = "http://192.168.100.5:8000/api/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    private val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    override val apiRepository: ApiRepository by lazy {
        ApiRepositoryImpl(retrofitService)
    }

    override val dbRepository: DBRepository by lazy {
        DBRepositoryImpl(AppDatabase.getDatabase(context).appDao())
    }
}