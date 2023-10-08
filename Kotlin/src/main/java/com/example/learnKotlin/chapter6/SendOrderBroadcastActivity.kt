package com.example.learnKotlin.chapter6

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.Kotlin.R
import kotlinx.android.synthetic.main.activity_send_order_broadcast.*

class SendOrderBroadcastActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_order_broadcast)

        button.setOnClickListener{
            val intent = Intent("com.example.broadcasttest.MY_BROADCAST")
            intent.setPackage(packageName)
            sendOrderedBroadcast(intent,null)
        }
    }
}