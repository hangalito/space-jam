package com.hangalo.spacejam.ui.screens.interval

import androidx.annotation.StringRes
import com.hangalo.spacejam.model.AstronomicPicture
import com.hangalo.spacejam.ui.screens.DefaultUiState


sealed interface IntervalUiState : DefaultUiState {
    data object Loading : IntervalUiState

    data class Success(val data: List<AstronomicPicture>) : IntervalUiState

    data object DefaultError : DefaultUiState.Error, IntervalUiState

    data class IncorrectInterval(@StringRes val msg: Int) : DefaultUiState.Error, IntervalUiState

}
