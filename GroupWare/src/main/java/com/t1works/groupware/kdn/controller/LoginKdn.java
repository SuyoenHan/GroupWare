package com.t1works.groupware.kdn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class LoginKdn {

	@RequestMapping(value="/t1/login.tw")
	public String test_test1() {
		return "kdn/login"; 
	}
	
}
