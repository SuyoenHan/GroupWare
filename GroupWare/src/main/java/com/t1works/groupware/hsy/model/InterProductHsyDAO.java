package com.t1works.groupware.hsy.model;

import java.util.List;

public interface InterProductHsyDAO {

	// 여행사 홈페이지에서 보여줄 상품정보 가져오기
	List<ProductHsyVO> selectProductInfoForHome();

	// pNo 존재하는지 확인하기
	int isExistPno(String pNo);

	// 상세페이지에 필요한 상품정보 알아오기
	ProductHsyVO selectOneProductInfo(String pNo);

	// 해당 상품에 예약이 가능한 인원수 인지 검사하기 
	int checkClientAjax(ClientHsyVO cvo);

	// 특정상품의 현재예약인원 수 udpate하기
	int updateNowNo(ClientHsyVO cvo);

	// 특정제품에 현재 예약가능한 인원수 알아오기
	int getAvailableCount(String fk_pNo);

}
