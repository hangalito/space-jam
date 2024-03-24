package com.hangalo.spacejam.data

import com.hangalo.spacejam.data.local.SavedRepository
import com.hangalo.spacejam.data.remote.apod.APODRepository


interface AppContainer {
    val repository: APODRepository
    val savedRepository: SavedRepository
}
