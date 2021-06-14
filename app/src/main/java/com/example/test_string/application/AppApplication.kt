package com.example.test_string.application

import android.app.Application
import com.dzungvu.restring.core.RestringApp

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        RestringApp.initialize(this)
        val data = mutableMapOf<String, Map<String, String>>()
        val en = mutableMapOf<String, Map<String, String>>(
            Pair(
                "en",
                mutableMapOf(Pair("hello", "xin chao"))
            )
        )
        data.putAll(en)
        RestringApp.getInstance().injectData(data)
    }
}