package com.t1works.groupware.sia.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.t1works.groupware.sia.model.ApprovalSiaVO;
import com.t1works.groupware.sia.service.InterMyDocumentSiaService;

@Component
@Controller
public class MyDocumentSiaController {
	
	@Autowired // Type에 따라 알아서 Bean 을 주입해준다.
	private InterMyDocumentSiaService service;
	
	// 내문서함 클릭 시 수신함 일반결재문서로 이동!
	@RequestMapping(value="/t1/myDocument.tw")
	public String myDocument() {		
		return "sia/myDocument/myDocuNorm_rec.gwTiles";
	}
	
	// 내문서함 - 수신함 - 일반결재문서 (기본으로 일반결재문서가 보여짐!)
	@RequestMapping(value="/t1/myDocuNorm_rec.tw")
	public String myDocuNorm_rec() {
		return "sia/myDocument/myDocuNorm_rec.gwTiles";		
	}
	
	// 내문서함 - 수신함 - 지출결재문서
	@RequestMapping(value="/t1/myDocuSpend_rec.tw")
	public String myDocuSpend_rec() {
		return "sia/myDocument/myDocuSpend_rec.gwTiles";		
	}
	
	// 내문서함 - 수신함 - 근태휴가결재문서
	@RequestMapping(value="/t1/myDocuVacation_rec.tw")
	public String myDocuVacation_rec(HttpServletRequest request) {
		
		request.setAttribute("ano", request.getParameter("ano"));
		request.setAttribute("anocode", request.getParameter("anocode"));
		request.setAttribute("astatus", request.getParameter("astatus"));
		request.setAttribute("fromDate", request.getParameter("fromDate"));
		request.setAttribute("toDate", request.getParameter("toDate"));
		request.setAttribute("vno", request.getParameter("vno"));
		request.setAttribute("sort", request.getParameter("sort"));		
		request.setAttribute("searchWord", request.getParameter("searchWord"));
		
		return "sia/myDocument/myDocuVacation_rec.gwTiles";		
	}	
	
	
	// 내문서함 - 발신함 - 일반결재문서 (기본으로 일반결재문서가 보여짐!)
	@RequestMapping(value="/t1/myDocuNorm_send.tw")
	public String myDocuNorm_send() {
		return "sia/myDocument/myDocuNorm_send.gwTiles";		
	}
	
	// 내문서함 - 발신함 - 지출결재문서
	@RequestMapping(value="/t1/myDocuSpend_send.tw")
	public String myDocuSpend_send() {
		return "sia/myDocument/myDocuSpend_send.gwTiles";		
	}
	
	// 내문서함 - 발신함 - 근태휴가결재문서
	@RequestMapping(value="/t1/myDocuVacation_send.tw")
	public String myDocuVacation_send() {
		return "sia/myDocument/myDocuVacation_send.gwTiles";		
	}
	
	
	// 내문서함 - 임시저장함 - 일반결재문서 (기본으로 일반결재문서가 보여짐!)
	@RequestMapping(value="/t1/myDocuNorm_temp.tw")
	public String myDocuNorm_temp() {
		return "sia/myDocument/myDocuNorm_temp.gwTiles";		
	}
	
	// 내문서함 - 임시저장함 - 지출결재문서
	@RequestMapping(value="/t1/myDocuSpend_temp.tw")
	public String myDocuSpend_temp() {
		return "sia/myDocument/myDocuSpend_temp.gwTiles";		
	}
	
	// 내문서함 - 임시저장함 - 근태휴가결재문서
	@RequestMapping(value="/t1/myDocuVacation_temp.tw")
	public String myDocuVacation_temp() {
		return "sia/myDocument/myDocuVacation_temp.gwTiles";		
	}
	
	//////////////////////////////////////////////////////////////////////
	
