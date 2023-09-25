package com.example.internet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/*
* 使用OkHttp获取网络请求
* */
public class OkHttpActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView responseText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http);

        responseText = findViewById(R.id.responseText);
        findViewById(R.id.sendRequestBtn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        sendRequestWithOkHttp();
    }

    private void sendRequestWithOkHttp() {
        new Thread(() -> {
            try {
                //获取客户端实例
                OkHttpClient client = new OkHttpClient();
                //创建请求对象实例
                Request request = new Request.Builder()
                        .url("https://www.baidu.com")
                        .build();
                //来发送请求并获取服务器返回的数据
                Response response = client.newCall(request).execute();
                //得到返回的具体内容
                String result = response.body().string();
                if (result != null){
                    showResponse(result);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }).start();
    }

    private void showResponse(String response) {
        //对异步消息处理机制进行了一层封装
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                responseText.setText(response);
            }
        });
    }
}