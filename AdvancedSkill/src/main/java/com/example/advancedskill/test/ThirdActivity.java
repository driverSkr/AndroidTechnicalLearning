package com.example.advancedskill.test;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.example.advancedskill.R;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        setCustomTaskDescription();
    }

    private void setCustomTaskDescription() {
        ActivityManager.TaskDescription taskDescription = new ActivityManager.TaskDescription("ThirdActivity",
                BitmapFactory.decodeResource(getResources(), R.drawable.tenxun));
        setTaskDescription(taskDescription);
    }
}