package com.hangalo.spacejam

import com.hangalo.spacejam.data.FakeDatasource
import com.hangalo.spacejam.data.remote.FakeApiService
import com.hangalo.spacejam.data.remote.apod.NetworkRepository
import com.hangalo.spacejam.rules.TestDispatcherRule
import com.hangalo.spacejam.ui.screens.UiState
import com.hangalo.spacejam.ui.screens.home.HomeViewModel
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import java.sql.Date


class HomeViewModelTest {

    @get:Rule
    val testDispatcherRule = TestDispatcherRule()

    @Test
    fun homeViewModel_getToday_verify() {
        val viewModel = HomeViewModel(NetworkRepository(FakeApiService()))
        viewModel.getTodayPicture()
        assertEquals(viewModel.uiState, UiState.Success(FakeDatasource.today()))
    }

    @Test
    fun homeViewModel_getYesterday_verify() {
        val viewModel = HomeViewModel(NetworkRepository(FakeApiService()))
        viewModel.getYesterdayPicture()
        assertEquals(viewModel.uiState, UiState.Success(FakeDatasource.yesterday()))
    }

    @Test
    fun homeViewModel_get2daysAgo_verify() {
        val viewModel = HomeViewModel(NetworkRepository(FakeApiService()))
        viewModel.get2daysAgoPicture()
        assertEquals(viewModel.uiState, UiState.Success(FakeDatasource.twoDaysAgo()))
    }

    @Test
    fun homeViewModel_getByDate_verify() {
        val viewModel = HomeViewModel(NetworkRepository(FakeApiService()))
        val date = Date.valueOf("2004-11-10").time
        viewModel.getPictureByDate(date)
        assertEquals(
            viewModel.uiState,
            UiState.Success(FakeDatasource.getByDate(date))
        )
    }
}
