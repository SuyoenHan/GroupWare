<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String ctxPath = request.getContextPath();
%>

<script>

</script>
<div id="sidemenu" style="height: 100%;">
<p id="mail" onclick="location.href='<%= ctxPath%>/t1/mail.tw'">메일함</p>
<p id="myDocument" onclick="location.href='<%= ctxPath%>/t1/myDocument.tw'">내문서함</p>
<p id="electronPay" onclick="location.href='<%= ctxPath%>/t1/electronPay.tw'">전자결재</p>
<p id="employeeMap" onclick="location.href='<%= ctxPath%>/t1/employeeMap.tw'">주소록</p>
<p id="employeeBoard" onclick="location.href='<%= ctxPath%>/t1/employeeBoard.tw'">사내게시판</p>
<p id="salary" onclick="location.href='<%= ctxPath%>/t1/salary.tw'">월급관리</p>
<p id="toDo" onclick="location.href='<%= ctxPath%>/t1/toDo.tw'">업무관리</p>
</div>
