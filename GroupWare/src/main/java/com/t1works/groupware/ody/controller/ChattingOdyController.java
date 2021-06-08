package com.t1works.groupware.ody.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.t1works.groupware.bwb.model.MemberBwbVO;
import com.t1works.groupware.ody.model.MemberOdyVO;
import com.t1works.groupware.ody.service.InterChatOdyService;

@Component
@Controller
public class ChattingOdyController {

	@Autowired
	private InterChatOdyService service;
	
	// === #173. (웹채팅관련4) === //
	@RequestMapping(value="/t1/chatting/chatmain.tw", method= {RequestMethod.GET})
	public String requiredLogin_chatmain(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		
		MemberBwbVO loginuser =(MemberBwbVO)session.getAttribute("loginuser");
		String employeeid = loginuser.getEmployeeid();
		// 부서정보
		List<MemberOdyVO> departmentList = service.getDepartmentList();
		
		// 사원정보
		List<MemberOdyVO> employeeList = service.getEmployeeList(employeeid);
		
		
		request.setAttribute("departmentList", departmentList);
		request.setAttribute("employeeList", employeeList);
		return "ody/chatmain";
	}
	
	@RequestMapping(value="/t1/chatting/chatwith.tw")
	public String requiredLogin_chatwith(HttpServletRequest request, HttpServletResponse response) {
		
		String empId = request.getParameter("to");
		request.setAttribute("empId", empId);
		return "ody/chatwith";
	}
	
	
	
	
}
