package com.example.advancedskill.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

/**
 * 测试onBind()
 */
class BindService : Service() {

    private val myBinder = MyBinder()

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "$TAG：onCreate()")
    }

    override fun onBind(intent: Intent): IBinder = myBinder

    companion object {
        private val TAG = BindService::class.simpleName
        class MyBinder : Binder() {
            fun startBind() {
                Log.d(TAG, "你执行了startBind()")
            }
            fun endBind() {
                Log.d(TAG, "你结束了endBind()")
            }
        }
    }
}