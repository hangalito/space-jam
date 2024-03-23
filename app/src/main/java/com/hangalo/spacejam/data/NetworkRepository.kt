package com.hangalo.spacejam.data

import com.hangalo.spacejam.model.AstronomicPicture
import com.hangalo.spacejam.network.ApiService


data class NetworkRepository(private val apiService: ApiService) : SpaceJamRepository {
    override suspend fun getTodayPicture(): AstronomicPicture {
        return apiService.getTodayPicture()
    }
}
