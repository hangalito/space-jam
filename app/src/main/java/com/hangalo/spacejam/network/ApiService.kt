package com.hangalo.spacejam.network

import com.hangalo.spacejam.BuildConfig
import com.hangalo.spacejam.model.AstronomicPicture
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
    @GET("apod?api_key=${BuildConfig.NASA_API_KEY}")
    suspend fun getTodayPicture(): AstronomicPicture

    @GET("apod?api_key=${BuildConfig.NASA_API_KEY}")
    suspend fun getYesterdayPicture(@Query("date") date: String): AstronomicPicture
}
