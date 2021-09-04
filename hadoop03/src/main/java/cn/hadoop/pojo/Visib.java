package cn.hadoop.pojo;

public class Visib {
	private String name;
	private String date;
	private String visib;
	
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
	public String getVisib() {
		return visib;
	}
	public void setVisib(String visib) {
		this.visib = visib;
	}
	@Override
	public String toString() {
		return "Visib [name=" + name + ", date=" + date + ", visib=" + visib + "]";
	}
	
}
