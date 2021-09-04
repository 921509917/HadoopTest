package com.duan.hadoop.bigData.flow;

import com.duan.hadoop.bean.Flow;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FlowMapper extends Mapper<LongWritable,Text,Text,Flow> {
    Text text;
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] values = line.split(" ");
        Flow flow = new Flow();
        flow.setPhone(values[0]);
        flow.setAddr(values[1]);
        flow.setName(values[2]);
        flow.setFlow(Integer.parseInt(values[3]));
        text = new Text();
        text.set(flow.getName());
        context.write(text,flow);
    }
}
