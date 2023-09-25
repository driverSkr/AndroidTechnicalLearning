package com.example.componentstest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;

public class IpConfigActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ip_config);

        findViewById(R.id.btn_click).setOnClickListener(v -> {
            ipConfigSetting();
        });
    }

    private void ipConfigSetting(){
        String localIp = Settings.Global.getString(getContentResolver(),"settings_local_ip");
        boolean local_ip = Settings.Global.putString(getContentResolver(), "setings_local_ip", "192.168.1.100");
        String localMask = Settings.Global.getString(getContentResolver(),"settings_local_mask");
        String localGateway = Settings.Global.getString(getContentResolver(),"settings_local_gateway");
        Log.d("boge","localIp: "+localIp);
        Log.d("boge","localMask: "+localMask);
        Log.d("boge","localGateway: "+localGateway);
        if(localIp == null){
            localIp = "192.168.1.100";
        }
        if(localMask == null){
            localMask = "255.255.255.0";
        }
        if(localGateway == null){
            localGateway = "192.168.1.1";
        }
    }
}