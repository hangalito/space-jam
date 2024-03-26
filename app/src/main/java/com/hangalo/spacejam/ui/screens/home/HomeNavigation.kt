package com.hangalo.spacejam.ui.screens.home

import com.hangalo.spacejam.R
import com.hangalo.spacejam.ui.screens.navtigation.Navigation


object HomeNavigation : Navigation {
    override val route: String
        get() = "home"
    override val title: Int
        get() = R.string.app_name
}
