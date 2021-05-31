package com.t1works.groupware.kdn.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.t1works.groupware.common.MyUtil;
import com.t1works.groupware.bwb.model.MemberBwbVO;
import com.t1works.groupware.kdn.model.BoardVOKdn;
import com.t1works.groupware.kdn.service.InterBoardServiceKdn;

@Controller
public class BoardKdnController {
	
	@Autowired // Type에 따라 알아서 Bean 을 주입해준다.
	private InterBoardServiceKdn service;
	
	// === 게시판 글쓰기 폼 페이지 요청 하기 ===
	@RequestMapping(value="/t1/noticePostUpload.tw")
	public ModelAndView requiredLogin_noticePostUpload(HttpServletRequest request, HttpServletResponse response, Map<String,String> paraMap, BoardVOKdn boardvo, ModelAndView mav) {
		mav.setViewName("kdn/noticepostupload.gwTiles");
		return mav;
	}
	
	// === 게시판 글쓰기 완료 요청 ===
	@RequestMapping(value="/t1/uploadComplete.tw", method= {RequestMethod.POST})
	public ModelAndView noticeUploadComplete(ModelAndView mav, BoardVOKdn boardvo) {
		int n = service.noticePostUpload(boardvo);	// 파일첨부가 없는 글쓰기
		
		if(n==1) {	//글쓰기가 성공한 경우
			mav.setViewName("redirect:/t1/employeeBoard.tw");
		} else {	//글쓰기가 실패한 경우
			mav.setViewName("kdn/error/uploadfail.gwTiles");
		}
		
		return mav;
	} 
	
	
	String boardTypeNo = "";
	
