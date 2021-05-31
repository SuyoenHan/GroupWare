package com.t1works.groupware.hsy.service;

import java.util.List;
import java.util.Map;

import com.t1works.groupware.hsy.model.ClientHsyVO;
import com.t1works.groupware.hsy.model.ProductHsyVO;
import com.t1works.groupware.hsy.model.TwLocationHsyVO;

public interface InterProductHsyService {

	//  여행사 홈페이지에서 보여줄 상품정보 가져오기 => 페이징바 처리
	List<ProductHsyVO> selectProductInfoForHome(Map<String,String> paraMap);

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

	// 회사위치테이블에 있는 정보 가져오기  
	List<TwLocationHsyVO> twLocationAjax();

	// 해당 고객명, 연락처에 일치하는 고객예약항목이 있는지 검사
	int checkClientMobile(Map<String,String> paraMap);

	// 특정고객의 예약정보를 가져오기
	List<ClientHsyVO> moreReserveListAjax(Map<String, String> paraMap);

	// 해당 연락처의 예약정보 총 개수 구하기
	int getTotalReserveCount(String clientmobile);

	// 여행확정 상품인지 확인하기
	Map<String, String> checkProductStatus(String pNo);

	// 고객테이블에 delete하고 제품테이블에 update (transaction처리)
	int deleteUpdateClientAjax(ClientHsyVO cvo) throws Throwable;

	// 고객테이블에 update하고 제품테이블에 update (transaction처리)
	int changeCountAjax(ClientHsyVO cvo) throws Throwable;

	// 상품목록 총 페이지 수
	int selectProductTotalPage(Map<String, String> paraMap);

}
