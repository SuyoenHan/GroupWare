package com.t1works.groupware.hsy.controller;

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
import com.t1works.groupware.hsy.model.ClientHsyVO;
import com.t1works.groupware.hsy.model.ProductHsyVO;
import com.t1works.groupware.hsy.model.TodoHsyVO;
import com.t1works.groupware.hsy.service.InterProductHsyService;
import com.t1works.groupware.hsy.service.InterTodoHsyService;

@Component
@Controller
public class TodoHsyController {
	
	@Autowired 
	private InterTodoHsyService service;
	
	@Autowired 
	private InterProductHsyService service2;
	
	// 사원 업무관리 페이지 매핑 url
	@RequestMapping(value="/t1/employeeTodo.tw")    // 로그인이 필요한 url
	public ModelAndView requiredLogin_employeeTodo(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {
		
		
		String requiredState=request.getParameter("requiredState");
		// 1) 해당 페이지가 로드되면 처음에는 신규등록업무를 보여주기 위함
		if(requiredState==null) requiredState="0"; 		
		
		// "0":신규등록업무, "1":진행중업무, "2":진행완료업무 에 해당하지 않는 값이 url에 입력된 경우 신규등록업무로 보여준다
		if(!("0".equals(requiredState) || "1".equals(requiredState) || "2".equals(requiredState))) {
			requiredState="0";
		}
		
		
		String periodOption= request.getParameter("periodOption"); 
		// 2) 업무완료 페이지가 로드되면 처음에는 1주일 기간을 보여주기 위함
		if(periodOption==null) periodOption="week"; 
		
		if(!("week".equals(periodOption) || "month".equals(periodOption) || "3months".equals(periodOption))) {
			periodOption="week";
		}
			
		
		// 3) 업무관리페이지에서 보여줄 정보 페이징바 처리해서 정보 가져오기
		String currentShowPageNo= request.getParameter("currentShowPageNo");
		if(currentShowPageNo == null) currentShowPageNo = "1";
		
		try {
			Integer.parseInt(currentShowPageNo);
		} catch(NumberFormatException e) {
			currentShowPageNo = "1"; 
		}
		
		String sizePerPage= "4"; // 4행씩 고정
		if(!"4".equals(sizePerPage)) sizePerPage = "4";
		
		Map<String,String> paraMap= new HashMap<>();
		paraMap.put("currentShowPageNo", currentShowPageNo);
		paraMap.put("sizePerPage", sizePerPage);
		
		// 로그인유저의 사원번호와 requiredState, periodOption를 paraMap에 추가하기

		HttpSession session= request.getSession();
		MemberBwbVO loginuser= (MemberBwbVO) session.getAttribute("loginuser");
		paraMap.put("employeeid", loginuser.getEmployeeid());
		paraMap.put("requiredState", requiredState);
		paraMap.put("periodOption", periodOption);
		
		int totalPage = service.selectTotalPage(paraMap);
		
		if( Integer.parseInt(currentShowPageNo) > totalPage || Integer.parseInt(currentShowPageNo) < 1) {
			currentShowPageNo = "1";
			paraMap.put("currentShowPageNo", currentShowPageNo);
		}
		
		// 페이징바 처리한 업무관리 목록가져오기(트랜잭션 처리=> 지연된업무 update처리 동시진행)
		List<TodoHsyVO> tvoList= null;
		
		try {
			tvoList = service.employeeTodoList(paraMap); 
			// 해당항목이 없는경우 tvoList.size() == 0
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		
		mav.addObject("requiredState", requiredState);
		mav.addObject("tvoList", tvoList);
		mav.addObject("periodOption", periodOption);
		
		// 4) 페이징바 생성
		String pageBar= "";
		int blockSize= 5;
		int loop=1;
		int pageNo=0;
		
		pageNo= ((Integer.parseInt(currentShowPageNo)-1)/blockSize) * blockSize + 1 ;

		if(pageNo != 1) {
			pageBar += "&nbsp;<a href='employeeTodo.tw?currentShowPageNo=1&requiredState="+requiredState+"&periodOption="+periodOption+"'>[맨처음]</a>&nbsp;"; 
			pageBar += "&nbsp;<a href='employeeTodo.tw?currentShowPageNo="+(pageNo-1)+"&requiredState="+requiredState+"&periodOption="+periodOption+"'>[이전]</a>&nbsp;";
		}
		
		while(!(loop>blockSize || pageNo > totalPage) ) { // totalPage가 0인경우 페이지바는 생성되지 않는다
			if(pageNo == Integer.parseInt(currentShowPageNo)) {
				pageBar += "&nbsp;<span style='color:#fff; background-color: #003d66; font-weight:bold; padding:2px 4px;'>"+pageNo+"</span>&nbsp;";
			}
			else {
				pageBar += "&nbsp;<a href='employeeTodo.tw?currentShowPageNo="+pageNo+"&requiredState="+requiredState+"&periodOption="+periodOption+"'>"+pageNo+"</a>&nbsp;";
			}
			loop++;
			pageNo++;
		}// end of while-----------------------------
		
		if(pageNo <= totalPage) {
			pageBar += "&nbsp;<a href='employeeTodo.tw?currentShowPageNo="+pageNo+"&requiredState="+requiredState+"&periodOption="+periodOption+"'>[다음]</a>&nbsp";
			pageBar += "&nbsp;<a href='employeeTodo.tw?currentShowPageNo="+totalPage+"&requiredState="+requiredState+"&periodOption="+periodOption+"'>[마지막]</a>&nbsp";
		}
		
		mav.addObject("pageBar", pageBar);
		
		
		// 5) 신규등록업무, 진행중업무, 진행완료업무 총 건수 표시
		Map<String,String> cntMap= service.selectCntTodoByrequiredState(loginuser.getEmployeeid());
		
		mav.addObject("newTodoCnt", cntMap.get("newTodoCnt"));
		mav.addObject("ingTodoCnt", cntMap.get("ingTodoCnt"));
		mav.addObject("doneTodoCnt", cntMap.get("doneTodoCnt"));

		
		// 6) 기간별로 완료한 업무 총건수 나눠서 표시
		Map<String,String> donePeriodMap= service.selectCntTodoByPeriod(loginuser.getEmployeeid());
		mav.addObject("weekCnt", donePeriodMap.get("weekCnt"));
		mav.addObject("monthCnt", donePeriodMap.get("monthCnt"));
		mav.addObject("threeMonthsCnt", donePeriodMap.get("threeMonthsCnt"));
		
		
		mav.setViewName("hsy/toDo/employeeTodo.gwTiles");
		return mav;
		
	}// end of public ModelAndView requiredLogin_employeeTodo(----
	
	
	// 신규등록업무에서 진행중업무로 업데이트하는 ajax 매핑 url
	@ResponseBody
	@RequestMapping(value="/t1/goWorkStart.tw", method= {RequestMethod.POST})
	public String goWorkStart(HttpServletRequest request) {
		
		String fk_pNo= request.getParameter("fk_pNo");
		int n= service.goWorkStart(fk_pNo);
		// 업데이트 성공 시 n==1
		
		JSONObject jsonObj= new JSONObject();
		jsonObj.put("n", n);
		
		return jsonObj.toString();
		
	} // end of public String goWorkStart(HttpServletRequest request) {------
	
	
	// 진행중인 업무 상태변경(진행중 또는 보류)에 따라 update하기
	@ResponseBody
	@RequestMapping(value="/t1/updateIngDetail.tw", method= {RequestMethod.POST})
	public String updateIngDetail(TodoHsyVO tvo) {
		
		int n=service.updateIngDetail(tvo);
		
		JSONObject jsonObj= new JSONObject();
		jsonObj.put("n", n);  // update성공 시 n==1
		
		return jsonObj.toString();
	}// end of public String goWorkStart(HttpServletRequest request) {--------
	
	
	// 진행중 업무에서 진행완료 업무로 업데이트하는 ajax 매핑 url
	@ResponseBody
	@RequestMapping(value="/t1/goWorkDone.tw", method= {RequestMethod.POST})
	public String goWorkDone(HttpServletRequest request) {
			
		String fk_pNo= request.getParameter("fk_pNo");
		int n= service.goWorkDone(fk_pNo);
		// 업데이트 성공 시 n==1
		
		JSONObject jsonObj= new JSONObject();
		jsonObj.put("n", n);
		
		return jsonObj.toString();
		
	} // end of public String goWorkDone(HttpServletRequest request) {------
	
	
	// 특정업무 상세 정보 조회1 (상품에 대한 정보)
	@ResponseBody
	@RequestMapping(value="/t1/selectProductInfoForModal.tw",produces="text/plain;charset=UTF-8")
	public String selectProductInfoForModal(HttpServletRequest request) {
			
		// 1) 여행상품 상세번호 알아오기
		String pNo= request.getParameter("fk_pNo");
				
		// pNo 존재하는지 확인하기 (url 장난 방지) => 숫자형태가 아닌 문자입력 또는 존재하지 않는 pNo 입력한 경우
		int n= service2.isExistPno(pNo);
		if(n==0) { // url 장난친 경우 제일 첫번째 상품을 보여준다
			pNo="1"; 
		}
		
		// 2) pNo를 이용하여 필요한 상품정보 가져오기
		ProductHsyVO pvo= service2.selectOneProductInfo(pNo);
		
		JSONObject jsonObj= new JSONObject();
		jsonObj.put("pName", pvo.getpName());
		jsonObj.put("miniNo", pvo.getMiniNo());
		jsonObj.put("maxNo", pvo.getMaxNo());
		jsonObj.put("nowNo", pvo.getNowNo());
		jsonObj.put("startDate", pvo.getStartDate());
		jsonObj.put("endDate", pvo.getEndDate());
		jsonObj.put("period", pvo.getPeriod());
		
		return jsonObj.toString();
		
	} // end of public String selectProductInfoForModal(HttpServletRequest request)---
	
	
	// 특정업무 상세 정보 조회2 (고객에 대한 정보)
	@ResponseBody
	@RequestMapping(value="/t1/selectClientInfoForModal.tw",method= {RequestMethod.POST},produces="text/plain;charset=UTF-8")
	public String selectClientInfoForModal(HttpServletRequest request) {

		// 1) 여행상품 상세번호 알아오기
		String pNo= request.getParameter("fk_pNo");
				
		// pNo 존재하는지 확인하기 (url 장난 방지) => 숫자형태가 아닌 문자입력 또는 존재하지 않는 pNo 입력한 경우
		int n= service2.isExistPno(pNo);
		if(n==0) { // url 장난친 경우 제일 첫번째 상품을 보여준다
			pNo="1"; 
		}
		
		// 2) pNo를 이용하여 필요한 고객정보 가져오기
		List<ClientHsyVO> cvoList= service.selectClientInfoByPno(pNo);
		
		JSONArray jsonArr= new JSONArray();
		for(ClientHsyVO cvo: cvoList) {
			
			JSONObject jsonObj= new JSONObject();
			jsonObj.put("clientname",cvo.getClientname());
			jsonObj.put("clientmobile",cvo.getClientmobile());
			jsonObj.put("cnumber",cvo.getCnumber());
			jsonObj.put("fk_pNo",cvo.getFk_pNo());
			
			jsonArr.put(jsonObj);
		}// end of for--------------------
		
		return jsonArr.toString();
		
	} // end of public String selectProductInfoForModal(HttpServletRequest request) {-----
	
}
