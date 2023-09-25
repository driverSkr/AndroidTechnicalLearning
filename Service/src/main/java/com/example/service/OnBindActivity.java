package com.example.service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

import com.example.service.service.MyService;

//使用onBind()方法在Activity和Service进行通信
/*任何一个Service 在整个应用程序范围内都是通用的，即MyService不仅可以和OnBindActivity绑定，还可以和任何一个其他的Activity 进行绑定，
而且在绑定完成后，它们都可以获取相同的DownloadBinder实例*/
public class OnBindActivity extends AppCompatActivity {

    private MyService.DownloadBinder downloadBinder;
    private final ServiceConnection connection = new ServiceConnection() {
        //会在Activity与Service成功绑定的时候调用
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //有了这个实例，Activity 和Service 之间的关系就变得非常紧密
            downloadBinder = (MyService.DownloadBinder) service;
            downloadBinder.startDownload();
            downloadBinder.getProgress();
        }
        //只有在Service的创建进程崩溃或者被杀掉的时候才会调用，这个方法不太常用
        @Override
        public void onServiceDisconnected(ComponentName name) {}
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_bind);

        Intent intent = new Intent(this,MyService.class);

        findViewById(R.id.bindServiceBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //BIND_AUTO_CREATE表示在Activity和Service进行绑定后自动创建Service 。
                // 这会使得MyService中的onCreate()方法得到执行，但onStartCommand()方法不会执行
                bindService(intent,connection, Context.BIND_AUTO_CREATE);// 绑定Service
            }
        });
        findViewById(R.id.unbindServiceBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(connection);//解绑Service
            }
        });
    }
}