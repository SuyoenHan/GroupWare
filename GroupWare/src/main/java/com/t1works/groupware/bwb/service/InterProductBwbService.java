package com.t1works.groupware.bwb.service;

import java.util.List;
import java.util.Map;

import com.t1works.groupware.bwb.model.MemberBwbVO;
import com.t1works.groupware.bwb.model.ProductBwbVO;

public interface InterProductBwbService {
	
	// 미배정 업무 가져오기
	List<ProductBwbVO> selectProudctList(Map<String, String> paraMap);
	
	// 미배정 업무 총 갯수 구해오기(totalProduct)
	int selectNoAssignedProduct(String employeeid);
	
	// 부장의 해당부서팀의 직원들 가져오기
	List<MemberBwbVO> selectMemberList(Map<String, String> paraMap2);
	
	// 업무테이블에서 배정자 update해주기
	int goAssign(Map<String, String> paraMap);
	
	// 배정된 업무 가져오기
	List<ProductBwbVO> selectAssignedList(Map<String, String> paraMap);
	
	// 배정 업무 총 갯수 구해오기(totalProduct)
	int selectAssignedTotal(String employeeid);
	
	// 배정된 부서 업무 모두 뽑아오기((기간,검색어 허용))
	List<ProductBwbVO> selectAllDepartmentToDo(Map<String, String> paraMap);
	
	// 부서 총 업무 갯수알아오기
	int selectdepartProduct(Map<String, String> paraMap);
	
	// === 특정 업무 클릭 시 modal창의 header정보 가져오기
	ProductBwbVO deptgetOneInfoheader(String pNo);
	
	// 부서업무 중 특정업무에 대한 페이징처리해서 고객리스트 뽑아오기
	List<ProductBwbVO> selectClient(Map<String, String> paraMap);
	
	// 해당 상품에 대한 고객명단의 총 페이지수 알아오기
	int selectTotalClient(Map<String, String> paraMap);
	
	//CS1,2,3팀의 실적 건수 가지고 오기
	int selectDoneCount(String string);
	
	// 해당부서의 실적의 가장 예전 날짜, 최근 날짜 가지고 오기
	Map<String, String> selectOldNewDate(String dcode);
	
	// 선택한 년,월에 해당하는 실적 데이터 가지고 오기
	List<ProductBwbVO> selectPerformance(Map<String, String> paraMap);
	
	// chart에 들어가기 위한 1개의 부서에 대한 name값,3개월에 대한 직원 각각 실적건수
	Map<String, String> selectCntPerformance(Map<String, String> paraMap);
	
	// 선택날짜를 가지고 -1달,-2달 값 가지고 오기
	Map<String, String> changeDate(String firstDate);
	
	// chart에 들어가기 위한 부서 name값,3개월에 대한 부서 각각 실적건수
	Map<String, String> selectDepCntPerformance(Map<String, String> paraMap);

}
