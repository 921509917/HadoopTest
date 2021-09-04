package cn.hadoop.dao;

import cn.hadoop.pojo.StationTempCount;
import cn.hadoop.utils.MysqlUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 某站点宜居、偏冷、偏热数据量查询
 *
 */
public class StationTempCountDao {
	public List<StationTempCount> getCount(String stationNum){
		List<StationTempCount> list = new ArrayList<>();

		String stn = stationNum.split("-")[0];
		String wban = stationNum.split("-")[1];

		Connection conn = MysqlUtils.getConn();

		String sql = "SELECT \r\n" +
				"SUM(CASE WHEN temp>(25*1.8+32) THEN 1 ELSE 0 END) AS hot,\r\n" +
				"SUM(CASE WHEN temp<(15*1.8+32) THEN 1 ELSE 0 END) AS cold,\r\n" +
				"SUM(CASE WHEN temp>=(15*1.8+32) AND temp<=(25*1.8+32) THEN 1 ELSE 0 END) AS livable\r\n" +
				"FROM weather WHERE stn=? AND wban=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);

			ps.setString(1, stn);
			ps.setString(2, wban);

			rs = ps.executeQuery();

			while(rs.next()) {
				StationTempCount stc = new StationTempCount();

				stc.setName(stationNum);
				stc.setHot(rs.getInt(1));
				stc.setCold(rs.getInt(2));
				stc.setLivable(rs.getInt(3));

				list.add(stc);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			MysqlUtils.close(rs, ps);
		}

		return list;
	}
}
