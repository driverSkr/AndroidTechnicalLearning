package com.example.video;

import android.app.Dialog;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

/*
* 视频弹窗，带有进度条
* */
public class VideoDialogSeeBarActivity extends AppCompatActivity implements View.OnClickListener {

    private SeekBar seekBar;
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_dialog_see_bar);

        findViewById(R.id.btn_play).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        showVideoDialog(v);
    }

    public void showVideoDialog(View view) {
        Dialog videoDialog = new Dialog(this);
        videoDialog.setContentView(R.layout.dialog_video_player_seebar);

        videoView = videoDialog.findViewById(R.id.videoView);
        MediaController mediaController = new MediaController(this);
        seekBar = videoDialog.findViewById(R.id.seekBar);
        ImageButton pauseButton = videoDialog.findViewById(R.id.pauseButton);

        // 设置视频路径
        String videoPath = "android.resource://"+getPackageName()+"/"+R.raw.one_piece;// 替换为您的视频文件路径
        videoView.setVideoPath(videoPath);

        // 设置MediaController
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);

        // 设置暂停按钮的点击事件
        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (videoView.isPlaying()) {
                    videoView.pause();
                    pauseButton.setImageResource(android.R.drawable.ic_media_play);
                } else {
                    videoView.start();
                    pauseButton.setImageResource(android.R.drawable.ic_media_pause);
                }
            }
        });

        // 设置进度条的进度更新
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                seekBar.setMax(videoView.getDuration());
                seekBar.setProgress(0);
                updateSeekBar();
            }
        });

        videoDialog.show();
    }

    private void updateSeekBar() {
        seekBar.setProgress(videoView.getCurrentPosition());
        if (videoView.isPlaying()) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    updateSeekBar();
                }
            };
            Handler handler = new Handler();
            handler.postDelayed(runnable, 1000); // 每秒更新一次进度条
        }
    }

}