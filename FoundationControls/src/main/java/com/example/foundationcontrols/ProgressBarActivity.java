package com.example.foundationcontrols;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

/*
* 进度条ProgressBar
* */
public class ProgressBarActivity extends AppCompatActivity implements View.OnClickListener {

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);

        progressBar = findViewById(R.id.progressBar);
        findViewById(R.id.visible).setOnClickListener(this);
        findViewById(R.id.progress).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //进度条的可见性
            case R.id.visible:
                if (progressBar.getVisibility() == View.VISIBLE){
                    progressBar.setVisibility(View.GONE);
                }else {
                    progressBar.setVisibility(View.VISIBLE);
                }
                break;
            //进度条的进度
            case R.id.progress:
                progressBar.setProgress(progressBar.getProgress()+10);
                break;
        }
    }
}