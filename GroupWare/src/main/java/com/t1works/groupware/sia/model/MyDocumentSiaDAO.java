package com.t1works.groupware.sia.model;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public class MyDocumentSiaDAO implements InterMyDocumentSiaDAO {

	@Resource
	private SqlSessionTemplate sqlsession4;
	
	// 내문서함 - 수신함 - 일반결재문서에 해당하는 문서 조회하기
	@Override
	public List<ApprovalSiaVO> getnorm_reclist(Map<String, String> paraMap) {
		List<ApprovalSiaVO> norm_reclist = sqlsession4.selectList("mydocument_sia.getnorm_reclist", paraMap);
		return norm_reclist;
	}
	
	// 검색에 해당하는 수신함 - 일반결재 글의 총 페이지수를 알아오기
	@Override
	public int getTotalPage(Map<String, String> paraMap) {
		int totalPage = sqlsession4.selectOne("mydocument_sia.getTotalPage", paraMap);
		return totalPage;
	}
	
	// 내문서함 - 수신함 - 일반결재문서 문서 한 개 상세보기
	@Override
	public ApprovalSiaVO myDocuNorm_detail(Map<String, String> paraMap) {
		ApprovalSiaVO avo = sqlsession4.selectOne("mydocument_sia.myDocuNorm_detail", paraMap);
		return avo;
	}
	
	//////////////////////////////////////////////////////////////////////

	// 내문서함 - 수신함 - 지출결재문서에 해당하는 문서 조회하기
	@Override
	public List<ApprovalSiaVO> getSpend_reclist(Map<String, String> paraMap) {
		List<ApprovalSiaVO> spend_reclist = sqlsession4.selectList("mydocument_sia.getSpend_reclist", paraMap);
		return spend_reclist;
	}

	// 검색에 해당하는 수신함 - 지출결재 글의 총 페이지수를 알아오기
	@Override
	public int getSpendTotalPage(Map<String, String> paraMap) {
		int totalPage = sqlsession4.selectOne("mydocument_sia.getSpendTotalPage", paraMap);
		return totalPage;
	}	
	
	// 내문서함 - 수신함 - 지출 결재 문서 한 개 상세보기
	@Override
	public ApprovalSiaVO myDocuSpend_detail(Map<String, String> paraMap) {
		ApprovalSiaVO avo = sqlsession4.selectOne("mydocument_sia.myDocuSpend_detail", paraMap);
		return avo;
	}
	
	/////////////////////////////////////////////////////////////////////

	// 내문서함 - 수신함 - 근태결재문서에 해당하는 문서 조회하기
	@Override
	public List<ApprovalSiaVO> getVacation_reclist(Map<String, String> paraMap) {
		List<ApprovalSiaVO> vacation_reclist = sqlsession4.selectList("mydocument_sia.getVacation_reclist", paraMap);
		return vacation_reclist;
	}

	// 검색에 해당하는 수신함 - 근태결재 글의 총 페이지수를 알아오기
	@Override
	public int getVacationTotalPage(Map<String, String> paraMap) {
		int totalPage = sqlsession4.selectOne("mydocument_sia.getVacationTotalPage", paraMap);
		return totalPage;
	}

	// 내문서함 - 수신함 - 근태결재문서 한 개 상세보기
	@Override
	public ApprovalSiaVO myDocuVacation_detail(Map<String, String> paraMap) {
		ApprovalSiaVO avo = sqlsession4.selectOne("mydocument_sia.myDocuVacation_detail", paraMap);
		return avo;
	}

	/////////////////////////////////////////////////////////////////////

	// 내문서함 - 수신함 결재의견 작성하기
	@Override
	public int addOpinion(ApprovalSiaVO avo) {
		int n = sqlsession4.insert("mydocument_sia.addOpinion", avo);
		return n;
	}

	// 내문서함 - 수신함 결재의견 조회하기
	@Override
	public List<ApprovalSiaVO> getOpinionList(String parentAno) {
		List<ApprovalSiaVO> avo = sqlsession4.selectList("mydocument_sia.getOpinionList", parentAno);
		return avo;
	}

	// 내문서함 - 수신함 - 결재버튼 클릭
	@Override
	public int approval(Map<String, String> paraMap) {
		int n = sqlsession4.update("mydocument_sia.approval", paraMap);
		return n;
	}

	// 내문서함 - 수신함 - 반려버튼 클릭
	@Override
	public int reject(String ano) {
		int n = sqlsession4.update("mydocument_sia.reject", ano);
		return n;
	}
	
	/////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////	
	
	// 내문서함 - 발신함 - 일반결재문서에 해당하는 문서 조회하기
	@Override
	public List<ApprovalSiaVO> getnorm_sendlist(Map<String, String> paraMap) {
		List<ApprovalSiaVO> norm_sendlist = sqlsession4.selectList("mydocument_sia.getnorm_sendlist", paraMap);
		return norm_sendlist;
	}
	
	// 검색에 해당하는 발신함 - 일반결재 글의 총 페이지수를 알아오기
	@Override
	public int getNormSendTotalPage(Map<String, String> paraMap) {
		int totalPage = sqlsession4.selectOne("mydocument_sia.getNormSendTotalPage", paraMap);
		return totalPage;
	}
	
	// 내문서함 - 발신함 - 일반결재문서 문서 한 개 상세보기
	@Override
	public ApprovalSiaVO myDocuNorm_send_detail(Map<String, String> paraMap) {
		ApprovalSiaVO avo = sqlsession4.selectOne("mydocument_sia.myDocuNorm_send_detail", paraMap);
		return avo;
	}
	
	//////////////////////////////////////////////////////////////////////

	// 내문서함 - 발신함 - 지출결재문서에 해당하는 문서 조회하기
	@Override
	public List<ApprovalSiaVO> getSpend_sendlist(Map<String, String> paraMap) {
		List<ApprovalSiaVO> spend_sendlist = sqlsession4.selectList("mydocument_sia.getSpend_sendlist", paraMap);
		return spend_sendlist;
	}

	// 검색에 해당하는 발신함 - 지출결재 글의 총 페이지수를 알아오기
	@Override
	public int getSpendSendTotalPage(Map<String, String> paraMap) {
		int totalPage = sqlsession4.selectOne("mydocument_sia.getSpendSendTotalPage", paraMap);
		return totalPage;
	}	
	
	// 내문서함 - 발신함 - 지출 결재 문서 한 개 상세보기
	@Override
	public ApprovalSiaVO myDocuSpend_send_detail(Map<String, String> paraMap) {
		ApprovalSiaVO avo = sqlsession4.selectOne("mydocument_sia.myDocuSpend_send_detail", paraMap);
		return avo;
	}
	
	/////////////////////////////////////////////////////////////////////

	// 내문서함 - 발신함 - 근태결재문서에 해당하는 문서 조회하기
	@Override
	public List<ApprovalSiaVO> getVacation_sendlist(Map<String, String> paraMap) {
		List<ApprovalSiaVO> vacation_sendlist = sqlsession4.selectList("mydocument_sia.getVacation_sendlist", paraMap);
		return vacation_sendlist;
	}

	// 검색에 해당하는 발신함 - 근태결재 글의 총 페이지수를 알아오기
	@Override
	public int getVacationSendTotalPage(Map<String, String> paraMap) {
		int totalPage = sqlsession4.selectOne("mydocument_sia.getVacationSendTotalPage", paraMap);
		return totalPage;
	}

	// 내문서함 - 발신함 - 근태결재문서 한 개 상세보기
	@Override
	public ApprovalSiaVO myDocuVacation_send_detail(Map<String, String> paraMap) {
		ApprovalSiaVO avo = sqlsession4.selectOne("mydocument_sia.myDocuVacation_send_detail", paraMap);
		return avo;
	}
	
	/////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	// 내문서함 - 결재완료 - 근태결재문서에 해당하는 문서 조회하기
	@Override
	public List<ApprovalSiaVO> getVacation_completelist(Map<String, String> paraMap) {
		List<ApprovalSiaVO> vacation_completelist = sqlsession4.selectList("mydocument_sia.getVacation_completelist", paraMap);
		return vacation_completelist;
	}

	// 검색에 해당하는 결재완료 - 근태결재 글의 총 페이지수를 알아오기
	@Override
	public int getVacationCompleteTotalPage(Map<String, String> paraMap) {
		int totalPage = sqlsession4.selectOne("mydocument_sia.getVacationCompleteTotalPage", paraMap);
		return totalPage;
	}
	

}
