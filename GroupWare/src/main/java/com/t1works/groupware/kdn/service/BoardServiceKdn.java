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
	
	/*
	// 페이징 처리를 안한 검색어가 없는 전체 글목록 보여주기
	@Override
	public List<BoardVOKdn> boardListNoSearch() {
		List<BoardVOKdn> boardList = dao.boardListNoSearch();
		
		return boardList;
	}
	*/

}
