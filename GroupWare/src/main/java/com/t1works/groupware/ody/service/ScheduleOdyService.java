package com.t1works.groupware.ody.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import com.t1works.groupware.ody.model.InterScheduleOdyDAO;
import com.t1works.groupware.ody.model.MemberOdyVO;
import com.t1works.groupware.ody.model.ScalCategoryOdyVO;
import com.t1works.groupware.ody.model.ScheduleOdyVO;

@Component
@Service
public class ScheduleOdyService implements InterScheduleOdyService{

	@Autowired
	private InterScheduleOdyDAO sdao;
	
	// 캘린더 소분류 가져오기
	@Override
	public List<ScalCategoryOdyVO> getSmallCategory(Map<String,String> paraMap) {
		List<ScalCategoryOdyVO> scategoryList = sdao.getSmallCategory(paraMap);
		return scategoryList;
	}

	// 일정 관리 등록
	@Override
	public int registerSchedule(Map<String,String> paraMap) {
		int n = sdao.registerSchedule(paraMap);
		return n;
	}

	// 등록된 일정 가져오기
	@Override
	public List<ScheduleOdyVO> getScheduleList(String fk_employeeid) {
		List<ScheduleOdyVO> scheduleList = sdao.getScheduleList(fk_employeeid);
		return scheduleList;
	}

	// 사원 명단 불러오기
	@Override
	public List<MemberOdyVO> searchJoinEmpList(String joinEmp) {
		List<MemberOdyVO> joinEmpList = sdao.searchJoinEmpList(joinEmp);
		return joinEmpList;
	}

	// 일정 상세 보기
	@Override
	public ScheduleOdyVO getDetailSchedule(String sdno) {
		ScheduleOdyVO svo = sdao.getDetailSchedule(sdno);
		return svo;
	}

	// 일정 상세보기에서 삭제 클릭
	@Override
	public int delSchedule(String sdno) {
		int n=sdao.delSchedule(sdno);
		return n;
	}

	// 일정 수정 완료
	@Override
	public int editEndSchedule(Map<String,String> paraMap) {
		int n= sdao.editEndSchedule(paraMap);
		return n;
	}

	// 총 게시물 건수(totalCount)
	@Override
	public int getTotalCount(Map<String, String> paraMap) {
		int n = sdao.getTotalCount(paraMap);
		return n;
	}

	 // 페이징 처리한 글목록 가져오기
	@Override
	public List<ScheduleOdyVO> scheduleListSearchWithPaging(Map<String, String> paraMap) {
		 List<ScheduleOdyVO> scheduleList = sdao.scheduleListSearchWithPaging(paraMap);
		return scheduleList;
	}

	// 내 캘린더 정보 가져오기
	@Override
	public List<ScalCategoryOdyVO> getEmpSList(String employeeid) {
		 List<ScalCategoryOdyVO> empSList = sdao.getEmpSList(employeeid);
		return empSList;
	}

	// 전체 캘린더 정보 가져오기
	@Override
	public List<ScalCategoryOdyVO> getFullSList() {
		List<ScalCategoryOdyVO> fullSList = sdao.getFullSList();
		return fullSList;
	}

	// 내 캘린더 추가
	@Override
	public int addMyCalendar(Map<String, String> paraMap) {
		int n = sdao.addMyCalendar(paraMap);
		return n;
	}

	// 소분류 캘린더 삭제
	@Override
	public int deleteCalendar(String scno) {
		int n = sdao.deleteCalendar(scno);
		return n;
	}

	// 사내 캘린더 추가 기능
	@Override
	public int addComCalendar(Map<String, String> paraMap) {
		int n = sdao.addComCalendar(paraMap);
		return n;
	}

	// 캘린더 수정
	@Override
	public int editCalendar(Map<String, String> paraMap) {
		int n = sdao.editCalendar(paraMap);
		return n;
	}


	
}
