package com.hangalo.spacejam.domain.data.remote

import com.hangalo.spacejam.domain.AstronomicPicture

interface RemoteRepository {
    suspend fun getNewestPicture(): AstronomicPicture

    suspend fun getYesterdayPicture(): AstronomicPicture

    suspend fun getPictureByDate(dateMillis: Long): AstronomicPicture

    suspend fun getPicturesFrom(startDate: Long): List<AstronomicPicture>

    suspend fun getPicturesWithinInterval(startDate: Long, endDate: Long): List<AstronomicPicture>
}
