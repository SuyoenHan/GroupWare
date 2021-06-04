<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%	String ctxPath = request.getContextPath(); %>
<link rel="stylesheet" type="text/css" href="<%=ctxPath%>/resources/css/kdn/board.css"/>

<style type="text/css">
	
	.move {cursor: pointer;}
	.moveColor {color: #660029; font-weight: bold;}
	
	a {text-decoration: none !important;}
	
	td.comment {text-align: center;}
</style>

<script type="text/javascript">

$(document).ready(function(){
	
	//goReadComment(); // 페이징처리 안한 댓글 읽어오기
	//goViewComment(1); // 페이징처리 한 댓글 읽어오기
	
	$("span.move").hover(function(){
		                    $(this).addClass("moveColor");
		                }
                        ,function(){
                        	$(this).removeClass("moveColor");
                        });
	
});// end of $(document).ready(function(){})------------------
		
</script>

<div id="board-container">
	<a href="javascript:location.href='employeeBoard.tw'" style="text-decoration:none; color: black;"><i class="fas fa-bullhorn fa-lg"></i>&nbsp;<span style="display: inline-block; font-size:22px; margin-bottom: 40px;">공지사항</span></a>
	
	<c:if test="${not empty requestScope.boardvo}">
		<table id="post-table" class="table table-bordered">
			<tr class="thead">
				<th style="width: 100px;">작성자</th>
				<td>${requestScope.boardvo.name}</td>
			</tr>
			<tr class="thead">
				<th>작성일시</th>
				<td>${requestScope.boardvo.regDate}</td>
			</tr>
			<tr class="thead">
				<th>구분</th>
				<c:choose>
					<c:when test="${requestScope.boardvo.fk_categnum eq '1'}">
						<td>전체공지</td>
					</c:when>
					<c:when test="${requestScope.boardvo.fk_categnum eq '2'}">
						<td>총무공지</td>
					</c:when>
					<c:otherwise>
						<td>경조사</td>
					</c:otherwise>
				</c:choose>
			</tr>
			<tr class="thead">
				<th>제목</th>
				<td>${requestScope.boardvo.subject}</td>
			</tr>
			<tr class="thead">
				<th>첨부파일</th>
				<td>${requestScope.boardvo.subject}</td>
			</tr>
			<tr class="thead">
				<td colspan='2'>
				    <p style="word-break: break-all;">${requestScope.boardvo.content}</p>
				</td>
			</tr>
		</table>
	</c:if>
	
	<c:if test="${empty requestScope.boardvo}">
		<div style="padding: 50px 0; font-size: 16pt; color: red;">존재하지 않습니다</div>
	</c:if>
	
	<div id="viewpost-btn-container">
		<c:set var="gobackURL2" value="${fn:replace(requestScope.gobackURL,'&', ' ') }" />
		<c:if test="${requestScope.boardvo.previousseq ne null}">
			<i class="fas fa-angle-double-right"></i>&nbsp;&nbsp;이전글&nbsp;&nbsp;<span class="move" onclick="javascript:location.href='viewNotice.tw?seq=${requestScope.boardvo.previousseq}&searchType=${requestScope.searchType}&searchWord=${requestScope.searchWord}&gobackURL=${gobackURL2}'">${requestScope.boardvo.previoussubject}</span><br>
		</c:if>
		<c:if test="${requestScope.boardvo.nextseq ne null}">
			<i class="fas fa-angle-double-right"></i>&nbsp;&nbsp;다음글&nbsp;&nbsp;<span class="move" onclick="javascript:location.href='viewNotice.tw?seq=${requestScope.boardvo.nextseq}&searchType=${requestScope.searchType}&searchWord=${requestScope.searchWord}&gobackURL=${gobackURL2}'">${requestScope.boardvo.nextsubject}</span>
		</c:if>
		<button type="button" class="float-right btn-style" onclick="javascript:location.href='<%=ctxPath%>/${requestScope.gobackURL}'">목록</button><!-- 기존 검색된결과목록 -->
		<!-- <button type="button" class="float-right btn-style" onclick="javascript:location.href='suggestionBoard.tw'">전체목록보기</button> -->
		<c:if test="${requestScope.boardvo.fk_employeeid eq loginuser.employeeid}">
			<button type="button" class="float-right btn-style" onclick="javascript:location.href='<%= ctxPath%>/t1/noticeDel.tw?seq=${requestScope.boardvo.seq}'">삭제</button>
			<button type="button" class="float-right btn-style" onclick="javascript:location.href='<%= ctxPath%>/t1/noticeEdit.tw?seq=${requestScope.boardvo.seq}'">수정</button>
		</c:if>
	</div>
</div>




    