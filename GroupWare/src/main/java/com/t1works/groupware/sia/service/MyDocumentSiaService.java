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

}
