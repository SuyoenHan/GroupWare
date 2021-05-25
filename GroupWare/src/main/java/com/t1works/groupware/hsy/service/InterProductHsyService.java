package com.t1works.groupware.hsy.service;

import java.util.List;

import com.t1works.groupware.hsy.model.ClientHsyVO;
import com.t1works.groupware.hsy.model.ProductHsyVO;

public interface InterProductHsyService {

	//  여행사 홈페이지에서 보여줄 상품정보 가져오기
	List<ProductHsyVO> selectProductInfoForHome();

	// pNo 존재하는지 확인하기
	int isExistPno(String pNo);

	// 상세페이지에 필요한 상품정보 알아오기
	ProductHsyVO selectOneProductInfo(String pNo);

	// 해당 상품에 예약이 가능한 인원수 인지 검사하기 
	int checkClientAjax(ClientHsyVO cvo);

	// 해당 상품에 예약정보를 insert하고 상품테이블이 update하기 (transaction처리)
	int insertUpdateClientAjax(ClientHsyVO cvo) throws Throwable;

	// 특정제품에 현재 예약가능한 인원수
	int getAvailableCount(String fk_pNo);

	// 해당제품에 특정고객의 예약이 존재하는지 확인
	int isExistClientAjax(ClientHsyVO cvo);

}
