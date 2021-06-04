package com.t1works.groupware.sia.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.t1works.groupware.bwb.model.MemberBwbVO;
import com.t1works.groupware.sia.model.ApprovalSiaVO;
import com.t1works.groupware.sia.service.InterMyDocumentSiaService;

@Component
@Controller
public class MyDocumentSiaController {
	
	@Autowired // Type에 따라 알아서 Bean 을 주입해준다.
	private InterMyDocumentSiaService service;	
	
	// 내문서함 - 수신함 - 일반결재문서 (기본으로 일반결재문서가 보여짐!)
	@RequestMapping(value="/t1/myDocuNorm_rec.tw")
	public String requiredLogin_myDocuNorm_rec(HttpServletRequest request, HttpServletResponse response) {
		
		request.setAttribute("ano", request.getParameter("ano"));
		request.setAttribute("astatus", request.getParameter("astatus"));
		request.setAttribute("fromDate", request.getParameter("fromDate"));
		request.setAttribute("toDate", request.getParameter("toDate"));
		request.setAttribute("ncat", request.getParameter("ncat"));
		request.setAttribute("sort", request.getParameter("sort"));		
		request.setAttribute("searchWord", request.getParameter("searchWord"));
		request.setAttribute("currentShowPageNo", request.getParameter("currentShowPageNo"));
		
		return "sia/myDocument/myDocuNorm_rec.gwTiles";		
	}
	
	// 내문서함 - 수신함 - 지출결재문서
	@RequestMapping(value="/t1/myDocuSpend_rec.tw")
	public String requiredLogin_myDocuSpend_rec(HttpServletRequest request, HttpServletResponse response) {
		
		request.setAttribute("ano", request.getParameter("ano"));
		request.setAttribute("astatus", request.getParameter("astatus"));
		request.setAttribute("fromDate", request.getParameter("fromDate"));
		request.setAttribute("toDate", request.getParameter("toDate"));
		request.setAttribute("scat", request.getParameter("scat"));
		request.setAttribute("sort", request.getParameter("sort"));		
		request.setAttribute("searchWord", request.getParameter("searchWord"));
		request.setAttribute("currentShowPageNo", request.getParameter("currentShowPageNo"));
		
		return "sia/myDocument/myDocuSpend_rec.gwTiles";		
	}
	
	// 내문서함 - 수신함 - 근태휴가결재문서
	@RequestMapping(value="/t1/myDocuVacation_rec.tw")
	public String requiredLogin_myDocuVacation_rec(HttpServletRequest request, HttpServletResponse response) {		
		
		request.setAttribute("ano", request.getParameter("ano"));
		request.setAttribute("astatus", request.getParameter("astatus"));
		request.setAttribute("fromDate", request.getParameter("fromDate"));
		request.setAttribute("toDate", request.getParameter("toDate"));
		request.setAttribute("vno", request.getParameter("vno"));
		request.setAttribute("sort", request.getParameter("sort"));		
		request.setAttribute("searchWord", request.getParameter("searchWord"));
		request.setAttribute("currentShowPageNo", request.getParameter("currentShowPageNo"));
		
		return "sia/myDocument/myDocuVacation_rec.gwTiles";		
	}
	
	
	// 내문서함 - 발신함 - 일반결재문서 (기본으로 일반결재문서가 보여짐!)
	@RequestMapping(value="/t1/myDocuNorm_send.tw")
	public String requiredLogin_myDocuNorm_send(HttpServletRequest request, HttpServletResponse response) {
		
		request.setAttribute("ano", request.getParameter("ano"));
		request.setAttribute("astatus", request.getParameter("astatus"));
		request.setAttribute("fromDate", request.getParameter("fromDate"));
		request.setAttribute("toDate", request.getParameter("toDate"));
		request.setAttribute("ncat", request.getParameter("ncat"));
		request.setAttribute("sort", request.getParameter("sort"));		
		request.setAttribute("searchWord", request.getParameter("searchWord"));
		request.setAttribute("currentShowPageNo", request.getParameter("currentShowPageNo"));
		
		return "sia/myDocument/myDocuNorm_send.gwTiles";		
	}
	
	// 내문서함 - 발신함 - 지출결재문서
	@RequestMapping(value="/t1/myDocuSpend_send.tw")
	public String requiredLogin_myDocuSpend_send(HttpServletRequest request, HttpServletResponse response) {
		
		request.setAttribute("ano", request.getParameter("ano"));
		request.setAttribute("astatus", request.getParameter("astatus"));
		request.setAttribute("fromDate", request.getParameter("fromDate"));
		request.setAttribute("toDate", request.getParameter("toDate"));
		request.setAttribute("scat", request.getParameter("scat"));
		request.setAttribute("sort", request.getParameter("sort"));		
		request.setAttribute("searchWord", request.getParameter("searchWord"));
		request.setAttribute("currentShowPageNo", request.getParameter("currentShowPageNo"));
		
		return "sia/myDocument/myDocuSpend_send.gwTiles";		
	}
	
	// 내문서함 - 발신함 - 근태휴가결재문서
	@RequestMapping(value="/t1/myDocuVacation_send.tw")
	public String requiredLogin_myDocuVacation_send(HttpServletRequest request, HttpServletResponse response) {
		
		request.setAttribute("ano", request.getParameter("ano"));
		request.setAttribute("astatus", request.getParameter("astatus"));
		request.setAttribute("fromDate", request.getParameter("fromDate"));
		request.setAttribute("toDate", request.getParameter("toDate"));
		request.setAttribute("vno", request.getParameter("vno"));
		request.setAttribute("sort", request.getParameter("sort"));		
		request.setAttribute("searchWord", request.getParameter("searchWord"));
		request.setAttribute("currentShowPageNo", request.getParameter("currentShowPageNo"));
		
		return "sia/myDocument/myDocuVacation_send.gwTiles";		
	}
	
	
	// 내문서함 - 임시저장함 - 일반결재문서 (기본으로 일반결재문서가 보여짐!)
	@RequestMapping(value="/t1/myDocuNorm_temp.tw")
	public String requiredLogin_myDocuNorm_temp(HttpServletRequest request, HttpServletResponse response) {
		
		request.setAttribute("ano", request.getParameter("ano"));
		request.setAttribute("astatus", request.getParameter("astatus"));
		request.setAttribute("fromDate", request.getParameter("fromDate"));
		request.setAttribute("toDate", request.getParameter("toDate"));
		request.setAttribute("ncat", request.getParameter("ncat"));
		request.setAttribute("sort", request.getParameter("sort"));		
		request.setAttribute("searchWord", request.getParameter("searchWord"));
		request.setAttribute("currentShowPageNo", request.getParameter("currentShowPageNo"));
		
		return "sia/myDocument/myDocuNorm_temp.gwTiles";		
	}
	
	// 내문서함 - 임시저장함 - 지출결재문서
	@RequestMapping(value="/t1/myDocuSpend_temp.tw")
	public String requiredLogin_myDocuSpend_temp(HttpServletRequest request, HttpServletResponse response) {
		
		request.setAttribute("ano", request.getParameter("ano"));
		request.setAttribute("astatus", request.getParameter("astatus"));
		request.setAttribute("fromDate", request.getParameter("fromDate"));
		request.setAttribute("toDate", request.getParameter("toDate"));
		request.setAttribute("scat", request.getParameter("scat"));
		request.setAttribute("sort", request.getParameter("sort"));		
		request.setAttribute("searchWord", request.getParameter("searchWord"));
		request.setAttribute("currentShowPageNo", request.getParameter("currentShowPageNo"));
		
		return "sia/myDocument/myDocuSpend_temp.gwTiles";		
	}
	
