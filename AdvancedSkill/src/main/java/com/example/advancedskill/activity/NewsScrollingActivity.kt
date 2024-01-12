package com.example.advancedskill.activity

import android.icu.text.SimpleDateFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import androidx.lifecycle.lifecycleScope
import com.example.advancedskill.R
import com.example.advancedskill.databinding.ActivityNewsScrollingBinding
import com.example.advancedskill.extension.setOnClickListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import kotlin.random.Random

/**
 * 新闻滚动
 */
class NewsScrollingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewsScrollingBinding

    private var isPlaying = false // 是否正在播放新闻
    private val mNewsArray = arrayListOf( "北斗导航系统正式开通，定位精度媲美GPS",
        "黑人之死引发美国各地反种族主义运动", "印度运营商禁止华为中兴反遭诺基亚催债",
        "贝鲁特发生大爆炸全球紧急救援黎巴嫩", "日本货轮触礁毛里求斯造成严重漏油污染")

    private val mHandler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            var desc = binding.tvMessage.text.toString()
            when (msg.what) {
                BEGIN -> { // 开始播放
                    desc = "$desc\n${getCurrentFormattedDateTime()} 开始播放新闻"
                }
                SCROLL -> { // 滚动播放
                    desc = "$desc\n${getCurrentFormattedDateTime()} ${msg.obj}"
                }
                END -> { // 结束播放
                    desc = "$desc\n${getCurrentFormattedDateTime()} 新闻播放结束"
                }
            }
            binding.tvMessage.text = desc
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsScrollingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setOnClickListener(binding.btnStart, binding.btnStop) {
            if (this.id == R.id.btn_start) { //开始播放新闻的按钮
                if (!isPlaying) { // 如果不在播放就开始播放
                    isPlaying = true
                    playNews()
                }
            } else if (this.id == R.id.btn_stop) { // 点击了结束播放新闻的按钮
                isPlaying = false
            }
        }
    }

    // 定义一个新闻播放的协程
    private fun playNews() = lifecycleScope.launch(Dispatchers.IO) {
        mHandler.sendEmptyMessage(BEGIN)// 向处理器发送播放开始的空消息
        while (isPlaying) { // 正在播放新闻
            delay(2000) // 睡眠两秒（2000毫秒）
            val message = Message.obtain() // 获得默认的消息对象
            message.what = SCROLL
            message.obj = mNewsArray[Random.nextInt(5)]
            mHandler.sendMessage(message)
        }
        mHandler.sendEmptyMessage(END)  // 向处理器发送播放结束的空消息
        isPlaying = false
    }

    // 获取当前时间
    fun getCurrentFormattedDateTime(): String {
        val sdf = SimpleDateFormat("yyyy年 M月d日 HH:mm:ss", Locale.getDefault())
        return sdf.format(Date())
    }

    companion object {
        // 0为开始，1为滚动，2为结束
        private const val BEGIN = 0
        private const val SCROLL = 1
        private const val END = 2
    }
}