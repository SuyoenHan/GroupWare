package com.t1works.groupware.bwb.model;

import java.util.List;
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

	// 모든직위 가져오기
	@Override
	public List<MemberBwbVO> selectPositionList() {
		
		List<MemberBwbVO> selectPositionList = sqlsession.selectList("memberBwb.selectPositionList");
		return selectPositionList;
	}

	
	// 소속에 따른 직무설명 가져오기
	@Override
	public String selectDuty(String dname) {

		String duty = sqlsession.selectOne("memberBwb.selectDuty", dname);
		return duty;
	}

	
	// pname과 dname을 통해 pcode,dcode 가져오기.
	@Override
	public Map<String, String> selectPDcode(Map<String, String> paraMap) {
		
		Map<String, String> PDMap = sqlsession.selectOne("memberBwb.selectPDcode", paraMap);
		
		return PDMap;
	}

	// 회원정보 업데이트하기
	@Override
	public int updateOneInfo(MemberBwbVO mvo) {
		
		int n = sqlsession.update("memberBwb.updateOneInfo", mvo);
		
		return n;
	}

	
	// pcode에 따른 연차수 가져오기
	@Override
	public String selectOffCnt(String pcode) {
		
		String offcnt = sqlsession.selectOne("memberBwb.selectOffCnt", pcode);
		
		return offcnt;
	}
	
	
	// 직원정보등록하기 ==> 추후 ajax이용할때, 코드 변경예정...
	@Override
	public int registerOne(MemberBwbVO mvo) {
		
		int n = sqlsession.insert("memberBwb.registerOne", mvo);
		return n;
	}
	
    
   
   
   
   
}