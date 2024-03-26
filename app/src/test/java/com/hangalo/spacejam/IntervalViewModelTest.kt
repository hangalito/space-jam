package com.hangalo.spacejam

import com.hangalo.spacejam.data.FakeDatasource
import com.hangalo.spacejam.data.FakeDatasource.astronomicPictures
import com.hangalo.spacejam.data.remote.FakeApiService
import com.hangalo.spacejam.data.remote.apod.NetworkRepository
import com.hangalo.spacejam.rules.TestDispatcherRule
import com.hangalo.spacejam.ui.screens.interval.IntervalUiState
import com.hangalo.spacejam.ui.screens.interval.IntervalViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import java.sql.Date


class IntervalViewModelTest {

    @get:Rule
    val testRule = TestDispatcherRule()

    @Test
    fun intervalViewModel_getFromDate_verifyData() = runTest {
        val viewModel = IntervalViewModel(NetworkRepository(FakeApiService()))
        val startDate = FakeDatasource.twoDaysAgo().date
        val data = listOf(astronomicPictures[0], astronomicPictures[1], astronomicPictures[2])
        val actual = IntervalUiState.Success(data)
        viewModel.getPicturesFromDate(Date.valueOf(startDate).time)
        assertEquals(
            actual,
            viewModel.uiState
        )

    }
}
