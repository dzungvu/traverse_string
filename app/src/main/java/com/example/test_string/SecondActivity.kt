package com.example.test_string

import android.os.Bundle
import com.example.test_string.bases.BaseActivity

class SecondActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.second_container, SecondFragment())
            .commit()
    }
}