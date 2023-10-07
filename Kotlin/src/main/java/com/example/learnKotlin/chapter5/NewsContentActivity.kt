package com.example.learnKotlin.chapter5

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.Kotlin.R
import com.example.learnKotlin.chapter5.fragment.NewsContentFragment

class NewsContentActivity : AppCompatActivity() {

    companion object{
        fun actionStart(context: Context,title: String,content: String){
            val intent = Intent(context,NewsContentActivity::class.java).apply {
                putExtra("news_title", title)
                putExtra("news_content", content)
            }
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_content)

        val title = intent.getShortExtra("news_title")
    }
}