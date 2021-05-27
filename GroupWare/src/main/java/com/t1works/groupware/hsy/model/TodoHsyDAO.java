package com.t1works.groupware.hsy.model;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public class TodoHsyDAO implements InterTodoHsyDAO {
	
	@Autowired 
	private SqlSessionTemplate sqlsession2;

	// 특정유저의 특정업무상태에 해당하는 총 페이지 수 알아오기
	@Override
	public int selectTotalPage(Map<String, String> paraMap) {
		
		int n=sqlsession2.selectOne("todoHsy.selectTotalPage", paraMap);
		return n;
		
	} // end of public int selectTotalPage(Map<String, String> paraMap) {------
	
	// 업무관리페이지에서 보여줄 정보 가져오기
	@Override
	public List<TodoHsyVO> employeeTodoList(Map<String, String> paraMap) {
		
		List<TodoHsyVO> tvoList= sqlsession2.selectList("todoHsy.employeeTodoList",paraMap);
		return tvoList;
		
	} // end of public List<TodoHsyVO> employeeTodoList(Map<String, String> paraMap) {----

	
	// 신규등록업무, 진행중업무, 진행완료업무 총 건수 가져오기
	@Override
	public Map<String, String> selectCntTodoByrequiredState(String employeeid) {

		Map<String,String> cntMap= sqlsession2.selectOne("todoHsy.selectCntTodoByrequiredState", employeeid);
		return cntMap;
	} // end of public Map<String, String> selectCntTodoByrequiredState(String employeeid) {---

	
	// 신규등록업무에서 진행중업무로 업데이트
	@Override
	public int goWorkStart(String fk_pNo) {

		int n= sqlsession2.update("todoHsy.goWorkStart", fk_pNo);	
		return n;
		
	} // end of public int goWorkStart(String fk_pNo) {-------

	
	// 지연상태인 모든 업무들 지연일자 update 해주기
	@Override
	public void updateDelayDay() {
		sqlsession2.update("todoHsy.updateDelayDay");
	}// end of public void updateDelayDay() {

	
	// 진행중인 업무 상태변경(진행중 또는 보류)에 따라 update하기
	@Override
	public int updateIngDetail(TodoHsyVO tvo) {
		
		int n=sqlsession2.update("todoHsy.updateIngDetail", tvo);
		return n;
	}// end of public int updateIngDetail(TodoHsyVO tvo) {-----

	
	// 진행중 업무에서 진행완료 업무로 업데이트하는 ajax 매핑 url
	@Override
	public int goWorkDone(String fk_pNo) {
		int n=sqlsession2.update("todoHsy.goWorkDone", fk_pNo);
		return n;
	} // end of public int goWorkDone(String fk_pNo) {----

	
	// 기간별로 완료한 업무 총건수 나눠서 표시
	@Override
	public Map<String, String> selectCntTodoByPeriod(String employeeid) {
		Map<String,String> donePeriodMap=sqlsession2.selectOne("todoHsy.selectCntTodoByPeriod",employeeid);
		return donePeriodMap;
	} // end of public Map<String, String> selectCntTodoByPeriod(String employeeid) {-----

	
	

	
}
