package com.t1works.groupware.kdn.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.t1works.groupware.kdn.model.BoardVOKdn;
import com.t1works.groupware.kdn.model.InterBoardDAOKdn;

@Component
@Service
public class BoardServiceKdn implements InterBoardServiceKdn {

	@Autowired
	private InterBoardDAOKdn dao;
	
	// === 공지사항 === 
	
	// 공지사항 글쓰기
	@Override
	public int noticePostUpload(BoardVOKdn boardvo) {
		int n = dao.noticePostUpload(boardvo);
		return n;
	}

	// 총 게시물 건수(totalCount) 구하기 - 검색이 있을때와 검색이 없을때로 나뉜다.
	@Override
	public int getTotalCount(Map<String, String> paraMap) {
		int n = dao.getTotalCount(paraMap);
		return n;
	}

	// 페이징 처리한 글목록 가져오기(검색어 유무 상관없이 모두 다 포함한것)
	@Override
	public List<BoardVOKdn> noticeBoardListSearchWithPaging(Map<String, String> paraMap) {
		List<BoardVOKdn> boardList = dao.noticeBoardListSearchWithPaging(paraMap);
		return boardList;
	}

	// 공지사항 글조회수 증가와 함께 글1개를 조회를 해주는 것
	@Override
	public BoardVOKdn getView(String seq, String login_userid) {
		BoardVOKdn boardvo = dao.getView(seq);	// 공지사항 글1개 조회하기
		
		if(login_userid != null && boardvo != null && !login_userid.equals(boardvo.getFk_employeeid())) {
			// 로그인이 되어있어야하고 boardvo 데이터가 있어야하고 게시글 작성자 아이디가 게시글 조회하는 유저 아이디와 다를때 
			dao.setAddReadCount(seq); // 글 조회수 1 증가하기
			boardvo = dao.getView(seq);
		}
		
		return boardvo;
	}

	// 글조회수 증가는 없고 단순히 글1개 조회만을 해주는 것
	@Override
	public BoardVOKdn getViewWithNoAddCount(String seq) {
		BoardVOKdn boardvo = dao.getView(seq);	//글1개 조회하기
		return boardvo;
	}
	

}
