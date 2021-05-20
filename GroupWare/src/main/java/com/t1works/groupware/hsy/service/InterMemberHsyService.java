package com.t1works.groupware.hsy.service;

import java.util.List;

import com.t1works.groupware.hsy.model.DepartmentHsyVO;
import com.t1works.groupware.hsy.model.MemberHsyVO;

public interface InterMemberHsyService {

	// 모든 부서에 대한 정보 가져오기
	List<DepartmentHsyVO> selectAllDepartment();

	// 모든 직원 정보 가져오기
	List<MemberHsyVO> selectAllMember();

}
