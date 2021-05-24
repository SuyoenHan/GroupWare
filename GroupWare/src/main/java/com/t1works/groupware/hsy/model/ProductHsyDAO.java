package com.t1works.groupware.hsy.model;

import java.util.*;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public class ProductHsyDAO implements InterProductHsyDAO {
	
	@Autowired 
	private SqlSessionTemplate sqlsession2;

	// 여행사 홈페이지에서 보여줄 상품정보 가져오기
	@Override
	public List<ProductHsyVO> selectProductInfoForHome() {

		List<ProductHsyVO> pvoList= sqlsession2.selectList("productHsy.selectProductInfoForHome");
		return pvoList;
	}

	
	// pNo 존재하는지 확인하기
	@Override
	public int isExistPno(String pNo) {
		
		int n= sqlsession2.selectOne("productHsy.isExistPno", pNo);
		return n; 
		
		/*
		 	존재하는 경우 n==1
		 	존재하지 않는 경우 n==0;
		*/
		
	}// end of public int isExistPno(String pNo) {---------------

	
	// 상세페이지에 필요한 상품정보 알아오기
	@Override
	public ProductHsyVO selectOneProductInfo(String pNo) {

		ProductHsyVO pvo= sqlsession2.selectOne("productHsy.selectOneProductInfo", pNo);
		return pvo;
	}

	// 해당 상품에 예약이 가능한 인원수 인지 검사하기 
	@Override
	public int checkClientAjax(ClientHsyVO cvo) {
		
		int n= sqlsession2.selectOne("productHsy.checkClientAjax",cvo);
		return n;
		
		
	 	// n==1  예약가능
		
	
	} // end of public int checkClientAjax(ClientHsyVO cvo) {-----------

	
	// 특정상품의 현재예약인원 수 udpate하기
	@Override
	public int updateNowNo(ClientHsyVO cvo) {
		int n= sqlsession2.update("productHsy.updateNowNo",cvo);
		return n;
		
		// n==1  업데이트성공 
		
	} // end of public int updateNowNo(ClientHsyVO cvo) {------


	// 특정제품에 현재 예약가능한 인원수 알아오기
	@Override
	public int getAvailableCount(String fk_pNo) {
		int count= sqlsession2.selectOne("productHsy.getAvailableCount", fk_pNo);
		return count;
	}
	
}
