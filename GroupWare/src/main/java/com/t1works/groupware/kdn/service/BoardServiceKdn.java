package com.t1works.groupware.kdn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

//import org.springframework.beans.factory.annotation.Autowired;

import com.t1works.groupware.kdn.model.BoardVOKdn;
import com.t1works.groupware.kdn.model.InterBoardDAOKdn;

public class BoardServiceKdn implements InterBoardServiceKdn {

	@Autowired
	private InterBoardDAOKdn dao;
	
	// === 공지사항 글쓰기 ===
	@Override
	public int noticePostUpload(BoardVOKdn boardvo) {
		int n = dao.noticePostUpload(boardvo);
		return n;
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