	// 내문서함 - 임시저장함 - 근태휴가결재문서
	@RequestMapping(value="/t1/myDocuVacation_temp.tw")
	public String requiredLogin_myDocuVacation_temp(HttpServletRequest request, HttpServletResponse response) {
		
		request.setAttribute("ano", request.getParameter("ano"));
		request.setAttribute("astatus", request.getParameter("astatus"));
		request.setAttribute("fromDate", request.getParameter("fromDate"));
		request.setAttribute("toDate", request.getParameter("toDate"));
		request.setAttribute("vno", request.getParameter("vno"));
		request.setAttribute("sort", request.getParameter("sort"));		
		request.setAttribute("searchWord", request.getParameter("searchWord"));
		request.setAttribute("currentShowPageNo", request.getParameter("currentShowPageNo"));
		
		return "sia/myDocument/myDocuVacation_temp.gwTiles";		
	}
	
	
	// 내문서함 - 결재완료문서 - 일반결재문서 (기본으로 일반결재문서가 보여짐!)
	@RequestMapping(value="/t1/myDocuNorm_complete.tw")
	public String requiredLogin_myDocuNorm_complete(HttpServletRequest request, HttpServletResponse response) {
		
		request.setAttribute("ano", request.getParameter("ano"));
		request.setAttribute("astatus", request.getParameter("astatus"));
		request.setAttribute("fromDate", request.getParameter("fromDate"));
		request.setAttribute("toDate", request.getParameter("toDate"));
		request.setAttribute("ncat", request.getParameter("ncat"));
		request.setAttribute("sort", request.getParameter("sort"));		
		request.setAttribute("searchWord", request.getParameter("searchWord"));
		request.setAttribute("currentShowPageNo", request.getParameter("currentShowPageNo"));
				
		return "sia/myDocument/myDocuNorm_complete.gwTiles";		
	}
	
	// 내문서함 - 결재완료문서 - 지출결재문서
	@RequestMapping(value="/t1/myDocuSpend_complete.tw")
	public String requiredLogin_myDocuSpend_complete(HttpServletRequest request, HttpServletResponse response) {
		
		request.setAttribute("ano", request.getParameter("ano"));
		request.setAttribute("astatus", request.getParameter("astatus"));
		request.setAttribute("fromDate", request.getParameter("fromDate"));
		request.setAttribute("toDate", request.getParameter("toDate"));
		request.setAttribute("scat", request.getParameter("scat"));
		request.setAttribute("sort", request.getParameter("sort"));		
		request.setAttribute("searchWord", request.getParameter("searchWord"));
		request.setAttribute("currentShowPageNo", request.getParameter("currentShowPageNo"));
		
		return "sia/myDocument/myDocuSpend_complete.gwTiles";
	}
	
	// 내문서함 - 결재완료문서 - 근태휴가결재문서
	@RequestMapping(value="/t1/myDocuVacation_complete.tw")
	public String requiredLogin_myDocuVacation_complete(HttpServletRequest request, HttpServletResponse response) {
		
		request.setAttribute("ano", request.getParameter("ano"));
		request.setAttribute("astatus", request.getParameter("astatus"));
		request.setAttribute("fromDate", request.getParameter("fromDate"));
		request.setAttribute("toDate", request.getParameter("toDate"));
		request.setAttribute("vno", request.getParameter("vno"));
		request.setAttribute("sort", request.getParameter("sort"));		
		request.setAttribute("searchWord", request.getParameter("searchWord"));
		request.setAttribute("currentShowPageNo", request.getParameter("currentShowPageNo"));
				
		return "sia/myDocument/myDocuVacation_complete.gwTiles";		
	}
	
	//////////////////////////////////////////////////////////////////////
	
	// 내문서함 - 수신함 - 일반결재문서에 해당하는 문서 조회하기
	@ResponseBody
	@RequestMapping(value="/t1/norm_reclist.tw", produces="text/plain;charset=UTF-8")
	public String norm_reclist(HttpServletRequest request) {
		
		String astatus = request.getParameter("astatus");
		String fromDate = request.getParameter("fromDate");
		String toDate = request.getParameter("toDate");
		String ncat = request.getParameter("ncat");
		String sort = request.getParameter("sort");
		String searchWord = request.getParameter("searchWord");
		String currentShowPageNo = request.getParameter("currentShowPageNo");
				
		if(astatus == null || (!"0".equals(astatus) && !"1".equals(astatus) && !"2".equals(astatus) && !"3".equals(astatus))) {
			astatus = "";
		}
		if(fromDate == null || toDate == null) {
			fromDate = "";
			toDate = "";
		}
		if(sort == null || (!"atitle".equals(sort) && !"ano".equals(sort))) {
			sort = "";
		}
		if(searchWord == null || "".equals(searchWord) || searchWord.trim().isEmpty()) {
			searchWord = "";
		}
		
		String a = "";
		if(ncat.length() == 0) {
			a = "";
		}
		else {
			String[] ncatArr = ncat.split(","); // [2,3]
			int cnt = 0;
			for(int i=0; i<ncatArr.length; i++) {
				cnt++;
				
				if(cnt < ncatArr.length) {
					a += "'"+ncatArr[i]+"',";
				}
				else {
					a += "'"+ncatArr[i]+"'";
				}
			}
		}
		
		if(currentShowPageNo == null || currentShowPageNo == "") {
			currentShowPageNo = "1";
		}
		
		try {
			Integer.parseInt(currentShowPageNo); 
		} catch (Exception e) {
			currentShowPageNo = "1";
		}
		
		int sizePerPage = 10;
		
		int startRno = ((Integer.parseInt(currentShowPageNo) - 1) * sizePerPage) + 1;
		int endRno = startRno + sizePerPage - 1;
		
		HttpSession session = request.getSession();
		MemberBwbVO loginuser = (MemberBwbVO) session.getAttribute("loginuser");		
		String userid = loginuser.getEmployeeid();		
		
		Map<String, String> paraMap = new HashMap<>();		
		paraMap.put("astatus", astatus);
		paraMap.put("fromDate", fromDate);
		paraMap.put("toDate", toDate);
		paraMap.put("sort", sort);
		paraMap.put("searchWord", searchWord);
		paraMap.put("a", a);
		paraMap.put("startRno", String.valueOf(startRno));
		paraMap.put("endRno", String.valueOf(endRno));
		paraMap.put("userid", userid);
		
		List<ApprovalSiaVO> approvalvo = service.getnorm_reclist(paraMap);
		
		JSONArray jsonArr = new JSONArray();
		
		if(approvalvo != null) {
			for(ApprovalSiaVO appvo : approvalvo) {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("atitle", appvo.getAtitle());
				jsonObj.put("ano", appvo.getAno());
				jsonObj.put("ncatname", appvo.getNcatname());
				jsonObj.put("astatus", appvo.getAstatus());
				jsonObj.put("asdate", appvo.getAsdate());
				jsonObj.put("rno", appvo.getRno());
				
				jsonArr.put(jsonObj);
			}
		}
		return jsonArr.toString();
	}
	
	
	// 검색에 해당하는 수신함 - 일반결재 글의 총 페이지수를 알아오기
	@ResponseBody
	@RequestMapping(value="/t1/getTotalPage.tw", method= {RequestMethod.GET})
	public String getTotalPage(HttpServletRequest request) {
		
		String astatus = request.getParameter("astatus");
		String fromDate = request.getParameter("fromDate");
		String toDate = request.getParameter("toDate");
		String ncat = request.getParameter("ncat");
		String sort = request.getParameter("sort");
		String searchWord = request.getParameter("searchWord");
		String sizePerPage = request.getParameter("sizePerPage");
		
		if(astatus == null || (!"0".equals(astatus) && !"1".equals(astatus) && !"2".equals(astatus) && !"3".equals(astatus))) {
			astatus = "";
		}
		if(fromDate == null || toDate == null) {
			fromDate = "";
			toDate = "";
		}
		if(sort == null || (!"atitle".equals(sort) && !"ano".equals(sort))) {
			sort = "";
		}
		if(searchWord == null || "".equals(searchWord) || searchWord.trim().isEmpty()) {
			searchWord = "";
		}
		
		String a = "";
		if(ncat.length() == 0) {
			a = "";
		}
		else {
			String[] ncatArr = ncat.split(","); // [2,3]
			int cnt = 0;
			for(int i=0; i<ncatArr.length; i++) {
				cnt++;
				
				if(cnt < ncatArr.length) {
					a += "'"+ncatArr[i]+"',";
				}
				else {
					a += "'"+ncatArr[i]+"'";
				}				
			}
		}
		
		Map<String, String> paraMap = new HashMap<>();		
		paraMap.put("astatus", astatus);
		paraMap.put("fromDate", fromDate);
		paraMap.put("toDate", toDate);
		paraMap.put("sort", sort);
		paraMap.put("searchWord", searchWord);
		paraMap.put("a", a);
		paraMap.put("sizePerPage", sizePerPage);		
		
		// 검색에 해당하는 수신함 - 일반결재 글의 총 페이지수를 알아오기
		int totalPage = service.getTotalPage(paraMap);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("totalPage", totalPage); 
		
		return jsonObj.toString();		
	}
	
	
	// 내문서함 - 수신함 - 일반결재 문서 한 개 상세보기
	@RequestMapping(value="/t1/myDocuNorm_detail.tw", produces="text/plain;charset=UTF-8")
	public ModelAndView myDocuNorm_detail(HttpServletRequest request, ModelAndView mav) {
		String ano = request.getParameter("ano");		
		String ncatname = request.getParameter("ncatname");
				
		mav.addObject("ano", request.getParameter("ano"));
		mav.addObject("ncatname", request.getParameter("ncatname"));		
		mav.addObject("astatus", request.getParameter("astatus"));
		mav.addObject("fromDate", request.getParameter("fromDate"));
		mav.addObject("toDate", request.getParameter("toDate"));
		mav.addObject("ncat", request.getParameter("ncat"));
		mav.addObject("sort", request.getParameter("sort"));		
		mav.addObject("searchWord", request.getParameter("searchWord"));
		mav.addObject("currentShowPageNo", request.getParameter("currentShowPageNo"));
		
		String employeeid = null;
		
		HttpSession session = request.getSession();
		MemberBwbVO loginuser = (MemberBwbVO)session.getAttribute("loginuser");
		
		if(loginuser != null) {
			employeeid = loginuser.getEmployeeid(); 
		}
		
		Map<String, String> paraMap = new HashMap<>();
		paraMap.put("ano", ano);
		paraMap.put("ncatname", ncatname);
		paraMap.put("employeeid", employeeid);
		
		try {
			ApprovalSiaVO avo = service.myDocuNorm_detail(paraMap);			
			
			mav.addObject("avo", avo);
			
		} catch (NumberFormatException e) {
			
		}
		
		mav.setViewName("sia/myDocumentDetail/myDocuNorm_detail.gwTiles");
		
		return mav;
	}
		
	
	//////////////////////////////////////////////////////////////////////
	
