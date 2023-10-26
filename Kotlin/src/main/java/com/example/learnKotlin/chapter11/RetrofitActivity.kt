package com.example.learnKotlin.chapter11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.Kotlin.R
import com.example.learnKotlin.chapter11.dao.PushService
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_retrofit.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)

        getPushMsg.setOnClickListener {
            val retrofit = Retrofit.Builder()
                .baseUrl("http://baobab.kaiyanapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val pushService = retrofit.create(PushService::class.java)
            val message = pushService.getPushMessage("api/v3/messages")
            Log.d("boge",message.toString())
        }
    }
}