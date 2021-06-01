package com.t1works.groupware.hsy.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.t1works.groupware.hsy.model.*;

@Component
@Service
public class MemberHsyService implements InterMemberHsyService {

	@Autowired 
	private InterMemberHsyDAO mdao;
	
	@Autowired 
	private InterDepartmentHsyDAO ddao;
	
	
	// 모든 부서에 대한 정보 가져오기
	@Override
	public List<DepartmentHsyVO> selectAllDepartment() {
		List<DepartmentHsyVO> departmentList= ddao.selectAllDepartment();
		return departmentList;
		
	} // end of public List<DepartmentHsyVO> selectAllDepartment() {----
	
	// 모든 직원 정보 가져오기
	@Override
	public List<MemberHsyVO> selectAllMember() {
		List<MemberHsyVO> employeeList= mdao.selectAllMember();
		return employeeList;
	} // end of public List<MemberHsyVO> selectAllMember() {----

	// 주소록 직원목록 총페이지
	@Override
	public int selectTotalPage(Map<String, String> paraMap) {

		int n=0;
		
		// 검색조건이 있는 경우
		if(paraMap.get("searchType")!=null && paraMap.get("searchWord")!=null && !("".equals(paraMap.get("searchType")) || "".equals(paraMap.get("searchWord")))) {
			n=mdao.selectTotalPage1(paraMap);
		}
		
		// 검색조건이 없는 경우
		else { 
			n=mdao.selectTotalPage2(paraMap.get("sizePerPage"));
		}
		
		return n;
	} // end of public int selectTotalPage(Map<String, String> paraMap) {-----

	// 페이징 직원목록 가져오기
	@Override
	public List<MemberHsyVO> selectPagingMember(Map<String, String> paraMap) {

		 List<MemberHsyVO> selectPagingMember=null;
		 
		 int currentShowPageNo= Integer.parseInt(paraMap.get("currentShowPageNo"));
		 int sizePerPage= Integer.parseInt(paraMap.get("sizePerPage"));
		 
		 String start=String.valueOf((currentShowPageNo * sizePerPage) - (sizePerPage - 1));
		 String end=String.valueOf((currentShowPageNo * sizePerPage));
		 
		 paraMap.put("start", start);
		 paraMap.put("end", end);
		 
		 Map<String, String> paraMap2= new HashMap<>();
		 paraMap2.put("start", start);
		 paraMap2.put("end", end);
		 
		// 검색조건이 있는 경우
		 if(paraMap.get("searchType")!=null && paraMap.get("searchWord")!=null && !("".equals(paraMap.get("searchType")) || "".equals(paraMap.get("searchWord")))) {
			selectPagingMember=mdao.selectPagingMember1(paraMap);
		}
		 
		// 검색조건이 없는 경우
		else { 
			selectPagingMember=mdao.selectPagingMember2(paraMap2);
		}
		
		return selectPagingMember;
	} // end of public List<MemberHsyVO> selectPagingMember(Map<String, String> paraMap) {----

	// 사번에 해당하는 직원정보 가져오기
	@Override
	public MemberHsyVO employeeInfoAjaxHsy(String employeeid) {

		MemberHsyVO mvo= mdao.employeeInfoAjaxHsy(employeeid);
		return mvo;
	} // end of public MemberHsyVO employeeInfoAjaxHsy(String employeeid) ---

	
	// 특정직원의 근태결재 승인처리 완료된 문서번호
	@Override
	public List<String> getAttendanceAno(String employeeid) {
		
		List<String> anoList= mdao.getAttendanceAno(employeeid);
		return anoList;
	} // end of public List<String> getAttendanceAno(String employeeid) {----

	
	// 월급명세서에 필요한 정보 가져오기 => 근태내역 (연차/병가/지각/반차/경조휴가 사용 일 수)
	@Override
	public Map<String, Integer> getAttendanceForSalary(Map<String, String> paraMap) {
		
		Map<String,Integer> attendanceMap= mdao.getAttendanceForSalary(paraMap);
		return attendanceMap;
	} // end of public Map<String, String> getAttendanceForSalary(Map<String, String> paraMap) {----
	
	
	// 해당 직원의 특정 년도, 월의  총 야근 시간 가져오기
	@Override
	public int getTotalLateWorkTime(Map<String, String> paraMap) {

		int totalLateWorkTime= mdao.getTotalLateWorkTime(paraMap);
		return totalLateWorkTime;
	} // end of public int getTotalLateWorkTime(Map<String, String> paraMap) {-----

	
	
	
	
}