	// 내문서함 - 수신함 - 지출결재문서에 해당하는 문서 조회하기 (Ajax 처리)
	@ResponseBody
	@RequestMapping(value="/t1/spend_reclist.tw", produces="text/plain;charset=UTF-8")
	public String spend_reclist(HttpServletRequest request) {
		
		String astatus = request.getParameter("astatus");
		String fromDate = request.getParameter("fromDate");
		String toDate = request.getParameter("toDate");
		String scat = request.getParameter("scat");
		String sort = request.getParameter("sort");
		String searchWord = request.getParameter("searchWord");
		String currentShowPageNo = request.getParameter("currentShowPageNo");
				
		if(astatus == null || (!"0".equals(astatus) && !"1".equals(astatus) && !"2".equals(astatus) && !"3".equals(astatus))) {
			astatus = "";
		}
		if(fromDate == null || toDate == null) {
			fromDate = "";
			toDate = "";
		}
		if(sort == null || (!"atitle".equals(sort) && !"ano".equals(sort))) {
			sort = "";
		}
		if(searchWord == null || "".equals(searchWord) || searchWord.trim().isEmpty()) {
			searchWord = "";
		}
		
		String a = "";
		if(scat.length() == 0) {
			a = "";
		}
		else {
			String[] scatArr = scat.split(","); // [2,3]
			int cnt = 0;
			for(int i=0; i<scatArr.length; i++) {
				cnt++;
				
				if(cnt < scatArr.length) {
					a += "'"+scatArr[i]+"',";
				}
				else {
					a += "'"+scatArr[i]+"'";
				}
			}
		}
		
		if(currentShowPageNo == null || currentShowPageNo == "") {
			currentShowPageNo = "1";
		}
		
		try {
			Integer.parseInt(currentShowPageNo); 
		} catch (Exception e) {
			currentShowPageNo = "1";
		}
		
		int sizePerPage = 10;
		
		int startRno = ((Integer.parseInt(currentShowPageNo) - 1) * sizePerPage) + 1;
		int endRno = startRno + sizePerPage - 1;
		
		HttpSession session = request.getSession();
		MemberBwbVO loginuser = (MemberBwbVO) session.getAttribute("loginuser");		
		String userid = loginuser.getEmployeeid();
		
		Map<String, String> paraMap = new HashMap<>();		
		paraMap.put("astatus", astatus);
		paraMap.put("fromDate", fromDate);
		paraMap.put("toDate", toDate);
		paraMap.put("sort", sort);
		paraMap.put("searchWord", searchWord);
		paraMap.put("a", a);
		paraMap.put("startRno", String.valueOf(startRno));
		paraMap.put("endRno", String.valueOf(endRno));
		paraMap.put("userid", userid);
		
		List<ApprovalSiaVO> approvalvo = service.getSpend_reclist(paraMap);
		
		JSONArray jsonArr = new JSONArray();
		
		
		if(approvalvo != null) {
			for(ApprovalSiaVO appvo : approvalvo) {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("atitle", appvo.getAtitle());
				jsonObj.put("ano", appvo.getAno());
				jsonObj.put("scatname", appvo.getScatname());
				jsonObj.put("astatus", appvo.getAstatus());
				jsonObj.put("asdate", appvo.getAsdate());
				jsonObj.put("rno", appvo.getRno());
				
				jsonArr.put(jsonObj);
			}
		}		
		
		return jsonArr.toString();
	}
	
	
	// 검색에 해당하는 수신함 - 지출결재 글의 총 페이지수를 알아오기
	@ResponseBody
	@RequestMapping(value="/t1/getSpendTotalPage.tw", method= {RequestMethod.GET})
	public String getSpendTotalPage(HttpServletRequest request) {
		
		String astatus = request.getParameter("astatus");
		String fromDate = request.getParameter("fromDate");
		String toDate = request.getParameter("toDate");
		String scat = request.getParameter("scat");
		String sort = request.getParameter("sort");
		String searchWord = request.getParameter("searchWord");
		String sizePerPage = request.getParameter("sizePerPage");
		
		if(astatus == null || (!"0".equals(astatus) && !"1".equals(astatus) && !"2".equals(astatus) && !"3".equals(astatus))) {
			astatus = "";
		}
		if(fromDate == null || toDate == null) {
			fromDate = "";
			toDate = "";
		}
		if(sort == null || (!"atitle".equals(sort) && !"ano".equals(sort))) {
			sort = "";
		}
		if(searchWord == null || "".equals(searchWord) || searchWord.trim().isEmpty()) {
			searchWord = "";
		}
		
		String a = "";
		if(scat.length() == 0) {
			a = "";
		}
		else {
			String[] scatArr = scat.split(","); // [2,3]
			int cnt = 0;
			for(int i=0; i<scatArr.length; i++) {
				cnt++;
				
				if(cnt < scatArr.length) {
					a += "'"+scatArr[i]+"',";
				}
				else {
					a += "'"+scatArr[i]+"'";
				}				
			}
		}
		
		Map<String, String> paraMap = new HashMap<>();		
		paraMap.put("astatus", astatus);
		paraMap.put("fromDate", fromDate);
		paraMap.put("toDate", toDate);
		paraMap.put("sort", sort);
		paraMap.put("searchWord", searchWord);
		paraMap.put("a", a);
		paraMap.put("sizePerPage", sizePerPage);		
		
		// 검색에 해당하는 수신함 - 지출결재 글의 총 페이지수를 알아오기
		int totalPage = service.getSpendTotalPage(paraMap);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("totalPage", totalPage); 
				
		return jsonObj.toString();		
	}
	
	
	// 내문서함 - 수신함 - 지출 결재 문서 한 개 상세보기
	@RequestMapping(value="/t1/myDocuSpend_detail.tw", produces="text/plain;charset=UTF-8")
	public ModelAndView myDocuSpend_detail(HttpServletRequest request, ModelAndView mav) {
		String ano = request.getParameter("ano");		
		String scatname = request.getParameter("scatname");
		
		mav.addObject("ano", request.getParameter("ano"));
		mav.addObject("scatname", request.getParameter("scatname"));		
		mav.addObject("astatus", request.getParameter("astatus"));
		mav.addObject("fromDate", request.getParameter("fromDate"));
		mav.addObject("toDate", request.getParameter("toDate"));
		mav.addObject("scat", request.getParameter("scat"));
		mav.addObject("sort", request.getParameter("sort"));		
		mav.addObject("searchWord", request.getParameter("searchWord"));
		mav.addObject("currentShowPageNo", request.getParameter("currentShowPageNo"));
		
		String employeeid = null;
		
		HttpSession session = request.getSession();
		MemberBwbVO loginuser = (MemberBwbVO)session.getAttribute("loginuser");
		
		if(loginuser != null) {
			employeeid = loginuser.getEmployeeid(); 
		}
		
		Map<String, String> paraMap = new HashMap<>();
		paraMap.put("ano", ano);
		paraMap.put("scatname", scatname);
		paraMap.put("employeeid", employeeid);
		
		try {
			ApprovalSiaVO avo = service.myDocuSpend_detail(paraMap);			
			mav.addObject("avo", avo);			
		} catch (NumberFormatException e) {
			
		}
		
		mav.setViewName("sia/myDocumentDetail/myDocuSpend_detail.gwTiles");
		
		return mav;
	}
	
