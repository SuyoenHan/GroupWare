package com.t1works.groupware.ody.model;

public class CarOdyVO {
	private int cno; //차량 번호
	private String carname; // 차량명
	private String carlicense; // 차량 번호판
	
	public int getCno() {
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
	}
	public String getCarname() {
		return carname;
	}
	public void setCarname(String carname) {
		this.carname = carname;
	}
	public String getCarlicense() {
		return carlicense;
	}
	public void setCarlicense(String carlicense) {
		this.carlicense = carlicense;
	}
}
