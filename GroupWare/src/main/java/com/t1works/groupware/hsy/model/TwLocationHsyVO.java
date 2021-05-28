package com.t1works.groupware.hsy.model;

public class TwLocationHsyVO {

	private String storeCode;     // 지점코드
	private String storeName;	  // 지점명
	private String storeAddress;  // 지점주소
	private String storeImg;      // 지점 소개이미지
	private String storePhone;    // 지점전화번호
	private String lat;           // 위도
	private String lng;           // 경도
	private String zindex;        // zindex
	
	
	public String getStoreCode() {
		return storeCode;
	}
	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}
	
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	
	public String getStoreAddress() {
		return storeAddress;
	}
	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}
	
	public String getStoreImg() {
		return storeImg;
	}
	public void setStoreImg(String storeImg) {
		this.storeImg = storeImg;
	}
	
	public String getStorePhone() {
		return storePhone;
	}
	public void setStorePhone(String storePhone) {
		this.storePhone = storePhone;
	}
	
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	
	public String getZindex() {
		return zindex;
	}
	public void setZindex(String zindex) {
		this.zindex = zindex;
	}
	
}
