package com.example.componentstest.utils;

import java.io.File;

public class FileUtil {

    public static long getFileUsedSpace(String filePath) {
        File file = new File(filePath);
        long usedSpace = file.length();
        return usedSpace;
    }

    public static long getFileFreeSpace(String filePath) {
        File file = new File(filePath);
        long freeSpace = file.getFreeSpace();
        return freeSpace;
    }

    public static double convertBytesToGB(long bytes) {
        double gigabytes = (double) bytes / (1024 * 1024 * 1024);
        return gigabytes;
    }
}

