package com.example.service.service;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.example.service.FrontDeskActivity;
import com.example.service.R;

@SuppressLint("UnspecifiedImmutableFlag")
public class FrontDeskService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("FrontDeskService","onCreate executed");
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("FrontDesk_Service", "前台Service通知", NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(channel);
        }
        Intent intent2 = new Intent(this, FrontDeskActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent2, 0);
        Notification notification = new NotificationCompat.Builder(this, "FrontDesk_Service")
                .setContentTitle("This is content title")
                .setContentText("this is content text")
                .setSmallIcon(R.drawable.small_icon)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.large_icon))
                .setContentIntent(pi)
                .build();
        //这就是我们学习的创建通知的方法.只不过这次在构建Notification对象后并没有使用NotificationManager 将通知显示出来，而是调用了startForeground()方法
        //调用startForeground()方法后就会让MyService变成一个前台Service ，并在系统状态栏显示出来
        startForeground(1,notification);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}