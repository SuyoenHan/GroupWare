package com.t1works.groupware.kdn.model;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface InterBoardDAOKdn {

	int noticePostUpload(BoardVOKdn boardvo); // 공지사항 글쓰기

	int getTotalCount(Map<String, String> paraMap); // 총 게시물 건수(totalCount) 구하기 - 검색이 있을때와 검색이 없을때로 나뉜다.

	List<BoardVOKdn> noticeBoardListSearchWithPaging(Map<String, String> paraMap); // 페이징 처리한 글목록 가져오기(검색어 유무 상관없이 모두 다 포함한것)

	// 페이징 처리를 안한 검색어가 없는 전체 글목록 보여주기
	//List<BoardVOKdn> boardListNoSearch();

}
