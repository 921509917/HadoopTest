package cn.hadoop.pojo;

import java.util.Arrays;

public class StationInfo {
	private String name;//站点编号：stn-wban
	private String[] value;//经纬度坐标
	private String country;//站点所在的国家

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String[] getValue() {
		return value;
	}
	public void setValue(String[] value) {
		this.value = value;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	@Override
	public String toString() {
		return "StationInfo [name=" + name + ", value=" + Arrays.toString(value) + ", country=" + country + "]";
	}

}
