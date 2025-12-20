package com.example.room1

import android.app.Application
import android.content.Context

class MyApp : Application() {
    var personalLogs: String = "personalLogs"

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }

    companion object {
        lateinit var appContext: Context
            private set
    }
}