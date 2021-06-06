package com.t1works.groupware.hsy.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.t1works.groupware.hsy.model.ClientHsyVO;
import com.t1works.groupware.hsy.model.InterClientHsyDAO;
import com.t1works.groupware.hsy.model.InterTodoHsyDAO;
import com.t1works.groupware.hsy.model.TodoHsyVO;

@Component
@Service
public class TodoHsyService implements InterTodoHsyService {

	@Autowired 
	private InterTodoHsyDAO tdao;
	
	@Autowired 
	private InterClientHsyDAO cdao;
	
	// 특정유저의 특정업무상태에 해당하는 총 페이지 수 알아오기
	@Override
	public int selectTotalPage(Map<String, String> paraMap) {
		
		int n= tdao.selectTotalPage(paraMap);
		return n;
		
	} // end of public int selectTotalPage(Map<String, String> paraMap) {
	
	
	// 업무관리페이지에서 보여줄 정보 가져오기 (트랜잭션처리)
	@Override
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor= {Throwable.class})
	public List<TodoHsyVO> employeeTodoList(Map<String, String> paraMap) throws Throwable{

		// 1) 지연상태인 모든 업무들 지연일자 update 해주기
		tdao.updateDelayDay();

		// 2) 페이징처리된 업무정보 가져오기
		int currentShowPageNo= Integer.parseInt(paraMap.get("currentShowPageNo"));
		int sizePerPage= Integer.parseInt(paraMap.get("sizePerPage"));
		
		String start=String.valueOf((currentShowPageNo * sizePerPage) - (sizePerPage - 1));
		String end=String.valueOf((currentShowPageNo * sizePerPage));
		paraMap.put("start", start);
		paraMap.put("end", end);
		
		List<TodoHsyVO> tvoList= tdao.employeeTodoList(paraMap);
		
		for(TodoHsyVO tvo:tvoList) {
			
			// 3) 시작일과 완료일이 null인 경우 화면에 '-'로 표시해 주기 위해 데이터 가공
			if(tvo.getStartDate()==null) tvo.setStartDate("-");
			if(tvo.getEndDate()==null) tvo.setEndDate("-");
			
			// 4) 현재예약인원과 최대예약인원  데이터 형태 바꿔주기
			if(tvo.getNowNo().length()==1) tvo.setNowNo("0"+tvo.getNowNo());
			if(tvo.getMaxNo().length()==1) tvo.setMaxNo("0"+tvo.getMaxNo());
			
			// 5) 최소예약인원 또는 최대예약인원 충족여부에 따라 색깔 다르게 주기 위해 데이터 가공
			if(Integer.parseInt(tvo.getMiniNo()) <= Integer.parseInt(tvo.getNowNo()) 
			   && Integer.parseInt(tvo.getNowNo()) < Integer.parseInt(tvo.getMaxNo())  ) {
				tvo.setFullState("1");  // 최소예약인원 충족
			}
			else if(Integer.parseInt(tvo.getMiniNo())>Integer.parseInt(tvo.getNowNo())) {
				tvo.setFullState("0"); // 최소예약인원 충족안함
			}
			else {
				tvo.setFullState("2"); // 최대예약인원 충족
			}
			
		}// end of for(TodoHsyVO tvo:tvoList) {------------
		
		return tvoList;
	} // end of public List<TodoHsyVO> employeeTodoList(Map<String, String> paraMap) {---


	// 신규등록업무, 진행중업무, 진행완료업무 총 건수 가져오기
	@Override
	public Map<String, String> selectCntTodoByrequiredState(String employeeid) {
		Map<String,String> cntMap= tdao.selectCntTodoByrequiredState(employeeid);
		return cntMap;
	} // end of public Map<String, String> selectCntTodoByrequiredState(String employeeid){--


	// 신규등록업무에서 진행중업무로 업데이트
	@Override
	public int goWorkStart(String fk_pNo) {
		int n=tdao.goWorkStart(fk_pNo);
		return n;
	} // end of public int goWorkStart(String fk_pNo) {-------


	// 진행중인 업무 상태변경(진행중 또는 보류)에 따라 update하기
	@Override
	public int updateIngDetail(TodoHsyVO tvo) {
		int n=tdao.updateIngDetail(tvo);
		return n;
	} // end of public int updateIngDetail(TodoHsyVO tvo) {-----


	// 진행중 업무에서 진행완료 업무로 업데이트하는 ajax 매핑 url
	@Override
	public int goWorkDone(String fk_pNo) {
		int n=tdao.goWorkDone(fk_pNo);
		return n;
	} // end of public int goWorkDone(String fk_pNo) {----


	// 기간별로 완료한 업무 총건수 나눠서 표시
	@Override
	public Map<String, String> selectCntTodoByPeriod(String employeeid) {

		Map<String,String> donePeriodMap= tdao.selectCntTodoByPeriod(employeeid);
		return donePeriodMap;
	} // end of public Map<String, String> selectCntTodoByPeriod(String employeeid) {---


	 // pNo를 이용하여 필요한 고객정보 가져오기 (페이징처리)
	@Override
	public List<ClientHsyVO> selectClientInfoByPno(Map<String, String> paraMap) {
		List<ClientHsyVO> cvoList=cdao.selectClientInfoByPno(paraMap);
		return cvoList;
	} // end of public List<ClientHsyVO> selectClientInfoByPno(String pNo) {------


	// 특정업무에 관한 고객의 totalPage 수 알아오기
	@Override
	public int getclientLisTotalPage(Map<String, String> paraMap) {

		int totalPage= cdao.getclientLisTotalPage(paraMap);
		return totalPage;
		
	} // end of public int getclientLisTotalPage(Map<String, String> paraMap) {-----

	
	// 선택 날짜로 부터 6개월 이전까지의 날짜리스트 만들기
	@Override
	public Map<String, String> getDateBeforeSix(String selectedDate) {
		Map<String,String> dateMap= tdao.getDateBeforeSix(selectedDate);
		return dateMap;
	} // end of public Map<String, String> getDateBeforeSix(String selectedDate) {----


	// 해당년월의 처리업무에 해당하는 fk_pno 가져오기
	@Override
	public List<String> getFk_pnoListByDate(Map<String, String> paraMap) {
		List<String> fk_pnoList= tdao.getFk_pnoListByDate(paraMap);
		return fk_pnoList;
	} // end of public List<String> getFk_pnoListByDate(Map<String, String> paraMap) {---


	// 해당년월의 처리 업무 건 수 와 고객 수 가져오기 
	@Override
	public Map<String, String> getPerfAndClientCnt(Map<String, String> paraMap2) {
		Map<String,String> EachPerfAndClientCnt= tdao.getPerfAndClientCnt(paraMap2);
		return EachPerfAndClientCnt;
	} // end of public Map<String, String> getPerfAndClientCnt(Map<String, String> paraMap2) {---


	// 특정 년월에 끝난 업무 정부 가져오기 => 업무명, 시작일, 종료일, 담당 고객 수 (종료일 오름차순)
	@Override
	public List<TodoHsyVO> getPerfClientInfoForModal(Map<String, String> paraMap) {
		List<TodoHsyVO> modalList= tdao.getPerfClientInfoForModal(paraMap);
		return modalList;
	} // end of public List<TodoHsyVO> getPerfClientInfoForModal(Map<String, String> paraMap) {---
 


}
