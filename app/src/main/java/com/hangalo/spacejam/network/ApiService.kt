package com.hangalo.spacejam.network

import com.hangalo.spacejam.BuildConfig
import com.hangalo.spacejam.model.AstronomicPicture
import retrofit2.http.GET


interface ApiService {
    @GET("apod?api_key=${BuildConfig.NASA_API_KEY}")
    suspend fun getTodayPicture(): AstronomicPicture
}
