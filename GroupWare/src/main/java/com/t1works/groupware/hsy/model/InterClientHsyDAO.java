package com.t1works.groupware.hsy.model;

public interface InterClientHsyDAO {

	// 특정 상품에 예약정보 insert 하기
	int insertClientAjax(ClientHsyVO cvo);

	// 해당제품에 특정고객의 예약이 존재하는지 확인
	int isExistClientAjax(ClientHsyVO cvo);

}
