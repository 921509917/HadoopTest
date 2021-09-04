package cn.hadoop.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.sql.*;
import java.text.SimpleDateFormat;

/*
 * 将gsod.txt文档中的数据导入MySQL数据库中的weather表
 */
public class MysqlUtils {
	private static Connection conn = null;
	static {
		try {
			//1、加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			//2、获取Connection对象
			String url = "jdbc:mysql://localhost:3306/gsod2?rewriteBatchedStatements=true";
			String user = "root";
			String password = "3406";
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void close(ResultSet rs, PreparedStatement ps) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				rs = null;
			}
		}
		if(ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				ps = null;
			}
		}
	}

	public static void close(PreparedStatement ps) {
		if(ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				ps = null;
			}
		}
	}

	public static Connection getConn() {
		return conn;
	}

	public static void main(String[] args) {
		PreparedStatement ps = null;
		Reader in = null;
		BufferedReader bufr = null;
		try {
			//Java.IO相关内容
			in = new FileReader(new File("D:/gsod.txt"));
			bufr = new BufferedReader(in);
			String line = bufr.readLine();

			//3、获取ps对象
			String sql = "insert into weather values (null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);

			//创建SimpleDateFormat对象
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

			//计数器
			int count = 0;
			//计时器
			long startTime = System.currentTimeMillis();

			//设置事务提交为手动
			conn.setAutoCommit(false);

			//为？占位符赋值
			while(line != null) {//判断读取得到内容不为空
				String[] strs = line.split("/");

				ps.setString(1, strs[0]);//stn
				ps.setString(2, strs[1]);//wban
				ps.setDate(3, new Date(sdf.parse(strs[2]).getTime()));
				ps.setDouble(4, Double.parseDouble(strs[3]));
				ps.setDouble(5, Double.parseDouble(strs[4]));
				ps.setDouble(6, Double.parseDouble(strs[5]));
				ps.setDouble(7, Double.parseDouble(strs[6]));
				ps.setDouble(8, Double.parseDouble(strs[7]));
				ps.setDouble(9, Double.parseDouble(strs[8]));
				ps.setDouble(10, Double.parseDouble(strs[9]));
				ps.setDouble(11, Double.parseDouble(strs[10]));
				ps.setDouble(12, Double.parseDouble(strs[11]));
				ps.setDouble(13, Double.parseDouble(strs[12]));
				ps.setDouble(14, Double.parseDouble(strs[13]));
				ps.setDouble(15, Double.parseDouble(strs[14]));

				ps.setString(16, strs[15]);

				count++;//计数器加1

				ps.addBatch();//批量操作

				//每5万行插入一次数据
				if(count % 50000 ==0) {
					ps.executeBatch();
					System.out.println("插入"+count+"条数据");
				}
				line = bufr.readLine();//读取下一行数据
			}
			ps.executeBatch();
			conn.commit();//手动提交事务

			long endtime = System.currentTimeMillis();
			System.out.println("执行插入"+count+"条数据，用时："+(endtime-startTime)+"毫秒");

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(ps != null) {
				ps = null;
			}
			if(bufr != null) {
				bufr = null;
			}
			if(in != null) {
				in = null;
			}
		}
	}
}
