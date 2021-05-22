package com.t1works.groupware.hsy.controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.t1works.groupware.hsy.model.*;
import com.t1works.groupware.hsy.service.InterMemberHsyService;

@Component
@Controller
public class MemberHsyController {
	
	@Autowired 
	private InterMemberHsyService service;
	
	
	// 주소록(조직도) 매핑 주소
	@RequestMapping(value="/t1/employeeMap.tw")        // 로그인이 필요한 url
	public ModelAndView employeeMap(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {
		
		// 주소록표기
		// 1) 모든 부서에 대한 정보 가져오기
		List<DepartmentHsyVO> departmentList= service.selectAllDepartment();
		mav.addObject("departmentList", departmentList);
		
		
		// 2) 모든 직원 정보 가져오기
		List<MemberHsyVO> employeeList= service.selectAllMember();
		mav.addObject("employeeList",employeeList);
		
		
		// 3) 페이징바 처리 직원정보 가져오기
		String currentShowPageNo= request.getParameter("currentShowPageNo");
		if(currentShowPageNo == null) currentShowPageNo = "1";
		
		try {
			Integer.parseInt(currentShowPageNo);
		} catch(NumberFormatException e) {
			currentShowPageNo = "1"; 
		}
		
		String sizePerPage= "7"; // 5행씩 고정
		if(!"7".equals(sizePerPage)) sizePerPage = "7";
		
		String searchType= request.getParameter("searchType"); // name, dname, pname, email
		String searchWord= request.getParameter("searchWord");
		
		if(searchType!=null && !"".equals(searchType) && !("name".equalsIgnoreCase(searchType)||"dname".equalsIgnoreCase(searchType)||"pname".equalsIgnoreCase(searchType)||"email".equalsIgnoreCase(searchType))) {
			searchType="name";
		}
		
		if(searchType!=null && !"email".equalsIgnoreCase(searchType)) { // 대소문자 구분없이 검색가능하도록 설정
			searchWord=searchWord.toUpperCase();
		}
		else if("email".equalsIgnoreCase(searchType)) {
			searchWord=searchWord.toLowerCase();
		}
		
		Map<String,String> paraMap= new HashMap<>();
		paraMap.put("currentShowPageNo", currentShowPageNo);
		paraMap.put("sizePerPage", sizePerPage);
		paraMap.put("searchType", searchType);  // null인경우 존재
		paraMap.put("searchWord", searchWord);  // null인경우 존재 
		int totalPage = service.selectTotalPage(paraMap);
		
		if( Integer.parseInt(currentShowPageNo) > totalPage ) {
			currentShowPageNo = "1";
			paraMap.put("currentShowPageNo", currentShowPageNo);
		}
		
		List<MemberHsyVO> pagingEmployeeList= service.selectPagingMember(paraMap);
		mav.addObject("pagingEmployeeList", pagingEmployeeList);
		
		if(searchType!=null) searchType=searchType.toLowerCase(); // 대문자로 장난친 경우 소문자로 변경해서 넣어주기 위해 tolowerCase사용
		
		mav.addObject("searchType", searchType);  // view단에 값을 넣어주기 위한 용도 
		mav.addObject("searchWord", searchWord);  // view단에 값을 넣어주기 위한 용도
		
		// 4) 페이징바 생성
		String pageBar= "";
		int blockSize= 5;
		int loop=1;
		int pageNo=0;
		
		pageNo= ((Integer.parseInt(currentShowPageNo)-1)/blockSize) * blockSize + 1 ;
		if(searchType == null) searchType="";
		
		if(searchWord == null) searchWord="";
		
		if(pageNo != 1) {
			pageBar += "&nbsp;<a href='employeeMap.tw?currentShowPageNo=1&sizePerPage="+sizePerPage+"&searchType="+searchType+"&searchWord="+searchWord+"'>[맨처음]</a>&nbsp;"; 
			pageBar += "&nbsp;<a href='employeeMap.tw?currentShowPageNo="+(pageNo-1)+"&sizePerPage="+sizePerPage+"&searchType="+searchType+"&searchWord="+searchWord+"'>[이전]</a>&nbsp;";
		}
		
		while(!(loop>blockSize || pageNo > totalPage) ) {
			if(pageNo == Integer.parseInt(currentShowPageNo)) {
				pageBar += "&nbsp;<span style='color:#fff; background-color: #5e5e5e; font-weight:bold; padding:2px 4px;'>"+pageNo+"</span>&nbsp;";
			}
			else {
				pageBar += "&nbsp;<a href='employeeMap.tw?currentShowPageNo="+pageNo+"&sizePerPage="+sizePerPage+"&searchType="+searchType+"&searchWord="+searchWord+"'>"+pageNo+"</a>&nbsp;";
			}
			loop++;
			pageNo++;
		}// end of while-----------------------------
		
		if(pageNo <= totalPage) {
			pageBar += "&nbsp;<a href='employeeMap.tw?currentShowPageNo="+pageNo+"&sizePerPage="+sizePerPage+"&searchType="+searchType+"&searchWord="+searchWord+"'>[다음]</a>&nbsp";
			pageBar += "&nbsp;<a href='employeeMap.tw?currentShowPageNo="+totalPage+"&sizePerPage="+sizePerPage+"&searchType="+searchType+"&searchWord="+searchWord+"'>[마지막]</a>&nbsp";
		}
		
		mav.addObject("pageBar", pageBar);
		
		mav.setViewName("hsy/employee/employeeMap.gwTiles");
		return mav;
		
	} // end of public ModelAndView employeeMap(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {--------------------------------------------
	
	
	
	// post방식으로 한명의 직원정보 조회해오는 ajax 매핑주소
	@ResponseBody
	@RequestMapping(value="/t1/employeeInfoAjaxHsy.tw", produces="text/plain;charset=UTF-8", method= {RequestMethod.POST})
	public String employeeInfoAjaxHsy(HttpServletRequest request) {
		
		String employeeid= request.getParameter("employeeid");
		
		// 1) 사번에 해당하는 직원정보 가져오기
		MemberHsyVO mvo= service.employeeInfoAjaxHsy(employeeid);

		// 2) mvo의 정보를 json에 넣어주기
		JSONObject jsonObj= new JSONObject();  
		jsonObj.put("employeeid", mvo.getEmployeeid());
		jsonObj.put("email", mvo.getEmail());
		jsonObj.put("name", mvo.getName());
		jsonObj.put("cmobile", mvo.getCmobile());
		jsonObj.put("mobile", mvo.getMobile());
		jsonObj.put("dname", mvo.getDname());
		jsonObj.put("pname", mvo.getPname());
		jsonObj.put("duty", mvo.getDuty());
		
		return jsonObj.toString();
	
	} // end of public String employeeInfoAjaxHsy(HttpServletRequest request) {-----
	
	
	
	

	
}
