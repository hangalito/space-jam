package com.hangalo.spacejam.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.hangalo.spacejam.domain.AstronomicPicture
import com.hangalo.spacejam.ui.components.AstronomicPictureCard
import com.hangalo.spacejam.ui.components.AstronomicPictureView
import com.hangalo.spacejam.ui.components.ErrorScreen
import com.hangalo.spacejam.ui.components.LoadingScreen
import com.hangalo.spacejam.ui.screens.UiState.Error
import com.hangalo.spacejam.ui.screens.UiState.Loading
import com.hangalo.spacejam.ui.screens.UiState.Success.MultiplePictures
import com.hangalo.spacejam.ui.screens.UiState.Success.SinglePicture

@Composable
@NonRestartableComposable
fun ScreenManager(
    uiState: UiState,
    onRetryClick: () -> Unit,
) {
    val modifier: Modifier = Modifier.fillMaxSize()
    when (uiState) {
        is Loading -> LoadingScreen(modifier)
        is Error -> ErrorScreen(uiState, modifier, onRetryClick = onRetryClick)

        is SinglePicture -> SinglePictureView(
            data = uiState.picture,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize()
                .verticalScroll(rememberScrollState())
        )

        is MultiplePictures -> MultiplePicturesView(data = uiState.pictures, modifier)
    }
}

@Composable
fun SinglePictureView(data: AstronomicPicture, modifier: Modifier = Modifier) {
    AstronomicPictureView(apod = data, modifier = modifier)
}

@Composable
fun MultiplePicturesView(
    data: List<AstronomicPicture>,
    modifier: Modifier = Modifier,
) {
    val gap: Dp = 8.dp

    LazyColumn(
        contentPadding = PaddingValues(12.dp),
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(gap)
    ) {
        items(data) { apod ->
            if (apod.mediaType == "image") {
                AstronomicPictureCard(apod)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun MultiplePicturesViewPreview() {
    val mockData = AstronomicPicture(
        date = "2024-09-11",
        explanation = "A natural border between Slovakia and Poland is the Tatra Mountains. A prominent destination for astrophotographers, the Tatras are the highest mountain range in the Carpathians. In the featured image taken in May, one can see the center of our Milky Way galaxy with two of its famous stellar nurseries, the Lagoon and Omega Nebula, just over the top of the Tatras. Stellar nurseries are full of ionized hydrogen, a fundamental component for the formation of Earth-abundant water. As a fundamental ingredient in all known forms of life, water is a crucial element in the Universe. Such water can be seen in the foreground in the form of the Bialka River.   Portal Universe: Random APOD Generator",
        copyright = "Bartolomeu Hangalo",
        hdUrl = "https://apod.nasa.gov/apod/image/2409/NightTatra_Rosadzinski_5028.jpg",
        mediaType = "image",
        serviceName = "v1",
        title = "A Night Sky over the Tatra Mountains",
        url = "https://apod.nasa.gov/apod/image/2409/NightTatra_Rosadzinski_960.jpg"
    )
    val data = mutableListOf<AstronomicPicture>()
    repeat(20) { data.add(mockData) }
    MultiplePicturesView(data)
}
