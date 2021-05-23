package com.t1works.groupware.aop;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.t1works.groupware.common.MyUtil;


@Aspect
@Component
public class GroupwareAOP {
	
	
	// === 주업무 설정 === //
	@Pointcut("execution(public * com.tlworks..*Controller.requiredLogin_*(..))")
    public void requiredLogin(){}
	
	// === 보조업무 설정 === //
	@Before("requiredLogin()")	   
	   public void loginCheck(JoinPoint joinPoint) { // JoinPoint joinPoint 는 포인트컷 되어진 주업무의 메소드이다. 
		  
		   // getArgs()는 리턴타입이 object이므로 형변환을 해줘야한다.
		   HttpServletRequest request = (HttpServletRequest)joinPoint.getArgs()[0]; // 주 업무에 있던 첫번째 파라미터를 땡겨오기
		   HttpServletResponse response = (HttpServletResponse)joinPoint.getArgs()[1]; // 주 업무에 있던 두번째 파라미터를 땡겨오기
		   
		   HttpSession session = request.getSession();
		   
		   if(session.getAttribute("loginuser")==null) {
			   String message = "먼저 로그인 하세요";
			   String loc = request.getContextPath()+"/login.action";
			   
			   request.setAttribute("message", message);
			   request.setAttribute("loc", loc);
			   
			   // >>> 로그인 성공 후 로그인 하기전 페이지로 돌아가는 작업 만들기 <<< // 
			   String url = MyUtil.getCurrentURL(request);
			   session.setAttribute("goBackURL", url); // 세션에  url페이지를 저장시켜준다.
			   
			   
			   // controller가 아닌 일반 java파일에서 jsp단으로 보낼땐, dispatcher를 사용한다.
			   RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/bwb/msg.jsp");
			   
			   try {
				  dispatcher.forward(request, response);
			   } catch (ServletException | IOException e) {
				  e.printStackTrace();
			   }

		   }
		   
		   
		   
		   // 로그인 유무를 확인하기 위해서는 request를 통해 session을 얻어와야 한다.
		   
		   
	   }
	
}
