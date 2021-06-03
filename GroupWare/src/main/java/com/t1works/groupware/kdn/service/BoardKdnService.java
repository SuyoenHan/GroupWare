package com.t1works.groupware.kdn.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.t1works.groupware.kdn.model.BoardKdnVO;
import com.t1works.groupware.kdn.model.CommentKdnVO;
import com.t1works.groupware.kdn.model.InterBoardKdnDAO;

@Component
@Service
public class BoardKdnService implements InterBoardKdnService {

	@Autowired
	private InterBoardKdnDAO dao;
	
	// ========== 공지사항 =========== 
	
	// 공지사항 글쓰기
	@Override
	public int noticePostUpload(BoardKdnVO boardvo) {
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
	public List<BoardKdnVO> noticeBoardListSearchWithPaging(Map<String, String> paraMap) {
		List<BoardKdnVO> boardList = dao.noticeBoardListSearchWithPaging(paraMap);
		return boardList;
	}

	// 공지사항 글조회수 증가와 함께 글1개를 조회를 해주는 것
	@Override
	public BoardKdnVO getView(Map<String, String> paraMap, String login_userid) {
		BoardKdnVO boardvo = dao.getView(paraMap);	// 공지사항 글1개 조회하기
		
		if(login_userid != null && boardvo != null && !login_userid.equals(boardvo.getFk_employeeid())) {
			// 로그인이 되어있어야하고 boardvo 데이터가 있어야하고 게시글 작성자 아이디가 게시글 조회하는 유저 아이디와 다를때 
			dao.setAddReadCount(paraMap); // 글 조회수 1 증가하기
			boardvo = dao.getView(paraMap);
		}
		
		return boardvo;
	}

	// 글조회수 증가는 없고 단순히 글1개 조회만을 해주는 것
	@Override
	public BoardKdnVO getViewWithNoAddCount(Map<String, String> paraMap) {
		BoardKdnVO boardvo = dao.getView(paraMap);	//글1개 조회하기
		return boardvo;
	}

	//글 수정하기
	@Override
	public int noticeEdit(BoardKdnVO boardvo) {
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
	public List<BoardKdnVO> listSearchWithPaging(Map<String, String> paraMap) {
		List<BoardKdnVO> boardList = dao.listSearchWithPaging(paraMap);
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
	public int suggPostUpload(BoardKdnVO boardvo) {
		int n = dao.suggPostUpload(boardvo);
		return n;
	}

	// (건의사항) 글조회수 증가와 함께 글1개를 조회를 해주는 것
	@Override
	public BoardKdnVO getSuggPostView(Map<String, String> paraMap, String login_userid) {
		BoardKdnVO boardvo = dao.getSuggPostView(paraMap);	// 공지사항 글1개 조회하기
		
		if(login_userid != null && boardvo != null && !login_userid.equals(boardvo.getFk_employeeid())) {
			// 로그인이 되어있어야하고 boardvo 데이터가 있어야하고 게시글 작성자 아이디가 게시글 조회하는 유저 아이디와 다를때 
			dao.setAddSuggReadCount(paraMap); // 글 조회수 1 증가하기
			boardvo = dao.getSuggPostView(paraMap);
		}
		
		return boardvo;
	}

	// (건의사항) 글조회수 증가는 없고 단순히 글1개 조회만을 해주는 것
	@Override
	public BoardKdnVO getSuggViewWithNoAddCount(Map<String, String> paraMap) {
		BoardKdnVO boardvo = dao.getSuggPostView(paraMap);	//글1개 조회하기
		return boardvo;
	}

	//(건의사항) 글 수정하기
	@Override
	public int suggEdit(BoardKdnVO boardvo) {
		int n = dao.suggEdit(boardvo);
		return n;
	}
	
	// 글 삭제하기
	@Override
	public int suggDel(Map<String, String> paraMap) {
		int n = dao.suggDel(paraMap);
		return n;
	}
	
	// 건의사항 댓글쓰기
	@Override
	public int addSuggComment(CommentKdnVO commentvo) {
		int n=0, result=0;
	      
	    n = dao.addSuggComment(commentvo); // 댓글쓰기(tbl_comment 테이블에 insert)
	    //  n <== 1 
	      
	    if(n==1) {
	       result = dao.updateSuggCmntCount(commentvo.getFk_seq()); // tbl_generalboard 테이블에 commentCount 컬럼의 값을 1증가(update) 
	    //  m <== 1     
	    }
	      
		return result;
		
	}
	
	// 원게시물에 딸린 댓글들을 페이징처리해서 조회해오기(Ajax 로 처리)
	@Override
	public List<CommentKdnVO> getSuggCmntListPaging(Map<String, String> paraMap) {
		List<CommentKdnVO> commentList = dao.getSuggCmntListPaging(paraMap);
		return commentList;
	}

	// 건의사항 원게시물에 딸린 댓글 totalPage 알아오기(Ajax 로 처리)
	@Override
	public int getSuggCmntTotalPage(Map<String, String> paraMap) {
		int totalPage = dao.getSuggCmntTotalPage(paraMap);
		return totalPage;
	}
	
	// ========== 자유게시판 ===========
	
	// 자유게시판 글쓰기 완료 요청
	@Override
	public int genPostUpload(BoardKdnVO boardvo) {
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
	public List<BoardKdnVO> genListSearchWithPaging(Map<String, String> paraMap) {
		List<BoardKdnVO> boardList = dao.genListSearchWithPaging(paraMap);
		return boardList;
	}

	// (자유게시판) 글조회수 증가와 함께 글1개를 조회를 해주는 것
	public BoardKdnVO getGenPostView(Map<String, String> paraMap, String login_userid) {
		BoardKdnVO boardvo = dao.getGenPostView(paraMap);	// 공지사항 글1개 조회하기
		
		if(login_userid != null && boardvo != null && !login_userid.equals(boardvo.getFk_employeeid())) {
			// 로그인이 되어있어야하고 boardvo 데이터가 있어야하고 게시글 작성자 아이디가 게시글 조회하는 유저 아이디와 다를때 
			dao.setAddGenReadCount(paraMap); // 글 조회수 1 증가하기
			boardvo = dao.getGenPostView(paraMap);
		}
		
		return boardvo;
	}
	
	// (자유게시판) 글조회수 증가는 없고 단순히 글1개 조회만을 해주는 것
	@Override
	public BoardKdnVO getGenViewWithNoAddCount(Map<String, String> paraMap) {
		BoardKdnVO boardvo = dao.getGenPostView(paraMap);	//글1개 조회하기
		return boardvo;
	}

	//(자유게시판) 글 수정하기
	@Override
	public int generalEdit(BoardKdnVO boardvo) {
		int n = dao.generalEdit(boardvo);
		return n;
	}

	// 글 삭제하기
	@Override
	public int generalDel(Map<String, String> paraMap) {
		int n = dao.generalDel(paraMap);
		return n;
	}

	// 댓글쓰기
	@Override
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor= {Throwable.class})
	public int addComment(CommentKdnVO commentvo) {
		int n=0, result=0;
	      
	      n = dao.addComment(commentvo); // 댓글쓰기(tbl_comment 테이블에 insert)
	      //  n <== 1 
	      
	      if(n==1) {
	         result = dao.updateCommentCount(commentvo.getFk_seq()); // tbl_generalboard 테이블에 commentCount 컬럼의 값을 1증가(update) 
	      //  m <== 1     
	      }
	      
		return result;
	}

	// 원게시물에 딸린 댓글들을 페이징처리해서 조회해오기(Ajax 로 처리)
	@Override
	public List<CommentKdnVO> getCommentListPaging(Map<String, String> paraMap) {
		List<CommentKdnVO> commentList = dao.getCommentListPaging(paraMap);
		return commentList;
	}

	// 원게시물에 딸린 댓글 totalPage 알아오기(Ajax 로 처리)
	@Override
	public int getCommentTotalPage(Map<String, String> paraMap) {
		int totalPage = dao.getCommentTotalPage(paraMap);
		return totalPage;
	}

	

	

	

	

	

	


	

}
