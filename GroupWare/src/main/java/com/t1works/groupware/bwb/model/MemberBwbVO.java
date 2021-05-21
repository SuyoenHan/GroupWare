package com.t1works.groupware.bwb.model;

public class MemberBwbVO {
	
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
	private String managerid;  	   // 직속상사사번
	private String employeeimg;    // 직원이미지
	
	
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
	
	public String getManagerid() {
		return managerid;
	}
	
	public void setManagerid(String managerid) {
		this.managerid = managerid;
	}
	
	public String getEmployeeimg() {
		return employeeimg;
	}
	
	public void setEmployeeimg(String employeeimg) {
		this.employeeimg = employeeimg;
	}

}
