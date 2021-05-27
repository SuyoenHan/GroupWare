package com.t1works.groupware.ody.model;

public class BcalCategoryVO {
	private int bcno; // 대분류 번호 number(8) not null
	private String bcname; // 대분류명
	
	public int getBcno() {
		return bcno;
	}
	public void setBcno(int bcno) {
		this.bcno = bcno;
	}
	public String getBcname() {
		return bcname;
	}
	public void setBcname(String bcname) {
		this.bcname = bcname;
	}
}
