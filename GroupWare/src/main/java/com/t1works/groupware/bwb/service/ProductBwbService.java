package com.t1works.groupware.bwb.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.t1works.groupware.bwb.model.InterProductBwbDAO;
import com.t1works.groupware.bwb.model.MemberBwbVO;
import com.t1works.groupware.bwb.model.ProductBwbVO;

@Component
@Service
public class ProductBwbService implements InterProductBwbService {
	
	 @Autowired
     private InterProductBwbDAO dao; 
	 
	// 미배정 업무정보 가져오기
	 @Override
	 public List<ProductBwbVO> selectProudctList(Map<String, String> paraMap) {
		
		List<ProductBwbVO> productList = dao.selectProudctList(paraMap);
		return productList;
	 }
	 
	// 미배정 업무 총 갯수 구해오기
	@Override
	public int selectNoAssignedProduct(String employeeid) {
		
		int totalProduct = dao.selectNoAssignedProduct(employeeid);
		
		return totalProduct;
	}
	
	// 부장의 해당부서팀의 직원들 가져오기
	@Override
	public List<MemberBwbVO> selectMemberList(Map<String, String> paraMap2) {
		
		List<MemberBwbVO> memberList = dao.selectMemberList(paraMap2);
		return memberList;
	}
	
	// 업무테이블에서 배정자 update해주기
	@Override
	public int goAssign(Map<String, String> paraMap) {

		int n = dao.goAssign(paraMap);
		return n;
	}
	
	// 배정된 업무 가져오기
	@Override
	public List<ProductBwbVO> selectAssignedList(Map<String, String> paraMap) {
		
		List<ProductBwbVO> assignedList = dao.selectAssignedList(paraMap);
		return assignedList;
	}
	
	// 배정 업무 총 갯수 구해오기(totalProduct)
	@Override
	public int selectAssignedTotal(String employeeid) {
		
		int totalProduct = dao.selectAssignedTotal(employeeid);
		
		return totalProduct;
	}
	
	// 배정된 부서 업무 모두 뽑아오기((기간,검색어 허용))
	@Override
	public List<ProductBwbVO> selectAllDepartmentToDo(Map<String, String> paraMap) {
		
		List<ProductBwbVO> productList = dao.selectAllDepartmentToDo(paraMap);
		return productList;
	}
	
	// 부서 총 업무 갯수알아오기
	@Override
	public int selectdepartProduct(Map<String, String> paraMap) {
		
		int departProduct = dao.selectdepartProduct(paraMap);
		return departProduct;
	}
	
	// === #2. 특정 업무 클릭 시 modal창의 header정보 가져오기
	@Override
	public ProductBwbVO deptgetOneInfoheader(String pNo) {
		
		ProductBwbVO pvo = dao.deptgetOneInfoheader(pNo);
		
		String period = pvo.getPeriod();
		if(period.equalsIgnoreCase("0")) {
			pvo.setPeriod("당일 여행");
		}
		else {
			pvo.setPeriod((Integer.parseInt(pvo.getPeriod()))+"박"+(Integer.parseInt(pvo.getPeriod())+1)+"일");
		}
		
		String endDate_es = pvo.getEndDate();
		String[] endDateArr = endDate_es.split("-");

		String year = endDateArr[0];
		String month = endDateArr[1];
		String day = endDateArr[2];
		
		if(month.length()==1) {
			month="0"+month;
		}
		
		if(day.length()==1) {
			day="0"+day;
		}
		
		pvo.setEndDate(year+"년"+month+"월"+day+"일");
		
		
		String startDate_es = pvo.getStartDate();
		String[] startDateArr = startDate_es.split("-");
		
		year = startDateArr[0];
		month = startDateArr[1];
		day = startDateArr[2];
		
		if(month.length()==1) {
			month="0"+month;
		}
		
		if(day.length()==1) {
			day="0"+day;
		}
		
		pvo.setStartDate(year+"년"+month+"월"+day+"일");

		return pvo;
	}
	
	// 부서업무 중 특정업무에 대한 페이징처리해서 고객리스트 뽑아오기
	@Override
	public List<ProductBwbVO> selectClient(Map<String, String> paraMap) {
		
		List<ProductBwbVO> clientList = dao.selectClient(paraMap);
		
		return clientList;
	}
	
