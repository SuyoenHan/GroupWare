package com.t1works.groupware.kdn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EmailKdnController {

	@RequestMapping(value="/t1/mail.tw")
	public String view_inbox(){
		
		return "kdn/mail_inbox.gwTiles";
	}
	
	@RequestMapping(value="/t1/mail_sent.tw")
	public String view_sent(){
		
		return "kdn/mail_sent.gwTiles";
	}
	
	@RequestMapping(value="/t1/mail_trash.tw")
	public String view_Trash(){
		
		return "kdn/mail_trash.gwTiles";
	}

	@RequestMapping(value="/t1/mail_important.tw")
	public String view_important(){
		
		return "kdn/mail_important.gwTiles";
	}
	
	@RequestMapping(value="/t1/new_mail.tw")
	public String view_mailform(){
		
		return "kdn/new_mail.gwTiles";
	}
	
}
