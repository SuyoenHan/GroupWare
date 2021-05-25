<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
   String ctxPath = request.getContextPath();
%>

<div id="mainMenu"> 
	<div id="left-menu">
		
		<span id="menu1" onclick="location.href='<%= ctxPath%>/t1/home.tw'"><img id="gwLogo" src="<%=ctxPath%>/resources/images/login/t1works_logo.jpg" /></span>
	   <span>${loginuser.name}
	      <c:if test="${loginuser.fk_pcode eq 1}">
	         사원님
	      </c:if>
	      <c:if test="${loginuser.fk_pcode eq 2}">
	         대리님
	      </c:if>
	      <c:if test="${loginuser.fk_pcode eq 3}">
	         부장님
	      </c:if>
	      <c:if test="${loginuser.fk_pcode eq 4}">
	         사장님
	      </c:if>환영합니다.</span>
	
	</div>
	<div id="right-menu">
   <span id="menu2" onclick="location.href='<%= ctxPath%>/t1/travelAgency.tw'">홈</span>
   <span id="menu3">마이페이지</span>
   <span id="menu4">일정관리</span>
   <span id="menu5">예약현황</span>
   <span id="menu6">공지사항</span>
   <form name="searchFrm" style="display:inline-block;">
   	<input type="text"/>
   	<button type="submit">검색</button>
   </form>
   <span id="menu7" onclick="location.href='<%= ctxPath%>/t1/logout.tw'">
   	 <c:if test="${not empty loginuser}">
           로그아웃
     </c:if>
   </span>
   </div>
</div>
