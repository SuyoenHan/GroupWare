package com.t1works.groupware.hsy.model;

import java.util.*;

public interface InterClientHsyDAO {

	// 특정 상품에 예약정보 insert 하기
	int insertClientAjax(ClientHsyVO cvo);

	// 해당제품에 특정고객의 예약이 존재하는지 확인
	int isExistClientAjax(ClientHsyVO cvo);

	// 해당 고객명, 연락처에 일치하는 고객예약항목이 있는지 검사
	int checkClientMobile(Map<String, String> paraMap);

	// 특정고객의 예약정보를 가져오기
	List<ClientHsyVO> moreReserveListAjax(Map<String, String> paraMap);

	// 해당 연락처의 예약정보 총 개수 구하기
	int getTotalReserveCount(String clientmobile);

	// 고객테이블에 해당예약건 delete
	int deleteClientAjax(ClientHsyVO cvo);

	// 고객테이블에 해당예약건 update
	int updateClientAjax(ClientHsyVO cvo);

	// pNo를 이용하여 필요한 고객정보 가져오기
	List<ClientHsyVO> selectClientInfoByPno(String pNo);

}
