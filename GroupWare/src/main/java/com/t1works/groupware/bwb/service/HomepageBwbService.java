package com.t1works.groupware.bwb.service;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.t1works.groupware.bwb.model.InterMemberBwbDAO;
import com.t1works.groupware.bwb.model.MemberBwbVO;
import com.t1works.groupware.common.AES256;
import com.t1works.groupware.common.Sha256;


@Component
@Service
public class HomepageBwbService implements InterHomepageBwbService {
   
   @Autowired
   private InterMemberBwbDAO dao;
   
   @Autowired
   private AES256 aes;
   
   // 로그인시 해당 회원 조회해오기
   @Override
   public MemberBwbVO selectMember(Map<String, String> paraMap) {
      
	  // employeeid를 가지고 주민번호 가져오기
	  String sjubun = dao.selectJubun(paraMap.get("employeeid"));
	  
	  
	  
	  String jubun = "";
	  String fjubun ="";
	  MemberBwbVO mvo = null;
	  
	  try {
		  if(sjubun.length()>13) { 
			  jubun = aes.decrypt(sjubun);
			  fjubun = jubun.substring(0, 6); // 930305
		  }
		  else fjubun = sjubun.substring(0, 6); // 930305;
		  
	  } catch (UnsupportedEncodingException | GeneralSecurityException e) {
		e.printStackTrace();
	  }
	  
	 
	  
	  String passwd = paraMap.get("passwd");
	  
	  if(fjubun.equalsIgnoreCase(passwd)) { // 초기 비밀번호를 안바꾼 사람

		  mvo = dao.selectMember(paraMap);
	  }
	  else {
		  paraMap.put("passwd", Sha256.encrypt(passwd));
		  mvo = dao.selectMember(paraMap);

	  }
      
      return mvo;
   }
   
   
   
   // 로그인기록 테이블에 insert하기
   @Override
   public int insertlogin_history(Map<String, String> paraMap) {
	   
	   int n = dao.insertlogin_history(paraMap);
	   
	   return n;
   }
   
   // 출퇴근기록 테이블에 insert하기(출근시간)
   @Override
   public int insertIntime(Map<String, String> paraMap) {
	   
	   int n = dao.insertIntime(paraMap);
	   return n;
   }
   
   // 출퇴근기록 테이블에 select하기(출근시간)
   @Override
   public String selectIntime(Map<String, String> paraMap) {
	   
	   String intime = dao.selectIntime(paraMap);
	   return intime;
   }
   
   
   // 지각여부 판단하기(update)
   @Override
   public void updatelateno(Map<String, String> paraMap) {
	   
	   dao.updatelateno(paraMap);
	   
   }
   
   
	// 지각여부 판단하기(select)
	@Override
	public String selectlateno(String intime) {
		
		String latenoTime = dao.selectlateno(intime);
		
		return latenoTime;
	}
	
	// 출퇴근기록 테이블에 update하기(퇴근시간)
	@Override
	public int updateOuttime(Map<String, String> paraMap) {
		
		int n = dao.updateOuttime(paraMap);
		
	    return n;
	}
	
	// 출퇴근테이블에서 select하기(퇴근시간)
	@Override
	public String selectOuttime(Map<String, String> paraMap) {
		
		String outtime = dao.selectOuttime(paraMap);
		
	    return outtime;
	}
	

   
}