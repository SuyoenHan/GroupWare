package com.t1works.groupware.hsy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.t1works.groupware.hsy.model.*;

@Component
@Service
public class ProductHsyService implements InterProductHsyService {

	@Autowired 
	private InterProductHsyDAO pdao;

	@Autowired 
	private InterClientHsyDAO cdao;

	
	// 여행사 홈페이지에서 보여줄 상품정보 가져오기
	@Override
	public List<ProductHsyVO> selectProductInfoForHome() {

		List<ProductHsyVO> pvoList= pdao.selectProductInfoForHome();
		
		// 여행시작일과 여행 종료일 데이터 형태 바꿔주기
		for(ProductHsyVO pvo: pvoList) {
			
			// 1) 여행시작일 데이터 형태 바꿔주기
			String[] startDateArr= pvo.getStartDate().split("-");
			
			String year= startDateArr[0];
			String month= startDateArr[1];
			
			if(month.length()==1) { // 월이 한자리인 경우 앞에 "0" 붙여주기
				month="0"+month;
			}
			
			String day= startDateArr[2];
			if(day.length()==1) { // 일이 한자리인 경우 앞에 "0" 붙여주기
				day="0"+day;
			}
			
			pvo.setStartDate(year+"년 "+month+"월 "+day+"일");
			
			
			// 2) 여행종료일 데이터 형태 바꿔주기
			String[] endDateArr= pvo.getEndDate().split("-");
			
			year= endDateArr[0];
			month= endDateArr[1];
			
			if(month.length()==1) { // 월이 한자리인 경우 앞에 "0" 붙여주기
				month="0"+month;
			}
			
			day= endDateArr[2];
			if(day.length()==1) { // 일이 한자리인 경우 앞에 "0" 붙여주기
				day="0"+day;
			}
			
			pvo.setEndDate(year+"년 "+month+"월 "+day+"일");
			
		} // end of for(ProductHsyVO pvo: pvoList) {-------------
		
		return pvoList;
	}

	
	// pNo 존재하는지 확인하기
	@Override
	public int isExistPno(String pNo) {
		int n= pdao.isExistPno(pNo);
		return n;
	} 

	
	// 상세페이지에 필요한 상품정보 알아오기
	@Override
	public ProductHsyVO selectOneProductInfo(String pNo) {
		
		ProductHsyVO pvo= pdao.selectOneProductInfo(pNo);
		
		// 1) 여행시작일 데이터 형태 바꿔주기
		String[] startDateArr= pvo.getStartDate().split("-");
		
		String year= startDateArr[0];
		String month= startDateArr[1];
		
		if(month.length()==1) { // 월이 한자리인 경우 앞에 "0" 붙여주기
			month="0"+month;
		}
		
		String day= startDateArr[2];
		if(day.length()==1) { // 일이 한자리인 경우 앞에 "0" 붙여주기
			day="0"+day;
		}
		
		pvo.setStartDate(year+"년 "+month+"월 "+day+"일");
		
		
		// 2) 여행종료일 데이터 형태 바꿔주기
		String[] endDateArr= pvo.getEndDate().split("-");
		
		year= endDateArr[0];
		month= endDateArr[1];
		
		if(month.length()==1) { // 월이 한자리인 경우 앞에 "0" 붙여주기
			month="0"+month;
		}
		
		day= endDateArr[2];
		if(day.length()==1) { // 일이 한자리인 경우 앞에 "0" 붙여주기
			day="0"+day;
		}
		
		pvo.setEndDate(year+"년 "+month+"월 "+day+"일");

		
		// 3) 예약인원 수 데이터 형태 바꿔주기
		if(pvo.getNowNo().length()==1) {
			pvo.setNowNo("0"+pvo.getNowNo());
		}
		
		if(pvo.getMaxNo().length()==1) {
			pvo.setMaxNo("0"+pvo.getMaxNo());
		}
		
		return pvo;
		
	}// end of public ProductHsyVO selectOneProductInfo(String pNo) {-----


	// 해당 상품에 예약이 가능한 인원수 인지 검사하기 
	@Override
	public int checkClientAjax(ClientHsyVO cvo) {
		
		int n= pdao.checkClientAjax(cvo);
		return n;
		/*
		 	n==1  예약가능
		 	n==0 예약불가능
		*/
		
	}// end of public int checkClientAjax(ClientHsyVO cvo) {------


	// 특정제품에 현재 예약가능한 인원수
	@Override
	public int getAvailableCount(String fk_pNo) {
		int count= pdao.getAvailableCount(fk_pNo);
		return count;
	}// end of public int getAvailableCount(String fk_pNo) {----
	
	
	// 해당 상품에 예약정보를 insert하고 상품테이블이 update하기 (transaction처리)
	@Override
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor= {Throwable.class})
	public int insertUpdateClientAjax(ClientHsyVO cvo) throws Throwable {
		
		int result= 0;
		int m= cdao.insertClientAjax(cvo);

		/*
		 	m==1 예약성공
		 	예약실패 시 (이미 같은 사람이름으로 해당 상품 예약한 값이 존재=> SQLException발생)
		*/
		
		if(m==1) { // 예약이 제대로 isnert된 경우
 			result= pdao.updateNowNo(cvo);
		}
		
		return result;
		
		/*
	 		result==1 트랜잭션 처리 성공
	 		result==0 트랜잭션 처리 실패 
		*/
		
	}// end of public int insertUpdateClientAjax(ClientHsyVO cvo) throws Throwable {----

	
	// 해당제품에 특정고객의 예약이 존재하는지 확인
	@Override
	public int isExistClientAjax(ClientHsyVO cvo) {
		
		int n= cdao.isExistClientAjax(cvo);
		return n;
	} // end of public int isExistClientAjax(ClientHsyVO cvo) {-----

}
