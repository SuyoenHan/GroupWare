<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<% String ctxPath = request.getContextPath(); %>
<link rel="stylesheet" type="text/css" href="<%=ctxPath %>/resources/css/kdn/mail.css" />
<script type="text/javascript">
$(document).ready(function(){
	
	// 아이콘 hover 효과
	$('a.viewmail-icon').hover(function() {
		$(this).children().css("color", "black");
	}, function(){
		$(this).children().css("color", "#999999");
	});
	
	
	
});


function moveToTrash(){
	var frm = document.toTrashFrm;
	frm.seq.value = "${requestScope.evo.seq}";
	frm.searchType.value = "${requestScope.paraMap.searchType}";
    frm.searchWord.value = "${requestScope.paraMap.searchWord}";
	
	frm.method="get";
	frm.action="<%=ctxPath%>/t1/moveToTrash.tw";
	frm.submit();
}

</script>


<div id="viewmail-container">

	<c:set var="gobackURL2" value="${fn:replace(requestScope.gobackURL,'&', ' ') }" />

	<c:if test="${not empty requestScope.gobackURL}">
		<a href="<%=ctxPath%>/${requestScope.gobackURL}" class="viewmail-icon"><i class="fas fa-arrow-left fa-lg viewmail-icon" style="color: #999999; margin-top: 20px; margin-left:0;"></i></a>
	</c:if>
	<c:if test="${empty requestScope.gobackURL}">
		<a href="mail.tw"><i class="fas fa-arrow-left fa-lg viewmail-icon" style="color:black;"></i></a>
	</c:if>

	<div id="btn-container" class="float-right">
	<c:if test="${not empty requestScope.evo.previousseq}">
		<c:choose>
			<c:when test="${requestScope.mailBoxNo eq '2'}"><!-- 보낸메일함 메일 열람의 경우 -->
				<a href="javascript:location.href='viewMail.tw?mailBoxNo=2&seq=${requestScope.evo.previousseq}&searchType=${requestScope.searchType}&searchWord=${requestScope.searchWord}&gobackURL=${gobackURL2}'" class="viewmail-icon"><i class="fas fa-chevron-left fa-lg viewmail-icon" style="color: #999999;"></i></a>
			</c:when>
			<c:when test="${requestScope.mailBoxNo eq '3'}"><!-- 중요메일함 열람의 경우 -->
				<a href="javascript:location.href='viewMail.tw?mailBoxNo=3&seq=${requestScope.evo.previousseq}&searchType=${requestScope.searchType}&searchWord=${requestScope.searchWord}&gobackURL=${gobackURL2}'" class="viewmail-icon"><i class="fas fa-chevron-left fa-lg viewmail-icon" style="color: #999999;"></i></a>
			</c:when>
			<c:otherwise><!-- 받은메일함 열람의 경우 -->
				<a href="javascript:location.href='viewMail.tw?mailBoxNo=1&seq=${requestScope.evo.previousseq}&searchType=${requestScope.searchType}&searchWord=${requestScope.searchWord}&gobackURL=${gobackURL2}'" class="viewmail-icon"><i class="fas fa-chevron-left fa-lg viewmail-icon" style="color: #999999;"></i></a>
			</c:otherwise>
		</c:choose>
	</c:if>
	<c:if test="${not empty requestScope.evo.nextseq}">
		<c:choose>
			<c:when test="${requestScope.mailBoxNo eq '2'}"><!-- 보낸메일함 메일 열람의 경우 -->
				<a href="javascript:location.href='viewMail.tw?mailBoxNo=2&seq=${requestScope.evo.nextseq}&searchType=${requestScope.searchType}&searchWord=${requestScope.searchWord}&gobackURL=${gobackURL2}'" class="viewmail-icon"><i class="fas fa-chevron-right fa-lg viewmail-icon" style="color: #999999;"></i></a>
			</c:when>
			<c:when test="${requestScope.mailBoxNo eq '3'}"><!-- 중요메일함 열람의 경우 -->
				<a href="javascript:location.href='viewMail.tw?mailBoxNo=3&seq=${requestScope.evo.nextseq}&searchType=${requestScope.searchType}&searchWord=${requestScope.searchWord}&gobackURL=${gobackURL2}'" class="viewmail-icon"><i class="fas fa-chevron-right fa-lg viewmail-icon" style="color: #999999;"></i></a>
			</c:when>
			<c:otherwise><!-- 받은메일함 열람의 경우 -->
				<a href="javascript:location.href='viewMail.tw?mailBoxNo=1&seq=${requestScope.evo.nextseq}&searchType=${requestScope.searchType}&searchWord=${requestScope.searchWord}&gobackURL=${gobackURL2}'" class="viewmail-icon"><i class="fas fa-chevron-right fa-lg viewmail-icon" style="color: #999999;"></i></a>
			</c:otherwise>
		</c:choose>
	</c:if>
	<a href="t1/new_mail.tw?seq='${requestScope.evo.groupno}" class="viewmail-icon"><i class="fas fa-reply fa-lg viewmail-icon" style="color: #999999;"></i></a>
	<a href="javascript:moveToTrash()" class="viewmail-icon"><i class="far fa-trash-alt fa-lg viewmail-icon" style="color: #999999;"></i></a>
	</div>
	<hr style="clear:both; margin: 10px 0 20px 0;">
	<h3>${requestScope.evo.subject}</h3>
	<h4>${requestScope.evo.senderName}&lt;${requestScope.evo.senderEmail}&gt;</h4>
	<span style="color: #999999;">보낸날짜: </span><span>${requestScope.evo.sendingDate}</span><br>
	<span style="color: #999999;">받는사람: </span><span>${requestScope.evo.receiverName}&lt;${requestScope.evo.receiverEmail}&gt;</span><br>
	<c:if test="${not empty requestScope.evo.ccEmail}">
		<span style="color: #999999;">참조메일: </span><span>${requestScope.evo.ccEmail}</span><br>
	</c:if>
	<c:if test="${not empty requestScope.evo.fileName}">
		<span style="color: #999999;">첨부파일: </span>
		<span>
			<c:choose>
				<c:when test="${not empty requestScope.evo.fk_seq}"><!-- 보낸메일함 -->
					<a href="<%=ctxPath%>/t1/downloadEmailAttach.tw?seq=${requestScope.evo.seq}&mailBoxNo=2">${requestScope.evo.orgFilename}</a>
				</c:when>
				<c:otherwise><!-- 받은메일함, 중요메일함 -->
					<a href="<%=ctxPath%>/t1/downloadEmailAttach.tw?seq=${requestScope.evo.seq}&mailBoxNo=1">${requestScope.evo.orgFilename}</a>
				</c:otherwise>
			</c:choose>
		</span>
	</c:if>
	<hr>
	<p>${requestScope.evo.content}</p>

</div>

 <form name="toTrashFrm">
    	<input type="hidden" name="seq" />
    	<input type="hidden" name="gobackURL" value="${requestScope.gobackURL}"/>
    	<input type="hidden" name="searchType" />
      	<input type="hidden" name="searchWord" />
 </form>