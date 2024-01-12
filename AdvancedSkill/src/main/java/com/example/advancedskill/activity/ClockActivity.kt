package com.example.advancedskill.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import com.example.advancedskill.databinding.ActivityClockBinding
import android.text.format.DateFormat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * 时钟
 */
class ClockActivity : AppCompatActivity() {

    private lateinit var binding: ActivityClockBinding

    private val timerHandler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            if (msg.what == UPDATE_TIME_WHAT) {
                val sysTime = System.currentTimeMillis()
                val sysTimeStr = DateFormat.format("yyyy M月d日 EE HH:mm:ss", sysTime)
                binding.tvTime.text = sysTimeStr
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClockBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch(Dispatchers.IO) {
            while (true) {
                val msg = Message.obtain()
                msg.what = UPDATE_TIME_WHAT
                timerHandler.sendMessage(msg)
                delay(1000)
            }
        }
    }

    companion object {
        private const val UPDATE_TIME_WHAT = 0
    }
}