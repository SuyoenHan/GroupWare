package com.t1works.groupware.kdn.model;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;

public class BoardDAOKdn implements InterBoardDAOKdn {
	
	@Resource
	private SqlSessionTemplate sqlsession3; // 원격DB에 연결
	
	// === 공지사항 글쓰기 ===
	@Override
	public int noticePostUpload(BoardVOKdn boardvo) {
		int n = sqlsession3.insert("boardKdn.noticePostUpload", boardvo);
		return n;
	}
	
	/*
	// 페이징 처리를 안한 검색어가 없는 전체 글목록 보여주기
	@Override
	public List<BoardVOKdn> boardListNoSearch() {
		List<BoardVOKdn> boardList = sqlsession.selectList("board.boardListNoSearch");
		
		return boardList;
	}


*/
}
