package com.t1works.groupware.ody.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import com.t1works.groupware.ody.model.InterChatOdyDAO;
import com.t1works.groupware.ody.model.MemberOdyVO;

@Component
@Service
public class ChatOdyService implements InterChatOdyService {

	@Autowired 
	private InterChatOdyDAO cdao;
	
	// 부서 정보 조회
	@Override
	public List<MemberOdyVO> getDepartmentList() {
		List<MemberOdyVO> departmentList = cdao.getDepartmentList();
		return departmentList;
	}

	// 사원 정보 조회
	@Override
	public List<MemberOdyVO> getEmployeeList(String employeeid) {
		List<MemberOdyVO> employeeList = cdao.getEmployeeList(employeeid);
		return employeeList;
	}

	// 이름 가져오기
	@Override
	public Map<String,String> findChatName(String empId) {
		Map<String,String> paraMap = cdao.findChatName(empId);
		return paraMap;
	}

}
