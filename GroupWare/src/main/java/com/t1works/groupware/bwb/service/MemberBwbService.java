package com.t1works.groupware.bwb.service;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.t1works.groupware.bwb.model.InterMemberBwbDAO;
import com.t1works.groupware.bwb.model.MemberBwbVO;
import com.t1works.groupware.common.AES256;


@Component
@Service
public class MemberBwbService implements InterMemberBwbService {
	
   @Autowired
   private InterMemberBwbDAO dao;
	
   @Autowired
   private AES256 aes;
   
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
		
		// 주민번호 암호화 하기
		try {
			String jubun = aes.encrypt(mvo.getJubun());
			mvo.setJubun(jubun);
		} catch (UnsupportedEncodingException | GeneralSecurityException e) {
			e.printStackTrace();
		}

		int n = dao.registerOne(mvo);
		return n;
	}
	
	// 등록한 직원의 fk_dcode를 통해 managerid 알아오기
	@Override
	public String selectMangerid(String dcode) {
		
		String managerid = dao.selectMangerid(dcode);
		return managerid;
	}

	// 부서명 가져오기
	@Override
	public String selectdname(String dcode) {
		
		String dname = dao.selectdname(dcode);
		return dname;
	}

	// 직위 가져오기
	@Override
	public String selectpname(String pcode) {
		
		String pname = dao.selectpname(pcode);
		return pname;
	}


	// 비밀번호 변경하기
	@Override
	public int updatePasswd(Map<String, String> paraMap) {
        
		int n = dao.updatePasswd(paraMap);
		
		return n;
	}

	// 부장을 제외한 직원 ID를 가져오기
	@Override
	public List<MemberBwbVO> selectMemberList(String dcode) {
		
		List<MemberBwbVO> memberList = dao.selectMemberList(dcode);
		return memberList;
	}

	// 부서 전체 이름,코드 가져오기
	@Override
	public List<MemberBwbVO> selectDepartmentList() {
		
		List<MemberBwbVO> departmentList = dao.selectDepartmentList();
		return departmentList;
	}
		

}
