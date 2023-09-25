package com.example.componentstest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;

import com.example.componentstest.model.WifiData;

import java.util.ArrayList;
import java.util.List;

public class WifiListActivity extends AppCompatActivity {

    private List<ScanResult> mWifiScanResult;
    private WifiManager mWifiManager;

    private final BroadcastReceiver wifiScanReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (WifiManager.SCAN_RESULTS_AVAILABLE_ACTION.equals(intent.getAction())){
                // 扫描结果已经可用，获取结果
                getWifiList();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi_list);

        mWifiManager = (WifiManager)this.getSystemService(Context.WIFI_SERVICE);

        findViewById(R.id.click).setOnClickListener(v -> {
            //注册广播接收器
            registerReceiver(wifiScanReceiver,new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
            //开始wifi扫描
            mWifiManager.startScan();
            // 等待一段时间，例如 5 秒
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 取消广播接收器的注册
        unregisterReceiver(wifiScanReceiver);
    }

    public List<WifiData> getWifiList() {
        boolean enabled = mWifiManager.isWifiEnabled();
        mWifiScanResult = mWifiManager.getScanResults();
        Log.d("boge","enabled = "+enabled);
        Log.d("boge","wifiInfo = "+mWifiScanResult.toString());
        List<WifiData> wifiList = new ArrayList<>();
        if (mWifiScanResult != null && mWifiScanResult.size() > 0) {
            for (ScanResult sr : mWifiScanResult) {
                if (!sr.SSID.equals("")) {
                    WifiData data = new WifiData();
                    data.setBssid(sr.BSSID);
                    data.setName(sr.SSID);

                    int numLevel = mWifiManager.calculateSignalLevel(sr.level,5);
                    String level = "";
                    switch (numLevel) {
                        case 5:
                            level = "很好";
                            break;
                        case 4:
                            level = "较好";
                            break;
                        case 3:
                            level = "一般";
                            break;
                        case 2:
                            level = "较差";
                            break;
                        case 1:
                            level = "很差";
                            break;
                    }
                    data.setLevel(level);
                    data.setFrequency(sr.frequency);
                    data.setCapabilites(sr.capabilities);
                    wifiList.add(data);
                }
            }
        }
        Log.d("boge","wifiList = " + wifiList);
        return wifiList;
    }
}