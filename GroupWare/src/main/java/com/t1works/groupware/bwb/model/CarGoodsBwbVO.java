package com.t1works.groupware.bwb.model;

public class CarGoodsBwbVO {
	
	private int rscno; // 차량 예약번호
	private String rcdate;
	private String rctime;
	private String carname;
	private String rdestination;
	private String rcpeople;
	private String rcsubject;
	private String name; // 직원명
	private int rsgno; // 사무용품 예약번호
	private String fk_employeeid; // 사번
	private String rgdate; // 예약날짜
	private String rgtime; // 예약 시간
	private int gstatus; // 승인 여부
	private String goodsname; // 사무용품명
	private String rgsubject; // 용도
	
	
	
	public int getRscno() {
		return rscno;
	}
	public void setRscno(int rscno) {
		this.rscno = rscno;
	}
	public String getRcdate() {
		return rcdate;
	}
	public void setRcdate(String rcdate) {
		this.rcdate = rcdate;
	}
	public String getRctime() {
		return rctime;
	}
	public void setRctime(String rctime) {
		this.rctime = rctime;
	}
	public String getCarname() {
		return carname;
	}
	public void setCarname(String carname) {
		this.carname = carname;
	}
	public String getRdestination() {
		return rdestination;
	}
	public void setRdestination(String rdestination) {
		this.rdestination = rdestination;
	}
	public String getRcpeople() {
		return rcpeople;
	}
	public void setRcpeople(String rcpeople) {
		this.rcpeople = rcpeople;
	}
	public String getRcsubject() {
		return rcsubject;
	}
	public void setRcsubject(String rcsubject) {
		this.rcsubject = rcsubject;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRsgno() {
		return rsgno;
	}
	public void setRsgno(int rsgno) {
		this.rsgno = rsgno;
	}
	public String getFk_employeeid() {
		return fk_employeeid;
	}
	public void setFk_employeeid(String fk_employeeid) {
		this.fk_employeeid = fk_employeeid;
	}
	public String getRgdate() {
		return rgdate;
	}
	public void setRgdate(String rgdate) {
		this.rgdate = rgdate;
	}
	public String getRgtime() {
		return rgtime;
	}
	public void setRgtime(String rgtime) {
		this.rgtime = rgtime;
	}
	public int getGstatus() {
		return gstatus;
	}
	public void setGstatus(int gstatus) {
		this.gstatus = gstatus;
	}
	public String getGoodsname() {
		return goodsname;
	}
	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}
	public String getRgsubject() {
		return rgsubject;
	}
	public void setRgsubject(String rgsubject) {
		this.rgsubject = rgsubject;
	}


	
	
	
}
