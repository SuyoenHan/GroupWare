package com.t1works.groupware.bwb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomepageBwbController {
	
	@RequestMapping(value="/t1/home.tw")
	public String test_test1() {
		return "bwb/homepage.gwTiles"; 
	}
	
	
}
