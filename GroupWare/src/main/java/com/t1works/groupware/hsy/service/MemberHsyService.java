package com.t1works.groupware.hsy.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.t1works.groupware.hsy.model.*;

@Component
@Service
public class MemberHsyService implements InterMemberHsyService {

	@Autowired 
	private InterMemberHsyDAO mdao;
	
	@Autowired 
	private InterDepartmentHsyDAO ddao;
	
	
	// 모든 부서에 대한 정보 가져오기
	@Override
	public List<DepartmentHsyVO> selectAllDepartment() {
		List<DepartmentHsyVO> departmentList= ddao.selectAllDepartment();
		return departmentList;
		
	}
	
	// 모든 직원 정보 가져오기
	@Override
	public List<MemberHsyVO> selectAllMember() {
		List<MemberHsyVO> employeeList= mdao.selectAllMember();
		return employeeList;
	}

}
