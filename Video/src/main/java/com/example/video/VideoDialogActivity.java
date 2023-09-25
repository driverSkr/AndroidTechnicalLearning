package com.example.video;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.VideoView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

/*
* 视频弹窗，带有关闭按钮
* */
public class VideoDialogActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_dialog);

        findViewById(R.id.btn_play).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        showVideoDialog();
    }

    // 创建并显示视频播放对话框
    private void showVideoDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_video_player, null);
        VideoView videoView = dialogView.findViewById(R.id.videoView);

        // 设置视频路径
        String videoPath = "android.resource://"+getPackageName()+"/"+R.raw.one_piece; // 替换为您的视频文件路径
        videoView.setVideoPath(videoPath);
        videoView.start();

        builder.setView(dialogView)
                .setPositiveButton("关闭", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 关闭对话框
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}