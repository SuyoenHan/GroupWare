package com.t1works.groupware.ody.model;

public class ScalCategoryOdyVO {
	private int scno; // 캘린더 소분류 번호
	private int fk_bcno; // 캘린더 대분류 번호
	private String scname; // 캘린더 소분류명
	private String fk_employeeid; // 사번
	
	
	public int getScno() {
		return scno;
	}
	public void setScno(int scno) {
		this.scno = scno;
	}
	public int getFk_bcno() {
		return fk_bcno;
	}
	public void setFk_bcno(int fk_bcno) {
		this.fk_bcno = fk_bcno;
	}
	public String getScname() {
		return scname;
	}
	public void setScname(String scname) {
		this.scname = scname;
	}
	public String getFk_employeeid() {
		return fk_employeeid;
	}
	public void setFk_employeeid(String fk_employeeid) {
		this.fk_employeeid = fk_employeeid;
	}

}
