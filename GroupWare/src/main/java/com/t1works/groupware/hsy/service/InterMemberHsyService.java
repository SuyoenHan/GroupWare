package com.t1works.groupware.hsy.service;

import java.util.List;
import java.util.Map;

import com.t1works.groupware.hsy.model.DepartmentHsyVO;
import com.t1works.groupware.hsy.model.MemberHsyVO;

public interface InterMemberHsyService {

	// 모든 부서에 대한 정보 가져오기
	List<DepartmentHsyVO> selectAllDepartment();

	// 모든 직원 정보 가져오기
	List<MemberHsyVO> selectAllMember();

	// 주소록 직원목록 총페이지
	int selectTotalPage(Map<String, String> paraMap);

	// 페이징 직원목록 가져오기
	List<MemberHsyVO> selectPagingMember(Map<String, String> paraMap);

	// 사번에 해당하는 직원정보 가져오기
	MemberHsyVO employeeInfoAjaxHsy(String employeeid);

}
