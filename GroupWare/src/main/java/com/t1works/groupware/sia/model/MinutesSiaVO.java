package com.t1works.groupware.sia.model;

public class MinutesSiaVO {
	
	private int mno;		// 순번
	private String fk_ncat;	// 일반결재카테고리
	private int fk_ano;		// 문서번호
	private String mdate;	// 회의시간
	
	
	public int getMno() {
		return mno;
	}
	
	public void setMno(int mno) {
		this.mno = mno;
	}
	
	public String getFk_ncat() {
		return fk_ncat;
	}
	
	public void setFk_ncat(String fk_ncat) {
		this.fk_ncat = fk_ncat;
	}
	
	public int getFk_ano() {
		return fk_ano;
	}
	
	public void setFk_ano(int fk_ano) {
		this.fk_ano = fk_ano;
	}
	
	public String getMdate() {
		return mdate;
	}
	
	public void setMdate(String mdate) {
		this.mdate = mdate;
	}
	
}
