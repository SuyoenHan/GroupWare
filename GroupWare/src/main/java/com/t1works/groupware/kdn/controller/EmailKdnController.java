package com.t1works.groupware.kdn.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.t1works.groupware.bwb.model.MemberBwbVO;
import com.t1works.groupware.common.FileManager;
import com.t1works.groupware.common.MyUtil;
import com.t1works.groupware.kdn.model.EmailKdnVO;
import com.t1works.groupware.kdn.service.InterEmailKdnService;

@Controller
public class EmailKdnController {
	
	@Autowired // Type에 따라 알아서 Bean 을 주입해준다.
	private InterEmailKdnService service;
	
	@Autowired     // Type에 따라 알아서 Bean 을 주입해준다.
	private FileManager fileManager;

	
	// === 메일쓰기 폼 페이지 요청 하기 ===
	@RequestMapping(value="/t1/new_mail.tw")
	public ModelAndView requiredLogin_new_Email(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {
		
		String receiverEmail = request.getParameter("receiverEmail");
		mav.addObject("receiverEmail", receiverEmail);
		
		// === #142. 답변글쓰기가 추가된 경우 ===
		String parentSeq = request.getParameter("parentSeq");
		String groupno = request.getParameter("groupno");
		String depthno = request.getParameter("depthno");
		
		mav.addObject("parentSeq", parentSeq);
		mav.addObject("groupno", groupno);
		mav.addObject("depthno", depthno);
		
		// 이메일주소 자동완성을 위한 주소록 가져오기
		List<String> emailList = service.getEmailList();
		mav.addObject("emailList", emailList);
		
		mav.setViewName("kdn/mail/new_mail.gwTiles");
		return mav;
	}
	
	// === 메일쓰기 완료 요청 ===
	@RequestMapping(value="/t1/sendSuccess.tw", method= {RequestMethod.POST})
	public ModelAndView sendSuccess(HttpServletRequest request, Map<String,String> paraMap, ModelAndView mav, EmailKdnVO evo, MultipartHttpServletRequest mrequest) {
		
		String ccMail = request.getParameter("ccMail");
		String checkSaveSentMail = request.getParameter("saveSentMail");
		if(ccMail == null) {
			ccMail = "";
		}
		
		MultipartFile attach = evo.getAttach();
		
		if(!attach.isEmpty()) {
			HttpSession session = mrequest.getSession();
			String root = session.getServletContext().getRealPath("/");
			
			String path =root+"resources"+File.separator+"files";
			// ~~~ webapp의 절대경로 => C:\NCS\workspace(spring)\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\Board\resources\files
			
			// 2. 파일첨부를 위한 변수의 설정 및 값을 초기화한 후 파일올리기
			
			String newFileName = "";// WAS(톰캣)의 디스크에 저장될 파일명 
			byte[] bytes = null; //첨부파일의 내용을 담는 것
			long fileSize = 0;	//첨부파일의 크기
			
			try {
				bytes = attach.getBytes();
				//첨부파일의 내용물을 읽어오는것
				String originalFilnename = attach.getOriginalFilename();
				
				newFileName = fileManager.doFileUpload(bytes, originalFilnename, path);
				
	        // 3. BoardVO boardvo 에 fileName 값과 orgFilename 값과 fileSize 값을 넣어주기
				
				evo.setFileName(newFileName); // WAS(톰캣)에 저장될 파일명
				evo.setOrgFilename(originalFilnename); // 게시판 페이지에서 첨부된 파일(강아지.png)을 보여줄 때 사용.
	            										   // 또한 사용자가 파일을 다운로드 할때 사용되어지는 파일명으로 사용.
				fileSize = attach.getSize(); // 첨부파일의 크기(단위는 byte임)
				evo.setFileSize(String.valueOf(fileSize)); 
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		}
		
		int n=0;
		
		// 첨부파일이 없는 경우
		if(attach.isEmpty()) {
			n = service.send(evo, checkSaveSentMail); // 파일첨부 없는 메일쓰기
		} else { //첨부파일이 있는 경우
			n = service.sendWithFile(evo, checkSaveSentMail); //파일첨부 있는 메일쓰기
		}
		
		if(n==1) {	//메일쓰기가 성공한 경우
			mav.setViewName("redirect:/t1/mail.tw");
		} else {	//글쓰기가 실패한 경우
			mav.setViewName("error/uploadfail.gwTiles");
		}
		
		return mav;
	}
	
	
	// 받은메일함 페이지 요청 ===
	@RequestMapping(value="/t1/mail.tw")
	public ModelAndView list(ModelAndView mav, HttpServletRequest request) {
		
		List<EmailKdnVO> emailList = null;
		
		HttpSession session = request.getSession();
		MemberBwbVO loginuser = (MemberBwbVO)session.getAttribute("loginuser");
		
		String searchType = request.getParameter("searchType");
		String searchWord = request.getParameter("searchWord");
		String str_currentShowPageNo = request.getParameter("currentShowPageNo");
		
		if(searchType == null || (!"subject".equals(searchType) && !"name".equals(searchType))) {
			searchType = "";
		}
		
		if(searchWord == null || "".equals(searchWord)|| searchWord.trim().isEmpty()) {
			searchWord = "";
		}
		
		Map<String, String> paraMap = new HashMap <>();
		paraMap.put("searchType", searchType);
		paraMap.put("searchWord", searchWord);
		paraMap.put("email", loginuser.getEmail());
		paraMap.put("moveToTrash", "0");
		
		// 먼저 총 게시물 건수(totalCount)를 구해와야 한다.
	    // 총 게시물 건수(totalCount)는 검색조건이 있을때와 없을때로 나뉘어진다.
		int totalCount = 0; // 총 게시물 건수
		int sizePerPage = 10;       // 한 페이지당 보여줄 게시물 건수
		int currentShowPageNo = 0; // 현재 보여주는 페이지 번호로서, 초기치로는 1페이지로 설정함
		int totalPage = 0;          // 총 페이지수(웹브라우저상에서 보여줄 총 페이지 개수, 페이지바)  
		
		int startRno = 0;           // 시작 행번호
	    int endRno = 0;             // 끝 행번호 
		
	    // 총 게시물 건수(totalCount)
	    totalCount = service.getTotalCount(paraMap);
	    //System.out.println("~~ 확인용 : "+totalCount);
	    
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
	    
	    emailList = service.emailListSearchWithPaging(paraMap);
		// 페이징 처리한 글목록 가져오기(검색어 유무 상관없이 모두 다 포함한것)
		
		// 아래는 검색대상 컬럼과 검색어를 유지시키기 위한 것임.
		if(!"".equals(searchType) && !"".equals(searchWord)) {
			mav.addObject("paraMap", paraMap);
		}
		
		// 페이지바 만들기
		int blockSize = 5;
		
		int loop = 1;
		// loop는 1부터 증가하여 1개 블럭을 이루는 페이지번호의 개수[ 지금은 10개(== blockSize) ] 까지만 증가하는 용도이다.
		
		int pageNo = ((currentShowPageNo - 1)/blockSize) * blockSize + 1;

		String pageBar = "<ul style='list-style: none;'>";
		String url = "mail.tw";
		
		// === [맨처음][이전] 만들기 ===
		if(pageNo != 1) {
			pageBar += "<li style='display:inline-block; width:70px; font-size:12pt;'><a href='"+url+"?searchType="+searchType+"&searchWord="+searchWord+"&currentShowPageNo="+1+"'>[맨처음]</a></li>";
			pageBar += "<li style='display:inline-block; width:50px; font-size:12pt;'><a href='"+url+"?searchType="+searchType+"&searchWord="+searchWord+"&currentShowPageNo="+(pageNo-1)+"'>[이전]</a></li>";
		}
		
		while( !(loop > blockSize || pageNo > totalPage)) {
			
			if(pageNo == currentShowPageNo) {
				pageBar += "<li style='display:inline-block; width:30px; font-size:12pt; border:solid 1px gray; color:red; padding:2px 4px;'>"+pageNo+"</li>";
			} else {
				pageBar += "<li style='display:inline-block; width:30px; font-size:12pt;'><a href='"+url+"?searchType="+searchType+"&searchWord="+searchWord+"&currentShowPageNo="+pageNo+"'>"+pageNo+"</a></li>";
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
		
		// === 페이징 처리되어진 후 특정 글제목을 클릭하여 상세내용을 본 이후 사용자가 목록보기 버튼을 클릭했을때 돌아갈 페이지를 알려주기 위해 현재 페이지 주소를 뷰단으로 넘겨준다. ===
		String gobackURL = MyUtil.getCurrentURL(request);
		
		mav.addObject("gobackURL", gobackURL);
		
		// == 페이징 처리를 한 검색어가 있는 전체 글목록 보여주기 끝== //
		///////////////////////////////////////////////////////////////
		
		mav.addObject("emailList", emailList);
		mav.setViewName("kdn/mail/mail_inbox.gwTiles");
		return mav;
	}

	// 메일 1개 열람하기
	@RequestMapping(value="/t1/viewMail.tw")
	public ModelAndView view(HttpServletRequest request, ModelAndView mav) {
		
		String mailBoxNo = request.getParameter("mailBoxNo");
		// 조회하고자 하는 글번호 받아오기
		String seq = request.getParameter("seq");
		
		HttpSession session = request.getSession();
		MemberBwbVO loginuser = (MemberBwbVO)session.getAttribute("loginuser");
		
		// 글목록에서 검색되어진 글내용일 경우 이전글제목, 다음글제목은 검색되어진 결과물내의 이전글과 다음글이 나오도록 하기 위한 것이다.  
	      String searchType = request.getParameter("searchType");
	      String searchWord = request.getParameter("searchWord");
	      
	      if(searchType == null) { searchType = ""; }
	      if(searchWord == null) { searchWord = ""; }
	      
	      Map<String,String> paraMap = new HashMap<>();
	      paraMap.put("seq", seq);
	      paraMap.put("searchType", searchType);
	      paraMap.put("searchWord", searchWord);
	      paraMap.put("mailBoxNo", mailBoxNo);
	      paraMap.put("email", loginuser.getEmail());
	      
	      
	      mav.addObject("mailBoxNo", mailBoxNo);
	      mav.addObject("searchType", searchType);
	      mav.addObject("searchWord", searchWord);
	      /////////////////////////////////////////////////////////////////////////////
		
		// 페이징 처리되어진 후 특정 글제목을 클릭하여 상세내용을 본 이후 사용자가 목록보기 버튼을 클릭했을때 돌아갈 페이지를 알려주기 위해 현재 페이지 주소를 뷰단으로 넘겨준다.
		String gobackURL = request.getParameter("gobackURL");
		//System.out.println("확인용 gobackURL : "+gobackURL);
		
		if(gobackURL != null) {
			gobackURL = gobackURL.replaceAll(" ", "&");
		}
		mav.addObject("gobackURL", gobackURL);
		
		
		try {
			Integer.parseInt(seq);
			
			EmailKdnVO evo = null;
			if(mailBoxNo.equals("1")) { // 받은메일함
				evo = service.getView(paraMap);
				mav.addObject("evo", evo);
			} else if(mailBoxNo.equals("2")) {	// 보낸메일함
				evo = service.getSentMailView(paraMap);
				mav.addObject("evo", evo);
			} else {	// 중요메일함
				evo = service.getImportantMailView(paraMap);
				mav.addObject("evo", evo);
			}
		}catch(NumberFormatException e) {

		}
		
		mav.setViewName("kdn/mail/viewMail.gwTiles");
		
		return mav;
	}
	
	// 보낸메일함 페이지 요청
	@RequestMapping(value="/t1/mail_sent.tw")
	public ModelAndView view_sentMail(ModelAndView mav, HttpServletRequest request) {
		
		List<EmailKdnVO> emailList = null;
		
		HttpSession session = request.getSession();
		MemberBwbVO loginuser = (MemberBwbVO)session.getAttribute("loginuser");
		
		String searchType = request.getParameter("searchType");
		String searchWord = request.getParameter("searchWord");
		String str_currentShowPageNo = request.getParameter("currentShowPageNo");
		
		if(searchType == null || (!"subject".equals(searchType) && !"name".equals(searchType))) {
			searchType = "";
		}
		
		if(searchWord == null || "".equals(searchWord)|| searchWord.trim().isEmpty()) {
			searchWord = "";
		}
		
		Map<String, String> paraMap = new HashMap <>();
		paraMap.put("searchType", searchType);
		paraMap.put("searchWord", searchWord);
		paraMap.put("email", loginuser.getEmail());
		
		// 먼저 총 게시물 건수(totalCount)를 구해와야 한다.
	    // 총 게시물 건수(totalCount)는 검색조건이 있을때와 없을때로 나뉘어진다.
		int totalCount = 0; // 총 게시물 건수
		int sizePerPage = 10;       // 한 페이지당 보여줄 게시물 건수
		int currentShowPageNo = 0; // 현재 보여주는 페이지 번호로서, 초기치로는 1페이지로 설정함
		int totalPage = 0;          // 총 페이지수(웹브라우저상에서 보여줄 총 페이지 개수, 페이지바)  
		
		int startRno = 0;           // 시작 행번호
	    int endRno = 0;             // 끝 행번호 
		
	    // 총 게시물 건수(totalCount)
	    totalCount = service.getMailSentTotalCount(paraMap);
	    //System.out.println("~~ 확인용 : "+totalCount);
	    
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
	    
	    emailList = service.sentEmailListSearchWithPaging(paraMap);
		// 페이징 처리한 글목록 가져오기(검색어 유무 상관없이 모두 다 포함한것)
		
		// 아래는 검색대상 컬럼과 검색어를 유지시키기 위한 것임.
		if(!"".equals(searchType) && !"".equals(searchWord)) {
			mav.addObject("paraMap", paraMap);
		}
		
		// 페이지바 만들기
		int blockSize = 5;
		
		int loop = 1;
		// loop는 1부터 증가하여 1개 블럭을 이루는 페이지번호의 개수[ 지금은 10개(== blockSize) ] 까지만 증가하는 용도이다.
		
		int pageNo = ((currentShowPageNo - 1)/blockSize) * blockSize + 1;

		String pageBar = "<ul style='list-style: none;'>";
		String url = "mail_sent.tw";
		
		// === [맨처음][이전] 만들기 ===
		if(pageNo != 1) {
			pageBar += "<li style='display:inline-block; width:70px; font-size:12pt;'><a href='"+url+"?searchType="+searchType+"&searchWord="+searchWord+"&currentShowPageNo="+1+"'>[맨처음]</a></li>";
			pageBar += "<li style='display:inline-block; width:50px; font-size:12pt;'><a href='"+url+"?searchType="+searchType+"&searchWord="+searchWord+"&currentShowPageNo="+(pageNo-1)+"'>[이전]</a></li>";
		}
		
		while( !(loop > blockSize || pageNo > totalPage)) {
			
			if(pageNo == currentShowPageNo) {
				pageBar += "<li style='display:inline-block; width:30px; font-size:12pt; border:solid 1px gray; color:red; padding:2px 4px;'>"+pageNo+"</li>";
			} else {
				pageBar += "<li style='display:inline-block; width:30px; font-size:12pt;'><a href='"+url+"?searchType="+searchType+"&searchWord="+searchWord+"&currentShowPageNo="+pageNo+"'>"+pageNo+"</a></li>";
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
		
		// === 페이징 처리되어진 후 특정 글제목을 클릭하여 상세내용을 본 이후 사용자가 목록보기 버튼을 클릭했을때 돌아갈 페이지를 알려주기 위해 현재 페이지 주소를 뷰단으로 넘겨준다. ===
		String gobackURL = MyUtil.getCurrentURL(request);
		
		mav.addObject("gobackURL", gobackURL);
		
		// == 페이징 처리를 한 검색어가 있는 전체 글목록 보여주기 끝== //
		///////////////////////////////////////////////////////////////
		
		mav.addObject("emailList", emailList);
		mav.setViewName("kdn/mail/mail_sent.gwTiles");
		return mav;
	}
	
	// 중요메일함 페이지 요청
	@RequestMapping(value="/t1/mail_important.tw")
	public ModelAndView view_importantMail(ModelAndView mav, HttpServletRequest request) {
		
		List<EmailKdnVO> emailList = null;
		
		HttpSession session = request.getSession();
		MemberBwbVO loginuser = (MemberBwbVO)session.getAttribute("loginuser");
		
		String searchType = request.getParameter("searchType");
		String searchWord = request.getParameter("searchWord");
		String str_currentShowPageNo = request.getParameter("currentShowPageNo");
		
		if(searchType == null || (!"subject".equals(searchType) && !"name".equals(searchType))) {
			searchType = "";
		}
		
		if(searchWord == null || "".equals(searchWord)|| searchWord.trim().isEmpty()) {
			searchWord = "";
		}
		
		Map<String, String> paraMap = new HashMap <>();
		paraMap.put("searchType", searchType);
		paraMap.put("searchWord", searchWord);
		paraMap.put("email", loginuser.getEmail());
		paraMap.put("checkImportant", "1");
		paraMap.put("moveToTrash", "0");
		
		// 먼저 총 게시물 건수(totalCount)를 구해와야 한다.
	    // 총 게시물 건수(totalCount)는 검색조건이 있을때와 없을때로 나뉘어진다.
		int totalCount = 0; // 총 게시물 건수
		int sizePerPage = 10;       // 한 페이지당 보여줄 게시물 건수
		int currentShowPageNo = 0; // 현재 보여주는 페이지 번호로서, 초기치로는 1페이지로 설정함
		int totalPage = 0;          // 총 페이지수(웹브라우저상에서 보여줄 총 페이지 개수, 페이지바)  
		
		int startRno = 0;           // 시작 행번호
	    int endRno = 0;             // 끝 행번호 
		
	    // 총 게시물 건수(totalCount)
	    totalCount = service.getMailImportantTotalCount(paraMap);
	    //System.out.println("~~ 확인용 : "+totalCount);
	    
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
	    
	    emailList = service.emailListSearchWithPaging(paraMap);
		// 페이징 처리한 글목록 가져오기(검색어 유무 상관없이 모두 다 포함한것)
		
		// 아래는 검색대상 컬럼과 검색어를 유지시키기 위한 것임.
		if(!"".equals(searchType) && !"".equals(searchWord)) {
			mav.addObject("paraMap", paraMap);
		}
		
		// 페이지바 만들기
		int blockSize = 5;
		
		int loop = 1;
		// loop는 1부터 증가하여 1개 블럭을 이루는 페이지번호의 개수[ 지금은 10개(== blockSize) ] 까지만 증가하는 용도이다.
		
		int pageNo = ((currentShowPageNo - 1)/blockSize) * blockSize + 1;

		String pageBar = "<ul style='list-style: none;'>";
		String url = "mail_sent.tw";
		
		// === [맨처음][이전] 만들기 ===
		if(pageNo != 1) {
			pageBar += "<li style='display:inline-block; width:70px; font-size:12pt;'><a href='"+url+"?searchType="+searchType+"&searchWord="+searchWord+"&currentShowPageNo="+1+"'>[맨처음]</a></li>";
			pageBar += "<li style='display:inline-block; width:50px; font-size:12pt;'><a href='"+url+"?searchType="+searchType+"&searchWord="+searchWord+"&currentShowPageNo="+(pageNo-1)+"'>[이전]</a></li>";
		}
		
		while( !(loop > blockSize || pageNo > totalPage)) {
			
			if(pageNo == currentShowPageNo) {
				pageBar += "<li style='display:inline-block; width:30px; font-size:12pt; border:solid 1px gray; color:red; padding:2px 4px;'>"+pageNo+"</li>";
			} else {
				pageBar += "<li style='display:inline-block; width:30px; font-size:12pt;'><a href='"+url+"?searchType="+searchType+"&searchWord="+searchWord+"&currentShowPageNo="+pageNo+"'>"+pageNo+"</a></li>";
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
		
		// === 페이징 처리되어진 후 특정 글제목을 클릭하여 상세내용을 본 이후 사용자가 목록보기 버튼을 클릭했을때 돌아갈 페이지를 알려주기 위해 현재 페이지 주소를 뷰단으로 넘겨준다. ===
		String gobackURL = MyUtil.getCurrentURL(request);
		
		mav.addObject("gobackURL", gobackURL);
		
		// == 페이징 처리를 한 검색어가 있는 전체 글목록 보여주기 끝== //
		///////////////////////////////////////////////////////////////
		
		mav.addObject("emailList", emailList);
		mav.setViewName("kdn/mail/mail_important.gwTiles");
		return mav;
	}
	
	// 휴지통 페이지 요청
	@RequestMapping(value="/t1/mail_trash.tw")
	public ModelAndView view_trash(ModelAndView mav, HttpServletRequest request) {
		
		List<EmailKdnVO> emailList = null;
		
		HttpSession session = request.getSession();
		MemberBwbVO loginuser = (MemberBwbVO)session.getAttribute("loginuser");
		
		String searchType = request.getParameter("searchType");
		String searchWord = request.getParameter("searchWord");
		String str_currentShowPageNo = request.getParameter("currentShowPageNo");
		
		if(searchType == null || (!"subject".equals(searchType) && !"name".equals(searchType))) {
			searchType = "";
		}
		
		if(searchWord == null || "".equals(searchWord)|| searchWord.trim().isEmpty()) {
			searchWord = "";
		}
		
		Map<String, String> paraMap = new HashMap <>();
		paraMap.put("searchType", searchType);
		paraMap.put("searchWord", searchWord);
		paraMap.put("email", loginuser.getEmail());
		paraMap.put("moveToTrash", "1");
		
		// 먼저 총 게시물 건수(totalCount)를 구해와야 한다.
	    // 총 게시물 건수(totalCount)는 검색조건이 있을때와 없을때로 나뉘어진다.
		int totalCount = 0; // 총 게시물 건수
		int sizePerPage = 10;       // 한 페이지당 보여줄 게시물 건수
		int currentShowPageNo = 0; // 현재 보여주는 페이지 번호로서, 초기치로는 1페이지로 설정함
		int totalPage = 0;          // 총 페이지수(웹브라우저상에서 보여줄 총 페이지 개수, 페이지바)  
		
		int startRno = 0;           // 시작 행번호
	    int endRno = 0;             // 끝 행번호 
		
	    // 총 게시물 건수(totalCount)
	    totalCount = service.getTrashTotalCount(paraMap);
	    //System.out.println("~~ 확인용 : "+totalCount);
	    
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
	    
	    emailList = service.trashListSearchWithPaging(paraMap);
		// 페이징 처리한 글목록 가져오기(검색어 유무 상관없이 모두 다 포함한것)
		
		// 아래는 검색대상 컬럼과 검색어를 유지시키기 위한 것임.
		if(!"".equals(searchType) && !"".equals(searchWord)) {
			mav.addObject("paraMap", paraMap);
		}
		
		// 페이지바 만들기
		int blockSize = 5;
		
		int loop = 1;
		// loop는 1부터 증가하여 1개 블럭을 이루는 페이지번호의 개수[ 지금은 10개(== blockSize) ] 까지만 증가하는 용도이다.
		
		int pageNo = ((currentShowPageNo - 1)/blockSize) * blockSize + 1;

		String pageBar = "<ul style='list-style: none;'>";
		String url = "mail_trash.tw";
		
		// === [맨처음][이전] 만들기 ===
		if(pageNo != 1) {
			pageBar += "<li style='display:inline-block; width:70px; font-size:12pt;'><a href='"+url+"?searchType="+searchType+"&searchWord="+searchWord+"&currentShowPageNo="+1+"'>[맨처음]</a></li>";
			pageBar += "<li style='display:inline-block; width:50px; font-size:12pt;'><a href='"+url+"?searchType="+searchType+"&searchWord="+searchWord+"&currentShowPageNo="+(pageNo-1)+"'>[이전]</a></li>";
		}
		
		while( !(loop > blockSize || pageNo > totalPage)) {
			
			if(pageNo == currentShowPageNo) {
				pageBar += "<li style='display:inline-block; width:30px; font-size:12pt; border:solid 1px gray; color:red; padding:2px 4px;'>"+pageNo+"</li>";
			} else {
				pageBar += "<li style='display:inline-block; width:30px; font-size:12pt;'><a href='"+url+"?searchType="+searchType+"&searchWord="+searchWord+"&currentShowPageNo="+pageNo+"'>"+pageNo+"</a></li>";
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
		
		// === 페이징 처리되어진 후 특정 글제목을 클릭하여 상세내용을 본 이후 사용자가 목록보기 버튼을 클릭했을때 돌아갈 페이지를 알려주기 위해 현재 페이지 주소를 뷰단으로 넘겨준다. ===
		String gobackURL = MyUtil.getCurrentURL(request);
		
		mav.addObject("gobackURL", gobackURL);
		
		// == 페이징 처리를 한 검색어가 있는 전체 글목록 보여주기 끝== //
		///////////////////////////////////////////////////////////////
		
		mav.addObject("emailList", emailList);
		mav.setViewName("kdn/mail/mail_trash.gwTiles");
		return mav;
	}
	
	
	
	// 첨부파일 다운로드 받기
	@RequestMapping(value="/t1/downloadEmailAttach.tw")
	public void requiredLogin_download(HttpServletRequest request, HttpServletResponse response) {
		
		String mailBoxNo = request.getParameter("mailBoxNo");
		String seq = request.getParameter("seq");
		// 첨부파일이 있는 글번호
		
		//System.out.println("메일번호 확인용 : "+seq);
		
		Map<String, String> paraMap = new HashMap<>();
		paraMap.put("seq", seq);
		paraMap.put("searchType", "");
		paraMap.put("searchWord", "");
		
		// 첨부파일이 있는 글번호에서 fileName값(ex. 20210604100859609711349587800.png)과 orgFilename(ex. cloth_buckaroo_3.png)을 DB에서 가져와야한다
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = null;
		
		try {
			Integer.parseInt(seq);
			EmailKdnVO evo = null;
			if(mailBoxNo.equals("2")) {
				evo = service.getSentMailView(paraMap);
			} else {
				evo = service.getView(paraMap);
			} 
			
			if(evo == null || (evo != null && evo.getFileName() == null)) {
				out = response.getWriter(); // 웹브라우저상에 메시지를 쓰기 위한 객체생성
				
				out.println("<script type='text/javascript'>alert('존재하지 않는 글번호이거나 첨부파일이 없으므로 파일 다운로드가 불가합니다!!'); history.back();</script>");
				return;
			} else {
				String fileName = evo.getFileName();
				
				String orgFilename = evo.getOrgFilename();
				
				HttpSession session = request.getSession();
				String root = session.getServletContext().getRealPath("/");
				
				String path =root+"resources"+File.separator+"files";
				
				// **** file 다운로드 하기 **** //
				boolean flag = false; // file 다운로드의 성공,실패를 알려주는 용도 
				flag = fileManager.doFileDownload(fileName, orgFilename, path, response);
				
				if(!flag) {
					// 다운로드가 실패할 경우 메시지를 띄워준다.
	               out = response.getWriter();
	               // 웹브라우저상에 메시지를 쓰기 위한 객체생성.
	               
	               out.println("<script type='text/javascript'>alert('파일 다운로드가 실패되었습니다!!'); history.back();</script>"); 
				}
				
			}
			
		} catch(NumberFormatException e) {
			try {
				out = response.getWriter(); // 웹브라우저상에 메시지를 쓰기 위한 객체생성
				
				out.println("<script type='text/javascript'>alert('파일 다운로드가 불가합니다!!'); history.back();</script>");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		} catch(IOException e2) { }
		
	}
	
	// 받은메일함&보낸메일함 목록에서 단일 혹은 다중 선택하여 메일 삭제하기
	@RequestMapping(value="/t1/delImmed.tw")
	public ModelAndView delEnd(ModelAndView mav, HttpServletRequest request) {
		
		String gobackURL = request.getParameter("gobackURL");
		mav.addObject("gobackURL",gobackURL);
		
		String str_arrEmailSeq = request.getParameter("str_arrEmailSeq");
		String[] arrEmailSeq = str_arrEmailSeq.split(",");
		String mailBoxNo = request.getParameter("mailBoxNo");
		System.out.println(str_arrEmailSeq);
		List<String> emailSeqList = Arrays.asList(arrEmailSeq);
		
        int n = 0;
        if(mailBoxNo.equals("1")) {
        	n = service.delImmed(emailSeqList); // 받은메일함의 메일 휴지통 이동없이 바로 삭제
        } else {
        	n = service.delSentMail(emailSeqList); // 보낸메일함의 메일 삭제
        	
        }
        // n 이 1 이라면 정상적으로 삭제됨.
        // n 이 0 이라면 글삭제에 필요한 글암호가 틀린경우 
        
        if(n == 0) {
            mav.addObject("message", "에러로 인해 메일을 삭제하지 못했습니다.");
            if(mailBoxNo.equals("1")) {
            	mav.addObject("loc", request.getContextPath()+"/"+gobackURL);
            } else {
            mav.addObject("loc", request.getContextPath()+"/"+gobackURL);
           }
        }            
        else {
           mav.addObject("message", "선택하신 메일이 삭제되었습니다.");
           if(mailBoxNo.equals("1")) {
        	   mav.addObject("loc", request.getContextPath()+"/"+gobackURL);
           } else {
        	   mav.addObject("loc", request.getContextPath()+"/"+gobackURL);
           }
        }
        mav.setViewName("msg");
		return mav;
	}
	
	
	// 중요메일함 중요표시체크해제, 받은메일함 중요표시체크/체크해제
	@RequestMapping(value="/t1/goStar.tw")
	public ModelAndView goUnstarred(ModelAndView mav, HttpServletRequest request) {
		
		String checkImportant = request.getParameter("checkImportant");
		//System.out.println("중요표시여부 0은 표시해제, 1은 표시: "+checkImportant);
		String mailBoxNo = request.getParameter("mailBoxNo");
		//System.out.println("메일함 번호: "+mailBoxNo);
		String str_arrEmailSeq = request.getParameter("str_arrEmailSeq");
		String[] arrEmailSeq = str_arrEmailSeq.split(",");
		//System.out.println("중요메일함 체크한 메일번호 배열: "+str_arrEmailSeq);
		List<String> emailSeqList = Arrays.asList(arrEmailSeq);

		int n = 0, m = 0;
		if(checkImportant.equals("0")) { 
			n = service.removeStar(emailSeqList); //중요표시해제
			if(n == 0) {
				mav.addObject("message", "에러발생으로 중요표시해제를 하지 못했습니다.");
				if(mailBoxNo.equals("1")) { // mailBoxNo가 1인 경우 받은메일함으로 이동
					mav.addObject("loc", request.getContextPath()+"/t1/mail.tw"); // 받은메일함으로 이동
				} else {
					mav.addObject("loc", request.getContextPath()+"/t1/mail_important.tw"); // 중요메일함으로 이동
				}
				mav.setViewName("msg");
			} else {        
				//mav.addObject("message", "선택하신 메일의 중요표시가 해제되었습니다.");
				if(mailBoxNo.equals("1")) {
					mav.addObject("loc", request.getContextPath()+"/t1/mail.tw");
				} else {
					mav.addObject("loc", request.getContextPath()+"/t1/mail_important.tw");
				}
				mav.setViewName("pageReloadOnly");
			}
		} else { 
			m = service.addStar(emailSeqList); // 중요표시하기
			if(m == 0) {
				mav.addObject("message", "에러발생으로 중요표시를 하지 못했습니다.");
				if(mailBoxNo.equals("1")) {
					mav.addObject("loc", request.getContextPath()+"/t1/mail.tw");
				} else {
					mav.addObject("loc", request.getContextPath()+"/t1/mail_important.tw");
				}
				mav.setViewName("msg");
			} else {            
				//mav.addObject("message", "선택하신 메일이 중요표시되었습니다.");
				if(mailBoxNo.equals("1")) {
					mav.addObject("loc", request.getContextPath()+"/t1/mail.tw");
				} else {
					mav.addObject("loc", request.getContextPath()+"/t1/mail_important.tw");
				}
				mav.setViewName("pageReloadOnly");
			}
		}
        
		return mav;
		
	}
	
	// 메일 휴지통으로 이동시키기
	@RequestMapping(value="/t1/moveToTrash.tw")
	public ModelAndView moveToTrash(ModelAndView mav, HttpServletRequest request) {
		
		String str_arrEmailSeq = request.getParameter("str_arrEmailSeq");
		String[] arrEmailSeq = str_arrEmailSeq.split(",");
		//System.out.println("받은메일함 체크한 메일번호 배열: "+str_arrEmailSeq);
		List<String> emailSeqList = Arrays.asList(arrEmailSeq);
		
		int n = service.moveToTrash(emailSeqList);
		
		if(n == 0) {
            mav.addObject("message", "에러발생으로 선택하신 작업을 실패하였습니다.");
           	mav.addObject("loc", request.getContextPath()+"/t1/mail.tw");
           	mav.setViewName("msg");
        }            
        else {
           //mav.addObject("message", "선택하신 메일을 휴지통으로 이동했습니다.");
    	   mav.addObject("loc", request.getContextPath()+"/t1/mail.tw");
    	   mav.setViewName("pageReloadOnly");
        }
		
		return mav;
		
	}
	
	// 휴지통 메일을 받은메일함으로 이동시키기
	@RequestMapping(value="/t1/moveToMailInbox.tw")
	public ModelAndView moveToMailInbox(ModelAndView mav, HttpServletRequest request) {
		
		String gobackURL = request.getParameter("gobackURL");
		String str_arrEmailSeq = request.getParameter("str_arrEmailSeq");
		String[] arrEmailSeq = str_arrEmailSeq.split(",");
		//System.out.println("받은메일함 체크한 메일번호 배열: "+str_arrEmailSeq);
		List<String> emailSeqList = Arrays.asList(arrEmailSeq);
		
		int n = service.moveToMailInbox(emailSeqList);
		
		if(n == 0) {
			mav.addObject("message", "에러발생으로 선택하신 작업을 실패하였습니다.");
			mav.addObject("loc", request.getContextPath()+"/"+gobackURL);
			mav.setViewName("msg");
		}            
		else {
			//mav.addObject("message", "선택하신 메일을 휴지통으로 이동했습니다.");
			mav.addObject("loc", request.getContextPath()+"/"+gobackURL);
			mav.setViewName("pageReloadOnly");
		}
		
		return mav;
		
	}
	
	
	
	
}
