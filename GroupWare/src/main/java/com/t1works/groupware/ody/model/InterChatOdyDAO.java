package com.t1works.groupware.ody.model;

import java.util.List;



public interface InterChatOdyDAO {

	// 부서 정보 조회
	List<MemberOdyVO> getDepartmentList();

	// 사원 정보 조회
	List<MemberOdyVO> getEmployeeList(String employeeid);

}
