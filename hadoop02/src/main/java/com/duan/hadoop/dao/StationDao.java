package com.duan.hadoop.dao;

import com.duan.hadoop.bean.Station;
import com.duan.hadoop.util.MySQLUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StationDao {
    //从自定义的工具类中获取连接对象
    private Connection conn = MySQLUtils.getConn();

    /**
     * 获取数据库中所有站点信息
     * @throws SQLException
     */
    public List<Station> getStation() {
        //创建保存站点信息对象的集合
        List<Station> list = new ArrayList<Station>();

        String sql = "select * from station_info";
        //创建传输对象
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            //执行sql，返回结果集对象
            rs = ps.executeQuery();
            //遍历结果集对象
            while(rs.next()) {//通常将从数据库中得到的数据封装到对象中
                //将数据封装到对象中
                Station s = new Station();
                //拼接站点的名称：stn-wban
                s.setName(rs.getString("stn")+"-"+rs.getString("wban"));
                //指定站点的经纬度
                String[] value = {rs.getDouble("longitude")+"",
                        rs.getDouble("latitude")+""};
                s.setValue(value);

                //将得到的对象保存到集合中
                list.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //关闭资源
            MySQLUtils.close(rs, ps);
        }

        return list;
    }
}
