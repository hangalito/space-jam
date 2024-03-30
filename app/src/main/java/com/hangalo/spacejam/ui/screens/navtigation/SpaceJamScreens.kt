package com.hangalo.spacejam.ui.screens.navtigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hangalo.spacejam.ui.screens.home.HomeNavigation
import com.hangalo.spacejam.ui.screens.home.SpaceJamHome
import com.hangalo.spacejam.ui.screens.interval.IntervalNavigation
import com.hangalo.spacejam.ui.screens.interval.SpaceJamPictureListApp


@Composable
fun SpaceJamScreens(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(navController = navController, startDestination = HomeNavigation.route) {
        composable(HomeNavigation.route) {
            SpaceJamHome(navController)
        }
        composable(IntervalNavigation.route) {
            SpaceJamPictureListApp(navController)
        }
    }
}
