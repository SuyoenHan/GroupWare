package com.t1works.groupware.hsy.service;

import java.util.List;
import java.util.Map;

import com.t1works.groupware.hsy.model.ClientHsyVO;
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

	// 진행중 업무에서 진행완료 업무로 업데이트
	int goWorkDone(String fk_pNo);

	// 기간별로 완료한 업무 총건수 나눠서 표시
	Map<String, String> selectCntTodoByPeriod(String employeeid);
	
	// pNo를 이용하여 필요한 고객정보 가져오기 (페이징처리)
	List<ClientHsyVO> selectClientInfoByPno(Map<String, String> paraMap);

	// 특정업무에 관한 고객의 totalPage 수 알아오기
	int getclientLisTotalPage(Map<String, String> paraMap);

	// 선택 날짜로 부터 6개월 이전까지의 날짜리스트 만들기
	Map<String, String> getDateBeforeSix(String string);

	// 해당년월의 처리업무에 해당하는 fk_pno 가져오기
	List<String> getFk_pnoListByDate(Map<String, String> paraMap);

	// 해당년월의 처리 업무 건 수 와 고객 수 가져오기 
	Map<String, String> getPerfAndClientCnt(Map<String, String> paraMap2);

	// 특정 년월에 끝난 업무 정부 가져오기 => 업무명, 시작일, 종료일, 담당 고객 수 (종료일 오름차순)
	List<TodoHsyVO> getPerfClientInfoForModal(Map<String, String> paraMap);

	
}
