package com.hangalo.spacejam.domain.data.local

import com.hangalo.spacejam.domain.AstronomicPicture
import kotlinx.coroutines.flow.Flow


interface SavedRepository {

    suspend fun getAllStream(): Flow<List<AstronomicPicture>>

    suspend fun getByDate(date: String): Flow<AstronomicPicture?>

    suspend fun insert(astronomicPicture: AstronomicPicture)

    suspend fun delete(astronomicPicture: AstronomicPicture)

}
