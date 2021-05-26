package com.t1works.groupware.ody.model;

import java.util.List;
import java.util.Map;



public interface InterScheduleOdyDAO {

	// 캘린더 소분류 가져오기
	List<ScalCategoryOdyVO> getSmallCategory(Map<String,String> paraMap);

	// 일정 관리 등록
	int registerSchedule(Map<String,String> paraMap);

	// 등록된 일정 가져오기
	List<ScheduleOdyVO> getScheduleList(Map<String,String> paraMap);

	// 사원 명단 불러오기
	List<MemberOdyVO> searchJoinEmpList(String joinEmp);

}
