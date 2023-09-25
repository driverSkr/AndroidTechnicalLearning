package com.example.internet.utils;

import androidx.annotation.NonNull;

import com.example.internet.interfaces.HttpCallbackListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/*
* 自定义http连接工具：使用了回调参数，更加安全，方便
* 1.HttpCallbackListener自定义回调
* 2.okhttp3.Callback（okhttp自带的回调参数）
* */
public class HttpUtil {

/*使用HttpURLConnection发送http请求，并获得响应数据*/
    //传入一个http糊掉监听，当使用该方法时，便要实现监听器里的onFinish()、onError()方法
    //我们就可以在onFinish()方法里对响应数据进行处理了
    //如果出现了异常，就可以在onError()方法里对异常情况进行处理
    public static void sendHttpRequest(final String address, final HttpCallbackListener listener){
        new Thread((() -> {
            HttpURLConnection connection = null;
            try {
                StringBuilder response = new StringBuilder();
                URL url = new URL(address);
                connection = (HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(8000);
                connection.setReadTimeout(8000);
                InputStream input = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                String line;
                while ((line = reader.readLine()) != null){
                    response.append(line);
                }
                if (listener != null){
                    //回调onFinish()方法
                    listener.onFinish(response.toString());
                }
            }catch (Exception e){
                if (listener != null){
                    //回调onError()方法
                    listener.onError(e);
                }
            }finally {
                if (connection != null){
                    connection.disconnect();
                }
            }
        })).start();
    }
/*--------------------在调用sendHttpRequest()方法的时候就可以这样写：----------------*/
    /*HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {
        @Override
        public void onFinish(String response) {
            //在这里根据返回内容执行具体的逻辑
        }

        @Override
        public void onError(Exception e) {
            //在这里对异常情况进行处理
        }
    });*/

/*使用okhttp发送http请求，并获得响应数据*/
    //okhttp3.Callback参数，这个是OkHttp库中自带的回调接口，类似于我们刚才自己编写的HttpCallbackListener
    public static void sendOkHttpRequest(String address,okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(address)
                .build();
        //OkHttp在enqueue()方法的内部已经帮我们开好子线程了，然后会在子线程中执行HTTP请求，
        // 并将最终的请求结果回调到okhttp3.Callback当中
        client.newCall(request).enqueue(callback);
    }
/*--------------------在调用sendOkHttpRequest()方法的时候就可以这样写：----------------*/
    /*HttpUtil.sendOkHttpRequest(address, new Callback() {
        @Override
        public void onResponse(@NonNull Call call, @NonNull Response response) throws
        IOException {
            //得到服务器返回的具体内容
            String responseData = response.body().string();
        }

        @Override
        public void onFailure(@NonNull Call call, @NonNull IOException e) {
            //在这里对异常情况进行处理
        }
    });*/
}
