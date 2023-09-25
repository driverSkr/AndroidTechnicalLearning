package com.example.service;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.TextView;

//使用Handler机制解析异步消息处理机制，完成在主线程更新UI的过程
public class HandlerAsyncActivity extends AppCompatActivity {

    private static final int UPDATE_TEXT = 1;
    //由于Handler 的构造函数中我们传入了Looper.getMainLooper()，所以此时handleMessage()方法中的代码也会在主线程中运行，于是我们在这里就可以安心地进行UI操作了
    private final Handler handler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            //在这里可以进行UI操作
            switch (msg.what){
                case UPDATE_TEXT:
                    textView.setText("Nice to meet you");
            }
        }
    };
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_async);

        textView = findViewById(R.id.textView);
        findViewById(R.id.changeTextBtn).setOnClickListener(v -> {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Message msg = Message.obtain();
                    msg.what = UPDATE_TEXT;
                    handler.sendMessage(msg);//将Message对象发送出去
                }
            }).start();
        });
    }
}