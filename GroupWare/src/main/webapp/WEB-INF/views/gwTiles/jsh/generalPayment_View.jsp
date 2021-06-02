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

.log {
	width: 80%;
	height: 100px;
	margin: 50px auto;
}

</style>

<script type="text/javascript">
		$(document).ready(function(){
			//사이드바 세부메뉴 나타내기 
		//	$("div#submenu3").show();
			
			
			
		});
</script>

<div id="containerview">
	<hr style="border: 2px gray;">
		<h3 align="center">${requestScope.ncatname}</h3>
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
				<td>${requestScope.epvo.arecipient1}</td>
			</tr>
			
			<tr>
				<th>제목</th>
				<td>${epvo.atitle}</td>
			</tr>
		
			
			<tr>
				<th>작성자</th>
				<td>${epvo.name}</td>
			</tr>
		
			<tr>
				<th>소속</th>
				<td>${epvo.dname}</td>
			</tr>
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
			<tr>
				<th>결재상태</th>
				<c:if test="${epvo.astatus==0}">
					<td>제출</td>
				</c:if>
				<c:if test="${epvo.astatus==1}">
					<td>결재진행중</td>
				</c:if>
				<c:if test="${epvo.astatus==2}">
					<td>반려</td>
				</c:if>
				<c:if test="${epvo.astatus==3}">
					<td>승인완료</td>
				</c:if>
				
			</tr>
			
			<tr>
				<th>문서번호</th>
				<td>${epvo.ano}</td>
			</tr>
			
			<tr>
				<th style="height:350px;">글내용</th>
				<td>${epvo.acontent}</td>
			</tr>
			
			<tr>
				<th>첨부파일</th>
				<td>
					<c:if test="${not empty epvo.afile}">
					   ${epvo.afile}
					</c:if>
					
					<c:if test="${empty epvo.afile}">
					   <div align="center">첨부파일이 존재하지 않습니다.</div>
					</c:if>
				</td>
			</tr>
		
		
		</table>
		
		<table class="log">
			<tr>
				<th style="width:20%;">결재로그</th>
				<td>
				
				
				</td>
			</tr>
		</table>
		<table class="log">
			<tr>
				<th style="width:20%;">결재의견</th>
				<td>
					<c:if test="${not empty requestScope.opinionList}">
						<c:forEach var="ovo" items="${requestScope.opinionList}">
						  <div>${ovo.dname}&nbsp;${ovo.name}&nbsp;${ovo.pname}<span>[</span>${ovo.odate}<span>]</span></div>
						  <div>${ovo.ocontent}</div>
						</c:forEach>
					</c:if>
					
					<c:if test="${empty requestScope.opinionList}">
					  <div> 의견이 존재하지 않습니다. </div>
					</c:if>
				</td>
			</tr>
		</table>
		
	</div>

<input type="button"  style="margin-top:10px; "onclick="location.href='<%= ctxPath%>/t1/generalPayment_List.tw'"  value="전체목록"/>    
	<button type="button" onclick="javascript:location.href='<%=ctxPath %>/t1/generalPayment_List.tw'" >전체목록보기</button>
     <button type="button" onclick="javascript:location.href='<%=ctxPath %>/${requestScope.gobackURL}'" >검색결과목록보기</button>

</div>