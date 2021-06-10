package com.t1works.groupware.ody.model;

import java.util.List;
import java.util.Map;

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

	// 이름 가져오기
	@Override
	public Map<String,String> findChatName(String empId) {
		Map<String,String> paraMap = sqlsession5.selectOne("chatting_ody.findChatName",empId);
		return paraMap;
	}

}
