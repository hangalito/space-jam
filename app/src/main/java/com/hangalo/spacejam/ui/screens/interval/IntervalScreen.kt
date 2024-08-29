package com.hangalo.spacejam.ui.screens.interval

import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hangalo.spacejam.domain.AstronomicPicture
import com.hangalo.spacejam.ui.util.AstronomicPictureChip
import com.hangalo.spacejam.ui.util.ErrorScreen
import com.hangalo.spacejam.ui.util.LoadingScreen


@Composable
fun IntervalScreen(
    uiState: IntervalUiState,
    modifier: Modifier = Modifier,
    retryAction: () -> Unit,
) {
    when (uiState) {
        IntervalUiState.Loading -> LoadingScreen(modifier)
        is IntervalUiState.Success -> AstronomicPicturesList(data = uiState.data, modifier)
        is IntervalUiState.DefaultError -> ErrorScreen(uiState, modifier, retryAction)
        is IntervalUiState.IncorrectInterval -> ErrorScreen(uiState, modifier, retryAction)
    }
}

@Composable
fun AstronomicPicturesList(
    data: List<AstronomicPicture>,
    modifier: Modifier = Modifier,
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(90.dp),
        modifier = modifier
    ) {
        items(data, key = { it.date }) { astronomicPicture ->
            AstronomicPictureChip(astronomicPicture) {}
        }
    }
}
