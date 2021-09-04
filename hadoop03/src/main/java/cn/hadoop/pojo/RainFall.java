package cn.hadoop.pojo;

public class RainFall {
	private String name;
	private String date;
	private String rpcp;
	private String sndp; 
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
	public String getRpcp() {
		return rpcp;
	}
	public void setRpcp(String rpcp) {
		this.rpcp = rpcp;
	}
	public String getSndp() {
		return sndp;
	}
	public void setSndp(String sndp) {
		this.sndp = sndp;
	}
	@Override
	public String toString() {
		return "RainFall [name=" + name + ", date=" + date + ", rpcp=" + rpcp + ", sndp=" + sndp + "]";
	}
	
}
