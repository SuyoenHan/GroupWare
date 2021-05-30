package com.t1works.groupware.bwb.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.t1works.groupware.bwb.model.InterProductBwbDAO;
import com.t1works.groupware.bwb.model.MemberBwbVO;
import com.t1works.groupware.bwb.model.ProductBwbVO;

@Component
@Service
public class ProductBwbService implements InterProductBwbService {
	
	 @Autowired
     private InterProductBwbDAO dao; 
	 
	// 미배정 업무정보 가져오기
	 @Override
	 public List<ProductBwbVO> selectProudctList(Map<String, String> paraMap) {
		
		List<ProductBwbVO> productList = dao.selectProudctList(paraMap);
		return productList;
	 }
	 
	// 미배정 업무 총 갯수 구해오기
	@Override
	public int selectNoAssignedProduct(String employeeid) {
		
		int totalProduct = dao.selectNoAssignedProduct(employeeid);
		
		return totalProduct;
	}
	
	// 부장의 해당부서팀의 직원들 가져오기
	@Override
	public List<MemberBwbVO> selectMemberList(Map<String, String> paraMap2) {
		
		List<MemberBwbVO> memberList = dao.selectMemberList(paraMap2);
		return memberList;
	}
	
	// 업무테이블에서 배정자 update해주기
	@Override
	public int goAssign(Map<String, String> paraMap) {

		int n = dao.goAssign(paraMap);
		return n;
	}
	
	// 배정된 업무 가져오기
	@Override
	public List<ProductBwbVO> selectAssignedList(Map<String, String> paraMap) {
		
		List<ProductBwbVO> assignedList = dao.selectAssignedList(paraMap);
		return assignedList;
	}
	
	// 배정 업무 총 갯수 구해오기(totalProduct)
	@Override
	public int selectAssignedTotal(String employeeid) {
		
		int totalProduct = dao.selectAssignedTotal(employeeid);
		
		return totalProduct;
	}
	
	
	

}
