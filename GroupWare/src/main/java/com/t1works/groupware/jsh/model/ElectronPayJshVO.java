package com.t1works.groupware.jsh.model;

public class ElectronPayJshVO {

	private String  ano; 						  // 문서번호
	private String  fk_employeeid;    // 사번
	private String  anocode;      		  //결재구분 (1:일반결재, 2:지출결재, 3:근태결재)
	private String  arecipient1;           // 수신자    
	private String  atitle;      			  //제목
	private String  astatus;                 // default '0' not null     -- 결재상태 (0:제출, 1:결재진행중, 2:반려, 3:승인완료)
	private String  acontent;      		  // 글내용
	private String  asdate;                // 기안일
	private String  afile;                    //  첨부파일
	private String  apaper;       	 	 //  문서상태 (0:수신, 1:발신, 2:임시보관)
	
    //////일반결재문서 //////
	private String ncat;     //일반결재카테고리 (1:회의록, 2:위임장, 3:외부공문, 4:협조공문)
	private String fk_ano;      // 문서번호
	private String  ncatname; // 일반결재카테고리명 (1:회의록, 2:위임장, 3:외부공문, 4:협조공문)
	////////
	
	//회의록
	 private String mno;       //순번
	 private String mdate;  //회의시간
	 
	//위임장
	 private String wno; // 순번
	 private String fk_wiimdate; //위임기간
	 
	 //외부공문
	 private String eno; //순번
	 
	 //협조공문
	 private String cno;        //순번
	 private String comname; // 타회사명
	 
	 
	
	//Member
	private String employeeid;     // 사번
	private String fk_dcode; 	   // 부서코드
	private String fk_pcode;	   // 직급코드
	private String name;           // 직원명
	private String passwd;		   // 비밀번호
	private String email;   	   // 회사이메일
	private String mobile;   	   // 연락처
	private String cmobile;  	   // 회사연락처
	private String jubun;   	   // 주민번호
	private String hiredate;  	   // 입사일자
	private String status;  	   // 재직상태 (0:재직중 , 1:퇴사)
	
	private String employeeimg;    // 직원이미지
	
//////
	   
/////// 부서
	   private String dcode;        // 부서코드
	   private String dname;		 // 부서명
	   private String managerid;	 // 부서장사번
	   private String duty;  		 // 직무
		
//////
	 //직급
	   private String pname;  // 직급명
	   
	   
/////////////전자결재 의견작성
	   private String ono;      // 의견순번
	   //private String fk_ano;   // 문서번호
	  // private String fk_employeeid;    // 작성자
	   private String ocontent; // 의견내용
	   private String odate;    // 작성일자
	   
	   
	   
	   
	   
	   
	public String getAno() {
		return ano;
	}
	public void setAno(String ano) {
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
	
	
	
	
	////// MEMBER
	
	public String getEmployeeid() {
		return employeeid;
	}
	public void setEmployeeid(String employeeid) {
		this.employeeid = employeeid;
	}
	public String getFk_dcode() {
		return fk_dcode;
	}
	public void setFk_dcode(String fk_dcode) {
		this.fk_dcode = fk_dcode;
	}
	public String getFk_pcode() {
		return fk_pcode;
	}
	public void setFk_pcode(String fk_pcode) {
		this.fk_pcode = fk_pcode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getCmobile() {
		return cmobile;
	}
	public void setCmobile(String cmobile) {
		this.cmobile = cmobile;
	}
	public String getJubun() {
		return jubun;
	}
	public void setJubun(String jubun) {
		this.jubun = jubun;
	}
	public String getHiredate() {
		return hiredate;
	}
	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getEmployeeimg() {
		return employeeimg;
	}
	public void setEmployeeimg(String employeeimg) {
		this.employeeimg = employeeimg;
	}
	
	////Member///////
	
	
	////일반결재문서
	public String getNcat() {
		return ncat;
	}
	public void setNcat(String ncat) {
		this.ncat = ncat;
	}
	public String getFk_ano() {
		return fk_ano;
	}
	public void setFk_ano(String fk_ano) {
		this.fk_ano = fk_ano;
	}
	public String getNcatname() {
		return ncatname;
	}
	public void setNcatname(String ncatname) {
		this.ncatname = ncatname;
	}
	
	////부서 
	
	public String getDcode() {
		return dcode;
	}
	public void setDcode(String dcode) {
		this.dcode = dcode;
	}
	
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	
	
	public String getManagerid() {
		return managerid;
	}
	public void setManagerid(String managerid) {
		this.managerid = managerid;
	}
	
	public String getDuty() {
		return duty;
	}
	public void setDuty(String duty) {
		this.duty = duty;
	}

	//직급
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	

	
	public String getMno() {
		return mno;
	}
	
	public void setMno(String mno) {
		this.mno = mno;
	}
	public String getMdate() {
		return mdate;
	}
	public void setMdate(String mdate) {
		this.mdate = mdate;
	}
	public String getWno() {
		return wno;
	}
	public void setWno(String wno) {
		this.wno = wno;
	}
	public String getFk_wiimdate() {
		return fk_wiimdate;
	}
	public void setFk_wiimdate(String fk_wiimdate) {
		this.fk_wiimdate = fk_wiimdate;
	}
	public String getEno() {
		return eno;
	}
	public void setEno(String eno) {
		this.eno = eno;
	}
	public String getCno() {
		return cno;
	}
	public void setCno(String cno) {
		this.cno = cno;
	}
	public String getComname() {
		return comname;
	}
	public void setComname(String comname) {
		this.comname = comname;
	
	}
	
	
	//전자결재 의견작성
	public String getOno() {
		return ono;
	}
	public void setOno(String ono) {
		this.ono = ono;
	}
	
	public String getOcontent() {
		return ocontent;
	}
	public void setOcontent(String ocontent) {
		this.ocontent = ocontent;
	}
	public String getOdate() {
		return odate;
	}
	public void setOdate(String odate) {
		this.odate = odate;
	}
	
}
