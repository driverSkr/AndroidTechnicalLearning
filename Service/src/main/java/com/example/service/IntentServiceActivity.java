package com.example.service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.service.service.MyIntentService;

//使用IntentService:集开启线程和自动停止于一身
/*Service中的代码都是默认运行在主线程当中的，如果直接在Service 里处理一些耗时的逻辑，就很容易出现ANR（Application Not Responding）的情况
* 所以这个时候就需要用到Android 多线程编程的技术了，我们应该在Service的每个具体的方法里开启一个子线程，然后在这里处理那些耗时的逻辑*/
/*但是，这种Service 一旦启动，就会一直处于运行状态，必须调用stopService()或stopSelf()方法，或者被系统回收，Service 才会停止*/
public class IntentServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_service);

        Intent intent = new Intent(this, MyIntentService.class);
        findViewById(R.id.startIntentServiceBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //打印主线程的id
                Log.d("MyIntentService","IntentServiceActivity:Thread id is " + Thread.currentThread().getName());
                startService(intent);
            }
        });
    }
}