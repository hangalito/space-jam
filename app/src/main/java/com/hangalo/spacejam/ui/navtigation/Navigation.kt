package com.hangalo.spacejam.ui.navtigation

import androidx.annotation.StringRes


interface Navigation {

    val route: String

    @get:StringRes
    val title: Int
}
