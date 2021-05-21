package com.t1works.groupware.hsy.model;

import java.util.List;

public interface InterDepartmentHsyDAO {

	// 모든 부서에 대한 정보 가져오기
	List<DepartmentHsyVO> selectAllDepartment();

}
