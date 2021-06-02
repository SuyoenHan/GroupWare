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

</style>

<script type="text/javascript">
	$(document).ready(function(){
		
		
	});
</script>

<div id="containerview">	
	<h2 style="font-size: 20pt; font-weight: bold;" align="center">${requestScope.avo.ncatname}</h2>
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
				<td style="height:70px;">d</td>
				<td>d</td>
				<td>d</td>
			</tr>
		</table>
		
		<table id="table2">
			<tr>
				<th style="width:150px;">수신참조</th>
				<td>${requestScope.avo.arecipient1}</td>
			</tr>
			
			<tr>
				<th>제목</th>
				<td>${requestScope.avo.atitle}</td>
			</tr>
			
			
			<tr>
				<th>작성자</th>
				<td>${requestScope.avo.name}</td>
			</tr>
			
			<tr>
				<th>소속</th>
				<td>${requestScope.avo.dname}</td>
			</tr>
			
			<c:if test="${requestScope.ncat eq '1'}">
				<tr>
					<th>회의시간</th>
					<td>${requestScope.avo.mdate}</td>
				</tr>
			</c:if>
			<c:if test="${requestScope.ncat eq '2'}">
				<tr>
					<th>위임기간</th>
					<td>${requestScope.avo.fk_wiimdate}</td>
				</tr>
			</c:if>
			<c:if test="${requestScope.ncat eq '3'}">
				<tr>
					<th>타회사명</th>
					<td>${requestScope.avo.comname}</td>
				</tr>
			</c:if>
			<tr>
				<th>문서상태</th>
				<td>${requestScope.avo.apaper}</td>
			</tr>
			
			<tr>
				<th>문서번호</th>
				<td>${requestScope.avo.ano}</td>
			</tr>
			
			<tr>
				<th style="height:250px;">글내용</th>
				<td>${requestScope.avo.acontent}</td>
			</tr>
			
			<tr>
				<th>첨부파일</th>
				<td>${requestScope.avo.afile}</td>
			</tr>
		</table>
	</div>
</div>