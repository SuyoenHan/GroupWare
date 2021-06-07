package com.t1works.groupware.jsh.service;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.t1works.groupware.jsh.model.ElectronPayJshVO;
import com.t1works.groupware.jsh.model.InterElectronPayJshDAO;


@Component
@Service
public class PaymentJshService implements InterPaymentJshService {

	@Autowired
	private InterElectronPayJshDAO dao;

	// 일반결재내역 목록 보여주기 검색어가 없는
	@Override
	public List<ElectronPayJshVO> generalPayment_List() {
		List<ElectronPayJshVO>electronList = dao.generalPayment_List();
		return electronList ;
	}

	// 일반결재내역 페이징 처리를 안한 검색어가 있는 전체 글목록 보여주기 == //
	@Override
	public List<ElectronPayJshVO> electronListSearch(Map<String, String> paraMap) {
		List<ElectronPayJshVO>electronList = dao. electronListSearch(paraMap);
		return electronList;
	}

	//검색어 입력시 자동글 완성하기
	@Override
	public List<String> wordSearchShow(Map<String, String> paraMap) {
		 List<String> wordList = dao.wordSearchShow(paraMap);
			return wordList;
	}

	// 총 게시물 건수(totalCount) 구하기 - 검색이 있을때와 검색이 없을때로 나뉜다.
	@Override
	public int getTotalCount(Map<String, String> paraMap) {
		int n = dao.getTotalCount(paraMap);
		return n;
	}

	 //페이징 처리한 글목록 가져오기(검색이 있든지, 검색이 없든지 모두 다 포함한 것)
	@Override
	public List<ElectronPayJshVO> electronListSearchWithPaging(Map<String, String> paraMap) {
		List<ElectronPayJshVO> electronList = dao.electronListSearchWithPaging(paraMap);
		return electronList;
	}

	// 하나의 일반결재내역 문서 보여주기
	@Override
	public ElectronPayJshVO generalOneView(Map<String, String> paraMap) {
		ElectronPayJshVO epvo =dao.generalOneView(paraMap);
		return epvo;
	}

	//하나의 일반결재내역에서 결재의견목록 보여주기
	@Override
	public List<ElectronPayJshVO> oneOpinionList(Map<String, String> paraMap) {
		List<ElectronPayJshVO> opinionList = dao.oneOpinionList(paraMap);
		return opinionList;
	}

	//일반결재 글쓰기
	@Override
	public ElectronPayJshVO WriteJsh(Map<String, String> paraMap) {
		ElectronPayJshVO write_view = dao.WriteJsh(paraMap);
		return write_view;
	}
	// 수신자 정보 select해오기
		@Override
		public ElectronPayJshVO mWriteJsh(HashMap<String, String> paraMap) {
			ElectronPayJshVO write_mview = dao.mWriteJsh(paraMap);
			return write_mview;
		}

	

	// 일반결재 문서insert
	@Override
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor= {Throwable.class})
	public int addPayment(ElectronPayJshVO epvo) throws Throwable {
		
		
		// 1) insert될 문서번호를 알아온다
		String ano = dao.insertno();
		epvo.setAno(ano);
		
		int n1=0, n2=0, n3=0; 
		// 2) 전자결재 테이블에 insert
		 n1 = dao.Electricadd(epvo);
		 
		if(n1==1) {
			// 3) ncatname 조건에 따라 insert 시켜줌 일반결재테이블에 insert  
			 n2 = dao.Generaladd(epvo);
			
		  }
		if(n2==1) {
		 // 4) ncatname 조건에 따라 (회의록,위임장,외부공문,협조공문) 테이블에 insert 시켜줌 
			 n3 =dao.selectadd(epvo);
		}
		return n3;
	}

	// 글쓰기( 파일첨부가 있는 글쓰기  ) 
	@Override
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor= {Throwable.class})
	public int addPayment_withFile(ElectronPayJshVO epvo) throws Throwable {
	// 1) insert될 문서번호를 알아온다
			String ano = dao.insertno();
			epvo.setAno(ano);
			
			int n1=0, n2=0, n3=0; 
			// 2) 전자결재 테이블에 insert
			 n1 = dao.Electricadd_withFile(epvo);
			 
			if(n1==1) {
				// 3) ncatname 조건에 따라 insert 시켜줌 일반결재테이블에 insert  
			 n2 = dao.Generaladd(epvo);
				
			  }
			if(n2==1) {
			// 4) ncatname 조건에 따라 (회의록,위임장,외부공문,협조공문) 테이블에 insert 시켜줌 
				 n3 =dao.selectadd(epvo);
			}
			return n3;
	}

	//임시저장함 insert-첨부파일X
	@Override
	public int savePayment(ElectronPayJshVO epvo) throws Throwable {
		
		String ano = dao.insertno();
		epvo.setAno(ano);
		
		int n1=0, n2=0, n3=0; 
		// 2) 전자결재 테이블에 insert
		 n1 = dao.ElectricSave(epvo);
		 
		if(n1==1) {
			// 3) ncatname 조건에 따라 insert 시켜줌 일반결재테이블에 insert  
		 n2 = dao.Generaladd(epvo);
			
		  }
		if(n2==1) {
		// 4) ncatname 조건에 따라 (회의록,위임장,외부공문,협조공문) 테이블에 insert 시켜줌 
			 n3 =dao.selectadd(epvo);
		}
		return n3;
	}
	
	//임시저장함 insert-첨부파일O
	@Override
	public int savePayment_withFile(ElectronPayJshVO epvo) throws Throwable {
		
		String ano = dao.insertno();
		epvo.setAno(ano);
		
		int n1=0, n2=0, n3=0; 
		// 2) 전자결재 테이블에 insert
		 n1 = dao.ElectricSave_withFile(epvo);
		 
		if(n1==1) {
			// 3) ncatname 조건에 따라 insert 시켜줌 일반결재테이블에 insert  
		 n2 = dao.Generaladd(epvo);
			
		  }
		if(n2==1) {
		// 4) ncatname 조건에 따라 (회의록,위임장,외부공문,협조공문) 테이블에 insert 시켜줌 
			 n3 =dao.selectadd(epvo);
		}
		return n3;
	}

	
	
	
	
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//지출결재
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	
	//페이징 처리한 글목록 가져오기(검색이 있든지, 검색이 없든지 모두 다 포함한 것)
	@Override
	public List<ElectronPayJshVO> expListSearchWithPaging(Map<String, String> paraMap) {
		List<ElectronPayJshVO> expList = dao.expListSearchWithPaging(paraMap);
		return expList;
	}

	// 총 게시물 건수(totalCount) 구하기 - 검색이 있을때와 검색이 없을때로 나뉜다.
	@Override
	public int expGetTotalCount(Map<String, String> paraMap) {
		int n = dao.expGetTotalCount(paraMap);
		return n;
	}

	//검색어 입력시 자동글 완성하기
	@Override
	public List<String> expWordSearchShow(Map<String, String> paraMap) {
		List<String> wordList = dao.expWordSearchShow(paraMap);
		return wordList;
	}

	// 하나의 전자결재내역 문서 보여주기
	@Override
	public ElectronPayJshVO expOneView(Map<String, String> paraMap) {
		ElectronPayJshVO epvo =dao.expOneView(paraMap);
		return epvo;
	}

	

	

	
	
}
