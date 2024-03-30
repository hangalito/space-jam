package com.hangalo.spacejam.ui.screens.interval

import com.hangalo.spacejam.R
import com.hangalo.spacejam.ui.screens.navtigation.Navigation


object IntervalNavigation : Navigation {
    override val route: String
        get() = "interval"
    override val title: Int
        get() = R.string.app_name
}
