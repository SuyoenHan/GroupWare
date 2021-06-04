package com.t1works.groupware.sia.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.t1works.groupware.sia.model.ApprovalSiaVO;
import com.t1works.groupware.sia.model.InterMyDocumentSiaDAO;

@Component
@Service
public class MyDocumentSiaService implements InterMyDocumentSiaService {
	
	@Autowired
	private InterMyDocumentSiaDAO dao;
	
	// 내문서함 - 수신함 - 일반결재문서에 해당하는 문서 조회하기
	@Override
	public List<ApprovalSiaVO> getnorm_reclist(Map<String, String> paraMap) {
		List<ApprovalSiaVO> norm_reclist = dao.getnorm_reclist(paraMap);
		return norm_reclist;
	}
	
	// 검색에 해당하는 수신함 - 일반결재 글의 총 페이지수를 알아오기
	@Override
	public int getTotalPage(Map<String, String> paraMap) {
		int totalPage = dao.getTotalPage(paraMap);
		return totalPage;
	}

	// 내문서함 - 수신함 - 일반결재문서 문서 한 개 상세보기
	@Override
	public ApprovalSiaVO myDocuNorm_detail(Map<String, String> paraMap) {
		ApprovalSiaVO avo = dao.myDocuNorm_detail(paraMap);
		return avo;
	}

	//////////////////////////////////////////////////////////////////////

	// 내문서함 - 수신함 - 지출결재문서에 해당하는 문서 조회하기
	@Override
	public List<ApprovalSiaVO> getSpend_reclist(Map<String, String> paraMap) {
		List<ApprovalSiaVO> spend_reclist = dao.getSpend_reclist(paraMap);
		return spend_reclist;
	}


	// 검색에 해당하는 수신함 - 지출결재 글의 총 페이지수를 알아오기
	@Override
	public int getSpendTotalPage(Map<String, String> paraMap) {
		int totalPage = dao.getSpendTotalPage(paraMap);
		return totalPage;
	}
		
	// 내문서함 - 수신함 - 지출 결재 문서 한 개 상세보기
	@Override
	public ApprovalSiaVO myDocuSpend_detail(Map<String, String> paraMap) {
		ApprovalSiaVO avo = dao.myDocuSpend_detail(paraMap);
		return avo;		
	}

	/////////////////////////////////////////////////////////////////////
	
	// 내문서함 - 수신함 - 근태결재문서에 해당하는 문서 조회하기
	@Override
	public List<ApprovalSiaVO> getVacation_reclist(Map<String, String> paraMap) {
		List<ApprovalSiaVO> vacation_reclist = dao.getVacation_reclist(paraMap);
		return vacation_reclist;
	}

	// 검색에 해당하는 수신함 - 근태결재 글의 총 페이지수를 알아오기
	@Override
	public int getVacationTotalPage(Map<String, String> paraMap) {
		int totalPage = dao.getVacationTotalPage(paraMap);
		return totalPage;
	}

	// 내문서함 - 수신함 - 근태결재문서 한 개 상세보기
	@Override
	public ApprovalSiaVO myDocuVacation_detail(Map<String, String> paraMap) {
		ApprovalSiaVO avo = dao.myDocuVacation_detail(paraMap);
		return avo;
	}
	
	/////////////////////////////////////////////////////////////////////	

	// 내문서함 - 수신함 결재의견 작성하기
	@Override
	public int addOpinion(ApprovalSiaVO avo) {
		int n = dao.addOpinion(avo);
		return n;
	}

	// 내문서함 - 수신함 결재의견 조회하기
	@Override
	public List<ApprovalSiaVO> getOpinionList(String parentAno) {
		List<ApprovalSiaVO> avo = dao.getOpinionList(parentAno);
		return avo;
	}
	
	// 내문서함 - 수신함 결재버튼 클릭
	@Override
	public int approval(Map<String, String> paraMap) {
		int n = dao.approval(paraMap);
		return n;
	}

	// 내문서함 - 수신함 반려버튼 클릭
	@Override
	public int reject(String ano) {
		int n = dao.reject(ano);
		return n;
	}

	////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////
	
	// 내문서함 - 발신함 - 일반결재문서에 해당하는 문서 조회하기
	@Override
	public List<ApprovalSiaVO> getnorm_sendlist(Map<String, String> paraMap) {
		List<ApprovalSiaVO> norm_sendlist = dao.getnorm_sendlist(paraMap);
		return norm_sendlist;
	}
	
	// 검색에 해당하는 발신함 - 일반결재 글의 총 페이지수를 알아오기
	@Override
	public int getNormSendTotalPage(Map<String, String> paraMap) {
		int totalPage = dao.getNormSendTotalPage(paraMap);
		return totalPage;
	}

	// 내문서함 - 발신함 - 일반결재문서 문서 한 개 상세보기
	@Override
	public ApprovalSiaVO myDocuNorm_send_detail(Map<String, String> paraMap) {
		ApprovalSiaVO avo = dao.myDocuNorm_send_detail(paraMap);
		return avo;
	}

	//////////////////////////////////////////////////////////////////////

	// 내문서함 - 발신함 - 지출결재문서에 해당하는 문서 조회하기
	@Override
	public List<ApprovalSiaVO> getSpend_sendlist(Map<String, String> paraMap) {
		List<ApprovalSiaVO> spend_sendlist = dao.getSpend_sendlist(paraMap);
		return spend_sendlist;
	}


	// 검색에 해당하는 발신함 - 지출결재 글의 총 페이지수를 알아오기
	@Override
	public int getSpendSendTotalPage(Map<String, String> paraMap) {
		int totalPage = dao.getSpendSendTotalPage(paraMap);
		return totalPage;
	}
		
	// 내문서함 - 발신함 - 지출 결재 문서 한 개 상세보기
	@Override
	public ApprovalSiaVO myDocuSpend_send_detail(Map<String, String> paraMap) {
		ApprovalSiaVO avo = dao.myDocuSpend_send_detail(paraMap);
		return avo;		
	}

	/////////////////////////////////////////////////////////////////////
	
	// 내문서함 - 발신함 - 근태결재문서에 해당하는 문서 조회하기
	@Override
	public List<ApprovalSiaVO> getVacation_sendlist(Map<String, String> paraMap) {
		List<ApprovalSiaVO> vacation_sendlist = dao.getVacation_sendlist(paraMap);
		return vacation_sendlist;
	}

	// 검색에 해당하는 발신함 - 근태결재 글의 총 페이지수를 알아오기
	@Override
	public int getVacationSendTotalPage(Map<String, String> paraMap) {
		int totalPage = dao.getVacationSendTotalPage(paraMap);
		return totalPage;
	}

	// 내문서함 - 발신함 - 근태결재문서 한 개 상세보기
	@Override
	public ApprovalSiaVO myDocuVacation_send_detail(Map<String, String> paraMap) {
		ApprovalSiaVO avo = dao.myDocuVacation_send_detail(paraMap);
		return avo;
	}
	
	////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	// 내문서함 - 결재완료 - 근태결재문서에 해당하는 문서 조회하기
	@Override
	public List<ApprovalSiaVO> getVacation_completelist(Map<String, String> paraMap) {
		List<ApprovalSiaVO> vacation_completelist = dao.getVacation_completelist(paraMap);
		return vacation_completelist;
	}

	// 검색에 해당하는 결재완료 - 근태결재 글의 총 페이지수를 알아오기
	@Override
	public int getVacationCompleteTotalPage(Map<String, String> paraMap) {
		int totalPage = dao.getVacationCompleteTotalPage(paraMap);
		return totalPage;
	}

}
