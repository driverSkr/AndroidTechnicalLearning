package com.example.learnKotlin.chapter6.broadcast

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

//静态注册实现开机启动(在AndroidManifest.xml中注册)
class BootCompleteReceiver : BroadcastReceiver() {

    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context,"Boot Complete",Toast.LENGTH_SHORT).show()
    }
}