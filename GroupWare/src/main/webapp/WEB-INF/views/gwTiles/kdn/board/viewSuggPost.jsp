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
	
});// end of $(document).ready(function(){})------------------

//=== 댓글쓰기 ===
function goWriteComment() {
	
	var contentVal = $("input#commentContent").val().trim();
	if(contentVal == "") {
		alert("댓글 내용을 입력하세요!!");
		return; // 종료
	}
	
	var form_data = $("form[name=writeCmntFrm]").serialize();
	
	$.ajax({
		url:"<%= ctxPath%>/t1/addSuggComment.tw",
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
		url:"<%= ctxPath%>/t1/suggCommentList.tw",
		data:{"fk_seq":"${requestScope.boardvo.seq}",
			  "currentShowPageNo":currentShowPageNo},
		dataType:"json",
		success:function(json){ 
			
			var html = "";
			
			if(json.length > 0) {
				$.each(json, function(index, item){
					
					html += "<strong style='font-size:13px;'>"+item.name+"</strong>&nbsp;&nbsp;";
					html += "<span style='display:inline-block; margin-bottom:5px; font-size:13px;'>"+item.regDate+"</span>&nbsp;&nbsp;"
					
					if($("input[name=fk_employeeid]").val() == item.fk_employeeid){
						html+="<a style='margin-right:5px; font-size:13px; text-decoration: none;'>수정</a><a style='border-left: solid 1px gray; padding-left: 5px; font-size:13px; text-decoration: none;'>삭제</a><br>";
					} else {
						html+="<br>";
					}
					
					html += "<span style='font-size:13px;'>"+item.content+"</span>";
					
					if(index < json.length-1){
						html +="<hr style='margin: 10px 0;'>";
					}
					
				});
			}
			else {
				html += "<span>댓글이 없습니다</span>";
			}
			$("div#commentDisplay").html(html);
			
			// 페이지바 함수 호출
			makeCommentPageBar(currentShowPageNo);
		},
		error: function(request, status, error){
			alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
	 	}
	});
	
}// end of function goViewComment(currentShowPageNo) {}----------------------

//==== 댓글내용 페이지바  Ajax로 만들기 ==== // 
function makeCommentPageBar(currentShowPageNo) {

	<%-- 원글에 대한 댓글의 totalPage 수를 알아오려고 한다. --%> 
	$.ajax({
		url:"<%= ctxPath%>/t1/getSuggCmntTotalPage.tw",
		data:{"fk_seq":"${requestScope.boardvo.seq}",
			  "sizePerPage":"5"},
		type:"get",
		dataType:"json",
		success:function(json) {
			
			if(json.totalPage > 0) {
				// 댓글이 있는 경우 
				
				var totalPage = json.totalPage;
				
				var pageBarHTML = "<ul style='list-style: none;'>";
				
				var blockSize = 3;
				// blockSize 는 1개 블럭(토막)당 보여지는 페이지번호의 개수 이다.
				
				var loop = 1;
			    
			    if(typeof currentShowPageNo == "string") {
			    	currentShowPageNo = Number(currentShowPageNo);
			    }
			    
				var pageNo = Math.floor((currentShowPageNo - 1)/blockSize) * blockSize + 1;
				
			// === [맨처음][이전] 만들기 === 
				if(pageNo != 1) {
					pageBarHTML += "<li style='display:inline-block; width:70px; font-size:12pt;'><a href='javascript:goViewComment(\"1\")'>[맨처음]</a></li>";
					pageBarHTML += "<li style='display:inline-block; width:50px; font-size:12pt;'><a href='javascript:goViewComment(\""+(pageNo-1)+"\")'>[이전]</a></li>";
				}
			
				while( !(loop > blockSize || pageNo > totalPage) ) {
				
					if(pageNo == currentShowPageNo) {
						pageBarHTML += "<li style='display:inline-block; width:30px; font-size:12pt; border:solid 1px gray; color:red; padding:2px 4px;'>"+pageNo+"</li>";
					}
					else {
						pageBarHTML += "<li style='display:inline-block; width:30px; font-size:12pt;'><a href='javascript:goViewComment(\""+pageNo+"\")'>"+pageNo+"</a></li>";
					}
					
					loop++;
					pageNo++;
				}// end of while------------------------
			
			
			// === [다음][마지막] 만들기 === 
				if(pageNo <= totalPage) {
					pageBarHTML += "<li style='display:inline-block; width:50px; font-size:12pt;'><a href='javascript:goViewComment(\""+pageNo+"\")'>[다음]</a></li>";
					pageBarHTML += "<li style='display:inline-block; width:70px; font-size:12pt;'><a href='javascript:goViewComment(\""+totalPage+"\")'>[마지막]</a></li>";
				}
				
				pageBarHTML += "</ul>";
			    
				$("div#pageBar").html(pageBarHTML);
			}
		},
		error: function(request, status, error){
			alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
	 	}
	});
	
}// end of function makeCommentPageBar(currentShowPageNo) {}-----------------

