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
	<div id="electronPay" onclick="location.href='<%= ctxPath%>/t1/electronPay.tw'">전자결재</div>
	<div id="employeeMap" onclick="location.href='<%= ctxPath%>/t1/employeeMap.tw'">주소록</div>
	<div id="employeeBoard" onclick="location.href='<%= ctxPath%>/t1/employeeBoard.tw'">사내게시판</div>
	<div id="salary" onclick="location.href='<%= ctxPath%>/t1/salary.tw'">월급관리</div>
	<div id="toDo" onclick="location.href='<%= ctxPath%>/t1/toDo.tw'">업무관리</div>
</div>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="//netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script type="text/javascript">
	$(document).ready(function(){
		$("div.submenu").hide();
		
		
		/* var sidemenuHeight=$("div#sidemenu").height();
		console.log(sidemenuHeight);
		
		$("p").click(function(){
			$(this).height(sidemenuHeight);
		});
		 */
		
		
	});


</script>


	<div id="sidemenu">
		<a href="javascript:void(0)" class="closebtn" onclick="closeNav()"><span>×</span></a><br>
		<p id="mail" onclick="openNav()">메일함</p>
		<p id="myDocument" onclick="location.href='<%= ctxPath%>/t1/myDocument.tw'">내문서함</p>
		<p id="electronPay" onclick="location.href='<%= ctxPath%>/t1/electronPay.tw'">전자결재</p>
		<p id="employeeMap" onclick="location.href='<%= ctxPath%>/t1/employeeMap.tw'">주소록</p>
		<p id="employeeBoard" onclick="location.href='<%= ctxPath%>/t1/employeeBoard.tw'">사내게시판</p>
		<p id="salary" onclick="location.href='<%= ctxPath%>/t1/salary.tw'">월급관리</p>
		<p id="toDo" onclick="location.href='<%= ctxPath%>/t1/toDo.tw'">업무관리</p>
		<p id="reservation" onclick="location.href='<%= ctxPath%>/t1/rsRoom.tw'">예약관리</p>
	</div>
	<div id="submenu1" class="submenu">
	    <a href="<%=ctxPath%>/t1/new_mail.tw">메일쓰기</a>
	    <a href="<%=ctxPath%>/t1/mail.tw">받은메일함</a>
	    <a href="<%=ctxPath%>/t1/mail_sent.tw">보낸메일함</a>
	    <a href="<%=ctxPath%>/t1/mail_important.tw">중요메일함</a>
	    <a href="<%=ctxPath%>/t1/mail_trash.tw">휴지통</a>
	</div>
	
	<div id="submenu2" class="submenu">
	    <a href="<%=ctxPath%>/t1/mydocu_receive.tw">수신함</a>
	    <a href="<%=ctxPath%>/t1/mydocu_send.tw">발신함</a>
	    <a href="<%=ctxPath%>/t1/mydocu_temp.tw">임시저장함</a>
	</div>
	<div id="submenu3" class="submenu">
	    <a href="<%=ctxPath%>/t1/generalPayment_List.tw">일반결재내역</a>
	    <a href="<%=ctxPath%>/t1/generalPayment_Write.tw">일반결재문서작성</a>
	    <a href="#">지출결재내역</a>
	    <a href="#">지출결재문서작성</a>
	    <a href="#">지출결재내역</a>
	    <a href="#">근태/휴가결재내역</a>
	    <a href="#">근태/휴가결재문서작성</a>
	</div>
	<div id="submenu4" class="submenu">
	    <a href="<%=ctxPath%>/t1/employeeMap.tw">직원목록</a>
	    <a href="<%=ctxPath%>/t1/employeeChart.tw">조직도</a>
	</div>
	<div id="submenu5" class="submenu">
	    <a href="<%=ctxPath%>/t1/employeeBoard.tw">공지사항</a>
	    <a href="<%=ctxPath%>/t1/suggestionBoard.tw">건의사항</a>
	    <a href="<%=ctxPath%>/t1/generalBoard.tw">자유게시판</a>
	</div>
	<div id="submenu6" class="submenu">
	    <a href="<%=ctxPath%>/t1/salary.tw ">수당월별내역</a>
	    <a href="<%=ctxPath%>/t1/salaryDetail.tw">성과금/야근수당내역</a>
	</div>
	<div id="submenu7" class="submenu">
	    <%-- <c:choose>
	    	<c:when  test="${loginuser.pcode eq '1'}"> --%>
			    <a href="<%=ctxPath%>/t1/employeeTodo.tw   ">나의업무현황</a>
			    <a href="<%=ctxPath%>/t1/employeePerformance.tw">나의실적현황</a>
		    <%-- </c:when>
		    <c:when  test="${loginuser.dcode eq '4' && loginuser.pcode eq '3'}"> --%>
			    <a href="<%=ctxPath%>/t1/personnelManage.tw">인사관리</a>
			    <a href="<%=ctxPath%>/t1/registerNewEmployee.tw">신입사원등록</a>
		    <%-- </c:when>
		    <c:when  test="${loginuser.pcode eq '3' && (loginuser.dcode eq '1' || loginuser.dcode eq '2' || loginuser.dcode eq '3')}"> --%>
			    <a href="<%=ctxPath%>/t1/leaderTodo.tw ">나의업무현황</a>
			    <a href="<%=ctxPath%>/t1/departmentTodo.tw ">부서업무현황</a>
			    <a href="<%=ctxPath%>/t1/departmentPerformance.tw">부서실적현황</a>
		    <%-- </c:when>
		    <c:when test="${loginuser.pcode eq'4'}"> --%>
			    <a href="<%=ctxPath%>/t1/companyPerformance.tw">회사실적현황</a>
			    <a href="<%=ctxPath%>/t1/companyPerformance/departmentPerformance.tw">부서별 실적현황</a>
		    <%-- </c:when>
	    </c:choose> --%>
	</div>
	<div id="submenu8" class="submenu">
	    <a href="<%=ctxPath%>/t1/rsRoom.tw">회의실대여신청</a>
	    <a href="<%=ctxPath%>/t1/rsCar.tw">차량대여신청</a>
	    <a href="<%=ctxPath%>/t1/rsGoods.tw">사무용품 대여신청</a>
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



>>>>>>> branch 'main' of https://github.com/bwb930305/GroupWare.git
