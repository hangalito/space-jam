package com.hangalo.spacejam.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hangalo.spacejam.R
import com.hangalo.spacejam.ui.screens.UiState.Error

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
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
        horizontalAlignment = Alignment.CenterHorizontally,
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
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.padding(4.dp))
        Button(onClick = onRetryClick) {
            Text(stringResource(R.string.retry))
        }
    }
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
