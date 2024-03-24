package com.hangalo.spacejam.ui.screens

import androidx.annotation.StringRes
import com.hangalo.spacejam.model.AstronomicPicture


sealed interface UiState {
    data object Loading : UiState
    data class Success(val data: AstronomicPicture) : UiState

    interface Error : UiState {
        data object Error : UiState.Error

        data class InvalidDate(@StringRes val msg: Int) : UiState.Error
    }
}
