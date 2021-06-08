package com.t1works.groupware.ody.model;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


@Component
@Repository
public class ChatOdyDAO implements InterChatOdyDAO {

	@Resource
	private SqlSessionTemplate sqlsession5;
	
	@Override
	public List<MemberOdyVO> getDepartmentList() {
		List<MemberOdyVO> departmentList = sqlsession5.selectList("chatting_ody.getDepartmentList");
		return departmentList;
	}

	@Override
	public List<MemberOdyVO> getEmployeeList(String employeeid) {
		List<MemberOdyVO> employeeList = sqlsession5.selectList("chatting_ody.getEmployeeList",employeeid);
		return employeeList;
	}

}
