package com.hangalo.spacejam.ui.screens.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hangalo.spacejam.data.remote.apod.APODRepository
import com.hangalo.spacejam.ui.screens.UiState
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


class HomeViewModel(private val apodRepository: APODRepository) : ViewModel() {
    var uiState: UiState by mutableStateOf(UiState.Loading)
        private set

    var retry: () -> Unit = {}

    init {
        getTodayPicture()
    }


    fun getTodayPicture() {
        retry = { getTodayPicture() }
        uiState = UiState.Loading
        viewModelScope.launch {
            uiState = try {
                UiState.Success(apodRepository.getTodayPicture())
            } catch (ex: HttpException) {
                Log.d("HttpException", ex.localizedMessage as String)
                UiState.Error.Error
            } catch (ex: IOException) {
                Log.d("IOException", ex.localizedMessage as String)
                UiState.Error.Error
            }
        }
    }

    fun getYesterdayPicture() {
        retry = { getYesterdayPicture() }
        uiState = UiState.Loading
        viewModelScope.launch {
            uiState = try {
                UiState.Success(apodRepository.getYesterdayPicture())
            } catch (ex: HttpException) {
                Log.d("HttpException", ex.localizedMessage as String)
                UiState.Error.Error
            } catch (ex: IOException) {
                Log.d("IOException", ex.localizedMessage as String)
                UiState.Error.Error
            }
        }
    }

    fun get2daysAgoPicture() {
        retry = { get2daysAgoPicture() }
        uiState = UiState.Loading
        viewModelScope.launch {
            uiState = try {
                UiState.Success(apodRepository.get2daysAgoPicture())
            } catch (ex: HttpException) {
                Log.d("HttpException", ex.localizedMessage as String)
                UiState.Error.Error
            } catch (ex: IOException) {
                Log.d("IOException", ex.localizedMessage as String)
                UiState.Error.Error
            }
        }
    }

    fun getPictureByDate(dateMillis: Long) {
        retry = { getPictureByDate(dateMillis) }
        uiState = UiState.Loading
        viewModelScope.launch {
            uiState = try {
                UiState.Success(apodRepository.getPictureByDate(dateMillis))
            } catch (ex: HttpException) {
                var err: UiState = UiState.Error.Error
                val code = ex.code()
                Log.d("HttpException", ex.localizedMessage as String)
                if (code == 400) {
                    err =
                        UiState.Error.InvalidDate("Date must be between Jun 16, 1995 and Mar 24, 2024.")
                }
                err
            } catch (ex: IOException) {
                Log.d("IOException", ex.localizedMessage as String)
                UiState.Error.Error
            }
        }
    }
}
