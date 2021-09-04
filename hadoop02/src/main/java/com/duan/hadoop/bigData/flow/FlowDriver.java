package com.duan.hadoop.bigData.flow;



import com.duan.hadoop.bean.Flow;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.net.URISyntaxException;

public class FlowDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException, URISyntaxException {
            /*配置对象*/
            Configuration conf = new Configuration();
            //获取job对象
            Job job = Job.getInstance(conf);
            //设置jar存储位置----反射
            job.setJarByClass(FlowDriver.class);
            job.setUser("admin");
            //关联Map和Reduce类
            job.setMapperClass(FlowMapper.class);
            job.setReducerClass(FlowReducer.class);
            //设置Mapper阶段输出数据的key和value
            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(Flow.class);
            //设置最终输出数据的key和value
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(Flow.class);
            //设置分区数
            job.setPartitionerClass(FlowPartition.class);
            job.setNumReduceTasks(3);
            String a = "hdfs://192.168.78.16:9000/input,hdfs://192.168.78.16:9000/s,f:/input/inputFlow,f:/output";
            //设置输入路径和输出路径
            FileInputFormat.setInputPaths(job,new Path("hdfs://192.168.78.16:9000/input"));
            FileOutputFormat.setOutputPath(job,new Path("hdfs://192.168.78.16:9000/output"));
            boolean fl = job.waitForCompletion(true);
            System.exit(fl?0:1);

    }
}
