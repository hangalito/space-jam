package com.hangalo.spacejam.data.remote.apod

import com.hangalo.spacejam.model.AstronomicPicture
import com.hangalo.spacejam.network.ApiService
import java.sql.Date


data class NetworkRepository(private val apiService: ApiService) : APODRepository {
    override suspend fun getTodayPicture(): AstronomicPicture {
        return apiService.getTodayPicture()
    }

    override suspend fun getYesterdayPicture(): AstronomicPicture {
        val today = Date(System.currentTimeMillis()).toString().split("-")
        val date = "${today.first()}-${today[1]}-${today.last().toInt() - 1}"
        return apiService.getYesterdayPicture(date)
    }
}
