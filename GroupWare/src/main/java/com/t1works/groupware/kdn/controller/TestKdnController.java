package com.t1works.groupware.kdn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestKdnController {

	@RequestMapping(value="/t1/sidenavbar_iframe.tw")
	public ModelAndView show_sideNavBar(ModelAndView mav) {
		
		mav.setViewName("kdn/sidenavbar_iframe");
		
		return mav;
		
	}
	@RequestMapping(value="/t1/content_iframe.tw")
	public ModelAndView show_content(ModelAndView mav) {
		
		mav.setViewName("kdn/content_iframe");
		
		return mav;
		
	}
	
	@RequestMapping(value="/t1/sidenavbar_test.tw")
	public ModelAndView test_sidenavbar(ModelAndView mav) {
		
		mav.setViewName("kdn/sidenavbar_animation");
		
		return mav;
		
	}
	
}
