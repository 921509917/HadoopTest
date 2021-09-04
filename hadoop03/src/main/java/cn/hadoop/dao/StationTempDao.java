package cn.hadoop.dao;

import cn.hadoop.pojo.CountryWeather;
import cn.hadoop.utils.MysqlUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StationTempDao {
	public List<CountryWeather> getStationWeather(String stationNum){
		List<CountryWeather> list = new ArrayList<>();

		String stn = stationNum.split("-")[0];
		String wban = stationNum.split("-")[1];

		Connection conn = MysqlUtils.getConn();

		String sql = "SELECT stn, wban, yearmoda, temp, MAX, MIN FROM weather WHERE stn=? AND wban=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, stn);
			ps.setString(2, wban);

			rs = ps.executeQuery();

			while(rs.next()) {
				CountryWeather cw = new CountryWeather();
				cw.setName(stationNum);
				cw.setDate(rs.getString(3));
				cw.setAvg(rs.getString(4));
				cw.setMax(rs.getString(5));
				cw.setMin(rs.getString(6));
				cw.setRange(Double.parseDouble(rs.getString(5)) - Double.parseDouble(rs.getString(6)) + "");

				list.add(cw);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			MysqlUtils.close(rs, ps);
		}

		return list;
	}
}