	//////////////////////////////////////////////////////////////////////

	
	// 내문서함 - 수신함 - 근태결재문서에 해당하는 문서 조회하기
	@ResponseBody
	@RequestMapping(value="/t1/vacation_reclist.tw", produces="text/plain;charset=UTF-8")
	public String vacation_reclist(HttpServletRequest request) {
					
		String astatus = request.getParameter("astatus");
		String fromDate = request.getParameter("fromDate");
		String toDate = request.getParameter("toDate");
		String vno = request.getParameter("vno");
		String sort = request.getParameter("sort");
		String searchWord = request.getParameter("searchWord");
		String currentShowPageNo = request.getParameter("currentShowPageNo");		
		
		if(astatus == null || (!"0".equals(astatus) && !"1".equals(astatus) && !"2".equals(astatus) && !"3".equals(astatus))) {
			astatus = "";
		}
		if(fromDate == null || toDate == null) {
			fromDate = "";
			toDate = "";
		}
		if(sort == null || (!"atitle".equals(sort) && !"ano".equals(sort))) {
			sort = "";
		}
		if(searchWord == null || "".equals(searchWord) || searchWord.trim().isEmpty()) {
			searchWord = "";
		}
		
		String a = "";
		if(vno.length() == 0) {
			a = "";
		}
		else {
			String[] vnoArr = vno.split(","); // [2,3]
			int cnt = 0;
			for(int i=0; i<vnoArr.length; i++) {
				cnt++;
				
				if(cnt < vnoArr.length) {
					a += "'"+vnoArr[i]+"',";
				}
				else {
					a += "'"+vnoArr[i]+"'";
				}
			}
		}
		
		if(currentShowPageNo == null || currentShowPageNo == "") {
			currentShowPageNo = "1";
		}
		
		try {
			Integer.parseInt(currentShowPageNo); 
		} catch (Exception e) {
			currentShowPageNo = "1";
		}
		
		int sizePerPage = 10;
		
		int startRno = ((Integer.parseInt(currentShowPageNo) - 1) * sizePerPage) + 1;
		int endRno = startRno + sizePerPage - 1;
		
		HttpSession session = request.getSession();
		MemberBwbVO loginuser = (MemberBwbVO) session.getAttribute("loginuser");		
		String userid = loginuser.getEmployeeid();
		
		Map<String, String> paraMap = new HashMap<>();
		paraMap.put("astatus", astatus);
		paraMap.put("fromDate", fromDate);
		paraMap.put("toDate", toDate);
		paraMap.put("sort", sort);
		paraMap.put("searchWord", searchWord);
		paraMap.put("a", a);
		paraMap.put("startRno", String.valueOf(startRno));
		paraMap.put("endRno", String.valueOf(endRno));
		paraMap.put("userid", userid);
		
		List<ApprovalSiaVO> approvalvo = service.getVacation_reclist(paraMap);		
		
		JSONArray jsonArr = new JSONArray();		
		
		if(approvalvo != null) {
			for(ApprovalSiaVO appvo : approvalvo) {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("atitle", appvo.getAtitle());
				jsonObj.put("ano", appvo.getAno());
				jsonObj.put("vcatname", appvo.getVcatname());
				jsonObj.put("astatus", appvo.getAstatus());
				jsonObj.put("asdate", appvo.getAsdate());
				jsonObj.put("rno", appvo.getRno());
				
				jsonArr.put(jsonObj);
			}
		}		
		
		return jsonArr.toString();
	}
	
	
	// 검색에 해당하는 수신함 - 근태결재 글의 총 페이지수를 알아오기
	@ResponseBody
	@RequestMapping(value="/t1/getVacationTotalPage.tw", method= {RequestMethod.GET})
	public String getVacationTotalPage(HttpServletRequest request) {
		
		String astatus = request.getParameter("astatus");
		String fromDate = request.getParameter("fromDate");
		String toDate = request.getParameter("toDate");
		String vno = request.getParameter("vno");
		String sort = request.getParameter("sort");
		String searchWord = request.getParameter("searchWord");
		String sizePerPage = request.getParameter("sizePerPage");
		
		if(astatus == null || (!"0".equals(astatus) && !"1".equals(astatus) && !"2".equals(astatus) && !"3".equals(astatus))) {
			astatus = "";
		}
		if(fromDate == null || toDate == null) {
			fromDate = "";
			toDate = "";
		}
		if(sort == null || (!"atitle".equals(sort) && !"ano".equals(sort))) {
			sort = "";
		}
		if(searchWord == null || "".equals(searchWord) || searchWord.trim().isEmpty()) {
			searchWord = "";
		}
		
		String a = "";
		if(vno.length() == 0) {
			a = "";
		}
		else {
			String[] vnoArr = vno.split(","); // [2,3]
			int cnt = 0;
			for(int i=0; i<vnoArr.length; i++) {
				cnt++;
				
				if(cnt < vnoArr.length) {
					a += "'"+vnoArr[i]+"',";
				}
				else {
					a += "'"+vnoArr[i]+"'";
				}				
			}
		}
		
		Map<String, String> paraMap = new HashMap<>();
		paraMap.put("astatus", astatus);
		paraMap.put("fromDate", fromDate);
		paraMap.put("toDate", toDate);
		paraMap.put("sort", sort);
		paraMap.put("searchWord", searchWord);
		paraMap.put("a", a);
		paraMap.put("sizePerPage", sizePerPage);		
		
		// 검색에 해당하는 수신함 - 근태결재 글의 총 페이지수를 알아오기
		int totalPage = service.getVacationTotalPage(paraMap);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("totalPage", totalPage);		
		
		return jsonObj.toString();
	}
	
	
	// 내문서함 - 수신함 - 근태결재문서 한 개 상세보기
	@RequestMapping(value="/t1/myDocuVacation_detail.tw", produces="text/plain;charset=UTF-8")
	public ModelAndView myDocuVacation_detail(HttpServletRequest request, ModelAndView mav) {
		String ano = request.getParameter("ano");		
		String vcatname = request.getParameter("vcatname");
		
		mav.addObject("ano", request.getParameter("ano"));
		mav.addObject("vcatname", request.getParameter("vcatname"));		
		mav.addObject("astatus", request.getParameter("astatus"));
		mav.addObject("fromDate", request.getParameter("fromDate"));
		mav.addObject("toDate", request.getParameter("toDate"));
		mav.addObject("vno", request.getParameter("vno"));
		mav.addObject("sort", request.getParameter("sort"));		
		mav.addObject("searchWord", request.getParameter("searchWord"));
		mav.addObject("currentShowPageNo", request.getParameter("currentShowPageNo"));
		
		
		String employeeid = null;
		
		HttpSession session = request.getSession();
		MemberBwbVO loginuser = (MemberBwbVO)session.getAttribute("loginuser");
		
		if(loginuser != null) {
			employeeid = loginuser.getEmployeeid(); 
		}
		
		Map<String, String> paraMap = new HashMap<>();
		paraMap.put("ano", ano);
		paraMap.put("vcatname", vcatname);
		paraMap.put("employeeid", employeeid);
		
		try {
			// 근태결재문서 한 개 상세보기
			ApprovalSiaVO avo = service.myDocuVacation_detail(paraMap);						
			
			mav.addObject("avo", avo);		
			
		} catch (NumberFormatException e) {
			
		}
		
		
		mav.setViewName("sia/myDocumentDetail/myDocuVacation_detail.gwTiles");
		
		return mav;
	}
	
	//////////////////////////////////////////////////////////////////////
	
