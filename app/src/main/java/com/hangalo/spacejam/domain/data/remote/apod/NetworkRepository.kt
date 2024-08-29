package com.hangalo.spacejam.domain.data.remote.apod

import com.hangalo.spacejam.domain.AstronomicPicture
import com.hangalo.spacejam.domain.network.ApiService
import java.sql.Date


/**
 * [APODRepository] implementation.
 */
data class NetworkRepository(private val apiService: ApiService) : APODRepository {
    override suspend fun getTodayPicture(): AstronomicPicture {
        return apiService.getTodayPicture()
    }

    override suspend fun getYesterdayPicture(): AstronomicPicture {
        val today = Date(System.currentTimeMillis()).toString().split("-")
        val date = "${today.first()}-${today[1]}-${today.last().toInt() - 1}"
        return apiService.getPictureByDate(date)
    }

    suspend fun get2daysAgoPicture(): AstronomicPicture {
        val today = Date(System.currentTimeMillis()).toString().split("-")
        val date = "${today.first()}-${today[1]}-${today.last().toInt() - 2}"
        return apiService.getPictureByDate(date)
    }

    override suspend fun getPictureByDate(dateMillis: Long): AstronomicPicture {
        val date = Date(dateMillis).toString()
        return apiService.getPictureByDate(date)
    }

    override suspend fun getPicturesFrom(starDate: Long): List<AstronomicPicture> {
        val date = Date(starDate).toString()
        return apiService.getPicturesFrom(date)
    }

    override suspend fun getPicturesWithinInterval(
        startDate: Long,
        endDate: Long
    ): List<AstronomicPicture> {
        val startDateString = Date(startDate).toString()
        val endDateString = Date(endDate).toString()
        return apiService.getPicturesInInterval(startDateString, endDateString)
    }
}
