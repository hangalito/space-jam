package com.hangalo.spacejam.ui.components

import android.content.Context
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.SpaceBetween
import androidx.compose.material3.*
import androidx.compose.material3.CardDefaults.elevatedCardElevation
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.TooltipDefaults.rememberPlainTooltipPositionProvider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale.Companion.Crop
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.text.style.TextAlign.Companion.Justify
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.hangalo.spacejam.R
import com.hangalo.spacejam.domain.AstronomicPicture
import com.hangalo.spacejam.ui.screens.UiState.Error


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AstronomicPictureView(
    apod: AstronomicPicture,
    modifier: Modifier = Modifier,
    onBookmarkClick: (AstronomicPicture) -> Unit = {}
) {
    val context: Context = LocalContext.current

    Column(
        modifier = modifier.padding(8.dp)
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = SpaceBetween,
            verticalAlignment = CenterVertically
        ) {
            Text(
                text = "${stringResource(id = R.string.date_label)} ${dateFormat(apod.date)}"
            )
            TooltipBox(
                positionProvider = rememberPlainTooltipPositionProvider(),
                tooltip = { Text(text = "Bookmark this picture") },
                state = rememberTooltipState()
            ) {
                IconButton(onClick = {
                    onBookmarkClick(apod)
                    Toast.makeText(context, "Action not available yet", Toast.LENGTH_LONG).show()
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_save),
                        contentDescription = null
                    )
                }
            }
        }
        Box {
            AsyncImage(
                contentDescription = apod.title,
                model = imageRequest(context, apod.url),
                imageLoader = imageLoader(context),
                placeholder = painterResource(id = R.drawable.ic_img_placeholder),
                contentScale = Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
                    .padding(8.dp)
            )
            Copyright(copyright = apod.copyright, modifier = Modifier.align(BottomEnd))
        }
        Text(
            text = apod.title,
            style = typography.titleLarge,
        )
        HorizontalDivider(modifier.padding(vertical = 4.dp))
        Text(
            text = apod.explanation,
            textAlign = Justify,
            letterSpacing = .3.sp,
            modifier = modifier.padding(8.dp)
        )
    }
}

@Composable
fun AstronomicPictureCard(
    apod: AstronomicPicture,
    modifier: Modifier = Modifier
) {
    val context: Context = LocalContext.current

    ElevatedCard(
        modifier = modifier,
        elevation = elevatedCardElevation(
            defaultElevation = 8.dp,
            pressedElevation = (-2).dp,
            focusedElevation = 4.dp,
            hoveredElevation = 4.dp
        )
    ) {
        Column(
            modifier = modifier,
            horizontalAlignment = CenterHorizontally
        ) {
            AsyncImage(
                contentDescription = apod.title,
                model = imageRequest(context, apod.url),
                imageLoader = imageLoader(context),
                placeholder = painterResource(id = R.drawable.ic_img_placeholder),
                contentScale = Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
            )
            Text(
                apod.title,
                style = typography.titleLarge,
                textAlign = Center,
                modifier = modifier.padding(8.dp)
            )
            Row(
                horizontalArrangement = SpaceBetween,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(8.dp),
            ) {
                Text(text = dateFormat(apod.date))
                Copyright(copyright = apod.copyright)
            }
        }
    }
}

@Composable
@NonRestartableComposable
fun Copyright(
    copyright: String,
    modifier: Modifier = Modifier,
    color: Color = LocalContentColor.current
) {
    if (copyright.isNotBlank()) {
        Text(text = "© $copyright", modifier, color)
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorScreen(
    uiState: Error,
    modifier: Modifier = Modifier,
    @DrawableRes drawable: Int = R.drawable.ic_offline,
    onRetryClick: () -> Unit = {}
) {
    Column(
        modifier.padding(12.dp),
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = drawable),
            contentDescription = null,
            modifier = Modifier.size(80.dp)
        )
        Spacer(Modifier.padding(4.dp))
        Text(
            text = uiState.message,
            style = typography.titleLarge,
            textAlign = Center
        )
        Spacer(Modifier.padding(4.dp))
        Button(onClick = onRetryClick) {
            Text(stringResource(R.string.retry))
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun AstronomicPicturePreview() {
    val mock = AstronomicPicture(
        date = "2024-09-11",
        explanation = "A natural border between Slovakia and Poland is the Tatra Mountains. A prominent destination for astrophotographers, the Tatras are the highest mountain range in the Carpathians. In the featured image taken in May, one can see the center of our Milky Way galaxy with two of its famous stellar nurseries, the Lagoon and Omega Nebula, just over the top of the Tatras. Stellar nurseries are full of ionized hydrogen, a fundamental component for the formation of Earth-abundant water. As a fundamental ingredient in all known forms of life, water is a crucial element in the Universe. Such water can be seen in the foreground in the form of the Bialka River.   Portal Universe: Random APOD Generator",
        copyright = "Bartolomeu Hangalo",
        hdUrl = "https://apod.nasa.gov/apod/image/2409/NightTatra_Rosadzinski_5028.jpg",
        mediaType = "image",
        serviceName = "v1",
        title = "A Night Sky over the Tatra Mountains",
        url = "https://apod.nasa.gov/apod/image/2409/NightTatra_Rosadzinski_960.jpg"
    )
    AstronomicPictureView(mock)
}

@Preview(showBackground = true)
@Composable
private fun AstronomicPictureCardPreview() {
    val mock = AstronomicPicture(
        date = "2024-09-11",
        explanation = "A natural border between Slovakia and Poland is the Tatra Mountains. A prominent destination for astrophotographers, the Tatras are the highest mountain range in the Carpathians. In the featured image taken in May, one can see the center of our Milky Way galaxy with two of its famous stellar nurseries, the Lagoon and Omega Nebula, just over the top of the Tatras. Stellar nurseries are full of ionized hydrogen, a fundamental component for the formation of Earth-abundant water. As a fundamental ingredient in all known forms of life, water is a crucial element in the Universe. Such water can be seen in the foreground in the form of the Bialka River.   Portal Universe: Random APOD Generator",
        copyright = "Bartolomeu Hangalo",
        hdUrl = "https://apod.nasa.gov/apod/image/2409/NightTatra_Rosadzinski_5028.jpg",
        mediaType = "image",
        serviceName = "v1",
        title = "A Night Sky over the Tatra Mountains",
        url = "https://apod.nasa.gov/apod/image/2409/NightTatra_Rosadzinski_960.jpg"
    )
    AstronomicPictureCard(mock)
}

@Preview(showBackground = true)
@Composable
private fun LoadingScreenPreview() {
    LoadingScreen(Modifier.fillMaxSize())
}

@Preview(showBackground = true)
@Composable
private fun ErrorScreenPreview() {
    ErrorScreen(uiState = Error("Connection error"), Modifier.fillMaxSize())
}
