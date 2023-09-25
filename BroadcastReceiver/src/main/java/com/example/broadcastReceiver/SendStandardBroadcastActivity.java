package com.example.broadcastReceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

//发送自定义广播: 发送标准广播
/*注意：在Android8.0 系统之后，静态注册的BroadcastReceiver 是无法接收隐式广播的，
而默认情况下我们发出的自定义广播恰恰都是隐式广播。因此这里一定要调用setPackage()方法，指定这条广播是
发送给哪个应用程序的，从而让它变成一条显式广播，否则静态注册的BroadcastReceiver 将无法接收到这条广播*/
public class SendStandardBroadcastActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_standard_broadcast);

        findViewById(R.id.button).setOnClickListener(v -> {
            Intent intent = new Intent("com.example.broadcasttest.MY_BROADCAST");
            //传入当前应用程序的包名
            //getPackageName()的语法糖写法，用于获取当前应用程序的包名
            intent.setPackage(getPackageName());
            sendBroadcast(intent);
        });
    }
}