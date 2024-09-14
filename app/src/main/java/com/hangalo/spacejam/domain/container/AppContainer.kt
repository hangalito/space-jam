package com.hangalo.spacejam.domain.container

import com.hangalo.spacejam.domain.data.local.LocalRepository
import com.hangalo.spacejam.domain.data.remote.RemoteRepository

interface AppContainer {
    val remoteRepository: RemoteRepository
    val localRepository: LocalRepository
}
