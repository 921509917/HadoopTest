package com.duan.hadoop.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class HDFSUtils {
	static Configuration conf = null;
	static FileSystem fs = null;
	private final static String HDFS_Path = "hdfs://192.168.78.16:9000";
	
	static {
		try {
			conf = new Configuration();
			conf.set("dfs.replication", "1");
//			fs = FileSystem.get(new URI(HDFS_Path), conf, "root");
			fs = FileSystem.get(new URI(HDFS_Path), conf);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void HDFS_put(String winPath, String hdfsPath) {
		InputStream in = null;
		OutputStream out = null;
		try {
			File winFile = new File(winPath);
			in = new FileInputStream(winFile);
			out = fs.create(new Path(hdfsPath));
			
			IOUtils.copy(in, out);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(in, out);
		}
	}
	
	public static void close(InputStream in, OutputStream out) {
		if(in != null) {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				in = null;
			}
		}
		
		if(out != null) {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				out = null;
			}
		}
	}
}
