package com.t1works.groupware.ody.model;

import java.util.List;
import java.util.Map;



public interface InterScheduleOdyDAO {

	// 캘린더 소분류 가져오기
	List<ScalCategoryOdyVO> getSmallCategory(Map<String,String> paraMap);

	// 일정 관리 등록
	int registerSchedule(Map<String,String> paraMap);

	// 등록된 일정 가져오기
	List<ScheduleOdyVO> getScheduleList(String fk_employeeid);

	// 사원 명단 불러오기
	List<MemberOdyVO> searchJoinEmpList(String joinEmp);

	// 일정 상세 보기
	ScheduleOdyVO getDetailSchedule(String sdno);

	// 일정 상세보기에서 삭제 클릭
	int delSchedule(String sdno);

	// 일정 수정 완료
	int editEndSchedule(Map<String,String> paraMap);

	// 총 게시물 건수(totalCount)
	int getTotalCount(Map<String, String> paraMap);

	// 페이징 처리한 글목록 가져오기
	List<ScheduleOdyVO> scheduleListSearchWithPaging(Map<String, String> paraMap);

	// 내 캘린더 정보 가져오기
	List<ScalCategoryOdyVO> getEmpSList(String employeeid);

	// 전체 캘린더 정보 가져오기
	List<ScalCategoryOdyVO> getFullSList();

	// 내 캘린더 추가
	int addMyCalendar(Map<String, String> paraMap);

	// 내 캘린더의 소분류인 이름 존재 여부
	int existMyCalendar(Map<String, String> paraMap);
		
	// 소분류 캘린더 삭제
	int deleteCalendar(String scno);

	// 사내 캘린더의 소분류인 이름 존재 여부
	int existComCalendar(String scname);

		
	// 사내 캘린더 추가
	int addComCalendar(Map<String, String> paraMap);

	// 캘린더 수정하기
	int editCalendar(Map<String, String> paraMap);

	// 홈페이지에서 내 캘린더 보이기
	List<ScheduleOdyVO> getMyCalendarList(String employeeid);

	// 홈페이지에서 해당 날짜를 클릭했을 때 내 일정 가져오기
	List<ScheduleOdyVO> myCalendarInfo(Map<String, String> paraMap);

	// 오늘 날짜 내 캘린더 보여주기
	List<ScheduleOdyVO> todayMyCal(String employeeid);





	
	

}
