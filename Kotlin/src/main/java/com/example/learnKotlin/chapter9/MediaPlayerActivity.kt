package com.example.learnKotlin.chapter9

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.Kotlin.R
import kotlinx.android.synthetic.main.activity_media_player.*

//播放音频
class MediaPlayerActivity : AppCompatActivity() {

    private val mediaPlayer = MediaPlayer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_media_player)

        initMediaPlayer()
        play.setOnClickListener {
            if (!mediaPlayer.isPlaying){
                mediaPlayer.start() // 开始播放
            }
        }
        pause.setOnClickListener {
            if (mediaPlayer.isPlaying){
                mediaPlayer.pause() // 暂停播放
            }
        }
        stop.setOnClickListener {
            if (mediaPlayer.isPlaying){
                //调用reset()方法将MediaPlayer 重置为刚刚创建的状态
                mediaPlayer.reset() // 停止播放
                initMediaPlayer()
            }
        }
    }

    private fun initMediaPlayer(){
        //AssetManager可用于读取assets 目录下的任何资源
        val assetManager = assets
        //openFd()方法将音频文件句柄打开
        val fd = assetManager.openFd("b.mp3")
        mediaPlayer.setDataSource(fd.fileDescriptor,fd.startOffset,fd.length)
        mediaPlayer.prepare()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
        mediaPlayer.release()
    }
}