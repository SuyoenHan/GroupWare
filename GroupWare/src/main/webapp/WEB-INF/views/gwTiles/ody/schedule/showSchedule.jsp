<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
 	String ctxPath = request.getContextPath();
	//     /groupware
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link href='<%=ctxPath %>/resources/fullcalendar/main.min.css' rel='stylesheet' />

<style type="text/css">

</style>
<script src='<%=ctxPath %>/resources/fullcalendar/main.min.js'></script>
<script src='<%=ctxPath %>/resources/fullcalendar/ko.js'></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script type="text/javascript">

$(document).ready(function(){
	
	// sidemenu와 content길이 맞추기
	func_height1();
	
	// sebumenu와 content길이 맞추기
	func_height2();
	
	  var calendarEl = document.getElementById('calendar');
      var calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridMonth',
        locale: 'ko',
        selectable: true,
	      editable: false,
	      headerToolbar: {
	    	    left: 'prev',
	    	    center: 'title',
	    	    right: 'next' // today 추가?
	    	  },
	    	  
        dateClick: function(info) {
      	//  alert('Date: ' + info.dateStr);
      	    $(".fc-day").css('background','none'); // 현재 날짜 배경색 없애기
      	    info.dayEl.style.backgroundColor = '#b1b8cd';
      	    chgdate=info.dateStr;
      	    $("input#chgdate").val(chgdate);
      	  }
      });
      
      
      calendar.render();
      
}); // end of $(document).ready(function()


</script>
</head>
<body>
<div id="sebumenu">
	<div><a href="<%= ctxPath%>/t1/rsRoom.tw">회의실 대여신청</a></div>
	<div style="background-color: #6c757d;"><a href="<%= ctxPath%>/t1/rsCar.tw" style="color: #fff;">차량 대여신청</a></div>
	<div><a href="<%= ctxPath%>/t1/rsGoods.tw">사무용품 대여신청</a></div>
</div>


<div id="content" style="background-color: white; width: 1180px; padding-left: 50px; ">

	<div id="firstWrap">
	
	
	</div>

	<div id="calendarWrapper">
			<div id="calendar" style="margin-top: 50px;" ></div>
	</div>



</div>
</body>
</html>