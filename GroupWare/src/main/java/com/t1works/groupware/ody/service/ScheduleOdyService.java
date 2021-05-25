package com.t1works.groupware.ody.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.t1works.groupware.ody.model.InterScheduleOdyDAO;
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
	public int registerSchedule(ScheduleOdyVO svo) {
		int n = sdao.registerSchedule(svo);
		return n;
	}

	// 등록된 일정 가져오기
	@Override
	public List<ScheduleOdyVO> getScheduleList(Map<String,String> paraMap) {
		List<ScheduleOdyVO> scheduleList = sdao.getScheduleList(paraMap);
		return scheduleList;
	}
	
	
}
