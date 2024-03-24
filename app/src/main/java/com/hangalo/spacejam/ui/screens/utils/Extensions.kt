package com.hangalo.spacejam.ui.screens.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.hangalo.spacejam.R
import com.hangalo.spacejam.data.local.SavedRepository
import com.hangalo.spacejam.model.AstronomicPicture
import kotlinx.coroutines.flow.map
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


suspend inline fun AstronomicPicture.isSaved(savedRepository: SavedRepository): Boolean {
    var savedItems: List<AstronomicPicture> = listOf()
    savedRepository.getAllStream().map { savedItems = it }
    return savedItems.contains(this)
}
