package com.example.componentstest.utils;

import android.util.Log;

import java.io.File;

public class FileClearUtil {

    // 清空指定路径下的所有文件和文件夹
    public static boolean clearDirectory(String path) {
        File directory = new File(path);
        Log.d("boge","directory = "+directory.getPath());
        if (directory.exists() && directory.isDirectory()) {
            Log.d("boge","name = ");
            return clearDirectoryContents(directory);
        }
        return false;
    }

    // 递归清空文件夹内容
    private static boolean clearDirectoryContents(File directory) {
        Log.d("boge","directory = "+directory.getPath());
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                Log.d("boge","name = "+file.getName());
                if (file.isDirectory()) {
                    clearDirectoryContents(file);
                }
                if (!file.delete()) {
                    return false;
                }
            }
        }
        return true;
    }
}
