package com.t1works.groupware.ody.model;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


@Component
@Repository
public class ScheduleOdyDAO implements InterScheduleOdyDAO {

	@Resource
	private SqlSessionTemplate sqlsession5;
	
	// 캘린더 소분류 가져오기
	@Override
	public List<ScalCategoryOdyVO> getSmallCategory(Map<String,String> paraMap) {
		List<ScalCategoryOdyVO> scategoryList = sqlsession5.selectList("schedule_ody.getSmallCategory",paraMap);
		return scategoryList;
	}

	// 일정 관리 등록
	@Override
	public int registerSchedule(Map<String,String> paraMap) {
		int n = sqlsession5.insert("schedule_ody.registerSchedule",paraMap);
		return n;
	}

	// 등록된 일정 가져오기
	@Override
	public List<ScheduleOdyVO> getScheduleList(String fk_employeeid) {
		List<ScheduleOdyVO> scheduleList = sqlsession5.selectList("schedule_ody.getScheduleList",fk_employeeid);
		return scheduleList;
	}

	// 사원 명단 불러오기
	@Override
	public List<MemberOdyVO> searchJoinEmpList(String joinEmp) {
		List<MemberOdyVO> joinEmpList = sqlsession5.selectList("schedule_ody.searchJoinEmpList",joinEmp); 
		return joinEmpList;
	}

	// 일정 상세 보기
	@Override
	public ScheduleOdyVO getDetailSchedule(String sdno) {
		ScheduleOdyVO svo = sqlsession5.selectOne("schedule_ody.getDetailSchedule",sdno);
		return svo;
	}

	// 일정 상세보기에서 삭제 클릭
	@Override
	public int delSchedule(String sdno) {
		int n = sqlsession5.delete("schedule_ody.delSchedule",sdno);
		return n;
	}

	// 일정 수정 완료
	@Override
	public int editEndSchedule(Map<String,String> paraMap) {
		int n = sqlsession5.update("schedule_ody.editEndSchedule",paraMap);
		return n;
	}

	// 총 게시물 건수(totalCount)
	@Override
	public int getTotalCount(Map<String, String> paraMap) {
		int n = sqlsession5.selectOne("schedule_ody.getTotalCount",paraMap);
		return n;
	}

	// 페이징 처리한 글목록 가져오기
	@Override
	public List<ScheduleOdyVO> scheduleListSearchWithPaging(Map<String, String> paraMap) {
		List<ScheduleOdyVO> scheduleList = sqlsession5.selectList("schedule_ody.scheduleListSearchWithPaging",paraMap);
		return scheduleList;
	}

	// 내 캘린더 정보 가져오기
	@Override
	public List<ScalCategoryOdyVO> getEmpSList(String employeeid) {
		List<ScalCategoryOdyVO> empSList = sqlsession5.selectList("schedule_ody.getEmpSList",employeeid);
		return empSList;
	}

	// 전체 캘린더 정보 가져오기
	@Override
	public List<ScalCategoryOdyVO> getFullSList() {
		List<ScalCategoryOdyVO>  fullSList = sqlsession5.selectList("schedule_ody.getFullSList");
		return  fullSList;
	}

	// 내 캘린더의 소분류 이름 존재 여부 --1 
	@Override
	public int existMyCalendar(Map<String, String> paraMap) {
		int m = sqlsession5.selectOne("schedule_ody.existMyCalendar",paraMap);
		return m;
	}
	
	
	// 내 캘린더 추가--2
	@Override
	public int addMyCalendar(Map<String, String> paraMap) {
		int n = sqlsession5.insert("schedule_ody.addMyCalendar",paraMap);
		return n;
	}

	
	// 소분류 캘린더 삭제
	@Override
	public int deleteCalendar(String scno) {
		int n = sqlsession5.delete("schedule_ody.deleteCalendar",scno);
		return n;
	}

	// 사내 캘린더의 소분류인 이름 존재 여부 -- 1
		@Override
		public int existComCalendar(String scname) {
			int m = sqlsession5.selectOne("schedule_ody.existComCalendar",scname);
			return m;
		}
		
	// 사내 캘린더 추가 -- 2
	@Override
	public int addComCalendar(Map<String, String> paraMap) {
		int n = sqlsession5.insert("schedule_ody.addComCalendar",paraMap);
		return n;
	}

	// 내 캘린더 수정하기 --2
	@Override
	public int editCalendar(Map<String, String> paraMap) {
		int n = sqlsession5.update("schedule_ody.editCalendar",paraMap);
		return n;
	}

	// 홈페이지에서 내 캘린더 보이기
	@Override
	public List<ScheduleOdyVO> getMyCalendarList(String employeeid) {
		List<ScheduleOdyVO> myCalendarList = sqlsession5.selectList("schedule_ody.getMyCalendarList",employeeid);
		return myCalendarList;
	}

	// 홈페이지에서 해당 날짜를 클릭했을 때 내 일정 가져오기
	@Override
	public List<ScheduleOdyVO> myCalendarInfo(Map<String, String> paraMap) {
		List<ScheduleOdyVO> mycalInfoList =  sqlsession5.selectList("schedule_ody.myCalendarInfo",paraMap);
		return mycalInfoList;
	}
	
	// 오늘 날짜 내 캘린더 보여주기
	@Override
	public List<ScheduleOdyVO> todayMyCal(String employeeid) {
		List<ScheduleOdyVO> todayMyCalList =  sqlsession5.selectList("schedule_ody.todayMyCal",employeeid);
		return todayMyCalList;
	}


}
