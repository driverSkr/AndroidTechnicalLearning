package com.example.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //知晓当前是在哪一个Activity
        Log.d("BaseActivity", javaClass.simpleName)
        //添加当前活动进集合
        ActivityCollector.addActivity(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        //移除当前活动出集合
        ActivityCollector.removeActivity(this)
    }
}