package com.example.internet;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.internet.AnalyzingJSON.GsonActivity;
import com.example.internet.AnalyzingJSON.JSONObjectActivity;
import com.example.internet.AnalyzingXML.PullXMLActivity;
import com.example.internet.AnalyzingXML.SAXforXMLActivity;

@SuppressLint("NonConstantResourceId")
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_JsonObject).setOnClickListener(this);
        findViewById(R.id.btn_Gson).setOnClickListener(this);
        findViewById(R.id.btn_Pull_Xml).setOnClickListener(this);
        findViewById(R.id.btn_SAX_Xml).setOnClickListener(this);
        findViewById(R.id.btn_http_url_connection).setOnClickListener(this);
        findViewById(R.id.btn_okhttp).setOnClickListener(this);
        findViewById(R.id.btn_retrofit).setOnClickListener(this);
        findViewById(R.id.btn_webView).setOnClickListener(this);
        findViewById(R.id.btn_eyeOpening).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_JsonObject:
                startActivity(new Intent(this, JSONObjectActivity.class));
                break;
            case R.id.btn_Gson:
                startActivity(new Intent(this, GsonActivity.class));
                break;
            case R.id.btn_Pull_Xml:
                startActivity(new Intent(this, PullXMLActivity.class));
                break;
            case R.id.btn_SAX_Xml:
                startActivity(new Intent(this, SAXforXMLActivity.class));
                break;
            case R.id.btn_http_url_connection:
                startActivity(new Intent(this, HttpURLConnectionActivity.class));
                break;
            case R.id.btn_okhttp:
                startActivity(new Intent(this, OkHttpActivity.class));
                break;
            case R.id.btn_retrofit:
                startActivity(new Intent(this, RetrofitActivity.class));
                break;
            case R.id.btn_webView:
                startActivity(new Intent(this, WebViewActivity.class));
                break;
            case R.id.btn_eyeOpening:
                startActivity(new Intent(this,EyeOpeningActivity.class));
        }
    }
}