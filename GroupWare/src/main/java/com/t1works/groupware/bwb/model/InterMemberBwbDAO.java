package com.t1works.groupware.bwb.model;

import java.util.Map;


public interface InterMemberBwbDAO {

   // 로그인 시 직원 select해오기
   MemberBwbVO selectMember(Map<String, String> paraMap);

}