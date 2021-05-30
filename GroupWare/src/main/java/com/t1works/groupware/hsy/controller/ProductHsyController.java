package com.t1works.groupware.hsy.controller;

import java.util.*;

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

import com.t1works.groupware.common.MyUtil;
import com.t1works.groupware.hsy.model.*;
import com.t1works.groupware.hsy.service.InterProductHsyService;

@Component
@Controller
public class ProductHsyController {
	
	@Autowired 
	private InterProductHsyService service;
	
	// 여행사 홈페이지 매핑 url
	@RequestMapping(value="/t1/travelAgency.tw") 
	public ModelAndView travelAgency(ModelAndView mav, HttpServletRequest request) {
		
		// 1) 여행사 홈페이지에서 보여줄 상품정보 가져오기 => 페이징바 처리
		
		String currentShowPageNo= request.getParameter("currentShowPageNo");
		if(currentShowPageNo == null) currentShowPageNo = "1";
		
		try {
			Integer.parseInt(currentShowPageNo);
		} catch(NumberFormatException e) {
			currentShowPageNo = "1"; 
		}
		
		String sizePerPage= "6"; // 6개씩 고정
		if(!"6".equals(sizePerPage)) sizePerPage = "6";
		
		Map<String,String> paraMap= new HashMap<>();
		paraMap.put("currentShowPageNo", currentShowPageNo);
		paraMap.put("sizePerPage", sizePerPage);
		
		// 상품목록 총 페이지 수
		int productTotalPage = service.selectProductTotalPage(paraMap);
		
		if( Integer.parseInt(currentShowPageNo) > productTotalPage || Integer.parseInt(currentShowPageNo) < 1) {
			currentShowPageNo = "1";
			paraMap.put("currentShowPageNo", currentShowPageNo);
		}
		
		List<ProductHsyVO> pvoList= service.selectProductInfoForHome(paraMap);
		mav.addObject("pvoList", pvoList);
		
		// 2) 페이징바 생성
		String pageBar= "";
		int blockSize= 3;
		int loop=1;
		int pageNo=0;
		
		pageNo= ((Integer.parseInt(currentShowPageNo)-1)/blockSize) * blockSize + 1 ;
		
		if(pageNo != 1) {
			pageBar += "&nbsp;<a href='travelAgency.tw?currentShowPageNo=1'>◀◀</a>&nbsp;"; 
			pageBar += "&nbsp;<a href='travelAgency.tw?currentShowPageNo="+(pageNo-1)+"'>◀</a>&nbsp;";
		}
		
		while(!(loop>blockSize || pageNo > productTotalPage) ) {
			if(pageNo == Integer.parseInt(currentShowPageNo)) {
				pageBar += "&nbsp;<span style='color:#fff; background-color: #003d66; font-weight:bold; padding:2px 4px;'>"+pageNo+"</span>&nbsp;";
			}
			else {
				pageBar += "&nbsp;<a href='travelAgency.tw?currentShowPageNo="+pageNo+"'>"+pageNo+"</a>&nbsp;";
			}
			loop++;
			pageNo++;
		}// end of while-----------------------------
		
		if(pageNo <= productTotalPage) {
			pageBar += "&nbsp;<a href='travelAgency.tw?currentShowPageNo="+pageNo+"'>▶</a>&nbsp;";
			pageBar += "&nbsp;<a href='travelAgency.tw?currentShowPageNo="+productTotalPage+"'>▶▶</a>&nbsp;";
		}
		
		mav.addObject("pageBar", pageBar);
		
		// 3) 상품상세페이지에서 사용할 gobackURL 작업
		String currentURL= MyUtil.getCurrentURL(request);
		currentURL= currentURL.replaceAll("&", " ");
		mav.addObject("goBackURL", currentURL);
		
		mav.setViewName("hsy/home/travelHome.mainTiles");
		return mav;
	}// end of public ModelAndView travelAgency(){--------------------
	
	
	// 여행상품 상세페이지 매핑 url
	@RequestMapping(value="/t1/travelDetail.tw") 
	public ModelAndView travelDetail(ModelAndView mav, HttpServletRequest request) {
		
		// 1) 여행상품 상세번호 알아오기
		String pNo= request.getParameter("pNo");
		
		// pNo 존재하는지 확인하기 (url 장난 방지) => 숫자형태가 아닌 문자입력 또는 존재하지 않는 pNo 입력한 경우
		int n= service.isExistPno(pNo);
		if(n==0) { // url 장난친 경우 제일 첫번째 상품을 보여준다
			pNo="1"; 
		}
		
		// 2) 상세페이지에 필요한 정보 알아오기
		ProductHsyVO pvo= service.selectOneProductInfo(pNo);
		mav.addObject("pvo", pvo);
	
		// 3) 목록으로 gobackURL 작업
		mav.addObject("goBackUrl", request.getParameter("goBackUrl"));
		mav.setViewName("hsy/product/travelDetail.mainTiles");
		
		return mav;
		
	} // end of public ModelAndView travelDetail(ModelAndView mav) {------
	
	
	//  예약이 가능한 인원수 인지 검사하고 예약불가능하면 예약가능한 인원수 가져오는 url 매핑주소 
	@ResponseBody
	@RequestMapping(value="/t1/checkClientAjax.tw", method= {RequestMethod.POST},produces="text/plain;charset=UTF-8")
	public String checkClientAjax(ClientHsyVO cvo) {
		
		// 1) 예약이 가능한 인원수 인지 검사
		int n= service.checkClientAjax(cvo);
		
		/*
		 	n==1  예약가능
		 	n==0 예약불가능
		*/
		
		JSONObject jsonObj= new JSONObject();
		jsonObj.put("n", n);
		
		if(n!=1) { // 예약이 불가능한 경우
			
			// 2) 특정제품에 현재 예약가능한 인원수
			int count= service.getAvailableCount(cvo.getFk_pNo());
			jsonObj.put("count", count);
		}
		
		return jsonObj.toString();
		
	} // end of public String checkClientAjax(ClientHsyVO cvo) {------
	 
	
	// 결제창 매핑 url
	@RequestMapping(value="/t1/orderPayment.tw")
	public ModelAndView checkClientAjax(ModelAndView mav, HttpServletRequest request) {
		
		mav.addObject("amount", Integer.parseInt(request.getParameter("amount")));
		mav.addObject("buyer_name", request.getParameter("buyer_name"));
		mav.addObject("buyer_tel", request.getParameter("buyer_tel"));
		mav.addObject("fk_pNo", request.getParameter("fk_pNo"));
		
		mav.setViewName("hsy/payment/paymentGateway");
		return mav;
		
	}// end of public ModelAndView checkClientAjax(ModelAndView mav) {-----
	
	
	// 고객테이블에 isnert하고 제품테이블에 update 하는 매핑 url
	@ResponseBody
	@RequestMapping(value="/t1/insertUpdateClientAjax.tw",method= {RequestMethod.POST},produces="text/plain;charset=UTF-8")
	public String insertUpdateClientAjax(ClientHsyVO cvo) {

		// 고객테이블에 isnert하고 제품테이블에 update (transaction처리)
		int result=0;
		
		try {
			result = service.insertUpdateClientAjax(cvo);
		} catch (Throwable e) {
			result=0;
		}
		
		/*
		 	result==1 예약성공 (트랜잭션 처리 성공)
		 	result==0 예약실패 (이미 같은 사람이름으로 해당 상품 예약한 값이 존재 또는 트랜잭션 처리 실패)
		*/
		
		JSONObject jsonObj= new JSONObject();
		jsonObj.put("result", result);
		
		return jsonObj.toString();
	}// end of public String checkClientAjax(ClientHsyVO cvo) {-----
	
	
	// 해당제품에 특정고객의 예약이 존재하는지 확인하는 매핑 url
	@ResponseBody
	@RequestMapping(value="/t1/isExistClientAjax.tw",method= {RequestMethod.POST},produces="text/plain;charset=UTF-8")
	public String isExistClientAjax(ClientHsyVO cvo) {

		int n=service.isExistClientAjax(cvo);
		/*
		 	n==1  해당제품에 특정고객의 예약이 존재하는 경우
		 	n==0 해당제품에 특정고객의 예약이 존재하지 않는 경우
		*/
		
		JSONObject jsonObj= new JSONObject();
		jsonObj.put("n",n);
		
		return jsonObj.toString();
		
	}// end of public String isExistClientAjax(ClientHsyVO cvo) {----
	
	
	// 고객의 나의예약현황 확인 페이지 매핑 url
	@RequestMapping(value="/t1/myreserve.tw")
	public ModelAndView myreserve(ModelAndView mav,HttpServletRequest request) {
		
		if("GET".equalsIgnoreCase(request.getMethod())){ // get방식인 경우
			mav.setViewName("hsy/reserve/reserveForm.mainTiles");
		}
		else { // post방식인 경우
			
			// 1) form에서 받은 정보를 이용하여 해당 고객명, 연락처에 일치하는 고객예약항목이 있는지 검사
			String clientname=request.getParameter("clientname");
			String clientmobile=request.getParameter("clientmobile");
			
			Map<String,String> paraMap= new HashMap<>();
			paraMap.put("clientmobile", clientmobile);
			paraMap.put("clientname", clientname);
			
			int n= service.checkClientMobile(paraMap);
			
			if(n>0) { // 2) 예약정보가 존재하는 경우 나의예약현황 리스트 페이지로 이동
				
				//2-1) 해당 연락처의 예약정보 총 개수 구하기
				int totalCount= service.getTotalReserveCount(clientmobile);
				
				mav.addObject("totalCount", totalCount);
				mav.addObject("clientmobile", clientmobile);
				mav.setViewName("hsy/reserve/myReserveList.mainTiles");
			}
			else { // 3) 예약정보가 존재하지 않는 경우
				
				// 나의예약현황 폼 주소로 돌아가기
				String message="해당 정보로 예약된 내역이 존재하지 않습니다.";
				String loc=request.getContextPath()+"/t1/myreserve.tw";
				
				request.setAttribute("message", message);
				request.setAttribute("loc", loc);
				mav.setViewName("hsy/msgHsy");
			}
		}
		return mav;
		
	} // end of public ModelAndView myreserve(ModelAndView mav,HttpServletRequest request) {---
	
	
	
