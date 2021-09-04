package com.duan.hadoop.bigData.weather;

import java.io.*;
import java.util.zip.GZIPInputStream;

/**
 * 批量处理大数据文件
 */
public class WeatherFileCombine {
    public static void main(String[] args) {

        FileInputStream fis = null;
        GZIPInputStream gis = null;
        InputStreamReader isr = null;
        BufferedReader bufr = null;
        //把gsod_2016文件的路径复制到下面的路径中即可
        String inputPath = "F:\\gsod_2016";

        FileWriter fw = null;
        BufferedWriter bufw = null;
        String outPath = "gsod.txt";
        int fileCount = 0;  // 处理的文件数量
        int dataCount = 0;	// 处理的数据数量
        try {
            File fileDir = new File(inputPath);
            fw = new FileWriter(outPath);
            bufw = new BufferedWriter(fw);
            for(File file : fileDir.listFiles()) {
                // file对象  --  007026-99999-2016.op
                fis = new FileInputStream(file);
                gis = new GZIPInputStream(fis);
                // InputStreamReader 字符流
                isr = new InputStreamReader(gis);
                // 将字符流转变为缓冲字符流
                bufr = new BufferedReader(isr);

                String line = bufr.readLine();
                String str = "";
                while(line != null) {
                    // 处理
                    if(line.startsWith("STN")) {
                        str = "";
                        line = bufr.readLine();
                        continue ;
                    }

                    String[] strs = line.split(" ");
                    String word = "";
                    for(int i=0; i<strs.length; i++) {
                        if(strs[i].equals("")) {
                            continue;
                        }

                        if(strs[i].equals("9999.9") || strs[i].equals("999.9") || strs[i].equals("99.99")) {
                            strs[i] = "0.0";
                        }

                        if(strs[i].endsWith("*")) {
                            strs[i] = strs[i].replace("*", "");
                        }

                        if(strs[i].matches(".*[A-I]")) {
                            strs[i] = strs[i].substring(0, strs[i].length()-1);
                        }

                        if(i == 0) {
                            word += strs[i];
                        }else {
                            word += "/"+strs[i];
                        }
                    }

                    String[] newStrs = word.split("/");
                    for(int i=0; i<newStrs.length; i++) {
                        if(i==4 || i==6 || i==8 || i==10 || i==12 || i==14) {
                            continue;
                        }else {
                            if(i != (newStrs.length-1)) {
                                str += newStrs[i]+"/";
                            }else {
                                str += newStrs[i];
                            }
                        }
                    }

                    bufw.write(str);
                    bufw.newLine();
                    dataCount++;
                    str = "";
                    line = bufr.readLine();
                }
                if(bufr!=null){
                    try {
                        bufr.close();
                    } catch (IOException e) {
                    }
                }
                if(isr!=null){
                    try {
                        isr.close();
                    } catch (IOException e) {
                    }
                }
                if(gis!=null){
                    try {
                        gis.close();
                    } catch (IOException e) {
                    }
                }
                if(fis!=null){
                    try {
                        fis.close();
                    } catch (IOException e) {
                    }
                }
                fileCount++;
                if(fileCount % 50 == 0) {
                    System.out.println();
                }else {
                    System.out.print(".");
                }
            }
            bufw.flush();
            System.out.println("共处理:"+fileCount+"个文件,和"+dataCount+"条数据");
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(bufw!=null){
                try {
                    bufw.close();
                } catch (IOException e) {
                }
            }
            if(fw!=null){
                try {
                    fw.close();
                } catch (IOException e) {
                }
            }

        }
    }

}
