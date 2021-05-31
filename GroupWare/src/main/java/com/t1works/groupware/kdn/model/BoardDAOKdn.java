package com.t1works.groupware.kdn.model;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class BoardDAOKdn implements InterBoardDAOKdn {
	
	@Resource
	private SqlSessionTemplate sqlsession3; // 원격DB에 연결
	
	// 공지사항 글쓰기
	@Override
	public int noticePostUpload(BoardVOKdn boardvo) {
		int n = sqlsession3.insert("board.noticePostUpload", boardvo);
		return n;
	}

	// 총 게시물 건수(totalCount) 구하기 - 검색이 있을때와 검색이 없을때로 나뉜다.
	@Override
	public int getTotalCount(Map<String, String> paraMap) {
		int n =sqlsession3.selectOne("board.getTotalCount", paraMap);
		return n;
	}

	// 페이징 처리한 글목록 가져오기(검색어 유무 상관없이 모두 다 포함한것)
	@Override
	public List<BoardVOKdn> noticeBoardListSearchWithPaging(Map<String, String> paraMap) {
		List<BoardVOKdn> boardList = sqlsession3.selectList("board.noticeBoardListSearchWithPaging", paraMap);
		return boardList;
	}
	
	// 공지사항 글 조회수 1 증가하기
	@Override
	public void setAddReadCount(String seq) {
		sqlsession3.update("board.setAddReadCount", seq);
	}

	// 공지사항 글1개 조회하기
	@Override
	public BoardVOKdn getView(String seq) {
		BoardVOKdn boardvo = sqlsession3.selectOne("board.getView", seq);
		return boardvo;
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
