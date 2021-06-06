package com.t1works.groupware.kdn.model;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class EmailKdnDAO implements InterEmailKdnDAO {
	
	@Resource
	private SqlSessionTemplate sqlsession3; // 원격DB에 연결

	// 이메일주소 자동완성을 위한 주소록 가져오기
	@Override
	public List<String> getEmailList() {
		List<String> emailList = sqlsession3.selectList("email.getEmailList");
		return emailList;
	}

	// tbl_mailinbox 테이블에서 groupno 컬럼의 최대값 구하기
	@Override
	public int getGroupnoMax() {
		int max = sqlsession3.selectOne("email.getGroupnoMax");
	    return max;
	}

	// 파일첨부가 없는 메일쓰기
	@Override
	public int send(EmailKdnVO evo) {
		int n = sqlsession3.insert("email.send", evo);
		return n;
	}

	// 파일첨부가 있는 메일쓰기
	@Override
	public int sendWithFile(EmailKdnVO evo) {
		int n = sqlsession3.insert("email.sendWithFile", evo);
		return n;
	}
	
	// 총 이메일 건수 구해오기
	@Override
	public int getTotalCount(Map<String, String> paraMap) {
		int n =sqlsession3.selectOne("email.getTotalCount", paraMap);
		return n;
	}

	// 페이징 처리한 이메일목록 가져오기(검색어 유무 상관없이 모두 다 포함한것)
	@Override
	public List<EmailKdnVO> emailListSearchWithPaging(Map<String, String> paraMap) {
		List<EmailKdnVO> boardList = sqlsession3.selectList("email.emailListSearchWithPaging", paraMap);
		return boardList;
	}

	//이메일 열람하기
	@Override
	public EmailKdnVO getView(Map<String, String> paraMap) {
		EmailKdnVO evo = sqlsession3.selectOne("email.getView",paraMap);
		return evo;
	}

}
