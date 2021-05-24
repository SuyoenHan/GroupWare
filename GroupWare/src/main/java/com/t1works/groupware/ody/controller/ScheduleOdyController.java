package com.t1works.groupware.ody.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Component
@Controller
public class ScheduleOdyController {
	
	
	
	@RequestMapping(value="/t1/schedule.tw")
	public String requiredLogin_showSchedule(HttpServletRequest request, HttpServletResponse response) {
	
		return "ody/schedule/showSchedule.gwTiles";
	}
	
	
	
	@RequestMapping(value="/t1/insertSchedule.tw")
	public String requiredLogin_insertSchedule(HttpServletRequest request, HttpServletResponse response) {
		
		return "ody/schedule/insertSchedule.gwTiles";
	}
	
	
}
