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

   // 출퇴근기록 테이블에 insert하기(출근시간)
   @Override
   public int insertIntime(Map<String, String> paraMap) {
	   
	   int n = sqlsession.insert("memberBwb.insertIntime", paraMap);
	   return n;
   }

   // 출퇴근기록 테이블에 select하기(출근시간)
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

	// 출퇴근기록 테이블에 update하기(퇴근시간)
	@Override
	public int updateOuttime(Map<String, String> paraMap) {
		
		int n = sqlsession.update("memberBwb.updateOuttime", paraMap);
	    return n;
	}

	// 출퇴근테이블에서 select하기(퇴근시간)
	@Override
	public String selectOuttime(Map<String, String> paraMap) {
		
		String outtime = sqlsession.selectOne("memberBwb.selectOuttime", paraMap);
		
	    return outtime;
	}
	
    
   
   
   
   
}