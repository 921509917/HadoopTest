package com.duan.hadoop.bigData.flow;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class FlowTest {
    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
        Configuration configuration = new Configuration();
        configuration.set("dfs.replication","2");
        FileSystem system = FileSystem.get(new URI("hdfs://192.168.78.16:9000"),configuration,"admin");
        system.copyFromLocalFile(new Path("F:\\flow.txt"),new Path("/input/flow.txt"));
        system.close();

    }
}
