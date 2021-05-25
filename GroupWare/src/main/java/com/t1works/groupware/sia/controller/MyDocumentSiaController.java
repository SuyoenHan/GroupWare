package com.t1works.groupware.sia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.t1works.groupware.sia.service.InterMyDocumentSiaService;

@Component
@Controller
public class MyDocumentSiaController {
	
	@Autowired // Type에 따라 알아서 Bean 을 주입해준다.
	private InterMyDocumentSiaService service;
	
	// 내문서함 보기 - 일반결재문서가 보여진다.
	@RequestMapping(value="/t1/myDocuNorm.tw")
	public String myDocuNorm() {
		return "sia/myDocument/myDocuNorm.gwTiles";		
	}
	
	
	// 내문서함 보기 - 지출결재문서
	@RequestMapping(value="/t1/myDocuSpend.tw")
	public String myDocuSpend() {
		return "sia/myDocument/myDocuSpend.gwTiles";
	}
	
	// 내문서함 보기 - 근태/휴가결재문서
	@RequestMapping(value="/t1/myDocuVacation.tw")
	public String myDocuVacation() {
		return "sia/myDocument/myDocuVacation.gwTiles";
	}
}
