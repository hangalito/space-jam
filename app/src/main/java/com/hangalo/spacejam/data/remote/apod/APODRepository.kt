package com.hangalo.spacejam.data.remote.apod

import com.hangalo.spacejam.model.AstronomicPicture


interface APODRepository {
    suspend fun getTodayPicture(): AstronomicPicture

    suspend fun getYesterdayPicture(): AstronomicPicture

    suspend fun get2daysAgoPicture(): AstronomicPicture

    suspend fun getPictureByDate(dateMillis: Long): AstronomicPicture
}
