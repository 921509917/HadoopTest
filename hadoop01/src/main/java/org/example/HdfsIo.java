package org.example;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

public class HdfsIo {
    //IO流的方式进行上传
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        //获取对象
        Configuration configuration = new Configuration();
        FileSystem fileSystem = FileSystem.get(new URI("hdfs://192.168.78.16:9000"),configuration,"djf");
        //获取输入流--上传
        FileInputStream inputStream = new FileInputStream(new File(""));
        //获取输入流--下载
        FSDataInputStream open = fileSystem.open(new Path("/"));
        //定位读取文件(文件大小)
        open.seek(1024*1024*128);
        //获取输出流--上传
        FSDataOutputStream fsDataOutputStream = fileSystem.create(new Path(""));
        //获取输出流--上传
        FileOutputStream fileOutputStream = new FileOutputStream(new File(""));
        //流的对拷
        IOUtils.copyBytes(inputStream,fsDataOutputStream,configuration);
        //关闭资源
        IOUtils.closeStream(fsDataOutputStream);
        IOUtils.closeStream(inputStream);
        fileSystem.close();
    }
}
