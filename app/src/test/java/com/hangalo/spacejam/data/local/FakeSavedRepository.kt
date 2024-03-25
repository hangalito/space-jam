package com.hangalo.spacejam.data.local

import com.hangalo.spacejam.data.FakeDatasource
import com.hangalo.spacejam.model.AstronomicPicture
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import java.sql.Date


class FakeSavedRepository : AstronomicPictureDao {

    override fun getAll(): Flow<List<AstronomicPicture>> {
        return object : Flow<List<AstronomicPicture>> {
            override suspend fun collect(collector: FlowCollector<List<AstronomicPicture>>) {
                collector.emit(FakeDatasource.astronomicPictures)
            }
        }
    }

    override suspend fun getByDate(date: String): Flow<AstronomicPicture?> {
        return object : Flow<AstronomicPicture?> {
            override suspend fun collect(collector: FlowCollector<AstronomicPicture?>) {
                collector.emit(FakeDatasource.getByDate(Date.valueOf(date).time))
            }
        }
    }

    override suspend fun insert(astronomicPicture: AstronomicPicture) {
        FakeDatasource.astronomicPictures.add(astronomicPicture)
    }

    override suspend fun delete(astronomicPicture: AstronomicPicture) {
        FakeDatasource.astronomicPictures.remove(astronomicPicture)
    }
}
