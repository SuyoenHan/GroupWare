<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String ctxPath = request.getContextPath();
%>

<script type="text/javascript">

	$(document).ready(function(){
		
		
		
	}); // end of $(document).ready(function(){

</script>

<div id="sidemenu">
	<div id="mail" onclick="location.href='<%= ctxPath%>/t1/mail.tw'">메일함</div>
	<div id="myDocument" onclick="location.href='<%= ctxPath%>/t1/myDocument.tw'">내문서함</div>
	<div id="electronPay" onclick="location.href='<%= ctxPath%>/t1/electronPay.tw'">전자결제</div>
	<div id="employeeMap" onclick="location.href='<%= ctxPath%>/t1/employeeMap.tw'">주소록</div>
	<div id="employeeBoard" onclick="location.href='<%= ctxPath%>/t1/employeeBoard.tw'">사내게시판</div>
	<div id="salary" onclick="location.href='<%= ctxPath%>/t1/salary.tw'">월급관리</div>
	<div id="toDo" onclick="location.href='<%= ctxPath%>/t1/toDo.tw'">업무관리</div>
</div>