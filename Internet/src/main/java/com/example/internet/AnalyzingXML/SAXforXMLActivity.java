package com.example.internet.AnalyzingXML;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.internet.R;
import com.example.internet.handler.ContentHandler;
import com.example.internet.interfaces.HttpCallbackListener;
import com.example.internet.utils.HttpUtil;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.SAXParserFactory;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/*
* SAX方式解析XML格式数据
* */
//从Android9.0 系统开始，应用程序默认只允许使用HTTPS类型的网络请求，HTTP类型的网络请求因为有安全隐患默认不再被支持，
// 而我们搭建的Apache 服务器现在使用的就是HTTP。那么为了能让程序使用HTTP,需要在res/raw文件夹下创建一个允许明文访问的文件，
// 并在清单文件<application>中添加 android:networkSecurityConfig="@xml/network_config"
public class SAXforXMLActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView responseText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saxfor_xmlactivity);

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
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        // 指定访问的服务器地址是计算机本机
                        .url("http://172.20.10.3/get_data.xml")
                        .build();
                Response response = client.newCall(request).execute();
                String responseData = response.body().string();
                if (responseData != null){
                    showResponse(responseData);
                    parseXMLWithSAX(responseData);
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }).start();
    }

    //解析XML数据
    private void parseXMLWithSAX(String xmlData) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            XMLReader xmlReader = factory.newSAXParser().getXMLReader();
            ContentHandler handler = new ContentHandler();
            // 将ContentHandler的实例设置到XMLReader中
            xmlReader.setContentHandler(handler);
            //开始执行解析
            xmlReader.parse(new InputSource(new StringReader(xmlData)));
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    private void showResponse(String response) {
        //对异步消息处理机制进行了一层封装
        runOnUiThread(() -> responseText.setText(response));
    }
}