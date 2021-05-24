package com.t1works.groupware.bwb.model;

import java.util.Map;


public interface InterMemberBwbDAO {

   // 로그인 시 직원 select해오기
   MemberBwbVO selectMember(Map<String, String> paraMap);
   
   // 로그인기록 테이블에 insert하기
   int insertlogin_history(Map<String, String> paraMap);

   // 출퇴근기록 테이블에 insert하기
   int insertIntime(Map<String, String> paraMap);

   // 출퇴근기록 테이블에서 출근시간 select하기
   String selectIntime(Map<String, String> paraMap);
   
   // 지각여부 판단하기(update)
   void updatelateno(Map<String, String> paraMap);
   
   // 지각여부 판단하기(select)
   String selectlateno(String intime);


}