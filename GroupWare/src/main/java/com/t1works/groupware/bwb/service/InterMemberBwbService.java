package com.t1works.groupware.bwb.service;

import java.util.List;
import java.util.Map;

import com.t1works.groupware.bwb.model.MemberBwbVO;

public interface InterMemberBwbService {
	
	// 모든직위 가져오기
	List<MemberBwbVO> selectPositionList();
	
	// 소속에 따른 직무설명 가져오기
	String selectDuty(String dname);
	
	// pname과 dname을 통해 pcode,dcode 가져오기.
	Map<String,String> selectPDcode(Map<String, String> paraMap);
	
	// 회원정보 업데이트하기
	int updateOneInfo(MemberBwbVO mvo);
	
	

}
