package com.hangalo.spacejam.ui.screens.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hangalo.spacejam.domain.AstronomicPicture
import com.hangalo.spacejam.domain.container.ViewModelProvider.Factory
import com.hangalo.spacejam.ui.components.AstronomicPictureView
import com.hangalo.spacejam.ui.components.ErrorScreen
import com.hangalo.spacejam.ui.components.LoadingScreen
import com.hangalo.spacejam.ui.screens.UiState
import com.hangalo.spacejam.ui.screens.UiState.*

@Composable
@NonRestartableComposable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = Factory)
) {
    val uiState by viewModel.uiState.collectAsState()
    ScreenManager(
        uiState = uiState,
        onRetryClick = viewModel.retry,
        modifier = modifier.fillMaxSize()
    )
}

@Composable
@NonRestartableComposable
private fun ScreenManager(
    uiState: UiState,
    modifier: Modifier = Modifier,
    onRetryClick: () -> Unit,
) {
    when (uiState) {
        is Loading -> LoadingScreen(modifier)
        is Error -> ErrorScreen(
            uiState = uiState,
            modifier = modifier,
            onRetryClick = onRetryClick
        )
        is Success -> SuccessScreen(data = uiState.apod, modifier)
    }
}

@Composable
fun SuccessScreen(data: AstronomicPicture, modifier: Modifier = Modifier) {
    AstronomicPictureView(apod = data, modifier = modifier)
}
