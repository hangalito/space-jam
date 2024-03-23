package com.hangalo.spacejam.ui.screens

import com.hangalo.spacejam.model.AstronomicPicture


sealed interface UiState {
    data object Loading : UiState
    data object Error : UiState
    data class Success(val data: AstronomicPicture) : UiState
}
