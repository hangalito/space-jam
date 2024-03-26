package com.hangalo.spacejam.ui.screens.interval

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.hangalo.spacejam.ui.utils.ErrorScreen
import com.hangalo.spacejam.ui.utils.LoadingScreen


@Composable
fun IntervalScreen(
    intervalUiState: IntervalUiState,
    modifier: Modifier = Modifier,
    retryAction: () -> Unit,
) {
    when (intervalUiState) {
        IntervalUiState.Loading -> LoadingScreen(modifier)
        is IntervalUiState.Success -> {}
        is IntervalUiState.DefaultError -> ErrorScreen(intervalUiState, modifier, retryAction)
        is IntervalUiState.IncorrectInterval -> ErrorScreen(intervalUiState, modifier, retryAction)
    }
}
