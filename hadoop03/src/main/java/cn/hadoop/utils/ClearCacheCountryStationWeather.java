package cn.hadoop.utils;

import cn.hadoop.pojo.Station;

import javax.servlet.ServletContext;
import java.util.List;

/**
 * 自动清空上次点击区域中站点的缓存信息
 *
 */
public class ClearCacheCountryStationWeather implements Runnable{
	private ServletContext sc ;		// application用于区域存储站点的气象数据
	private List<Station> list ;	// 区域的站点集合

	public ClearCacheCountryStationWeather(ServletContext sc, List<Station> list ) {
		this.sc = sc;
		this.list = list;
	}

	@Override
	public void run() {
		for(Station s : list) {
			sc.removeAttribute(s.getName());
		}
	}


}
