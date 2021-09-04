package cn.hadoop.utils;

import org.junit.jupiter.api.Test;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLTest {
	/**
	 * 获取在station_info表中被重复使用的经纬度坐标和被使用的次数
	 * @throws Exception
	 */
	public List<String> getRepLatLon() throws Exception {
		List<String> list = new ArrayList<String>();
//		Writer writ = new FileWriter(new File("la_lo_count"));
//		BufferedWriter bw = new BufferedWriter(writ);
		Connection conn = MysqlUtils.getConn();

		String sql = "SELECT * FROM " +
				"(SELECT latitude la, longitude lo, COUNT(*) c FROM station_info GROUP BY latitude, longitude) tmp WHERE c>1;";
		PreparedStatement ps = conn.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();
		StringBuffer sb = new StringBuffer();
		while(rs.next()) {
			String lat = rs.getString(1);
			String lon = rs.getString(2);
			String count = rs.getString(3);
			sb.append(lat).append("/").append(lon).append("/").append(count);

			list.add(sb.toString());
//			bw.write(sb.toString());
//			bw.newLine();
			sb.setLength(0);
		}

		rs.close();
		ps.close();
//		bw.close();
//		writ.close();
		return list;
	}
	/*
 SELECT * FROM dist_weather ,
(SELECT stn s ,wban w FROM station_info WHERE latitude=0.000 AND longitude=0.000) tmp 
WHERE dist_weather.stn_weather=tmp.s AND dist_weather.wban_weather=tmp.w ORDER BY station_count DESC;
	 */
	public void getStnAndWabn() throws Exception {
		List<String> list = getRepLatLon();

		Connection conn = MysqlUtils.getConn();

		BufferedWriter bw = new BufferedWriter(new FileWriter(new File("delete.txt")));

		for(String tmp : list) {
			String[] strs = tmp.split("/");
			String lat = strs[0];
			String lon = strs[1];

//			bw.write(lat+":"+lon);
//			bw.newLine();
			String sql = "SELECT * FROM dist_weather ," +
					"(SELECT stn s ,wban w FROM station_info WHERE latitude=? AND longitude=?) tmp " +
					"WHERE dist_weather.stn_weather=tmp.s AND dist_weather.wban_weather=tmp.w ORDER BY station_count DESC";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, lat);
			ps.setString(2, lon);

			ResultSet rs = ps.executeQuery();
			rs.next();
			while(rs.next()) {
//				System.out.println("++++++++++++++++");
				String stn = rs.getString(1);
				String wban = rs.getString(2);
				String count = rs.getString(3);
				bw.write(stn+"==="+wban+"====="+count);
				bw.newLine();
			}

//			bw.write("=====================================================");
//			bw.newLine();

			rs.close();
			ps.close();
		}

		bw.close();
	}

	public void delete() throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(new File("delete.txt")));
		Connection conn = MysqlUtils.getConn();
		String line = "";
		while((line = br.readLine()) != null) {
			String[] strs = line.split("===");
			String stn = strs[0];
			String wban = strs[1];


			String sql = "delete from station_info where stn=? and wban=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, stn);
			ps.setString(2, wban);

			ps.execute();

			ps.close();

		}

		br.close();
	}

	@Test
	public void insert() {
		Connection conn = MysqlUtils.getConn();

		PreparedStatement ps = null;

		BufferedReader br = null;
		try {

			String sql = "insert into countries values (?, ?, ?)";

			ps = conn.prepareStatement(sql);

			br = new BufferedReader(new FileReader("WebContent/data/out10.txt"));

			String line = "";

			while((line = br.readLine()) != null) {
				String[] strs1 = line.split("======");
				String json_country = strs1[1];
				String code = strs1[0].split("===")[0];
				System.out.println();
				String gsod_country = strs1[0].split("===")[1];

				ps.setString(1, code);
				ps.setString(2, gsod_country);
				ps.setString(3, json_country);

				ps.addBatch();

			}

			ps.executeBatch();

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}	
