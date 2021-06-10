<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.net.InetAddress"%>
<% 
	String ctxPath = request.getContextPath();

	// 웹 채팅 서버 받기
	InetAddress inet = InetAddress.getLocalHost(); 
	String serverIP = inet.getHostAddress();
	int portnumber = request.getServerPort();
	String serverName = "http://"+serverIP+":"+portnumber; 
%>


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
		<table id="right-menu-style">
		   	<tbody>
		   		<tr style="height: 30px;">
		   			<td class="t1tour-shortcut"><i class="fas fa-plane fa-2x"></i></td>
		   			<td class="mypage"><i class="far fa-user-circle fa-2x"></i></td>
		   			<td class="schedule"><i class="far fa-calendar-alt fa-2x"></i></td>
		   			<td class="rsv"><i class="far fa-calendar-check fa-2x"></i></td>
		   			<td class="notice"><i class="fas fa-bullhorn fa-2x"></i></td>
		   			<td class="groupchat" onclick='window.open("<%= serverName%><%= ctxPath%>/t1/chatting/chatwith.tw", "", "left=100px, top=100px, width=500px, height=600px");'><i class="far fa-comments fa-2x"></i></td>
		   			<td rowspan="2">
		   			<form name="searchFrm" style="display:inline-block;">
				   	<input type="text"/>
				   	<input type="button" class="navbar-btn" value="검색"/>
				   </form>
		   			</td>
		   		</tr>
		   		<tr style="height: 20px;">
		   			<td class="t1tour-shortcut">티원투어</td>
		   			<td class="mypage">마이페이지</td>
		   			<td class="schedule">일정관리</td>
		   			<td class="rsv">예약현황</td>
		   			<td class="notice">공지사항</td>
		   			<td class="notice">그룹챗</td>
		   		</tr>
		    </tbody>
	    </table>
   </div>
   
</div>
