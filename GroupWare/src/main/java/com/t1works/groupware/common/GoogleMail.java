package com.t1works.groupware.common;

import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;

import org.springframework.stereotype.Component;

import com.t1works.groupware.hsy.model.ClientHsyVO;

@Component
public class GoogleMail {

	
	// 여행상품 결제 후 보내는 메일
	public void sendmail(String clientemail, String clientname, String pName) throws Exception {
		
		// 1. 정보를 담기 위한 객체
		// Properties는 map과 같은 것으로 <String,String> 타입만 받는다
        Properties prop = new Properties(); 
        
        // 2. SMTP(Simple Mail Transfer Protocoal) 서버의 계정 설정
        //    Google Gmail 과 연결할 경우 Gmail 의 email 주소를 지정 
        prop.put("mail.smtp.user","hansysample@gmail.com");
        
        // 3. SMTP 서버 정보 설정
        //    Google Gmail 인 경우  smtp.gmail.com
        prop.put("mail.smtp.host", "smtp.gmail.com");
             
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.debug", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        prop.put("mail.smtp.socketFactory.fallback", "false");
        
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        
        // 다형성으로 부모클래스 자료형 사용
        Authenticator smtpAuth= new MySMTPAuthenticator();
        Session ses= Session.getInstance(prop,smtpAuth);
        
        // 메일을 전송할 때 상세한 상황을 콘솔에 출력한다.
        ses.setDebug(false);
        
        // 메일의 내용을 담기 위한 객체생성
        MimeMessage msg = new MimeMessage(ses);
        
        // 제목 설정
        String subject = "티원여행사에서 보낸 메일입니다.";
        msg.setSubject(subject);
        
        // 보내는 사람의 메일주소
        String sender = "hansysample@gmail.com";
        Address fromAddr = new InternetAddress(sender);
        msg.setFrom(fromAddr);
        
        // 받는 사람의 메일주소
        Address toAddr = new InternetAddress(clientemail);
        msg.addRecipient(Message.RecipientType.TO, toAddr);
	
        // 메시지 본문의 내용과 형식, 캐릭터 셋 설정
        msg.setContent(clientname+" 고객님의  <span style='color: red; font-weight: bold;'>\""+pName+"\" </span> 예약이 성공적으로 완료되었습니다. <br/>즐거운 여행 되시길 바랍니다.", "text/html;charset=UTF-8");
        
        // 메일 발송하기
        Transport.send(msg);
	
	} // end of public void sendmail(String clientemail, String clientname, String pName) throws Exception {------

	
	// 진행 중 업무에서 고객에게 보내는 메일 (여행 준비물)
	public void sendmail(ClientHsyVO cvo) throws Exception {
		
		// 1. 정보를 담기 위한 객체
		// Properties는 map과 같은 것으로 <String,String> 타입만 받는다
        Properties prop = new Properties(); 
        
        // 2. SMTP(Simple Mail Transfer Protocoal) 서버의 계정 설정
        //    Google Gmail 과 연결할 경우 Gmail 의 email 주소를 지정 
        prop.put("mail.smtp.user","hansysample@gmail.com");
        
        // 3. SMTP 서버 정보 설정
        //    Google Gmail 인 경우  smtp.gmail.com
        prop.put("mail.smtp.host", "smtp.gmail.com");
             
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.debug", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        prop.put("mail.smtp.socketFactory.fallback", "false");
        
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        
        // 다형성으로 부모클래스 자료형 사용
        Authenticator smtpAuth= new MySMTPAuthenticator();
        Session ses= Session.getInstance(prop,smtpAuth);
        
        // 메일을 전송할 때 상세한 상황을 콘솔에 출력한다.
        ses.setDebug(false);
        
        // 메일의 내용을 담기 위한 객체생성
        MimeMessage msg = new MimeMessage(ses);
        
        // 제목 설정
        String subject = "티원여행사에서 보낸 메일입니다.";
        msg.setSubject(subject);
        
        // 보내는 사람의 메일주소
        String sender = "hansysample@gmail.com";
        Address fromAddr = new InternetAddress(sender);
        msg.setFrom(fromAddr);
        
        // 받는 사람의 메일주소
        Address toAddr = new InternetAddress(cvo.getClientemail());
        msg.addRecipient(Message.RecipientType.TO, toAddr);
	
        // 메시지 본문의 내용과 형식, 캐릭터 셋 설정
        msg.setContent("[총 "+cvo.getCnumber()+"명] "+cvo.getClientname()+" 님이 결제하신 <span style='color: red; font-weight: bold;'>"+cvo.getpName()+"</span> 이 "+cvo.getStartDate()+" 에 출발합니다. <br/>"+ 
		        	   "아래의 준비물을 챙겨오시기 바랍니다 <br/>"+
		        	   "1)&nbsp;마실 물&nbsp;&nbsp;&nbsp; 2)&nbsp;세면도구&nbsp;&nbsp;&nbsp; 3)&nbsp;여분용 마스크&nbsp;&nbsp;&nbsp;4)&nbsp;슬리퍼"
		        		, "text/html;charset=UTF-8");
		        
        // 메일 발송하기
        Transport.send(msg);
		
	} // end of public void sendmail(ClientHsyVO cvo) throws Exception {------
	
