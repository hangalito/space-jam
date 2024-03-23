package com.hangalo.spacejam.data

import com.hangalo.spacejam.network.ApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit


class DefaultAppContainer : AppContainer {

    private val baseUrl: String = "https://api.nasa.gov/planetary/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl).build()

    private val retrofitService: ApiService = retrofit.create(ApiService::class.java)

    override val repository: SpaceJamRepository by lazy {
        NetworkRepository(retrofitService)
    }


}
