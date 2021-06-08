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
   
   // 출퇴근기록 테이블에서 select하기(출근시간)
   String selectIntime(Map<String, String> paraMap);
   
   // 지각여부 판단하기(update)
   void updatelateno(Map<String, String> paraMap);
   
   // 지각여부 판단하기(select)
   String selectlateno(String intime);
   
   // 출퇴근기록 테이블에 update하기(퇴근시간)
   String updateOuttime(Map<String, String> paraMap) throws Throwable;
   
   // 출퇴근기록 테이블에서 select작업(퇴근시간)
   String selectOuttime(Map<String, String> paraMap);
   
   // 이용자의 총 연차수 가지고 오기
   String selectTotaloffCnt(String pcode);
   
   // 이용자의 사용연차수 가지고 오기
   String selectUseoffCnt(String fk_employeeid);
   
   // 나의 월별 출퇴근기록 가지고오기
   List<Map<String, String>> selectmyMonthIndolence(String fk_employeeid);
   
   // 부서 월별 출퇴근기록 가지고오기
   List<Map<String, String>> selectDepMonthIndolence(String fk_dcode);
   
   
   




   
}