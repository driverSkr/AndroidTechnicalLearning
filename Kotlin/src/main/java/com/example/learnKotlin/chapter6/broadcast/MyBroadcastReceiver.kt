package com.example.learnKotlin.chapter6.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class MyBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context,"received in MyBroadcastReceiver",Toast.LENGTH_SHORT).show()
        Log.d("boge","received in MyBroadcastReceiver")

        //表示将这条广播截断，后面的BroadcastReceiver 将无法再接收到这条广播
        abortBroadcast()
    }
}