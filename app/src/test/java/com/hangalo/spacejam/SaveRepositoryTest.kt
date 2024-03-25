package com.hangalo.spacejam

import com.hangalo.spacejam.data.FakeDatasource
import com.hangalo.spacejam.data.local.FakeSavedRepository
import com.hangalo.spacejam.data.local.OfflineSavedRepository
import com.hangalo.spacejam.model.AstronomicPicture
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import java.sql.Date


class SaveRepositoryTest {

    @Test
    fun savedRepository_getAll_verify() = runTest {
        val savedRepository = OfflineSavedRepository(FakeSavedRepository())
        var actual = listOf<AstronomicPicture>()
        savedRepository.getAllStream().collect { actual = it }
        Assert.assertEquals(
            FakeDatasource.astronomicPictures,
            actual
        )
    }

    @Test
    fun savedRepository_getByDate_verify() = runTest {
        val savedRepository = OfflineSavedRepository(FakeSavedRepository())
        val date = "2004-11-10"
        var actual: AstronomicPicture? = null
        savedRepository.getByDate(date).collect { actual = it }
        Assert.assertEquals(
            FakeDatasource.getByDate(Date.valueOf(date).time),
            actual
        )
    }

    @Test
    fun savedRepository_insert_verify() = runTest {
        val savedRepository = OfflineSavedRepository(FakeSavedRepository())
        val actual = FakeDatasource.astronomicPictures
        val astronomicPicture = AstronomicPicture("", "", "", "", "", "", "")
        savedRepository.insert(astronomicPicture)
        actual.add(astronomicPicture)
        Assert.assertEquals(
            FakeDatasource.astronomicPictures,
            actual
        )
    }

    @Test
    fun savedRepository_delete_verify() = runTest {
        val savedRepository = OfflineSavedRepository(FakeSavedRepository())
        val actual = FakeDatasource.astronomicPictures
        val astronomicPicture = AstronomicPicture("", "", "", "", "", "", "")
        savedRepository.delete(astronomicPicture)
        actual.remove(astronomicPicture)
        Assert.assertEquals(
            FakeDatasource.astronomicPictures,
            actual
        )
    }
}
