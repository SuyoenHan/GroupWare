package com.t1works.groupware.hsy.model;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public class ClientHsyDAO implements InterClientHsyDAO {
	
	@Autowired 
	private SqlSessionTemplate sqlsession2;

	// 특정 상품에 예약정보 insert 하기
	@Override
	public int insertClientAjax(ClientHsyVO cvo) {
		int m= sqlsession2.insert("clientHsy.insertClientAjax", cvo);
		return m;
		
		/*
	 		m==1 예약성공
	 		예약실패 시 (이미 같은 사람이름으로 해당 상품 예약한 값이 존재=> SQLException발생)
	    */
		
	}// end of public int insertClientAjax(ClientHsyVO cvo)------

	// 해당제품에 특정고객의 예약이 존재하는지 확인
	@Override
	public int isExistClientAjax(ClientHsyVO cvo) {
		int n= sqlsession2.selectOne("clientHsy.isExistClientAjax", cvo);
		return n;
		
		/*
	 		n==1  해당제품에 특정고객의 예약이 존재하는 경우
	 		n==0 해당제품에 특정고객의 예약이 존재하지 않는 경우
	    */
		
	}// end of public int isExistClientAjax(ClientHsyVO cvo) {-------

	
	// 해당 고객명, 연락처에 일치하는 고객예약항목이 있는지 검사
	@Override
	public int checkClientMobile(Map<String,String> paraMap) {
		
		int n=sqlsession2.selectOne("clientHsy.checkClientMobile", paraMap);
		return n;
		// n== 예약목록에 해당 전화번호가 있는 행의 수
	
	} // end of public int checkClientMobile(String clientmobile) {-------

	
	// 특정고객의 예약정보를 가져오기
	@Override
	public List<ClientHsyVO> moreReserveListAjax(Map<String, String> paraMap) {

		List<ClientHsyVO> cvoList= sqlsession2.selectList("clientHsy.moreReserveListAjax", paraMap);
		return cvoList;
		 
	} // end of public List<ClientHsyVO> moreReserveListAjax(Map<String, String> paraMap) {------

	
	// 해당 연락처의 예약정보 총 개수 구하기
	@Override
	public int getTotalReserveCount(String clientmobile) {

		int totalCount= sqlsession2.selectOne("clientHsy.getTotalReserveCount", clientmobile);
		return totalCount;
	} // end of public int getTotalCount(String clientmobile) {------

	
	// 고객테이블에 해당예약건 delete
	@Override
	public int deleteClientAjax(ClientHsyVO cvo) {

		int m= sqlsession2.delete("clientHsy.deleteClientAjax", cvo);
		return m;	
		// m==1 예약취소 성공
	
	} // end of public int deleteClientAjax(ClientHsyVO cvo) {-------

	// 고객테이블에 해당예약건 update
	@Override
	public int updateClientAjax(ClientHsyVO cvo) {

		int m= sqlsession2.delete("clientHsy.updateClientAjax", cvo);
		return m;	
		// m==1 인원수 변경 성공
	}
	
	
	
	
	
}