	// 해당 상품에 대한 고객명단의 총 페이지수 알아오기
	@Override
	public int selectTotalClient(Map<String, String> paraMap) {
		
		int totalPage = dao.selectTotalClient(paraMap);
		
		return totalPage;
	}
	
	//CS1,2,3팀의 실적 건수 가지고 오기
	@Override
	public int selectDoneCount(String string) {
		
		int team_DoneCount = dao.selectDoneCount(string);
		return team_DoneCount;
	}
	
	// 해당부서의 실적의 가장 예전 날짜, 최근 날짜 가지고 오기
	@Override
	public Map<String, String> selectOldNewDate(String dcode) {
		
		Map<String, String> paraMap = dao.selectOldNewDate(dcode);
		String minEndDate = paraMap.get("minEndDate"); //2020-05
		String maxEndDate = paraMap.get("maxEndDate");
		
		String minYear  = minEndDate.substring(0, 4); //2020
		String minMonth = minEndDate.substring(5);  // 05

		String maxYear  = maxEndDate.substring(0, 4);
		String maxMonth = maxEndDate.substring(5);
		

		paraMap.put("minYear", minYear);
		paraMap.put("minMonth", minMonth);
		paraMap.put("maxYear", maxYear);
		paraMap.put("maxMonth", maxMonth);
		
		return paraMap;
	}
	
	// 선택한 년,월에 해당하는 실적 데이터 가지고 오기
	@Override
	public List<ProductBwbVO> selectPerformance(Map<String, String> paraMap) {
		
		String selectedMonth = paraMap.get("selectedMonth");
		String selectedYear = paraMap.get("selectedYear");

		String finalDate = selectedYear+"-"+selectedMonth;
		
		// System.out.println(finalDate); 2021-2
		
		int lastMonth = Integer.parseInt(selectedMonth)-2;
		int lastYear = Integer.parseInt(selectedYear);
		
		if(lastMonth<=0) {
			lastMonth  = lastMonth+12;
			lastYear = lastYear-1;
		}
		
		// 기준년월 - 3월 만들어주기
		String lastDate = lastYear+"-"+lastMonth;
		
		// System.out.println(lastDate); 2020-11
		
		paraMap.put("finalDate", finalDate);
		paraMap.put("lastDate", lastDate);
		
		// 선택한 년,월에 해당하는 실적 데이터 가지고 오기
		List<ProductBwbVO> performanceList = dao.selectPerformance(paraMap);
		
		return performanceList;
	}
	
	// chart에 들어가기 위한 1개 부서에 대한 name값,3개월에 대한 직원 각각 실적건수
	@Override
	public Map<String, String> selectCntPerformance(Map<String, String> paraMap) {
		
		Map<String, String> resultMap = dao.selectCntPerformance(paraMap);
		return resultMap;
	}
	
	// 선택날짜를 가지고 -1달,-2달 값 가지고 오기
	@Override
	public Map<String, String> changeDate(String firstDate) {
		
		Map<String, String> paraMap = dao.changeDate(firstDate);
		return paraMap;
	}
	
	// chart에 들어가기 위한 부서 name값,3개월에 대한 부서 각각 실적건수
	@Override
	public Map<String, String> selectDepCntPerformance(Map<String, String> paraMap) {
		
		Map<String, String> resultMap = dao.selectDepCntPerformance(paraMap);
		
		String sDCnt = resultMap.get("DCnt"); 
		String sprevDCnt = resultMap.get("prevDCnt");
		
		int DCnt = Integer.parseInt(sDCnt);
		int prevDCnt = Integer.parseInt(sprevDCnt);
		
		
		String compareValue = "";
		int icompareValue = 0; 
		
		if(prevDCnt==0) {
			compareValue="전달 실적이 없습니다.";
		}
		else {
			icompareValue = ((DCnt/prevDCnt)-1)*100;
			compareValue = "약 "+String.valueOf(icompareValue)+"%";
		}
		
		resultMap.put("compareValue", compareValue);
		
		return resultMap;
	}
	
	
	

}
