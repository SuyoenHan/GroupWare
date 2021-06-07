<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% 
	String ctxPath = request.getContextPath();
%>

<style>

</style>

<script>

	$(document).ready(function(){
		
		
		
		
	});

</script>

<div id="rentalHeader"> 
	<span>차량관리</span>
	<span>사무용품관리</span>
</div>

<div id="rentalCar">

	<table>
		<thead> 
			<tr> 
				<th>대여구분</th>
				<th>예약날짜</th>
				<th>시간</th>
				<th>차량명</th>
				<th>도착지</th>
				<th>신청자</th>
				<th>탑승자</th>
				<th>목적</th>
				<th>승인여부</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${empty carList}">
					미승인된 내역이 존재하지 않습니다.
			</c:if>
			<c:if test="${not empty carList}">
				<c:forEach var="carvo" items="${carList}">
				<tr> 
					<td>차량대여 <input type="hidden" value="${carvo.rscno}"/> </td>
					<td>${carvo.rcdate}</td>
					<td>${carvo.rctime}</td>
					<td>${carvo.carname}</td>
					<td>${carvo.rdestination}</td>
					<td>${carvo.name}</td>
					<td>${carvo.rcpeople}</td>
					<td>${carvo.rcsubject}</td>
					<td> <button type="button">승인</button> </td>
				</tr>
				</c:forEach>
			</c:if>
			
		</tbody>
		
	
	</table>

</div>