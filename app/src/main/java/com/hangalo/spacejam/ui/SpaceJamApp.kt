package com.hangalo.spacejam.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.hangalo.spacejam.ui.screens.SpaceJamScreens


/**
 * The main composable for the app.
 */
@Composable
fun SpaceJamApp() {
    val navHostController = rememberNavController()
    SpaceJamScreens(navHostController)
}
