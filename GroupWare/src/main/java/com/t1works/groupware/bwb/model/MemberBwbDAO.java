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
   
   
   
   
}