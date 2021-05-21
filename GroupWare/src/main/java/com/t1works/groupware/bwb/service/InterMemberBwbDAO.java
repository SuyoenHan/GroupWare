package com.t1works.groupware.bwb.service;

import java.util.Map;

import com.t1works.groupware.bwb.model.MemberBwbVO;

public interface InterMemberBwbDAO {

	// 로그인 시 직원 select해오기
	MemberBwbVO selectMember(Map<String, String> paraMap);

}
