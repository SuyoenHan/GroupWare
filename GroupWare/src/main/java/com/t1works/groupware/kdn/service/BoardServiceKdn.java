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
	
	// ========== 공지사항 =========== 
	
	// 공지사항 글쓰기
	@Override
	public int noticePostUpload(BoardVOKdn boardvo) {
		int n = dao.noticePostUpload(boardvo);
		return n;
	}

	// (공지사항) 총 게시물 건수(totalCount) 구하기 - 검색이 있을때와 검색이 없을때로 나뉜다.
	@Override
	public int getNoticeTotalCount(Map<String, String> paraMap) {
		int n = dao.getNoticeTotalCount(paraMap);
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

	//글 수정하기
	@Override
	public int noticeEdit(BoardVOKdn boardvo) {
		int n = dao.noticeEdit(boardvo);
		return n;
	}
	
	// 글 삭제하기
	@Override
	public int noticeDel(Map<String, String> paraMap) {
		int n = dao.noticeDel(paraMap);
		return n;
	}
	
	
	// ========== 건의사항 ===========
	
	// (건의사항) 페이징 처리한 글목록 가져오기(검색어 유무 상관없이 모두 다 포함한것)
	@Override
	public List<BoardVOKdn> listSearchWithPaging(Map<String, String> paraMap) {
		List<BoardVOKdn> boardList = dao.listSearchWithPaging(paraMap);
		return boardList;
	}

	// 총 게시물 건수(totalCount) 구하기 - 검색이 있을때와 검색이 없을때로 나뉜다.
	@Override
	public int getTotalCount(Map<String, String> paraMap) {
		int n = dao.getTotalCount(paraMap);
		return n;
	}

	// 건의사항 게시판 글쓰기 완료 요청
	@Override
	public int suggPostUpload(BoardVOKdn boardvo) {
		int n = dao.suggPostUpload(boardvo);
		return n;
	}

	// (건의사항) 글조회수 증가와 함께 글1개를 조회를 해주는 것
	@Override
	public BoardVOKdn getSuggPostView(String seq, String login_userid) {
		BoardVOKdn boardvo = dao.getSuggPostView(seq);	// 공지사항 글1개 조회하기
		
		if(login_userid != null && boardvo != null && !login_userid.equals(boardvo.getFk_employeeid())) {
			// 로그인이 되어있어야하고 boardvo 데이터가 있어야하고 게시글 작성자 아이디가 게시글 조회하는 유저 아이디와 다를때 
			dao.setAddSuggReadCount(seq); // 글 조회수 1 증가하기
			boardvo = dao.getSuggPostView(seq);
		}
		
		return boardvo;
	}

	// (건의사항) 글조회수 증가는 없고 단순히 글1개 조회만을 해주는 것
	@Override
	public BoardVOKdn getSuggViewWithNoAddCount(String seq) {
		BoardVOKdn boardvo = dao.getSuggPostView(seq);	//글1개 조회하기
		return boardvo;
	}

	//(건의사항) 글 수정하기
	@Override
	public int suggEdit(BoardVOKdn boardvo) {
		int n = dao.suggEdit(boardvo);
		return n;
	}
	
	// 글 삭제하기
	@Override
	public int suggDel(Map<String, String> paraMap) {
		int n = dao.suggDel(paraMap);
		return n;
	}
	
	// ========== 자유게시판 ===========
	
	// 자유게시판 글쓰기 완료 요청
	@Override
	public int genPostUpload(BoardVOKdn boardvo) {
		int n = dao.genPostUpload(boardvo);
		return n;
	}

	// 자유게시판 총 게시물 건수(totalCount) 구하기 - 검색이 있을때와 검색이 없을때로 나뉜다.
	@Override
	public int getGenTotalCount(Map<String, String> paraMap) {
		int n = dao.getGenTotalCount(paraMap);
		return n;
	}

	// (자유게시판) 페이징 처리한 글목록 가져오기(검색어 유무 상관없이 모두 다 포함한것)
	@Override
	public List<BoardVOKdn> genListSearchWithPaging(Map<String, String> paraMap) {
		List<BoardVOKdn> boardList = dao.genListSearchWithPaging(paraMap);
		return boardList;
	}

	// (자유게시판) 글조회수 증가와 함께 글1개를 조회를 해주는 것
	public BoardVOKdn getGenPostView(String seq, String login_userid) {
		BoardVOKdn boardvo = dao.getGenPostView(seq);	// 공지사항 글1개 조회하기
		
		if(login_userid != null && boardvo != null && !login_userid.equals(boardvo.getFk_employeeid())) {
			// 로그인이 되어있어야하고 boardvo 데이터가 있어야하고 게시글 작성자 아이디가 게시글 조회하는 유저 아이디와 다를때 
			dao.setAddGenReadCount(seq); // 글 조회수 1 증가하기
			boardvo = dao.getGenPostView(seq);
		}
		
		return boardvo;
	}
	
	// (자유게시판) 글조회수 증가는 없고 단순히 글1개 조회만을 해주는 것
	@Override
	public BoardVOKdn getGenViewWithNoAddCount(String seq) {
		BoardVOKdn boardvo = dao.getGenPostView(seq);	//글1개 조회하기
		return boardvo;
	}

	//(자유게시판) 글 수정하기
	@Override
	public int generalEdit(BoardVOKdn boardvo) {
		int n = dao.generalEdit(boardvo);
		return n;
	}

	// 글 삭제하기
	@Override
	public int generalDel(Map<String, String> paraMap) {
		int n = dao.generalDel(paraMap);
		return n;
	}

	

	

	

	


	

}
