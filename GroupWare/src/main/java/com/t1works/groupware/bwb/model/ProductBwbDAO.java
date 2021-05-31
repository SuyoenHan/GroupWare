package com.t1works.groupware.bwb.model;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public class ProductBwbDAO implements InterProductBwbDAO {
	
	
	@Autowired 
	private SqlSessionTemplate sqlsession;
	
	// 미배정 업무리스트 가져오기
	@Override
	public List<ProductBwbVO> selectProudctList(Map<String, String> paraMap) {
		
		List<ProductBwbVO> productList = sqlsession.selectList("productBwb.selectProudctList",paraMap);
		
		return productList;
	}
	
	// 미배정 업무 총 갯수 구해오기
	@Override
	public int selectNoAssignedProduct(String employeeid) {
		
		int totalProduct = sqlsession.selectOne("productBwb.selectNoAssignedProduct",employeeid);
		return totalProduct;
	}
	
	// 부장의 해당부서팀의 직원들 가져오기
	@Override
	public List<MemberBwbVO> selectMemberList(Map<String, String> paraMap2) {
		
		List<MemberBwbVO> memberList = sqlsession.selectList("productBwb.selectMemberList", paraMap2);
		return memberList;
	}
	
	// 업무테이블에서 배정자 update해주기
	@Override
	public int goAssign(Map<String, String> paraMap) {
		
		int n = sqlsession.update("productBwb.goAssign", paraMap);
		return n;
	}
	
	// 배정된 업무 가져오기
	@Override
	public List<ProductBwbVO> selectAssignedList(Map<String, String> paraMap) {
		
		List<ProductBwbVO> assignedList = sqlsession.selectList("productBwb.selectAssignedList", paraMap);
		return assignedList;
	}
	
	// 배정된업무 총 갯수 구해오기
	@Override
	public int selectAssignedTotal(String employeeid) {
		
		int totalProduct = sqlsession.selectOne("productBwb.selectAssignedTotal",employeeid);
		return totalProduct;
	}
	
	// 배정된 부서 업무 모두 뽑아오기((기간,검색어 허용))
	@Override
	public List<ProductBwbVO> selectAllDepartmentToDo(Map<String, String> paraMap) {

		List<ProductBwbVO> productList = sqlsession.selectList("productBwb.selectAllDepartmentToDo",paraMap);
		return productList;
	}
	
	// 부서 총 업무 갯수 알아오기
	@Override
	public int selectdepartProduct(Map<String, String> paraMap) {
		
		int departProduct = sqlsession.selectOne("productBwb.selectdepartProduct", paraMap);
		return departProduct;
	}

}
