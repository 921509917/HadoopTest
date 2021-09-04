package com.duan.hadoop.dao;

import com.duan.hadoop.bean.Weather;
import com.duan.hadoop.util.MySQLUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WeatherDao {
    //从自定义的工具类中获取连接对象
    private Connection conn = MySQLUtils.getConn();

    public List<Weather> getWeather(String stn, String wban){
        List<Weather> list = new ArrayList<Weather>();
        //创建sql语句，以及preparedStatement对象
        String sql = "select * from weather where stn=? and wban=?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, stn);
            ps.setString(2, wban);
            rs = ps.executeQuery();
            while(rs.next()) {
                Weather w = new Weather();
                w.setStn(rs.getString(2));
                w.setWban(rs.getString(3));
                w.setYearmoda(rs.getDate(4));
                w.setTemp(rs.getDouble(5));
                w.setDewp(rs.getDouble(6));
                w.setSlp(rs.getDouble(7));
                w.setStp(rs.getDouble(8));
                w.setVisib(rs.getDouble(9));
                w.setWdsp(rs.getDouble(10));
                w.setMxspd(rs.getDouble(11));
                w.setGust(rs.getDouble(12));
                w.setMax(rs.getDouble(13));
                w.setMin(rs.getDouble(14));
                w.setPrcp(rs.getDouble(15));
                w.setSndp(rs.getDouble(16));
                w.setFrshtt(rs.getString(17));

                list.add(w);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            MySQLUtils.close(rs, ps);
        }

        return list;
    }
}
