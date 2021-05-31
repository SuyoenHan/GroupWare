package com.t1works.groupware.sia.model;

public class WiimjangSiaVO {
	
	private int wno;			// 순번
	private String fk_ncat;		// 일반결재카테고리
	private int fk_ano;			// 문서번호
	private String fk_wiimdate;	// 위임기간
	
	
	public int getWno() {
		return wno;
	}
	
	public void setWno(int wno) {
		this.wno = wno;
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
	
	public String getFk_wiimdate() {
		return fk_wiimdate;
	}
	
	public void setFk_wiimdate(String fk_wiimdate) {
		this.fk_wiimdate = fk_wiimdate;
	}
	
}