	// 내문서함 - 수신함 - 결재의견 작성하기
	@ResponseBody
	@RequestMapping(value="/t1/addOpinion.tw", method= {RequestMethod.POST}, produces="text/plain;charset=UTF-8")
	public String addOpinion(ApprovalSiaVO avo) { // 그대로 보여주는 것이기 때문에 return 타입은 String
		
		int n = service.addOpinion(avo);
		
		JSONObject jsonObj = new JSONObject();		
		jsonObj.put("n", n);
		jsonObj.put("employeeid", avo.getEmployeeid());
				
		return jsonObj.toString();
	}
	
	
	// 내문서함 - 수신함 - 결재의견 조회하기
	@ResponseBody
	@RequestMapping(value="/t1/opinionList.tw", method= {RequestMethod.GET}, produces="text/plain;charset=UTF-8")
	public String readOpinion(HttpServletRequest request) {
		
		String parentAno = request.getParameter("parentAno");
		
		List<ApprovalSiaVO> opinionList = service.getOpinionList(parentAno);
		
		JSONArray jsonArr = new JSONArray(); // []
		
		if(opinionList != null) {
			for(ApprovalSiaVO avo : opinionList) {
				JSONObject jsonObj = new JSONObject();				
				jsonObj.put("dname", avo.getDname());
				jsonObj.put("name", avo.getName());
				jsonObj.put("pname", avo.getPname());
				jsonObj.put("odate", avo.getOdate());
				jsonObj.put("ocontent", avo.getOcontent());
				
				jsonArr.put(jsonObj);
			}
		}
		
		return jsonArr.toString();		
	}
	
	
	// 내문서함 - 수신함 - 결재버튼 클릭
	@ResponseBody
	@RequestMapping(value="/t1/approval.tw", method= {RequestMethod.POST}, produces="text/plain;charset=UTF-8")
	public String approval(HttpServletRequest request) { 
		
		String ano = request.getParameter("ano");
		String astatus = request.getParameter("astatus");
		String arecipient1 = request.getParameter("arecipient1");
		String arecipient2 = request.getParameter("arecipient2");
		String arecipient3 = request.getParameter("arecipient3");	
		String employeeid = request.getParameter("employeeid");
		String fk_pcode = request.getParameter("fk_pcode");
				
		if(arecipient2 == null) {
			arecipient2 = "";
		}
		if(arecipient3 == null) {
			arecipient3 = "";
		}
		
		
		Map<String, String> paraMap = new HashMap<>(); 
		paraMap.put("ano", ano);  
		paraMap.put("astatus", astatus);
		paraMap.put("arecipient1", arecipient1);
		paraMap.put("arecipient2", arecipient2);
		paraMap.put("arecipient3", arecipient3);
		paraMap.put("employeeid", employeeid);
		paraMap.put("fk_pcode", fk_pcode); 
	 
		int n = service.approval(paraMap); 
		
		
		JSONObject jsonObj = new JSONObject();		
		jsonObj.put("n", n);
		/*
		 * jsonObj.put("employeeid", avo.getEmployeeid()); jsonObj.put("arecipient1",
		 * avo.getArecipient1()); jsonObj.put("arecipient2", avo.getArecipient2());
		 * jsonObj.put("arecipient3", avo.getArecipient3()); jsonObj.put("fk_pcode",
		 * avo.getFk_pcode());
		 */
	
		return jsonObj.toString();
	}
	
	
	// 내문서함 - 수신함 - 반려버튼 클릭
	@ResponseBody
	@RequestMapping(value="/t1/reject.tw", method= {RequestMethod.POST}, produces="text/plain;charset=UTF-8")
	public String reject(HttpServletRequest request) { 
		
		String ano = request.getParameter("ano");	
	 
		int n = service.reject(ano); 	
		
		JSONObject jsonObj = new JSONObject();		
		jsonObj.put("n", n);
				
		return jsonObj.toString();
	}
	
	//////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////

	
	// 내문서함 - 발신함 - 일반결재문서에 해당하는 문서 조회하기
	@ResponseBody
	@RequestMapping(value="/t1/norm_sendlist.tw", produces="text/plain;charset=UTF-8")
	public String norm_sendlist(HttpServletRequest request) {
		
		String astatus = request.getParameter("astatus");
		String fromDate = request.getParameter("fromDate");
		String toDate = request.getParameter("toDate");
		String ncat = request.getParameter("ncat");
		String sort = request.getParameter("sort");
		String searchWord = request.getParameter("searchWord");
		String currentShowPageNo = request.getParameter("currentShowPageNo");
				
		if(astatus == null || (!"0".equals(astatus) && !"1".equals(astatus) && !"2".equals(astatus) && !"3".equals(astatus))) {
			astatus = "";
		}
		if(fromDate == null || toDate == null) {
			fromDate = "";
			toDate = "";
		}
		if(sort == null || (!"atitle".equals(sort) && !"ano".equals(sort))) {
			sort = "";
		}
		if(searchWord == null || "".equals(searchWord) || searchWord.trim().isEmpty()) {
			searchWord = "";
		}
		
		String a = "";
		if(ncat.length() == 0) {
			a = "";
		}
		else {
			String[] ncatArr = ncat.split(","); // [2,3]
			int cnt = 0;
			for(int i=0; i<ncatArr.length; i++) {
				cnt++;
				
				if(cnt < ncatArr.length) {
					a += "'"+ncatArr[i]+"',";
				}
				else {
					a += "'"+ncatArr[i]+"'";
				}
			}
		}
		
		if(currentShowPageNo == null || currentShowPageNo == "") {
			currentShowPageNo = "1";
		}
		
		try {
			Integer.parseInt(currentShowPageNo); 
		} catch (Exception e) {
			currentShowPageNo = "1";
		}
		
		int sizePerPage = 10;
		
		int startRno = ((Integer.parseInt(currentShowPageNo) - 1) * sizePerPage) + 1;
		int endRno = startRno + sizePerPage - 1;
		
		HttpSession session = request.getSession();
		MemberBwbVO loginuser = (MemberBwbVO) session.getAttribute("loginuser");		
		String userid = loginuser.getEmployeeid();
		
		Map<String, String> paraMap = new HashMap<>();
		paraMap.put("astatus", astatus);
		paraMap.put("fromDate", fromDate);
		paraMap.put("toDate", toDate);
		paraMap.put("sort", sort);
		paraMap.put("searchWord", searchWord);
		paraMap.put("a", a);
		paraMap.put("startRno", String.valueOf(startRno));
		paraMap.put("endRno", String.valueOf(endRno));
		paraMap.put("userid", userid);
		
		List<ApprovalSiaVO> approvalvo = service.getnorm_sendlist(paraMap);
		
		JSONArray jsonArr = new JSONArray();
		
		if(approvalvo != null) {
			for(ApprovalSiaVO appvo : approvalvo) {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("atitle", appvo.getAtitle());
				jsonObj.put("ano", appvo.getAno());
				jsonObj.put("ncatname", appvo.getNcatname());
				jsonObj.put("astatus", appvo.getAstatus());
				jsonObj.put("asdate", appvo.getAsdate());
				jsonObj.put("rno", appvo.getRno());
				
				jsonArr.put(jsonObj);
			}
		}
		return jsonArr.toString();
	}
	
	
	// 검색에 해당하는 발신함 - 일반결재 글의 총 페이지수를 알아오기
	@ResponseBody
	@RequestMapping(value="/t1/getNormSendTotalPage.tw", method= {RequestMethod.GET})
	public String getNormSendTotalPage(HttpServletRequest request) {
		
		String astatus = request.getParameter("astatus");
		String fromDate = request.getParameter("fromDate");
		String toDate = request.getParameter("toDate");
		String ncat = request.getParameter("ncat");
		String sort = request.getParameter("sort");
		String searchWord = request.getParameter("searchWord");
		String sizePerPage = request.getParameter("sizePerPage");
		
		if(astatus == null || (!"0".equals(astatus) && !"1".equals(astatus) && !"2".equals(astatus) && !"3".equals(astatus))) {
			astatus = "";
		}
		if(fromDate == null || toDate == null) {
			fromDate = "";
			toDate = "";
		}
		if(sort == null || (!"atitle".equals(sort) && !"ano".equals(sort))) {
			sort = "";
		}
		if(searchWord == null || "".equals(searchWord) || searchWord.trim().isEmpty()) {
			searchWord = "";
		}
		
		String a = "";
		if(ncat.length() == 0) {
			a = "";
		}
		else {
			String[] ncatArr = ncat.split(","); // [2,3]
			int cnt = 0;
			for(int i=0; i<ncatArr.length; i++) {
				cnt++;
				
				if(cnt < ncatArr.length) {
					a += "'"+ncatArr[i]+"',";
				}
				else {
					a += "'"+ncatArr[i]+"'";
				}				
			}
		}
		
		Map<String, String> paraMap = new HashMap<>();		  
		paraMap.put("astatus", astatus);
		paraMap.put("fromDate", fromDate);
		paraMap.put("toDate", toDate);
		paraMap.put("sort", sort);
		paraMap.put("searchWord", searchWord);
		paraMap.put("a", a);
		paraMap.put("sizePerPage", sizePerPage);		
		
		// 검색에 해당하는 발신함 - 일반결재 글의 총 페이지수를 알아오기
		int totalPage = service.getNormSendTotalPage(paraMap);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("totalPage", totalPage); 
		
		return jsonObj.toString();		
	}
	
	
	// 내문서함 - 발신함 - 일반결재 문서 한 개 상세보기
	@RequestMapping(value="/t1/myDocuNorm_send_detail.tw", produces="text/plain;charset=UTF-8")
	public ModelAndView myDocuNorm_send_detail(HttpServletRequest request, ModelAndView mav) {
		String ano = request.getParameter("ano");		
		String ncatname = request.getParameter("ncatname");
				
		mav.addObject("ano", request.getParameter("ano"));
		mav.addObject("ncatname", request.getParameter("ncatname"));		
		mav.addObject("astatus", request.getParameter("astatus"));
		mav.addObject("fromDate", request.getParameter("fromDate"));
		mav.addObject("toDate", request.getParameter("toDate"));
		mav.addObject("ncat", request.getParameter("ncat"));
		mav.addObject("sort", request.getParameter("sort"));		
		mav.addObject("searchWord", request.getParameter("searchWord"));
		mav.addObject("currentShowPageNo", request.getParameter("currentShowPageNo"));
		
		String employeeid = null;
		
		HttpSession session = request.getSession();
		MemberBwbVO loginuser = (MemberBwbVO)session.getAttribute("loginuser");
		
		if(loginuser != null) {
			employeeid = loginuser.getEmployeeid(); 
		}
		
		Map<String, String> paraMap = new HashMap<>();
		paraMap.put("ano", ano);
		paraMap.put("ncatname", ncatname);
		paraMap.put("employeeid", employeeid);
		
		try {
			ApprovalSiaVO avo = service.myDocuNorm_send_detail(paraMap);			
			
			mav.addObject("avo", avo);
			
		} catch (NumberFormatException e) {
			
		}
		
		mav.setViewName("sia/myDocumentDetail/myDocuNorm_send_detail.gwTiles");
		
		return mav;
	}
		
	
	//////////////////////////////////////////////////////////////////////
	
