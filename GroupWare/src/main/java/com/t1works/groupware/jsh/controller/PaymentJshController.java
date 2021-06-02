package com.t1works.groupware.jsh.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.servlet.ModelAndView;

import com.t1works.groupware.bwb.model.MemberBwbVO;
import com.t1works.groupware.common.MyUtil;
import com.t1works.groupware.jsh.model.ElectronPayJshVO;
import com.t1works.groupware.jsh.service.InterPaymentJshService;

@Controller
public class PaymentJshController {

	@Autowired // Type에 따라 알아서 Bean 을 주입해준다.
	private InterPaymentJshService service;

	// 글목록 보여주기
	@RequestMapping(value = "/t1/generalPayment_List.tw")
	public ModelAndView generalPayment_List(ModelAndView mav, HttpServletRequest request) {
		List<ElectronPayJshVO> electronList = null;

		/*
		 * // 일반결재내역 페이징 처리를 안한 검색어가 있는 전체 글목록 보여주기 시작== // String searchType =
		 * request.getParameter("searchType"); String searchCategory =
		 * request.getParameter("searchCategory"); String searchWord =
		 * request.getParameter("searchWord");
		 * 
		 * if(searchType == null || !"atitle".equals(searchType) &&
		 * !"name".equals(searchType)) { searchType = ""; }
		 * System.out.println(searchType); if(searchCategory == "" ||
		 * (!"1".equals(searchCategory) && !"2".equals(searchCategory) &&
		 * !"3".equals(searchCategory) && !"4".equals(searchCategory) )) {
		 * searchCategory = ""; }
		 * 
		 * if(searchWord == null || "".equals(searchWord) ||
		 * searchWord.trim().isEmpty()) { searchWord = ""; }
		 * 
		 * Map<String, String> paraMap = new HashMap<>(); paraMap.put("searchType",
		 * searchType); paraMap.put("searchCategory", searchCategory);
		 * paraMap.put("searchWord", searchWord);
		 * 
		 * electronList = service.electronListSearch(paraMap); // 일반결재내역 페이징 처리를 안한 검색어가
		 * 있는 전체 글목록 보여주기 끝== //
		 */
		// 검색어가 없는 글전체 목록
		// electronList = service.generalPayment_List();

		// ====일반결재내역 페이징 처리를 한 검색어가 있는 전체 글목록 보여주기 시작== //
		String searchType = request.getParameter("searchType");
		String searchCategory = request.getParameter("searchCategory");
		String searchWord = request.getParameter("searchWord");
		String str_currentShowPageNo = request.getParameter("currentShowPageNo");

		if (searchType == null || !"atitle".equals(searchType) && !"name".equals(searchType)) {
			searchType = "";
		}
		//System.out.println(searchType);
		if (searchCategory == "" || (!"1".equals(searchCategory) && !"2".equals(searchCategory)&& !"3".equals(searchCategory) && !"4".equals(searchCategory))) {
			searchCategory = "";
		}

		if (searchWord == null || "".equals(searchWord) || searchWord.trim().isEmpty()) {
			searchWord = "";
		}

		Map<String, String> paraMap = new HashMap<>();
		paraMap.put("searchType", searchType);
		paraMap.put("searchCategory", searchCategory);
		paraMap.put("searchWord", searchWord);

		// 먼저 총 게시물 건수(totalCount)를 구해와야 한다.
		// 총 게시물 건수(totalCount)는 검색조건이 있을때와 없을때로 나뉘어진다.
		int totalCount = 0; // 게시판에 올라와 있는 총 게시물 건수
		// int sizePerPage = 10; // 한 페이지당 보여줄 게시물 건수
		int sizePerPage = 2; // 한 페이지당 보여줄 게시물 건수
		int currentShowPageNo = 0;// 현재 보여주는 페이지 번호로서, 초기치로는 1페이지로 설정함.
		int totalPage = 0; // 총 페이지수(웹브라우저상에서 보여줄 총 페이지 개수, 페이지바)

		int startRno = 0; // 시작 행번호
		int endRno = 0; // 끝 행번호

		// 먼저 총 게시물 건수(totalCount)
		totalCount = service.getTotalCount(paraMap);
		//System.out.println("~~~ 확인용  totalCount" + totalCount);

		// 만약에 총 게시물 건수(totalCount)가 127개 이라면
		// 총 페이지 수(totalPage)는 13개가 되어야 한다.


		  totalPage = (int) Math.ceil((double) totalCount/ sizePerPage); 
		//  (double)127/10 ==> 12.7 ==>Math.ceil(12.7) ==> 13. 0 ==>13 ==> Math.ceil()의 파라미터는 double타입 
		// (double)120/10 ==> 12.7 ==>Math.ceil(12.0) ==> 12. 0 ==>12
		  
		  if(str_currentShowPageNo == null) { 
			  //게시판에 보여지는 초기화면
			  currentShowPageNo=1; 
			}
		  else { 
			  try { 
				  currentShowPageNo = Integer.parseInt(str_currentShowPageNo);
				  if(currentShowPageNo <1 || currentShowPageNo >totalPage) {
					  currentShowPageNo=1; 
					  }
			   } catch (NumberFormatException e) {
				   currentShowPageNo=1; } 
			  }
		  
		  // **** 가져올 게시글의 범위를 구한다.(공식임!!!) ****
		/*  
		  currentShowPageNo startRno endRno
		  -------------------------------------------- 
		  1 page ===> 1         10 
		  2 page ===> 11        20 
		  3 page ===> 21         30
		  4 page ===> 31          40 ...... ... ...
		 */
		 
		  startRno = ((currentShowPageNo - 1 ) * sizePerPage) + 1; 
		  endRno = startRno +sizePerPage - 1;
		  
		  paraMap.put("startRno", String.valueOf( startRno ) ); // String.valueOf =>  int type 을 String 으로 바꿔주기 
		  paraMap.put("endRno", String.valueOf( endRno ) );
		  
		  electronList = service.electronListSearchWithPaging(paraMap);
		  //페이징 처리한 글목록가져오기(검색이 있든지, 검색이 없든지 모두 다 포함한 것)
		  
		  // 아래는 검색대상 컬럼과 검색어를 유지시키기 위한 것임. 
		  if(!"".equals(searchCategory)&& !"".equals(searchType) && !"".equals(searchWord)) {
			  mav.addObject("paraMap",paraMap); 
		   }
		  
		 // == #121. 페이지 바 만들기
		  int blockSize = 3; 
		  //int blockSize = 10;
		  
		  int loop =1;
		 // loop는 1부터 증가하여 1개 블럭을 이루는 페이지번호의 개수[ 지금은 10개(== blockSize) ] 까지만 증가하는 용도이다.
		 
		  
		  // === 공식 === 
		  int pageNo = ((currentShowPageNo - 1)/blockSize) * blockSize + 1; 
		  // ==========
		 
		  String pageBar = "<ul style=' list-style:none;'>";
		  
		  String url ="generalPayment_List.tw";
		  
		  // === [맨처음][이전] 만들기 ===
		  if(pageNo != 1) { 
			  pageBar+= "<li style='display:inline-block; width:70px; font-size:12pt;'><a href='"+url+"?searchCategory="+searchCategory+"&searchType="+searchType+"&searchWord="+searchWord+"&currentShowPageNo=1'>[맨처음]</a></li>";
			  pageBar+= "<li style='display:inline-block; width:50px; font-size:12pt;'><a href='"+url +"?searchCategory="+searchCategory+"&searchType="+searchType+"&searchWord="+searchWord+"&currentShowPageNo="+(pageNo-1)+"'>[이전]</a></li>"; 
		 }
		 
		  while(!(loop > blockSize || pageNo > totalPage)) {
		  
				  if(pageNo == currentShowPageNo)  { 
					  pageBar += "<li style='display:inline-block; width:30px; font-size:12pt; border:solid 1px gray; color:red; padding:2px 4px;'>"+pageNo+"</li>"; 
				  }
				  else {
					  pageBar+= "<li style='display:inline-block; width:30px; font-size:12pt;'><a href='"+url +"?searchCategory="+searchCategory+"&searchType="+searchType+"&searchWord="+searchWord+"&currentShowPageNo="+pageNo+"'>"+pageNo+"</a></li>";          
				  }
		  
		  loop++;
		  
		  pageNo++;
		  
		  }// end of while--------------------
		  
		  // === [다음][마지막] 만들기 === 
		  if(pageNo <= totalPage) { 
			  pageBar += "<li style='display:inline-block; width:50px; font-size:12pt;'><a href='"+url+"?searchCategory="+searchCategory+"&searchType="+searchType+"&searchWord="+searchWord+"&currentShowPageNo="+pageNo+"'>[다음]</a></li>"; 
			  pageBar +="<li style='display:inline-block; width:70px; font-size:12pt;'><a href='"+url+"?searchCategory="+searchCategory+"&searchType="+searchType+"&searchWord="+searchWord+"&currentShowPageNo="+totalPage+"'>[마지막]</a></li>"; 
		   }
		 
		  
		  pageBar += "</ul>";
		  
		  mav.addObject("pageBar",pageBar);
		 

			// === #123. 페이징 처리되어진 후 특정 글제목을 클릭하여 상세내용을 본 이후
	        //           사용자가 목록보기 버튼을 클릭했을때 돌아갈 페이지를 알려주기 위해
	        //           현재 페이지 주소를 뷰단으로 넘겨준다.
	    	String gobackURL = MyUtil.getCurrentURL(request);
	    	//System.out.println("~~~확인용 gobackURL =>"+gobackURL);
	    	//~~~확인용 gobackURL =>t1/generalPayment_List.tw?searchCategory=&searchType=&searchWord=&currentShowPageNo=3	    	
	    	mav.addObject("gobackURL",gobackURL);
	      // == #114. 페이징 처리를 한 검색어가 있는 전체 글목록 보여주기  끝== //
	      //////////////////////////////////////////////////////////////////////////////////////////////////////////
		  
		  
		  
		// === 페이징 처리를 한 검색어가 있는 전체 글목록 보여주기 끝== //
		//////////////////////////////////////////////////////////////////////////////////////////////////////////

		mav.addObject("electronList", electronList);
		mav.setViewName("jsh/generalPayment_List.gwTiles");
		return mav;

	}

