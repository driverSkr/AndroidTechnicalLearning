package com.example.video;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

/*
* 视频播放，带有媒体控制器
* */
public class MediaControllerVideoActivity extends AppCompatActivity implements View.OnClickListener {


    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_controller_video);

        videoView = (VideoView) findViewById(R.id.videoView);
        findViewById(R.id.btn_play).setOnClickListener(this);
        findViewById(R.id.btn_pause).setOnClickListener(this);

        videoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.one_piece));
        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_play:
                videoView.start();
                break;
            case R.id.btn_pause:
                videoView.pause();
                break;
        }
    }
}