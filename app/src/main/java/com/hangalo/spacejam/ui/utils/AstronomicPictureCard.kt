package com.hangalo.spacejam.ui.utils

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.hangalo.spacejam.R
import com.hangalo.spacejam.model.AstronomicPicture
import com.hangalo.spacejam.ui.theme.SpaceJamTheme

@Composable
fun AstronomicPictureCard(
    astronomicPicture: AstronomicPicture,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column {
            Row {
                Text(
                    text = astronomicPicture.title,
                    style = typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .weight(9f)
                        .padding(8.dp)
                        .align(Alignment.CenterVertically)
                )
                IconButton(
                    onClick = { /*TODO: bookmark picture*/ },
                    modifier = Modifier
                        .weight(1.45f)
                        .padding(8.dp)
                        .align(Alignment.CenterVertically)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_save),
                        contentDescription = null
                    )
                }
            }
            Text(
                text = "${stringResource(R.string.date_label)} ${astronomicPicture.getDate()}",
                style = typography.labelLarge,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(horizontal = 8.dp)
            )
        }
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
        Text(
            text = astronomicPicture.explanation,
            style = typography.bodyMedium,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Justify,
            modifier = Modifier.padding(12.dp)
        )
    }

}

@Preview(showBackground = true)
@Composable
private fun PreviewAstronomicPictureCard() {
    val mockData = AstronomicPicture(
        date = "2024-03-23",
        explanation = "",
        hdUrl = "",
        mediaType = "",
        serviceName = "",
        title = "Ares 3 Landing Site: The Martian Revisited",
        url = ""
    )
    SpaceJamTheme {
        AstronomicPictureCard(mockData)
    }
}
