package com.t1works.groupware.hsy.controller;

import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.t1works.groupware.bwb.model.MemberBwbVO;
import com.t1works.groupware.bwb.service.InterHomepageBwbService;
import com.t1works.groupware.hsy.model.DepartmentHsyVO;
import com.t1works.groupware.hsy.model.MemberHsyVO;
import com.t1works.groupware.hsy.service.InterMemberHsyService;

@Component
@Controller
public class MemberHsyController {
	
	@Autowired 
	private InterMemberHsyService service;
	
	@Autowired 
	private InterHomepageBwbService service2;
	
	
	// 주소록(조직도) 매핑 주소
	@RequestMapping(value="/t1/employeeMap.tw")        // 로그인이 필요한 url
	public ModelAndView requiredLogin_employeeMap(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {
		
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
		
		String sizePerPage= "7"; // 7행씩 고정
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
		
		if( Integer.parseInt(currentShowPageNo) > totalPage || Integer.parseInt(currentShowPageNo) < 1) {
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
			pageBar += "&nbsp;<a href='employeeMap.tw?currentShowPageNo=1&searchType="+searchType+"&searchWord="+searchWord+"'>[맨처음]</a>&nbsp;"; 
			pageBar += "&nbsp;<a href='employeeMap.tw?currentShowPageNo="+(pageNo-1)+"&searchType="+searchType+"&searchWord="+searchWord+"'>[이전]</a>&nbsp;";
		}
		
		while(!(loop>blockSize || pageNo > totalPage) ) {
			if(pageNo == Integer.parseInt(currentShowPageNo)) {
				pageBar += "&nbsp;<span style='color:#fff; background-color: #003d66; font-weight:bold; padding:2px 4px;'>"+pageNo+"</span>&nbsp;";
			}
			else {
				pageBar += "&nbsp;<a href='employeeMap.tw?currentShowPageNo="+pageNo+"&searchType="+searchType+"&searchWord="+searchWord+"'>"+pageNo+"</a>&nbsp;";
			}
			loop++;
			pageNo++;
		}// end of while-----------------------------
		
		if(pageNo <= totalPage) {
			pageBar += "&nbsp;<a href='employeeMap.tw?currentShowPageNo="+pageNo+"&searchType="+searchType+"&searchWord="+searchWord+"'>[다음]</a>&nbsp";
			pageBar += "&nbsp;<a href='employeeMap.tw?currentShowPageNo="+totalPage+"&searchType="+searchType+"&searchWord="+searchWord+"'>[마지막]</a>&nbsp";
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
	
	
	
	// 월급관리 url 매핑
	@RequestMapping(value="/t1/salary.tw")        // 로그인이 필요한 url
	public ModelAndView requiredLogin_salary(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {
		
		HttpSession session = request.getSession();
		MemberBwbVO loginuser= (MemberBwbVO) session.getAttribute("loginuser");
		
		// 1) 특정 직원의 입사일 가져오기 => 입사일을 년도, 월, 일 단위로 나눠서 뷰단으로 넘기기
		String hiredate= loginuser.getHiredate();
		
		// System.out.println(loginuser.getHiredate());
		// 2010-07-10 00:00:00.0
		
		mav.addObject("hireYear", hiredate.substring(0,4));
		mav.addObject("hireMonth", hiredate.substring(5,7));
		mav.addObject("hireDay", hiredate.substring(8,10));
		
		// 2) 현재날짜 가져오기 => 년도, 월, 일 단위로 나눠서 뷰단으로 넘기기
		Calendar currentDate = Calendar.getInstance();
		int currentYear = currentDate.get(Calendar.YEAR);
		int currentMonth = currentDate.get(Calendar.MONTH)+1;
		
		mav.addObject("currentYear", currentYear);
		mav.addObject("currentMonth", currentMonth);
		
		mav.setViewName("hsy/employee/salary.gwTiles");
		return mav;
		
	} // end of public ModelAndView requiredLogin_salary(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) ----
	
	
	
	// 입력 비밀번호가 일치하는지 확인하는 매핑 url
	@ResponseBody
	@RequestMapping(value="/t1/passwdCheckForSalary.tw", method= {RequestMethod.POST}, produces="text/plain;charset=UTF-8")
	public String passwdCheckForSalary(HttpServletRequest request) {
		
		String employeeid=request.getParameter("employeeid");
		String passwd=request.getParameter("passwd");
		
		Map<String,String> paraMap = new HashMap<>();
        paraMap.put("employeeid", employeeid);
        paraMap.put("passwd", passwd);
        
        // 입력한 비밀번호가 일치하는지에 따라 json에 값 넣어주기
        MemberBwbVO mvo= service2.selectMember(paraMap);
        
        JSONObject jsonObj= new JSONObject();
		if(mvo==null) jsonObj.put("n", 0);   // 비밀번호가 일치하지 않는 경우
		else jsonObj.put("n", 1); // 비밀번호가 일치하는 경우
		
		
		return jsonObj.toString();
		
	} // end of public String passwdCheckForSalary(MemberHsyVO mvo) {----
	

	// 선택한 년도의 월급이 존재하는 월들의 정보 가져오는 매핑 url
	@ResponseBody
	@RequestMapping(value="/t1/salaryListInfoByYear.tw", method= {RequestMethod.POST}, produces="text/plain;charset=UTF-8")
	public String salaryListInfoByYear(HttpServletRequest request) {
		
		
		String employeeid= request.getParameter("employeeid");
	
		// 1) 특정직원의 소속, 직위, 기본급 가져오기
		MemberHsyVO mvo= service.employeeInfoAjaxHsy(employeeid);
		
		JsonObject jsonObj= new JsonObject();
		jsonObj.addProperty("dname", mvo.getDname());
		jsonObj.addProperty("pname", mvo.getPname());
		jsonObj.addProperty("salary", mvo.getSalary());
		
		return new Gson().toJson(jsonObj);
		
	} // end of public String salaryListInfoByYear(HttpServletRequest request)---
	
	
	// 월급명세서 상페 페이지 매핑 url
	@RequestMapping(value="/t1/salaryDetailForm.tw", method= {RequestMethod.POST})
	public ModelAndView salaryDetailForm(HttpServletRequest request, ModelAndView mav) {
		
		// 1) 월급명세서 출력하기를 선택한 년도와 월
		String employeeid= request.getParameter("employeeid");
		String year= request.getParameter("year");
		String month= request.getParameter("month");
		
		// 월이 한자리인 경우 앞에 0 붙여서 넘기기
		if(month.length()==1) month="0"+month;
		mav.addObject("employeeid", employeeid);
		mav.addObject("year", year);
		mav.addObject("month", month);
		
		// 2) 현재날짜 가져오기
		Calendar currentDate = Calendar.getInstance();
		int currentYear = currentDate.get(Calendar.YEAR);
		String currentMonth = String.valueOf(currentDate.get(Calendar.MONTH)+1);
		String currentDay= String.valueOf(currentDate.get(Calendar.DATE));
		
		// 월이 한자리인 경우 앞에 0 붙여서 넘기기
		if(currentMonth.length()==1) currentMonth="0"+currentMonth;

		// 일이 한자리인 경우 앞에 0 붙여서 넘기기
		if(currentDay.length()==1) currentDay="0"+currentDay;
		
		mav.addObject("currentYear", currentYear);
		mav.addObject("currentMonth", currentMonth);
		mav.addObject("currentDay",currentDay);
		
		// 3-1) 월급명세서에 필요한 정보 가져오기 => 인적사항 (사번/성명/소속/직급)
		MemberHsyVO mvo= service.employeeInfoAjaxHsy(employeeid);
		mav.addObject("mvo", mvo);
		
		// 3-2) 월급명세서에 필요한 정보 가져오기 => 근태내역 (연차/병가/지각/반차/경조휴가 사용 일 수)
		
		// 해당 직원의 근태결재 승인처리 완료된 문서번호 가져오기
		List<String> anoList= service.getAttendanceAno(employeeid);
		
		// 근태결재 승인처리 완료된 문서번호 가공하기 => in절에 사용될 수 있도록 데이터 가공
		String anoForIn="";
		for(int i=0; i<anoList.size();i++) {   

			String str= (i < anoList.size()-1) ? "," : "";
			anoForIn+= anoList.get(i)+str;
		} // end of for------------------------
		
		
		Map<String,String> paraMap= new HashMap<>();
		paraMap.put("employeeid", employeeid);
		paraMap.put("date",year+"-"+month);
		paraMap.put("anoForIn", anoForIn);
		
		// 근태내역 (연차/병가/지각/반차/경조휴가 사용 일 수) 가져오기
		Map<String,Integer> attendanceMap= service.getAttendanceForSalary(paraMap);
		
		
		// 정상출근 일 수 => 해당 년도, 월의 평일 수에서 휴가사용일 제외 (해당 년도 월의 평일수를 구해서 view단으로 넘기기 => 자바스크립트에서 계산)
		// ========== 특정 년도, 월의 평일 수 구하기 사전작업
		HttpSession session = request.getSession();
		MemberBwbVO loginuser= (MemberBwbVO) session.getAttribute("loginuser");
		
		String hiredate= loginuser.getHiredate();
		
		String hireYear= hiredate.substring(0,4);
		String hireMonth= hiredate.substring(5,7);
		String hireDay= hiredate.substring(8,10);
		
		int nYear= Integer.parseInt(year);
		
		String startDate = year+month+"01"; // 입사년도, 월과 다른 경우 시작일은 01일 부터 카운트
		String endDate = "";
		 
		if(year.equals(hireYear) && month.equals(hireMonth)) { // 입사년도, 월과 같은 경우 시작일은 입사일부터 카운트
			startDate=year+month+hireDay; // 입사년도, 월과 같은 경우 시작일은 입사일부터 카운트
		}
		
		if("02".equals(month)) {  // 월급명세서 출력 선택날짜가 2월인 경우 윤달 고려
			if(nYear%4==0) endDate=year+month+29; // 윤달인 경우
			else endDate=year+month+28; // 윤달이 아닌 경우
		}
		else if("04".equals(month)||"06".equals(month)||"09".equals(month)||"11".equals(month)) { // 마지막날이 30일인 경우
			endDate=year+month+30;
		}
		else { // 마지막날이 31일인 경우
			endDate=year+month+31;
		}
		
		// System.out.println(startDate);
		// System.out.println(endDate);
		
		// ========== 특정 년도, 월의 평일 수 구하기
		
	    // 공휴일 설정 (신정, 삼일절, 어린이날, 성탄절 설정)
	    List<Map<String,String>> holidayList = new ArrayList<>();
	    Map<String, String> holidayMap = new HashMap<>();

	    holidayMap.put("holidayDt", year+"0101");	//신정
	    holidayList.add(holidayMap);

	    holidayMap = new HashMap<>();
	    holidayMap.put("holidayDt", year+"0301");	//삼일절
	    holidayList.add(holidayMap);

	    holidayMap = new HashMap<>();
	    holidayMap.put("holidayDt", year+"0505");	//어린이날
	    holidayList.add(holidayMap);

	    holidayMap = new HashMap<>();
	    holidayMap.put("holidayDt", year+"1225");	//성탄절
	    holidayList.add(holidayMap);


	    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	    int workDays=0;
	    
	    try{
			Calendar start = Calendar.getInstance();
	        start.setTime(sdf.parse(startDate)); //시작일 날짜 설정

	      	Calendar end = Calendar.getInstance();
	      	end.setTime(sdf.parse(endDate)); //종료일 날짜 설정

	      	Calendar hol = Calendar.getInstance();

			int workingDays = 0;
	    	int holDays = 0;
	    	
	      	while (!start.after(end)) {
	      		int day = start.get(Calendar.DAY_OF_WEEK);	 //주말인지 확인하기
	      		int holday = 0; 

	      		if ((day != Calendar.SATURDAY) && (day != Calendar.SUNDAY)){
		      		workingDays++;	//평일 수 증가
		      	}
	      		
		      	//시작일과 공휴일이 같을때 공휴일이 주말인지 체크 => 주말이 아니면 holDays +1 증가
		      	if(!holidayList.isEmpty()){
		      		for(int i=0;i<holidayList.size();i++){
			      		hol.setTime(sdf.parse((String)holidayList.get(i).get("holidayDt").toString()));	//실제 공휴일 날짜 설정
			      		holday = hol.get(Calendar.DAY_OF_WEEK); //주말인지 확인하기
			      		if (start.equals(hol) &&(holday != Calendar.SATURDAY) && (holday != Calendar.SUNDAY)){	//공휴일수: 공휴일이 평일인경우 +1
				      		holDays++;
			      		}	  
		      		} // end of for-----------------
		      	}
		      	start.add(Calendar.DATE, 1);
	      	} // end of while-----------------------
	      	
	    // System.out.printf("최종일수 %d", workingDays-holDays); //평일 수 - 평일인 공휴일수

	    workDays = workingDays-holDays;  // 해당 월의 총 근무수 (연차를 사용하지 않은 경우)
				
		}catch(Exception e){
			e.printStackTrace();
		}
		
	    
	    // 3-3) 월급명세서에 필요한 정보 가져오기 => 근속수당 계산을 위해 근속년도 view단으로 넘기기
	    int continueYear= nYear-Integer.parseInt(hireYear);
	    
	    if(continueYear%5==0  && hireMonth.equals(month)) { // 근속수당 100만원 지급 (5년마다)
	    	mav.addObject("continueYear", continueYear);
	    }
	    else { // 근속수당 해당 없는 경우
	    	mav.addObject("continueYear", 0);
	    }
	    
		mav.addObject("workDays", workDays);
		mav.addObject("attendanceMap", attendanceMap);
		
		
		// 3-4) 월급명세서에 필요한 정보 가져오기 => 해당 년도, 월의  총 야근 시간 가져오기
		int totalLateWorkTime= service.getTotalLateWorkTime(paraMap);
		mav.addObject("totalLateWorkTime", totalLateWorkTime); // 야근시간이 없는 경우 0
		
		
		mav.setViewName("hsy/employee/salaryDetail.gwTiles");
		return mav;
		
	} // end of public ModelAndView salaryDetailForm(HttpServletRequest request, ModelAndView mav) {------
}
