package com.example.componentstest.utils;

import android.os.Environment;
import android.os.StatFs;

public class StorageUtil {

    public static StorageInfo getStorageInfo(String path) {
        StatFs stat = new StatFs(path);
        long blockSize = stat.getBlockSizeLong();
        long totalBlocks = stat.getBlockCountLong();
        long availableBlocks = stat.getAvailableBlocksLong();
        long usedBlocks = totalBlocks - availableBlocks;

        long totalSize = totalBlocks * blockSize;
        long usedSize = usedBlocks * blockSize;
        long freeSize = availableBlocks * blockSize;

        return new StorageInfo(totalSize, usedSize, freeSize);
    }

    private static String getSecondaryStoragePath() {
        return System.getenv("SECONDARY_STORAGE");
    }

    public static double convertBytesToGB(long bytes) {
        double gigabytes = (double) bytes / (1024 * 1024 * 1024);
        return gigabytes;
    }

    public static class StorageInfo {
        public final String storageType;
        public final long totalSize;
        public final long usedSize;
        public final long freeSize;

        public StorageInfo(String storageType, long totalSize, long usedSize, long freeSize) {
            this.storageType = storageType;
            this.totalSize = totalSize;
            this.usedSize = usedSize;
            this.freeSize = freeSize;
        }

        public StorageInfo(long totalSize, long usedSize, long freeSize) {
            this.storageType = "";
            this.totalSize = totalSize;
            this.usedSize = usedSize;
            this.freeSize = freeSize;
        }

        @Override
        public String toString() {
            return "StorageInfo{" +
                    "storageType='" + storageType + '\'' +
                    ", totalSize=" + convertBytesToGB(totalSize) +
                    ", usedSize=" + convertBytesToGB(usedSize) +
                    ", freeSize=" + convertBytesToGB(freeSize) +
                    '}';
        }
    }
}
