<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<% String ctxPath = request.getContextPath(); %>
<link rel="stylesheet" type="text/css" href="<%=ctxPath %>/resources/css/kdn/mail.css" />
<div id="mail-container">

	<c:set var="gobackURL2" value="${fn:replace(requestScope.gobackURL,'&', ' ') }" />

	<c:if test="${not empty requestScope.gobackURL}">
		<a href="<%=ctxPath%>/${requestScope.gobackURL}"><i class="fas fa-arrow-left fa-lg" style="color:black;"></i></a>
	</c:if>
	<c:if test="${empty requestScope.gobackURL}">
		<a href="mail.tw"><i class="fas fa-arrow-left fa-lg" style="color:black;"></i></a>
	</c:if>





	<div id="btn-container" class="float-right">
	<c:if test="${not empty requestScope.evo.previousseq}">
		<a href="javascript:location.href='viewMail.tw?seq=${requestScope.evo.previousseq}&searchType=${requestScope.searchType}&searchWord=${requestScope.searchWord}&gobackURL=${gobackURL2}'" ><i class="fas fa-chevron-left fa-lg" style="color:black;"></i></a>
	</c:if>
	<c:if test="${not empty requestScope.evo.nextseq}">
		<a href="javascript:location.href='viewMail.tw?seq=${requestScope.evo.nextseq}&searchType=${requestScope.searchType}&searchWord=${requestScope.searchWord}&gobackURL=${gobackURL2}'" ><i class="fas fa-chevron-right fa-lg" style="color:black;"></i></a>
	</c:if>
	<a href="t1/new_mail.tw?seq='${requestScope.evo.groupno}"><i class="fas fa-reply fa-lg" style="color:black;"></i></a>
	<a href="javascript:moveToTrash()" ><i class="far fa-trash-alt fa-lg" style="color:black;"></i></a>
	</div>
	<hr>
	
	<h4>${requestScope.evo.subject}</h4>
	<span>${requestScope.evo.senderName}&lt;${requestScope.evo.senderEmail}&gt;</span><br>
	<span>보낸날짜: ${requestScope.evo.sendingDate}</span><br>
	<span>받는사람: ${requestScope.evo.receiverName}&lt;${requestScope.evo.receiverEmail}&gt;</span><br>
	<span>참조메일: </span><br>
	<span>첨부파일: ${requestScope.evo.orgFilename}</span>
	<hr>
	<p>${requestScope.evo.content}</p>

</div>