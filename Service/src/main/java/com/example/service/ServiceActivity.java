package com.example.service;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.service.service.MyService;

//启停Service
public class ServiceActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        findViewById(R.id.startServiceBtn).setOnClickListener(this);
        findViewById(R.id.stopServiceBtn).setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")//用于告知编译器忽略特定的Lint错误或警告
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, MyService.class);
        switch (v.getId()){
            case R.id.startServiceBtn:
                startService(intent);//启动Service
                break;
            case R.id.stopServiceBtn:
                stopService(intent);//停止Service
                break;
        }
    }
}