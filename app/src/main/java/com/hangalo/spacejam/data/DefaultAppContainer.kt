package com.hangalo.spacejam.data

import android.content.Context
import com.hangalo.spacejam.data.local.OfflineSavedRepository
import com.hangalo.spacejam.data.local.SavedRepository
import com.hangalo.spacejam.data.local.SpaceJamDatabase
import com.hangalo.spacejam.data.remote.apod.APODRepository
import com.hangalo.spacejam.data.remote.apod.NetworkRepository
import com.hangalo.spacejam.network.ApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit


/**
 * [AppContainer] implementation that provides instance of [APODRepository] and [SavedRepository]
 */
class DefaultAppContainer(private val context: Context) : AppContainer {

    private val baseUrl: String = "https://api.nasa.gov/planetary/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl).build()

    private val retrofitService: ApiService = retrofit.create(ApiService::class.java)

    /**
     * Implementation for [APODRepository]
     */
    override val repository: APODRepository by lazy {
        NetworkRepository(retrofitService)
    }

    /**
     * Implementation for [SavedRepository]
     */
    override val savedRepository: SavedRepository by lazy {
        OfflineSavedRepository(SpaceJamDatabase.getInstance(context).astronomicPictureDao())
    }


}
