package com.t1works.groupware.ody.model;

public class ScheduleOdyVO {
	private int sdno; // 일정관리 번호
	private String startdate; // 시작일
	private String enddate; // 종료일
	private String subject; // 제목
	private String color; // 색상
	private String place; // 장소
	private String joinEmp; // 공유자
	private String content; // 내용
	private int fk_scno; // 캘린더 소분류 번호
	private int fk_bcno;// 캘린더 대분류 번호
	private String fk_employeeid; // 사번
	
	
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getJoinEmp() {
		return joinEmp;
	}
	public void setJoinEmp(String joinEmp) {
		this.joinEmp = joinEmp;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getFk_scno() {
		return fk_scno;
	}
	public void setFk_scno(int fk_scno) {
		this.fk_scno = fk_scno;
	}
	public String getFk_employeeid() {
		return fk_employeeid;
	}
	public void setFk_employeeid(String fk_employeeid) {
		this.fk_employeeid = fk_employeeid;
	}
	public int getSdno() {
		return sdno;
	}
	public void setSdno(int sdno) {
		this.sdno = sdno;
	}
	public int getFk_bcno() {
		return fk_bcno;
	}
	public void setFk_bcno(int fk_bcno) {
		this.fk_bcno = fk_bcno;
	}
}
