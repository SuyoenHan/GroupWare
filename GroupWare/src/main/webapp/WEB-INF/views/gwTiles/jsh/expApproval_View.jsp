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
		<h3 align="center">${requestScope.scatname}</h3>
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
				<th>문서번호</th>
				<td>${epvo.ano}</td>
			</tr>
			
			<tr >
				<th>제목</th>
				<td colspan="3">${epvo.atitle}</td>
			</tr>
		
		
			<c:if test="${requestScope.scatname eq '지출결의서'}">
				<tr>
					<th>지출일자</th>
					<td colspan="3">${epvo.exdate}</td>
				</tr>
				<tr>
					<th>지출금액</th>
					<td colspan="3">${epvo.exprice}</td>
				</tr>
			</c:if>
		
			<c:if test="${requestScope.scatname eq '법인카드사용신청서'}">
				<tr>
					<th>사용예정일</th>
					<td colspan="3">${epvo.codate}</td>
				</tr>
				<tr>
					<th>카드번호</th>
					<td colspan="3">${epvo.cocardnum}</td>
				</tr>
				<tr>
					<th>예상금액</th>
					<td>${epvo.coprice}</td>
					<th>지출목적</th>
					<td>
						<c:if test="${requestScope.epvo.copurpose eq '1'}">교통비</c:if>
						<c:if test="${requestScope.epvo.copurpose eq '2'}">사무비품</c:if>
						<c:if test="${requestScope.epvo.copurpose eq '3'}">주유비</c:if>
						<c:if test="${requestScope.epvo.copurpose eq '4'}">출장비</c:if>	
						<c:if test="${requestScope.epvo.copurpose eq '5'}">식비</c:if>
						<c:if test="${requestScope.epvo.copurpose eq '6'}">기타</c:if>
					</td>
				</tr>
				
			</c:if>
		
			<tr>
				<th>결재상태</th>
				<c:if test="${epvo.astatus==0}">
					<td colspan="3">제출</td>
				</c:if>
				<c:if test="${epvo.astatus==1}">
					<td colspan="3">결재진행중</td>
				</c:if>
				<c:if test="${epvo.astatus==2}">
					<td colspan="3">반려</td>
				</c:if>
				<c:if test="${epvo.astatus==3}">
					<td colspan="3">승인완료</td>
				</c:if>
				
			</tr>
			
			<tr>
				<th style="height:350px;">글내용</th>
				<td colspan="3">${epvo.acontent}</td>
			</tr>
			
			
			
			<tr>
				<th>첨부파일</th>
				<td colspan="3">
					<c:if test="${not empty epvo.orgFilename}">
					   <a href ="<%= ctxPath%>/download1.tw?ano=${requestScope.epvo.ano}">${requestScope.epvo.orgFilename}</a>
					   
					</c:if>
					
					<c:if test="${empty epvo.orgFilename}">
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

  
	<button type="button" onclick="javascript:location.href='<%=ctxPath %>/t1/expApproval_List.tw'" >전체목록보기</button>
     <button type="button" onclick="javascript:location.href='<%=ctxPath %>/${requestScope.gobackURL}'" >검색결과목록보기</button>

</div>