package com.example.video;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.VideoView;

/*
* 视频播放
* */
public class PlayVideoActivity extends AppCompatActivity implements View.OnClickListener {

    //VideoView 只是帮我们做了一个很好的封装而已，它的背后仍然是使用MediaPlayer 对视频文件进行控制的
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);

        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.one_piece);
        videoView = findViewById(R.id.videoView);
        videoView.setVideoURI(uri);
        findViewById(R.id.play).setOnClickListener(this);
        findViewById(R.id.pause).setOnClickListener(this);
        findViewById(R.id.replay).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.play:
                if (!videoView.isPlaying()){
                    videoView.start();//开始播放
                }
                break;
            case R.id.pause:
                if (videoView.isPlaying()){
                    videoView.pause();//暂停播放
                }
                break;
            case R.id.replay:
                if (videoView.isPlaying()){
                    videoView.resume();//重新播放
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        videoView.suspend();
    }
}