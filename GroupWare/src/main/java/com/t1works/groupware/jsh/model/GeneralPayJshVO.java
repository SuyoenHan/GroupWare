package com.t1works.groupware.jsh.model;

public class GeneralPayJshVO {

	private String ncat;     //일반결재카테고리 (1:회의록, 2:위임장, 3:외부공문, 4:협조공문)
	private int fk_ano;      // 문서번호
	private String  ncatname; // 일반결재카테고리명 (1:회의록, 2:위임장, 3:외부공문, 4:협조공문)
	
	
	public String getNcat() {
		return ncat;
	}
	public void setNcat(String ncat) {
		this.ncat = ncat;
	}
	public int getFk_ano() {
		return fk_ano;
	}
	public void setFk_ano(int fk_ano) {
		this.fk_ano = fk_ano;
	}
	public String getNcatname() {
		return ncatname;
	}
	public void setNcatname(String ncatname) {
		this.ncatname = ncatname;
	}
	
	
	
}
