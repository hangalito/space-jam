package com.hangalo.spacejam.ui.util

import androidx.compose.material3.DrawerState
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.hangalo.spacejam.ui.screens.home.HomeNavigation
import com.hangalo.spacejam.ui.screens.home.HomeViewModel
import com.hangalo.spacejam.ui.screens.interval.IntervalNavigation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

interface MenuSheetActions {
    fun onHomeClick()
    fun onYesterdayClick()
    fun onSelectDateClick()
    fun onSelectDateRangeClick()
    fun onSeeSavedClick()
    fun onUploadClick()
    fun onSignInClick()

    companion object {
        fun defaultActions(
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

                override fun onSelectDateClick() {
                    goHome()
                    coroutineScope.launch { drawerState.close() }
                    changeVisibility()
                }

                override fun onSelectDateRangeClick() {
                    coroutineScope.launch { drawerState.close() }
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
    }
}
