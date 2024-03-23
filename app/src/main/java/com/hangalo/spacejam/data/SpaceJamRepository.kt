package com.hangalo.spacejam.data

import com.hangalo.spacejam.model.AstronomicPicture


interface SpaceJamRepository {
    suspend fun getTodayPicture(): AstronomicPicture
}
