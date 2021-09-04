package com.duan.hadoop.service;

import com.duan.hadoop.bean.Station;
import com.duan.hadoop.dao.StationDao;

import java.util.List;

public class StationService {
    private static StationDao dao = new StationDao();

    //查询站点信息
    public List<Station> getStation(){
        return dao.getStation();
    }
}
