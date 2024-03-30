@file:Suppress("DEPRECATION")

package com.hangalo.spacejam.ui.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Absolute.SpaceBetween
            ) {
                Text(
                    text = "${stringResource(R.string.date_label)} ${astronomicPicture.getDate()}",
                    style = typography.labelLarge,
                    modifier = Modifier
                )
                astronomicPicture.copyright.takeIf { it.isNotEmpty() }.let {
                    Text(
                        text = "©\uFE0F ${it?.strip()}",
                        style = typography.labelSmall,
                        modifier = Modifier.alpha(.75f)
                    )
                }
            }
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


@Composable
fun AstronomicPictureChip(
    astronomicPicture: AstronomicPicture,
    modifier: Modifier = Modifier,
    onClick: (AstronomicPicture) -> Unit,
) {
    val shape = RoundedCornerShape(21.dp)
    Card(
        shape = shape,
        modifier = modifier,
        onClick = { onClick(astronomicPicture) }
    ) {
        Box {
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
                    .align(Alignment.Center)
            )
            Text(
                text = astronomicPicture.getDate(),
                style = typography.bodyLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = LocalContentColor.current,
                modifier = Modifier
                    .padding(4.dp)
                    .align(Alignment.BottomStart)
            )
        }
        Spacer(modifier = Modifier.padding(4.dp))
        Text(
            text = astronomicPicture.title,
            style = typography.titleSmall,
            maxLines = 2,
            fontWeight = FontWeight.SemiBold,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(4.dp)
        )
        Text(
            text = astronomicPicture.explanation,
            style = typography.bodyMedium,
            maxLines = 12,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Justify,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(4.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewAstronomicPictureCard() {
    val mockData = AstronomicPicture(
        date = "2024-03-23",
        explanation = "What does a supernova remnant sound like? Although sound is a compression wave in matter and does not carry into empty space, interpretive sound can help listeners appreciate and understand a visual image of a supernova remnant in a new way. Recently, the Jellyfish Nebula (IC 443) has been sonified quite creatively.  In the featured sound-enhanced video, when an imaginary line passes over a star, the sound of a drop falling into water is played, a sound particularly relevant to the nebula's aquatic namesake.  Additionally, when the descending line crosses gas that glows red, a low tone is played, while green sounds a middle tone, and blue produces a tone with a relatively high pitch. Light from the supernova that created the Jellyfish Nebula left approximately 35,000 years ago, when humanity was in the stone age.  The nebula will slowly disperse over the next million years, although the explosion also created a dense neutron star which will remain indefinitely.",
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


@Preview(showBackground = true)
@Composable
private fun AstronomicPictureChipPreview() {
    val mockData = AstronomicPicture(
        date = "2024-03-25",
        explanation = "What does a supernova remnant sound like? Although sound is a compression wave in matter and does not carry into empty space, interpretive sound can help listeners appreciate and understand a visual image of a supernova remnant in a new way. Recently, the Jellyfish Nebula (IC 443) has been sonified quite creatively.  In the featured sound-enhanced video, when an imaginary line passes over a star, the sound of a drop falling into water is played, a sound particularly relevant to the nebula's aquatic namesake.  Additionally, when the descending line crosses gas that glows red, a low tone is played, while green sounds a middle tone, and blue produces a tone with a relatively high pitch. Light from the supernova that created the Jellyfish Nebula left approximately 35,000 years ago, when humanity was in the stone age.  The nebula will slowly disperse over the next million years, although the explosion also created a dense neutron star which will remain indefinitely.",
        mediaType = "",
        serviceName = "",
        title = "Sonified: The Jellyfish Nebula Supernova Remnant",
        url = "https://youtube.com/embed/NqBfQeJqkfU?rel=0"
    )
    AstronomicPictureChip(mockData) {}

}
