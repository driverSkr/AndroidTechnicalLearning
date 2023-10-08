package com.example.learnKotlin.chapter6

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.Kotlin.R

//广播动态注册监听时间变化(系统每隔一分钟就会发出一条android.intent.action.TIME_TICK的广播)
class TimeChangeActivity : AppCompatActivity() {

    lateinit var timeChangeReceiver: TimeChangeReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_change)

        val intentFilter = IntentFilter()
        //BroadcastReceiver想要监听什么广播，就在这里添加相应的action
        intentFilter.addAction("android.intent.action.TIME_TICK")
        timeChangeReceiver = TimeChangeReceiver()
        registerReceiver(timeChangeReceiver,intentFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(timeChangeReceiver)
    }

    inner class TimeChangeReceiver: BroadcastReceiver(){
        //每当系统时间发生变化时，onReceive()方法就会得到执行
        override fun onReceive(context: Context?, intent: Intent?) {
            Toast.makeText(context,"Time has changed",Toast.LENGTH_SHORT).show()
        }
    }
}