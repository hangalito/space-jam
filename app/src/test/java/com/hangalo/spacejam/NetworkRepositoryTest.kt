package com.hangalo.spacejam

import com.hangalo.spacejam.data.remote.FakeApiService
import com.hangalo.spacejam.data.FakeDatasource
import com.hangalo.spacejam.data.remote.apod.NetworkRepository
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
        assertEquals(repository.getTodayPicture(), FakeDatasource.today())
    }

    @Test
    fun networkAstronomicPicturesRepository_getYesterdayPicture_verifyPicture() = runTest {
        val repository = NetworkRepository(FakeApiService())
        assertEquals(repository.getYesterdayPicture(), FakeDatasource.yesterday())
    }

    @Test
    fun networkAstronomicPicturesRepository_get2daysAgoPicture_verifyPicture() = runTest {
        val repository = NetworkRepository(FakeApiService())
        assertEquals(repository.get2daysAgoPicture(), FakeDatasource.twoDaysAgo())
    }

    @Test
    fun networkAstronomicPicturesRepository_getPictureByDate_verifyPicture() = runTest {
        val repository = NetworkRepository(FakeApiService())
        val dateMillis = Date.valueOf("2004-11-10").time
        assertEquals(repository.getPictureByDate(dateMillis), FakeDatasource.getByDate(dateMillis))
    }
}
