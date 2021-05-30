package com.t1works.groupware.bwb.model;

import java.util.List;
import java.util.Map;

public interface InterProductBwbDAO {
	
	// 미배정 업무 정보 가져오기
	List<ProductBwbVO> selectProudctList(Map<String, String> paraMap);
	
	// 미배정 업무 총 갯수 구해오기
	int selectNoAssignedProduct(String employeeid);
	
	// 부장의 해당부서팀의 직원들 가져오기
	List<MemberBwbVO> selectMemberList(Map<String, String> paraMap2);
	
	// 업무테이블에서 배정자 update해주기
	int goAssign(Map<String, String> paraMap);
	
	// 배정된 업무 가져오기
	List<ProductBwbVO> selectAssignedList(Map<String, String> paraMap);
	
	// 배정 업무 총 갯수 구해오기(totalProduct)
	int selectAssignedTotal(String employeeid);

}
