package com.t1works.groupware.hsy.model;

public class ProductHsyVO {

	private String pNo;        // 상품번호
	private String pName;      // 상품명
	private String miniNo;     // 최소예약인원
	private String maxNo;      // 최대예약인원
	private String nowNo;      // 현재예약인원
	private String startDate;  // 여행시작일
	private String endDate;    // 여행종료일
	private String pImage;     // 상품이미지
	private String price;      // 인당 가격
	
	// select용 변수
	private String period;     // 여행기간 => 0박 0일
	private String remainCnt;   // 최대예약인원-현재예약인원
	
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
	
	public String getMiniNo() {
		return miniNo;
	}
	public void setMiniNo(String miniNo) {
		this.miniNo = miniNo;
	}
	
	public String getMaxNo() {
		return maxNo;
	}
	public void setMaxNo(String maxNo) {
		this.maxNo = maxNo;
	}
	
	public String getNowNo() {
		return nowNo;
	}
	public void setNowNo(String nowNo) {
		this.nowNo = nowNo;
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
	
	public String getpImage() {
		return pImage;
	}
	public void setpImage(String pImage) {
		this.pImage = pImage;
	}
	
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}

	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
	public String getRemainCnt() {
		return remainCnt;
	}
	public void setRemainCnt(String remainCnt) {
		this.remainCnt = remainCnt;
	}
	
	
	
	
}
