package com.hangalo.spacejam.ui.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.twotone.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpSize
import com.hangalo.spacejam.R
import com.hangalo.spacejam.model.AstronomicPicture
import java.sql.Date


@Composable
fun AstronomicPicture.getDate(): String {
    val today = Date(System.currentTimeMillis()).toString()
    val split = today.split("-")
    val yesterday = "${split.first()}-${split[1]}-${split.last().toInt() - 1}"

    return when (date) {
        today -> stringResource(id = R.string.today)
        yesterday -> stringResource(id = R.string.yesterday)
        else -> date
    }
}


fun Icons.defaultIconSize(): DpSize = DpSize(
    this.Default.DateRange.defaultWidth,
    this.Default.DateRange.defaultHeight
)
