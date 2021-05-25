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

div#firstWrap{
	margin-top: 20px;
	float: right;
}

a{
    color: #000;
    text-decoration: none;
    background-color: transparent;
    cursor: pointer;
}

a:hover {
    color: #000;
    cursor: pointer;
    text-decoration: none;
    background-color: transparent;
}
.fc-daygrid{
    color: #000;
    cursor: pointer;
    text-decoration: none;
    background-color: transparent;
}


.fc-header-toolbar{
	height: 30px;
}

</style>
<script src='<%=ctxPath %>/resources/fullcalendar/main.min.js'></script>
<script src='<%=ctxPath %>/resources/fullcalendar/ko.js'></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script type="text/javascript">

$(document).ready(function(){

	// sebumenu와 content길이 맞추기
	func_height2();
	
	  var calendarEl = document.getElementById('calendar');
      var calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridMonth',
        locale: 'ko',
        selectable: true,
	      editable: false,
	      headerToolbar: {
	    	  left: 'prev,next today',
	          center: 'title',
	          right: 'dayGridMonth,timeGridWeek,timeGridDay'
	    	  },
	    	  
        dateClick: function(info) {
      	//  alert('Date: ' + info.dateStr);
      	    $(".fc-day").css('background','none'); // 현재 날짜 배경색 없애기
      	    info.dayEl.style.backgroundColor = '#b1b8cd';
      	    date=info.dateStr;
      	    $("input[name=chooseDate]").val(date);
      	    
      	    var frm = document.dateFrm;
      	    frm.action="<%= ctxPath%>/t1/insertSchedule.tw";
      	    frm.submit();
      	    
      	  }
      });
      
      
      calendar.render();
      
}); // end of $(document).ready(function()


</script>
</head>
<body>


	<div id="firstWrap">
		<select id="searchType">
			<option value="">선택하세요</option>
			<option value="name">이름</option>
			<option value="subject">제목</option>
			<option value="content">내용</option>
		</select>	
		<input type="text" id="searchWord" value=""/>
		<button type="button">검색</button>
	
	</div>

	<div id="calendarWrapper">
			<div id="calendar" style="margin-top: 100px;" ></div>
	</div>
	
	<div>
		<form name="dateFrm">
			<input type="text" name="chooseDate" value=""/>	
		</form>
	</div>


</body>
</html>