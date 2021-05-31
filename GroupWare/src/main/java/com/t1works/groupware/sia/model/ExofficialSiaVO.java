package com.t1works.groupware.sia.model;

public class ExofficialSiaVO {
	
	private int eno;		// 순번
	private String fk_ncat;	// 일반결재카테고리
	private int fk_ano;		// 문서번호
	
	
	public int getEno() {
		return eno;
	}
	
	public void setEno(int eno) {
		this.eno = eno;
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
	
}
