package com.t1works.groupware.kdn.service;

import java.util.List;
import java.util.Map;

import com.t1works.groupware.kdn.model.BoardVOKdn;

public interface InterBoardServiceKdn {

	// === 공지사항 ===
	
	int noticePostUpload(BoardVOKdn boardvo); // 공지사항 글쓰기

	int getTotalCount(Map<String, String> paraMap);	// 총 게시물 건수(totalCount) 구하기 - 검색이 있을때와 검색이 없을때로 나뉜다. 

	List<BoardVOKdn> noticeBoardListSearchWithPaging(Map<String, String> paraMap); // 페이징 처리한 글목록 가져오기(검색어 유무 상관없이 모두 다 포함한것)

	BoardVOKdn getView(String seq, String login_userid); // 글조회수 증가와 함께 글1개를 조회를 해주는 것

	BoardVOKdn getViewWithNoAddCount(String seq); // 글조회수 증가는 없고 단순히 글1개 조회만을 해주는 것

	
	
	

}
