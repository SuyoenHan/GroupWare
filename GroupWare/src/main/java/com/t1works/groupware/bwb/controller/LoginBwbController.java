package com.t1works.groupware.bwb.controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.t1works.groupware.bwb.model.MemberBwbVO;
import com.t1works.groupware.bwb.service.InterMemberBwbService;

@Controller
public class LoginBwbController {
	
	@Autowired // Type에 따라 알아서 Bean 을 주입해준다.
	private InterMemberBwbService service;
	
	
	// 그룹웨어 홈페이지 들어갔을때의 화면 구현
	@RequestMapping(value="/t1/home.tw")
	public ModelAndView test_test1(HttpServletRequest request,ModelAndView mav) {
		
		String method = request.getMethod();
		
		if(!"post".equalsIgnoreCase(method)) { // get방식
			
			HttpSession session = request.getSession();
			MemberBwbVO loginuser = (MemberBwbVO)session.getAttribute("loginuser");
			
			if(loginuser!=null) {
				mav.setViewName("/bwb/homepage.gwTiles");
				
			}
			else {
				mav.setViewName("/bwb/login/login");
			}
			return mav;
		}
		else { // post방식
			
			String employeeid = request.getParameter("employeeid");
			String passwd = request.getParameter("passwd");
			
			Map<String,String> paraMap = new HashMap<>();
			paraMap.put("employeeid", employeeid);
			paraMap.put("passwd", passwd);
			
			// 직원테이블에서 select해오기
			MemberBwbVO mvo = service.selectMember(paraMap);
			
			if(mvo != null) { // 로그인 성공했을경우
				HttpSession session =  request.getSession();
				session.setAttribute("loginuser", mvo);
				
				mav.setViewName("/bwb/homepage.gwTiles");	
				
				return mav;
			}
			else {// 로그인 실패했을 경우
				String message = "아이디와 비밀번호를 다시 확인해주세요";
				String loc = request.getContextPath()+"/t1/home.tw";
				
				paraMap = new HashMap<>();
				paraMap.put("message", message);
				paraMap.put("loc", loc);
				
				mav.addObject("paraMap", paraMap);
				mav.setViewName("/bwb/msg");		
				return mav;
			}
		
		}
		
	}
	

}
