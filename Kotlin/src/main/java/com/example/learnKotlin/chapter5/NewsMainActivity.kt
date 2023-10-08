package com.example.learnKotlin.chapter5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.Kotlin.R

//新闻的主页面
class NewsMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_main)
    }
}