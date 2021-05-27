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
	public List<ScheduleOdyVO> getScheduleList(Map<String,String> paraMap) {
		List<ScheduleOdyVO> scheduleList = sqlsession5.selectList("schedule_ody.getScheduleList",paraMap);
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

	
}
