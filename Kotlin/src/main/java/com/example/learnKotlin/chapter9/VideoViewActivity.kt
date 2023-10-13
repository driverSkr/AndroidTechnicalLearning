package com.example.learnKotlin.chapter9

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.Kotlin.R
import kotlinx.android.synthetic.main.activity_video_view.*

class VideoViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_view)

        val uri = Uri.parse("android.resource://$packageName/${R.raw.abc}")
        videoView.setVideoURI(uri)
        play.setOnClickListener {
            if (!videoView.isPlaying){
                videoView.start() //开始播放
            }
        }

        pause.setOnClickListener {
            if (videoView.isPlaying){
                videoView.pause() // 暂停播放
            }
        }

        replay.setOnClickListener {
            if (videoView.isPlaying){
                videoView.resume() //重新播放
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        videoView.suspend()
    }
}