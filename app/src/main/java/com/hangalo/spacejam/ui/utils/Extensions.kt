package com.hangalo.spacejam.ui.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.twotone.DateRange
import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpSize
import com.hangalo.spacejam.R
import com.hangalo.spacejam.model.AstronomicPicture
import com.hangalo.spacejam.ui.screens.home.HomeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
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

fun MenuSheetActions.Companion.defaultActions(
    vModel: HomeViewModel,
    drawerState: DrawerState,
    coroutineScope: CoroutineScope,
    changeVisibility: () -> Unit,
): MenuSheetActions {
    return object : MenuSheetActions {
        override fun onHomeClick() {
            coroutineScope.launch { drawerState.close() }
            vModel.getTodayPicture()
        }

        override fun onYesterdayClick() {
            coroutineScope.launch { drawerState.close() }
            vModel.getYesterdayPicture()
        }

        override fun on2daysClick() {
            coroutineScope.launch { drawerState.close() }
            vModel.get2daysAgoPicture()
        }

        override fun onSelectDateClick() {
            coroutineScope.launch { drawerState.close() }
            changeVisibility()
        }

        override fun onSelectDateRangeClick() {
            // TODO: write code for onSelectDateRangeClick
        }

        override fun onSeeSavedClick() {
            // TODO: write code for onSeeSavedClick
        }

        override fun onUploadClick() {
            // TODO: write code for onUploadClick
        }

        override fun onSignInClick() {
            // TODO: write code for onSignInClick
        }

    }
}
