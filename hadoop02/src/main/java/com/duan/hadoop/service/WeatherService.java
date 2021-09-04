package com.duan.hadoop.service;

import com.duan.hadoop.bean.Temp;
import com.duan.hadoop.bean.Weather;
import com.duan.hadoop.dao.WeatherDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeatherService {
    private static WeatherDao dao = new WeatherDao();
    private static Map<String, List<Weather>> weathers = new HashMap<String, List<Weather>>();

    //通过stn、wban查询天气数据，并把对应的数据存储到缓存中
    private void cache(String stn, String wban){
        String stationFlag = stn+wban;
        //如果缓存中不存当前数据，通过dao查询，并存放到缓存中
        if(!weathers.containsKey(stationFlag)) {
            //去数据库查数据并存放在list集合中
            List<Weather> list = dao.getWeather(stn, wban);
            //将查询到的数据放在缓存中
            weathers.put(stationFlag, list);
        }
    }

    //从缓存中获取对应站点（stn、wban）的天气数据
    private List<Weather> getWeather(String stn, String wban){
        //首先确保缓存中存在该站点的数据
        cache(stn, wban);
        return weathers.get(stn+wban);
    }

    public List<Temp> getTemp(String stn, String wban){
        List<Weather> list = getWeather(stn, wban);
        List<Temp> tempList = new ArrayList<Temp>();

        //遍历
        for(Weather w : list) {
            Temp t = new Temp();
            t.setStn(w.getStn());
            t.setWban(w.getWban());
            t.setYearmoda(w.getYearmoda().toString());
            t.setTemp(w.getTemp());
            t.setDewp(w.getDewp());
            t.setMax(w.getMax());
            t.setMin(w.getMin());

            tempList.add(t);
        }

        return tempList;
    }
}
