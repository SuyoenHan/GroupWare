package com.t1works.groupware.jsh.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.ResultType;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public class ElectronPayJshDAO implements InterElectronPayJshDAO {


	@Resource
	 private SqlSessionTemplate sqlsession6;  //원격 DB에 연결
	 

	
	
	
	///////////////////////////////////////////////////////////////////////
	@Override
	// 일반결재내역 목록 보여주기
		public List<ElectronPayJshVO> generalPayment_List() {
			List<ElectronPayJshVO> electronList = sqlsession6.selectList("payment_Jsh.generalPayment_List");         
			return electronList;
		}



	// 일반결재내역 페이징 처리를 안한 검색어가 있는 전체 글목록 보여주기 == //
	@Override
	public List<ElectronPayJshVO> electronListSearch(Map<String, String> paraMap) {
		List<ElectronPayJshVO> electronList = sqlsession6.selectList("payment_Jsh.electronListSearch",paraMap);         
		return electronList;
	}


	//검색어 입력시 자동글 완성하기
	@Override
	public List<String> wordSearchShow(Map<String, String> paraMap) {
		 List<String> wordList = sqlsession6.selectList("payment_Jsh.wordSearchShow", paraMap);
		return wordList;
	}


	// 총 게시물 건수(totalCount) 구하기 - 검색이 있을때와 검색이 없을때로 나뉜다.
	@Override
	public int getTotalCount(Map<String, String> paraMap) {
		int n =sqlsession6.selectOne("payment_Jsh.getTotalCount", paraMap);
		return n;
	}



	 //페이징 처리한 글목록 가져오기(검색이 있든지, 검색이 없든지 모두 다 포함한 것)
	@Override
	public List<ElectronPayJshVO> electronListSearchWithPaging(Map<String, String> paraMap) {
		List<ElectronPayJshVO> electronList = sqlsession6.selectList("payment_Jsh.electronListSearchWithPaging",paraMap);
		return  electronList ;
	}



	// 하나의 일반결재내역 문서 보여주기
	@Override
	public ElectronPayJshVO generalOneView(Map<String, String> paraMap) {
		ElectronPayJshVO epvo =sqlsession6.selectOne("payment_Jsh.generalOneView",paraMap);
		return epvo;
	}


	//하나의 일반결재내역에서 결재의견 목록 보여주기
	@Override
	public List<ElectronPayJshVO> oneOpinionList(Map<String, String> paraMap) {
		List<ElectronPayJshVO> opinionList = sqlsession6.selectList("payment_Jsh.oneOpinionList",paraMap);
		return opinionList;
	}



	//일반결재 글쓰기
	@Override
	public ElectronPayJshVO WriteJsh(Map<String, String> paraMap) {
		ElectronPayJshVO write_view =sqlsession6.selectOne("payment_Jsh.WriteJsh",paraMap);
		return write_view;
	}

	// 수신자 정보 select해오기
	@Override
	public ElectronPayJshVO mWriteJsh(HashMap<String, String> paraMap) {
		ElectronPayJshVO write_mview =sqlsession6.selectOne("payment_Jsh.mWriteJsh",paraMap);
		return write_mview;
	}
	
	
	// 1) insert될 문서번호를 알아온다
	@Override
	public String insertno() {
		String ano = sqlsession6.selectOne("payment_Jsh.insertno");
		return ano;
	}

	//전자결재테이블 insert
	@Override
	public int Electricadd(ElectronPayJshVO epvo) {
		int n1 = sqlsession6.insert("payment_Jsh.Electricadd",epvo);
		return n1;
	}


	// 3) ncatname 조건에 따라 insert 시켜줌 일반결재테이블에 insert  
	@Override
	public int Generaladd(ElectronPayJshVO epvo) {
		int n2 = sqlsession6.insert("payment_Jsh.Generaladd",epvo);
		return n2;
	}


	// 4) ncatname 조건에 따라 (회의록,위임장,외부공문,협조공문) 테이블에 insert 시켜줌 
	@Override
	public int selectadd(ElectronPayJshVO epvo) {
		int n3 = sqlsession6.insert("payment_Jsh.selectadd",epvo);
		return n3;
	}


   //글쓰기 ( 첨부파일이 있는 경우 ) ===
	@Override
	public int Electricadd_withFile(ElectronPayJshVO epvo) {
		int n1 = sqlsession6.insert("payment_Jsh.Electricadd_withFile",epvo);
		return n1;
	}


	//임시저장함 insert-첨부파일X
	@Override
	public int ElectricSave(ElectronPayJshVO epvo) {
		int n1 = sqlsession6.insert("payment_Jsh.ElectricSave",epvo);
		return n1;
	}


	//임시저장함 insert-첨부파일X
	@Override
	public int ElectricSave_withFile(ElectronPayJshVO epvo) {
		int n1 = sqlsession6.insert("payment_Jsh.ElectricSave_withFile",epvo);
		return n1;
		
	}

	





	
	
	
	
}
