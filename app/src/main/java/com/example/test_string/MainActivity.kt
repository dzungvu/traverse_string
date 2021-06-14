package com.example.test_string

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.dzungvu.restring.core.RestringApp
import com.example.test_string.bases.BaseActivity

class MainActivity : BaseActivity() {
    private val tag = "MainActivity"

    //todo: use view binding instead
//    private val tvContent: TextView by lazy { findViewById(R.id.tvContent) }
    private val btnChangeScreen: Button by lazy { findViewById(R.id.btnChangeScreen) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(tag, "onCreate")
        setContentView(R.layout.activity_main)
//        tvContent.text = getString(R.string.app_name)
        btnChangeScreen.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

        val data = mutableMapOf<String, Map<String, String>>()
        val en = mutableMapOf<String, Map<String, String>>(
            Pair(
                "en",
                mutableMapOf(Pair("hello", "xin chao"), Pair("app_name", "restring"))
            )
        )
        data.putAll(en)
        RestringApp.getInstance().injectData(data)
    }
}