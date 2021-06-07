package com.t1works.groupware.hsy.model;

import java.util.List;

public interface InterDepartmentHsyDAO {

	// 모든 부서에 대한 정보 가져오기
	List<DepartmentHsyVO> selectAllDepartment();

	// 직급에 맞는 건당성과금 가져오기
	String getCommissionpercase(String employeeid);

}
