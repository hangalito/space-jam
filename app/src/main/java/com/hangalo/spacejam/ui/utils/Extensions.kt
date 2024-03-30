package com.hangalo.spacejam.ui.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpSize
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.hangalo.spacejam.R
import com.hangalo.spacejam.model.AstronomicPicture
import com.hangalo.spacejam.ui.screens.home.HomeNavigation
import com.hangalo.spacejam.ui.screens.home.HomeViewModel
import com.hangalo.spacejam.ui.screens.interval.IntervalNavigation
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
    viewModel: ViewModel,
    drawerState: DrawerState,
    coroutineScope: CoroutineScope,
    navController: NavHostController,
    changeVisibility: () -> Unit,
): MenuSheetActions {
    return object : MenuSheetActions {

        private fun goHome() {
            navController.navigate(HomeNavigation.route) {
                popUpTo(HomeNavigation.route) {
                    saveState = true
                }
                launchSingleTop = true
            }
        }

        override fun onHomeClick() {
            goHome()
            coroutineScope.launch { drawerState.close() }
            (viewModel as HomeViewModel).getTodayPicture()
        }

        override fun onYesterdayClick() {
            goHome()
            coroutineScope.launch { drawerState.close() }
            (viewModel as HomeViewModel).getYesterdayPicture()
        }

        override fun on2daysClick() {
            goHome()
            coroutineScope.launch { drawerState.close() }
            (viewModel as HomeViewModel).get2daysAgoPicture()
        }

        override fun onSelectDateClick() {
            goHome()
            coroutineScope.launch { drawerState.close() }
            changeVisibility()
        }

        override fun onSelectDateRangeClick() {
            navController.navigate(IntervalNavigation.route)
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
