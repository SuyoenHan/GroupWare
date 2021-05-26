package com.t1works.groupware.bwb.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.t1works.groupware.bwb.model.InterMemberBwbDAO;
import com.t1works.groupware.bwb.model.MemberBwbVO;


@Component
@Service
public class HomepageBwbService implements InterHomepageBwbService {
   
   @Resource
   private InterMemberBwbDAO dao;
   
   // 로그인시 해당 회원 조회해오기
   @Override
   public MemberBwbVO selectMember(Map<String, String> paraMap) {
      
      MemberBwbVO mvo = dao.selectMember(paraMap);
      
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