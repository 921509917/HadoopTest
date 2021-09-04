package cn.hadoop.dao;

import cn.hadoop.pojo.CountryTable;
import cn.hadoop.utils.MysqlUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CountryTableDao {

	public List<CountryTable> getStationByCountry(String country_code) {

		List<CountryTable> list = new ArrayList<CountryTable>();

		Connection conn = MysqlUtils.getConn();

		String sql = "SELECT ct.* FROM countries_tables ct, countries c WHERE ct.country_code=c.fips_id AND c.json_name=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			ps = conn.prepareStatement(sql);

			ps.setString(1, country_code);

			rs = ps.executeQuery();

			while(rs.next()) {
				CountryTable ct = new CountryTable();
				ct.setCountryCode(rs.getString(1));

				String name = rs.getString(2) + "-" + rs.getString(3);

				ct.setName(name);
				ct.setFlag(rs.getInt(4));

				list.add(ct);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			MysqlUtils.close(rs, ps);
		}

		return list;
	}
}
