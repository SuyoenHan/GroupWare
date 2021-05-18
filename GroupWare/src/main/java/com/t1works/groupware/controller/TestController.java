package com.t1works.groupware.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
	
	@RequestMapping(value="/test/home.tw")
	public String test_test1() {
		return "bwb/test.gwTiles"; 
	}
	
	
}
