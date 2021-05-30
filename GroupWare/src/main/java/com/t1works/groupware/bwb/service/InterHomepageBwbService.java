package com.t1works.groupware.bwb.service;

import java.util.List;
import java.util.Map;

import com.t1works.groupware.bwb.model.MemberBwbVO;

public interface InterHomepageBwbService {
   
   // 직원테이블에서 select해오기
   MemberBwbVO selectMember(Map<String, String> paraMap);
   
   // 로그인 기록테이블에 insert하기 
   int insertlogin_history(Map<String, String> paraMap);
   
   // 출퇴근기록 테이블에 insert하기(출근시간)
   int insertIntime(Map<String, String> paraMap);
   
   // 출퇴근기록 테이블에서 출근시간 select하기
   String selectIntime(Map<String, String> paraMap);
   
   // 지각여부 판단하기(update)
   void updatelateno(Map<String, String> paraMap);
   
   // 지각여부 판단하기(select)
   String selectlateno(String intime);
   
   // 출퇴근기록 테이블에 update하기(퇴근시간)
   int updateOuttime(Map<String, String> paraMap);
   
   // 출퇴근테이블에서 select하기(퇴근시간)
   String selectOuttime(Map<String, String> paraMap);
   




   
}