package com.example.internet.AnalyzingJSON;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.internet.R;
import com.example.internet.entity.App;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/*
* 使用GSON解析Json格式数据
* */
//GSON并没有被添加到Android 官方的API中，因此如果想要使用这个功能的话，就必须在项目中添加GSON库的依赖
public class GsonActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView responseText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gson);

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
                    parseJSONWithGSON(responseData);
                    showResponse(responseData);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }).start();
    }

    //解析json数据[{id="", name="", version=""},{id="", name="", version=""},{id="", name="", version=""}]
    private void parseJSONWithGSON(String jsonData) {
        Gson gson = new Gson();
        Type typeOf = new TypeToken<List<App>>() {}.getType();
        //// 调用Gson的 T fromJson(String, Type)将List集合的json串反序列化为List对象
        //[App{id="", name="", version=""}, App{id="", name="", version=""},App{id="", name="", version=""}]
        List<App> appList = gson.fromJson(jsonData, typeOf);
        for (App app : appList) {
            Log.d("boge","id is " + app.getId());
            Log.d("boge","name is " + app.getName());
            Log.d("boge","version is " + app.getVersion());
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