package com.example.internet.AnalyzingXML;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.internet.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/*
* 以Pull方式解析XML格式的数据
* */
//从Android9.0 系统开始，应用程序默认只允许使用HTTPS类型的网络请求，HTTP类型的网络请求因为有安全隐患默认不再被支持，
// 而我们搭建的Apache 服务器现在使用的就是HTTP。那么为了能让程序使用HTTP,需要在res/raw文件夹下创建一个允许明文访问的文件，
// 并在清单文件<application>中添加 android:networkSecurityConfig="@xml/network_config"
public class PullXMLActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView responseText;
    String id = "";
    String name = "";
    String version = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_xmlactivity);

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
                        // 指定访问的服务器地址是计算机本机
                        .url("http://172.20.10.3/get_data.xml")
                        .build();
                //来发送请求并获取服务器返回的数据
                Response response = client.newCall(request).execute();
                //得到返回的具体内容
                String result = response.body().string();
                Log.d("boge",result);
                if (result != null){
                    parseXMLWithPull(result);
                    showResponse(result);
                    Log.d("boge","test");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }).start();
    }
    private void parseXMLWithPull(String xmlData) {
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            //将服务器返回的XML数据设置进去，之后就可以开始解析了
            xmlPullParser.setInput(new StringReader(xmlData));
            //解析XML数据，得到当前的解析事件
            int eventType = xmlPullParser.getEventType();
            Log.d("boge", "eventType:"+eventType);
            //while循环中不断地进行解析，如果当前的解析事件不等于XmlPullParser.END_DOCUMENT，
            // 说明解析工作还没完成，调用next()方法后可以获取下一个解析事件。
            while (eventType != XmlPullParser.END_DOCUMENT){
                //通过getName()方法得到了当前节点的名字
                String nodeName = xmlPullParser.getName();
                Log.d("boge","nodeName"+nodeName);
                switch (eventType){
                    // 开始解析某个节点
                    case XmlPullParser.START_TAG:
                        switch (nodeName){
                            //发现节点名等于id、name 或version ，就调用nextText()方法来获取节点内具体的内容
                            case "id":
                                id = xmlPullParser.nextText();
                                break;
                            case "name":
                                name = xmlPullParser.nextText();
                                break;
                            case "version":
                                version = xmlPullParser.nextText();
                                break;
                        }
                        break;
                    // 每当解析完一个app节点，就将获取到的内容打印出来
                    case XmlPullParser.END_TAG:
                        if ("app" == nodeName){
                            Log.d("boge","id is " + id);
                            Log.d("boge","name is " + name);
                            Log.d("boge","version is " + version);
                        }
                        break;
                }
                eventType = xmlPullParser.next();
                Log.d("boge","eventType="+eventType);

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