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

    init {
        getTodayPicture()
    }


    fun getTodayPicture() {
        uiState = UiState.Loading
        viewModelScope.launch {
            uiState = try {
                UiState.Success(apodRepository.getTodayPicture())
            } catch (ex: HttpException) {
                Log.d("HttpException", ex.localizedMessage as String)
                UiState.Error
            } catch (ex: IOException) {
                Log.d("IOException", ex.localizedMessage as String)
                UiState.Error
            }
        }
    }

    fun getYesterdayPicture() {
        uiState = UiState.Loading
        viewModelScope.launch {
            uiState = try {
                UiState.Success(apodRepository.getYesterdayPicture())
            } catch (ex: HttpException) {
                Log.d("HttpException", ex.localizedMessage as String)
                UiState.Error
            } catch (ex: IOException) {
                Log.d("IOException", ex.localizedMessage as String)
                UiState.Error
            }
        }
    }
}
