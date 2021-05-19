package com.t1works.groupware.controller.bwb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestControllerBwB {
	
	@RequestMapping(value="/t1/home.tw")
	public String test_test1() {
		return "bwb/homepage.gwTiles"; 
	}
	
	
}
