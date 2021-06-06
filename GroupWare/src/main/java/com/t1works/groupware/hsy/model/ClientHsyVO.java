package com.t1works.groupware.hsy.model;

public class ClientHsyVO {

	private String fk_pNo;        // 상품번호
	private String clientmobile;  // 고객연락처
	private String clientname;    // 고객명
	private String cnumber;       // 예약인원수
	private String clientemail;   // 고객이메일
	
	// select용 
	private String pNo;        // 상품번호
	private String pName;      // 상품명
	private String startDate;  // 여행시작일
	private String endDate;    // 여행종료일
	private String price;      // 인당 가격
	private String period;     // 여행기간 => 0박 0일
	
	
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
	
	public String getpNo() {
		return pNo;
	}
	public void setpNo(String pNo) {
		this.pNo = pNo;
	}
	
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	
	public String getClientemail() {
		return clientemail;
	}
	public void setClientemail(String clientemail) {
		this.clientemail = clientemail;
	}
	
	
}
