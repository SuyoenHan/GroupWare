package com.t1works.groupware.ody.model;

public class RsCarOdyVO {
	
	private int rscno;            // 차량 예약번호
	private int fk_cno;           // 차량번호
	private String fk_employeeid; // 사번
	private String name;          // 직원명
	private String rdestination;  // 도착지
	private String rcpeople;      // 탑승자
	private String rcsubject;     // 목적
	private String rcdate;        // 예약날짜
	private int rctime;           // 예약시간
	private int cstatus;          // 예약 상태
	private String carname;       // 차량명
	
	public int getRscno() {
		return rscno;
	}
	public void setRscno(int rscno) {
		this.rscno = rscno;
	}
	public int getFk_cno() {
		return fk_cno;
	}
	public void setFk_cno(int fk_cno) {
		this.fk_cno = fk_cno;
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
	public String getRcdate() {
		return rcdate;
	}
	public void setRcdate(String rcdate) {
		this.rcdate = rcdate;
	}
	public int getRctime() {
		return rctime;
	}
	public void setRctime(int rctime) {
		this.rctime = rctime;
	}
	public int getCstatus() {
		return cstatus;
	}
	public void setCstatus(int cstatus) {
		this.cstatus = cstatus;
	}
	public String getCarname() {
		return carname;
	}
	public void setCarname(String carname) {
		this.carname = carname;
	}
	
}
