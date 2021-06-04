package com.t1works.groupware.kdn.service;

import java.util.List;
import java.util.Map;

import com.t1works.groupware.kdn.model.BoardKdnVO;
import com.t1works.groupware.kdn.model.CommentKdnVO;

public interface InterBoardKdnService {

	// === 공지사항 ===
	
	int noticePostUpload(BoardKdnVO boardvo); // 공지사항 글쓰기

	int getNoticeTotalCount(Map<String, String> paraMap);	// (공지사항) 총 게시물 건수(totalCount) 구하기 - 검색이 있을때와 검색이 없을때로 나뉜다. 

	List<BoardKdnVO> noticeBoardListSearchWithPaging(Map<String, String> paraMap); // 페이징 처리한 글목록 가져오기(검색어 유무 상관없이 모두 다 포함한것)

	BoardKdnVO getView(Map<String, String> paraMap, String login_userid); // 글조회수 증가와 함께 글1개를 조회를 해주는 것

	BoardKdnVO getViewWithNoAddCount(Map<String, String> paraMap); // 글조회수 증가는 없고 단순히 글1개 조회만을 해주는 것
	
	int noticeEdit(BoardKdnVO boardvo); // 글 수정하기
	
	int noticeDel(Map<String, String> paraMap); // 글 삭제하기
	
	// === 건의사항 ===

	List<BoardKdnVO> listSearchWithPaging(Map<String, String> paraMap);	// (페이징 처리한 글목록 가져오기(검색어 유무 상관없이 모두 다 포함한것)

	int getTotalCount(Map<String, String> paraMap); // 총 게시물 건수(totalCount) 구하기 - 검색이 있을때와 검색이 없을때로 나뉜다.

	int suggPostUpload(BoardKdnVO boardvo); // 건의사항 게시판 글쓰기 완료 요청

	BoardKdnVO getSuggPostView(Map<String, String> paraMap, String login_userid); // 글조회수 증가와 함께 글1개를 조회를 해주는 것

	BoardKdnVO getSuggViewWithNoAddCount(Map<String, String> paraMap); // 글조회수 증가는 없고 단순히 글1개 조회만을 해주는 것

	int suggEdit(BoardKdnVO boardvo); // 글 수정하기
	
	int suggDel(Map<String, String> paraMap); // 글 삭제하기
	
	int addSuggComment(CommentKdnVO commentvo); // 댓글 쓰기
	
	List<CommentKdnVO> getSuggCmntListPaging(Map<String, String> paraMap); // 원게시물에 딸린 댓글들을 페이징처리해서 조회해오기(Ajax 로 처리)
	
	int getSuggCmntTotalPage(Map<String, String> paraMap); // 원게시물에 딸린 댓글 totalPage 알아오기(Ajax 로 처리)
	
	int delSuggComment(String seq); // 댓글 삭제하기
	
	int editSuggComment(String seq); // (건의사항) 댓글 수정하기
	
	// === 자유게시판 ===
	
	int genPostUpload(BoardKdnVO boardvo); // 자유게시판 글쓰기 완료 요청

	int getGenTotalCount(Map<String, String> paraMap); // 총 게시물 건수(totalCount) 구하기 - 검색이 있을때와 검색이 없을때로 나뉜다.

	List<BoardKdnVO> genListSearchWithPaging(Map<String, String> paraMap); // (페이징 처리한 글목록 가져오기(검색어 유무 상관없이 모두 다 포함한것)

	BoardKdnVO getGenViewWithNoAddCount(Map<String, String> paraMap); // 글조회수 증가는 없고 단순히 글1개 조회만을 해주는 것

	BoardKdnVO getGenPostView(Map<String, String> paraMap, String login_userid); // 글조회수 증가와 함께 글1개를 조회를 해주는 것

	int generalEdit(BoardKdnVO boardvo); // 글 수정하기

	int generalDel(Map<String, String> paraMap); // 글 삭제하기

	int addComment(CommentKdnVO commentvo); // 댓글 쓰기

	List<CommentKdnVO> getCommentListPaging(Map<String, String> paraMap); // 원게시물에 딸린 댓글들을 페이징처리해서 조회해오기(Ajax 로 처리)

	int getCommentTotalPage(Map<String, String> paraMap); // 원게시물에 딸린 댓글 totalPage 알아오기(Ajax 로 처리)

	int delGenComment(String seq); //(자유게시판) 댓글 삭제하기

	

	

	

	

	

	

	

	

	

	
	
	

}
