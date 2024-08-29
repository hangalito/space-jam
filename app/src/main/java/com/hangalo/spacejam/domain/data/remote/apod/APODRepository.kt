package com.hangalo.spacejam.domain.data.remote.apod

import com.hangalo.spacejam.domain.AstronomicPicture


interface APODRepository {
    suspend fun getTodayPicture(): AstronomicPicture

    suspend fun getYesterdayPicture(): AstronomicPicture

    suspend fun getPictureByDate(dateMillis: Long): AstronomicPicture

    suspend fun getPicturesFrom(starDate: Long): List<AstronomicPicture>

    suspend fun getPicturesWithinInterval(
        starDate: Long,
        endDate: Long
    ): List<AstronomicPicture>
}
