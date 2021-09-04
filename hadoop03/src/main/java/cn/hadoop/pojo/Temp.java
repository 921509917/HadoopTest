package cn.hadoop.pojo;


public class Temp {
	private String name;//stn-wban
	private String date;
	private String avg; 
	private String max;
	private String min;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getAvg() {
		return avg;
	}
	public void setAvg(String avg) {
		this.avg = avg;
	}
	public String getMax() {
		return max;
	}
	public void setMax(String max) {
		this.max = max;
	}
	public String getMin() {
		return min;
	}
	public void setMin(String min) {
		this.min = min;
	}
	@Override
	public String toString() {
		return "Temp [name=" + name + ", date=" + date + ", avg=" + avg + ", max=" + max + ", min=" + min + "]";
	}
	
}
