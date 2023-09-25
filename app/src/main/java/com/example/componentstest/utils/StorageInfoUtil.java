package com.example.componentstest.utils;

import java.io.File;

public class StorageInfoUtil {

    public static StorageInfo getStorageInfo(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return null;
        }

        long totalSize = file.getTotalSpace();
        long freeSize = file.getFreeSpace();
        long usedSize = totalSize - freeSize;

        return new StorageInfo(convertBytesToGB(totalSize), convertBytesToGB(usedSize), convertBytesToGB(freeSize));
    }

    public static double convertBytesToGB(long bytes) {
        double gigabytes = (double) bytes / (1024 * 1024 * 1024);
        return gigabytes;
    }

    public static class StorageInfo {
        private double totalSizeGB;
        private double usedSizeGB;
        private double freeSizeGB;

        public StorageInfo(double totalSizeGB, double usedSizeGB, double freeSizeGB) {
            this.totalSizeGB = totalSizeGB;
            this.usedSizeGB = usedSizeGB;
            this.freeSizeGB = freeSizeGB;
        }

        public double getTotalSizeGB() {
            return totalSizeGB;
        }

        public double getUsedSizeGB() {
            return usedSizeGB;
        }

        public double getFreeSizeGB() {
            return freeSizeGB;
        }

        @Override
        public String toString() {
            return "StorageInfo{" +
                    "totalSizeGB=" + totalSizeGB +
                    ", usedSizeGB=" + usedSizeGB +
                    ", freeSizeGB=" + freeSizeGB +
                    '}';
        }
    }
}
