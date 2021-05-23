package com.t1works.groupware.ody.model;

public class RsGoodsOdyVO {

	private int rsgno; // 사무용품 예약번호
	private int fk_gno; // 사무용품 번호
	private String fk_employeeid; // 사번
	private String name; // 직원명
	private String rgdepartment; // 신청부서 
	private String rgsubject; // 목적
	private String rgdate; // 예약날짜
	private int rgtime; // 예약 시간
	private int gstatus; // 반납 여부?
	
	public int getRsgno() {
		return rsgno;
	}
	public void setRsgno(int rsgno) {
		this.rsgno = rsgno;
	}
	public int getFk_gno() {
		return fk_gno;
	}
	public void setFk_gno(int fk_gno) {
		this.fk_gno = fk_gno;
	}
	public String getFk_employeeid() {
		return fk_employeeid;
	}
	public void setFk_employeeid(String fk_employeeid) {
		this.fk_employeeid = fk_employeeid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRgdepartment() {
		return rgdepartment;
	}
	public void setRgdepartment(String rgdepartment) {
		this.rgdepartment = rgdepartment;
	}
	public String getRgsubject() {
		return rgsubject;
	}
	public void setRgsubject(String rgsubject) {
		this.rgsubject = rgsubject;
	}
	public String getRgdate() {
		return rgdate;
	}
	public void setRgdate(String rgdate) {
		this.rgdate = rgdate;
	}
	public int getRgtime() {
		return rgtime;
	}
	public void setRgtime(int rgtime) {
		this.rgtime = rgtime;
	}
	public int getGstatus() {
		return gstatus;
	}
	public void setGstatus(int gstatus) {
		this.gstatus = gstatus;
	}
	
	
}
