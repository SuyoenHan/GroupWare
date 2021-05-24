package com.t1works.groupware.kdn.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.t1works.groupware.kdn.model.BoardVOKdn;
import com.t1works.groupware.kdn.service.InterBoardServiceKdn;

@Controller
public class BoardControllerKdn {
	
	 //@Autowired // Type에 따라 알아서 Bean 을 주입해준다.
	private InterBoardServiceKdn service;

	
	// === 글목록 보기 페이지 요청 ===
	@RequestMapping(value="/t1/employeeBoard.tw")
	public ModelAndView list(ModelAndView mav) {
		
		List<BoardVOKdn> boardList = null;
		
		// == 페이징 처리를 안한 검색어가 없는 전체 글목록 보여주기 == //
		//boardList = service.boardListNoSearch();
		
		mav.addObject("boardList", boardList);
		mav.setViewName("kdn/board.gwTiles");
		
		return mav;
		
	}
	
	// === 게시판 글쓰기 폼 페이지 요청 하기 ===
	@RequestMapping(value="/t1/postupload.tw")
	public ModelAndView requiredLogin_postupload(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {
		
		mav.setViewName("kdn/postupload.gwTiles");
		// WEB-INF/views/gwTiles/kdn/postupload.jsp 파일을 생성한다
		
		return mav;
	}
	
	// === 게시판 글쓰기 완료 요청 ===
	@RequestMapping(value="/uploadComplete.tw", method= {RequestMethod.POST})
	public ModelAndView addEnd(ModelAndView mav, BoardVOKdn boardvo) {
	    // form 태그의 name 명과  BoardVO 의 필드명이 같다라면  request.getParameter("form 태그의 name명"); 을 사용하지 않더라도
	    //  자동적으로 BoardVO boardvo 에 set 되어진다.
		
		int n = service.noticePostUpload(boardvo);	// 파일첨부가 없는 글쓰기
		
		if(n==1) {	//글쓰기가 성공한 경우
			mav.setViewName("redirect:/employeeBoard.gwTiles");
		//   employeeBoard.tw 페이지로 redirect(페이지이동)해라는 말이다.
		} else {	//글쓰기가 실패한 경우
			mav.setViewName("kdn/uploadfail.gwTiles");
			// WEB-INF/views/gwTiles/kdn/uploadfail.jsp 파일을 생성한다
		}
		
		
		return mav;
	} 
	
	
	
	
	
}
