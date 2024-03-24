package com.hangalo.spacejam.ui.screens.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hangalo.spacejam.data.local.SavedRepository
import com.hangalo.spacejam.data.remote.apod.APODRepository
import com.hangalo.spacejam.ui.screens.UiState
import com.hangalo.spacejam.ui.screens.utils.isSaved
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


class HomeViewModel(
    private val apodRepository: APODRepository,
    private val savedRepository: SavedRepository,
) : ViewModel() {
    var uiState: UiState by mutableStateOf(UiState.Loading)
        private set

    init {
        getTodayPicture()
    }

    suspend fun isSaved(): Boolean {
        var fav = false
        if (uiState is UiState.Success) {
            with(uiState as UiState.Success) {
                fav = data.isSaved(savedRepository)
            }
        }
        return fav
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
}
