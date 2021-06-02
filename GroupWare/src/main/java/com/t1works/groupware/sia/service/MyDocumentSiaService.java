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

	
	// 검색에 해당하는 글의 총 페이지수를 알아오기
	@Override
	public int getTotalPage(Map<String, String> paraMap) {
		int totalPage = dao.getTotalPage(paraMap);
		return totalPage;
	}


	// 내문서함 - 수신함 - 일반 결재 문서 한 개 상세보기
	@Override
	public ApprovalSiaVO myDocuNorm_detail(Map<String, String> paraMap) {
		ApprovalSiaVO avo = dao.myDocuNorm_detail(paraMap);
		return avo;
	}


	// 내문서함 - 수신함 - 지출결재문서에 해당하는 문서 조회하기
	@Override
	public List<ApprovalSiaVO> getSpend_reclist(Map<String, String> paraMap) {
		List<ApprovalSiaVO> spend_reclist = dao.getSpend_reclist(paraMap);
		return spend_reclist;
	}


	// 검색에 해당하는 수신함-일반결재 글의 총 페이지수를 알아오기
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

	
	// 내문서함 - 수신함 - 근태결재문서에 해당하는 문서 조회하기
	@Override
	public List<ApprovalSiaVO> getVacation_reclist(Map<String, String> paraMap) {
		List<ApprovalSiaVO> vacation_reclist = dao.getVacation_reclist(paraMap);
		return vacation_reclist;
	}


	// 검색에 해당하는 수신함-근태결재 글의 총 페이지수를 알아오기
	@Override
	public int getVacationTotalPage(Map<String, String> paraMap) {
		int totalPage = dao.getVacationTotalPage(paraMap);
		return totalPage;
	}


	// 내문서함 - 수신함 - 근태결재문서 한 개 상세보기
	@Override
	public ApprovalSiaVO myDocuVaction_detail(Map<String, String> paraMap) {
		ApprovalSiaVO avo = dao.myDocuVaction_detail(paraMap);
		return avo;
	}
	

	// 내문서함 - 수신함 - 근태결재 결재의견 작성하기
	@Override
	public int addOpinion(ApprovalSiaVO avo) {
		int n = dao.addOpinion(avo);
		return n;
	}


	// 내문서함 - 수신함 - 근태결재 결재의견 조회하기
	@Override
	public List<ApprovalSiaVO> getOpinionList(String parentAno) {
		List<ApprovalSiaVO> avo = dao.getOpinionList(parentAno);
		return avo;
	}


	

}
