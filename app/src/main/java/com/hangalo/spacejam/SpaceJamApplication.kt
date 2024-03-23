package com.hangalo.spacejam

import android.app.Application
import com.hangalo.spacejam.data.AppContainer
import com.hangalo.spacejam.data.DefaultAppContainer


class SpaceJamApplication : Application() {
    lateinit var  container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}
