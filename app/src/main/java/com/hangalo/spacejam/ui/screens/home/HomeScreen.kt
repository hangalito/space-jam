package com.hangalo.spacejam.ui.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.hangalo.spacejam.model.AstronomicPicture
import com.hangalo.spacejam.ui.utils.AstronomicPictureCard
import com.hangalo.spacejam.ui.utils.ErrorScreen
import com.hangalo.spacejam.ui.utils.LoadingScreen

@Composable
fun HomeScreen(
    homeUiState: HomeUiState,
    modifier: Modifier = Modifier,
    retryAction: () -> Unit,
) {
    when (homeUiState) {
        HomeUiState.Loading -> LoadingScreen(modifier)
        is HomeUiState.Success -> SuccessScreen(homeUiState.data, modifier)
        is HomeUiState.Error -> ErrorScreen(homeUiState, modifier, retryAction)
        else -> LoadingScreen(modifier)
    }
}


@Composable
fun SuccessScreen(
    astronomicPicture: AstronomicPicture,
    modifier: Modifier = Modifier,
) {
    AstronomicPictureCard(astronomicPicture, modifier)
}
