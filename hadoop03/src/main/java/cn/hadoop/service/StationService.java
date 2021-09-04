package cn.hadoop.service;

import cn.hadoop.dao.StationDao;
import cn.hadoop.pojo.Station;
import cn.hadoop.pojo.StationInfo;
import com.alibaba.fastjson.JSON;

import java.util.*;

/*
 * 在service层通常用来处理业务逻辑
 */
public class StationService {
	//创建保存站点信息的集合--缓存
	private static Map<String, String> cache = null;
	private StationDao dao = new StationDao();

	//给缓存添加数据
	public void getCache() {
		if(cache == null) {
			//如果缓存对象，先创建对象
			cache = new HashMap<String, String>();
		}
		//去Dao中查询数据
		List<StationInfo> list = dao.getStations();
		List<Station> stationList = new ArrayList<Station>();
		Set<String> set = new HashSet<String>();
		for(StationInfo info : list) {
			Station s = new Station();
			s.setName(info.getName());
			s.setValue(info.getValue());
			set.add(info.getCountry());
			stationList.add(s);
		}

		//将集合变为String
		String stations = JSON.toJSONString(stationList);
		String countryCount = JSON.toJSONString(set.size());
		String stationCount = JSON.toJSONString(list.size());

		cache.put("stations", stations);
		cache.put("countryCount", countryCount);
		cache.put("stationCount", stationCount);

	}

	public Map<String, String> getStations(){
		//如果缓存中没有数据，去数据库查询
		if(cache == null) {
			getCache();
		}
		//向controller返回缓存中的数据
		return cache;
	}
}