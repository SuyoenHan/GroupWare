package com.t1works.groupware.kdn.model;

public class EmailKdnVO {
	
	private String seq;    			// 메일번호
	private String fk_employeeid ;  // 사번
	private String senderAddress;   // 발신자메일주소
	private String senderName;      // 발신자명
	private String receiverAddress; // 수신자메일주소
	private String ccAddress;       // 참조메일주소
	private String subject;         // 제목
	private String content;         // 내용
	private String sendingDate;     // 발신일시
	private String importanceMark;  // 중요표시(0:not starred(중요x), 1:starred(중요o))
	private String saveSentMail;    // 보낸메일함저장여부(0:저장안함, 1:저장)
	private String imageFile;       // 첨부이미지
	private String moveToTrash;     // 삭제여부(0:삭제안함, 1:삭제함(휴지통 이동))
	private String readStatus;      // 읽음여부(0:읽지않음, 1:읽음)
	
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getFk_employeeid() {
		return fk_employeeid;
	}
	public void setFk_employeeid(String fk_employeeid) {
		this.fk_employeeid = fk_employeeid;
	}
	public String getSenderAddress() {
		return senderAddress;
	}
	public void setSenderAddress(String senderAddress) {
		this.senderAddress = senderAddress;
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public String getReceiverAddress() {
		return receiverAddress;
	}
	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}
	public String getCcAddress() {
		return ccAddress;
	}
	public void setCcAddress(String ccAddress) {
		this.ccAddress = ccAddress;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSendingDate() {
		return sendingDate;
	}
	public void setSendingDate(String sendingDate) {
		this.sendingDate = sendingDate;
	}
	public String getImportanceMark() {
		return importanceMark;
	}
	public void setImportanceMark(String importanceMark) {
		this.importanceMark = importanceMark;
	}
	public String getSaveSentMail() {
		return saveSentMail;
	}
	public void setSaveSentMail(String saveSentMail) {
		this.saveSentMail = saveSentMail;
	}
	public String getImageFile() {
		return imageFile;
	}
	public void setImageFile(String imageFile) {
		this.imageFile = imageFile;
	}
	public String getMoveToTrash() {
		return moveToTrash;
	}
	public void setMoveToTrash(String moveToTrash) {
		this.moveToTrash = moveToTrash;
	}
	public String getReadStatus() {
		return readStatus;
	}
	public void setReadStatus(String readStatus) {
		this.readStatus = readStatus;
	}

	
	
	
	
	
	
}
