package com.hangalo.spacejam.ui.screens.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hangalo.spacejam.R
import com.hangalo.spacejam.domain.data.remote.apod.APODRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


class HomeViewModel(private val apodRepository: APODRepository) : ViewModel() {
    var homeUiState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set

    var retry: () -> Unit = {}

    init {
        getTodayPicture()
    }


    fun getTodayPicture() {
        retry = { getTodayPicture() }
        homeUiState = HomeUiState.Loading
        viewModelScope.launch {
            homeUiState = try {
                HomeUiState.Success(apodRepository.getTodayPicture())
            } catch (ex: HttpException) {
                Log.d("HttpException", ex.localizedMessage as String)
                HomeUiState.Error
            } catch (ex: IOException) {
                Log.d("IOException", ex.localizedMessage as String)
                HomeUiState.Error
            }
        }
    }

    fun getYesterdayPicture() {
        retry = { getYesterdayPicture() }
        homeUiState = HomeUiState.Loading
        viewModelScope.launch {
            homeUiState = try {
                HomeUiState.Success(apodRepository.getYesterdayPicture())
            } catch (ex: HttpException) {
                Log.d("HttpException", ex.localizedMessage as String)
                HomeUiState.Error
            } catch (ex: IOException) {
                Log.d("IOException", ex.localizedMessage as String)
                HomeUiState.Error
            }
        }
    }

    fun getPictureByDate(dateMillis: Long) {
        retry = { getPictureByDate(dateMillis) }
        homeUiState = HomeUiState.Loading
        viewModelScope.launch {
            homeUiState = try {
                HomeUiState.Success(apodRepository.getPictureByDate(dateMillis))
            } catch (ex: HttpException) {
                var err: HomeUiState = HomeUiState.Error
                val code = ex.code()
                Log.d("HttpException", ex.localizedMessage as String)
                if (code == 400) {
                    err =
                        HomeUiState.InvalidDate(R.string.invalid_date)
                }
                err
            } catch (ex: IOException) {
                Log.d("IOException", ex.localizedMessage as String)
                HomeUiState.Error
            }
        }
    }
}
