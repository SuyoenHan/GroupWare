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


<link href='<%=ctxPath %>/resources/fullcalendar/main.min.css' rel='stylesheet' />

<style type="text/css">

div#firstWrap{
	margin-top: 20px;
	float: right;
}

div#calendarWrapper{
	width: 80%;
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
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<!-- db에 저장된 스케쥴 값 가져오는 script -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>
<script type="text/javascript">

$(document).ready(function(){

	
	  // 풀캘린더와 관련된 소스코드
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
	    eventLimit:3,
	    eventLimitText: "Something",
		// db와 연동하는 법
	    events:function(info, successCallback, failureCallback){
	    	 $.ajax({
                 url: '<%= ctxPath%>/t1/selectSchedule.tw',
                 data:{"fk_employeeid":$('input#fk_employeeid').val(), "fk_bcno":$('input#fk_bcno').val()},
                 dataType: "json",
                 success:function(json) {
                	 var events = [];
                     if(json!=null){
                         
                             $.each(json, function(index, item) {
            						
                                    var startdate=moment(item.startdate).format('YYYY-MM-DD');
                                    var enddate=moment(item.enddate).format('YYYY-MM-DD');
                                    var penddate=moment(item.enddate).add(1, 'days').format('YYYY-MM-DD');
                                    var subject = item.subject;
                                   
                                    if(startdate==enddate){
                                   events.push({
                                               title: item.subject,
                                               start: startdate,
                                               end: enddate,
                                        	   url: "<%= ctxPath%>/t1/detailSchedule.tw?sdno="+item.sdno,
                                               color: item.color                                                   
                                  }); // events.push
                                    }
                                    else{
                                    	events.push({
                                            title: item.subject,
                                            start: startdate,
                                            end: penddate,
                                     		url: "<%= ctxPath%>/t1/detailSchedule.tw?sdno="+item.sdno,
                                            color: item.color     
                                    	 });
                                    	}
                             });  //.each()
                                                                  
                           
                         }
                             
                             console.log(events);                       
                         successCallback(events);                               
                  }//success: function end                          
          }); //ajax end
        }, //events:function end
		// 날짜 클릭할 때 발생하는 이벤트(일정 등록창으로 넘어간다)
        dateClick: function(info) {
      	//  alert('Date: ' + info.dateStr);
      	    $(".fc-day").css('background','none'); // 현재 날짜 배경색 없애기
      	    info.dayEl.style.backgroundColor = '#b1b8cd';
      	    date=info.dateStr;
      	    $("input[name=chooseDate]").val(date);
      	    
      	    var frm = document.dateFrm;
      	    frm.method="POST";
      	    frm.action="<%= ctxPath%>/t1/insertSchedule.tw";
      	    frm.submit();
      	    
      	  }
      });
      calendar.render();
      
}); // end of $(document).ready(function()


</script>
</head>
<body>

	<input type="hidden" value="${sessionScope.loginuser.employeeid}" id="fk_employeeid"/>
	<input type="checkBox" value="2" id="fk_bcno"/>전체캘린더
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

	<div id="calendarWrapper" style="margin-left: 80px;">
			<div id="calendar" style="margin-top: 100px;" ></div>
	</div>
	
	<div>
		<form name="dateFrm">
			<input type="hidden" name="chooseDate" value=""/>	
		</form>
	</div>


</body>
</html>