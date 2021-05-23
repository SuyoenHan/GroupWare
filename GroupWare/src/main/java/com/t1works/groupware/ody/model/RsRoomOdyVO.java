package com.t1works.groupware.ody.model;

public class RsRoomOdyVO {

	private int rsroomno;// 회의실 예약번호
	private int fk_roomno; // 회의실번호
	private String fk_employeeid;// 사번
	private String rsubject; // 용도
	private String rdate; // 날짜
	private int rtime; // 시간
	private int rstatus; // 상태
	private String name; // 직원명
	private String rdepartment; // 직원부서명
	
	public int getRsroomno() {
		return rsroomno;
	}
	public void setRsroomno(int rsroomno) {
		this.rsroomno = rsroomno;
	}
	public int getFk_roomno() {
		return fk_roomno;
	}
	public void setFk_roomno(int fk_roomno) {
		this.fk_roomno = fk_roomno;
	}
	public String getFk_employeeid() {
		return fk_employeeid;
	}
	public void setFk_employeeid(String fk_employeeid) {
		this.fk_employeeid = fk_employeeid;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRdepartment() {
		return rdepartment;
	}
	public void setRdepartment(String rdepartment) {
		this.rdepartment = rdepartment;
	}
	
}