	// === 공지사항게시판 글목록 보기 페이지 요청 ===
	@RequestMapping(value="/t1/employeeBoard.tw")
	public ModelAndView requiredLogin_viewNoticeBoard(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {
		
		List<BoardVOKdn> boardList = null;
		
		HttpSession session = request.getSession();
		session.setAttribute("readCountPermission", "yes");
		
		boardTypeNo = "1";
		String fk_categnum = request.getParameter("fk_categnum");
		
		
		String searchType = request.getParameter("searchType");
		String searchWord = request.getParameter("searchWord");
		String str_currentShowPageNo = request.getParameter("currentShowPageNo");
		String str_sizePerPage = request.getParameter("sizePerPage");
		
		if(fk_categnum == null || (!"1".equals(fk_categnum) && !"2".equals(fk_categnum) && !"3".equals(fk_categnum))) {
			fk_categnum = "";
		}
		
		if(searchType == null || (!"subject".equals(searchType) && !"name".equals(searchType) && !"content".equals(searchType))) {
			searchType = "";
		}
		
		if(searchWord == null || "".equals(searchWord)|| searchWord.trim().isEmpty()) {
			searchWord = "";
		}
		
		if(str_sizePerPage == null || !("5".equals(str_sizePerPage) || "10".equals(str_sizePerPage) || "15".equals(str_sizePerPage)) ) {
			str_sizePerPage = "10";
		}
		
		Map<String, String> paraMap = new HashMap <>();
		paraMap.put("boardTypeNo", boardTypeNo);
		paraMap.put("fk_categnum", fk_categnum);
		paraMap.put("searchType", searchType);
		paraMap.put("searchWord", searchWord);
		paraMap.put("currentShowPageNo", str_currentShowPageNo);
		paraMap.put("sizePerPage", str_sizePerPage);
		
		int totalCount = 0; // 총 게시물 건수
		int sizePerPage = Integer.parseInt(str_sizePerPage); // 한 페이지당 보여줄 게시물 건수
		int currentShowPageNo = 0; // 현재 보여주는 페이지 번호로서, 초기치로는 1페이지로 설정함
		int totalPage = 0;          // 총 페이지수(웹브라우저상에서 보여줄 총 페이지 개수, 페이지바)  
		int startRno = 0;           // 시작 행번호
	    int endRno = 0;             // 끝 행번호 
		
	    
	    totalCount = service.getNoticeTotalCount(paraMap);
	    totalPage = (int)Math.ceil((double)totalCount/sizePerPage);
	    
	    if(str_currentShowPageNo == null) {
			// 게시판에 보여지는 초기화면
			currentShowPageNo = 1;
		} else {
			try {
				currentShowPageNo = Integer.parseInt(str_currentShowPageNo);
				if(currentShowPageNo < 1 || currentShowPageNo > totalPage) {
					currentShowPageNo = 1;
				}
			} catch (NumberFormatException e) {
				currentShowPageNo = 1;
			}
		}
	    
	    startRno = ((currentShowPageNo - 1 ) * sizePerPage) + 1;
	    endRno = startRno + sizePerPage - 1;
		
	    paraMap.put("startRno", String.valueOf(startRno));
	    paraMap.put("endRno", String.valueOf(endRno));
	    
	    // 페이징 처리한 글목록 가져오기(검색어 유무 상관없이 모두 다 포함한것)
		boardList = service.noticeBoardListSearchWithPaging(paraMap);
		
		// 아래는 검색대상 컬럼과 검색어를 유지시키기 위한 것임.
		if(!"".equals(searchType) && !"".equals(searchWord)) {
			mav.addObject("paraMap", paraMap);
		}
		
		// 페이지바 만들기
		int blockSize = 5;
		int loop = 1;
		int pageNo = ((currentShowPageNo - 1)/blockSize) * blockSize + 1;
		
		String pageBar = "<ul style='list-style: none;'>";
		String url = "employeeBoard.tw";
		
		// === [맨처음][이전] 만들기 ===
		if(pageNo != 1) {
			pageBar += "<li style='display:inline-block; width:70px; font-size:12pt;'><a href='"+url+"?searchType="+searchType+"&searchWord="+searchWord+"&currentShowPageNo="+1+"'>[맨처음]</a></li>";
			pageBar += "<li style='display:inline-block; width:50px; font-size:12pt;'><a href='"+url+"?searchType="+searchType+"&searchWord="+searchWord+"&currentShowPageNo="+(pageNo-1)+"'>[이전]</a></li>";
		}
		
		
		while( !(loop > blockSize || pageNo > totalPage)) {
			
			if(pageNo == currentShowPageNo) {
				pageBar += "<li style='display:inline-block; width:20px; font-size:11pt; font-weight: bold; padding:2px 2px;'>"+pageNo+"</li>";
			} else {
				pageBar += "<li style='display:inline-block; width:20px; font-size:11pt;'><a href='"+url+"?searchType="+searchType+"&searchWord="+searchWord+"&currentShowPageNo="+pageNo+"'>"+pageNo+"</a></li>";
			}
			
			
			loop++;
			pageNo++;
		}// end of while ----------
		
		// === [다음][마지막] 만들기 ===
		if(pageNo <= totalPage) {
			pageBar += "<li style='display:inline-block; width:50px; font-size:12pt;'><a href='"+url+"?searchType="+searchType+"&searchWord="+searchWord+"&currentShowPageNo="+pageNo+"'>[다음]</a></li>";
			pageBar += "<li style='display:inline-block; width:70px; font-size:12pt;'><a href='"+url+"?searchType="+searchType+"&searchWord="+searchWord+"&currentShowPageNo="+totalPage+"'>[마지막]</a></li>";
		}
		
		pageBar += "</ul>";
		
		mav.addObject("pageBar", pageBar);
		
		String gobackURL = MyUtil.getCurrentURL(request);
		mav.addObject("gobackURL", gobackURL);
		
		mav.addObject("boardList", boardList);
		mav.setViewName("kdn/noticeboard.gwTiles");
		
		return mav;
		
	}
	
	// === 공지사항 글1개를 보여주는 페이지 요청 ===
	@RequestMapping(value="/t1/viewNotice.tw")
	public ModelAndView requiredLogin_viewNotice(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {
		
		// 조회하고자 하는 글번호 받아오기
		String seq = request.getParameter("seq");
		// === #125. 페이징 처리되어진 후 특정 글제목을 클릭하여 상세내용을 본 이후 사용자가 목록보기 버튼을 클릭했을때 돌아갈 페이지를 알려주기 위해 현재 페이지 주소를 뷰단으로 넘겨준다.
		String gobackURL = request.getParameter("gobackURL");
		System.out.println("확인용 gobackURL : "+gobackURL);
		mav.addObject("gobackURL", gobackURL);
		
		try {
			Integer.parseInt(seq);
			
			String login_userid = null;
			
			HttpSession session = request.getSession();
			MemberBwbVO loginuser = (MemberBwbVO)session.getAttribute("loginuser");
			
			if(loginuser != null) {
				login_userid = loginuser.getEmployeeid();
			}
	
			BoardVOKdn boardvo = null;
			
			if("yes".equals(session.getAttribute("readCountPermission"))) { 
				//글목록보기를 클릭한 다음에 특정글을 조회해온 경우
				
				boardvo = service.getView(seq, login_userid);
				// 글조회수 증가와 함께 글1개를 조회를 해주는 것
				
				session.removeAttribute("readCountPermission");
				session.removeAttribute("readCountPermission");
				// 중요함!! session 에 저장된 readCountPermission 을 삭제한다.
			
			} else { // 웹브라우저에서 새로고침(F5)을 클릭한 경우이다.
				
				boardvo = service.getViewWithNoAddCount(seq);
				// 글조회수 증가는 없고 단순히 글1개 조회만을 해주는 것이다.
				
			}
			
			mav.addObject("boardvo", boardvo);
			
		
		}catch(NumberFormatException e) {

		}
		
		mav.setViewName("kdn/viewPost.gwTiles");
		
		return mav;
	}
	
	@ExceptionHandler(java.lang.Throwable.class)
   public void handleThrowable(Throwable e, HttpServletRequest request, HttpServletResponse response) {
      
      System.out.println("~~~~~ 오류메시지 : " + e.getMessage());
      
      try {
         // *** 웹브라우저에 출력하기 시작 *** //
         
         // HttpServletResponse response 객체는 넘어온 데이터를 조작해서 결과물을 나타내고자 할때 쓰인다. 
         response.setContentType("text/html; charset=UTF-8");
         
         PrintWriter out = response.getWriter();   // out 은 웹브라우저에 기술하는 대상체라고 생각하자.
         
         out.println("<html>");
         out.println("<head><title>오류메시지 출력하기</title></head>");
         out.println("<body>");
         out.println("<h1>오류발생</h1>");
         out.printf("<div><span style='font-weight: bold;'>오류메시지</span><br><span style='color: red;'>%s</span></div>", e.getMessage());
         out.printf("<div style='margin: 20px; color: blue; font-weight: bold; font-size: 26pt;'>%s</div>", "장난금지");
         out.println("<a href='/board/index.action'>홈페이지로 가기</a>");
         out.println("</body>");
         out.println("</html>");
         
         // *** 웹브라우저에 출력하기 끝 *** //
      } catch (IOException e1) {
         e1.printStackTrace();
      }
      
   }
	
	// === 공지사항 글수정 페이지 요청 === //
	@RequestMapping(value="/t1/noticeEdit.tw")
	public ModelAndView requiredLogin_noticeEdit(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {
		
		// 수정해야 할 글번호 가져오기
		String seq = request.getParameter("seq");
		
		// 글 수정해야할 글1개 내용 가져오기
		
		// 글조회수(readCount) 증가 없이 단순히 글1개만 조회 해주는 것이다.
		BoardVOKdn boardvo = service.getViewWithNoAddCount(seq);
				
		HttpSession session = request.getSession();
	    MemberBwbVO loginuser = (MemberBwbVO) session.getAttribute("loginuser");
	      
	    if( !loginuser.getEmployeeid().equals(boardvo.getFk_employeeid())) {
	        String message = "다른 사용자의 글은 삭제할 수 없습니다.";
	        String loc = "javascript:history.back()";
	         
	        mav.addObject("message", message);
	        mav.addObject("loc", loc);
	        mav.setViewName("msg");
	     } else {
	    	// 자신의 글을 수정할 경우
	         // 가져온 1개글을 글수정할 폼이 있는 view 단으로 보내준다.
	    	 mav.addObject("boardvo", boardvo);
	    	 mav.setViewName("kdn/noticeEdit.gwTiles");
	     }
		
		return mav;
	}
	
	// === 공지사항 글수정 페이지 완료하기 === //
	@RequestMapping(value="/t1/noticeEditEnd.tw", method= {RequestMethod.POST})
	public ModelAndView noticeEditEnd(ModelAndView mav, BoardVOKdn boardvo, HttpServletRequest request) {
		
		//  원본글의 글암호와 수정시 입력해준 암호가 일치할때만 글 수정이 가능하도록 해야한다.
		
		int n = service.noticeEdit(boardvo);
		// n 이 1 이라면 정상적으로 변경됨.
	    // n 이 0 이라면 글수정에 필요한 글암호가 틀린경우 
		
		if(n == 0) {
	         mav.addObject("message", "암호가 일치하지 않아 글 수정이 불가합니다.");
	      }
	      else {
	         mav.addObject("message", "글수정 성공!!");
	      }
		
		mav.addObject("loc", request.getContextPath()+"/t1/viewNotice.tw?seq="+boardvo.getSeq());
		mav.setViewName("msg");
		
		return mav;
		
	}
	
	// === 공지사항 글삭제 페이지 요청 === //
	@RequestMapping(value="/t1/noticeDel.tw")
	public ModelAndView requiredLogin_noticeDel(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {
		
		// 삭제해야 할 글번호 가져오기
		String seq = request.getParameter("seq");
		// 삭제해야할 글1개 내용 가져와서 로그인한 사람이 쓴 글이라면 글삭제가 가능하지만 다른 사람이 쓴 글은 삭제가 불가하도록 해야 한다.
		BoardVOKdn boardvo = service.getViewWithNoAddCount(seq);
		//글조회수(readCount) 증가 없이 단순히 글1개만 조회 해주는 것이다.
		
		HttpSession session = request.getSession();
	    MemberBwbVO loginuser = (MemberBwbVO) session.getAttribute("loginuser");
	      
	    if( !loginuser.getEmployeeid().equals(boardvo.getFk_employeeid()) ) {
	        String message = "다른 사용자의 글은 삭제가 불가합니다.";
	        String loc = "javascript:history.back()";
	         
	        mav.addObject("message", message);
	        mav.addObject("loc", loc);
	        mav.setViewName("msg");
	     } else { // 자신의 글을 삭제할 경우
	    	 // 글작성시 입력해준 글암호와 일치하는지 여부를 알아오도록 암호를 입력받아주는 del.jsp 페이지를 띄우도록 한다.
	    	 mav.addObject("seq", seq);
	    	 mav.setViewName("kdn/noticeDel.gwTiles");
	     }
		return mav;
	}
	
	// === 공지사항 글삭제 페이지 완료하기 === // 
	@RequestMapping(value="/t1/noticeDelEnd.tw", method= {RequestMethod.POST})
	public ModelAndView noticeDelEnd(ModelAndView mav, HttpServletRequest request) {
		
		//  글 삭제를 하려면 원본글의 글암호와 삭제시 입력해준 암호가 일치할때만 글 삭제가 가능하도록 해야한다.
		
		String seq = request.getParameter("seq");
        String pw = request.getParameter("pw");
      
        Map<String,String> paraMap = new HashMap<>();
        paraMap.put("seq", seq);
        paraMap.put("pw", pw);
		
        int n = service.noticeDel(paraMap); 
        // n 이 1 이라면 정상적으로 삭제됨.
        // n 이 0 이라면 글삭제에 필요한 글암호가 틀린경우 
        
        if(n == 0) {
            mav.addObject("message", "암호가 일치하지 않아 글 삭제가 불가합니다.");
            mav.addObject("loc", request.getContextPath()+"/t1/viewNotice.tw?seq="+seq);
        }
        else {
           mav.addObject("message", "글삭제 성공!!");
           mav.addObject("loc", request.getContextPath()+"/t1/employeeBoard.tw");
        }
        
        mav.setViewName("msg");
        
        
		return mav;
		
	}
	
	
	
	
	
	
	
	
	
	// === 건의사항게시판 글목록 보기 페이지 요청 ===
	@RequestMapping(value="/t1/suggestionBoard.tw")
	public ModelAndView requiredLogin_viewSuggestion(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {
		
		List<BoardVOKdn> boardList = null;
		
		HttpSession session = request.getSession();
		session.setAttribute("readCountPermission", "yes");
		
	    boardTypeNo = "2";
		String searchType = request.getParameter("searchType");
		String searchWord = request.getParameter("searchWord");
		String str_currentShowPageNo = request.getParameter("currentShowPageNo");
		String str_sizePerPage = request.getParameter("sizePerPage");
		
		if(searchType == null || (!"subject".equals(searchType) && !"name".equals(searchType) && !"content".equals(searchType))) {
			searchType = "";
		}
		
		if(searchWord == null || "".equals(searchWord)|| searchWord.trim().isEmpty()) {
			searchWord = "";
		}
		
		if(str_sizePerPage == null || !("5".equals(str_sizePerPage) || "10".equals(str_sizePerPage) || "15".equals(str_sizePerPage)) ) {
			str_sizePerPage = "10";
		}
		
		Map<String, String> paraMap = new HashMap <>();
		paraMap.put("boardTypeNo", boardTypeNo);
		paraMap.put("searchType", searchType);
		paraMap.put("searchWord", searchWord);
		paraMap.put("currentShowPageNo", str_currentShowPageNo);
		paraMap.put("sizePerPage", str_sizePerPage);
		
		int totalCount = 0; // 총 게시물 건수
		int sizePerPage = Integer.parseInt(str_sizePerPage); // 한 페이지당 보여줄 게시물 건수
		int currentShowPageNo = 0; // 현재 보여주는 페이지 번호로서, 초기치로는 1페이지로 설정함
		int totalPage = 0;          // 총 페이지수(웹브라우저상에서 보여줄 총 페이지 개수, 페이지바)  
		int startRno = 0;           // 시작 행번호
	    int endRno = 0;             // 끝 행번호 
		
	    
	    //System.out.println(boardTypeNo);
	    
	    totalCount = service.getTotalCount(paraMap);
	    totalPage = (int)Math.ceil((double)totalCount/sizePerPage);
	    
	    if(str_currentShowPageNo == null) {
			// 게시판에 보여지는 초기화면
			currentShowPageNo = 1;
		} else {
			try {
				currentShowPageNo = Integer.parseInt(str_currentShowPageNo);
				if(currentShowPageNo < 1 || currentShowPageNo > totalPage) {
					currentShowPageNo = 1;
				}
			} catch (NumberFormatException e) {
				currentShowPageNo = 1;
			}
		}
	    
	    startRno = ((currentShowPageNo - 1 ) * sizePerPage) + 1;
	    endRno = startRno + sizePerPage - 1;
		
	    paraMap.put("startRno", String.valueOf(startRno));
	    paraMap.put("endRno", String.valueOf(endRno));
	    
	    // 페이징 처리한 글목록 가져오기(검색어 유무 상관없이 모두 다 포함한것)
		boardList = service.listSearchWithPaging(paraMap);
		
		// 아래는 검색대상 컬럼과 검색어를 유지시키기 위한 것임.
		if(!"".equals(searchType) && !"".equals(searchWord)) {
			mav.addObject("paraMap", paraMap);
		}
		
		// 페이지바 만들기
		int blockSize = 5;
		int loop = 1;
		int pageNo = ((currentShowPageNo - 1)/blockSize) * blockSize + 1;
		
		String pageBar = "<ul style='list-style: none;'>";
		String url = "suggestionBoard.tw";
		
		// === [맨처음][이전] 만들기 ===
		if(pageNo != 1) {
			pageBar += "<li style='display:inline-block; width:70px; font-size:12pt;'><a href='"+url+"?searchType="+searchType+"&searchWord="+searchWord+"&currentShowPageNo="+1+"'>[맨처음]</a></li>";
			pageBar += "<li style='display:inline-block; width:50px; font-size:12pt;'><a href='"+url+"?searchType="+searchType+"&searchWord="+searchWord+"&currentShowPageNo="+(pageNo-1)+"'>[이전]</a></li>";
		}
		
		
		while( !(loop > blockSize || pageNo > totalPage)) {
			
			if(pageNo == currentShowPageNo) {
				pageBar += "<li style='display:inline-block; width:20px; font-size:11pt; font-weight: bold; padding:2px 2px;'>"+pageNo+"</li>";
			} else {
				pageBar += "<li style='display:inline-block; width:20px; font-size:11pt;'><a href='"+url+"?searchType="+searchType+"&searchWord="+searchWord+"&currentShowPageNo="+pageNo+"'>"+pageNo+"</a></li>";
			}
			
			
			loop++;
			pageNo++;
		}// end of while ----------
		
		// === [다음][마지막] 만들기 ===
		if(pageNo <= totalPage) {
			pageBar += "<li style='display:inline-block; width:50px; font-size:12pt;'><a href='"+url+"?searchType="+searchType+"&searchWord="+searchWord+"&currentShowPageNo="+pageNo+"'>[다음]</a></li>";
			pageBar += "<li style='display:inline-block; width:70px; font-size:12pt;'><a href='"+url+"?searchType="+searchType+"&searchWord="+searchWord+"&currentShowPageNo="+totalPage+"'>[마지막]</a></li>";
		}
		
		pageBar += "</ul>";
		
		mav.addObject("pageBar", pageBar);
		
		mav.addObject("boardList", boardList);
		mav.setViewName("kdn/suggestionboard.gwTiles");
		
		return mav;
		
	}

	// === 건의사항 글쓰기 폼 페이지 요청 하기 ===
	@RequestMapping(value="/t1/suggPostUpload.tw")
	public ModelAndView requiredLogin_suggestionPostUpload(HttpServletRequest request, HttpServletResponse response, Map<String,String> paraMap, BoardVOKdn boardvo, ModelAndView mav) {
		mav.setViewName("kdn/suggpostupload.gwTiles");
		return mav;
	}
	
	// === 건의사항 게시판 글쓰기 완료 요청 ===
	@RequestMapping(value="/t1/suggUploadComplete.tw", method= {RequestMethod.POST})
	public ModelAndView suggUploadComplete(ModelAndView mav, BoardVOKdn boardvo) {
		int n = service.suggPostUpload(boardvo);	// 파일첨부가 없는 글쓰기
		
		if(n==1) {	//글쓰기가 성공한 경우
			mav.setViewName("redirect:/t1/suggestionBoard.tw");
		} else {	//글쓰기가 실패한 경우
			mav.setViewName("kdn/error/uploadfail.gwTiles");
		}
		
		return mav;
	} 
	
	
	// === 건의사항 글1개를 보여주는 페이지 요청 ===
	@RequestMapping(value="/t1/viewSuggPost.tw")
	public ModelAndView requiredLogin_viewSuggPost(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {
		
		String seq = request.getParameter("seq");
		// === #125. 페이징 처리되어진 후 특정 글제목을 클릭하여 상세내용을 본 이후 사용자가 목록보기 버튼을 클릭했을때 돌아갈 페이지를 알려주기 위해 현재 페이지 주소를 뷰단으로 넘겨준다.
		String gobackURL = request.getParameter("gobackURL");
		mav.addObject("gobackURL", gobackURL);
		
		try {
			Integer.parseInt(seq);
			
			String login_userid = null;
			
			HttpSession session = request.getSession();
			MemberBwbVO loginuser = (MemberBwbVO)session.getAttribute("loginuser");
			
			if(loginuser != null) {
				login_userid = loginuser.getEmployeeid();
			}
	
			BoardVOKdn boardvo = null;
			
			if("yes".equals(session.getAttribute("readCountPermission"))) { 
				//글목록보기를 클릭한 다음에 특정글을 조회해온 경우
				
				boardvo = service.getSuggPostView(seq, login_userid);
				// 글조회수 증가와 함께 글1개를 조회를 해주는 것
				
				session.removeAttribute("readCountPermission");
				session.removeAttribute("readCountPermission");
				// 중요함!! session 에 저장된 readCountPermission 을 삭제한다.
			
			} else { // 웹브라우저에서 새로고침(F5)을 클릭한 경우이다.
				
				boardvo = service.getSuggViewWithNoAddCount(seq);
				// 글조회수 증가는 없고 단순히 글1개 조회만을 해주는 것이다.
			}
			
			mav.addObject("boardvo", boardvo);
			
		
		}catch(NumberFormatException e) {

		}
		
		mav.setViewName("kdn/viewSuggPost.gwTiles");
		
		return mav;
	}
	
	
	// === 건의사항 글수정 페이지 요청 === //
	@RequestMapping(value="/t1/suggEdit.tw")
	public ModelAndView requiredLogin_suggEdit(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {
		
		// 수정해야 할 글번호 가져오기
		String seq = request.getParameter("seq");
		
		// 글 수정해야할 글1개 내용 가져오기
		
		// 글조회수(readCount) 증가 없이 단순히 글1개만 조회 해주는 것이다.
		BoardVOKdn boardvo = service.getSuggViewWithNoAddCount(seq);
				
		HttpSession session = request.getSession();
	    MemberBwbVO loginuser = (MemberBwbVO) session.getAttribute("loginuser");
	      
	    if( !loginuser.getEmployeeid().equals(boardvo.getFk_employeeid())) {
	        String message = "다른 사용자의 글은 삭제할 수 없습니다.";
	        String loc = "javascript:history.back()";
	         
	        mav.addObject("message", message);
	        mav.addObject("loc", loc);
	        mav.setViewName("msg");
	     } else {
	    	// 자신의 글을 수정할 경우
	         // 가져온 1개글을 글수정할 폼이 있는 view 단으로 보내준다.
	    	 mav.addObject("boardvo", boardvo);
	    	 mav.setViewName("kdn/suggEdit.gwTiles");
	     }
		
		return mav;
	}
	
	// === 건의사항 글수정 페이지 완료하기 === //
	@RequestMapping(value="/t1/suggEditEnd.tw", method= {RequestMethod.POST})
	public ModelAndView suggEditEnd(ModelAndView mav, BoardVOKdn boardvo, HttpServletRequest request) {
		
		//  원본글의 글암호와 수정시 입력해준 암호가 일치할때만 글 수정이 가능하도록 해야한다.
		
		int n = service.suggEdit(boardvo);
		// n 이 1 이라면 정상적으로 변경됨.
	    // n 이 0 이라면 글수정에 필요한 글암호가 틀린경우 
		
		if(n == 0) {
	         mav.addObject("message", "암호가 일치하지 않아 글 수정이 불가합니다.");
	      }
	      else {
	         mav.addObject("message", "글수정 성공!!");
	      }
		
		mav.addObject("loc", request.getContextPath()+"/t1/viewSuggPost.tw?seq="+boardvo.getSeq());
		mav.setViewName("msg");
		
		return mav;
		
	}
	
	// === 건의사항 글삭제 페이지 요청 === //
		@RequestMapping(value="/t1/suggDel.tw")
		public ModelAndView requiredLogin_suggDel(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {
			
			// 삭제해야 할 글번호 가져오기
			String seq = request.getParameter("seq");
			// 삭제해야할 글1개 내용 가져와서 로그인한 사람이 쓴 글이라면 글삭제가 가능하지만 다른 사람이 쓴 글은 삭제가 불가하도록 해야 한다.
			BoardVOKdn boardvo = service.getSuggViewWithNoAddCount(seq);
			//글조회수(readCount) 증가 없이 단순히 글1개만 조회 해주는 것이다.
			
			HttpSession session = request.getSession();
		    MemberBwbVO loginuser = (MemberBwbVO) session.getAttribute("loginuser");
		      
		    if( !loginuser.getEmployeeid().equals(boardvo.getFk_employeeid()) ) {
		        String message = "다른 사용자의 글은 삭제가 불가합니다.";
		        String loc = "javascript:history.back()";
		         
		        mav.addObject("message", message);
		        mav.addObject("loc", loc);
		        mav.setViewName("msg");
		     } else { // 자신의 글을 삭제할 경우
		    	 // 글작성시 입력해준 글암호와 일치하는지 여부를 알아오도록 암호를 입력받아주는 del.jsp 페이지를 띄우도록 한다.
		    	 mav.addObject("seq", seq);
		    	 mav.setViewName("kdn/suggDel.gwTiles");
		     }
			return mav;
		}
		
		// === 건의사항 글삭제 페이지 완료하기 === // 
		@RequestMapping(value="/t1/suggDelEnd.tw", method= {RequestMethod.POST})
		public ModelAndView suggDelEnd(ModelAndView mav, HttpServletRequest request) {
			
			//  글 삭제를 하려면 원본글의 글암호와 삭제시 입력해준 암호가 일치할때만 글 삭제가 가능하도록 해야한다.
			
			String seq = request.getParameter("seq");
	        String pw = request.getParameter("pw");
	      
	        Map<String,String> paraMap = new HashMap<>();
	        paraMap.put("seq", seq);
	        paraMap.put("pw", pw);
			
	        int n = service.suggDel(paraMap); 
	        // n 이 1 이라면 정상적으로 삭제됨.
	        // n 이 0 이라면 글삭제에 필요한 글암호가 틀린경우 
	        
	        if(n == 0) {
	            mav.addObject("message", "암호가 일치하지 않아 글 삭제가 불가합니다.");
	            mav.addObject("loc", request.getContextPath()+"/t1/viewSuggPost.tw?seq="+seq);
	        }
	        else {
	           mav.addObject("message", "글삭제 성공!!");
	           mav.addObject("loc", request.getContextPath()+"/t1/suggestionBoard.tw");
	        }
	        
	        mav.setViewName("msg");
	        
	        
			return mav;
			
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// === 자유게시판 글목록 보기 페이지 요청 ===
	@RequestMapping(value="/t1/generalBoard.tw")
	public ModelAndView requiredLogin_viewGeneral(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {
		
		List<BoardVOKdn> boardList = null;
		
		HttpSession session = request.getSession();
		session.setAttribute("readCountPermission", "yes");
		
	    boardTypeNo = "3";
		
		String searchType = request.getParameter("searchType");
		String searchWord = request.getParameter("searchWord");
		String str_currentShowPageNo = request.getParameter("currentShowPageNo");
		String str_sizePerPage = request.getParameter("sizePerPage");
		
		if(searchType == null || (!"subject".equals(searchType) && !"name".equals(searchType) && !"content".equals(searchType))) {
			searchType = "";
		}
		
		if(searchWord == null || "".equals(searchWord)|| searchWord.trim().isEmpty()) {
			searchWord = "";
		}
		
		if(str_sizePerPage == null || !("5".equals(str_sizePerPage) || "10".equals(str_sizePerPage) || "15".equals(str_sizePerPage)) ) {
			str_sizePerPage = "10";
		}
		
		Map<String, String> paraMap = new HashMap <>();
		paraMap.put("boardTypeNo", boardTypeNo);
		paraMap.put("searchType", searchType);
		paraMap.put("searchWord", searchWord);
		paraMap.put("currentShowPageNo", str_currentShowPageNo);
		paraMap.put("sizePerPage", str_sizePerPage);
		
		int totalCount = 0; // 총 게시물 건수
		int sizePerPage = Integer.parseInt(str_sizePerPage); // 한 페이지당 보여줄 게시물 건수
		int currentShowPageNo = 0; // 현재 보여주는 페이지 번호로서, 초기치로는 1페이지로 설정함
		int totalPage = 0;          // 총 페이지수(웹브라우저상에서 보여줄 총 페이지 개수, 페이지바)  
		int startRno = 0;           // 시작 행번호
	    int endRno = 0;             // 끝 행번호 
		
	    
	   // System.out.println(boardTypeNo);
	    
	    
	    totalCount = service.getGenTotalCount(paraMap);
	    totalPage = (int)Math.ceil((double)totalCount/sizePerPage);
	    
	    if(str_currentShowPageNo == null) {
			// 게시판에 보여지는 초기화면
			currentShowPageNo = 1;
		} else {
			try {
				currentShowPageNo = Integer.parseInt(str_currentShowPageNo);
				if(currentShowPageNo < 1 || currentShowPageNo > totalPage) {
					currentShowPageNo = 1;
				}
			} catch (NumberFormatException e) {
				currentShowPageNo = 1;
			}
		}
	    
	    startRno = ((currentShowPageNo - 1 ) * sizePerPage) + 1;
	    endRno = startRno + sizePerPage - 1;
		
	    paraMap.put("startRno", String.valueOf(startRno));
	    paraMap.put("endRno", String.valueOf(endRno));
	    
	    // 페이징 처리한 글목록 가져오기(검색어 유무 상관없이 모두 다 포함한것)
		boardList = service.genListSearchWithPaging(paraMap);
		
		// 아래는 검색대상 컬럼과 검색어를 유지시키기 위한 것임.
		if(!"".equals(searchType) && !"".equals(searchWord)) {
			mav.addObject("paraMap2", paraMap);
		}
		
		// 페이지바 만들기
		int blockSize = 5;
		int loop = 1;
		int pageNo = ((currentShowPageNo - 1)/blockSize) * blockSize + 1;
		
		String pageBar = "<ul style='list-style: none;'>";
		String url = "generalBoard.tw";
		
		// === [맨처음][이전] 만들기 ===
		if(pageNo != 1) {
			pageBar += "<li style='display:inline-block; width:70px; font-size:12pt;'><a href='"+url+"?searchType="+searchType+"&searchWord="+searchWord+"&currentShowPageNo="+1+"'>[맨처음]</a></li>";
			pageBar += "<li style='display:inline-block; width:50px; font-size:12pt;'><a href='"+url+"?searchType="+searchType+"&searchWord="+searchWord+"&currentShowPageNo="+(pageNo-1)+"'>[이전]</a></li>";
		}
		
		
		while( !(loop > blockSize || pageNo > totalPage)) {
			
			if(pageNo == currentShowPageNo) {
				pageBar += "<li style='display:inline-block; width:20px; font-size:11pt; font-weight: bold; padding:2px 2px;'>"+pageNo+"</li>";
			} else {
				pageBar += "<li style='display:inline-block; width:20px; font-size:11pt;'><a href='"+url+"?searchType="+searchType+"&searchWord="+searchWord+"&currentShowPageNo="+pageNo+"'>"+pageNo+"</a></li>";
			}
			
			
			loop++;
			pageNo++;
		}// end of while ----------
		
		// === [다음][마지막] 만들기 ===
		if(pageNo <= totalPage) {
			pageBar += "<li style='display:inline-block; width:50px; font-size:12pt;'><a href='"+url+"?searchType="+searchType+"&searchWord="+searchWord+"&currentShowPageNo="+pageNo+"'>[다음]</a></li>";
			pageBar += "<li style='display:inline-block; width:70px; font-size:12pt;'><a href='"+url+"?searchType="+searchType+"&searchWord="+searchWord+"&currentShowPageNo="+totalPage+"'>[마지막]</a></li>";
		}
		
		pageBar += "</ul>";
		
		mav.addObject("pageBar", pageBar);
		
		mav.addObject("boardList", boardList);
		mav.setViewName("kdn/generalboard.gwTiles");
		
		return mav;
		
	}

		// === 자유게시판 글쓰기 폼 페이지 요청 하기 ===
		@RequestMapping(value="/t1/genPostUpload.tw")
		public ModelAndView requiredLogin_generalPostUpload(HttpServletRequest request, HttpServletResponse response, Map<String,String> paraMap, BoardVOKdn boardvo, ModelAndView mav) {
			mav.setViewName("kdn/genpostupload.gwTiles");
			return mav;
		}
		
		// === 자유게시판 게시판 글쓰기 완료 요청 ===
		@RequestMapping(value="/t1/genUploadComplete.tw", method= {RequestMethod.POST})
		public ModelAndView genUploadComplete(ModelAndView mav, BoardVOKdn boardvo) {
			int n = service.genPostUpload(boardvo);	// 파일첨부가 없는 글쓰기
			
			if(n==1) {	//글쓰기가 성공한 경우
				mav.setViewName("redirect:/t1/generalBoard.tw");
			} else {	//글쓰기가 실패한 경우
				mav.setViewName("kdn/error/uploadfail.gwTiles");
			}
			
			return mav;
		} 
		
		
		// === 자유게시판 글1개를 보여주는 페이지 요청 ===
		@RequestMapping(value="/t1/viewGenPost.tw")
		public ModelAndView requiredLogin_viewGenPost(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {
			
			String seq = request.getParameter("seq");
			// === #125. 페이징 처리되어진 후 특정 글제목을 클릭하여 상세내용을 본 이후 사용자가 목록보기 버튼을 클릭했을때 돌아갈 페이지를 알려주기 위해 현재 페이지 주소를 뷰단으로 넘겨준다.
			String gobackURL = request.getParameter("gobackURL");
			mav.addObject("gobackURL", gobackURL);
			
			BoardVOKdn boardvo = null;
			
			try {
				Integer.parseInt(seq);
				
				String login_userid = null;
				
				HttpSession session = request.getSession();
				MemberBwbVO loginuser = (MemberBwbVO)session.getAttribute("loginuser");
				
				if(loginuser != null) {
					login_userid = loginuser.getEmployeeid();
				}
		
				
				if("yes".equals(session.getAttribute("readCountPermission"))) { 
					//글목록보기를 클릭한 다음에 특정글을 조회해온 경우
					
					
					boardvo = service.getGenPostView(seq, login_userid);
					// 글조회수 증가와 함께 글1개를 조회를 해주는 것
					//System.out.println("~~~ 확인용 content : " + boardvo.getContent());
					
					session.removeAttribute("readCountPermission");
					// 중요함!! session 에 저장된 readCountPermission 을 삭제한다.
				
				} else { // 웹브라우저에서 새로고침(F5)을 클릭한 경우이다.
					
					boardvo = service.getGenViewWithNoAddCount(seq);
					// 글조회수 증가는 없고 단순히 글1개 조회만을 해주는 것이다.
				}
				
				mav.addObject("boardvo", boardvo);
				
			
			}catch(NumberFormatException e) {

			}
			
			mav.setViewName("kdn/viewGenPost.gwTiles");
			
			return mav;
		}


		// === 자유게시판 글수정 페이지 요청 === //
		@RequestMapping(value="/t1/generalEdit.tw")
		public ModelAndView requiredLogin_generalEdit(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {
			
			// 수정해야 할 글번호 가져오기
			String seq = request.getParameter("seq");
			
			// 글 수정해야할 글1개 내용 가져오기
			
			// 글조회수(readCount) 증가 없이 단순히 글1개만 조회 해주는 것이다.
			BoardVOKdn boardvo = service.getGenViewWithNoAddCount(seq);
					
			HttpSession session = request.getSession();
		    MemberBwbVO loginuser = (MemberBwbVO) session.getAttribute("loginuser");
		      
		    if( !loginuser.getEmployeeid().equals(boardvo.getFk_employeeid())) {
		        String message = "다른 사용자의 글은 삭제할 수 없습니다.";
		        String loc = "javascript:history.back()";
		         
		        mav.addObject("message", message);
		        mav.addObject("loc", loc);
		        mav.setViewName("msg");
		     } else {
		    	// 자신의 글을 수정할 경우
		         // 가져온 1개글을 글수정할 폼이 있는 view 단으로 보내준다.
		    	 mav.addObject("boardvo", boardvo);
		    	 mav.setViewName("kdn/generalEdit.gwTiles");
		     }
			
			return mav;
		}
		
		// === 자유게시판 글수정 페이지 완료하기 === //
		@RequestMapping(value="/t1/generalEditEnd.tw", method= {RequestMethod.POST})
		public ModelAndView generalEditEnd(ModelAndView mav, BoardVOKdn boardvo, HttpServletRequest request) {
			
			//  원본글의 글암호와 수정시 입력해준 암호가 일치할때만 글 수정이 가능하도록 해야한다.
			
			int n = service.generalEdit(boardvo);
			// n 이 1 이라면 정상적으로 변경됨.
		    // n 이 0 이라면 글수정에 필요한 글암호가 틀린경우 
			
			if(n == 0) {
		         mav.addObject("message", "암호가 일치하지 않아 글 수정이 불가합니다.");
		      }
		      else {
		         mav.addObject("message", "글수정 성공!!");
		      }
			
			mav.addObject("loc", request.getContextPath()+"/t1/viewGenPost.tw?seq="+boardvo.getSeq());
			mav.setViewName("msg");
			
			return mav;
			
		}
	
	
		// === 자유게시판 글삭제 페이지 요청 === //
		@RequestMapping(value="/t1/generalDel.tw")
		public ModelAndView requiredLogin_generalDel(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {
			
			// 삭제해야 할 글번호 가져오기
			String seq = request.getParameter("seq");
			// 삭제해야할 글1개 내용 가져와서 로그인한 사람이 쓴 글이라면 글삭제가 가능하지만 다른 사람이 쓴 글은 삭제가 불가하도록 해야 한다.
			BoardVOKdn boardvo = service.getGenViewWithNoAddCount(seq);
			//글조회수(readCount) 증가 없이 단순히 글1개만 조회 해주는 것이다.
			
			HttpSession session = request.getSession();
		    MemberBwbVO loginuser = (MemberBwbVO) session.getAttribute("loginuser");
		      
		    if( !loginuser.getEmployeeid().equals(boardvo.getFk_employeeid()) ) {
		        String message = "다른 사용자의 글은 삭제가 불가합니다.";
		        String loc = "javascript:history.back()";
		         
		        mav.addObject("message", message);
		        mav.addObject("loc", loc);
		        mav.setViewName("msg");
		     } else { // 자신의 글을 삭제할 경우
		    	 // 글작성시 입력해준 글암호와 일치하는지 여부를 알아오도록 암호를 입력받아주는 del.jsp 페이지를 띄우도록 한다.
		    	 mav.addObject("seq", seq);
		    	 mav.setViewName("kdn/generalDel.gwTiles");
		     }
			return mav;
		}
		
		// === 자유게시판 글삭제 페이지 완료하기 === // 
		@RequestMapping(value="/t1/generalDelEnd.tw", method= {RequestMethod.POST})
		public ModelAndView generalDelEnd(ModelAndView mav, HttpServletRequest request) {
			
			//  글 삭제를 하려면 원본글의 글암호와 삭제시 입력해준 암호가 일치할때만 글 삭제가 가능하도록 해야한다.
			
			String seq = request.getParameter("seq");
	        String pw = request.getParameter("pw");
	      
	        Map<String,String> paraMap = new HashMap<>();
	        paraMap.put("seq", seq);
	        paraMap.put("pw", pw);
			
	        int n = service.generalDel(paraMap); 
	        // n 이 1 이라면 정상적으로 삭제됨.
	        // n 이 0 이라면 글삭제에 필요한 글암호가 틀린경우 
	        
	        if(n == 0) {
	            mav.addObject("message", "암호가 일치하지 않아 글 삭제가 불가합니다.");
	            mav.addObject("loc", request.getContextPath()+"/t1/viewGenPost.tw?seq="+seq);
	        }
	        else {
	           mav.addObject("message", "글삭제 성공!!");
	           mav.addObject("loc", request.getContextPath()+"/t1/generalBoard.tw");
	        }
	        
	        mav.setViewName("msg");
	        
	        
			return mav;
			
		}
	
	
	
	
}
