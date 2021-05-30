package com.t1works.groupware.hsy.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.nurigo.java_sdk.api.Message;

@Component
@Controller
public class SendSmsHsyController {
	
	// 문자보내는 매핑 url
	@ResponseBody
	@RequestMapping(value="/t1/sendSms.tw",method= {RequestMethod.POST}, produces="text/plain;charset=UTF-8")
	public String sendSms(HttpServletRequest request) {
		
		//   >> SMS발송 <<
	    //   HashMap 에 받는사람번호, 보내는사람번호, 문자내용 등 을 저장한뒤 Coolsms 클래스의 send를 이용해 문자 전송
		
		// 1) 문자보내기 기본셋팅
		String api_key = "NCSPKYRQYQRKDK2A"; // 한수연 api_key
		String api_secret = "BARXKSL0EHFBGFO60JXSKGBOSNZQIWYY";  // 한수연 api_secret
		
		// net.nurigo.java_sdk.api.Message 
		Message coolsms = new Message(api_key, api_secret);	
		
		// 2) 문자보낼 때 필요한 정보 가져오기
		String mobile= request.getParameter("mobile"); // 받는사람 번호
		String smsContent= request.getParameter("smsContent"); // 문자내용
			
		// 3) 문자보낼때 반드시 필요한 파라미터 4개 (to, from, type, text) => 즉시전송 할 것이므로 전송시간 사용안함
		HashMap<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("to", mobile); // 수신번호
		paraMap.put("from", "01077976014"); // 발신번호 
		paraMap.put("type", "SMS"); // Message type ( SMS(단문), LMS(장문), MMS, ATA )
		paraMap.put("text", smsContent); // 문자내용    
		
		paraMap.put("app_version", "JAVA SDK v2.2"); // application name and version
			
		JSONObject jsobj= null;
		
		try {
			jsobj = (JSONObject)coolsms.send(paraMap);
		} catch (Exception e) {
			System.out.println("문자보내기에 실패하셨습니다.");		
		}
	    /*
        	org.json.JSONObject 이 아니라 
        	org.json.simple.JSONObject 이어야 한다.  
        */
		    
		return jsobj.toString();
		
	} // end of public String sendSms---------------------------------------------------------
}
