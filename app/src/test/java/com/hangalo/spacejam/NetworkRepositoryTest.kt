package com.hangalo.spacejam

import com.hangalo.spacejam.data.FakeDatasource
import com.hangalo.spacejam.data.FakeDatasource.astronomicPictures
import com.hangalo.spacejam.data.remote.FakeApiService
import com.hangalo.spacejam.domain.data.remote.apod.NetworkRepository
import com.hangalo.spacejam.rules.TestDispatcherRule
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import java.sql.Date


class NetworkRepositoryTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun networkAstronomicPicturesRepository_getTodayPicture_verifyPicture() = runTest {
        val repository = NetworkRepository(FakeApiService())
        assertEquals(
            FakeDatasource.today(),
            repository.getTodayPicture()
        )
    }

    @Test
    fun networkAstronomicPicturesRepository_getYesterdayPicture_verifyPicture() = runTest {
        val repository = NetworkRepository(FakeApiService())
        assertEquals(
            FakeDatasource.yesterday(),
            repository.getYesterdayPicture()
        )
    }

    @Test
    fun networkAstronomicPicturesRepository_get2daysAgoPicture_verifyPicture() = runTest {
        val repository = NetworkRepository(FakeApiService())
        assertEquals(
            FakeDatasource.twoDaysAgo(),
            repository.get2daysAgoPicture()
        )
    }

    @Test
    fun networkAstronomicPicturesRepository_getPictureByDate_verifyPicture() = runTest {
        val repository = NetworkRepository(FakeApiService())
        val dateMillis = Date.valueOf("2004-11-10").time
        assertEquals(
            FakeDatasource.getByDate(dateMillis),
            repository.getPictureByDate(dateMillis)
        )
    }

    @Test
    fun networkAstronomicPicturesRepository_getPictureFromDate_verifyPicture() = runTest {
        val repository = NetworkRepository(FakeApiService())
        val startDate = FakeDatasource.twoDaysAgo().date
        val actual =
            listOf(astronomicPictures.first(), astronomicPictures[1], astronomicPictures[2])
        assertEquals(
            actual,
            repository.getPicturesFrom(startDate)
        )
    }
}
