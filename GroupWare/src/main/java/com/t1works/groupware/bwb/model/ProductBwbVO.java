package com.t1works.groupware.bwb.model;

public class ProductBwbVO {
	
	private String pNo;        // 상품번호
	private String pName;      // 상품명
	private String miniNo;     // 최소예약인원
	private String maxNo;      // 최대예약인원
	private String nowNo;      // 현재예약인원
	private String startDate;  // 여행시작일
	private String endDate;    // 여행종료일
	private String pImage;     // 상품이미지
	private String price;      // 인당 가격
	
	private String fk_dcode;	  // 부서코드
	private String fk_managerid;  // 담당자사번
	

	private String fk_employeeid; // 직원사번
	private String assignDate;    // 배정일
	private String dueDate;       // 기한일
	private String hurryno;       // 긴급여부
	private String rno;			  // 순번
	private String ingdetail;     // 진행상세여부  0:진행중, -1:보류, 나머지:지연일 (진행중이 아니거나 진행완료인 경우 null)
	private String name;		  // 담당자명	
	private String employeeName;  // 배정자명
	
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
	
	public String getPrice() {
		return price;
	}
	
	public void setPrice(String price) {
		this.price = price;
	}
	
	public String getFk_dcode() {
		return fk_dcode;
	}
	
	public void setFk_dcode(String fk_dcode) {
		this.fk_dcode = fk_dcode;
	}
	
	public String getFk_managerid() {
		return fk_managerid;
	}
	
	public void setFk_managerid(String fk_managerid) {
		this.fk_managerid = fk_managerid;
	}
	
	public String getFk_employeeid() {
		return fk_employeeid;
	}
	
	public void setFk_employeeid(String fk_employeeid) {
		this.fk_employeeid = fk_employeeid;
	}
	
	public String getAssignDate() {
		return assignDate;
	}
	
	public void setAssignDate(String assignDate) {
		this.assignDate = assignDate;
	}
	
	public String getDueDate() {
		return dueDate;
	}
	
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	
	public String getHurryno() {
		return hurryno;
	}
	
	public void setHurryno(String hurryno) {
		this.hurryno = hurryno;
	}
	
	public String getRno() {
		return rno;
	}

	public void setRno(String rno) {
		this.rno = rno;
	}

	public String getIngdetail() {
		return ingdetail;
	}

	public void setIngdetail(String ingdetail) {
		this.ingdetail = ingdetail;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	
}
