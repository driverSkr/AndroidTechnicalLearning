package com.example.componentstest.utils;

import java.io.File;

public class PathMemoryUtil {

    public static long calculateUsedMemory(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return 0;
        }
        if (file.isFile()) {
            return file.length();
        }
        return calculateDirectorySize(file);
    }

    public static long calculateFreeMemory(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return 0;
        }
        return file.getFreeSpace();
    }

    private static long calculateDirectorySize(File directory) {
        long totalSize = 0;
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    totalSize += file.isFile() ? file.length() : calculateDirectorySize(file);
                }
            }
        }
        return totalSize;
    }
}

