package com.hangalo.spacejam.domain.container

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.hangalo.spacejam.SpaceJamApplication
import com.hangalo.spacejam.ui.SpaceJamViewModel


object ViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            SpaceJamViewModel(spaceJamApplication().container.networkRepository)
        }
    }
}

fun CreationExtras.spaceJamApplication(): SpaceJamApplication =
    (this[APPLICATION_KEY] as SpaceJamApplication)
