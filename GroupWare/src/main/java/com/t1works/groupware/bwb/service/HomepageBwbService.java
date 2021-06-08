package com.t1works.groupware.bwb.service;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor= {Throwable.class})
	public String updateOuttime(Map<String, String> paraMap) throws Throwable{
		
		 int n = dao.updateOuttime(paraMap);
		
		 // 출퇴근테이블에서 select하기(퇴근시간)
		 if(n==1) {
			 String outtime = dao.selectOuttime(paraMap); // xx:xx:xx
			 
			 int outTimehh = Integer.parseInt(outtime.substring(0, 2)); // 시간만 뽑아오기
			 int outTimemm = Integer.parseInt(outtime.substring(3, 5)); // 분만 뽑아오기
			 
			 String doLateTime = "0"; // 적용되는 시간
			 
			 if((outTimehh==19 && outTimemm>=45)||(outTimehh==20 && outTimemm<45)) {
				 doLateTime="1";
			 }
			 else if((outTimehh==20 && outTimemm>=45)||(outTimehh==21 && outTimemm<45)) {
				 doLateTime="2";
			 }
			 else if((outTimehh==21 && outTimemm>=45)||(outTimehh==22 && outTimemm<45)) {
				 doLateTime="3";
			 }
			 else if((outTimehh==22 && outTimemm>=45)||(outTimehh==23 && outTimemm<45)) {
				 doLateTime="4";
			 }
			 else if((outTimehh==23 && outTimemm>=45 && outTimemm<=59)) {
				 doLateTime="5";
			 }
			 
			 // 야근테이블에 insert하기
			 if(!doLateTime.equalsIgnoreCase("0")) {
				 paraMap.put("doLateTime", doLateTime);
				 int m = dao.insertdoLate(paraMap);
				 
				 if(m==1) {
					 return outtime;
				 }
				 else {
					 return "";
				 }
			 } // end of if(!doLateTime.equalsIgnoreCase("0")) 
			 else {
				 return outtime;
			 }
			 
		 }// end of if(n==1) {
		 else {
			 return "";
		 }

	}// end of public String updateOuttime(Map<String, String> paraMap) throws Throwable


	// 출퇴근기록 테이블에서 select작업(퇴근시간)
	@Override
	public String selectOuttime(Map<String, String> paraMap) {
		String outtime = dao.selectOuttime(paraMap);
		return outtime;
	}


	// 이용자의 총 연차수 가지고 오기
	@Override
	public String selectTotaloffCnt(String pcode) {
		String totalOffCnt = dao.selectTotaloffCnt(pcode);
		return totalOffCnt;
	}


	// 이용자의 사용연차수 가지고 오기
	@Override
	public String selectUseoffCnt(String fk_employeeid) {
		
		String useOffCnt = dao.selectUseoffCnt(fk_employeeid);
		return useOffCnt;
	}


	 // 나의 월별 출퇴근기록 가지고오기
	@Override
	public List<Map<String, String>> selectmyMonthIndolence(String fk_employeeid) {
		
		List<Map<String, String>> myIndolenceList = dao.selectmyMonthIndolence(fk_employeeid);
		return myIndolenceList;
	}


	// 부서 월별 출퇴근기록 가지고오기
	@Override
	public List<Map<String, String>> selectDepMonthIndolence(String fk_dcode) {
		List<Map<String,String>> depIndolenceList = dao.selectDepMonthIndolence(fk_dcode);
		return depIndolenceList;
	}
   
}