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
	
	// pcode에 따른 연차수 가져오기
	String selectOffCnt(String pcode);
	
	// 직원정보등록하기 ==> 추후 ajax이용할때, 코드 변경예정...
	int registerOne(MemberBwbVO mvo);
	
	// 등록한 직원의 fk_dcode를 통해 managerid 알아오기
	String selectMangerid(String dcode);
	
	// 부서명 가져오기
	String selectdname(String dcode);
	
	// 직위 가져오기
	String selectpname(String pcode);
	
	// 비밀번호 변경하기
	int updatePasswd(Map<String, String> paraMap);
	
	// 부장을 제외한 직원 ID를 가져오기
	List<MemberBwbVO> selectMemberList(String dcode);
	

}
