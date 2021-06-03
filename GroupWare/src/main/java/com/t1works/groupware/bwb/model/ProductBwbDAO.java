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
	
	// === #3. 특정 업무 클릭 시 modal창의 header정보 가져오기
	@Override
	public ProductBwbVO deptgetOneInfoheader(String pNo) {
		
		ProductBwbVO pvo = sqlsession.selectOne("productBwb.deptgetOneInfoheader", pNo);
		return pvo;
	}
	
	// 부서업무 중 특정업무에 대한 고객리스트 페이징처리해서 뽑아오기
	@Override
	public List<ProductBwbVO> selectClient(Map<String, String> paraMap) {
		
		List<ProductBwbVO> clientList = sqlsession.selectList("productBwb.selectClient", paraMap);
		return clientList;
	}
	
	
	// 해당 상품에 대한 고객명단의 총 페이지수 알아오기
	@Override
	public int selectTotalClient(Map<String, String> paraMap) {
		
		int totalPage = sqlsession.selectOne("productBwb.selectTotalClient", paraMap);
		return totalPage;
	}
	
	//CS1,2,3팀의 실적 건수 가지고 오기
	@Override
	public int selectDoneCount(String string) {
		
		int team_DoneCount = sqlsession.selectOne("productBwb.selectDoneCount", string);
		return team_DoneCount;
	}
	
	// 해당부서의 실적의 가장 예전 날짜, 최근 날짜 가지고 오기
	@Override
	public Map<String, String> selectOldNewDate(String dcode) {
		
		Map<String, String> paraMap = sqlsession.selectOne("productBwb.selectOldNewDate", dcode);
		return paraMap;
	}
	
	// 선택한 년,월에 해당하는 실적 데이터 가지고 오기
	@Override
	public List<ProductBwbVO> selectPerformance(Map<String, String> paraMap) {
		
		List<ProductBwbVO> performanceList = sqlsession.selectList("productBwb.selectPerformance", paraMap);
		return performanceList;
	}
	
	// chart에 들어가기 위한 name값,3개월에 대한 각각 실적건수
	@Override
	public Map<String, String> selectCntPerformance(Map<String, String> paraMap) {
		Map<String, String> resultMap = sqlsession.selectOne("productBwb.selectCntPerformance", paraMap);
		return resultMap;
	}
	
	
	// 선택날짜를 가지고 -1달,-2달 값 가지고 오기
	@Override
	public Map<String, String> changeDate(String firstDate) {
		
		Map<String, String> paraMap = sqlsession.selectOne("productBwb.changeDate", firstDate);
		return paraMap;
	}

}
