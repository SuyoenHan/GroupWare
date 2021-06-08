package com.t1works.groupware.bwb.controller;

import java.util.*;

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
import com.t1works.groupware.bwb.service.InterHomepageBwbService;
import com.t1works.groupware.bwb.service.InterMemberBwbService;

@Controller
public class HomepageBwbController {
	
	 @Autowired // Type에 따라 알아서 Bean 을 주입해준다.
	 private InterHomepageBwbService service;
	 
	 @Autowired // Type에 따라 알아서 Bean 을 주입해준다.
	 private InterMemberBwbService service2;
	 
	 
	 // 출퇴근기록 테이블에 insert 및 select작업(출근시간)
	 @ResponseBody
	 @RequestMapping(value="/t1/insertSelectIntime.tw", method= {RequestMethod.POST})
	 public String requiredLogin_insertSelectIntime(HttpServletRequest request, HttpServletResponse response) {
		 
		 String fk_employeeid = request.getParameter("fk_employeeid");
		 String gooutdate = request.getParameter("gooutdate"); 
		
		 
		 Map<String,String> paraMap = new HashMap<>();
		 paraMap.put("fk_employeeid", fk_employeeid);
		 paraMap.put("gooutdate", gooutdate);
		 
		 
		 
		 service.insertIntime(paraMap);
		 
		 String intime = service.selectIntime(paraMap);

		 JSONObject jsonobj = new JSONObject();
		 jsonobj.put("intime", intime);
		 
		 // 지각여부 시간 판단하기(select)
		 String latenoTime = service.selectlateno(intime);
		 jsonobj.put("latenoTime", latenoTime); // 1일경우 지각, 0,-1일 경우 정상출근
		 
		 // 지각여부 판단하기(update)
		 if("1".equalsIgnoreCase(latenoTime)) {
			 service.updatelateno(paraMap);
		 }
	
		 
		 return jsonobj.toString();
	 }
	 
	 
	// 출퇴근기록 테이블에서 select작업
	 @ResponseBody
	 @RequestMapping(value="/t1/selectIntime.tw")
	 public String requiredLogin_selectIntime(HttpServletRequest request, HttpServletResponse response) {
 
		 String fk_employeeid = request.getParameter("fk_employeeid");
		 String gooutdate = request.getParameter("gooutdate"); 
		 
		 Map<String,String> paraMap = new HashMap<>();
		 paraMap.put("fk_employeeid", fk_employeeid);
		 paraMap.put("gooutdate", gooutdate);

		 String intime = service.selectIntime(paraMap);
		 if(intime==null) {
			 intime="";
		 }
		 
		 JSONObject jsonobj = new JSONObject();
		 jsonobj.put("intime", intime);
		 
		 String latenoTime = service.selectlateno(intime);
		 jsonobj.put("latenoTime", latenoTime); // 1일경우 지각, 0,-1일 경우 정상출근

		 return jsonobj.toString();
	 }
	 
	 
	 // 퇴근버튼 클릭시 출퇴근 테이블에 insert 및 select작업 및 야근테이블에 insert작업 (트랜젝션 처리)
	 @ResponseBody
	 @RequestMapping(value="/t1/insertSelectOuttime.tw", method= {RequestMethod.POST})
	 public String requiredLogin_insertSelectOuttime(HttpServletRequest request, HttpServletResponse response) {
		 
		 String fk_employeeid = request.getParameter("fk_employeeid");
		 String gooutdate = request.getParameter("gooutdate"); 
		 
		 
		 Map<String,String> paraMap = new HashMap<>();
		 paraMap.put("fk_employeeid", fk_employeeid);
		 paraMap.put("gooutdate", gooutdate);
		 
		 
		 String outtime = "";
		 
		 // 출퇴근테이블에 insert하기(퇴근시간)
		 try {
			outtime = service.updateOuttime(paraMap);
		 } catch (Throwable e) {
			e.printStackTrace();
		 }

		 JSONObject jsonobj = new JSONObject();
		 jsonobj.put("outtime", outtime);
		 
		 return jsonobj.toString();
		 
	 }// end of public String requiredLogin_insertSelectOuttime
	
	 
	// 출퇴근기록 테이블에서 select작업(퇴근시간)
	 @ResponseBody
	 @RequestMapping(value="/t1/selectOuttime.tw")
	 public String requiredLogin_selectOuttime(HttpServletRequest request, HttpServletResponse response) {
 
		 String fk_employeeid = request.getParameter("fk_employeeid");
		 String gooutdate = request.getParameter("gooutdate"); 
		 
		 Map<String,String> paraMap = new HashMap<>();
		 paraMap.put("fk_employeeid", fk_employeeid);
		 paraMap.put("gooutdate", gooutdate);

		 String outtime = service.selectOuttime(paraMap);
		 
		 if(outtime==null) {
			 outtime="";
		 }
		 
		 JSONObject jsonobj = new JSONObject();
		 jsonobj.put("outtime", outtime);
		 
		 return jsonobj.toString();
	 }// end of @RequestMapping(value="/t1/selectOuttime.tw")
	 
