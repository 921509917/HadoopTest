package com.duan.hadoop.bigData.flow;

import com.duan.hadoop.bean.Flow;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class FlowReducer extends Reducer<Text,Flow,Text, IntWritable> {
    Text key;
    IntWritable value;
    @Override
    protected void reduce(Text key, Iterable<Flow> values, Context context) throws IOException, InterruptedException {
        Flow tmp = new Flow();
        for (Flow flow : values) {
            tmp.setPhone(flow.getPhone());
            tmp.setFlow(flow.getFlow());
        }
        key = new Text();
        key.set(tmp.getPhone()+"/t"+"花费流量：");
        value = new IntWritable(tmp.getFlow());
        context.write(key,value);
    }
}
