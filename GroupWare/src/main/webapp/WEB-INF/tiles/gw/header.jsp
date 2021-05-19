<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String ctxPath = request.getContextPath();
%>

<div id="mainMenu"> 
	<span id="menu1" onclick="location.href='<%= ctxPath%>/t1/home.tw'">T1Works</span>
	<label for="userid">아이디</label>  <input id="userid" type="text">
	<label for="password">비밀번호</label> <input id="password" type="password">
	<button type="button">로그인</button>
	<span>홈</span>
	<span>마이페이지</span>
	<span>일정관리</span>
	<span>예약현황</span>
	<span>공지사항</span>
	<span>검색</span>
</div>

<script type="text/javascript">
		
	//Function Declaration
	function func_height1(){ // sidemenu와 content길이 맞추기
		
    	var content_height= $("div#content").height();
        $("div#sidemenu").height(content_height);

    }
	
	function func_height2(){ // sebumenu와 content길이 맞추기
		
    	var content_height= $("div#content").height();
        $("div#sebumenu").height(content_height);

    }

</script>