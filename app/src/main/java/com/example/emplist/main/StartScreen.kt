package com.example.emplist.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.example.emplist.MainActivity
import com.example.emplist.R

class StartScreen:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        setContentView(R.layout.splashscreen)
        val btn = findViewById<View>(R.id.navigationbtn)
        btn.setOnClickListener {
            startActivity(Intent(this@StartScreen, MainActivity::class.java))
        }
    }
}