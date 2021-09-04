package cn.hadoop.service;

import cn.hadoop.dao.StationTempCountDao;
import cn.hadoop.dao.StationTempDao;
import cn.hadoop.pojo.CountryWeather;
import cn.hadoop.pojo.StationTempCount;
import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StationTempService {
	// 获取站点温度数据
	private StationTempDao dao1 = new StationTempDao();
	// 获取站点偏冷、宜居、偏热数据占比
	private StationTempCountDao dao2 = new StationTempCountDao();

	public List<StationTempCount> getCount(String stationNum){
		return dao2.getCount(stationNum);
	}

	public List<CountryWeather> getStationTemp(String stationNum){
		return dao1.getStationWeather(stationNum);
	}

	public Map<String, String> getData(String stationNum){
		Map<String, String> map = new HashMap<>();
		// 数据量
		List<StationTempCount> list1 = getCount(stationNum);
		// 温度数据
		List<CountryWeather> list2 = getStationTemp(stationNum);

		map.put("temp", JSON.toJSONString(list2));
		map.put("count", JSON.toJSONString(list1));

		return map;
	}
}
