package com.hangalo.spacejam.domain.container

import android.app.Application
import com.hangalo.spacejam.domain.data.DefaultAppContainer


class SpaceJamApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this)
    }
}
