package com.example.internet;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/*本程序使用到了网络功能，而访问网络是需要在清单文件声明权限的*/
public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        WebView webView = findViewById(R.id.webView);
        //getSettings()方法可以设置一些浏览器的属性
        //setJavaScriptEnabled()方法，让WebView支持JavaScript脚本
        webView.getSettings().setJavaScriptEnabled(true);
        //当需要从一个网页跳转到另一个网页时，我们希望目标网页仍然在当前WebV iew 中显示，而不是打开系统浏览器
        webView.setWebViewClient(new WebViewClient());
        //展示相应网页的内容
        webView.loadUrl("https://www.baidu.com");
    }
}