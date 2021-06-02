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
	
	goViewComment(1); // 페이징처리 한 댓글 읽어오기
	
	$("span.move").hover(function(){
		                    $(this).addClass("moveColor");
		                }
                        ,function(){
                        	$(this).removeClass("moveColor");
                        });
	
}); // end of $(document).ready(function(){})------------------

//=== 댓글쓰기 ===
function goWriteComment() {
	
	var contentVal = $("input#commentContent").val().trim();
	if(contentVal == "") {
		alert("댓글 내용을 입력하세요!!");
		return; // 종료
	}
	
	var form_data = $("form[name=writeCmntFrm]").serialize();
	
	$.ajax({
		url:"<%= ctxPath%>/t1/addComment.tw",
		data:form_data,
		type:"post",
		dataType:"json",
		success:function(json){
			// goReadComment();  // 페이징처리 안한 댓글 읽어오기
			goViewComment(1); // 페이징처리 한 댓글 읽어오기 
		   $("input#commentContent").val("");
		   
		},
		error: function(request, status, error){
			alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
	 	}
	});
	
}// end of function goAddWrite(){}--------------------------

//=== Ajax로 불러온 댓글내용을 페이징처리하기 ===
function goViewComment(currentShowPageNo) {
	
	$.ajax({
		url:"<%= ctxPath%>/t1/commentList.tw",
		data:{"fk_seq":"${requestScope.boardvo.seq}",
			  "currentShowPageNo":currentShowPageNo},
		dataType:"json",
		success:function(json){ 
			// []  또는 
			// [{"name":"이순신","regDate":"2021-05-28 10:41:52","content":"열네번째 댓글입니다."},{"name":"이순신","regDate":"2021-05-28 10:41:52","content":"열세번째 댓글입니다."},{"name":"이순신","regDate":"2021-05-28 10:41:52","content":"열두번째 댓글입니다."},{"name":"이순신","regDate":"2021-05-28 10:41:52","content":"열한번째 댓글입니다."},{"name":"이순신","regDate":"2021-05-28 10:41:52","content":"열번째 댓글입니다."}] 
			
			var html = "";
			
			if(json.length > 0) {
				$.each(json, function(index, item){
					html += "<tr>";
					html += "<td class='comment'>"+(index+1)+"</td>";
					html += "<td>"+ item.content +"</td>";
					
					if($("input[name=fk_employeeid]").val() == item.fk_employeeid){
						html+="<td class='comment'><button type='button'>수정</button><br><button type='button'>삭제</button></td>"
					}
					
					html += "<td class='comment'>"+ item.name +"</td>";
					html += "<td class='comment'>"+ item.regDate +"</td>";
					html += "</tr>";
				});
			}
			else {
				html += "<tr>";
				html += "<td colspan='4' class='comment'>댓글이 업습니다</td>";
				html += "</tr>";
			}
			
			$("tbody#commentDisplay").html(html);
			
			// 페이지바 함수 호출
			//makeCommentPageBar(currentShowPageNo);
		},
		error: function(request, status, error){
			alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
	 	}
	});
	
}// end of function goViewComment(currentShowPageNo) {}----------------------
		
		
</script>

<div id="board-container">
	<a href="javascript:location.href='generalBoard.tw'" style="text-decoration:none; color: black;"><i class="far fa-comments fa-lg"></i>&nbsp;&nbsp;<span style="display: inline-block; font-size:22px; margin-bottom: 40px;">자유게시판</span></a>

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
		<c:set var="gobackURL2" value="${fn:replace(requestScope.gobackURL,'&', ' ') }" />
		<div style="margin-bottom: 1%;">이전글제목&nbsp;&nbsp;<span class="move" onclick="javascript:location.href='viewGenPost.tw?seq=${requestScope.boardvo.previousseq}&gobackURL=${gobackURL2}'">${requestScope.boardvo.previoussubject}</span></div>
		<div style="margin-bottom: 1%;">다음글제목&nbsp;&nbsp;<span class="move" onclick="javascript:location.href='viewGenPost.tw?seq=${requestScope.boardvo.nextseq}&gobackURL=${gobackURL2}'">${requestScope.boardvo.nextsubject}</span></div>

	</c:if>
	
	<c:if test="${empty requestScope.boardvo}">
		<div style="padding: 50px 0; font-size: 16pt; color: red;">존재하지 않습니다</div>
	</c:if>
	
	<button type="button" onclick="javascript:location.href='generalBoard.tw'">전체목록보기</button>
	<button type="button" onclick="javascript:location.href='<%=ctxPath%>/${requestScope.gobackURL}'">검색된결과목록보기</button>
	<c:if test="${requestScope.boardvo.fk_employeeid eq loginuser.employeeid}">
		<button type="button" onclick="javascript:location.href='<%= ctxPath%>/t1/generalEdit.tw?seq=${requestScope.boardvo.seq}'">수정</button>
		<button type="button" onclick="javascript:location.href='<%= ctxPath%>/t1/generalDel.tw?seq=${requestScope.boardvo.seq}'">삭제</button>
	</c:if>
	<%-- 댓글쓰기 폼 추가 === --%>
    <c:if test="${not empty sessionScope.loginuser}">
    	<h3 style="margin-top: 50px;">댓글쓰기 및 보기</h3>
		<form name="writeCmntFrm" style="margin-top: 20px;">
	        <input type="hidden" name="fk_employeeid" value="${sessionScope.loginuser.employeeid}" />
			<input type="hidden" name="name" value="${sessionScope.loginuser.name}" />  
			댓글내용 : <input id="commentContent" type="text" name="content" class="long" /> 
			
			<%-- 댓글에 달리는 원게시물 글번호(즉, 댓글의 부모글 글번호) --%>
			<input type="hidden" name="fk_seq" value="${requestScope.boardvo.seq}" /> 
			
			<button id="btnComment" type="button" onclick="goWriteComment()">확인</button> 
			<button type="reset">취소</button> 
		</form>
    </c:if>

	<c:if test="${empty sessionScope.loginuser}">
    	<h3 style="margin-top: 50px;">댓글보기</h3>
    </c:if>	
    
    <!-- ===== #94. 댓글 내용 보여주기 ===== -->
	<table id="table2" class="table" style="margin-top: 2%; margin-bottom: 3%;">
		<thead>
		<tr>
		    <th style="width: 10%; text-align: center;">번호</th>
			<th style="width: 60%; text-align: center;">내용</th>
			<th style="width: 10%; text-align: center;"></th>
			<th style="width: 10%; text-align: center;">작성자</th>
			<th style="text-align: center;">작성일자</th>
		</tr>
		</thead>
		<tbody id="commentDisplay"></tbody>
	</table>

    <%-- ==== #136. 댓글 페이지바 ==== --%>
    <div id="pageBar" style="border:solid 0px gray; width: 90%; margin: 10px auto; text-align: center;"></div> 


</div>




    