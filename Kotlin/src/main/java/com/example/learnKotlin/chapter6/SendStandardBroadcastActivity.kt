package com.example.learnKotlin.chapter6

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.Kotlin.R
import kotlinx.android.synthetic.main.activity_send_standard_broadcast.*

//发送标准广播
class SendStandardBroadcastActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_standard_broadcast)

        button.setOnClickListener{
            val intent = Intent("com.example.broadcasttest.MY_BROADCAST")
            //一定要调用setPackage()方法，指定这条广播是发送给哪个应用程序的，从而让它变成一条显式广播
            intent.setPackage(packageName)
            sendBroadcast(intent)
        }
    }
}