	// 내문서함 - 수신함 - 일반결재문서에 해당하는 문서 조회하기
	@ResponseBody
	@RequestMapping(value="/t1/norm_reclist.tw", produces="text/plain;charset=UTF-8")
	public String norm_reclist(HttpServletRequest request) {
		
		String anocode = request.getParameter("anocode");
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
		
		if(currentShowPageNo == null) {
			currentShowPageNo = "1";
		}
		
		int sizePerPage = 10;
		
		int startRno = ((Integer.parseInt(currentShowPageNo) - 1) * sizePerPage) + 1;
		int endRno = startRno + sizePerPage - 1;
		
		
		Map<String, String> paraMap = new HashMap<>();
		paraMap.put("anocode", anocode);  
		paraMap.put("astatus", astatus);
		paraMap.put("fromDate", fromDate);
		paraMap.put("toDate", toDate);
		paraMap.put("sort", sort);
		paraMap.put("searchWord", searchWord);
		paraMap.put("a", a);
		paraMap.put("startRno", String.valueOf(startRno));
		paraMap.put("endRno", String.valueOf(endRno));
		
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
				
				jsonArr.put(jsonObj);
			}
		}
		return jsonArr.toString();
	}
	
	
	// totalPage 알아오기 (Ajax 로 처리)
	@ResponseBody
	@RequestMapping(value="/t1/getTotalPage.tw", method= {RequestMethod.GET})
	public String getTotalPage(HttpServletRequest request) {
		
		String anocode = request.getParameter("anocode");
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
		paraMap.put("anocode", anocode);  
		paraMap.put("astatus", astatus);
		paraMap.put("fromDate", fromDate);
		paraMap.put("toDate", toDate);
		paraMap.put("sort", sort);
		paraMap.put("searchWord", searchWord);
		paraMap.put("a", a);
		paraMap.put("sizePerPage", sizePerPage);		
		
		// 검색에 해당하는 수신함-일반결재 글의 총 페이지수를 알아오기
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
		
		Map<String, String> paraMap = new HashMap<>();
		paraMap.put("ano", ano);
		paraMap.put("ncat", ncatname);
		
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
		
		String anocode = request.getParameter("anocode");
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
		
		if(currentShowPageNo == null) {
			currentShowPageNo = "1";
		}
		
		int sizePerPage = 10;
		
		int startRno = ((Integer.parseInt(currentShowPageNo) - 1) * sizePerPage) + 1;
		int endRno = startRno + sizePerPage - 1;
		
		
		Map<String, String> paraMap = new HashMap<>();
		paraMap.put("anocode", anocode);  
		paraMap.put("astatus", astatus);
		paraMap.put("fromDate", fromDate);
		paraMap.put("toDate", toDate);
		paraMap.put("sort", sort);
		paraMap.put("searchWord", searchWord);
		paraMap.put("a", a);
		paraMap.put("startRno", String.valueOf(startRno));
		paraMap.put("endRno", String.valueOf(endRno));
		
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
				
				jsonArr.put(jsonObj);
			}
		}		
		
		return jsonArr.toString();
	}
	
	
	// totalPage 알아오기 (Ajax 로 처리)
	@ResponseBody
	@RequestMapping(value="/t1/getSpendTotalPage.tw", method= {RequestMethod.GET})
	public String getSpendTotalPage(HttpServletRequest request) {
		
		String anocode = request.getParameter("anocode");
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
		paraMap.put("anocode", anocode);  
		paraMap.put("astatus", astatus);
		paraMap.put("fromDate", fromDate);
		paraMap.put("toDate", toDate);
		paraMap.put("sort", sort);
		paraMap.put("searchWord", searchWord);
		paraMap.put("a", a);
		paraMap.put("sizePerPage", sizePerPage);		
		
		// 검색에 해당하는 수신함-지출결재 글의 총 페이지수를 알아오기
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
		
		Map<String, String> paraMap = new HashMap<>();
		paraMap.put("ano", ano);
		paraMap.put("scatname", scatname);
		
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
		
		String anocode = request.getParameter("anocode");
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
		
		Map<String, String> paraMap = new HashMap<>();
		paraMap.put("anocode", anocode);  
		paraMap.put("astatus", astatus);
		paraMap.put("fromDate", fromDate);
		paraMap.put("toDate", toDate);
		paraMap.put("sort", sort);
		paraMap.put("searchWord", searchWord);
		paraMap.put("a", a);
		paraMap.put("startRno", String.valueOf(startRno));
		paraMap.put("endRno", String.valueOf(endRno));
		
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
				
				jsonArr.put(jsonObj);
			}
		}		
		
		return jsonArr.toString();
	}
	
	
	// totalPage 알아오기 (Ajax 로 처리)
	@ResponseBody
	@RequestMapping(value="/t1/getVacationTotalPage.tw", method= {RequestMethod.GET})
	public String getVacationTotalPage(HttpServletRequest request) {
		
		String anocode = request.getParameter("anocode");
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
		paraMap.put("anocode", anocode);  
		paraMap.put("astatus", astatus);
		paraMap.put("fromDate", fromDate);
		paraMap.put("toDate", toDate);
		paraMap.put("sort", sort);
		paraMap.put("searchWord", searchWord);
		paraMap.put("a", a);
		paraMap.put("sizePerPage", sizePerPage);		
		
		// 검색에 해당하는 수신함-근태결재 글의 총 페이지수를 알아오기
		int totalPage = service.getVacationTotalPage(paraMap);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("totalPage", totalPage);		
		
		return jsonObj.toString();
	}
	
	
	// 내문서함 - 수신함 - 근태결재문서 한 개 상세보기
	@RequestMapping(value="/t1/myDocuVaction_detail.tw", produces="text/plain;charset=UTF-8")
	public ModelAndView myDocuVaction_detail(HttpServletRequest request, ModelAndView mav) {
		String ano = request.getParameter("ano");		
		String vcatname = request.getParameter("vcatname");
		
		mav.addObject("ano", request.getParameter("ano"));
		mav.addObject("vcatname", request.getParameter("vcatname"));
		mav.addObject("anocode", request.getParameter("anocode"));
		mav.addObject("astatus", request.getParameter("astatus"));
		mav.addObject("fromDate", request.getParameter("fromDate"));
		mav.addObject("toDate", request.getParameter("toDate"));
		mav.addObject("vno", request.getParameter("vno"));
		mav.addObject("sort", request.getParameter("sort"));		
		mav.addObject("searchWord", request.getParameter("searchWord"));
		
		Map<String, String> paraMap = new HashMap<>();
		paraMap.put("ano", ano);
		paraMap.put("vcatname", vcatname);
		
		try {
			ApprovalSiaVO avo = service.myDocuVaction_detail(paraMap);
			
			mav.addObject("avo", avo);
			
		} catch (NumberFormatException e) {
			
		}
		
		mav.setViewName("sia/myDocumentDetail/myDocuVacation_detail.gwTiles");
		
		return mav;
	}
	
	
	
