package cn.hadoop.dao;

import cn.hadoop.pojo.CountryWeather;
import cn.hadoop.utils.MysqlUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CountryWeatherDao {

	public List<CountryWeather> getCountryData(String country_code){
		Connection conn = MysqlUtils.getConn();

		List<CountryWeather> list = new ArrayList<>();

		String sql = "SELECT cw.* FROM countries c, countries_weather cw WHERE c.json_name=?  AND c.fips_id=cw.country_code";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, country_code);
			rs = ps.executeQuery();
			while(rs.next()) {
				CountryWeather cw = new CountryWeather();
				cw.setName(rs.getString(1));
				cw.setDate(rs.getString(2));
				cw.setMax(rs.getString(3));
				cw.setMin(rs.getString(4));
				cw.setAvg(rs.getString(5));
				cw.setRange(Double.parseDouble(rs.getString(3)) - Double.parseDouble(rs.getString(4)) + "");
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
