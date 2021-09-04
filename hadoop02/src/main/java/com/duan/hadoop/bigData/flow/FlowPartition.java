package com.duan.hadoop.bigData.flow;

import com.duan.hadoop.bean.Flow;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class FlowPartition extends Partitioner<Text, Flow> {
    @Override
    public int getPartition(Text text, Flow flow, int i) {
        int flowInt = flow.getFlow();
        int result;
        if (flowInt > 0 && flowInt < 2000){
            result = 0;
        }else if (flowInt > 2000 && flowInt < 5000){
            result = 1;
        }else {
            result = 2;
        }
        return result;
    }
}
