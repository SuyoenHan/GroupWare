package com.t1works.groupware.ody.model;

public class RoomOdyVO {
	private int roomno; // 회의실번호 
	private String roomname; // 회의실명
	private int cappeople; // 수용인원수
	
	public RoomOdyVO() {} // 기본생성자
	
	public int getRoomno() {
		return roomno;
	}
	public void setRoomno(int roomno) {
		this.roomno = roomno;
	}
	public String getRoomname() {
		return roomname;
	}
	public void setRoomname(String roomname) {
		this.roomname = roomname;
	}
	public int getCappeople() {
		return cappeople;
	}
	public void setCappeople(int cappeople) {
		this.cappeople = cappeople;
	}
}