	// 내문서함 - 발신함 - 지출결재문서에 해당하는 문서 조회하기 (Ajax 처리)
	@ResponseBody
	@RequestMapping(value="/t1/spend_sendlist.tw", produces="text/plain;charset=UTF-8")
	public String spend_sendlist(HttpServletRequest request) {
		
		String astatus = request.getParameter("astatus");
		String fromDate = request.getParameter("fromDate");
		String toDate = request.getParameter("toDate");
		String scat = request.getParameter("scat");
		String sort = request.getParameter("sort");
		String searchWord = request.getParameter("searchWord");
		String currentShowPageNo = request.getParameter("currentShowPageNo");
				
		if(astatus == null || (!"0".equals(astatus) && !"1".equals(astatus) && !"2".equals(astatus) && !"3".equals(astatus))) {
			astatus = "";
		}
		if(fromDate == null || toDate == null) {
			fromDate = "";
			toDate = "";
		}
		if(sort == null || (!"atitle".equals(sort) && !"ano".equals(sort))) {
			sort = "";
		}
		if(searchWord == null || "".equals(searchWord) || searchWord.trim().isEmpty()) {
			searchWord = "";
		}
		
		String a = "";
		if(scat.length() == 0) {
			a = "";
		}
		else {
			String[] scatArr = scat.split(","); // [2,3]
			int cnt = 0;
			for(int i=0; i<scatArr.length; i++) {
				cnt++;
				
				if(cnt < scatArr.length) {
					a += "'"+scatArr[i]+"',";
				}
				else {
					a += "'"+scatArr[i]+"'";
				}
			}
		}
		
		if(currentShowPageNo == null || currentShowPageNo == "") {
			currentShowPageNo = "1";
		}
		
		try {
			Integer.parseInt(currentShowPageNo); 
		} catch (Exception e) {
			currentShowPageNo = "1";
		}
		
		int sizePerPage = 10;
		
		int startRno = ((Integer.parseInt(currentShowPageNo) - 1) * sizePerPage) + 1;
		int endRno = startRno + sizePerPage - 1;
		
		HttpSession session = request.getSession();
		MemberBwbVO loginuser = (MemberBwbVO) session.getAttribute("loginuser");		
		String userid = loginuser.getEmployeeid();
		
		Map<String, String> paraMap = new HashMap<>();		
		paraMap.put("astatus", astatus);
		paraMap.put("fromDate", fromDate);
		paraMap.put("toDate", toDate);
		paraMap.put("sort", sort);
		paraMap.put("searchWord", searchWord);
		paraMap.put("a", a);
		paraMap.put("startRno", String.valueOf(startRno));
		paraMap.put("endRno", String.valueOf(endRno));
		paraMap.put("userid", userid);
		
		List<ApprovalSiaVO> approvalvo = service.getSpend_sendlist(paraMap);
		
		JSONArray jsonArr = new JSONArray();
		
		
		if(approvalvo != null) {
			for(ApprovalSiaVO appvo : approvalvo) {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("atitle", appvo.getAtitle());
				jsonObj.put("ano", appvo.getAno());
				jsonObj.put("scatname", appvo.getScatname());
				jsonObj.put("astatus", appvo.getAstatus());
				jsonObj.put("asdate", appvo.getAsdate());
				jsonObj.put("rno", appvo.getRno());
				
				jsonArr.put(jsonObj);
			}
		}		
		
		return jsonArr.toString();
	}
	
	
	// 검색에 해당하는 발신함 - 지출결재 글의 총 페이지수를 알아오기
	@ResponseBody
	@RequestMapping(value="/t1/getSpendSendTotalPage.tw", method= {RequestMethod.GET})
	public String getSpendSendTotalPage(HttpServletRequest request) {
		
		String astatus = request.getParameter("astatus");
		String fromDate = request.getParameter("fromDate");
		String toDate = request.getParameter("toDate");
		String scat = request.getParameter("scat");
		String sort = request.getParameter("sort");
		String searchWord = request.getParameter("searchWord");
		String sizePerPage = request.getParameter("sizePerPage");
		
		if(astatus == null || (!"0".equals(astatus) && !"1".equals(astatus) && !"2".equals(astatus) && !"3".equals(astatus))) {
			astatus = "";
		}
		if(fromDate == null || toDate == null) {
			fromDate = "";
			toDate = "";
		}
		if(sort == null || (!"atitle".equals(sort) && !"ano".equals(sort))) {
			sort = "";
		}
		if(searchWord == null || "".equals(searchWord) || searchWord.trim().isEmpty()) {
			searchWord = "";
		}
		
		String a = "";
		if(scat.length() == 0) {
			a = "";
		}
		else {
			String[] scatArr = scat.split(","); // [2,3]
			int cnt = 0;
			for(int i=0; i<scatArr.length; i++) {
				cnt++;
				
				if(cnt < scatArr.length) {
					a += "'"+scatArr[i]+"',";
				}
				else {
					a += "'"+scatArr[i]+"'";
				}				
			}
		}
		
		Map<String, String> paraMap = new HashMap<>();		
		paraMap.put("astatus", astatus);
		paraMap.put("fromDate", fromDate);
		paraMap.put("toDate", toDate);
		paraMap.put("sort", sort);
		paraMap.put("searchWord", searchWord);
		paraMap.put("a", a);
		paraMap.put("sizePerPage", sizePerPage);		
		
		// 검색에 해당하는 발신함 - 지출결재 글의 총 페이지수를 알아오기
		int totalPage = service.getSpendSendTotalPage(paraMap);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("totalPage", totalPage); 
				
		return jsonObj.toString();		
	}
	
	
	// 내문서함 - 발신함 - 지출 결재 문서 한 개 상세보기
	@RequestMapping(value="/t1/myDocuSpend_send_detail.tw", produces="text/plain;charset=UTF-8")
	public ModelAndView myDocuSpend_send_detail(HttpServletRequest request, ModelAndView mav) {
		String ano = request.getParameter("ano");		
		String scatname = request.getParameter("scatname");
		
		mav.addObject("ano", request.getParameter("ano"));
		mav.addObject("scatname", request.getParameter("scatname"));		
		mav.addObject("astatus", request.getParameter("astatus"));
		mav.addObject("fromDate", request.getParameter("fromDate"));
		mav.addObject("toDate", request.getParameter("toDate"));
		mav.addObject("scat", request.getParameter("scat"));
		mav.addObject("sort", request.getParameter("sort"));		
		mav.addObject("searchWord", request.getParameter("searchWord"));
		mav.addObject("currentShowPageNo", request.getParameter("currentShowPageNo"));
		
		String employeeid = null;
		
		HttpSession session = request.getSession();
		MemberBwbVO loginuser = (MemberBwbVO)session.getAttribute("loginuser");
		
		if(loginuser != null) {
			employeeid = loginuser.getEmployeeid(); 
		}
		
		Map<String, String> paraMap = new HashMap<>();
		paraMap.put("ano", ano);
		paraMap.put("scatname", scatname);
		paraMap.put("employeeid", employeeid);
		
		try {
			ApprovalSiaVO avo = service.myDocuSpend_detail(paraMap);			
			mav.addObject("avo", avo);			
		} catch (NumberFormatException e) {
			
		}
		
		mav.setViewName("sia/myDocumentDetail/myDocuSpend_send_detail.gwTiles");
		
		return mav;
	}
	
