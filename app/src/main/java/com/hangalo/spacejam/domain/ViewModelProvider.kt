package com.hangalo.spacejam.domain

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.hangalo.spacejam.ui.screens.home.HomeViewModel
import com.hangalo.spacejam.ui.screens.interval.IntervalViewModel


object ViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(spaceJamApplication().container.repository)
        }
        initializer {
            IntervalViewModel(spaceJamApplication().container.repository)
        }
    }
}

fun CreationExtras.spaceJamApplication(): SpaceJamApplication =
    (this[APPLICATION_KEY] as SpaceJamApplication)
