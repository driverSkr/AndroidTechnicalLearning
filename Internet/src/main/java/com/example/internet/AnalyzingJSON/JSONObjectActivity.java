package com.example.internet.AnalyzingJSON;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.internet.R;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/*
* 使用官方提供的JSONObject解析JSON数据
* */
public class JSONObjectActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView responseText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jsonobject);

        responseText = findViewById(R.id.responseText);
        findViewById(R.id.sendRequestBtn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        sendRequestWithOkHttp();
    }

    //发送http请求，获得json数据
    private void sendRequestWithOkHttp() {
        new Thread(() -> {
            try {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("http://172.20.10.3/get_data.json")
                        .build();
                Response response = client.newCall(request).execute();
                String responseData = response.body().string();
                if (responseData != null){
                    parseJSONWithJSONObject(responseData);
                    showResponse(responseData);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }).start();
    }

    //解析json数据[{},{},{}]
    private void parseJSONWithJSONObject(String jsonData) {
        try {
            //获得jsonArray，以{}来分组
            JSONArray jsonArray = new JSONArray(jsonData);
            for (int i = 0; i < jsonArray.length(); i++) {
                //获得单个json数据{}
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String id = jsonObject.getString("id");
                String name = jsonObject.getString("name");
                String version = jsonObject.getString("version");
                Log.d("boge","id is "+id);
                Log.d("boge","name is "+name);
                Log.d("boge","version is "+version);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
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