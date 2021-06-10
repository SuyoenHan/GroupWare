package com.t1works.groupware.ody.model;

import java.util.List;
import java.util.Map;



public interface InterChatOdyDAO {

	// 부서 정보 조회
	List<MemberOdyVO> getDepartmentList();

	// 사원 정보 조회
	List<MemberOdyVO> getEmployeeList(String employeeid);

	// 이름 가져오기
	Map<String,String> findChatName(String empId);

}