	//////////////////////////////////////////////////////////////////////

	
	// 내문서함 - 발신함 - 근태결재문서에 해당하는 문서 조회하기
	@ResponseBody
	@RequestMapping(value="/t1/vacation_sendlist.tw", produces="text/plain;charset=UTF-8")
	public String vacation_sendlist(HttpServletRequest request) {
					
		String astatus = request.getParameter("astatus");
		String fromDate = request.getParameter("fromDate");
		String toDate = request.getParameter("toDate");
		String vno = request.getParameter("vno");
		String sort = request.getParameter("sort");
		String searchWord = request.getParameter("searchWord");
		String currentShowPageNo = request.getParameter("currentShowPageNo");		
		
		if(astatus == null || (!"0".equals(astatus) && !"1".equals(astatus) && !"2".equals(astatus) && !"3".equals(astatus))) {
			astatus = "";
		}
		if(fromDate == null || toDate == null) {
			fromDate = "";
			toDate = "";
		}
		if(sort == null || (!"atitle".equals(sort) && !"ano".equals(sort))) {
			sort = "";
		}
		if(searchWord == null || "".equals(searchWord) || searchWord.trim().isEmpty()) {
			searchWord = "";
		}
		
		String a = "";
		if(vno.length() == 0) {
			a = "";
		}
		else {
			String[] vnoArr = vno.split(","); // [2,3]
			int cnt = 0;
			for(int i=0; i<vnoArr.length; i++) {
				cnt++;
				
				if(cnt < vnoArr.length) {
					a += "'"+vnoArr[i]+"',";
				}
				else {
					a += "'"+vnoArr[i]+"'";
				}
			}
		}
		
		if(currentShowPageNo == null || currentShowPageNo == "") {
			currentShowPageNo = "1";
		}
		
		try {
			Integer.parseInt(currentShowPageNo); 
		} catch (Exception e) {
			currentShowPageNo = "1";
		}
		
		int sizePerPage = 10;
		
		int startRno = ((Integer.parseInt(currentShowPageNo) - 1) * sizePerPage) + 1;
		int endRno = startRno + sizePerPage - 1;
		
		HttpSession session = request.getSession();
		MemberBwbVO loginuser = (MemberBwbVO) session.getAttribute("loginuser");		
		String userid = loginuser.getEmployeeid();
		
		Map<String, String> paraMap = new HashMap<>();		 
		paraMap.put("astatus", astatus);
		paraMap.put("fromDate", fromDate);
		paraMap.put("toDate", toDate);
		paraMap.put("sort", sort);
		paraMap.put("searchWord", searchWord);
		paraMap.put("a", a);
		paraMap.put("startRno", String.valueOf(startRno));
		paraMap.put("endRno", String.valueOf(endRno));
		paraMap.put("userid", userid);
		
		List<ApprovalSiaVO> approvalvo = service.getVacation_sendlist(paraMap);		
		
		JSONArray jsonArr = new JSONArray();		
		
		if(approvalvo != null) {
			for(ApprovalSiaVO appvo : approvalvo) {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("atitle", appvo.getAtitle());
				jsonObj.put("ano", appvo.getAno());
				jsonObj.put("vcatname", appvo.getVcatname());
				jsonObj.put("astatus", appvo.getAstatus());
				jsonObj.put("asdate", appvo.getAsdate());
				jsonObj.put("rno", appvo.getRno());
				
				jsonArr.put(jsonObj);
			}
		}		
		
		return jsonArr.toString();
	}
	
	
	// 검색에 해당하는 발신함 - 근태결재 글의 총 페이지수를 알아오기
	@ResponseBody
	@RequestMapping(value="/t1/getVacationSendTotalPage.tw", method= {RequestMethod.GET})
	public String getVacationSendTotalPage(HttpServletRequest request) {
		
		String astatus = request.getParameter("astatus");
		String fromDate = request.getParameter("fromDate");
		String toDate = request.getParameter("toDate");
		String vno = request.getParameter("vno");
		String sort = request.getParameter("sort");
		String searchWord = request.getParameter("searchWord");
		String sizePerPage = request.getParameter("sizePerPage");
		
		if(astatus == null || (!"0".equals(astatus) && !"1".equals(astatus) && !"2".equals(astatus) && !"3".equals(astatus))) {
			astatus = "";
		}
		if(fromDate == null || toDate == null) {
			fromDate = "";
			toDate = "";
		}
		if(sort == null || (!"atitle".equals(sort) && !"ano".equals(sort))) {
			sort = "";
		}
		if(searchWord == null || "".equals(searchWord) || searchWord.trim().isEmpty()) {
			searchWord = "";
		}
		
		String a = "";
		if(vno.length() == 0) {
			a = "";
		}
		else {
			String[] vnoArr = vno.split(","); // [2,3]
			int cnt = 0;
			for(int i=0; i<vnoArr.length; i++) {
				cnt++;
				
				if(cnt < vnoArr.length) {
					a += "'"+vnoArr[i]+"',";
				}
				else {
					a += "'"+vnoArr[i]+"'";
				}				
			}
		}
		
		Map<String, String> paraMap = new HashMap<>();		
		paraMap.put("astatus", astatus);
		paraMap.put("fromDate", fromDate);
		paraMap.put("toDate", toDate);
		paraMap.put("sort", sort);
		paraMap.put("searchWord", searchWord);
		paraMap.put("a", a);
		paraMap.put("sizePerPage", sizePerPage);		
		
		// 검색에 해당하는 발신함 - 근태결재 글의 총 페이지수를 알아오기
		int totalPage = service.getVacationSendTotalPage(paraMap);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("totalPage", totalPage);		
		
		return jsonObj.toString();
	}
	
	
	// 내문서함 - 발신함 - 근태결재문서 한 개 상세보기
	@RequestMapping(value="/t1/myDocuVacation_send_detail.tw", produces="text/plain;charset=UTF-8")
	public ModelAndView myDocuVacation_send_detail(HttpServletRequest request, ModelAndView mav) {
		String ano = request.getParameter("ano");		
		String vcatname = request.getParameter("vcatname");
		
		mav.addObject("ano", request.getParameter("ano"));
		mav.addObject("vcatname", request.getParameter("vcatname"));		
		mav.addObject("astatus", request.getParameter("astatus"));
		mav.addObject("fromDate", request.getParameter("fromDate"));
		mav.addObject("toDate", request.getParameter("toDate"));
		mav.addObject("vno", request.getParameter("vno"));
		mav.addObject("sort", request.getParameter("sort"));		
		mav.addObject("searchWord", request.getParameter("searchWord"));
		mav.addObject("currentShowPageNo", request.getParameter("currentShowPageNo"));
		
		
		String employeeid = null;
		
		HttpSession session = request.getSession();
		MemberBwbVO loginuser = (MemberBwbVO)session.getAttribute("loginuser");
		
		if(loginuser != null) {
			employeeid = loginuser.getEmployeeid(); 
		}
		
		Map<String, String> paraMap = new HashMap<>();
		paraMap.put("ano", ano);
		paraMap.put("vcatname", vcatname);
		paraMap.put("employeeid", employeeid);
		
		try {
			// 근태결재문서 한 개 상세보기
			ApprovalSiaVO avo = service.myDocuVacation_send_detail(paraMap);						
			
			mav.addObject("avo", avo);		
			
		} catch (NumberFormatException e) {
			
		}
		
		
		mav.setViewName("sia/myDocumentDetail/myDocuVacation_detail.gwTiles");
		
		return mav;
	}
	
