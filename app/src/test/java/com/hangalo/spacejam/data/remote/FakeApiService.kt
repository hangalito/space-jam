package com.hangalo.spacejam.data.remote

import com.hangalo.spacejam.data.FakeDatasource
import com.hangalo.spacejam.model.AstronomicPicture
import com.hangalo.spacejam.network.ApiService
import java.sql.Date


class FakeApiService : ApiService {
    override suspend fun getTodayPicture(): AstronomicPicture {
        return FakeDatasource.today()
    }

    override suspend fun getPictureByDate(date: String): AstronomicPicture {
        return FakeDatasource.getByDate(Date.valueOf(date).time)
    }

    override suspend fun getPicturesFrom(startDate: String): List<AstronomicPicture> {
        val initial = Date.valueOf(startDate).time
        val final = Date(System.currentTimeMillis()).time
        val res = mutableListOf<AstronomicPicture>()

        FakeDatasource.astronomicPictures.onEach {
            val date = Date.valueOf(it.date).time
            if (date in initial..final) res.add(it)
        }

        return res
    }
}
