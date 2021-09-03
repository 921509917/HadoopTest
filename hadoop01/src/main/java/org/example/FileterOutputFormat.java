package org.example;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

import java.io.IOException;

public class FileterOutputFormat extends RecordWriter<Text, NullWritable> {
    public FileterOutputFormat(TaskAttemptContext context) {
    }

    @Override
    public void write(Text text, NullWritable nullWritable) throws IOException, InterruptedException {

    }

    @Override
    public void close(TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {

    }
}
