package cn.hadoop.dao;

import cn.hadoop.pojo.Weather;
import cn.hadoop.utils.MysqlUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 用来温度类信息
 */
public class WeatherDao {

	public List<Weather> getWeather(String stn, String wban) {
		List<Weather> list = new ArrayList<Weather>();
		//stn=010010  wban=99999
		Connection conn = MysqlUtils.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from weather where STN=? AND WBAN=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, stn);
			ps.setString(2, wban);
			rs = ps.executeQuery();
			//遍历结果集对象，把每条封装到Weather对象
			while(rs.next()) {
				Weather w = new Weather();
				w.setStn(rs.getString(2));
				w.setWban(rs.getString(3));
				w.setDate(rs.getString(4));
				w.setTemp(rs.getString(5));
				w.setDewp(rs.getString(6));
				w.setSlp(rs.getString(7));
				w.setStp(rs.getString(8));
				w.setVisib(rs.getString(9));
				w.setWdsp(rs.getString(10));
				w.setMxspd(rs.getString(11));
				w.setGust(rs.getString(12));
				w.setMax(rs.getString(13));
				w.setMin(rs.getString(14));
				w.setPrcp(rs.getString(15));
				w.setSndp(rs.getString(16));
				String frshtt = rs.getString(17).trim();
				String[] severes = frshtt.split("");
				w.setFog(severes[0]);
				w.setRain(severes[1]);
				w.setSnow(severes[2]);
				w.setHail(severes[3]);
				w.setThunder(severes[4]);
				w.setTornado(severes[5]);

				list.add(w);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			MysqlUtils.close(rs, ps);
		}

		return list;
	}
}