	//////////////////////////////////////////////////////////////////////
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//////////////////////////////////////////////////////////////////////
	// 내문서함 - 결재완료문서 - 근태결재문서에 해당하는 문서 조회하기
	@ResponseBody
	@RequestMapping(value="/t1/vacation_completelist.tw", produces="text/plain;charset=UTF-8")
	public String vacation_completelist(HttpServletRequest request) {
					
		String astatus = request.getParameter("astatus");
		String fromDate = request.getParameter("fromDate");
		String toDate = request.getParameter("toDate");
		String vno = request.getParameter("vno");
		String sort = request.getParameter("sort");
		String searchWord = request.getParameter("searchWord");
		String currentShowPageNo = request.getParameter("currentShowPageNo");		
		
		if(astatus == null || (!"0".equals(astatus) && !"1".equals(astatus) && !"2".equals(astatus) && !"3".equals(astatus))) {
			astatus = "";
		}
		if(fromDate == null || toDate == null) {
			fromDate = "";
			toDate = "";
		}
		if(sort == null || (!"atitle".equals(sort) && !"ano".equals(sort))) {
			sort = "";
		}
		if(searchWord == null || "".equals(searchWord) || searchWord.trim().isEmpty()) {
			searchWord = "";
		}
		
		String a = "";
		if(vno.length() == 0) {
			a = "";
		}
		else {
			String[] vnoArr = vno.split(","); // [2,3]
			int cnt = 0;
			for(int i=0; i<vnoArr.length; i++) {
				cnt++;
				
				if(cnt < vnoArr.length) {
					a += "'"+vnoArr[i]+"',";
				}
				else {
					a += "'"+vnoArr[i]+"'";
				}
			}
		}
		
		if(currentShowPageNo == null ||currentShowPageNo=="") {
			currentShowPageNo = "1";
		}
		
		int sizePerPage = 10;
		
		int startRno = ((Integer.parseInt(currentShowPageNo) - 1) * sizePerPage) + 1;
		int endRno = startRno + sizePerPage - 1;
		
		HttpSession session = request.getSession();
		MemberBwbVO loginuser = (MemberBwbVO) session.getAttribute("loginuser");		
		String userid = loginuser.getEmployeeid();
		
		Map<String, String> paraMap = new HashMap<>();		
		paraMap.put("astatus", astatus);
		paraMap.put("fromDate", fromDate);
		paraMap.put("toDate", toDate);
		paraMap.put("sort", sort);
		paraMap.put("searchWord", searchWord);
		paraMap.put("a", a);
		paraMap.put("startRno", String.valueOf(startRno));
		paraMap.put("endRno", String.valueOf(endRno));
		paraMap.put("userid", userid);
		
		List<ApprovalSiaVO> approvalvo = service.getVacation_completelist(paraMap);		
		
		JSONArray jsonArr = new JSONArray();		
		
		if(approvalvo != null) {
			for(ApprovalSiaVO appvo : approvalvo) {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("atitle", appvo.getAtitle());
				jsonObj.put("ano", appvo.getAno());
				jsonObj.put("vcatname", appvo.getVcatname());
				jsonObj.put("astatus", appvo.getAstatus());
				jsonObj.put("asdate", appvo.getAsdate());
				jsonObj.put("rno", appvo.getRno());
				
				jsonArr.put(jsonObj);
			}
		}		
		
		return jsonArr.toString();
	}
	
	
	// totalPage 알아오기 (Ajax 로 처리)
	@ResponseBody
	@RequestMapping(value="/t1/getVacationCompleteTotalPage.tw", method= {RequestMethod.GET})
	public String getVacationCompleteTotalPage(HttpServletRequest request) {
		
		String astatus = request.getParameter("astatus");
		String fromDate = request.getParameter("fromDate");
		String toDate = request.getParameter("toDate");
		String vno = request.getParameter("vno");
		String sort = request.getParameter("sort");
		String searchWord = request.getParameter("searchWord");
		String sizePerPage = request.getParameter("sizePerPage");
		
		if(astatus == null || (!"0".equals(astatus) && !"1".equals(astatus) && !"2".equals(astatus) && !"3".equals(astatus))) {
			astatus = "";
		}
		if(fromDate == null || toDate == null) {
			fromDate = "";
			toDate = "";
		}
		if(sort == null || (!"atitle".equals(sort) && !"ano".equals(sort))) {
			sort = "";
		}
		if(searchWord == null || "".equals(searchWord) || searchWord.trim().isEmpty()) {
			searchWord = "";
		}
		
		String a = "";
		if(vno.length() == 0) {
			a = "";
		}
		else {
			String[] vnoArr = vno.split(","); // [2,3]
			int cnt = 0;
			for(int i=0; i<vnoArr.length; i++) {
				cnt++;
				
				if(cnt < vnoArr.length) {
					a += "'"+vnoArr[i]+"',";
				}
				else {
					a += "'"+vnoArr[i]+"'";
				}				
			}
		}
		
		Map<String, String> paraMap = new HashMap<>();		
		paraMap.put("astatus", astatus);
		paraMap.put("fromDate", fromDate);
		paraMap.put("toDate", toDate);
		paraMap.put("sort", sort);
		paraMap.put("searchWord", searchWord);
		paraMap.put("a", a);
		paraMap.put("sizePerPage", sizePerPage);		
		
		// 검색에 해당하는 결재완료-근태결재 글의 총 페이지수를 알아오기
		int totalPage = service.getVacationCompleteTotalPage(paraMap);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("totalPage", totalPage);		
		
		return jsonObj.toString();
	}
	
/*	
	// 내문서함 - 결재완료 - 근태결재문서 한 개 상세보기
	@RequestMapping(value="/t1/myDocuVacationComplete_detail.tw", produces="text/plain;charset=UTF-8")
	public ModelAndView myDocuVacationComplete_detail(HttpServletRequest request, ModelAndView mav) {
		String ano = request.getParameter("ano");		
		String vcatname = request.getParameter("vcatname");
		
		mav.addObject("ano", request.getParameter("ano"));
		mav.addObject("vcatname", request.getParameter("vcatname"));		
		mav.addObject("astatus", request.getParameter("astatus"));
		mav.addObject("fromDate", request.getParameter("fromDate"));
		mav.addObject("toDate", request.getParameter("toDate"));
		mav.addObject("vno", request.getParameter("vno"));
		mav.addObject("sort", request.getParameter("sort"));		
		mav.addObject("searchWord", request.getParameter("searchWord"));
		mav.addObject("currentShowPageNo", request.getParameter("currentShowPageNo"));
		
		
		String employeeid = null;
		
		HttpSession session = request.getSession();
		MemberBwbVO loginuser = (MemberBwbVO)session.getAttribute("loginuser");
		
		if(loginuser != null) {
			employeeid = loginuser.getEmployeeid(); 
		}
		
		Map<String, String> paraMap = new HashMap<>();
		paraMap.put("ano", ano);
		paraMap.put("vcatname", vcatname);
		paraMap.put("employeeid", employeeid);
		
		try {
			// 근태결재문서 한 개 상세보기
			ApprovalSiaVO avo = service.myDocuVacation_detail(paraMap);						
			
			mav.addObject("avo", avo);		
			
		} catch (NumberFormatException e) {
			
		}
		
		
		mav.setViewName("sia/myDocumentDetail/myDocuVacation_complete_detail.gwTiles");
		
		return mav;
	}
	
	
	
	
	
	//////////////////////////////////////////////////////////////////////

	
	
*/
	
	

}
