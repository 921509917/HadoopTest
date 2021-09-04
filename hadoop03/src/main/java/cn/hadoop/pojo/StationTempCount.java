package cn.hadoop.pojo;

public class StationTempCount {
	private String name;
	private int cold;
	private int livable;
	private int hot;
	public String getName() { 
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCold() {
		return cold;
	}
	public void setCold(int cold) {
		this.cold = cold;
	}
	public int getLivable() {
		return livable;
	}
	public void setLivable(int livable) {
		this.livable = livable;
	}
	public int getHot() {
		return hot;
	}
	public void setHot(int hot) {
		this.hot = hot;
	}
	
	
}
