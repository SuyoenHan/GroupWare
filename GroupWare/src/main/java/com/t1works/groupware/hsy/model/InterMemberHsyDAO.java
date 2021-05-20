package com.t1works.groupware.hsy.model;

import java.util.List;


public interface InterMemberHsyDAO {
	
	// 모든 직원 정보 가져오기
	List<MemberHsyVO> selectAllMember();

}
