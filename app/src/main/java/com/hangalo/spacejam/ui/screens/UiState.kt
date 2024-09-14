package com.hangalo.spacejam.ui.screens

import com.hangalo.spacejam.domain.AstronomicPicture

sealed interface UiState {
    data object Loading : UiState
    data class Error(val message: String) : UiState
    sealed interface Success : UiState {
        data class SinglePicture(val picture: AstronomicPicture) : Success
        data class MultiplePictures(val pictures: List<AstronomicPicture>) : Success
    }
}
