package com.hangalo.spacejam.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.hangalo.spacejam.R
import com.hangalo.spacejam.model.AstronomicPicture
import com.hangalo.spacejam.ui.screens.UiState
import com.hangalo.spacejam.ui.screens.utils.AstronomicPictureCard

@Composable
fun HomeScreen(
    uiState: UiState,
    modifier: Modifier = Modifier,
    retryAction: () -> Unit,
) {
    when (uiState) {
        UiState.Error -> ErrorScreen(modifier, retryAction)
        UiState.Loading -> LoadingScreen(modifier)
        is UiState.Success -> SuccessScreen(uiState.data, modifier)
    }
}


@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator()
    }
}


@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    retryAction: () -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_offline),
            contentDescription = stringResource(R.string.offline),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .padding(4.dp)
        )
        Text(
            text = stringResource(id = R.string.error_loading),
            modifier = Modifier.padding(4.dp)
        )
        Button(
            onClick = retryAction,
            modifier = Modifier.padding(4.dp)
        ) {
            Text(stringResource(id = R.string.retry))
        }
    }
}

@Composable
fun SuccessScreen(
    astronomicPicture: AstronomicPicture,
    modifier: Modifier = Modifier,
) {
    AstronomicPictureCard(astronomicPicture, modifier)
}