	 @RequestMapping(value="/t1/myMonthIndolence.tw")
	 public ModelAndView requiredLogin_myMonthIndolence(HttpServletRequest request, HttpServletResponse response,ModelAndView mav) {
		 
		 HttpSession session = request.getSession();
		 MemberBwbVO loginuser = (MemberBwbVO)session.getAttribute("loginuser");
		 
		 mav.addObject("loginuser", loginuser);
		 mav.setViewName("bwb/myMonthIndolence.gwTiles");
		 return mav;
	 }// end of public ModelAndView requiredLogin_myMonthIndolence
	 
	 // 나의 월별 출퇴근기록 가지고오기(ajax처리)
	 @ResponseBody
	 @RequestMapping(value="/t1/selectmyMonthIndolence.tw",produces="text/plain;charset=UTF-8")
	 public String selectmyMonthIndolence(HttpServletRequest request) {
		 
		 String fk_employeeid = request.getParameter("fk_employeeid");
		 
		 // 나의 월별 출퇴근기록 가지고오기
		 List<Map<String,String>> myIndolenceList = service.selectmyMonthIndolence(fk_employeeid);
		 
		 JSONArray jsonArr = new JSONArray();
		 for(Map<String,String> resultMap :myIndolenceList) {
			 
			 JSONObject jsonObj = new JSONObject();
			 jsonObj.put("name", resultMap.get("name"));
			 jsonObj.put("intime", resultMap.get("intime"));
			 jsonObj.put("outtime", resultMap.get("outtime"));
			 
			 jsonArr.put(jsonObj);
		 }

		 return jsonArr.toString();
		 
	 }// end of public String selectmyMonthIndolence
	 
	 // 부서근태관리 클릭시 맵핑주소
	 @RequestMapping(value="/t1/depMonthIndolence.tw")
	 public ModelAndView requiredLogin_depMonthIndolence(HttpServletRequest request, HttpServletResponse response,ModelAndView mav) {
		 
		 HttpSession session = request.getSession();
		 MemberBwbVO loginuser = (MemberBwbVO)session.getAttribute("loginuser");
		 
		 String dcode = loginuser.getFk_dcode();
		 String dname = service2.selectdname(dcode);
		 
		 mav.addObject("dname", dname);
		 mav.addObject("loginuser", loginuser);
		 mav.setViewName("bwb/depMonthIndolence.gwTiles");
		 return mav;
	 }// end of public ModelAndView requiredLogin_myMonthIndolence
	 
	 
	// 부서별 출퇴근기록 가지고오기(ajax처리)
	 @ResponseBody
	 @RequestMapping(value="/t1/selectDepMonthIndolence.tw",produces="text/plain;charset=UTF-8")
	 public String selectDepMonthIndolence(HttpServletRequest request) {
		 
		 String fk_dcode = request.getParameter("fk_dcode");
		 
		 // 부서 월별 출퇴근기록 가지고오기
		 List<Map<String,String>> depIndolenceList = service.selectDepMonthIndolence(fk_dcode);
		 
		 JSONArray jsonArr = new JSONArray();
		 for(Map<String,String> resultMap :depIndolenceList) {
			 
			 JSONObject jsonObj = new JSONObject();
			 jsonObj.put("name", resultMap.get("name"));
			 jsonObj.put("intime", resultMap.get("intime"));
			 jsonObj.put("outtime", resultMap.get("outtime"));
			 
			 jsonArr.put(jsonObj);
		 }
		 System.out.println(jsonArr);
		 return jsonArr.toString();
		 
	 }// end of public String selectmyMonthIndolence
	 
}

