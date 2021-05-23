package com.t1works.groupware.bwb.model;

import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;


//=== #32. DAO 선언 ===
@Repository
public class MemberBwbDAO implements InterMemberBwbDAO {
   
   @Resource
   private SqlSessionTemplate sqlsession;
   
   
   @Override
   public MemberBwbVO selectMember(Map<String, String> paraMap) {
      
      MemberBwbVO mvo = sqlsession.selectOne("memberBwb.selectMember", paraMap);
      
      return mvo;
   }


   @Override
   public int insertlogin_history(Map<String, String> paraMap) {
	   
	  int n = sqlsession.insert("memberBwb.insertlogin_history", paraMap);
	   
	  return n;
   }


   @Override
   public int insertIntime(Map<String, String> paraMap) {
	   
	   int n = sqlsession.insert("memberBwb.insertIntime", paraMap);
	   return n;
   }


   @Override
   public String selectIntime(Map<String, String> paraMap) {
	   
	   String intime = sqlsession.selectOne("memberBwb.selectIntime", paraMap);
	   return intime;
   }

   // 지각여부 판단하기(update)
	@Override
	public void updatelateno(Map<String, String> paraMap) {
		
		sqlsession.update("memberBwb.updateLateno", paraMap);

	}

	// 지각여부 판단하기(selects)	
	@Override
	public String selectlateno(String intime) {
		
		String latenoTime = sqlsession.selectOne("memberBwb.selectlateno", intime);
		return latenoTime;
	}
	
    
   
   
   
   
}