package cn.hadoop.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.hadoop.dao.CountryTableDao;
import cn.hadoop.dao.CountryWeatherDao;
import cn.hadoop.pojo.CountryTable;
import cn.hadoop.pojo.CountryWeather;
import com.alibaba.fastjson.JSON;


public class CountryWeatherService {
	private CountryWeatherDao dao = new CountryWeatherDao();
	private CountryTableDao tabDao = new CountryTableDao();
	private List<CountryTable> cold;
	private List<CountryTable> livable;
	private List<CountryTable> hot;
	
	public Map<String, String> getCountryData(String country_code){
		List<CountryWeather> list = dao.getCountryData(country_code);
		getCountryTableData(country_code);
		
		Map<String, String> map = new HashMap<>();
		System.out.println(cold.size()+"+++++++++++++++++++++++++++++");
		map.put("countryWeather", JSON.toJSONString(list));
		map.put("cold", JSON.toJSONString(cold));
		map.put("livable", JSON.toJSONString(livable));
		map.put("hot", JSON.toJSONString(hot));
		
		return map;
	}
	
	public void getCountryTableData(String country_code) {
		List<CountryTable> list = tabDao.getStationByCountry(country_code);
		
		cold = new ArrayList<>();
		livable = new ArrayList<>();
		hot = new ArrayList<>();
		
		for(CountryTable ct : list) {
			if(ct.getFlag()==0) {
				livable.add(ct);
			}else if(ct.getFlag() == 1) {
				cold.add(ct);
			}else if(ct.getFlag() == 2) {
				hot.add(ct);
			}
		}
	}
}
