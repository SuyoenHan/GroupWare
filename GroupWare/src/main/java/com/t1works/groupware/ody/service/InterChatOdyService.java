package com.t1works.groupware.ody.service;

import java.util.List;
import java.util.Map;

import com.t1works.groupware.ody.model.MemberOdyVO;



public interface InterChatOdyService {

	// 부서정보 조회
	List<MemberOdyVO> getDepartmentList();

	// 사원정보 조회
	List<MemberOdyVO> getEmployeeList(String employeeid);

	// 이름 가져오기
	Map<String,String> findChatName(String empId);

}
