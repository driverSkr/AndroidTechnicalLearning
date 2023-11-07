package com.example.advancedskill.test;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.example.advancedskill.R;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        setCustomTaskDescription();
    }

    private void setCustomTaskDescription() {
        ActivityManager.TaskDescription taskDescription = new ActivityManager.TaskDescription("FirstActivity",
                BitmapFactory.decodeResource(getResources(), R.drawable.hupu));
        setTaskDescription(taskDescription);
    }
}