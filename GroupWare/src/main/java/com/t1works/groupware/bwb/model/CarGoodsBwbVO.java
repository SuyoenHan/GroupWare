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
	private int fk_gno; // 사무용품 번호
	private String fk_employeeid; // 사번
	private String rgsubject; // 목적
	private String rgdate; // 예약날짜
	private int rgtime; // 예약 시간
	private int gstatus; // 승인 여부
	private String rdname; // 신청 부서
	private String goodsname; // 사무용품명
	private String rsubject; // 용도
	private String rdate; // 날짜
	private int rtime; // 시간
	private int rstatus; // 상태
	private String roomname; //회의실 이름
	
	
	
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
	public String getRdname() {
		return rdname;
	}
	public void setRdname(String rdname) {
		this.rdname = rdname;
	}
	public String getGoodsname() {
		return goodsname;
	}
	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}
	public String getRsubject() {
		return rsubject;
	}
	public void setRsubject(String rsubject) {
		this.rsubject = rsubject;
	}
	public String getRdate() {
		return rdate;
	}
	public void setRdate(String rdate) {
		this.rdate = rdate;
	}
	public int getRtime() {
		return rtime;
	}
	public void setRtime(int rtime) {
		this.rtime = rtime;
	}
	public int getRstatus() {
		return rstatus;
	}
	public void setRstatus(int rstatus) {
		this.rstatus = rstatus;
	}
	public String getRoomname() {
		return roomname;
	}
	public void setRoomname(String roomname) {
		this.roomname = roomname;
	}
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
	
	
}
