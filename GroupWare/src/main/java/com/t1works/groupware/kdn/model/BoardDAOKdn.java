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
	
	// =========== 공지사항 =============
	// 공지사항 글쓰기
	@Override
	public int noticePostUpload(BoardVOKdn boardvo) {
		int n = sqlsession3.insert("board.noticePostUpload", boardvo);
		return n;
	}

	// 공지사항 총 게시물 건수(totalCount) 구하기 - 검색이 있을때와 검색이 없을때로 나뉜다.
	@Override
	public int getNoticeTotalCount(Map<String, String> paraMap) {
		int n =sqlsession3.selectOne("board.getNoticeTotalCount", paraMap);
		return n;
	}

	// 공지사항 페이징 처리한 글목록 가져오기(검색어 유무 상관없이 모두 다 포함한것)
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
	
	// 공지사항 글 수정하기
	@Override
	public int noticeEdit(BoardVOKdn boardvo) {
		int n = sqlsession3.update("board.noticeEdit", boardvo);
		return n;
	}
	
	// 공지사항 글 삭제하기
	@Override
	public int noticeDel(Map<String, String> paraMap) {
		int n = sqlsession3.delete("board.noticeDel",paraMap);
		return n;
	}
	
	
	// =========== 건의사항 =============

	// (건의사항) 페이징 처리한 글목록 가져오기(검색어 유무 상관없이 모두 다 포함한것)
	@Override
	public List<BoardVOKdn> listSearchWithPaging(Map<String, String> paraMap) {
		List<BoardVOKdn> boardList = sqlsession3.selectList("board.listSearchWithPaging", paraMap);
		return boardList;
	}

	// 페이징 처리한 글목록 가져오기(검색어 유무 상관없이 모두 다 포함한것)
	@Override
	public int getTotalCount(Map<String, String> paraMap) {
		int n =sqlsession3.selectOne("board.getTotalCount", paraMap);
		return n;
	}

	// 건의사항 게시판 글쓰기 완료 요청
	@Override
	public int suggPostUpload(BoardVOKdn boardvo) {
		int n = sqlsession3.insert("board.suggPostUpload", boardvo);
		return n;
	}

	// 건의사항 글1개 조회하기
	@Override
	public BoardVOKdn getSuggPostView(String seq) {
		BoardVOKdn boardvo = sqlsession3.selectOne("board.getSuggPostView", seq);
		return boardvo;
	}

	// 건의사항 글 조회수 1 증가하기
	@Override
	public void setAddSuggReadCount(String seq) {
		sqlsession3.update("board.setAddSuggReadCount", seq);
	}

	// 건의사항 글 수정하기
	@Override
	public int suggEdit(BoardVOKdn boardvo) {
		int n = sqlsession3.update("board.suggEdit", boardvo);
		return n;
	}
	
	// 건의사항 글 삭제하기
	@Override
	public int suggDel(Map<String, String> paraMap) {
		int n = sqlsession3.delete("board.suggDel",paraMap);
		return n;
	}
	
	
	// =========== 자유게시판  =============
	
	// 자유게시판 글쓰기 완료 요청
	@Override
	public int genPostUpload(BoardVOKdn boardvo) {
		int n = sqlsession3.insert("board.genPostUpload", boardvo);
		return n;
	}

	// 자유게시판 페이징 처리한 글목록 가져오기(검색어 유무 상관없이 모두 다 포함한것)
	@Override
	public int getGenTotalCount(Map<String, String> paraMap) {
		int n =sqlsession3.selectOne("board.getGenTotalCount", paraMap);
		return n;
	}

	// 페이징 처리한 글목록 가져오기(검색어 유무 상관없이 모두 다 포함한것)
	@Override
	public List<BoardVOKdn> genListSearchWithPaging(Map<String, String> paraMap) {
		List<BoardVOKdn> boardList = sqlsession3.selectList("board.genListSearchWithPaging", paraMap);
		return boardList;
	}

	// 자유게시판 글1개 조회하기
	@Override
	public BoardVOKdn getGenPostView(String seq) {
		BoardVOKdn boardvo = sqlsession3.selectOne("board.getGenPostView", seq);
		return boardvo;
	}

	// 글 조회수 1 증가하기
	@Override
	public void setAddGenReadCount(String seq) {
		sqlsession3.update("board.setAddGenReadCount", seq);
		
	}

	// 글 수정하기
	@Override
	public int generalEdit(BoardVOKdn boardvo) {
		int n = sqlsession3.update("board.generalEdit", boardvo);
		return n;
	}

	// 글 삭제하기
	@Override
	public int generalDel(Map<String, String> paraMap) {
		int n = sqlsession3.delete("board.generalDel",paraMap);
		return n;
	}

	

	

	

	

	


}
