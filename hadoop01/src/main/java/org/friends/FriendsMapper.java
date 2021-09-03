package org.friends;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class FriendsMapper extends Mapper<LongWritable, Text,Text,Text> {
    Map<String,String[]> list = new LinkedHashMap<>();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //通过已设置的切割方式，可直接获取第一行中该用户的粉丝
        String fans = value.toString();
        //粉丝进行分割
        String[] fansSplit = fans.split(",");
        //将用户放入HashMap中
        list.put(key.toString(),fansSplit);


    }
}
