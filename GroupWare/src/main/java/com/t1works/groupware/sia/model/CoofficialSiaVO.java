package com.t1works.groupware.sia.model;

public class CoofficialSiaVO {
	
	private int cno;		// 순번
	private String fk_ncat;	// 일반결재카테고리
	private int fk_ano;		// 문서번호
	private String comname; // 타회사명
	
	
	public int getCno() {
		return cno;
	}
	
	public void setCno(int cno) {
		this.cno = cno;
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
	
	public String getComname() {
		return comname;
	}
	
	public void setComname(String comname) {
		this.comname = comname;
	}
	
}
