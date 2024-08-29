package com.hangalo.spacejam.ui.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpSize
import com.hangalo.spacejam.R
import com.hangalo.spacejam.model.AstronomicPicture
import java.time.LocalDate


/**
 * Get the [String] value for [AstronomicPicture]. <br>
 * @return "Today", "Yesterday", or the actual date.
 */
@Composable
fun AstronomicPicture.getDate(): String {
    val today = LocalDate.now()
    val yesterday = today.minusDays(1)

    return when (date) {
        "$today" -> stringResource(id = R.string.today)
        "$yesterday" -> stringResource(id = R.string.yesterday)
        else -> date
    }
}


fun Icons.defaultIconSize(): DpSize = DpSize(
    this.Default.DateRange.defaultWidth,
    this.Default.DateRange.defaultHeight
)

