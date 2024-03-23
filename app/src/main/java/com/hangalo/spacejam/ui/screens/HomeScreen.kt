package com.hangalo.spacejam.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.hangalo.spacejam.R
import com.hangalo.spacejam.model.AstronomicPicture

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
        Image(
            modifier = modifier.size(240.dp),
            painter = painterResource(id = R.drawable.loading_img),
            contentDescription = stringResource(R.string.loading)
        )
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
    Column(
        modifier = modifier.fillMaxWidth(), horizontalAlignment = CenterHorizontally
    ) {
        Text(
            text = astronomicPicture.title,
            style = typography.titleLarge,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(8.dp)
        )
        Box {
            Image(
                painter = painterResource(id = R.drawable.loading_img),
                contentDescription = stringResource(id = R.string.loading),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
            )
            AsyncImage(
                contentDescription = astronomicPicture.title,
                model = ImageRequest.Builder(LocalContext.current).data(astronomicPicture.url)
                    .crossfade(true).crossfade(1200).build(),
                error = painterResource(id = R.drawable.ic_broken_img),
                placeholder = painterResource(id = R.drawable.ic_img_placeholder),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
                    .padding(8.dp)
            )
        }
        Text(
            text = astronomicPicture.explanation,
            style = typography.bodyMedium,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Justify,
            modifier = Modifier.padding(12.dp)
        )
    }
}
