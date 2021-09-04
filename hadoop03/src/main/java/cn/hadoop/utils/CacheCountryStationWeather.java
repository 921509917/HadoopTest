package cn.hadoop.utils;

import cn.hadoop.dao.WeatherDao;
import cn.hadoop.pojo.Station;
import cn.hadoop.pojo.Weather;

import javax.servlet.ServletContext;
import java.util.List;

/**
 * 该类主要用来在返回区域的站点数据后，自动从数据库中查询当前区域中所有站点的气象数据
 *  并把数据存储在 ServletContext对象中，以便在整个项目中都可以使用。
 *
 */
public class CacheCountryStationWeather implements Runnable{
	private ServletContext sc ;		// application用于区域存储站点的气象数据
	private List<Station> list ;	// 区域的站点集合

	public CacheCountryStationWeather(ServletContext sc, List<Station> list) {
		this.sc = sc;
		this.list = list;
	}

	@Override
	public void run() {
		WeatherDao dao = new WeatherDao();

		for(Station s : list) {
			String[] names = s.getName().split("-");
			List<Weather> ws = dao.getWeather(names[0], names[1]);
			sc.setAttribute(s.getName(), ws);
			System.out.println("内存中存储"+s.getName()+"站点数据！");
		}

	}

}
