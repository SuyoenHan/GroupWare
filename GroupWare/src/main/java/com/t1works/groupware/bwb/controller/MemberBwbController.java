package com.t1works.groupware.bwb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.t1works.groupware.bwb.service.InterHomepageBwbService;

@Controller
public class MemberBwbController {
	
	@Autowired // Type에 따라 알아서 Bean 을 주입해준다.
	private InterHomepageBwbService service;
	
	@RequestMapping(value="/t1/toDo.tw")
	public String requiredLogin_insabuzang(HttpServletRequest request, HttpServletResponse response) {
		
		return "bwb/todo/insabuzang.gwTiles";
	}
	
	
}
