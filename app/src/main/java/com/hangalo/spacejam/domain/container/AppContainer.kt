package com.hangalo.spacejam.domain.container

import com.hangalo.spacejam.domain.data.local.SavedRepository
import com.hangalo.spacejam.domain.data.remote.apod.APODRepository


interface AppContainer {
    val networkRepository: APODRepository
    val savedRepository: SavedRepository
}
