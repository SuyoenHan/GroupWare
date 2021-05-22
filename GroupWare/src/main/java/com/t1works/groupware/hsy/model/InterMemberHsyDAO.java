package com.t1works.groupware.hsy.model;

import java.util.List;
import java.util.Map;


public interface InterMemberHsyDAO {
	
	// 모든 직원 정보 가져오기
	List<MemberHsyVO> selectAllMember();

	// 검색조건이 있는 직원목록 총페이지
	int selectTotalPage1(Map<String, String> paraMap);

	// 검색조건이 없는 직원목록 총페이지
	int selectTotalPage2(String sizePerPage);

	// 검색조건이 있는 직원목록 가져오기
	List<MemberHsyVO> selectPagingMember1(Map<String, String> paraMap);

	// 검색조건이 없는 직원목록 가져오기
	List<MemberHsyVO> selectPagingMember2(Map<String, String> paraMap2);

	// 사번에 해당하는 직원정보 가져오기
	MemberHsyVO employeeInfoAjaxHsy(String employeeid);



	
	

}
