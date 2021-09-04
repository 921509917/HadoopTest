package cn.hadoop.service;

import cn.hadoop.dao.StationDao;
import cn.hadoop.dao.WeatherDao;
import cn.hadoop.pojo.*;
import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeatherService {
	private WeatherDao dao = new WeatherDao();
	private StationDao stationDao = new StationDao();
	private static Map<String, List<Weather>> cache = null;

	// 从缓存中获取对应站点的数据
	public List<Weather> getCache(String params) {
		System.out.println(params+"==================");
		if (cache == null) { // 判断缓存是否存储在
			cache = new HashMap<>();
		}

		if (!cache.containsKey(params)) { // 判断缓存中是否存在对应站点的气象数据
			String[] strs = params.split("-");
			List<Weather> list = dao.getWeather(strs[0], strs[1]);
			cache.put(params, list);
		}

		return cache.get(params);
	}

	/**
	 * 從當前站點的所有數據解析溫度類數據
	 * @param params
	 * @return
	 */
	public List<Temp> getTemp(String params) {
		List<Weather> list = getCache(params);
		List<Temp> tempList = new ArrayList<>();

		for (Weather w : list) {
			Temp t = new Temp();
			t.setName(w.getStn() + "-" + w.getWban());
			t.setDate(w.getDate());
			t.setAvg(w.getTemp());
			t.setMax(w.getMax());
			t.setMin(w.getMin());

			tempList.add(t);
		}

		return tempList;
	}

	/**
	 * 從當前站點的所有數據中解析可見度數據
	 * @param params
	 * @return
	 */
	public List<Visib> getVisib(String params) {
		List<Weather> list = getCache(params);
		List<Visib> visibList = new ArrayList<>();

		for (Weather w : list) {
			Visib v = new Visib();
			v.setName(w.getStn() + "-" + w.getWban());
			v.setDate(w.getDate());
			v.setVisib(w.getVisib());

			visibList.add(v);
		}

		return visibList;
	}

	/**
	 * 從當前站點的所有數據中解析降水量數據
	 * @param params
	 * @return
	 */
	public List<RainFall> getRainFall(String params) {
		List<Weather> list = getCache(params);
		List<RainFall> rainfallList = new ArrayList<>();

		for (Weather w : list) {
			RainFall r = new RainFall();
			r.setName(w.getStn() + "-" + w.getWban());
			r.setDate(w.getDate());
			r.setRpcp(w.getPrcp());
			r.setSndp(w.getSndp());

			rainfallList.add(r);
		}

		return rainfallList;
	}

	/**
	 * 從當前站點的所有數據中解析惡劣天氣數據
	 * @param params
	 * @return
	 */
	public Severe getSevere(String params) {
		List<Weather> list = getCache(params);
		Severe s = new Severe();

		int fog = 0; // 雾
		int rain = 0; // 雨
		int snow = 0; // 雪
		int hail = 0; // 冰雹
		int thunder = 0; // 打雷
		int tornado = 0; //龍捲風

		for (Weather w : list) {

			fog += Integer.parseInt(w.getFog());
			rain += Integer.parseInt(w.getRain());
			snow += Integer.parseInt(w.getSnow());
			hail += Integer.parseInt(w.getHail());
			thunder += Integer.parseInt(w.getThunder());
			tornado += Integer.parseInt(w.getTornado());
		}

		s.setFog(fog+"");
		s.setRain(rain+"");
		s.setSnow(snow+"");
		s.setHail(hail+"");
		s.setThunder(thunder+"");
		s.setTornado(tornado+"");

		return s;
	}

	public String getStationInfo(String params) {
		String code = stationDao.getCountry(params);
		return code;
	}

	/**
	 * 將前台要顯示的所有數據封裝在一起
	 * @param params
	 * @return
	 */
	public Map<String, String> getData(String params){
		String countryCode = getStationInfo(params);

		Map<String, String> data = new HashMap<>();
		List<RainFall> rainFallList = getRainFall(params);
		Severe severe = getSevere(params);
		List<Temp> tempList = getTemp(params);
		List<Visib> visibList = getVisib(params);

		data.put("countryCode", countryCode);
		data.put("rain", JSON.toJSONString(rainFallList));
		data.put("severe", JSON.toJSONString(severe));
		data.put("temp", JSON.toJSONString(tempList));
		data.put("visib", JSON.toJSONString(visibList));

		return data;
	}
}
