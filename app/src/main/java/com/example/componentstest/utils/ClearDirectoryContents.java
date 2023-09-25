package com.example.componentstest.utils;

import java.io.File;

public class ClearDirectoryContents {
    public static void main(String[] args) {
        String directoryPath = "path/to/your/directory"; // 替换成你的文件夹路径

        File directory = new File(directoryPath);
        if (directory.exists() && directory.isDirectory()) {
            clearDirectoryContents(directory);
            System.out.println("Directory contents cleared successfully.");
        } else {
            System.out.println("Directory does not exist.");
        }
    }

    private static void clearDirectoryContents(File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    clearDirectoryContents(file);
                }
                file.delete();
            }
        }
    }
}
