package com.t1works.groupware.jsh.service;



import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


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
	public ElectronPayJshVO login_Write(Map<String, String> paraMap) {
		ElectronPayJshVO write_view = dao.login_Write(paraMap);
		return write_view;
	}

	//전자결재테이블 insert
	@Override
	public int Electricadd(ElectronPayJshVO epvo) {
		int n = dao.Electricadd(epvo);
		return n;
	}

	
	
}
