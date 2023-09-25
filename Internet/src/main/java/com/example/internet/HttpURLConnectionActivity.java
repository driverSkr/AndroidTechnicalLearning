package com.example.internet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/*
* 使用HttpURLConnection访问网络
* */
public class HttpURLConnectionActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView responseText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_urlconnection);

        responseText = findViewById(R.id.responseText);
        findViewById(R.id.sendRequestBtn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        sendRequestWithHttpURLConnection();
    }

    private void sendRequestWithHttpURLConnection(){
        //开启线程发起网络请求
        new Thread(() -> {
            HttpURLConnection connection = null;
            try {
                StringBuilder response = new StringBuilder();
                URL url = new URL("https://www.baidu.com");
                //获取HttpURLConnection 的实例
                connection =(HttpURLConnection)url.openConnection();
                connection.setRequestMethod("GET");
                //设置连接超时、读取超时的毫秒数
                connection.setConnectTimeout(8000);
                connection.setReadTimeout(8000);
                //获取到服务器返回的输入流
                InputStream input = connection.getInputStream();
            /*下面对获取到的输入流进行读取*/
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                String line;
                while ((line = reader.readLine()) != null){
                    response.append(line);
                }
                showResponse(response.toString());
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                //HTTP连接关闭
                connection.disconnect();
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