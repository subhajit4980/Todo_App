package com.example.mytodoapp.Application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TODOAPP : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}