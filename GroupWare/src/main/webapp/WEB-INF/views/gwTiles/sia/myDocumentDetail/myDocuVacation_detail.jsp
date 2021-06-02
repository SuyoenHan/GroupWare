<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<% String ctxPath = request.getContextPath(); %>

<style>
div#containerview{
	margin: 30px 0px 30px 50px;
	width: 80%;
}
table, th, td {border: solid 1px gray;}

#table1 {
	float: right; 
	width: 300px; 
	border-collapse: collapse;
	margin-right: 200px;
	margin-bottom: 50px;
}

#table1 th, #table1 td{
	text-align: center;
}
#table1 th {
	background-color: #395673; 
	color: #ffffff;
}
#table2 th {
	width: 150px;
}

th{
	background-color: #ccd9e6;
	padding: 7px;
}
td{
	padding: 7px;
}

#table2 {
	width: 70%;
	margin: 50px auto;
}
input.btn {
	width: 70px;
	border-radius: 0;
	font-weight: bold;
}

</style>

<script type="text/javascript">
	
	$(document).ready(function(){
		
	});
	
	
	// Function Declaration
	function goback(){
		var $myFrm= document.myFrm;
		$myFrm.method="POST";
		$myFrm.action="<%=ctxPath%>/t1/myDocuVacation_rec.tw";
		$myFrm.submit();
	}
	

</script>

<div id="containerview">	
	<h2 style="font-size: 20pt; font-weight: bold;" align="center">${requestScope.avo.vcatname}계</h2>
	<hr style="background-color: #395673; height: 1.5px; width: 80%;">
	<br>
	<div id="astatus">
		<table id="table1">		
			<tr>
				<th style="width:100px; height:40px;">대리</th>
				<th>부장</th>
				<th>사장</th>
			</tr>			
			<tr>
				<td style="height:70px;"><img src="<%= ctxPath%>/resources/images/sia/approval_1.png" width="35px;"></td>
				<td><img src="<%= ctxPath%>/resources/images/sia/approval_2.png" width="35px;"></td>
				<td><img src="<%= ctxPath%>/resources/images/sia/approval_3.png" width="35px;"></td>
			</tr>
		</table>
		
		<table id="table2">
			<tr>
				<th>문서상태</th>
				<td style="width: 35%;">
					<c:if test="${requestScope.avo.astatus eq '0'}">제출</c:if>
					<c:if test="${requestScope.avo.astatus eq '1'}">결재진행중</c:if>
					<c:if test="${requestScope.avo.astatus eq '2'}">반려</c:if>
					<c:if test="${requestScope.avo.astatus eq '3'}">승인완료</c:if>					
				</td>
				<th>문서번호</th>
				<td>${requestScope.avo.ano}</td>
			</tr>
			<tr>
				<th>제목</th>
				<td colspan="3">${requestScope.avo.atitle}</td>
			</tr>
			
			<c:if test="${requestScope.avo.vno eq '1'}"> 
				<tr>
					<th>요청기간</th>
					<td colspan="3">${requestScope.avo.slstart} - ${requestScope.avo.slend}&nbsp;&nbsp;&nbsp;[사용일수: <span style="color: blue; font-weight: bold;">${requestScope.avo.sldates}</span>일]</td>
				</tr>				
			</c:if>
			<c:if test="${requestScope.avo.vno eq '2'}">
				<tr>
					<th>요청기간</th>
					<td colspan="3">${requestScope.avo.afdate}&nbsp;&nbsp;
					<span style="color: blue; font-weight: bold;"><c:if test="${requestScope.avo.afdan eq '1'}">오전</c:if><c:if test="${requestScope.avo.afdan eq '2'}">오후</c:if></span>반차
					</td>
				</tr>
			</c:if>
			<c:if test="${requestScope.avo.vno eq '3'}">
				<tr>
					<th>요청기간</th>
					<td colspan="3">${requestScope.avo.daystart} - ${requestScope.avo.dayend}&nbsp;&nbsp;&nbsp;[사용일수: <span style="color: blue; font-weight: bold;">${requestScope.avo.daydates}</span>일]</td>
				</tr>
			</c:if>
			<c:if test="${requestScope.avo.vno eq '4'}">
				<tr>
					<th>요청기간</th>
					<td colspan="3">${requestScope.avo.congstart} - ${requestScope.avo.congend}&nbsp;&nbsp;&nbsp;[사용일수: <span style="color: blue; font-weight: bold;">${requestScope.avo.congdates}</span>일]</td>
				</tr>
			</c:if>
			<c:if test="${requestScope.avo.vno eq '5'}">
				<tr>
					<th>출장기간</th>
					<td colspan="3">${requestScope.avo.bustart} - ${requestScope.avo.buend}</td>
				</tr>
				<tr>
					<th>출장지</th>
					<td>${requestScope.avo.buplace}</td>				
					<th>출장인원</th>
					<td>${requestScope.avo.bupeople}</td>
				</tr>
			</c:if>
			<c:if test="${requestScope.avo.vno eq '6'}">
				<tr>
					<th>요청시간</th>
					<td colspan="3">${requestScope.avo.ewdate}시간</td>
				</tr>
			</c:if>	
			
			<tr>
				<th style="height:250px;">글내용</th>
				<td colspan="3">${requestScope.avo.acontent}</td>
			</tr>
				
			<tr>
				<th>첨부파일</th>
				<td colspan="3">${requestScope.avo.afile}</td>
			</tr>
		</table>
			
		<div align="center">상기와 같은 내용으로 <span style="font-weight: bold;">${requestScope.avo.vcatname}계</span> 을(를) 제출하오니 재가바랍니다.</div>
		<div align="right" style="margin: 4px 0; margin-right: 15%;">기안일: ${requestScope.avo.asdate}</div>
		<div align="right" style="margin-right: 15%;">신청자: ${requestScope.avo.dname} ${requestScope.avo.name}</div>
		
		<div style="margin-top: 20px;">
			<span style="margin-left: 15%">
				<input type="button" class="btn" onclick="goback();" value="목록"/>
			</span>
			<span style="margin-left: 50%">
				<input type="button" class="btn btn-primary" value="결재"/>
				<input type="button" class="btn btn-warning" value="보류"/>
				<input type="button" class="btn btn-danger" value="반려"/>
			</span>
		</div>		
	</div>
	
	<form name="myFrm">
		<input type="hidden" name="ano" value="${ano}" />
		<input type="hidden" name="vcatname" value="${vcatname}" />		
		<input type="hidden" name="astatus" value="${astatus}" />
		<input type="hidden" name="fromDate" value="${fromDate}" />
		<input type="hidden" name="toDate" value="${toDate}" />
		<input type="hidden" name="vno" value="${vno}" />
		<input type="hidden" name="sort" value="${sort}" />
		<input type="hidden" name="searchWord" value="${searchWord}" />
		<input type="hidden" name="currentShowPageNo" value="${currentShowPageNo}" />
	</form>
</div>