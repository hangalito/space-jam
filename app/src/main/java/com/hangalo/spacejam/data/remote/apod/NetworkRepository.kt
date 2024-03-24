package com.hangalo.spacejam.data.remote.apod

import com.hangalo.spacejam.model.AstronomicPicture
import com.hangalo.spacejam.network.ApiService


data class NetworkRepository(private val apiService: ApiService) : APODRepository {
    override suspend fun getTodayPicture(): AstronomicPicture {
        return apiService.getTodayPicture()
    }
}
