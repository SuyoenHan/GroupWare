package com.t1works.groupware.hsy.model;

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
	
	
	
	
	
}
