package com.t1works.groupware.sia.model;

public class ApprovalSiaVO {
	
	private int ano;				// 문서번호
	private String fk_employeeid;	// 사번
	private String anocode;			// 결재구분 (1:일반결재, 2:지출결재, 3:근태결재)
	private String arecipient1;		// 수신자1
	private String arecipient2;		// 수신자2
	private String arecipient3;		// 수신자3
	private String atitle;			// 제목
	private String astatus;			// 결재상태 (0:제출, 1:결재진행중, 2:반려, 3:승인완료)
	private String approvaldate1;	// 수신자1 결재날짜
	private String approvaldate2;	// 수신자2 결재날짜
	private String approvaldate3;	// 수신자3 결재날짜
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
	
	public String getArecipient1() {
		return arecipient1;
	}
	
	public void setArecipient1(String arecipient1) {
		this.arecipient1 = arecipient1;
	}
	
	public String getArecipient2() {
		return arecipient2;
	}

	public void setArecipient2(String arecipient2) {
		this.arecipient2 = arecipient2;
	}

	public String getArecipient3() {
		return arecipient3;
	}

	public void setArecipient3(String arecipient3) {
		this.arecipient3 = arecipient3;
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
	
	public String getApprovaldate1() {
		return approvaldate1;
	}

	public void setApprovaldate1(String approvaldate1) {
		this.approvaldate1 = approvaldate1;
	}

	public String getApprovaldate2() {
		return approvaldate2;
	}

	public void setApprovaldate2(String approvaldate2) {
		this.approvaldate2 = approvaldate2;
	}

	public String getApprovaldate3() {
		return approvaldate3;
	}

	public void setApprovaldate3(String approvaldate3) {
		this.approvaldate3 = approvaldate3;
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
