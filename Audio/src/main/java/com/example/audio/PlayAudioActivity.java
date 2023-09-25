package com.example.audio;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

/*
* 播放音频
* */
public class PlayAudioActivity extends AppCompatActivity implements View.OnClickListener {

    private MediaPlayer mediaPlayer = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_audio);

        try {
            //为MediaPlayer对象进行初始化操作
            initMediaPlayer();
        } catch (IOException e) {
            e.printStackTrace();
        }

        findViewById(R.id.play).setOnClickListener(this);
        findViewById(R.id.pause).setOnClickListener(this);
        findViewById(R.id.stop).setOnClickListener(this);
    }

    //为MediaPlayer对象进行初始化操作
    private void initMediaPlayer() throws IOException {
        //得到了一个AssetManager的实例，AssetManager可用于读取assets 目录下的任何资源
        AssetManager assetManager = getAssets();
        //将音频文件句柄打开
        AssetFileDescriptor fd = assetManager.openFd("a.mp3");
        mediaPlayer.setDataSource(fd.getFileDescriptor(),fd.getStartOffset(),fd.getLength());
        mediaPlayer.prepare();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
        mediaPlayer.release();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.play:
                if (!mediaPlayer.isPlaying()){
                    mediaPlayer.start();//开始播放
                }
                break;
            case R.id.pause:
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.pause();//暂停播放
                }
                break;
            case R.id.stop:
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.reset();//停止播放
                    try {
                        initMediaPlayer();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        }
    }
}