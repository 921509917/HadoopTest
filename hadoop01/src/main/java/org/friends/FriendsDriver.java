package org.friends;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueLineRecordReader;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.example.WordCountDriver;
import org.example.WordCountMapper;
import org.example.WordCountReducer;

import java.io.IOException;

public class FriendsDriver {
    private static String[] otherArgs;
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        /*配置对象*/
        Configuration conf = new Configuration();
        //设置切割分隔符
        conf.set(KeyValueLineRecordReader.KEY_VALUE_SEPERATOR,":");
        //获取job对象
        Job job = Job.getInstance(conf);
        //设置jar存储位置----反射
        job.setJarByClass(FriendsDriver.class);
        //关联Map和Reduce类
        job.setMapperClass(FriendsMapper.class);
        job.setReducerClass(FriendsReducer.class);
        //设置Mapper阶段输出数据的key和value
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
        //设置最终输出数据的key和value
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        //设置输入格式，默认为TextInputFormat
        job.setInputFormatClass(KeyValueTextInputFormat.class);
        otherArgs = new String[]{"f:/input/inputFriends","f:/output"};
        //设置输入路径和输出路径
        FileInputFormat.setInputPaths(job,new Path(otherArgs[0]));
        FileOutputFormat.setOutputPath(job,new Path(otherArgs[1]));

        boolean fl = job.waitForCompletion(true);
        System.exit(fl?0:1);

    }
}
