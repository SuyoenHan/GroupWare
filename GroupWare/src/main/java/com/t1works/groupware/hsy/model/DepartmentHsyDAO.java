package com.t1works.groupware.hsy.model;

import java.util.*;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public class DepartmentHsyDAO implements InterDepartmentHsyDAO {

	@Autowired 
	private SqlSessionTemplate sqlsession2;

	// 모든 부서에 대한 정보 가져오기
	@Override
	public List<DepartmentHsyVO> selectAllDepartment() {

		List<DepartmentHsyVO> departmentList= sqlsession2.selectList("departmentHsy.selectAllDepartment");
		return departmentList; 
	
	}

	// 직급에 맞는 건당성과금 가져오기
	@Override
	public String getCommissionpercase(String employeeid) {
	
		String commissionpercase= sqlsession2.selectOne("departmentHsy.getCommissionpercase",employeeid);
		return commissionpercase;
	}

}
