package com.hangalo.spacejam.ui.screens

import com.hangalo.spacejam.domain.AstronomicPicture

sealed interface UiState {
    data object Loading : UiState
    data class Error(val message: String) : UiState
    data class Success(val apod: AstronomicPicture) : UiState
}
