package com.hangalo.spacejam.ui.screens.home

import androidx.annotation.StringRes
import com.hangalo.spacejam.domain.AstronomicPicture
import com.hangalo.spacejam.ui.screens.DefaultUiState


sealed interface HomeUiState {
    data object Loading : HomeUiState
    data class Success(val data: AstronomicPicture) : HomeUiState

    data object Error : DefaultUiState.Error, HomeUiState

    data class InvalidDate(@StringRes val msg: Int) : DefaultUiState.Error, HomeUiState
}
