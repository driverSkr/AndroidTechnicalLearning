package com.example.learnKotlin.chapter5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.Kotlin.R

class SimpleNewsAppActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_news_app)
    }
}