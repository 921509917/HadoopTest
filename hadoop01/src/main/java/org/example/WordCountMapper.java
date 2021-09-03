package org.example;

import com.jcraft.jsch.IO;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionOutputStream;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.util.ReflectionUtils;

import java.io.*;

public class WordCountMapper extends Mapper<LongWritable, Text,Text, IntWritable> {
    Text k = new Text();
    IntWritable v = new IntWritable(1);
    String name;
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        //获取切片信息
        FileSplit inputSplit = (FileSplit) context.getInputSplit();
        //获取文件名称
        name = inputSplit.getPath().getName();

    }
    //压缩文件
    private static void compress(String filename, String method) throws IOException, ClassNotFoundException {
        //获取输入流
        FileInputStream fileInputStream = new FileInputStream(new File(filename));
        Class aClass = Class.forName(method);
        CompressionCodec codec = (CompressionCodec) ReflectionUtils.newInstance(aClass, new Configuration());
        //获取输出流
        FileOutputStream fileOutputStream = new FileOutputStream(new File(filename+codec.getDefaultExtension()));
        CompressionOutputStream outputStream = codec.createOutputStream(fileOutputStream);
        //流的对拷
        IOUtils.copyBytes(fileInputStream,outputStream,1024*1024,false);
        //关闭流
        IOUtils.closeStream(outputStream);
        IOUtils.closeStream(fileOutputStream);
        IOUtils.closeStream(fileInputStream);
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        compress("ss.txt","method");
    }
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //读取指定文件中的第一行
        String line = value.toString();
        //通过分隔符进行分隔
        String[] words = line.split(" ");
        for (String w: words) {
            k.set(w);
            context.write(k,v);
        }
    }
}
