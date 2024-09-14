package com.hangalo.spacejam.ui.components

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.SpaceBetween
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.TopEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale.Companion.Crop
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.text.style.TextAlign.Companion.End
import androidx.compose.ui.text.style.TextAlign.Companion.Justify
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.hangalo.spacejam.R
import com.hangalo.spacejam.domain.AstronomicPicture

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
            Text(text = "${stringResource(id = R.string.date_label)} ${dateFormat(apod.date)}")
            IconButton(onClick = {
                onBookmarkClick(apod)
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_save),
                    contentDescription = null
                )
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
            Copyright(
                copyright = apod.copyright,
                modifier = Modifier
                    .align(BottomEnd)
                    .padding(8.dp)
            )
        }
        Text(text = apod.title, style = typography.titleLarge)
        HorizontalDivider(modifier.padding(vertical = 4.dp))
        Text(
            text = apod.explanation, textAlign = Justify,
            letterSpacing = .3.sp, modifier = modifier.padding(8.dp)
        )
    }
}

@Composable
fun AstronomicPictureCard(
    apod: AstronomicPicture,
    modifier: Modifier = Modifier,
    loadPicture: (ImageLoader, ImageRequest) -> Unit = { _: ImageLoader, _: ImageRequest -> }
) {
    val context: Context = LocalContext.current
    val imageLoader: ImageLoader = imageLoader(context)
    val imageRequest: ImageRequest = imageRequest(context, apod.url)

    ElevatedCard(
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier,
            horizontalAlignment = CenterHorizontally
        ) {
            Box {
                AsyncImage(
                    contentDescription = apod.title,
                    model = imageRequest,
                    imageLoader = imageLoader,
                    placeholder = painterResource(id = R.drawable.ic_img_placeholder),
                    contentScale = Crop,
                    modifier = Modifier
                        .aspectRatio(4f / 3f)
                        .clickable { loadPicture(imageLoader, imageRequest) }
                )
                Copyright(
                    copyright = apod.copyright,
                    modifier = Modifier
                        .align(TopEnd)
                        .padding(4.dp)
                )
            }
            Text(
                apod.title,
                style = typography.titleSmall,
                textAlign = Center,
                modifier = Modifier.padding(4.dp)
            )
            Text(text = dateFormat(apod.date), style = typography.labelSmall)
        }
    }
}

@Composable
@NonRestartableComposable
private fun Copyright(
    copyright: String,
    modifier: Modifier = Modifier,
    color: Color = LocalContentColor.current,
    style: TextStyle = typography.labelSmall
) {
    if (copyright.isNotBlank()) {
        Text(text = "© $copyright", modifier, color, style = style, textAlign = End)
    }
}

@Preview(showBackground = true)
@Composable
private fun AstronomicPicturePreview() {
    AstronomicPictureView(mockData)
}

@Preview(showBackground = true)
@Composable
private fun AstronomicPictureCardPreview() {
    AstronomicPictureCard(mockData)
}

private val mockData: AstronomicPicture = AstronomicPicture(
    date = "2024-09-11",
    explanation = "A natural border between Slovakia and Poland is the Tatra Mountains. A prominent destination for astrophotographers, the Tatras are the highest mountain range in the Carpathians. In the featured image taken in May, one can see the center of our Milky Way galaxy with two of its famous stellar nurseries, the Lagoon and Omega Nebula, just over the top of the Tatras. Stellar nurseries are full of ionized hydrogen, a fundamental component for the formation of Earth-abundant water. As a fundamental ingredient in all known forms of life, water is a crucial element in the Universe. Such water can be seen in the foreground in the form of the Bialka River.   Portal Universe: Random APOD Generator",
    copyright = "Bartolomeu Hangalo",
    hdUrl = "https://apod.nasa.gov/apod/image/2409/NightTatra_Rosadzinski_5028.jpg",
    mediaType = "image",
    serviceName = "v1",
    title = "A Night Sky over the Tatra Mountains",
    url = "https://apod.nasa.gov/apod/image/2409/NightTatra_Rosadzinski_960.jpg"
)
