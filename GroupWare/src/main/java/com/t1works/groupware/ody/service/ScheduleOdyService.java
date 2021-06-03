package com.t1works.groupware.ody.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor= {Throwable.class})
	public int addMyCalendar(Map<String, String> paraMap) throws Throwable{

		int n=0;
		
		int m = sdao.existMyCalendar(paraMap);
		
		if(m==0) {
			n = sdao.addMyCalendar(paraMap);
		}
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
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor= {Throwable.class})
	public int addComCalendar(Map<String, String> paraMap) throws Throwable{
		
		int n=0;
		String scname = paraMap.get("scname");
		int m = sdao.existComCalendar(scname);
		
		if(m==0) {
			n = sdao.addComCalendar(paraMap);
		}
		return n;
	}

	// 내 캘린더 수정
	@Override
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor= {Throwable.class})
	public int editMyCalendar(Map<String, String> paraMap) throws Throwable{
		
		int n=0;
		
		int m = sdao.existMyCalendar(paraMap);
		
		if(m==0) {
			n = sdao.editCalendar(paraMap);
		}
		return n;

	}

	// 사내 캘린더 수정
	@Override
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor= {Throwable.class})
	public int editComCalendar(Map<String, String> paraMap) throws Throwable{
		
		int n=0;
		String scname = paraMap.get("scname");
		System.out.println(scname);
		int m = sdao.existComCalendar(scname);
		System.out.println("사내수정:"+m);
		if(m==0) {
			n = sdao.editCalendar(paraMap);
		}
		return n;
	}

	// 홈페이지에서 내 캘린더 보이기
	@Override
	public List<ScheduleOdyVO> getMyCalendarList(String employeeid) {
		List<ScheduleOdyVO> myCalendarList = sdao.getMyCalendarList(employeeid);
		return myCalendarList;
	}

	// 홈페이지에서 해당 날짜를 클릭했을 때 내 일정 가져오기
	@Override
	public List<ScheduleOdyVO> myCalendarInfo(Map<String, String> paraMap) {
		List<ScheduleOdyVO>  mycalInfoList = sdao.myCalendarInfo(paraMap);
		return mycalInfoList;
	}

	// 오늘 날짜 내 캘린더 보여주기
	@Override
	public List<ScheduleOdyVO> todayMyCal(String employeeid) {
		List<ScheduleOdyVO> todayMyCalList= sdao.todayMyCal(employeeid);
		return todayMyCalList;
	}


	
}
