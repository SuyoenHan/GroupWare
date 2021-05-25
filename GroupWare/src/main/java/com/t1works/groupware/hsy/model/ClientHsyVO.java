package com.t1works.groupware.hsy.model;

public class ClientHsyVO {

	private String fk_pNo;        // 상품번호
	private String clientmobile;  // 고객연락처
	private String clientname;    // 고객명
	private String cnumber;       // 예약인원수
	
	public String getFk_pNo() {
		return fk_pNo;
	}
	public void setFk_pNo(String fk_pNo) {
		this.fk_pNo = fk_pNo;
	}
	
	public String getClientmobile() {
		return clientmobile;
	}
	public void setClientmobile(String clientmobile) {
		this.clientmobile = clientmobile;
	}
	
	public String getClientname() {
		return clientname;
	}
	public void setClientname(String clientname) {
		this.clientname = clientname;
	}
	
	public String getCnumber() {
		return cnumber;
	}
	public void setCnumber(String cnumber) {
		this.cnumber = cnumber;
	}
	
	
	
	
}
