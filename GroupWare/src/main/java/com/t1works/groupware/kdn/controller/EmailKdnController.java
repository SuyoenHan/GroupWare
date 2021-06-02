package com.t1works.groupware.kdn.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.t1works.groupware.kdn.model.EmailKdnVO;
import com.t1works.groupware.kdn.service.InterEmailKdnService;

@Controller
public class EmailKdnController {
	
	@Autowired // Type에 따라 알아서 Bean 을 주입해준다.
	private InterEmailKdnService service;

	@RequestMapping(value="/t1/mail.tw")
	public String view_inbox(){
		
		return "kdn/mail/mail_inbox.gwTiles";
	}
	
	@RequestMapping(value="/t1/mail_sent.tw")
	public String view_sent(){
		
		return "kdn/mail/mail_sent.gwTiles";
	}
	
	@RequestMapping(value="/t1/mail_trash.tw")
	public String view_Trash(){
		
		return "kdn/mail/mail_trash.gwTiles";
	}

	@RequestMapping(value="/t1/mail_important.tw")
	public String view_important(){
		
		return "kdn/mail/mail_important.gwTiles";
	}
	
	@RequestMapping(value="/t1/new_mail.tw")
	public String view_mailform(){
		
		return "kdn/mail/new_mail.gwTiles";
	}
	
	// === 메일쓰기 폼 페이지 요청 하기 ===
	@RequestMapping(value="/t1/new_Email.tw")
	public ModelAndView requiredLogin_new_Email(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {
		
		// === #142. 답변글쓰기가 추가된 경우 ===
/*		String fk_seq = request.getParameter("fk_seq");
		String groupno = request.getParameter("groupno");
		String depthno = request.getParameter("depthno");
		
		mav.addObject("fk_seq", fk_seq);
		mav.addObject("groupno", groupno);
		mav.addObject("depthno", depthno); */
		
		
		mav.setViewName("kdn/mail/new_mail.gwTiles");
		
		
		return mav;
	}
	
	// === 메일쓰기 완료 요청 ===
/*	@RequestMapping(value="/t1/sendSuccess.tw", method= {RequestMethod.POST})
	public ModelAndView sendSuccess(Map<String,String> paraMap, ModelAndView mav, EmailKdnVO evo) {
		int n = service.send(evo);	// 파일첨부가 없는 이메일 쓰기
		
		if(n==1) {	//글쓰기가 성공한 경우
			
			mav.setViewName("redirect:/t1/mail.tw");
		//   list.action 페이지로 redirect(페이지이동)해라는 말이다.
			
		} else {	//글쓰기가 실패한 경우
			mav.setViewName("error/uploadfail.gwTiles");
			// WEB-INF/views/tiles1/board/error/add_error.jsp 파일을 생성한다
		}
		
		return mav;
	}
*/	
	
}