/*	
	// 내문서함 - 발신함 - 일반결재문서에 해당하는 문서 조회하기
	@ResponseBody
	@RequestMapping(value="/t1/norm_sendlist.tw", produces="text/plain;charset=UTF-8")
	public String norm_sendlist(HttpServletRequest request) {
		
		String anocode = request.getParameter("anocode");
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
		
		if(currentShowPageNo == null) {
			currentShowPageNo = "1";
		}
		
		int sizePerPage = 10;
		
		int startRno = ((Integer.parseInt(currentShowPageNo) - 1) * sizePerPage) + 1;
		int endRno = startRno + sizePerPage - 1;
		
				
		Map<String, String> paraMap = new HashMap<>();
		paraMap.put("anocode", anocode);  
		paraMap.put("astatus", astatus);
		paraMap.put("fromDate", fromDate);
		paraMap.put("toDate", toDate);
		paraMap.put("sort", sort);
		paraMap.put("searchWord", searchWord);
		paraMap.put("a", a);
		paraMap.put("startRno", String.valueOf(startRno));
		paraMap.put("endRno", String.valueOf(endRno));
		
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
				
				jsonArr.put(jsonObj);
			}
		}
		return jsonArr.toString();
	}
	
	
	// totalPage 알아오기 (Ajax 로 처리)
	@ResponseBody
	@RequestMapping(value="/t1/getSendTotalPage.tw", method= {RequestMethod.GET})
	public String getSendTotalPage(HttpServletRequest request) {
		
		String anocode = request.getParameter("anocode");
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
		paraMap.put("anocode", anocode);  
		paraMap.put("astatus", astatus);
		paraMap.put("fromDate", fromDate);
		paraMap.put("toDate", toDate);
		paraMap.put("sort", sort);
		paraMap.put("searchWord", searchWord);
		paraMap.put("a", a);
		paraMap.put("sizePerPage", sizePerPage);
		
		// 검색에 해당하는 글의 총 페이지수를 알아오기
		int totalPage = service.getSendTotalPage(paraMap);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("totalPage", totalPage);
		
		return jsonObj.toString();
	}
	*/
}
