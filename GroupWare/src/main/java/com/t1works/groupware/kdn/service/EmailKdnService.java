package com.t1works.groupware.kdn.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.t1works.groupware.kdn.model.EmailKdnVO;
import com.t1works.groupware.kdn.model.InterEmailKdnDAO;

@Component
@Service
public class EmailKdnService implements InterEmailKdnService {

	@Autowired
	private InterEmailKdnDAO dao;
	
	// 이메일주소 자동완성을 위한 주소록 가져오기
	@Override
	public List<String> getEmailList() {
		List<String> emailList = dao.getEmailList();
		return emailList;
	}

	// 파일 첨부가 없는 이메일 쓰기
	@Override
	public int send(EmailKdnVO evo) {
		if(evo.getParentSeq() == null || evo.getParentSeq().trim().isEmpty() ) {
			// 원글쓰기 이라면 groupno 컬럼의 값은 groupno 컬럼의 최대값(max)+1 로 해야 한다. 
			int groupno = dao.getGroupnoMax() + 1;
			evo.setGroupno(String.valueOf(groupno));
		}
		int n = dao.send(evo);
		
		return n;
	}

	// 파일 첨부가 있는 이메일 쓰기
	@Override
	public int sendWithFile(EmailKdnVO evo) {
		// == 원글쓰기인지 , 답변글쓰기인지 구분하기 == 
		if(evo.getParentSeq() == null || evo.getParentSeq().trim().isEmpty() ) {
			// 원글쓰기 이라면 groupno 컬럼의 값은 groupno 컬럼의 최대값(max)+1 로 해야 한다. 
			int groupno = dao.getGroupnoMax() + 1;
			evo.setGroupno(String.valueOf(groupno));
		}
		int n = dao.sendWithFile(evo);	//첨부파일이 있는 경우
		return n;
	}

	// 총 이메일 건수 구해오기
	@Override
	public int getTotalCount(Map<String, String> paraMap) {
		int n = dao.getTotalCount(paraMap);
		return n;
	}

	// 페이징 처리한 이메일목록 가져오기(검색어 유무 상관없이 모두 다 포함한것)
	@Override
	public List<EmailKdnVO> emailListSearchWithPaging(Map<String, String> paraMap) {
		List<EmailKdnVO> emailList = dao.emailListSearchWithPaging(paraMap);
		return emailList;
	}

	// 이메일 열람하기
	@Override
	public EmailKdnVO getView(Map<String, String> paraMap) {
		EmailKdnVO evo = dao.getView(paraMap);
		return evo;
	}


}
