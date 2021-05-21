package com.t1works.groupware.hsy.model;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public class MemberHsyDAO implements InterMemberHsyDAO {

	@Autowired 
	private SqlSessionTemplate sqlsession2;
	
	
	// 모든 직원 정보 가져오기
	@Override
	public List<MemberHsyVO> selectAllMember() {
		List<MemberHsyVO> employeeList= sqlsession2.selectList("memberHsy.selectAllMember");
		return employeeList;
	}

}
