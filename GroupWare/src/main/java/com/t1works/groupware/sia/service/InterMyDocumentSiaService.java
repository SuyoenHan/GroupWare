package com.t1works.groupware.sia.service;

import java.util.List;
import java.util.Map;

import com.t1works.groupware.sia.model.ApprovalSiaVO;

public interface InterMyDocumentSiaService {

	// 내문서함 - 수신함 - 일반결재문서에 해당하는 문서 조회하기
	List<ApprovalSiaVO> getnorm_reclist(Map<String, String> paraMap);

	// 검색에 해당하는 글의 총 페이지수를 알아오기
	int getTotalPage(Map<String, String> paraMap);

	// 내문서함 - 일반결재문서 한 개 상세보기
	ApprovalSiaVO myDocuNorm_detail(Map<String, String> paraMap);

	// 내문서함 - 수신함 - 지출결재문서에 해당하는 문서 조회하기
	List<ApprovalSiaVO> getSpend_reclist(Map<String, String> paraMap);

	// 검색에 해당하는 수신함-일반결재 글의 총 페이지수를 알아오기
	int getSpendTotalPage(Map<String, String> paraMap);

	// 내문서함 - 수신함 - 지출결재문서 한 개 상세보기
	ApprovalSiaVO myDocuSpend_detail(Map<String, String> paraMap);
	
	// 내문서함 - 수신함 - 근태결재문서에 해당하는 문서 조회하기
	List<ApprovalSiaVO> getVacation_reclist(Map<String, String> paraMap);

	// 검색에 해당하는 수신함-근태결재 글의 총 페이지수를 알아오기
	int getVacationTotalPage(Map<String, String> paraMap);

	// 내문서함 - 수신함 - 근태결재문서 한 개 상세보기
	ApprovalSiaVO myDocuVaction_detail(Map<String, String> paraMap);

	
	

	

}
