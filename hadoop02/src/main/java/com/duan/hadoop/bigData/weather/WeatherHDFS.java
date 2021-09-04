package com.duan.hadoop.bigData.weather;

import com.duan.hadoop.util.HDFSUtils;

public class WeatherHDFS {
    private static final String winPath= "";
    private static final String hdfsPath= "";
    public static void main(String[] args) {
        HDFSUtils.HDFS_put(winPath,hdfsPath);
    }
}
