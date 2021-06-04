package com.t1works.groupware.sia.model;

import java.util.List;
import java.util.Map;

public interface InterMyDocumentSiaDAO {

	// 내문서함 - 수신함 - 일반결재문서에 해당하는 문서 조회하기
	List<ApprovalSiaVO> getnorm_reclist(Map<String, String> paraMap);

	// 검색에 해당하는 수신함 - 일반결재 글의 총 페이지수를 알아오기
	int getTotalPage(Map<String, String> paraMap);

	// 내문서함 - 수신함 - 일반결재문서 문서 한 개 상세보기
	ApprovalSiaVO myDocuNorm_detail(Map<String, String> paraMap);
	
	//////////////////////////////////////////////////////////////////////

	// 내문서함 - 수신함 - 지출결재문서에 해당하는 문서 조회하기
	List<ApprovalSiaVO> getSpend_reclist(Map<String, String> paraMap);

	// 검색에 해당하는 수신함 - 지출결재 글의 총 페이지수를 알아오기
	int getSpendTotalPage(Map<String, String> paraMap);

	// 내문서함 - 수신함 - 지출 결재 문서 한 개 상세보기
	ApprovalSiaVO myDocuSpend_detail(Map<String, String> paraMap);
	
	//////////////////////////////////////////////////////////////////////
	
	// 내문서함 - 수신함 - 근태결재문서에 해당하는 문서 조회하기
	List<ApprovalSiaVO> getVacation_reclist(Map<String, String> paraMap);

	// 검색에 해당하는 수신함 - 근태결재 글의 총 페이지수를 알아오기
	int getVacationTotalPage(Map<String, String> paraMap);

	// 내문서함 - 수신함 - 근태결재문서 한 개 상세보기
	ApprovalSiaVO myDocuVacation_detail(Map<String, String> paraMap);
	
	//////////////////////////////////////////////////////////////////////	

	// 내문서함 - 수신함 - 결재의견 작성하기
	int addOpinion(ApprovalSiaVO avo);

	// 내문서함 - 수신함 - 결재의견 조회하기
	List<ApprovalSiaVO> getOpinionList(String parentAno);

	// 내문서함 - 수신함 - 결재버튼 클릭
	int approval(Map<String, String> paraMap);

	// 내문서함 - 수신함 - 반려버튼 클릭
	int reject(String ano);
	
	//////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////
	
	// 내문서함 - 발신함 - 일반결재문서에 해당하는 문서 조회하기
	List<ApprovalSiaVO> getnorm_sendlist(Map<String, String> paraMap);

	// 검색에 해당하는 발신함 - 일반결재 글의 총 페이지수를 알아오기
	int getNormSendTotalPage(Map<String, String> paraMap);

	// 내문서함 - 발신함 - 일반결재문서 문서 한 개 상세보기
	ApprovalSiaVO myDocuNorm_send_detail(Map<String, String> paraMap);
	
	//////////////////////////////////////////////////////////////////////

	// 내문서함 - 발신함 - 지출결재문서에 해당하는 문서 조회하기
	List<ApprovalSiaVO> getSpend_sendlist(Map<String, String> paraMap);

	// 검색에 해당하는 발신함 - 지출결재 글의 총 페이지수를 알아오기
	int getSpendSendTotalPage(Map<String, String> paraMap);

	// 내문서함 - 발신함 - 지출 결재 문서 한 개 상세보기
	ApprovalSiaVO myDocuSpend_send_detail(Map<String, String> paraMap);
	
	//////////////////////////////////////////////////////////////////////
	
	// 내문서함 - 발신함 - 근태결재문서에 해당하는 문서 조회하기
	List<ApprovalSiaVO> getVacation_sendlist(Map<String, String> paraMap);

	// 검색에 해당하는 발신함 - 근태결재 글의 총 페이지수를 알아오기
	int getVacationSendTotalPage(Map<String, String> paraMap);

	// 내문서함 - 발신함 - 근태결재문서 한 개 상세보기
	ApprovalSiaVO myDocuVacation_send_detail(Map<String, String> paraMap);

	//////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////
	
	
	
	
	
	
	
	
	
	

	// 내문서함 - 결재완료 - 근태결재문서에 해당하는 문서 조회하기
	List<ApprovalSiaVO> getVacation_completelist(Map<String, String> paraMap);

	// 검색에 해당하는 결재완료 - 근태결재 글의 총 페이지수를 알아오기
	int getVacationCompleteTotalPage(Map<String, String> paraMap);
	

}
