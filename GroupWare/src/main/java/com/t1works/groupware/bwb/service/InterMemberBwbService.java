package com.t1works.groupware.bwb.service;

import java.util.Map;

import com.t1works.groupware.bwb.model.MemberBwbVO;

public interface InterMemberBwbService {
   
   // 직원테이블에서 select해오기
   MemberBwbVO selectMember(Map<String, String> paraMap);

   
}