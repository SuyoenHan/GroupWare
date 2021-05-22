package com.t1works.groupware.hsy.model;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public class MemberHsyDAO implements InterMemberHsyDAO {

	@Autowired 
	private SqlSessionTemplate sqlsession2;
	
	
	// 모든 직원 정보 가져오기
	@Override
	public List<MemberHsyVO> selectAllMember() {
		List<MemberHsyVO> employeeList= sqlsession2.selectList("memberHsy.selectAllMember");
		return employeeList;
	}

	// 검색조건이 있는 직원목록 총페이지
	@Override
	public int selectTotalPage1(Map<String, String> paraMap) {
		int n=sqlsession2.selectOne("memberHsy.selectTotalPage1",paraMap);
		return n;
	}

	// 검색조건이 없는 직원목록 총페이지
	@Override
	public int selectTotalPage2(String sizePerPage) {
		int n=sqlsession2.selectOne("memberHsy.selectTotalPage2", sizePerPage);
		return n;
	}

	// 검색조건이 있는 직원목록 가져오기 (페이징처리)
	@Override
	public List<MemberHsyVO> selectPagingMember1(Map<String, String> paraMap) {
		List<MemberHsyVO> selectPagingMember=sqlsession2.selectList("memberHsy.selectPagingMember1", paraMap);
		return selectPagingMember;
		
	}

	// 검색조건이 없는 직원목록 가져오기 (페이징처리)
	@Override
	public List<MemberHsyVO> selectPagingMember2(Map<String, String> paraMap2) {
		
		List<MemberHsyVO> selectPagingMember= sqlsession2.selectList("memberHsy.selectPagingMember2", paraMap2);
		return selectPagingMember;
	}

	// 사번에 해당하는 직원정보 가져오기
	@Override
	public MemberHsyVO employeeInfoAjaxHsy(String employeeid) {

		MemberHsyVO mvo= sqlsession2.selectOne("memberHsy.employeeInfoAjaxHsy", employeeid);
		return mvo;
	}



}
