package com.hangalo.spacejam.domain.data.local

import com.hangalo.spacejam.domain.AstronomicPicture
import kotlinx.coroutines.flow.Flow

class LocalRepositoryImpl(private val astronomicPictureDao: AstronomicPictureDao) :
    LocalRepository {
    override fun getAllStream(): Flow<List<AstronomicPicture>> {
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
