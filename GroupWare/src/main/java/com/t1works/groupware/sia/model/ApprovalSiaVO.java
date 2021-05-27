package com.t1works.groupware.sia.model;

public class ApprovalSiaVO {
	
	private int ano;				// 문서번호
	private String fk_employeeid;	// 사번
	private String anocode;			// 결재구분 (1:일반결재, 2:지출결재, 3:근태결재)
	private String arecipient;		// 수신자    
	private String atitle;			// 제목
	private String astatus;			// 결재상태 (0:제출, 1:결재진행중, 2:반려, 3:승인완료)
	private String acontent;		// 글내용
	private String asdate;			// 기안일
	private String afile;			// 첨부파일
	private String apaper;			// 문서상태 (0:수신, 1:발신, 2:임시보관)
	
	private String ncat;		// 일반결재카테고리 (1:회의록, 2:위임장, 3:외부공문, 4:협조공문)
	private int fk_ano;			// 문서번호
	private String ncatname;	// 일반결재카테고리명 (1:회의록, 2:위임장, 3:외부공문, 4:협조공문)
	

	public int getAno() {
		return ano;
	}
	
	public void setAno(int ano) {
		this.ano = ano;
	}
	
	public String getFk_employeeid() {
		return fk_employeeid;
	}
	
	public void setFk_employeeid(String fk_employeeid) {
		this.fk_employeeid = fk_employeeid;
	}
	
	public String getAnocode() {
		return anocode;
	}
	
	public void setAnocode(String anocode) {
		this.anocode = anocode;
	}
	
	public String getArecipient() {
		return arecipient;
	}
	
	public void setArecipient(String arecipient) {
		this.arecipient = arecipient;
	}
	
	public String getAtitle() {
		return atitle;
	}
	
	public void setAtitle(String atitle) {
		this.atitle = atitle;
	}
	
	public String getAstatus() {
		return astatus;
	}
	
	public void setAstatus(String astatus) {
		this.astatus = astatus;
	}
	
	public String getAcontent() {
		return acontent;
	}
	
	public void setAcontent(String acontent) {
		this.acontent = acontent;
	}
	
	public String getAsdate() {
		return asdate;
	}
	
	public void setAsdate(String asdate) {
		this.asdate = asdate;
	}
	
	public String getAfile() {
		return afile;
	}
	
	public void setAfile(String afile) {
		this.afile = afile;
	}
	
	public String getApaper() {
		return apaper;
	}
	
	public void setApaper(String apaper) {
		this.apaper = apaper;
	}

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
