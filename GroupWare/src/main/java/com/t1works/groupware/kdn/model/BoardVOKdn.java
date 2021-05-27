package com.t1works.groupware.kdn.model;

public class BoardVOKdn {

	private String seq;          // 글번호 
	   private String fk_employeeid;    // 사용자ID
	   private String name;         // 글쓴이 
	   private String subject;      // 글제목
	   private String content;      // 글내용 
	   private String pw;           // 글암호
	   private String readCount;    // 글조회수
	   private String regDate;      // 글쓴시간
	   private String status;       // 글삭제여부   1:사용가능한 글,  0:삭제된글 
	   
	   
	   private String fk_categnum;		// 카테고리번호 
	   
	   public BoardVOKdn() {}
	   
	   public BoardVOKdn(String seq, String fk_employeeid, String name, String subject, String content, String pw, String readCount,
			String regDate, String status, String fk_categnum) {
		super();
		this.seq = seq;
		this.fk_employeeid = fk_employeeid;
		this.name = name;
		this.subject = subject;
		this.content = content;
		this.pw = pw;
		this.readCount = readCount;
		this.regDate = regDate;
		this.status = status;
		this.fk_categnum = fk_categnum;
	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getReadCount() {
		return readCount;
	}

	public void setReadCount(String readCount) {
		this.readCount = readCount;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFk_categnum() {
		return fk_categnum;
	}

	public void setFk_categnum(String fk_categnum) {
		this.fk_categnum = fk_categnum;
	}

	
}
