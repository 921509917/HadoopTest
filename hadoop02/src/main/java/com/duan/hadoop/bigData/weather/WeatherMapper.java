package com.duan.hadoop.bigData.weather;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class WeatherMapper extends Mapper<LongWritable, Text,Text, NullWritable> {
    /**
     *     要处理的数据行
     *     007026/99999/20160622/94.7/66.7/0.0/0.0/6.2/0.0/0.0/0.0/100.4/87.8/0.00/0.0/000000
     */
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] datas = line.split("/");

    }
}
