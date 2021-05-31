package com.t1works.groupware.hsy.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.t1works.groupware.hsy.model.ClientHsyVO;
import com.t1works.groupware.hsy.model.InterClientHsyDAO;
import com.t1works.groupware.hsy.model.InterProductHsyDAO;
import com.t1works.groupware.hsy.model.InterTwLocationHsyDAO;
import com.t1works.groupware.hsy.model.ProductHsyVO;
import com.t1works.groupware.hsy.model.TwLocationHsyVO;

@Component
@Service
public class ProductHsyService implements InterProductHsyService {

	@Autowired 
	private InterProductHsyDAO pdao;

	@Autowired 
	private InterClientHsyDAO cdao;

	@Autowired 
	private InterTwLocationHsyDAO tldao;

	
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
		
		if(pvo.getMiniNo().length()==1) {
			pvo.setMiniNo("0"+pvo.getMiniNo());
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


	// 회사위치테이블에 있는 정보 가져오기  
	@Override
	public List<TwLocationHsyVO> twLocationAjax() {

		List<TwLocationHsyVO> twLocationList= tldao.twLocationAjax();
		return twLocationList;
	} // end of public List<TwLocationHsyVO> twLocationAjax() {-------

	
	// 해당 고객명, 연락처에 일치하는 고객예약항목이 있는지 검사
	@Override
	public int checkClientMobile(Map<String,String> paraMap) {

		int n=cdao.checkClientMobile(paraMap);
		return n;
		
	} // end of public int checkClientMobile(String clientmobile) {


	// 특정고객의 예약정보를 가져오기
	@Override
	public List<ClientHsyVO> moreReserveListAjax(Map<String, String> paraMap) {
		
		List<ClientHsyVO> cvoList= cdao.moreReserveListAjax(paraMap);
		
		// 여행시작일과 여행 종료일 데이터 형태 바꿔주기
		for(ClientHsyVO cvo: cvoList) {
			
			// 1) 여행시작일 데이터 형태 바꿔주기
			String[] startDateArr= cvo.getStartDate().split("-");
			
			String year= startDateArr[0];
			String month= startDateArr[1];
			
			if(month.length()==1) { // 월이 한자리인 경우 앞에 "0" 붙여주기
				month="0"+month;
			}
			
			String day= startDateArr[2];
			if(day.length()==1) { // 일이 한자리인 경우 앞에 "0" 붙여주기
				day="0"+day;
			}
			
			cvo.setStartDate(year+"년 "+month+"월 "+day+"일");
			
			// 2) 여행종료일 데이터 형태 바꿔주기
			String[] endDateArr= cvo.getEndDate().split("-");
			
			year= endDateArr[0];
			month= endDateArr[1];
			
			if(month.length()==1) { // 월이 한자리인 경우 앞에 "0" 붙여주기
				month="0"+month;
			}
			
			day= endDateArr[2];
			if(day.length()==1) { // 일이 한자리인 경우 앞에 "0" 붙여주기
				day="0"+day;
			}
			
			cvo.setEndDate(year+"년 "+month+"월 "+day+"일");
			
			// 3) 전화번호 형태 바꿔주기
			String clientmobile=cvo.getClientmobile();
			clientmobile= clientmobile.substring(0, 3)+"-"+clientmobile.substring(3, 7)+"-"+clientmobile.substring(7);
			
			cvo.setClientmobile(clientmobile);
			
		} // end of for(ClientHsyVO cvo: cvoList) {------------
		
		return cvoList;
		
	} // end of public List<ClientHsyVO> moreReserveListAjax(Map<String, String> paraMap)-----


	// 해당 연락처의 예약정보 총 개수 구하기
	@Override
	public int getTotalReserveCount(String clientmobile) {

		int totalCount= cdao.getTotalReserveCount(clientmobile);
		return totalCount;
		
	}// end of public int getTotalCount(String clientmobile) {-----

	
	// 여행확정 상품인지 확인하기
	@Override
	public Map<String, String> checkProductStatus(String pNo) {
		
		Map<String,String> paraMap= pdao.checkProductStatus(pNo);
		return paraMap;
		
	} // end of public Map<String, String> checkProductStatus(String pNo)----

	
	// 고객테이블에 delete하고 제품테이블에 update (transaction처리)
	@Override
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor= {Throwable.class})
	public int deleteUpdateClientAjax(ClientHsyVO cvo) throws Throwable {
		
		int result= 0;
		
		// 1) 고객테이블에 해당예약건 delete
		int m= cdao.deleteClientAjax(cvo);
		//m==1 예약취소 성공
	
		if(m==1) { // 2) 예약취소가 제대로 된 경우 update
 			result= pdao.updateMinusNowNo(cvo);
		}
		
		return result;
		
		/*
	 		result==1 트랜잭션 처리 성공
	 		result==0 트랜잭션 처리 실패 
		*/
	
	} // end of public int deleteUpdateClientAjax(ClientHsyVO cvo) throws Throwable {----

	
	// 고객테이블에 update하고 제품테이블에 update (transaction처리)
	@Override
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor= {Throwable.class})
	public int changeCountAjax(ClientHsyVO cvo) throws Throwable {
	
		int result= 0;
		
		// 1) 고객테이블에 해당예약건 update
		int m= cdao.updateClientAjax(cvo);
		//m==1 예약취소 성공
	
		if(m==1) { // 2) 고객테이블의 update가 제대로 된 경우 update
 			result= pdao.updateNowNo(cvo);
		}
		
		return result;
		
		/*
	 		result==1 트랜잭션 처리 성공
	 		result==0 트랜잭션 처리 실패 
		*/
	
	} // end of public int changeCountAjax(ClientHsyVO cvo) throws Throwable {----

	
}
