package com.example.broadcastReceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Toast;

//动态注册广播接受者
public class ActiveRegisterBroadcastActivity extends AppCompatActivity {

    private TimeChangeReceiver timeChangeReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_register_broadcast);

        IntentFilter intentFilter = new IntentFilter();
        //当系统时间发生变化时，系统发出的正是一条值为android.intent.action.TIME_TICK的广播
        //也就是说我们的BroadcastReceiver想要监听什么广播，就在这里添加相应的action
        intentFilter.addAction("android.intent.action.TIME_TICK");
        timeChangeReceiver = new TimeChangeReceiver();
        //进行注册
        registerReceiver(timeChangeReceiver,intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //动态注册的BroadcastReceiver一定要取消注册才行
        unregisterReceiver(timeChangeReceiver);
    }

    //每当系统时间发生变化时，onReceive()方法就会得到执行
    private class TimeChangeReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "Time has changed", Toast.LENGTH_SHORT).show();
        }
    }
}