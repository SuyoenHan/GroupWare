package com.t1works.groupware.jsh.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;


import com.t1works.groupware.bwb.model.MemberBwbVO;
import com.t1works.groupware.common.FileManager;
import com.t1works.groupware.common.MyUtil;
import com.t1works.groupware.jsh.model.ElectronPayJshVO;
import com.t1works.groupware.jsh.service.InterPaymentJshService;

@Controller
public class PaymentJshController {

	@Autowired // Type에 따라 알아서 Bean 을 주입해준다.
	private InterPaymentJshService service;

	// 파일업로드 및 다운로드를 해주는 FileManager 클래스 의존객체 주입하기(DI : Dependency Injection) ===
	@Autowired // Type에 따라 알아서 Bean 을 주입해준다.
	private FileManager fileManager;

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
		// System.out.println(searchType);
		if (searchCategory == "" || (!"1".equals(searchCategory) && !"2".equals(searchCategory)
				&& !"3".equals(searchCategory) && !"4".equals(searchCategory))) {
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
		// System.out.println("~~~ 확인용 totalCount" + totalCount);

		// 만약에 총 게시물 건수(totalCount)가 127개 이라면
		// 총 페이지 수(totalPage)는 13개가 되어야 한다.

		totalPage = (int) Math.ceil((double) totalCount / sizePerPage);
		// (double)127/10 ==> 12.7 ==>Math.ceil(12.7) ==> 13. 0 ==>13 ==> Math.ceil()의
		// 파라미터는 double타입
		// (double)120/10 ==> 12.7 ==>Math.ceil(12.0) ==> 12. 0 ==>12

		if (str_currentShowPageNo == null) {
			// 게시판에 보여지는 초기화면
			currentShowPageNo = 1;
		} else {
			try {
				currentShowPageNo = Integer.parseInt(str_currentShowPageNo);
				if (currentShowPageNo < 1 || currentShowPageNo > totalPage) {
					currentShowPageNo = 1;
				}
			} catch (NumberFormatException e) {
				currentShowPageNo = 1;
			}
		}

		// **** 가져올 게시글의 범위를 구한다.(공식임!!!) ****
		/*
		 * currentShowPageNo startRno endRno
		 * -------------------------------------------- 1 page ===> 1 10 2 page ===> 11
		 * 20 3 page ===> 21 30 4 page ===> 31 40 ...... ... ...
		 */

		startRno = ((currentShowPageNo - 1) * sizePerPage) + 1;
		endRno = startRno + sizePerPage - 1;

		paraMap.put("startRno", String.valueOf(startRno)); // String.valueOf => int type 을 String 으로 바꿔주기
		paraMap.put("endRno", String.valueOf(endRno));

		electronList = service.electronListSearchWithPaging(paraMap);
		// 페이징 처리한 글목록가져오기(검색이 있든지, 검색이 없든지 모두 다 포함한 것)

		// 아래는 검색대상 컬럼과 검색어를 유지시키기 위한 것임.
		if (!"".equals(searchCategory) && !"".equals(searchType) && !"".equals(searchWord)) {
			mav.addObject("paraMap", paraMap);
		}

		// == #121. 페이지 바 만들기
		int blockSize = 3;
		// int blockSize = 10;

		int loop = 1;
		// loop는 1부터 증가하여 1개 블럭을 이루는 페이지번호의 개수[ 지금은 10개(== blockSize) ] 까지만 증가하는 용도이다.

		// === 공식 ===
		int pageNo = ((currentShowPageNo - 1) / blockSize) * blockSize + 1;
		// ==========

		String pageBar = "<ul style=' list-style:none;'>";

		String url = "generalPayment_List.tw";

		// === [맨처음][이전] 만들기 ===
		if (pageNo != 1) {
			pageBar += "<li style='display:inline-block; width:70px; font-size:12pt;'><a href='" + url
					+ "?searchCategory=" + searchCategory + "&searchType=" + searchType + "&searchWord=" + searchWord
					+ "&currentShowPageNo=1'>[맨처음]</a></li>";
			pageBar += "<li style='display:inline-block; width:50px; font-size:12pt;'><a href='" + url
					+ "?searchCategory=" + searchCategory + "&searchType=" + searchType + "&searchWord=" + searchWord
					+ "&currentShowPageNo=" + (pageNo - 1) + "'>[이전]</a></li>";
		}

		while (!(loop > blockSize || pageNo > totalPage)) {

			if (pageNo == currentShowPageNo) {
				pageBar += "<li style='display:inline-block; width:30px; font-size:12pt; border:solid 1px gray; color:red; padding:2px 4px;'>"
						+ pageNo + "</li>";
			} else {
				pageBar += "<li style='display:inline-block; width:30px; font-size:12pt;'><a href='" + url
						+ "?searchCategory=" + searchCategory + "&searchType=" + searchType + "&searchWord="
						+ searchWord + "&currentShowPageNo=" + pageNo + "'>" + pageNo + "</a></li>";
			}

			loop++;

			pageNo++;

		} // end of while--------------------

		// === [다음][마지막] 만들기 ===
		if (pageNo <= totalPage) {
			pageBar += "<li style='display:inline-block; width:50px; font-size:12pt;'><a href='" + url
					+ "?searchCategory=" + searchCategory + "&searchType=" + searchType + "&searchWord=" + searchWord
					+ "&currentShowPageNo=" + pageNo + "'>[다음]</a></li>";
			pageBar += "<li style='display:inline-block; width:70px; font-size:12pt;'><a href='" + url
					+ "?searchCategory=" + searchCategory + "&searchType=" + searchType + "&searchWord=" + searchWord
					+ "&currentShowPageNo=" + totalPage + "'>[마지막]</a></li>";
		}

		pageBar += "</ul>";

		mav.addObject("pageBar", pageBar);

		// === #123. 페이징 처리되어진 후 특정 글제목을 클릭하여 상세내용을 본 이후
		// 사용자가 목록보기 버튼을 클릭했을때 돌아갈 페이지를 알려주기 위해
		// 현재 페이지 주소를 뷰단으로 넘겨준다.
		String gobackURL = MyUtil.getCurrentURL(request);
		// System.out.println("~~~확인용 gobackURL =>"+gobackURL);
		// ~~~확인용 gobackURL
		// =>t1/generalPayment_List.tw?searchCategory=&searchType=&searchWord=&currentShowPageNo=3
		mav.addObject("gobackURL", gobackURL);
		// == #114. 페이징 처리를 한 검색어가 있는 전체 글목록 보여주기 끝== //
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

		// System.out.println("searchType=>" + searchType + " searchWord=>" +
		// searchWord);

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

		//추가하고자 하는 일반문서 종류와  글 번호 받아오기
		String ano = request.getParameter("ano");
		String ncatname = request.getParameter("ncatname");

		//[추가] 글목록에서 검색되어진 글내용일 경우 이전글제목, 다음글제목은 검색되어진 결과물내의 이전글과 다음글이 나오도록 하기 위한 것이다.  
	      String searchType = request.getParameter("searchType");
	      String searchWord = request.getParameter("searchWord");
	      String searchCategory = request.getParameter("searchCategory");
	      
	      if(searchType == null) {
	    	  searchType = "";
	      }
	      
	      if(searchWord == null) {
	    	  searchWord = "";
	      }
	      if(searchCategory == null) {
	    	  searchCategory = "";
	      }
	      
	      
        Map<String, String> paraMap = new HashMap<>();
		  paraMap.put("ano", ano);
		  paraMap.put("ncatname", ncatname);
	      paraMap.put("searchType", searchType);
	      paraMap.put("searchWord", searchWord);
	      paraMap.put("searchCategory", searchCategory);
	      
	      mav.addObject("searchCategory", searchCategory);
	      mav.addObject("searchType", searchType);
	      mav.addObject("searchWord", searchWord);
	     //[추가끝]///////////////////////////////////////////////////////////////////////////
		
		
		// === #125. 페이징 처리되어진 후 특정 글제목을 클릭하여 상세내용을 본 이후
		// 사용자가 목록보기 버튼을 클릭했을때 돌아갈 페이지를 알려주기 위해
		// 현재 페이지 주소를 뷰단으로 넘겨준다.
		String gobackURL = request.getParameter("gobackURL");
		// System.out.println("~~확인용 gobackURL=>" +gobackURL);
		//
		if (gobackURL != null) {
			gobackURL = gobackURL.replaceAll(" ", "&"); // gobackURL 속에 " "(공백)이 있으면 "&" 로 바꾸어준다
			// 이전글제목, 다음글제목을 클릭했을때 돌아갈 페이지 주소를 올바르게 만들어주기 위해서 한 것임.

			// System.out.println("~~확인용 gobackURL=>" +gobackURL);
		}

		mav.addObject("gobackURL", gobackURL);

		

		try {

			ElectronPayJshVO epvo = service.generalOneView(paraMap);
			// System.out.println("확인용~~ => "+epvo.getName());

			List<ElectronPayJshVO> opinionList = service.oneOpinionList(paraMap);

			// System.out.println(ncatname);
			mav.addObject("opinionList", opinionList);
			mav.addObject("epvo", epvo);
			mav.addObject("ncatname", ncatname);

		} catch (NumberFormatException e) {

		}

		mav.setViewName("jsh/generalPayment_View.gwTiles");

		return mav;

	}

