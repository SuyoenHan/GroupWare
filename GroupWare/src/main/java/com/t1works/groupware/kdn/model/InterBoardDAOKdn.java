package com.t1works.groupware.kdn.model;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface InterBoardDAOKdn {

	// === 공지사항 === 
	
	int noticePostUpload(BoardVOKdn boardvo); // 공지사항 글쓰기

	int getNoticeTotalCount(Map<String, String> paraMap); // 총 게시물 건수(totalCount) 구하기 - 검색이 있을때와 검색이 없을때로 나뉜다.

	List<BoardVOKdn> noticeBoardListSearchWithPaging(Map<String, String> paraMap); // (공지사항) 페이징 처리한 글목록 가져오기(검색어 유무 상관없이 모두 다 포함한것)

	BoardVOKdn getView(String seq); // 공지사항 글1개 조회하기

	void setAddReadCount(String seq); // 공지사항 글 조회수 1 증가하기
	
	int noticeEdit(BoardVOKdn boardvo); // 글 수정하기
	
	int noticeDel(Map<String, String> paraMap); // 글 삭제하기

	// === 건의사항 ===
	
	List<BoardVOKdn> listSearchWithPaging(Map<String, String> paraMap); // (건의사항) 페이징 처리한 글목록 가져오기(검색어 유무 상관없이 모두 다 포함한것)

	int getTotalCount(Map<String, String> paraMap); // 페이징 처리한 글목록 가져오기(검색어 유무 상관없이 모두 다 포함한것)

	int suggPostUpload(BoardVOKdn boardvo); // 건의사항 게시판 글쓰기 완료 요청

	BoardVOKdn getSuggPostView(String seq); // 건의사항 글1개 조회하기

	void setAddSuggReadCount(String seq); // 글 조회수 1 증가하기
	
	int suggEdit(BoardVOKdn boardvo); // 글 수정하기
	
	int suggDel(Map<String, String> paraMap); // 글 삭제하기

	// === 자유게시판 ===
	
	int genPostUpload(BoardVOKdn boardvo); // 자유게시판 글쓰기 완료 요청

	int getGenTotalCount(Map<String, String> paraMap); // 총 게시물 건수(totalCount) 구하기 - 검색이 있을때와 검색이 없을때로 나뉜다.

	List<BoardVOKdn> genListSearchWithPaging(Map<String, String> paraMap); // 페이징 처리한 글목록 가져오기(검색어 유무 상관없이 모두 다 포함한것)

	BoardVOKdn getGenPostView(String seq); // 자유게시판 글1개 조회하기

	void setAddGenReadCount(String seq); // 글 조회수 1 증가하기

	int generalEdit(BoardVOKdn boardvo); // 글 수정하기

	int generalDel(Map<String, String> paraMap); // 글삭제하기

	

	

	

	


}
