package com.t1works.groupware.hsy.controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sun.xml.internal.ws.api.message.Message;
import com.t1works.groupware.common.MyUtil;
import com.t1works.groupware.hsy.model.ClientHsyVO;
import com.t1works.groupware.hsy.model.ProductHsyVO;
import com.t1works.groupware.hsy.service.InterProductHsyService;

@Component
@Controller
public class ProductHsyController {
	
	@Autowired 
	private InterProductHsyService service;
	
	// 여행사 홈페이지 매핑 url
	@RequestMapping(value="/t1/travelAgency.tw") 
	public ModelAndView travelAgency(ModelAndView mav, HttpServletRequest request) {
		
		// 1) 여행사 홈페이지에서 보여줄 상품정보 가져오기
		List<ProductHsyVO> pvoList= service.selectProductInfoForHome();
		mav.addObject("pvoList", pvoList);
		
		// 2) 상품상세페이지에서 사용할 gobackURL 작업
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
		
		// pNo 존재하는지 확인하기 (url 장난 방지)
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
			mav.setViewName("hsy/reserve/myReserveList.mainTiles");
		}
		
		return mav;
		
	} // end of public ModelAndView myreserve(ModelAndView mav,HttpServletRequest request) {---
	
	
}