	// 진행 중 업무에서 고객에게 보내는 메일 (여행 준비물)
	public void sendmail_done(ClientHsyVO cvo) throws Exception {
		
		// 1. 정보를 담기 위한 객체
		// Properties는 map과 같은 것으로 <String,String> 타입만 받는다
        Properties prop = new Properties(); 
        
        // 2. SMTP(Simple Mail Transfer Protocoal) 서버의 계정 설정
        //    Google Gmail 과 연결할 경우 Gmail 의 email 주소를 지정 
        prop.put("mail.smtp.user","hansysample@gmail.com");
        
        // 3. SMTP 서버 정보 설정
        //    Google Gmail 인 경우  smtp.gmail.com
        prop.put("mail.smtp.host", "smtp.gmail.com");
             
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.debug", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        prop.put("mail.smtp.socketFactory.fallback", "false");
        
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        
        // 다형성으로 부모클래스 자료형 사용
        Authenticator smtpAuth= new MySMTPAuthenticator();
        Session ses= Session.getInstance(prop,smtpAuth);
        
        // 메일을 전송할 때 상세한 상황을 콘솔에 출력한다.
        ses.setDebug(false);
        
        // 메일의 내용을 담기 위한 객체생성
        MimeMessage msg = new MimeMessage(ses);
        
        // 제목 설정
        String subject = "티원여행사에서 보낸 메일입니다.";
        msg.setSubject(subject);
        
        // 보내는 사람의 메일주소
        String sender = "hansysample@gmail.com";
        Address fromAddr = new InternetAddress(sender);
        msg.setFrom(fromAddr);
        
        // 받는 사람의 메일주소
        Address toAddr = new InternetAddress(cvo.getClientemail());
        msg.addRecipient(Message.RecipientType.TO, toAddr);
	
        // 메시지 본문의 내용과 형식, 캐릭터 셋 설정
        msg.setContent("안녕하세요 "+cvo.getClientname()+"님 "+cvo.getStartDate()+" 출발하신<span style='color:blue'>"+cvo.getpName()+"</span>은 즐거우셨습니까?<br/>"+ 
		        	   "이번에 새로운 여행상품이 등록되었습니다. 아래주소를 클릭해주세요!! <br/>"+
		        	   "<a href='http://localhost:9090/groupware/t1/travelAgency.tw'>티원투어</a>"
		        		, "text/html;charset=UTF-8");
		        
        // 메일 발송하기
        Transport.send(msg);
		
	} // end of public void sendmail(ClientHsyVO cvo) throws Exception {------
	
	
}