</script>

<div id="board-container">
	<a href="javascript:location.href='suggestionBoard.tw'" style="text-decoration:none; color: black;"><i class="fas fa-exclamation fa-lg"></i>&nbsp;&nbsp;<span style="display: inline-block; font-size:22px; margin-bottom: 40px;">건의사항</span></a>

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
				<th>제목</th>
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
			<i class="fas fa-angle-double-right"></i>&nbsp;&nbsp;이전글&nbsp;&nbsp;<span class="move" onclick="javascript:location.href='viewSuggPost.tw?seq=${requestScope.boardvo.previousseq}&searchType=${requestScope.searchType}&searchWord=${requestScope.searchWord}&gobackURL=${gobackURL2}'">${requestScope.boardvo.previoussubject}</span><br>
		</c:if>
		<c:if test="${requestScope.boardvo.nextseq ne null}">
			<i class="fas fa-angle-double-right"></i>&nbsp;&nbsp;다음글&nbsp;&nbsp;<span class="move" onclick="javascript:location.href='viewSuggPost.tw?seq=${requestScope.boardvo.nextseq}&searchType=${requestScope.searchType}&searchWord=${requestScope.searchWord}&gobackURL=${gobackURL2}'">${requestScope.boardvo.nextsubject}</span>
		</c:if>
		<button type="button" class="float-right btn-style" onclick="javascript:location.href='<%=ctxPath%>/${requestScope.gobackURL}'">목록</button><!-- 기존 검색된결과목록 -->
		<!-- <button type="button" class="float-right btn-style" onclick="javascript:location.href='suggestionBoard.tw'">전체목록보기</button> -->
		<c:if test="${requestScope.boardvo.fk_employeeid eq loginuser.employeeid}">
			<button type="button" class="float-right btn-style" onclick="javascript:location.href='<%= ctxPath%>/t1/suggDel.tw?seq=${requestScope.boardvo.seq}'">삭제</button>
			<button type="button" class="float-right btn-style" onclick="javascript:location.href='<%= ctxPath%>/t1/suggEdit.tw?seq=${requestScope.boardvo.seq}'">수정</button>
		</c:if>
	</div>

	<%-- 댓글쓰기 폼 추가 === --%>
    <c:if test="${not empty sessionScope.loginuser}">
		<form name="writeCmntFrm" style="margin-top: 20px;">
	        <input type="hidden" name="fk_employeeid" value="${sessionScope.loginuser.employeeid}" />
			<input type="hidden" name="name" value="${sessionScope.loginuser.name}" />  
			<%-- 댓글에 달리는 원게시물 글번호(즉, 댓글의 부모글 글번호) --%>
			<input type="hidden" name="fk_seq" value="${requestScope.boardvo.seq}" /> 
			댓글쓰기(500자이내)<br>
			<input id="commentContent" type="text" name="content" style="display: inline-block; width: 93%; height: 28px; margin: 5px 0;"/> 
			<button class="cmnt-btn-style" id="btnComment" type="button" onclick="goWriteComment()">확인</button> 
			<button class="cmnt-btn-style" type="reset">취소</button>
		</form>
    </c:if>
    
    <span style="display: inline-block; margin-top: 20px; margin-bottom: 10px;">댓글(개수표시하기)</span>
    <!-- ===== #94. 댓글 내용 보여주기 ===== -->
    <div id="commentDisplay" style="clear:both; border-top: solid 1px #eee; border-bottom: solid 1px #eee; padding: 10px 10px;"></div>

    <%-- ==== #136. 댓글 페이지바 ==== --%>
    <div id="pageBar" style="width: 90%; margin: 10px auto; text-align: center;"></div> 
    


</div>





    