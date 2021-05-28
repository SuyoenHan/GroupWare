package com.t1works.groupware.bwb.controller;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.t1works.groupware.common.AES256;
import com.t1works.groupware.common.Sha256;
import com.t1works.groupware.hsy.model.DepartmentHsyVO;
import com.t1works.groupware.hsy.model.MemberHsyVO;
import com.t1works.groupware.hsy.service.InterMemberHsyService;

@Controller
public class MemberBwbController {
	
	@Autowired // Type에 따라 알아서 Bean 을 주입해준다.
	private InterMemberHsyService service;
	
	@Autowired // Type에 따라 알아서 Bean 을 주입해준다.
	private InterMemberBwbService service2;
	
	@Autowired // Type에 따라 알아서 Bean 을 주입해준다.
    private InterHomepageBwbService service3;
	
	@Autowired // Type에 따라 알아서 Bean 을 주입해준다.
	private AES256 aes;
	 
	
	// 인사부장- 업무관리(인사관리) 매핑 주소
	@RequestMapping(value="/t1/personnelManage.tw")        // 로그인이 필요한 url
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
		
		// 4) 모든 직위 가져오기
		List<MemberBwbVO> positionList = service2.selectPositionList();
		mav.addObject("positionList", positionList);
		
		
		
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
			pageBar += "&nbsp;<a href='personnelManage.tw?currentShowPageNo=1&sizePerPage="+sizePerPage+"&searchType="+searchType+"&searchWord="+searchWord+"'>[맨처음]</a>&nbsp;"; 
			pageBar += "&nbsp;<a href='personnelManage.tw?currentShowPageNo="+(pageNo-1)+"&sizePerPage="+sizePerPage+"&searchType="+searchType+"&searchWord="+searchWord+"'>[이전]</a>&nbsp;";
		}
		
		while(!(loop>blockSize || pageNo > totalPage) ) {
			if(pageNo == Integer.parseInt(currentShowPageNo)) {
				pageBar += "&nbsp;<span style='color:#fff; background-color: #5e5e5e; font-weight:bold; padding:2px 4px;'>"+pageNo+"</span>&nbsp;";
			}
			else {
				pageBar += "&nbsp;<a href='personnelManage.tw?currentShowPageNo="+pageNo+"&sizePerPage="+sizePerPage+"&searchType="+searchType+"&searchWord="+searchWord+"'>"+pageNo+"</a>&nbsp;";
			}
			loop++;
			pageNo++;
		}// end of while-----------------------------
		
		if(pageNo <= totalPage) {
			pageBar += "&nbsp;<a href='personnelManage.tw?currentShowPageNo="+pageNo+"&sizePerPage="+sizePerPage+"&searchType="+searchType+"&searchWord="+searchWord+"'>[다음]</a>&nbsp";
			pageBar += "&nbsp;<a href='personnelManage.tw?currentShowPageNo="+totalPage+"&sizePerPage="+sizePerPage+"&searchType="+searchType+"&searchWord="+searchWord+"'>[마지막]</a>&nbsp";
		}
		
		mav.addObject("pageBar", pageBar);
		
		mav.setViewName("bwb/todo/personnelManage.gwTiles");
		return mav;
		
	} // end of public ModelAndView employeeMap(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {--------------------------------------------
	
	
	// 부서를 통해 직무내용 뽑아오기
	@ResponseBody
	@RequestMapping(value="/t1/selectDuty.tw",produces="text/plain;charset=UTF-8")
	public String requiredLogin_selectDuty(HttpServletRequest request,HttpServletResponse response) {
		
		String dname = request.getParameter("dname");
		
		String duty = service2.selectDuty(dname);
		
		JSONObject jsonObj = new JSONObject();
		
		jsonObj.put("duty", duty);
			
		return jsonObj.toString();
	}// end of public String requiredLogin_selectDuty(HttpServletRequest request,HttpServletResponse response) {
	
	// 인사관리를 통해 조직원 정보 업데이트하기
	@ResponseBody
	@RequestMapping(value="/t1/updateOneInfo.tw")
	public String updateOneInfo(MemberBwbVO mvo) {
		
		// serialize()한 데이터값 뽑아오기
		String pname = mvo.getPname();
		String dname = mvo.getDname();
		String cmobile = mvo.getCmobile();
		String mobile = mvo.getMobile();
		
		Map<String,String> paraMap = new HashMap<>();
		
		paraMap.put("pname", pname);
		paraMap.put("dname", dname);
		
		// pname과 dname을 통해 pcode,dcode 가져오기.
		Map<String,String> PDMap  = service2.selectPDcode(paraMap);
		mvo.setFk_pcode(PDMap.get("fk_pcode")); 
		mvo.setFk_dcode(PDMap.get("fk_dcode"));
		
		String[] cmobileArr = cmobile.split("-");
		String[] mobileArr = mobile.split("-");
		
		cmobile = "";
		mobile = "";
		
		for(String scmobile:cmobileArr){
			cmobile += scmobile;
		}
		mvo.setCmobile(cmobile);
		
		for(String smobile:mobileArr){
			mobile += smobile;
		}

		mvo.setMobile(mobile);
		
		// 회원정보 업데이트하기
		int n = service2.updateOneInfo(mvo);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("n", n);
		
		return jsonObj.toString();
	}
	
	
	// 인사부장- 업무관리(신입사원 등록) 매핑 주소
	@RequestMapping(value="/t1/registerNewEmployee.tw")
	public ModelAndView requiredLogin_registerNewEmployee(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) { // 로그인이 필요한 url AOP사용
		
		// 1) 모든 부서명 가저오기
		List<DepartmentHsyVO> departmentList= service.selectAllDepartment();
		mav.addObject("departmentList", departmentList);
		
		// 2) 모든 직위 가져오기
		List<MemberBwbVO> positionList = service2.selectPositionList();
		mav.addObject("positionList", positionList);
		
		
		mav.setViewName("bwb/todo/registerNewEmployee.gwTiles");
		
		return mav;

	}
	
	// pcode에 따른 연차수 가져오기
	@ResponseBody
	@RequestMapping(value="/t1/selectOffCnt.tw")
	public String requiredLogin_selectOffCnt(HttpServletRequest request,HttpServletResponse response) {
		
		String pcode = request.getParameter("fk_pcode");
		
		String offcnt = service2.selectOffCnt(pcode);
		
		JSONObject jsonObj = new JSONObject();
		
		jsonObj.put("offcnt", offcnt);
			
		return jsonObj.toString();
		
	}// end of public String requiredLogin_selectDuty(HttpServletRequest request,HttpServletResponse response) {
	
	// 직원정보등록하기 ==> 추후 ajax이용할때, 코드 변경예정...
	@ResponseBody
	@RequestMapping(value="/t1/registerOne.tw")
	public String registerOne(HttpServletRequest request, MemberBwbVO mvo) {
		
		
		String jubun1 = request.getParameter("jubun1");
		String jubun2 = request.getParameter("jubun2");
		
		String jubun = jubun1+jubun2;
		
		mvo.setJubun(jubun);
		mvo.setPasswd(jubun1);
		
		String mobile1 = request.getParameter("mobile1");
		String mobile2 = request.getParameter("mobile2");
		String mobile3 = request.getParameter("mobile3");
		
		String mobile = mobile1+mobile2+mobile3;
		mvo.setMobile(mobile);
		
		String cmobile1 = request.getParameter("cmobile1");
		String cmobile2 = request.getParameter("cmobile2");
		String cmobile3 = request.getParameter("cmobile3");
		
		String cmobile = cmobile1+cmobile2+cmobile3;
		mvo.setCmobile(cmobile);
		
		String dcode = request.getParameter("fk_dcode");
		// 등록한 직원의 fk_dcode를 통해 managerid 알아오기
		String managerid = service2.selectMangerid(dcode);
		mvo.setManagerid(managerid);
		

		
		int n = service2.registerOne(mvo);
		
		
		JSONObject jsonObj = new JSONObject();
		
		jsonObj.put("n", n);
			
		return jsonObj.toString();
		
	}
	
	// 마이페이지 맵핑
	@RequestMapping(value="/t1/mypage.tw")
	public ModelAndView requiredLogin_mypage(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {
		
		
		HttpSession session = request.getSession();
		MemberBwbVO loginuser = (MemberBwbVO)session.getAttribute("loginuser");
		
		String dcode = loginuser.getFk_dcode();
		String pcode = loginuser.getFk_pcode();
		
		// 부서명 가져오기
		String dname = service2.selectdname(dcode);
		
		// 직무 가져오기
		String duty = service2.selectDuty(dname);
		
		// 직위 가져오기
		String pname = service2.selectpname(pcode);
		
		String jubun="";
		
		try {
			
			if(loginuser.getJubun().length()>13) {
				jubun = aes.decrypt(loginuser.getJubun());
			}
			else {
				jubun = loginuser.getJubun();
			}
		} catch (UnsupportedEncodingException | GeneralSecurityException e) {
			e.printStackTrace();
		}
		
		String fJubun = jubun.substring(0,6);
		
		String passwd = loginuser.getPasswd();
		
		Map<String,String> paraMap = new HashMap<>();
		paraMap.put("dname", dname);
		paraMap.put("duty", duty);
		paraMap.put("pname", pname);
		paraMap.put("fJubun", fJubun);
		paraMap.put("passwd", passwd);
		
		mav.addObject("paraMap", paraMap);
		
		mav.setViewName("bwb/mypage/mypage.gwTiles");
		
		return mav;
	}

	// 비밀번호 변경하기
	@ResponseBody
	@RequestMapping(value="/t1/changePwd.tw", method= {RequestMethod.POST})
	public String changePwd(HttpServletRequest request) {
		
		
		String spasswd = request.getParameter("passwd");
		String employeeid = request.getParameter("employeeid");
				
		String passwd = Sha256.encrypt(spasswd);
		
		Map<String,String> paraMap = new HashMap<>();
		paraMap.put("employeeid", employeeid);
		paraMap.put("passwd", passwd);
		
		
		int n = service2.updatePasswd(paraMap);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("n", n);
		
		return jsonObj.toString();
	}
	
	
	// 비밀번호 검사하기
	@ResponseBody
	@RequestMapping(value="/t1/checkpasswd.tw", method= {RequestMethod.POST})
	public String checkpasswd(HttpServletRequest request) {
		
		
		String employeeid = request.getParameter("employeeid");
        String passwd = request.getParameter("lastpasswd");
				
        Map<String,String> paraMap = new HashMap<>();
        paraMap.put("employeeid", employeeid);
        paraMap.put("passwd", passwd);
		
     // 직원테이블에서 select해오기
        MemberBwbVO mvo = service3.selectMember(paraMap);
		
        JSONObject jsonObj = new JSONObject();
        
        int n =0;
        
		if(mvo==null) {
			n=1;
		}
		
		jsonObj.put("n", n);
		
		return jsonObj.toString();
	}
	
}
