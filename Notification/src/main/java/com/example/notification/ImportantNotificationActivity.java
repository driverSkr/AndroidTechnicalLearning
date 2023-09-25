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
import android.os.Bundle;
import android.view.View;

public class ImportantNotificationActivity extends AppCompatActivity implements View.OnClickListener {

    private NotificationManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_important_notification);

        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationChannel channel = new NotificationChannel("important","Important",NotificationManager.IMPORTANCE_HIGH);
        manager.createNotificationChannel(channel);

        findViewById(R.id.sendNotice).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, MessageDisplayActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);
        Notification notification =new NotificationCompat.Builder(this,"important")
                .setContentTitle("黄子佼性骚扰案已立案")
                /*显示长文本用下方法*/
                .setStyle(new NotificationCompat.BigTextStyle().bigText("据台媒，艺人黄子佼被曝对未成年少女强拍裸照、猥亵等，台北地检署近日已将黄子佼列为被告。"))
                .setSmallIcon(R.drawable.toutiao)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                .setContentIntent(pendingIntent)//当点击通知时，触发PendingIntent，跳转
                .setAutoCancel(true)//当点击这个通知的时候，通知会自动取消
                .build();
        manager.notify(1,notification);
    }
}