<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%	String ctxPath = request.getContextPath();%>
<link href="//netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script type="text/javascript">
	$(document).ready(function(){
		$("div.submenu").hide();
		
		
		var sidemenuHeight=$("div#sidemenu").height();
		console.log(sidemenuHeight);
		
		$("p").click(function(){
			$(this).height(sidemenuHeight);
		});
		
		
		
	});


</script>


	<div id="sidemenu">
		<a href="javascript:void(0)" class="closebtn" onclick="closeNav()"><span>×</span></a><br>
		<p id="mail" onclick="openNav(this)">메일함</p>
		<p id="myDocument" onclick="location.href='<%= ctxPath%>/t1/myDocument.tw'">내문서함</p>
		<p id="electronPay" onclick="location.href='<%= ctxPath%>/t1/electronPay.tw'">전자결재</p>
		<p id="employeeMap" onclick="location.href='<%= ctxPath%>/t1/employeeMap.tw'">주소록</p>
		<p id="employeeBoard" onclick="location.href='<%= ctxPath%>/t1/employeeBoard.tw'">사내게시판</p>
		<p id="salary" onclick="location.href='<%= ctxPath%>/t1/salary.tw'">월급관리</p>
		<p id="toDo" onclick="location.href='<%= ctxPath%>/t1/toDo.tw'">업무관리</p>
		<p id="reservation" onclick="location.href='<%= ctxPath%>/t1/rsRoom.tw'">예약관리</p>
	</div>
	<div id="submenu1" class="submenu">
	    <a href="#">메일쓰기</a>
	    <a href="<%=ctxPath%>/t1/mail.tw">받은메일함</a>
	    <a href="<%=ctxPath%>/t1/mail_sent.tw">보낸메일함</a>
	    <a href="<%=ctxPath%>/t1/mail_trash.tw">휴지통</a>
	</div>
	
	<div id="submenu2" class="submenu">
	    <a href="#">수신함</a>
	    <a href="#">발신함</a>
	    <a href="#">임시저장함</a>
	</div>
	<div id="submenu3" class="submenu">
	    <a href="#">세부메뉴1</a>
	    <a href="#">세부메뉴2</a>
	    <a href="#">세부메뉴3</a>
	</div>
	<div id="submenu4" class="submenu">
	    <a href="#">직원목록</a>
	    <a href="#">조직도</a>
	</div>
	<div id="submenu5" class="submenu">
	    <a href="#">공지사항</a>
	    <a href="#">건의사항</a>
	    <a href="#">자유게시판</a>
	</div>
	<div id="submenu6" class="submenu">
	    <a href="#">수당월별내역</a>
	    <a href="#">성과금/야근수당내역</a>
	</div>
	<div id="submenu7" class="submenu">
	    <c:choose>
	    	<c:when  test="${session.loginuser.pcode eq '1'}">
			    <a href="#">나의업무현황</a>
			    <a href="#">나의실적현황</a>
		    </c:when>
		    <c:when  test="${session.loginuser.dcode eq '4' && session.loginuser.pcode eq '3'}">
			    <a href="#">인사관리</a>
			    <a href="#">신입사원등록</a>
		    </c:when>
		    <c:when  test="${session.loginuser.pcode eq '3' && (session.loginuser.dcode eq '1' || session.loginuser.dcode eq '2' || session.loginuser.dcode eq '3')}">
			    <a href="#">나의업무현황</a>
			    <a href="#">부서업무현황</a>
			    <a href="#">부서실적현황</a>
		    </c:when>
		    <c:when test="${session.loginuser.pcode eq'4'}">
			    <a href="#">회사실적현황</a>
			    <a href="#">부서별 실적현황</a>
		    </c:when>
	    </c:choose>
	</div>
	<div id="submenu8" class="submenu">
	    <a href="#">회의실대여신청</a>
	    <a href="#">차량대여신청</a>
	    <a href="#">사무용품 대여신청</a>
	</div>
		
		
		
		
		
		
<script type="text/javascript">
 	 
function openNav() {
	/* document.getElementsByClass("submenu").style.width = "200px"; */
	var target = $(this).prop("clicked",true);
	$(target).width("200px");
	var sidemenuHeight=$("div#sidemenu").height();
	$("div.submenu").height(sidemenuHeight);
	
	$("p").click(function(){
		var clickedMenu = $(this).attr('id');
		
		if(clickedMenu == 'mail'){
			location.href="<%= ctxPath%>/t1/mail.tw";
			$("div#submenu1").show();
		} else if(clickedMenu == 'myDocument'){
			location.href="<%= ctxPath%>/t1/myDocument.tw";
			$("div#submenu2").show();
		} else if(clickedMenu == 'electronPay'){
			location.href="<%= ctxPath%>/t1/electronPay.tw";
			$("div#submenu3").show();
		} else if(clickedMenu == 'employeeMap'){
			location.href="<%= ctxPath%>/t1/employeeMap.tw";
			$("div#submenu4").show();
		} else if(clickedMenu == 'employeeBoard'){
			location.href="<%= ctxPath%>/t1/employeeBoard.tw";
			$("div#submenu5").show();
		} else if(clickedMenu == 'salary'){
			location.href="<%= ctxPath%>/t1/salary.tw";
			$("div#submenu6").show();
		} else if(clickedMenu == 'toDo'){
			location.href="<%= ctxPath%>/t1/toDo.tw";
			$("div#submenu7").show();
		} else if(clickedMenu == 'reservation'){
			location.href="<%= ctxPath%>/t1/reservation.tw";
			$("div#submenu8").show();
		}
		
	});
	  
	}

function closeNav() {
	$("div.submenu").width("0");
  /* document.getElementsByClass("submenu").style.height = "158px"; */
  /* document.getElementsByClass("submenu").style.width = "0"; */
}

</script>



