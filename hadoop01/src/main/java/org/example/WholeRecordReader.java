package org.example;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

public class WholeRecordReader extends RecordReader<Text, ByteWritable> {
    FileSplit split;
    Configuration conf;
    private Text k = new Text();
    private ByteWritable v = new ByteWritable();
    boolean isProgress = true;

    @Override
    public void initialize(InputSplit inputSplit, TaskAttemptContext context) throws IOException,
            InterruptedException {
        this.split = (FileSplit) inputSplit;
        conf = context.getConfiguration();
    }

    @Override
    public boolean nextKeyValue() throws IOException, InterruptedException {
        if (isProgress) {
            byte[] buf = new byte[(int) split.getLength()];
            Path path = split.getPath();
            FileSystem fileSystem = path.getFileSystem(conf);
            /*获取输入流*/
            FSDataInputStream fsDataInputStream = fileSystem.open(path);
            /*拷贝*/
            IOUtils.readFully(fsDataInputStream, buf, 0, buf.length);
            k.set(k.toString());
            IOUtils.closeStream(fileSystem);
            isProgress = false;
        }


        return false;
    }

    @Override
    public Text getCurrentKey() throws IOException, InterruptedException {
        return k;
    }

    @Override
    public ByteWritable getCurrentValue() throws IOException, InterruptedException {
        return v;
    }

    @Override
    public float getProgress() throws IOException, InterruptedException {
        return 0;
    }

    @Override
    public void close() throws IOException {
    }
}