	// === #108. 검색어 입력시 자동글 완성하기 3 === //
	@ResponseBody
	@RequestMapping(value = "/t1/wordSearchShow.tw", method = {
			RequestMethod.GET }, produces = "text/plain;charset=UTF-8")
	public String wordSearchShow(HttpServletRequest request) {
		String searchType = request.getParameter("searchType");
		String searchWord = request.getParameter("searchWord");

	//	System.out.println("searchType=>" + searchType + "   searchWord=>" + searchWord);

		Map<String, String> paraMap = new HashMap<>();
		paraMap.put("searchType", searchType);
		paraMap.put("searchWord", searchWord);

		List<String> wordList = service.wordSearchShow(paraMap);

		JSONArray jsonArr = new JSONArray(); // []

		if (wordList != null) {
			for (String word : wordList) {
				JSONObject jsonObj = new JSONObject(); // {}
				jsonObj.put("word", word); // {"word":"글쓰기 첫번째 java 연습입니다"}

				jsonArr.put(jsonObj);
			}
		}

		return jsonArr.toString();
		// "[]" 또는
		// "[{"word":"글쓰기 첫번째 java 연습입니다"}, {"word":"글쓰기 두번째 JaVa 연습입니다"}]"
	}

	// 하나의 일반결재내역 문서 보여주기
	@RequestMapping(value = "/t1/view.tw")
	public ModelAndView generalOneView(HttpServletRequest request, ModelAndView mav) {
		
		String ano = request.getParameter("ano");
		String ncatname= request.getParameter("ncatname");
		

      // === #125. 페이징 처리되어진 후 특정 글제목을 클릭하여 상세내용을 본 이후
      //           사용자가 목록보기 버튼을 클릭했을때 돌아갈 페이지를 알려주기 위해
      //           현재 페이지 주소를 뷰단으로 넘겨준다.
      String gobackURL =request.getParameter("gobackURL");
       System.out.println("~~확인용 gobackURL=>" +gobackURL);
     // 
      if(gobackURL != null) {
    	  gobackURL =  gobackURL.replaceAll(" ", "&"); //  gobackURL 속에 " "(공백)이 있으면 "&" 로 바꾸어준다
    	 // 이전글제목, 다음글제목을 클릭했을때 돌아갈 페이지 주소를 올바르게 만들어주기 위해서 한 것임.
    	  
    	 // System.out.println("~~확인용 gobackURL=>" +gobackURL);
      }
      
      mav.addObject("gobackURL",gobackURL);
		
		
		Map<String, String> paraMap = new HashMap<>();
		paraMap.put("ano", ano);
		paraMap.put("ncatname", ncatname);
		
		
		
		try {   
		
		 ElectronPayJshVO epvo = service.generalOneView(paraMap);
		 //System.out.println("확인용~~ => "+epvo.getName());
		 
		 List<ElectronPayJshVO> opinionList = service.oneOpinionList(paraMap);
		 
		//System.out.println(ncatname);
		 mav.addObject("opinionList",opinionList);
		 mav.addObject("epvo", epvo);
		 mav.addObject("ncatname",ncatname);
		 
		} catch (NumberFormatException e) {
	         
	      }
		
		
		 mav.setViewName("jsh/generalPayment_View.gwTiles");
		
		return mav;
		
	}
	
	
	//  일반결재문서 작성페이지
	@RequestMapping(value = "/t1/generalPayment_Write.tw")
	public ModelAndView requiredLogin_Write(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {
	
		String userid="";
		String dname="";
		String dcode="";
		
		try {	
			HttpSession session = request.getSession(); 
			
			MemberBwbVO loginuser= (MemberBwbVO)session.getAttribute("loginuser");
			 //System.out.println("loginuser=>"+loginuser);
			if(loginuser != null) {
			 userid= loginuser.getEmployeeid(); 
			
			 HashMap<String, String> paraMap = new HashMap<String, String>();
			 paraMap.put("userid", userid);
			 		
			 ElectronPayJshVO write_view = service.login_Write(paraMap);
			 
			 mav.addObject("write_view", write_view);
			 
			}
			
		}catch(NumberFormatException e) {
			
		}
		
		
		mav.setViewName("jsh/generalPayment_Write.gwTiles");
		return mav;
	}
	
	
	
	
	
	
	
	
	
	
}
