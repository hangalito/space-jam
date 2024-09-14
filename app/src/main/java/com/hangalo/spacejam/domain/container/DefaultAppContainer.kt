package com.hangalo.spacejam.domain.container

import android.content.Context
import com.hangalo.spacejam.domain.data.local.LocalRepository
import com.hangalo.spacejam.domain.data.local.LocalRepositoryImpl
import com.hangalo.spacejam.domain.data.local.SpaceJamDatabase
import com.hangalo.spacejam.domain.data.remote.RemoteRepository
import com.hangalo.spacejam.domain.data.remote.RemoteRepositoryImpl
import com.hangalo.spacejam.domain.network.ApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

/**
 * [AppContainer] implementation that provides instance of [RemoteRepository] and [LocalRepository]
 */
class DefaultAppContainer(private val context: Context) : AppContainer {

    /**
     * API end-point.
     */
    private val baseUrl: String = "https://api.nasa.gov/planetary/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl).build()

    private val retrofitService: ApiService = retrofit.create(ApiService::class.java)

    /**
     * Implementation for [RemoteRepository]
     */
    override val remoteRepository: RemoteRepository by lazy {
        RemoteRepositoryImpl(retrofitService)
    }

    /**
     * Implementation for [LocalRepository]
     */
    override val localRepository: LocalRepository by lazy {
        LocalRepositoryImpl(SpaceJamDatabase.getInstance(context).astronomicPictureDao())
    }


}
