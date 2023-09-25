package com.example.video;

import android.app.Dialog;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

/*
* 视频弹窗，带有控制条（未完善）
* */
public class VideoDialogControl2Activity extends AppCompatActivity {

    private VideoView videoView;
    private MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_dialog_control2);

        videoView = findViewById(R.id.videoView);
        mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);

        Button playButton = findViewById(R.id.btn_play);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showVideoDialog();
            }
        });
    }

    private void showVideoDialog() {
        Dialog videoDialog = new Dialog(this);
        videoDialog.setContentView(R.layout.dialog_video_player);

        VideoView videoViewDialog = videoDialog.findViewById(R.id.videoView);
        videoViewDialog.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.one_piece)); // 替换为您的视频文件URI
        videoViewDialog.setMediaController(mediaController);
        mediaController.setMediaPlayer(videoViewDialog);
        videoViewDialog.requestFocus();
        videoViewDialog.start();

        videoDialog.show();
    }
}
