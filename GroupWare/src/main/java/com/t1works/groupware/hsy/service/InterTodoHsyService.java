package com.t1works.groupware.hsy.service;

import java.util.List;
import java.util.Map;

import com.t1works.groupware.hsy.model.TodoHsyVO;

public interface InterTodoHsyService {

	// 특정유저의 특정업무상태에 해당하는 총 페이지 수 알아오기 
	int selectTotalPage(Map<String, String> paraMap);
	
	// 업무관리페이지에서 보여줄 정보 가져오기 (트랜잭션처리)
	List<TodoHsyVO> employeeTodoList(Map<String, String> paraMap) throws Throwable;

	// 신규등록업무, 진행중업무, 진행완료업무 총 건수 가져오기
	Map<String, String> selectCntTodoByrequiredState(String employeeid);

	// 신규등록업무에서 진행중업무로 업데이트
	int goWorkStart(String fk_pNo);

	// 진행중인 업무 상태변경(진행중 또는 보류)에 따라 update하기
	int updateIngDetail(TodoHsyVO tvo);

	// 진행중 업무에서 진행완료 업무로 업데이트하는 ajax 매핑 url
	int goWorkDone(String fk_pNo);

}
