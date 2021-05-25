package com.t1works.groupware.bwb.model;

import java.util.List;
import java.util.Map;


public interface InterMemberBwbDAO {

   // 로그인 시 직원 select해오기
   MemberBwbVO selectMember(Map<String, String> paraMap);
   
   // 로그인기록 테이블에 insert하기
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
   
   // 모든직위 가져오기
   List<MemberBwbVO> selectPositionList();
  
   // 소속에 따른 직무설명 가져오기
   String selectDuty(String dname);
   
   // pname과 dname을 통해 pcode,dcode 가져오기.
   Map<String, String> selectPDcode(Map<String, String> paraMap);
   
   // 회원정보 업데이트하기 
   int updateOneInfo(MemberBwbVO mvo);


}