package com.example.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
/*
*   创建取消通知
* */
public class NotificationActivity extends AppCompatActivity implements View.OnClickListener {

    private NotificationManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        //获取通知管理器管理器
        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //获取通知渠道
        //Android系统的每一个版本都会对通知功能进行或多或少的修改，API不稳定的问题在通知上凸显得尤其严重，比
        //方说通知渠道功能在Android 8.0 系统之前就是没有的。
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //AndroidX库中提供了一个NotificationCompat类，使用这个类的构造器创建
            //Notification对象，就可以保证我们的程序在所有Android 系统版本上都能正常工作了
            /*通知渠道:渠道ID、渠道名称以及重要等级这3个参数*/
            //创建通知渠道的代码只在第一次执行的时候才会创建，当下次再执行创建代码时，系统会检测到该通知渠道已经存在了，因此不会重复创建，也并不会影响运行效率
            NotificationChannel channel = new NotificationChannel("normal", "Normal", NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(channel);
            //取消通知，和.setAutoCancel(true)作用一样(具体放哪个位置百度)
            //manager.cancel(1);

        /*建立一个重要通知（会直接弹出）*/
            NotificationChannel channel1 = new NotificationChannel("important","Important",NotificationManager.IMPORTANCE_HIGH);
            manager.createNotificationChannel(channel1);
        }
        findViewById(R.id.sendNotice).setOnClickListener(this);
        findViewById(R.id.sendImportantNotice).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            /*发送一条普通通知*/
            case R.id.sendNotice:
                Intent intent = new Intent(this, MessageDisplayActivity.class);
                //延时，待触发意图。点击通知可以跳转
                PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);

                Notification notification =new NotificationCompat.Builder(this,"normal")
                        .setContentTitle("黄子佼性骚扰案已立案")
                        //.setContentText("据台媒，艺人黄子佼被曝对未成年少女强拍裸照、猥亵等，台北地检署近日已将黄子佼列为被告。")
                        /*显示长文本用下方法*/
                        .setStyle(new NotificationCompat.BigTextStyle().bigText("据台媒，艺人黄子佼被曝对未成年少女强拍裸照、猥亵等，台北地检署近日已将黄子佼列为被告。"))
                        .setSmallIcon(R.drawable.toutiao)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                        .setContentIntent(pendingIntent)//当点击通知时，触发PendingIntent，跳转
                        .setAutoCancel(true)//当点击这个通知的时候，通知会自动取消
                        .build();
                manager.notify(1,notification);
                break;
            /*发送一条重要通知*/
            case R.id.sendImportantNotice:
                Intent intent1 = new Intent(this, MessageDisplayActivity.class);
                //延时，待触发意图。点击通知可以跳转
                PendingIntent pendingIntent1 = PendingIntent.getActivity(this,0,intent1,0);

                Notification notification1 =new NotificationCompat.Builder(this,"important")
                        .setContentTitle("黄子佼性骚扰案已立案")
                        //.setContentText("据台媒，艺人黄子佼被曝对未成年少女强拍裸照、猥亵等，台北地检署近日已将黄子佼列为被告。")
                        /*显示长文本用下方法*/
                        .setStyle(new NotificationCompat.BigTextStyle().bigText("据台媒，艺人黄子佼被曝对未成年少女强拍裸照、猥亵等，台北地检署近日已将黄子佼列为被告。"))
                        .setSmallIcon(R.drawable.toutiao)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                        .setContentIntent(pendingIntent1)//当点击通知时，触发PendingIntent，跳转
                        .setAutoCancel(true)//当点击这个通知的时候，通知会自动取消
                        .build();
                manager.notify(2,notification1);
                break;
        }
    }
}