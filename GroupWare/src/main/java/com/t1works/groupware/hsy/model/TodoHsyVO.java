package com.t1works.groupware.hsy.model;

public class TodoHsyVO {

	private String fk_pNo;		  // 상품번호
	private String fk_dcode;	  // 부서코드
	private String fk_managerid;  // 배정자사번
	private String assignDate;	  // 배정일
	private String startDate; 	  // 시작일
	private String dueDate;		  // 기한일
	private String endDate; 	  // 완료일
	private String fk_employeeid; // 담당자사번
	private String hurryno;		  // 긴급여부(0:일반 1: 긴급)
	private String ingDetail;     // 진행상세여부  0:진행중, -1:보류, 나머지:지연일 (진행중이 아니거나 진행완료인 경우 null)
	
	// select용 변수
	private String pName;     	 // 상품명            
	private String name;		 // 배정자명
	private String nowNo;        // 현재예약인원
	private String maxNo;        // 최대예약인원
	private String miniNo;       // 최소예약인원
	private String rno;          // 순번
	
	// 데이터 가공용 변수
	private String fullState;    // 예약수 충족여부 0:미충족, 1:최소예약인원충족, 2:최대예약인원충족
	
	public String getFk_pNo() {
		return fk_pNo;
	}
	public void setFk_pNo(String fk_pNo) {
		this.fk_pNo = fk_pNo;
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
	
	public String getAssignDate() {
		return assignDate;
	}
	public void setAssignDate(String assignDate) {
		this.assignDate = assignDate;
	}
	
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	public String getFk_employeeid() {
		return fk_employeeid;
	}
	public void setFk_employeeid(String fk_employeeid) {
		this.fk_employeeid = fk_employeeid;
	}
	
	public String getHurryno() {
		return hurryno;
	}
	public void setHurryno(String hurryno) {
		this.hurryno = hurryno;
	}
	
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getNowNo() {
		return nowNo;
	}
	public void setNowNo(String nowNo) {
		this.nowNo = nowNo;
	}
	
	public String getMaxNo() {
		return maxNo;
	}
	public void setMaxNo(String maxNo) {
		this.maxNo = maxNo;
	}
	
	public String getRno() {
		return rno;
	}
	public void setRno(String rno) {
		this.rno = rno;
	}
	
	public String getIngDetail() {
		return ingDetail;
	}
	public void setIngDetail(String ingDetail) {
		this.ingDetail = ingDetail;
	}
	
	public String getMiniNo() {
		return miniNo;
	}
	public void setMiniNo(String miniNo) {
		this.miniNo = miniNo;
	}
	
	public String getFullState() {
		return fullState;
	}
	public void setFullState(String fullState) {
		this.fullState = fullState;
	}
	
	
	
}
