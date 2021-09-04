package cn.hadoop.dao;
/*
 * 去数据库中查询站点信息
 */

import cn.hadoop.pojo.StationInfo;
import cn.hadoop.utils.MysqlUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StationDao {
	//获取站点信息的方法
	public List<StationInfo> getStations() {
		List<StationInfo> list = new ArrayList<StationInfo>();
		//获取工具类中的连接对象conn
		Connection conn = MysqlUtils.getConn();
		//获取传输对象
		String sql = "select stn,wban,latitude,longitude,country_code from station_info";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				//创建封装数据的目标对象
				StationInfo info = new StationInfo();
				//name：stn-wban
				String name = rs.getString(1)+"-"+rs.getString(2);
				//value:{lon,lat}
				String[] value = {rs.getDouble(4)+"",rs.getDouble(3)+""};
				String country = rs.getString(5);
				info.setName(name);
				info.setValue(value);
				info.setCountry(country);
				//将站点对象station存储在list中
				list.add(info);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			MysqlUtils.close(rs, ps);
		}

		return list;
	}


	public String getCountry(String params) {
		String[] strs = params.split("-");

		Connection conn = MysqlUtils.getConn();
		String sql = "select country_code from station_info where stn=? and wban=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		String code = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, strs[0]);
			ps.setString(2, strs[1]);
			rs = ps.executeQuery();
			rs.next();
			code = rs.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			MysqlUtils.close(rs, ps);
		}

		return code;
	}
}