	// 일반결재문서 작성페이지 호출
	@RequestMapping(value = "/t1/generalPayment_Write.tw")
	public ModelAndView requiredLogin_Write(HttpServletRequest request, HttpServletResponse response,
			ModelAndView mav) {

		String userid = "";
		
		try {
			HttpSession session = request.getSession();

			MemberBwbVO loginuser = (MemberBwbVO) session.getAttribute("loginuser");
			// System.out.println("loginuser=>"+loginuser);
			if (loginuser != null) {
				userid = loginuser.getEmployeeid();

				HashMap<String, String> paraMap = new HashMap<String, String>();
				paraMap.put("userid", userid);

				ElectronPayJshVO write_view = service.WriteJsh(paraMap);
				//System.out.println(write_view.getManagerid());
				ElectronPayJshVO write_mview = service.mWriteJsh(paraMap); // 수신자 정보 select해오기
				
				
				mav.addObject("write_view", write_view);
				mav.addObject("write_mview", write_mview);

			}

		} catch (NumberFormatException e) {

		}

		mav.setViewName("jsh/generalPayment_Write.gwTiles");
		return mav;
	}

	//제출하기 insert시키기
	@RequestMapping(value = "/t1/generalPayment_WriteEnd.tw", method= {RequestMethod.POST})
	 public ModelAndView WriteEnd(Map<String,String> paraMap, ModelAndView mav, ElectronPayJshVO epvo, MultipartHttpServletRequest mrequest) {
		 
		String mdate1 = mrequest.getParameter("mdate1");
		String mdate2 = mrequest.getParameter("mdate2");
		String mdate3 = mrequest.getParameter("mdate3");
		String fk_wiimdate1 = mrequest.getParameter("fk_wiimdate1");
		String fk_wiimdate2 = mrequest.getParameter("fk_wiimdate2");
		
		String mdate= mdate1+" "+mdate2+" ~ "+mdate3;
		String fk_wiimdate = fk_wiimdate1+" ~ "+fk_wiimdate2;
		
		epvo.setMdate(mdate);
		epvo.setFk_wiimdate(fk_wiimdate);
		
		// === 사용자가 쓴 글에 파일이 첨부되어 있는 것인지, 아니면 파일첨부가 안된것인지 구분을 지어주어야 한다. === 
		//  !!! 첨부파일이 있는 경우 작업 시작 !!! ===
		   
		   MultipartFile attach = epvo.getAttach();
		   
		   if(!attach.isEmpty()) { // 첨부파일이 있다라면
			// attach(첨부파일)가 비어있지 않으면(즉, 첨부파일이 있는 경우라면) 
			 //  System.out.println("파일이 존재합니다~");
			/*
	           1. 사용자가 보낸 첨부파일을 WAS(톰캣)의 특정 폴더에 저장해주어야 한다. 
	           >>> 파일이 업로드 되어질 특정 경로(폴더)지정해주기
	                                   우리는 WAS의 webapp/resources/files 라는 폴더로 지정해준다.
	                                   조심할 것은  Package Explorer 에서  files 라는 폴더를 만드는 것이 아니다.       
	        */     
		   // WAS의 webapp 의 절대경로를 알아와야 한다.
			  HttpSession session = mrequest.getSession();
			  String root = session.getServletContext().getRealPath("/");
			   
		  //  System.out.println("~~~~~~ webapp의 절대경로 =>" + root);
			  //~~~~~~ webapp의 절대경로 =>C:\NCS\workspace(spring)\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\Board\
			  
			  String path = root+"resources"+File.separator +"files";
			  /* File.separator 는 운영체제에서 사용하는 폴더와 파일의 구분자이다.
			           운영체제가 Windows 이라면 File.separator 는  "\" 이고,
			           운영체제가 UNIX, Linux 이라면  File.separator 는 "/" 이다. 
			  */
			  
			  // path 가 첨부파일이 저장될 WAS(톰캣)의 폴더가 된다.
		//	  System.out.println("~~~~~~ path =>" + path);
			  //~~~~~~ path =>C:\NCS\workspace(spring)\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\Board\resources\files
		   
			/*
			   2.파일첨부를 위한 변수의 설정 및 값을 초기화 한 후 파일 올리기 
			*/
			  String newFileName = "";
			  // WAS(톰캣)의 디스크에 저장될 파일명   
		   
			  byte[] bytes = null;
			  //첨부파일의 내용을 담는 것
			  
			  long fileSize =0;
			  
			  try {
				bytes = attach.getBytes();
				// 첨부파일의 내용물을 읽어오는 것
				
				String originalFilename = attach.getOriginalFilename();
				//originalFilename ==> "강아지.png"
				
				
				newFileName = fileManager.doFileUpload(bytes, originalFilename, path);
				 
			//	System.out.println(">> 확인용 newFileName=> " + newFileName);
				//>> 확인용 newFileName=> 202106031651411150647615171500.png

				
		     /*
	            3. ElectronPayJshVO epvo 에 fileName 값과 orgFilename 값과 fileSize 값을 넣어주기   
	         */
				epvo.setFileName(newFileName);
				// WAS(톰캣)에 저장될 파일명  (202106031651411150647615171500.png)
				epvo.setOrgFilename(originalFilename);
				// 게시판 페이지에서 첨부된 파일(강아지.png)을 보여줄 때 사용.
	            // 또한 사용자가 파일을 다운로드 할때 사용되어지는 파일명으로 사용.
				
				fileSize = attach.getSize(); // 첨부파일의 크기(단위는 byte임)
				epvo.setFileSize(String.valueOf(fileSize));
				
				
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		   
		   }
		   // === !!! 첨부파일이 있는 경우 작업 끝 !!! ===
		
		
		
		  // int n = service.addPayment(epvo); // <== 파일첨부가 없는 글쓰기
		
		
		 // === 파일첨부가 있는 글쓰기 또는 파일첨부가 없는 글쓰기로 나뉘어서 service호출하기
		    
		  int n = 0;
		   
		  try {
			  
			   //첨부파일이 없는 경우라면
			   if(attach.isEmpty()) {
				  
					n = service.addPayment(epvo); //insert(트랜잭션처리) <== 파일첨부가 없는 글쓰기
			   }
			   else {// 첨부파일 있는 경우 글쓰기
				    n= service.addPayment_withFile(epvo);
			   }
		   
		  
		   
		  } catch (Throwable e) {
			
			 e.printStackTrace();
		   }
		  
		   if(n==1) {
			   mav.setViewName("redirect:/t1/generalPayment_List.tw");
		   }
		  
	
	   return mav;
	}
	
	
	//임시저장함에 insert insert 시켜주기
	@RequestMapping(value = "/t1/generalPayment_saveWrite.tw", method= {RequestMethod.POST})
	 public ModelAndView saveWrite(Map<String,String> paraMap, ModelAndView mav, ElectronPayJshVO epvo, MultipartHttpServletRequest mrequest) {
		
		String mdate1 = mrequest.getParameter("mdate1");
		String mdate2 = mrequest.getParameter("mdate2");
		String mdate3 = mrequest.getParameter("mdate3");
		String fk_wiimdate1 = mrequest.getParameter("fk_wiimdate1");
		String fk_wiimdate2 = mrequest.getParameter("fk_wiimdate2");
		
		String mdate= mdate1+" "+mdate2+" ~ "+mdate3;
		String fk_wiimdate = fk_wiimdate1+" ~ "+fk_wiimdate2;
		
		epvo.setMdate(mdate);
		epvo.setFk_wiimdate(fk_wiimdate);
		
		// === 사용자가 쓴 글에 파일이 첨부되어 있는 것인지, 아니면 파일첨부가 안된것인지 구분을 지어주어야 한다. === 
		//  !!! 첨부파일이 있는 경우 작업 시작 !!! ===
		   
		   MultipartFile attach = epvo.getAttach();
		   
		   if(!attach.isEmpty()) { // 첨부파일이 있다라면
			// attach(첨부파일)가 비어있지 않으면(즉, 첨부파일이 있는 경우라면) 
			 //  System.out.println("파일이 존재합니다~");
			/*
	           1. 사용자가 보낸 첨부파일을 WAS(톰캣)의 특정 폴더에 저장해주어야 한다. 
	           >>> 파일이 업로드 되어질 특정 경로(폴더)지정해주기
	                                   우리는 WAS의 webapp/resources/files 라는 폴더로 지정해준다.
	                                   조심할 것은  Package Explorer 에서  files 라는 폴더를 만드는 것이 아니다.       
	        */     
		   // WAS의 webapp 의 절대경로를 알아와야 한다.
			  HttpSession session = mrequest.getSession();
			  String root = session.getServletContext().getRealPath("/");
			   
		  //  System.out.println("~~~~~~ webapp의 절대경로 =>" + root);
			  //~~~~~~ webapp의 절대경로 =>C:\NCS\workspace(spring)\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\Board\
			  
			  String path = root+"resources"+File.separator +"files";
			  /* File.separator 는 운영체제에서 사용하는 폴더와 파일의 구분자이다.
			           운영체제가 Windows 이라면 File.separator 는  "\" 이고,
			           운영체제가 UNIX, Linux 이라면  File.separator 는 "/" 이다. 
			  */
			  
			  // path 가 첨부파일이 저장될 WAS(톰캣)의 폴더가 된다.
		//	  System.out.println("~~~~~~ path =>" + path);
			  //~~~~~~ path =>C:\NCS\workspace(spring)\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\Board\resources\files
		   
			/*
			   2.파일첨부를 위한 변수의 설정 및 값을 초기화 한 후 파일 올리기 
			*/
			  String newFileName = "";
			  // WAS(톰캣)의 디스크에 저장될 파일명   
		   
			  byte[] bytes = null;
			  //첨부파일의 내용을 담는 것
			  
			  long fileSize =0;
			  
			  try {
				bytes = attach.getBytes();
				// 첨부파일의 내용물을 읽어오는 것
				
				String originalFilename = attach.getOriginalFilename();
				//originalFilename ==> "강아지.png"
				
				
				newFileName = fileManager.doFileUpload(bytes, originalFilename, path);
				 
			//	System.out.println(">> 확인용 newFileName=> " + newFileName);
				//>> 확인용 newFileName=> 202106031651411150647615171500.png

				
		     /*
	            3. ElectronPayJshVO epvo 에 fileName 값과 orgFilename 값과 fileSize 값을 넣어주기   
	         */
				epvo.setFileName(newFileName);
				// WAS(톰캣)에 저장될 파일명  (202106031651411150647615171500.png)
				epvo.setOrgFilename(originalFilename);
				// 게시판 페이지에서 첨부된 파일(강아지.png)을 보여줄 때 사용.
	            // 또한 사용자가 파일을 다운로드 할때 사용되어지는 파일명으로 사용.
				
				fileSize = attach.getSize(); // 첨부파일의 크기(단위는 byte임)
				epvo.setFileSize(String.valueOf(fileSize));
				
				
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		   
		   }
		   // === !!! 첨부파일이 있는 경우 작업 끝 !!! ===
		
		
		
		  // int n = service.addPayment(epvo); // <== 파일첨부가 없는 글쓰기
		
		
		 // === 파일첨부가 있는 글쓰기 또는 파일첨부가 없는 글쓰기로 나뉘어서 service호출하기
		    
		  int n = 0;
		   
		  try {
			  
			   //첨부파일이 없는 경우라면
			   if(attach.isEmpty()) {
				  
					n = service.savePayment(epvo); //insert(트랜잭션처리) <== 파일첨부가 없는 글쓰기
			   }
			   else {// 첨부파일 있는 경우 글쓰기
				    n= service.savePayment_withFile(epvo);
			   }
		   
		  
		   
		  } catch (Throwable e) {
			
			 e.printStackTrace();
		   }
		  
		   if(n==1) {
			   mav.setViewName("redirect:/t1/generalPayment_Write.tw");
		   }
		  
		
		return mav;
	}
	
	
	
	
	
	
	// ===  첨부파일 다운로드 받기
	   @RequestMapping(value="/download.tw")
	   public void download(HttpServletRequest request,HttpServletResponse response) {
		   
		   
		  String ano = request.getParameter("ano");
		  // 첨부파일이 있는 글번호
		  
		  Map<String,String> paraMap = new HashMap<>();
		  paraMap.put("ano", ano);
		  paraMap.put("searchType", "");
		  paraMap.put("searchWord", "");
		  paraMap.put("searchCategory", "");
		  
		  
		  /*
	     	첨부파일이 있는 글 번호에서
	        202106041000281212384472386100.jpg 처럼
	                   이러한 fileName값을 DB에서 가져와야 한다.
	                   또한 orgFilename 값도 DB에서 가져와야 한다. 
		  */
		  
		  response.setContentType("text/html; charset=UTF-8");
		  PrintWriter out = null;
		  
		  
		  try {
			  Integer.parseInt(ano);
			  
			  ElectronPayJshVO epvo = service.generalOneView(paraMap);
			  
			  if(epvo == null ||(epvo != null &&  epvo.getFileName() == null) ) {
				  //글이 없던지, 글이 존재하지만 첨부파일이 없는 경우.
				  
				  out = response.getWriter();
					// 웹브라우저 상에 메세지를 쓰기 위한 객체 생성
					
				  out.println("<script type='text/javascript'>alert('존재하지 않는 글 번호 이거나 첨부파일이 존재하지 않아서 파일 다운로드가 불가합니다!!'); history.back();</script>");
				  return; //종료
			  }
			  else {
				 String fileName = epvo.getFileName();
				 // 2020120809271535243254235235234.png 이것이 바로 WAS(톰캣) 디스크에 저장된 파일명이다. 
				 
				 String orgFilename= epvo.getOrgFilename();
				 // 강아지.png  다운로드시 보여줄 파일명
				 
				 
				 // 첨부파일이 저장되어 있는 WAS(톰캣)의 디스크 경로명을 알아와야만 다운로드를 해줄수 있다. 
		         // 이 경로는 우리가 파일첨부를 위해서 /addEnd.action 에서 설정해두었던 경로와 똑같아야 한다.
		         // WAS 의 webapp 의 절대경로를 알아와야 한다.
				
				  HttpSession session = request.getSession();
				  String root = session.getServletContext().getRealPath("/");
				   
			  //  System.out.println("~~~~~~ webapp의 절대경로 =>" + root);
				  //~~~~~~ webapp의 절대경로 =>C:\NCS\workspace(spring)\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\GroupWare\
				  
				  String path = root+"resources"+File.separator +"files";
				  /* File.separator 는 운영체제에서 사용하는 폴더와 파일의 구분자이다.
				           운영체제가 Windows 이라면 File.separator 는  "\" 이고,
				           운영체제가 UNIX, Linux 이라면  File.separator 는 "/" 이다. 
				  */
				  
				  // path 가 첨부파일이 저장될 WAS(톰캣)의 폴더가 된다.
			//	  System.out.println("~~~~~~ path =>" + path);
				  //~~~~~~ path =>C:\NCS\workspace(spring)\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\Board\resources\files
			   
				  
				  // **** file 다운로드 하기 **** // 
				  boolean flag = false; // file 다운로드의 성공,실패를 알려주는 용도 
				  flag = fileManager.doFileDownload(fileName, orgFilename, path, response);
		          // file 다운로드 성공시 flag 는 true, 
		          // file 다운로드 실패시 flag 는 false 를 가진다.
				  
				  if(!flag) {
					// 다운로드가 실패할 경우 메시지를 띄워준다.
			          
		               out = response.getWriter();
		               // 웹브라우저상에 메시지를 쓰기 위한 객체생성.
		               
		               out.println("<script type='text/javascript'>alert('파일 다운로드가 실패되었습니다!!'); history.back();</script>"); 
			           
				  }
				  
			  }
			  
		  }catch(NumberFormatException e) {
			  
			  try {
				out = response.getWriter();
				// 웹브라우저 상에 메세지를 쓰기 위한 객체 생성
				
				out.println("<script type='text/javascript'>alert('파일 다운로드가 불가합니다!!'); history.back();</script>");
			  } catch (IOException e1) {
				
				
			  }
			  
			  
			  
		  }catch(IOException e2) {
			  
		  }
		  
		  
		  
		  
	   }
	   
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
