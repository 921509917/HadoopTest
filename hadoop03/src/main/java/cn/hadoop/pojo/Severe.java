package cn.hadoop.pojo;

public class Severe {
	private String name;
	private String fog;			//雾
	private String rain;		//雨
	private String snow;	 	//雪
	private String hail;		//冰雹
	private String thunder;		//打雷
	private String tornado;		//龙卷风

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFog() {
		return fog;
	}
	public void setFog(String fog) {
		this.fog = fog;
	}
	public String getRain() {
		return rain;
	}
	public void setRain(String rain) {
		this.rain = rain;
	}
	public String getSnow() {
		return snow;
	}
	public void setSnow(String snow) {
		this.snow = snow;
	}
	public String getHail() {
		return hail;
	}
	public void setHail(String hail) {
		this.hail = hail;
	}
	public String getThunder() {
		return thunder;
	}
	public void setThunder(String thunder) {
		this.thunder = thunder;
	}
	public String getTornado() {
		return tornado;
	}
	public void setTornado(String tornado) {
		this.tornado = tornado;
	}
	@Override
	public String toString() {
		return "Severe [name=" + name + ", fog=" + fog + ", rain=" + rain + ", snow=" + snow + ", hail=" + hail
				+ ", thunder=" + thunder + ", tornado=" + tornado + "]";
	}

}
