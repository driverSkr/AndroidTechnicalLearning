package com.example.service;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.service.service.FrontDeskService;
import com.example.service.service.MyService;

//构建前台service
/*从Android 8.0 系统开始，只有当应用保持在前台可见状态的情况下，Service才能保证稳定运行，
一旦应用进入后台之后，Service 随时都有可能被系统回收。而如果你希望Service 能够一直保持运行状态，就可以考虑使用前台Service 。*/
//前台Service 和普通Service 最大的区别就在于，它一直会有一个正在运行的图标在系统的状态栏显示，下拉状态栏后可以看到更加详细的信息，非常类似于通知的效果
/*由于状态栏中一直有一个正在运行的图标，相当于我们的应用以另外一种形式保持在前台可见状态，所以系统不会倾向于回收前台Service 。
另外，用户也可以通过下拉状态栏清楚地知道当前什么应用正在运行，因此也不存在某些恶意应用长期在后台偷偷占用手机资源的情况*/
public class FrontDeskActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_desk);

        findViewById(R.id.startServiceBtn).setOnClickListener(this);
        findViewById(R.id.stopServiceBtn).setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")//用于告知编译器忽略特定的Lint错误或警告
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, FrontDeskService.class);
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