package com.hangalo.spacejam.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.hangalo.spacejam.SpaceJamApplication
import com.hangalo.spacejam.ui.screens.HomeViewModel


object ViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(spaceJamApplication().container.repository)
        }
    }
}

fun CreationExtras.spaceJamApplication(): SpaceJamApplication =
    (this[APPLICATION_KEY] as SpaceJamApplication)
