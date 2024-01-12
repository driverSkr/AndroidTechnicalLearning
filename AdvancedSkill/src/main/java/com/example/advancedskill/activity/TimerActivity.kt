package com.example.advancedskill.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.advancedskill.R
import com.example.advancedskill.databinding.ActivityTimerBinding

class TimerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTimerBinding

    private var isStarted = false // 是否开始计数
    private var mCount = 0 // 计数值
    private val mHandler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTimerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCount.setOnClickListener {
            if (it.id == R.id.btn_count) {
                if (!isStarted) { // 不在计数，则开始计数
                    binding.btnCount.text = "停止计数"
                    mHandler.post(mCounter)
                } else { // 已在计数，则停止计数
                    binding.btnCount.text = "开始计数"
                    mHandler.removeCallbacks(mCounter) // 立即取消计数任务
                }
                isStarted = !isStarted
            }
        }
    }

    // 定义一个计数任务
    private val mCounter = object : Runnable {
        @SuppressLint("SetTextI18n")
        override fun run() {
            mCount ++
            binding.tvResult.text = "当前计数值为：$mCount"
            mHandler.postDelayed(this, 1000)
        }
    }
}