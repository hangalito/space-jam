package com.hangalo.spacejam.network

import com.hangalo.spacejam.BuildConfig
import com.hangalo.spacejam.model.AstronomicPicture
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    /**
     * Get the [AstronomicPicture] of the current day
     * @return [AstronomicPicture]
     */
    @GET("apod?api_key=${BuildConfig.NASA_API_KEY}")
    suspend fun getTodayPicture(): AstronomicPicture

    /**
     * Get the picture from the APOD API in the specified date
     * @param date The date to get the picture of
     * @return [AstronomicPicture] on the specified date
     */
    @GET("apod?api_key=${BuildConfig.NASA_API_KEY}")
    suspend fun getPictureByDate(@Query("date") date: String): AstronomicPicture

    /**
     * Get the pictures from the the APOD API from the specified date to the current date
     * @param startDate The date to get the pictures from
     * @return [List] of [AstronomicPicture] from the specified date range
     */
    @GET("apod?api_key=${BuildConfig.NASA_API_KEY}")
    suspend fun getPicturesFrom(@Query("start_date") startDate: String): List<AstronomicPicture>
}
