package com.hangalo.spacejam.ui.screens

import androidx.annotation.StringRes
import com.hangalo.spacejam.R

enum class NavigationRoute(@StringRes val title: Int) {
    Home(R.string.app_name),
    Bookmarks(R.string.bookmarks),
}
