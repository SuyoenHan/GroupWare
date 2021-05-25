package com.t1works.groupware.jsh.controller;



import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.t1works.groupware.jsh.model.ElectronPayJshVO;
import com.t1works.groupware.jsh.service.InterPaymentJshService;

@Controller
public class PaymentJshController {
	
	@Autowired // Type에 따라 알아서 Bean 을 주입해준다.
	   private InterPaymentJshService service; 
	
	// 일반결재내역 목록 보여주기
	 @RequestMapping(value="/t1/electronPay.tw")
	   public ModelAndView generalPayment_List(ModelAndView mav, HttpServletRequest request) {  
		 List<ElectronPayJshVO> electronList = null;
		 
		 electronList =  service.generalPayment_List();
		
		 mav.addObject("electronList",electronList );
		 mav.setViewName("jsh/generalPayment_List.gwTiles");
	     return mav;
	     
	   }
	
		/*
		 * @RequestMapping(value="/t1/generalPayment_List.tw") public String
		 * generalPayment_List() {
		 * 
		 * return "jsh/generalPayment_List.gwTiles"; // /WEB-INF/views/sample/test1.jsp
		 * 페이지를 만들어야 한다. }
		 */
	
}
