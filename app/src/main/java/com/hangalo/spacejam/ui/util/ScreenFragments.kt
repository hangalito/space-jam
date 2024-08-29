package com.hangalo.spacejam.ui.util

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.hangalo.spacejam.R
import com.hangalo.spacejam.ui.screens.DefaultUiState
import com.hangalo.spacejam.ui.screens.home.HomeUiState
import com.hangalo.spacejam.ui.screens.interval.IntervalUiState

/**
 * The home screen displaying the loading message.
 */
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

/**
 * The home screen displaying error message and button to retry.
 */
@Composable
fun ErrorScreen(
    state: DefaultUiState.Error,
    modifier: Modifier = Modifier,
    retryAction: () -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        when (state) {
            is HomeUiState.InvalidDate -> {
                Text(
                    text = stringResource(state.msg),
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center
                )
            }

            is IntervalUiState.IncorrectInterval -> {
                Text(
                    text = stringResource(state.msg),
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center
                )
            }

            else -> {
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
    }
}
