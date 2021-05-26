package com.t1works.groupware.bwb.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.t1works.groupware.bwb.model.InterMemberBwbDAO;
import com.t1works.groupware.bwb.model.MemberBwbVO;

@Component
@Service
public class MemberBwbService implements InterMemberBwbService {
	
	@Resource
   private InterMemberBwbDAO dao;
	
	// 모든 직위가져오기
	@Override
	public List<MemberBwbVO> selectPositionList() {
		
		List<MemberBwbVO> selectPositionList = dao.selectPositionList();
		
		return selectPositionList;
	}
	
	
	// 소속에 따른 직무설명 가져오기	
	@Override
	public String selectDuty(String dname) {
		
		String duty = dao.selectDuty(dname);
		return duty;
	}

	// pname과 dname을 통해 pcode,dcode 가져오기.
	@Override
	public Map<String,String> selectPDcode(Map<String, String> paraMap) {
		
		Map<String,String> PDMap = dao.selectPDcode(paraMap);
		
		return PDMap;
	}

	// 회원정보 업데이트하기
	@Override
	public int updateOneInfo(MemberBwbVO mvo) {
		
		int n = dao.updateOneInfo(mvo);
		
		return n;
	}

	// pcode에 따른 연차수 가져오기
	@Override
	public String selectOffCnt(String pcode) {
		
		String offcnt = dao.selectOffCnt(pcode);
		return offcnt;
	}

	
	// 직원정보등록하기 ==> 추후 ajax이용할때, 코드 변경예정...
	@Override
	public int registerOne(MemberBwbVO mvo) {
		
		int n = dao.registerOne(mvo);
		return n;
	}
	
	

}
