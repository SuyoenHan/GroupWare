<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String ctxPath = request.getContextPath();%>

<div id="mainMenu"> 
	<div id="left-menu">
		<span id="menu1" onclick="location.href='<%= ctxPath%>/t1/home.tw'"><img id="gwLogo" src="<%=ctxPath%>/resources/images/login/t1works_logo.jpg" /></span>
		<div style="display: inline-block; vertical-align: middle; margin-top:15px;">  
		  <span style="font-weight: bold;">${loginuser.name}
		     <c:if test="${loginuser.fk_pcode eq 1}">사원님</c:if>
		     <c:if test="${loginuser.fk_pcode eq 2}">대리님</c:if>
		     <c:if test="${loginuser.fk_pcode eq 3}">부장님</c:if>
		     <c:if test="${loginuser.fk_pcode eq 4}">사장님</c:if></span><span>&nbsp;환영합니다.</span><br>
		  <span class="navbar-btn" onclick="location.href='<%= ctxPath%>/t1/logout.tw'">
		   	  <c:if test="${not empty loginuser}">로그아웃</c:if>
		  </span>
		</div>
	</div>
	<div id="right-menu">
		<table>
		   	<tbody>
		   		<tr style="height: 30px;">
		   			<td class="home"><img class="top-menu-icon" src="<%=ctxPath%>/resources/images/topnavbar/home.png"></td>
		   			<td class="mypage"><img class="top-menu-icon" src="<%=ctxPath%>/resources/images/topnavbar/mypage.png"></td>
		   			<td class="schedule"><img class="top-menu-icon" src="<%=ctxPath%>/resources/images/topnavbar/calendar.png"></td>
		   			<td class="rsv"><img class="top-menu-icon" src="<%=ctxPath%>/resources/images/topnavbar/rsv.png"></td>
		   			<td class="notice"><img class="top-menu-icon" src="<%=ctxPath%>/resources/images/topnavbar/notice.png"></td>
		   			<td rowspan="2">
		   			<form name="searchFrm" style="display:inline-block;">
				   	<input type="text"/>
				   	<input type="button" class="navbar-btn" value="검색"/>
				   </form>
		   			</td>
		   		</tr>
		   		<tr style="height: 20px;">
		   			<td class="home">홈</td>
		   			<td class="mypage">마이페이지</td>
		   			<td class="schedule">일정관리</td>
		   			<td class="rsv">예약현황</td>
		   			<td class="notice">공지사항</td>
		   		</tr>
		    </tbody>
	    </table>
   </div>
   
</div>
