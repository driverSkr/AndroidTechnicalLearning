package com.example.componentstest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.util.Log;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class GetStoragePathActivity extends AppCompatActivity {

    private static final String TAG = "GetStoragePathActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_storage_path);

        findViewById(R.id.click).setOnClickListener(v -> {
            List<String> pathList = getStoragePathList();
            List<String> dcimPathList = getDcimPathList();
            Log.d("boge","存储路径："+ pathList);
            Log.d("boge","dcim存储路径："+ dcimPathList);
        });
    }

    public List<String> getDcimPathList(){
        List<String> pathList = getStoragePathList();
        List<String> dcimPathList = new ArrayList<>();
        for (String path : pathList){
            dcimPathList.add(path + "/DCIM");
        }
        return dcimPathList;
    }

    private List<String> getStoragePathList() {
        String path;
        List<String> pathList = new ArrayList<>();
        //使用getSystemService(String)检索一个StorageManager用于访问系统存储功能。
        StorageManager mStorageManager = (StorageManager) this.getSystemService(Context.STORAGE_SERVICE);
        Class<?> storageVolumeClazz;
        try {
            storageVolumeClazz = Class.forName("android.os.storage.StorageVolume");
            Method getVolumeList = mStorageManager.getClass().getMethod("getVolumeList");
            Method getPath = storageVolumeClazz.getMethod("getPath");
            Object result = getVolumeList.invoke(mStorageManager);

            for (int i = 0; i < Array.getLength(result); i++) {
                Object storageVolumeElement = Array.get(result, i);
                path = (String) getPath.invoke(storageVolumeElement);
                pathList.add(path);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pathList;
    }
}