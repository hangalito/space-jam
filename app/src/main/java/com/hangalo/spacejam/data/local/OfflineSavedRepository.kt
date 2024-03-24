package com.hangalo.spacejam.data.local

import com.hangalo.spacejam.model.AstronomicPicture
import kotlinx.coroutines.flow.Flow


class OfflineSavedRepository(private val astronomicPictureDao: AstronomicPictureDao) :
    SavedRepository {
    override suspend fun getAllStream(): Flow<List<AstronomicPicture>> {
        return astronomicPictureDao.getAll()
    }

    override suspend fun getByDate(date: String): Flow<AstronomicPicture?> {
        return astronomicPictureDao.getByDate(date)
    }

    override suspend fun insert(astronomicPicture: AstronomicPicture) {
        astronomicPictureDao.insert(astronomicPicture)
    }

    override suspend fun delete(astronomicPicture: AstronomicPicture) {
        astronomicPictureDao.delete(astronomicPicture)
    }
}
