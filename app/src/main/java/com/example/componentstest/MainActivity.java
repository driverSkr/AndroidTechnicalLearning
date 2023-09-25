package com.example.componentstest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.componentstest.utils.FileSizeUtil;
import com.example.componentstest.utils.FileUtil;
import com.example.componentstest.utils.FileUtils;
import com.example.componentstest.utils.PathMemoryUtil;
import com.example.componentstest.utils.StorageInfoUtil;
import com.example.componentstest.utils.StorageQueryUtil;
import com.example.componentstest.utils.StorageUtil;

import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        state = Environment.getExternalStorageState();

        findViewById(R.id.click).setOnClickListener(v -> {
            if (Environment.MEDIA_MOUNTED.equals(state)){
                File externalStorageDir = Environment.getExternalStorageDirectory();
                File externalFilesDir = getExternalFilesDir(null); // 获取应用专用外部存储目录

                Toast.makeText(this, "外部存储可用", Toast.LENGTH_SHORT).show();
                Log.d("boge","存储状态是："+state);
                Log.d("boge","外部存储的路径："+externalStorageDir.getAbsolutePath());
                Log.d("boge","应用专用外部存储目录："+externalFilesDir.getAbsolutePath());
            }else {
                Toast.makeText(this, "外部存储不可用", Toast.LENGTH_SHORT).show();
                Log.d("boge","存储状态是："+state);
            }
        });

        findViewById(R.id.click1).setOnClickListener(this);
        findViewById(R.id.click2).setOnClickListener(this);
        findViewById(R.id.click3).setOnClickListener(this);
        findViewById(R.id.click4).setOnClickListener(this);
        findViewById(R.id.click5).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        FileUtils utils = new FileUtils(this);
        switch (v.getId()){
            case R.id.click1:
                boolean b = IsExistCard(this);
                Log.d("boge","IsExistCard = "+b);
                break;
            case R.id.click2:

                double fileUsedSpace = FileUtil.convertBytesToGB(FileUtil.getFileUsedSpace(utils.getDcimPath()));
                double fileFreeSpace = FileUtil.convertBytesToGB(FileUtil.getFileFreeSpace(utils.getDcimPath()));
                Log.d("boge","fileUsedSpace = "+fileUsedSpace + " ; fileFreeSpace = "+fileFreeSpace);
                break;
            case R.id.click3:
                long usedMemory = PathMemoryUtil.calculateUsedMemory(utils.getDcimPath());
                long freeMemory = PathMemoryUtil.calculateFreeMemory(utils.getDcimPath());

                // 将字节转换为GB
                double usedMemoryGB = FileUtil.convertBytesToGB(usedMemory);
                double freeMemoryGB = FileUtil.convertBytesToGB(freeMemory);

                Log.d("boge","Used memory on SD card: " + usedMemoryGB + " GB");
                Log.d("boge","Free memory on SD card: " + freeMemoryGB + " GB");
                break;
            case R.id.click4:
                double fileOrFilesSize = FileSizeUtil.getFileOrFilesSize(utils.getDcimPath(), 4);
                String autoFileOrFilesSize = FileSizeUtil.getAutoFileOrFilesSize(utils.getDcimPath());
                Log.d("boge","fileOrFilesSize = "+fileOrFilesSize +" ; autoFileOrFilesSize = "+autoFileOrFilesSize);
                break;
            case R.id.click5:
                StorageUtil.StorageInfo storageInfo = StorageUtil.getStorageInfo(utils.getDcimPath());
                Log.d("boge","storageInfo = "+ storageInfo);
        }
    }

    /**
     * 判断外置SD/TF卡是否挂载
     */
    public boolean IsExistCard(Context context) {
        boolean result = false;
        StorageManager mStorageManager = (StorageManager) context.getSystemService(Context.STORAGE_SERVICE);
        Class<?> storageVolumeClazz = null;
        try {
            storageVolumeClazz = Class.forName("android.os.storage.StorageVolume");
            // 通过反射获取 StorageManager 的 getVolumeList 方法，用于获取存储卡列表
            Method getVolumeList = mStorageManager.getClass().getMethod("getVolumeList");
            Method getPath = storageVolumeClazz.getMethod("getPath");
            Method isRemovable = storageVolumeClazz.getMethod("isRemovable");
            Method getState = storageVolumeClazz.getMethod("getState");
            Object obj = null;
            try {
                // 通过反射调用 getVolumeList 方法，获取存储卡列表
                obj = getVolumeList.invoke(mStorageManager);
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            final int length = Array.getLength(obj);
            for (int i = 0; i < length; i++) {
                Object storageVolumeElement = Array.get(obj, i);
                String path = (String) getPath.invoke(storageVolumeElement);
                Log.d("boge","path = "+path);
                boolean removable = (Boolean) isRemovable.invoke(storageVolumeElement);
                Log.d("boge","是否可移除 = "+removable);
                // 如果存储卡可移除且状态为已挂载，将结果设为 true，然后退出循环
                String state = (String) getState.invoke(storageVolumeElement);
                if (removable && state.equals(Environment.MEDIA_MOUNTED)) {
                    result = true;
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}