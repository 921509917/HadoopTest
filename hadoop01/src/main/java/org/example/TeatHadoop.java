package org.example;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Hello world!
 *
 */
public class TeatHadoop {
    public static void main(String[] args) throws Exception {
        //获取对象
        Configuration configuration = new Configuration();
        FileSystem fileSystem = FileSystem.get(new URI("hdfs:hadoop102:9000"),configuration,"duan");
        //文件上传
        fileSystem.copyFromLocalFile(new Path(""),new Path(""));
        fileSystem.delete(new Path("/"),true);
        fileSystem.copyToLocalFile(new Path("/"),new Path("/"));
        fileSystem.rename(new Path("/"),new Path("/"));
        FileStatus fileStatus = fileSystem.getFileStatus(new Path("/"));
        boolean file = fileStatus.isFile();

        /*
            文件下载 copyToLocalFile
            文件删除 delete
            文件修改名称 rename
        */
        //资源关闭
        fileSystem.close();
    }
}
