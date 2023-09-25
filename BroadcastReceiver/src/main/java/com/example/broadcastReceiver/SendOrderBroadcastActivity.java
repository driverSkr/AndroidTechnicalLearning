package com.example.broadcastReceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

//发送自定义广播: 发送有序广播
public class SendOrderBroadcastActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_order_broadcast);

        findViewById(R.id.button).setOnClickListener(v -> {
            Intent intent = new Intent("com.example.broadcasttest.MY_BROADCAST");
            //传入当前应用程序的包名
            //getPackageName()的语法糖写法，用于获取当前应用程序的包名
            intent.setPackage(getPackageName());
            sendOrderedBroadcast(intent,null);
        });
    }
}