<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String ctxPath= request.getContextPath(); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
 
<style type="text/css">

	div#salaryDetailContainer{
		border: solid 0px red;
		width:65%;
		margin: 0 auto;
		position: relative;
		left: -180px;
		padding: 80px 0px 0px 80px;
	}
	
	div#salaryDetailTitle{
		border: solid 0px red;
		font-size: 30pt;
		font-weight: bold;
		margin-bottom:50px;
	}
	
	table.table-striped{
		text-align:center;
		margin: 0 auto;
		margin-bottom:50px;
		border: solid 1px #ddd;
		border-collapse: collapse;
	}
	
	table.table-striped th, table.table-striped td{
		border: solid 1px #e6e6e6;
		height: 70px;
		vertical-align: middle !important;
		text-align: center;
		font-size:12pt;
	}
	
</style>   

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript">

	$(document).ready(function(){
		
		// 정상출근 일 수 구하기 => 해당 년도, 월의 평일 수에서 휴가사용일 제외 
		// 1) 평일 수
		var workDays= Number("${workDays}"); 
		
		// 2) 사용한 휴가 수 및 지각 수 합계
		var daydates= Number("${attendanceMap.daydates}");
		var sldates= Number("${attendanceMap.sldates}");
		var afdates= Number("${attendanceMap.afdates}");
		var congdates= Number("${attendanceMap.congdates}");
		var lateday= Number("${attendanceMap.lateday}");
		
		var notWorkDays= daydates+sldates+afdates+congdates+lateday;
		
		// 3) 정상출근 일 수 계산해서 값 넣어주기
		var realWorkDays= workDays-notWorkDays;
		$("td#workDay").text(realWorkDays);
		
		
		// 식비구하기 => 해당 년도, 월 평일 수 * 5,000원
		var foodMoney= workDays*5000;
		$("td#foodMoney").html(foodMoney.toLocaleString('en')+"<br/><span style='font-size:10pt;'>("+workDays+" 일 * 5,000 원)</span>");
	
		
		// 야근수당 구하기 => 야근시간 * 10,000원
		var totalLateWorkTime= Number("${totalLateWorkTime}"); 
		
		if(totalLateWorkTime==0){ // 야근수당이 없는 경우
			$("td#overTimeWork").text("-");
		}
		else{ // 야근수당이 있는 경우
			overTimeMoney= totalLateWorkTime*10000;
			$("td#overTimeWork").html(overTimeMoney.toLocaleString('en')+"<br/><span style='font-size:10pt;'>("+totalLateWorkTime+" 시간 * 10,000 원)</span>");
		}
		
		
	}); // end of $(document).ready(function(){-------------

</script>


<div id="salaryDetailContainer">
 	<div id="salaryDetailTitle" align="center">${year}년&nbsp;${month}월&nbsp;급여명세서</div>
	
	<table class="table table-striped personalInfo">
	    <tr>
	      <th rowspan="3" style="width:15%; background-color:#e6e6e6; font-size:15pt;">인적사항</th>
	      <th style="width:15%;">사번</th>
	      <td>${mvo.employeeid}</td>
	      <th style="width:15%;">성명</th>
	      <td>${mvo.name}</td>
	    </tr>
	    <tr>
	      <th>소속</th>
	      <td>${mvo.dname}</td>
	      <th>직급</th>
	      <td>${mvo.pname}</td>
	    </tr>
	    <tr>
	      <th>급여총액</th>
	      <td>급여총액</td>
	      <th></th>
	      <td></td>
	    </tr>
	</table>
	
	<table class="table table-striped attendance">
	    <tr>
	      <th rowspan="2" style="width:15%; background-color:#e6e6e6; font-size:15pt;">근태내역</th>
	      <th>정상출근</th>
	      <td id="workDay"></td>
	      <th>연차휴가</th>
	      <td>${attendanceMap.daydates}</td>
	      <th>병가</th>
	      <td>${attendanceMap.sldates}</td>
	    </tr>
	    <tr>
	      <th>지각</th>
	      <td>${attendanceMap.lateday}</td>
	      <th>반차휴가</th>
	      <td>${attendanceMap.afdates}</td>
	      <th>경조휴가</th>
	      <td>${attendanceMap.congdates}</td>
	    </tr>
	</table>
	
	<table class="table table-striped salaryDetail">
	    <tr>
	      <th rowspan="4" style="width:15%; background-color:#e6e6e6; font-size:15pt;">세부내역</th>
	      <th>항목</th>
	      <th>금액</th>
	      <th>항목</th>
	      <th>금액</th>
	    </tr>
	    <tr>
	      <th style="width:15%;">기본급</th>
	      <td><fmt:formatNumber value="${mvo.salary}" pattern="#,###" /></td>
	      <th style="width:15%;">근속수당</th>
	      <td>
	      	<c:if test="${continueYear ne 0}">
	      		1,000,000<br/>
	      		<span style='font-size:10pt;'>(${continueYear}&nbsp;년차)</span>
	      	</c:if>
	      	<c:if test="${continueYear eq 0}">-</c:if>
	      </td>
	    </tr>
	    <tr>
	      <th>야근수당</th>
	      <td id='overTimeWork'></td>
	      <th>상여금</th>
	      <td>상여금</td>
	    </tr>
	    <tr>
	      <th>식대</th>
	      <td id="foodMoney"></td>
	      <th>지급합계</th>
	      <td>지급합계</td>
	    </tr>
	</table>
	
	<div align="center">
		<span style="font-size: 22pt; font-weight: bold;">귀하의 노고에 감사드립니다</span><br/><br/>
		<span style="font-size: 13pt;">${currentYear}년&nbsp;${currentMonth}월&nbsp;${currentDay}일</span>
	</div>
	
	<div align="right" style="margin:70px 0px 30px 0px;">
		<span style="font-size: 18pt; font-weight: bold;">T1WORKS</span>
		<img src="<%=ctxPath%>/resources/images/login/t1works_icon.png" width="100px" height="100px" style="position:relative; top:-20px;" />
	</div>
	
</div>
