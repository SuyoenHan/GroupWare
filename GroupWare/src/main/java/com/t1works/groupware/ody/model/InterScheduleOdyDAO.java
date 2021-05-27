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

	// 일정 상세 보기
	ScheduleOdyVO getDetailSchedule(String sdno);

	// 일정 상세보기에서 삭제 클릭
	int delSchedule(String sdno);

	// 일정 수정 완료
	int editEndSchedule(Map<String,String> paraMap);

}
