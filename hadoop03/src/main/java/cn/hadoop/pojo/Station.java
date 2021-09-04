package cn.hadoop.pojo;

import java.util.Arrays;

public class Station {
	private String name;//站点编号：stn-wban
	private String[] value;//经纬度坐标

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
	@Override
	public String toString() {
		return "Station [name=" + name + ", value=" + Arrays.toString(value) + "]";
	}
}
