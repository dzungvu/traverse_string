package com.example.test_string.bases

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.BaseContextWrappingDelegate

abstract class BaseActivity : AppCompatActivity() {
    private var baseContextWrappingDelegate: AppCompatDelegate? = null

    override fun getDelegate() =
        baseContextWrappingDelegate ?: BaseContextWrappingDelegate(super.getDelegate()).apply {
            baseContextWrappingDelegate = this
        }
}