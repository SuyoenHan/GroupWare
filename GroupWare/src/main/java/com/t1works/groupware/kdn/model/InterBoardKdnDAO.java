package com.t1works.groupware.kdn.model;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface InterBoardKdnDAO {

	// === 공지사항 === 
	
	int noticePostUpload(BoardKdnVO boardvo); // 공지사항 글쓰기

	int getNoticeTotalCount(Map<String, String> paraMap); // 총 게시물 건수(totalCount) 구하기 - 검색이 있을때와 검색이 없을때로 나뉜다.

	List<BoardKdnVO> noticeBoardListSearchWithPaging(Map<String, String> paraMap); // (공지사항) 페이징 처리한 글목록 가져오기(검색어 유무 상관없이 모두 다 포함한것)

	BoardKdnVO getView(Map<String, String> paraMap); // 공지사항 글1개 조회하기

	void setAddReadCount(Map<String, String> paraMap); // 공지사항 글 조회수 1 증가하기
	
	int noticeEdit(BoardKdnVO boardvo); // 글 수정하기
	
	int noticeDel(Map<String, String> paraMap); // 글 삭제하기

	// === 건의사항 ===
	
	List<BoardKdnVO> listSearchWithPaging(Map<String, String> paraMap); // (건의사항) 페이징 처리한 글목록 가져오기(검색어 유무 상관없이 모두 다 포함한것)

	int getTotalCount(Map<String, String> paraMap); // 페이징 처리한 글목록 가져오기(검색어 유무 상관없이 모두 다 포함한것)

	int suggPostUpload(BoardKdnVO boardvo); // 건의사항 게시판 글쓰기 완료 요청

	BoardKdnVO getSuggPostView(Map<String, String> paraMap); // 건의사항 글1개 조회하기

	void setAddSuggReadCount(Map<String, String> paraMap); // 글 조회수 1 증가하기
	
	int suggEdit(BoardKdnVO boardvo); // 글 수정하기
	
	int suggDel(Map<String, String> paraMap); // 글 삭제하기
	
	int addSuggComment(CommentKdnVO commentvo); // 댓글쓰기(tbl_suggestionboardcomment 테이블에 insert)
	
	int updateSuggCmntCount(String fk_seq); // tbl_suggestionboard 테이블에 commentCount 컬럼의 값을 1증가(update) 

	List<CommentKdnVO> getSuggCmntListPaging(Map<String, String> paraMap); // 원게시물에 딸린 댓글들을 페이징처리해서 조회해오기(Ajax 로 처리)
	 
	int getSuggCmntTotalPage(Map<String, String> paraMap); // 원게시물에 딸린 댓글 totalPage 알아오기(Ajax 로 처리)
	
	int getGroupnoMax(); // tbl_suggestionboard 테이블에서 groupno 컬럼의 최대값 구하기
	
	int delSuggComment(String seq); // 건의사항 댓글 삭제하기
	
	
	// === 자유게시판 ===
	
	int genPostUpload(BoardKdnVO boardvo); // 자유게시판 글쓰기 완료 요청

	int getGenTotalCount(Map<String, String> paraMap); // 총 게시물 건수(totalCount) 구하기 - 검색이 있을때와 검색이 없을때로 나뉜다.

	List<BoardKdnVO> genListSearchWithPaging(Map<String, String> paraMap); // 페이징 처리한 글목록 가져오기(검색어 유무 상관없이 모두 다 포함한것)

	BoardKdnVO getGenPostView(Map<String, String> paraMap); // 자유게시판 글1개 조회하기

	void setAddGenReadCount(Map<String, String> paraMap); // 글 조회수 1 증가하기

	int generalEdit(BoardKdnVO boardvo); // 글 수정하기

	int generalDel(Map<String, String> paraMap); // 글삭제하기

	int addComment(CommentKdnVO commentvo); // 댓글쓰기(tbl_generalboardcomment 테이블에 insert)

	int updateCommentCount(String fk_seq); // tbl_generalboard 테이블에 commentCount 컬럼의 값을 1증가(update)

	int getCommentTotalPage(Map<String, String> paraMap); // 원게시물에 딸린 댓글 totalPage 알아오기(Ajax 로 처리)

	List<CommentKdnVO> getCommentListPaging(Map<String, String> paraMap); // 원게시물에 딸린 댓글들을 페이징처리해서 조회해오기(Ajax 로 처리)

	

	

	

	

	

	

	

	

	

	


}
