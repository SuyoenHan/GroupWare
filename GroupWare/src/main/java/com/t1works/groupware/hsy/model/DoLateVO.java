package com.t1works.groupware.hsy.model;

public class DoLateVO {

	
	private String doLateSysdate;  // 야근일시
	private String doLateTime;     // 야근시간
	private String doLateWhy;      // 야근사유
	private String fk_employeeid;  // 사번
	
	// select용 변수 
	private String endLateTime;   // 야근이 끝난 시간     
	private String overnightPay;  // 야근 수당
	
	public String getDoLateSysdate() {
		return doLateSysdate;
	}
	public void setDoLateSysdate(String doLateSysdate) {
		this.doLateSysdate = doLateSysdate;
	}
	
	public String getDoLateTime() {
		return doLateTime;
	}
	public void setDoLateTime(String doLateTime) {
		this.doLateTime = doLateTime;
	}
	
	public String getDoLateWhy() {
		return doLateWhy;
	}
	public void setDoLateWhy(String doLateWhy) {
		this.doLateWhy = doLateWhy;
	}
	
	public String getFk_employeeid() {
		return fk_employeeid;
	}
	public void setFk_employeeid(String fk_employeeid) {
		this.fk_employeeid = fk_employeeid;
	}
	
	public String getEndLateTime() {
		return endLateTime;
	}
	public void setEndLateTime(String endLateTime) {
		this.endLateTime = endLateTime;
	}
	
	public String getOvernightPay() {
		return overnightPay;
	}
	public void setOvernightPay(String overnightPay) {
		this.overnightPay = overnightPay;
	}

	
	
}
