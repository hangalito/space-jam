package com.hangalo.spacejam

import android.app.Application
import com.hangalo.spacejam.domain.container.AppContainer
import com.hangalo.spacejam.domain.container.DefaultAppContainer

class SpaceJamApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this)
    }
}
