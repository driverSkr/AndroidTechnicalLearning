package com.example.componentstest.utils;

import android.content.Context;
import android.os.storage.StorageManager;
import android.util.Log;

import java.lang.reflect.Array;
import java.lang.reflect.Method;

public class FileUtils {
    private static final String TAG = "FileUtil";

    private Context mContext;

    public FileUtils(Context context) {
        mContext = context;
    }

    /**
     * 通过反射调用获取内置存储和外置sd卡根路径(通用)
     *
     * @param is_removale 是否可移除，false返回内部存储路径，true返回外置SD卡路径
     * @return
     */
    private String getStoragePath(boolean is_removale) {
        String path = "";
        //使用getSystemService(String)检索一个StorageManager用于访问系统存储功能。
        StorageManager mStorageManager = (StorageManager) mContext.getSystemService(Context.STORAGE_SERVICE);
        Class<?> storageVolumeClazz = null;
        try {
            storageVolumeClazz = Class.forName("android.os.storage.StorageVolume");
            Method getVolumeList = mStorageManager.getClass().getMethod("getVolumeList");
            Method getPath = storageVolumeClazz.getMethod("getPath");
            Method isRemovable = storageVolumeClazz.getMethod("isRemovable");
            Object result = getVolumeList.invoke(mStorageManager);

            for (int i = 0; i < Array.getLength(result); i++) {
                Object storageVolumeElement = Array.get(result, i);
                path = (String) getPath.invoke(storageVolumeElement);
                boolean removable = (Boolean) isRemovable.invoke(storageVolumeElement);
                if (is_removale == removable) {
                    Log.d(TAG, "getStoragePath: "+path);
                    return path;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return path;
    }

    public String getRootPath(){
        return getStoragePath(true);
    }

    public String getDcimPath(){
        return getStoragePath(true)+"/DCIM";
    }

    public String getLivePath(){
        return getStoragePath(true)+"/DCIM/record/live/0";
    }
    public String getPhotoPath(){
        return getStoragePath(true)+"/DCIM/PHOTO";
    }

    public String getImagePath(){
        return getStoragePath(true)+"/DCIM/img";
    }
}
