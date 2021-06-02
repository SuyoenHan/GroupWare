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

	
	// 검색에 해당하는 글의 총 페이지수를 알아오기
	@Override
	public int getTotalPage(Map<String, String> paraMap) {
		int totalPage = sqlsession4.selectOne("mydocument_sia.getTotalPage", paraMap);
		return totalPage;
	}

	
	// 내문서함 - 문서 한 개 상세보기
	@Override
	public ApprovalSiaVO myDocuNorm_detail(Map<String, String> paraMap) {
		ApprovalSiaVO avo = sqlsession4.selectOne("mydocument_sia.myDocuNorm_detail", paraMap);
		return avo;
	}


	// 내문서함 - 수신함 - 지출결재문서에 해당하는 문서 조회하기
	@Override
	public List<ApprovalSiaVO> getSpend_reclist(Map<String, String> paraMap) {
		List<ApprovalSiaVO> spend_reclist = sqlsession4.selectList("mydocument_sia.getSpend_reclist", paraMap);
		return spend_reclist;
	}


	// 검색에 해당하는 수신함-지출결재 글의 총 페이지수를 알아오기
	@Override
	public int getSpendTotalPage(Map<String, String> paraMap) {
		int totalPage = sqlsession4.selectOne("mydocument_sia.getSpendTotalPage", paraMap);
		return totalPage;
	}
	
	
	// 내문서함 - 수신함 - 지출결재문서 한 개 상세보기
	@Override
	public ApprovalSiaVO myDocuSpend_detail(Map<String, String> paraMap) {
		ApprovalSiaVO avo = sqlsession4.selectOne("mydocument_sia.myDocuSpend_detail", paraMap);
		return avo;
	}


	// 내문서함 - 수신함 - 근태결재문서에 해당하는 문서 조회하기
	@Override
	public List<ApprovalSiaVO> getVacation_reclist(Map<String, String> paraMap) {
		List<ApprovalSiaVO> vacation_reclist = sqlsession4.selectList("mydocument_sia.getVacation_reclist", paraMap);
		return vacation_reclist;
	}


	// 검색에 해당하는 수신함-근태결재 글의 총 페이지수를 알아오기
	@Override
	public int getVacationTotalPage(Map<String, String> paraMap) {
		int totalPage = sqlsession4.selectOne("mydocument_sia.getVacationTotalPage", paraMap);
		return totalPage;
	}


	// 내문서함 - 수신함 - 근태결재문서 한 개 상세보기
	@Override
	public ApprovalSiaVO myDocuVaction_detail(Map<String, String> paraMap) {
		ApprovalSiaVO avo = sqlsession4.selectOne("mydocument_sia.myDocuVaction_detail", paraMap);
		return avo;
	}


	// 내문서함 - 수신함 - 근태결재 결재의견 작성하기
	@Override
	public int addOpinion(ApprovalSiaVO avo) {
		int n = sqlsession4.insert("mydocument_sia.addOpinion", avo);
		return n;
	}


	// 내문서함 - 수신함 - 근태결재 결재의견 조회하기
	@Override
	public List<ApprovalSiaVO> getOpinionList(String parentAno) {
		List<ApprovalSiaVO> avo = sqlsession4.selectList("mydocument_sia.getOpinionList", parentAno);
		return avo;
	}

	

}
