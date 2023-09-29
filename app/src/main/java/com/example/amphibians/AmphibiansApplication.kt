package com.example.amphibians

import android.app.Application
import com.example.amphibians.data.AppContainer


class AmphibiansApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = AppContainer()
    }
}