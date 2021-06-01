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
	<a href="javascript:location.href='suggestionBoard.tw'" style="text-decoration:none; color: black;"><i class="fas fa-exclamation fa-lg"></i>&nbsp;&nbsp;<span style="display: inline-block; font-size:22px; margin-bottom: 40px;">건의사항</span></a>

	<c:if test="${not empty requestScope.boardvo}">
		<table id="post-table" class="table">
			<tr class="thead">
				<th>글번호</th>
				<td>${requestScope.boardvo.seq}</td>
			</tr>
			<tr class="thead">
				<th>성명</th>
				<td>${requestScope.boardvo.name}</td>
			</tr>
			<tr class="thead">
				<th>제목</th>
				<td>${requestScope.boardvo.subject}</td>
			</tr>
			<tr class="thead">
				<th>내용</th>
				<td>
				    <p style="word-break: break-all;">${requestScope.boardvo.content}</p>
					<%-- 
					      style="word-break: break-all; 은 공백없는 긴영문일 경우 width 크기를 뚫고 나오는 것을 막는 것임. 
					           그런데 style="word-break: break-all; 나 style="word-wrap: break-word; 은
					           테이블태그의 <td>태그에는 안되고 <p> 나 <div> 태그안에서 적용되어지므로 <td>태그에서 적용하려면
					      <table>태그속에 style="word-wrap: break-word; table-layout: fixed;" 을 주면 된다.
					 --%>
				</td>
			</tr>
			<tr class="thead">
				<th>조회수</th>
				<td>${requestScope.boardvo.readCount}</td>
			</tr>
			<tr class="thead">
				<th>날짜</th>
				<td>${requestScope.boardvo.regDate}</td>
			</tr>
		</table>
		
		<br>
		
		<div style="margin-bottom: 1%;">이전글제목&nbsp;&nbsp;<span class="move" onclick="javascript:location.href='viewSuggestion.tw?seq=${requestScope.boardvo.previousseq}'">${requestScope.boardvo.previoussubject}</span></div>
		<div style="margin-bottom: 1%;">다음글제목&nbsp;&nbsp;<span class="move" onclick="javascript:location.href='viewSuggestion.tw?seq=${requestScope.boardvo.nextseq}'">${requestScope.boardvo.nextsubject}</span></div>

	</c:if>
	
	<c:if test="${empty requestScope.boardvo}">
		<div style="padding: 50px 0; font-size: 16pt; color: red;">존재하지 않습니다</div>
	</c:if>
	
	<button type="button" onclick="javascript:location.href='suggestionBoard.tw'">전체목록보기</button>
	
	<button type="button" onclick="javascript:location.href='${requestScope.gobackURL}'">검색된결과목록보기</button>
	<button type="button" onclick="javascript:location.href='<%= ctxPath%>/t1/suggEdit.tw?seq=${requestScope.boardvo.seq}'">수정</button>
	<button type="button" onclick="javascript:location.href='<%= ctxPath%>/t1/suggDel.tw?seq=${requestScope.boardvo.seq}'">삭제</button>
</div>





    