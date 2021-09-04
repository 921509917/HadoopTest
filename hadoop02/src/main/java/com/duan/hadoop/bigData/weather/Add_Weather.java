package com.duan.hadoop.bigData.weather;

import com.duan.hadoop.util.MySQLUtils;

import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;

/**
 * 要处理的数据行--并添加至weather表
 * 007026/99999/20160622/94.7/66.7/0.0/0.0/6.2/0.0/0.0/0.0/100.4/87.8/0.00/0.0/000000
 * 结果：执行插入4292877条数据，用时360353毫秒！
 */
public class Add_Weather {
    static Connection connection = MySQLUtils.getConn();

    public static double pD(String v) {
        return Double.parseDouble(v);
    }

    public static void main(String[] args) {
        PreparedStatement ps = null;
        FileReader fi = null;
        BufferedReader br = null;
        SimpleDateFormat format = null;
        try {
            String inputPath = "F:\\文件\\大数据\\达内上课\\hadoop-day11\\gsod.txt";
            String sql = "insert into weather values (null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            ps = connection.prepareStatement(sql);
            fi = new FileReader(new File(inputPath));
            br = new BufferedReader(fi);
            //进行时间处理
            format = new SimpleDateFormat("yyyyMMdd");
            //计数器
            int count = 0;
            //当前时间
            long startTime = System.currentTimeMillis();
            //设置事务提交为手动
            connection.setAutoCommit(false);
            //读取每一行
            String line = br.readLine();
            while (line != null) {
                String[] values = line.split("/");
                ps.setString(1, values[0]);
                ps.setString(2, values[1]);
                ps.setDate(3, new Date(format.parse(values[2]).getTime()));
                ps.setDouble(4, pD(values[3]));
                ps.setDouble(5, pD(values[4]));
                ps.setDouble(6, pD(values[5]));
                ps.setDouble(7, pD(values[6]));
                ps.setDouble(8, pD(values[7]));
                ps.setDouble(9, pD(values[8]));
                ps.setDouble(10, pD(values[9]));
                ps.setDouble(11, pD(values[10]));
                ps.setDouble(12, pD(values[11]));
                ps.setDouble(13, pD(values[12]));
                ps.setDouble(14, pD(values[13]));
                ps.setDouble(15, pD(values[14]));
                ps.setString(16, values[15]);
                count++;
                ps.addBatch();//批量操作
                //每50000条数据执行一次插入操作
                if (count % 50000 == 0) {
                    ps.executeBatch();
                    System.out.println("插入" + count + "条数据");
                }
                line = br.readLine();
            }
            ps.executeBatch();//执行操作
            connection.commit();//手动提交事务

            //获取执行完成之后的时间
            long endTime = System.currentTimeMillis();
            System.out.println("执行插入" + count + "条数据，用时" + (endTime - startTime) + "毫秒！");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //通过在finally代码块中，关闭资源
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    ps = null;
                }
            }

            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    br = null;
                }
            }

            if (fi != null) {
                try {
                    fi.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    fi = null;
                }
            }
        }

    }

}
