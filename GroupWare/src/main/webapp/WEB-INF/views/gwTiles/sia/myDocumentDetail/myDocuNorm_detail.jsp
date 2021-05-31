<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<% String ctxPath = request.getContextPath(); %>

<style>

table, th, td {border: solid 1px gray;}

#table1 {
	float:right; 
	width: 300px; 
	border-collapse: collapse;
	margin-right: 200px;
	margin-bottom: 80px;
}
    
th{
	background-color: #DDD;
}

#table2 {
	width: 80%;
	height: 700px;
	margin: 50px auto;
}

</style>

<script type="text/javascript">
	$(document).ready(function(){
		
		
	});
</script>

<div id="containerview">
	<hr style="border: 2px gray;">
		<h3 align="center"></h3>
	<hr>
	<br>
	<div id="astatus" >
		<table id="table1">
		
			<tr>
				<th style="width:100px; height:70px; ">대리</th>
				<th>부장</th>
				<th>사장</th>
			</tr>
			
			<tr>
				<td style="height:70px;">d</td>
				<td>d</td>
				<td>d</td>
			</tr>
		</table>
		
		<table id="table2">
			<tr>
				<th style="width:200px;">수신참조</th>
				<td></td>
			</tr>
			
			<tr>
				<th>제목</th>
				<td></td>
			</tr>
			
			
			<tr>
				<th>작성자</th>
				<td></td>
			</tr>
			
			<tr>
				<th>소속</th>
				<td></td>
			</tr>
		<%-- 	
			<c:if test="${requestScope.ncatname eq '회의록'}">
				<tr>
					<th>회의시간</th>
					<td>${epvo.mdate}</td>
				</tr>
			</c:if>
			<c:if test="${requestScope.ncatname eq '위임장'}">
				<tr>
					<th>위임기간</th>
					<td>${epvo.fk_wiimdate}</td>
				</tr>
			</c:if>
			<c:if test="${requestScope.ncatname eq '협조공문'}">
				<tr>
					<th>타회사명</th>
					<td>${epvo.comname}</td>
				</tr>
			</c:if>
		--%>	
			<tr>
				<th>문서상태</th>
				<td></td>
			</tr>
			
			<tr>
				<th>문서번호</th>
				<td></td>
			</tr>
			
			<tr>
				<th style="height:350px;">글내용</th>
				<td></td>
			</tr>
			
			<tr>
				<th>첨부파일</th>
				<td></td>
			</tr>			
			
		</table>
	</div>
</div>