	// 회사 위치 페이지 매핑 url
	@RequestMapping(value="/t1/travel/twLocation.tw")
	public ModelAndView twlocation(ModelAndView mav) {
		
		mav.setViewName("hsy/home/twLocation.mainTiles");
		return mav;
	
	} // end of public ModelAndView twLocation(ModelAndView mav) {---
	
	
	// 회사 위치 정보를 가져오는 ajax 매핑 url
	@ResponseBody
	@RequestMapping(value="/t1/travel/twLocationAjax.tw",produces="text/plain;charset=UTF-8")
	public String twLocationAjax() {

		// 회사위치테이블에 있는 정보 가져오기  
		List<TwLocationHsyVO> twLocationList = service.twLocationAjax();
		
		JSONArray jsonArr= new JSONArray();
		
		for(TwLocationHsyVO tlvo : twLocationList) {
            JSONObject jsonObj = new JSONObject();
            
            jsonObj.put("storeCode",tlvo.getStoreCode());
            jsonObj.put("storeName",tlvo.getStoreName());
            jsonObj.put("storeAddress",tlvo.getStoreAddress());
            jsonObj.put("storeImg",tlvo.getStoreImg());
            jsonObj.put("storePhone",tlvo.getStorePhone());
            
            double lat= Double.parseDouble(tlvo.getLat());
            double lng= Double.parseDouble(tlvo.getLng());
            int zindex= Integer.parseInt(tlvo.getZindex());
            
            jsonObj.put("lat",lat);
            jsonObj.put("lng",lng);
            jsonObj.put("zindex",zindex);
            
            jsonArr.put(jsonObj);
         }
		
		return jsonArr.toString();
		
	} // end of public String twLocationAjax() {-------
	
	
	// 특정고객의 예약정보를 가져오는 ajax 매핑 url
	@ResponseBody
	@RequestMapping(value="/t1/moreReserveListAjax.tw",method= {RequestMethod.POST}, produces="text/plain;charset=UTF-8")
	public String moreReserveListAjax(HttpServletRequest request) {
	
		String clientmobile= request.getParameter("clientmobile");
		String start= request.getParameter("start");
		String len= request.getParameter("len");
		String end= String.valueOf(Integer.parseInt(start)+Integer.parseInt(len)-1);
		
		Map<String,String> paraMap= new HashMap<>();
		paraMap.put("clientmobile", clientmobile);
		paraMap.put("start", start);
		paraMap.put("end", end);
		
		// 특정고객의 예약정보를 가져오기
		List<ClientHsyVO> cvoList =service.moreReserveListAjax(paraMap);
		
		JSONArray jsonArr= new JSONArray();
		
		if(cvoList.size()>0) { // 예약현황이 존재하는 경우 => 예약현황이 존재하지 않는 경우는 해당 url로 들어오지 못하도록 처리해둠
			for(ClientHsyVO cvo : cvoList) {
				JSONObject jsonObj= new JSONObject();
				
				jsonObj.put("pNo", cvo.getpNo());
				jsonObj.put("pName", cvo.getpName());
				jsonObj.put("startDate", cvo.getStartDate());
				jsonObj.put("endDate", cvo.getEndDate());
				jsonObj.put("period", cvo.getPeriod());
				jsonObj.put("price", cvo.getPrice());
				
				jsonObj.put("clientname", cvo.getClientname());
				jsonObj.put("clientmobile", cvo.getClientmobile());
				jsonObj.put("cnumber", cvo.getCnumber());
				
				// 여행확정 상품인지 표시해주기 위함
				Map<String,String> paraMap2= service.checkProductStatus(cvo.getpNo());
				if("0".equals(paraMap2.get("n"))) {
					jsonObj.put("status", "여행확정");
					jsonObj.put("statusColor", "status0");
				}
				else {
					jsonObj.put("status", "여행미확정");
					jsonObj.put("statusColor", "status1");
				}
				
				/*
			  	   n== "0" 여행확정상품 (취소불가)
			  	   n== "1" 여행미확정상품 (취소가능)
				*/
				
				jsonArr.put(jsonObj);
			}
		}
		
		return jsonArr.toString();
		
	} // end of public String moreReserveListAjax(HttpServletRequest request) {---------
	
	
	// 여행확정 상품인지 확인하는 url 매핑
	@ResponseBody
	@RequestMapping(value="/t1/checkProductStatus.tw",method= {RequestMethod.GET}, produces="text/plain;charset=UTF-8")
	public String checkProductStatus(HttpServletRequest request) {
	
		String pNo= request.getParameter("pNo");
		
		// 1) pNo 존재하는지 확인하기 (url 장난 방지) => 숫자형태가 아닌 문자입력 또는 존재하지 않는 pNo 입력한 경우
		int n= service.isExistPno(pNo);
		if(n==0) { // url 장난친 경우 제일 첫번째 상품을 보여준다
			pNo="1"; 
		}
		
		// 2) 여행확정 상품인지 확인하기
		Map<String,String> paraMap= service.checkProductStatus(pNo);
		
		JSONObject jsonObj= new JSONObject();
		jsonObj.put("n",paraMap.get("n"));
		jsonObj.put("pName", paraMap.get("pName"));
		
		/*
		  	n== "0" 여행확정상품 (취소불가)
		  	n== "1" 여행미확정상품 (취소가능)
		*/
		
		return jsonObj.toString();
		
	} // end of public String checkProductStatus(HttpServletRequest request) {----
	
	
	// 고객테이블에 delete하고 제품테이블에 update 하는 매핑 url
	@ResponseBody
	@RequestMapping(value="/t1/deleteUpdateClientAjax.tw",method= {RequestMethod.POST})
	public String deleteUpdateClientAjax(ClientHsyVO cvo) {
		
		// 1) 고객테이블에 delete하고 제품테이블에 update (transaction처리)
		int result=0;
		
		try {
			result = service.deleteUpdateClientAjax(cvo);
		} catch (Throwable e) {
			result=0;
		}
		
		/*
		 	result==1 예약성공 (트랜잭션 처리 성공)
		 	result==0 예약실패 (트랜잭션 처리 실패)
		*/
		
		JSONObject jsonObj= new JSONObject();
		jsonObj.put("result", result);
		
		return jsonObj.toString();
	}// end of public String deleteUpdateClientAjax(ClientHsyVO cvo) {------
	
	
	// 고객테이블에 update하고 제품테이블에 update 하는 매핑 url
	@ResponseBody
	@RequestMapping(value="/t1/changeCountAjax.tw",method= {RequestMethod.POST})
	public String changeCountAjax(ClientHsyVO cvo) {
		
		// 1) 고객테이블에 update하고 제품테이블에 update (transaction처리)
		int result=0;
		
		try {
			result = service.changeCountAjax(cvo);
		} catch (Throwable e) {
			result=0;
		}
		
		/*
		 	result==1 변경성공 (트랜잭션 처리 성공)
		 	result==0 변경실패 (트랜잭션 처리 실패)
		*/
		
		JSONObject jsonObj= new JSONObject();
		jsonObj.put("result", result);
		
		return jsonObj.toString();
	}// end of public String changeCountAjax(ClientHsyVO cvo) {-----------
		
	
}
