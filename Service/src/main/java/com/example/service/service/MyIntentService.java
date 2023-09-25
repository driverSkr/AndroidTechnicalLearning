package com.example.service.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

//集开启线程和自动停止于一身
public class MyIntentService extends IntentService {

    //这里首先要求必须先调用父类的构造函数，并传入一个字符串，这个字符串可以随意指定，只在调试的时候有用
    public MyIntentService() {
        super("MyIntentService");
    }

    //这个方法中可以处理一些耗时的逻辑，而不用担心ANR的问题，因为这个方法已经是在子线程中运行的了
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        //打印当前线程的id
        Log.d("MyIntentService","Thread id is " + Thread.currentThread().getName());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyIntentService","onDestroy executed");
    }
}
