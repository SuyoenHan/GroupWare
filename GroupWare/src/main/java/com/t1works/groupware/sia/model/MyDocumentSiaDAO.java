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
		List<ApprovalSiaVO> reclist = sqlsession4.selectList("mydocument_sia.getnorm_reclist", paraMap);
		return reclist;
	}

	
	// 검색에 해당하는 글의 총 페이지수를 알아오기
	@Override
	public int getTotalPage(Map<String, String> paraMap) {
		int totalPage = sqlsession4.selectOne("mydocument_sia.getTotalPage", paraMap);
		return totalPage;
	}

}
