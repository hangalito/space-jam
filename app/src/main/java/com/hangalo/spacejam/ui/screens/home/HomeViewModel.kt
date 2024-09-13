package com.hangalo.spacejam.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.hangalo.spacejam.domain.data.remote.apod.APODRepository
import com.hangalo.spacejam.ui.screens.UiState
import com.hangalo.spacejam.ui.screens.UiState.Success
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException

class HomeViewModel(private val networkRepository: APODRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()
    var retry: () -> Unit = this::getPicture

    init {
        viewModelScope.launch {
            try {
                _uiState.emit(Success(networkRepository.getTodayPicture()))
            } catch (error: HttpException) {
                _uiState.emit(UiState.Error(error.response.message))
            } catch (error: IOException) {
                _uiState.emit(UiState.Error(error.localizedMessage.orEmpty()))
            }
        }
    }

    fun getPicture() {
        viewModelScope.launch {
            _uiState.emit(UiState.Loading)
            try {
                _uiState.emit(Success(networkRepository.getTodayPicture()))
            } catch (error: HttpException) {
                _uiState.emit(UiState.Error(error.response.message))
            } catch (error: IOException) {
                _uiState.emit(UiState.Error(error.localizedMessage.orEmpty()))
            }
        }
    }
}
