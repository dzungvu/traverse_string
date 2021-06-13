package com.example.test_string.application

import android.app.Application
import com.dzungvu.restring.core.RestringApp

class AppApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        RestringApp.initialize(this)
    }
}