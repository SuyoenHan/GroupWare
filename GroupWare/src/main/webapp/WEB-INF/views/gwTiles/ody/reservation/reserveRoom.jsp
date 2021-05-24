<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
 	String ctxPath = request.getContextPath();
	//     /groupware
%>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link href='<%=ctxPath %>/resources/fullcalendar/main.min.css' rel='stylesheet' />
<style type="text/css">

table{
	border-collapse: collapse;
}

th, td{
	border: solid 1px #CCD1D1;
	text-align: center;
	height: 40px;
	vertical-align: middle;
}

/* 달력 css */
div#calendarWrapper{
	display: inline-block;
}

.fc-scroller {
   	overflow-y: hidden !important;
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
	height: 30ox;
}

div.room{
	display: inline-block;
	width:500px;
	margin-left: 500px;
 	margin-top: -1000px;
}

.roomClick{
	background-color: #b1b8cd;
}

button#btn_show{
	float: right;
	margin-top: 20px;
	margin-bottom: 20px;
}


</style>


<script src='<%=ctxPath %>/resources/fullcalendar/main.min.js'></script>
<script src='<%=ctxPath %>/resources/fullcalendar/ko.js'></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script type="text/javascript">

var curDate = new Date();

var curHour=curDate.getHours();

var currtime = curDate.getHours();
var curDay = curDate.getDate();
var curMonth = curDate.getMonth() + 1;
if(curMonth.toString().length < 2){
	curMonth = "0"+curMonth;
}

if(curDay.length < 2){
	curDay = "0"+curDay;
}

var curYear = curDate.getFullYear();
var curTime = curYear + "" + curMonth + "" + curDay;



$(document).ready(function(){
	
	// sebumenu와 content길이 맞추기
	func_height2();
	
	$("button#btn_Reserve").hide(); // 등록버튼 숨기기
	
	$("div#myModal").modal('hide');
	var roomno="";
	var chgdate="";

	
	$("tr.selectRoom").hover(function(){
		$(this).css("cursor", "pointer");
	});
	

	$("tr.selectRoom").click(function(){
		$("tr.selectRoom").removeClass("roomClick");
		var roomno = $(this).find("td#findno").html();
		var roomname= $(this).find("td#findname").html();

		$("input#roomno").val(roomno);
		$("input#roomname").val(roomname);
		$(this).addClass("roomClick");
	});
	
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
        calendar.setOption('height', 510);
        
		
        $("button#btn_show").click(function(){
        	
        	var roomno=$("input#roomno").val();
        	var chgdate=$("input#chgdate").val();
        	 if( roomno!= "" && chgdate != "" ){
             	$.ajax({
             		url:"<%= ctxPath%>/t1/rsroom/reserveRoom.tw",
             		data:{"roomno":roomno, "chgdate":chgdate},
             		dataType: "json",
             		success:function(json){
             		 //	console.log("json:"+json);
             		 	
             		 // 불러온 배열을 함수에 값 지정해주기
             		 	showReserve(json);
             		}
             		, error: function(request, status, error){
        	            alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
          	      }
             	});
             	
             }
        	 else if(roomno!= "" && chgdate == ""){
        		 alert("날짜를 선택하세요");
        	 }
        	 else if(roomno == "" && chgdate != ""){
        		 alert("회의실을 선택하세요");
        	 }
        	 else{
        		 alert("날짜 및 회의실을 선택하세요");
        	 }
        });
        
        // '등록' 버튼 클릭 이벤트
    	$("button#btn_Reserve").click(function(){
    		func_reserve();
    	});
    
        // 모알창에서 예약
    	$("button#realReserve").click(function(event){
    		 event.stopPropagation();
             event.preventDefault();
             
             var purpose = $("input#purpose").val().trim();
             
             if(purpose == ""){
            	 alert("목적을 입력하세요");
             }
             else{
            	 <%-- form으로 값 넘겨주기--%>
            	 var frm = document.reserveRoom;
            	 frm.method = "POST";
                 frm.action = "<%= ctxPath%>/t1/addReserveRoom.tw";
                 frm.submit();  
        
             }
    	});
    	
}); // end of $(document).ready(function(){}------

	function showReserve(json){
	//	console.log("개수:"+json.length); 
		
		var chgdate=$("input#chgdate").val();

		// 선택한 날짜 숫자로 바꿔주는 과정
		var chgYear = chgdate.substring(0,4);
		var chgMonth = chgdate.substring(5,7);
		var chgDay = chgdate.substring(8,10);
	
		chgdate=chgYear+""+chgMonth+""+chgDay;

		
		var rsroomno=Array();
		var rtime= Array();
		var name=Array();
		var fk_employeeid=Array();
		var rsubject=Array();
		var rdepartment=Array();
		var employeeid = $("input[name=fk_employeeid]").val();
		$("tbody#rstimeList tr").remove(); // 입력하신 정보가 없습니다. 사라지게 만들기
		
		// select 해온 값을 table td 값에 쌓아둔다.
		$.each(json, function(index,item){
			rsroomno.push(item.rsroomno);
			rtime.push(item.rtime);
			name.push(item.name);
			fk_employeeid.push(item.fk_employeeid);
			rsubject.push(item.rsubject);
			rdepartment.push(item.rdepartment);
		});
		
	
		for(var i=0;i<24;i++){ // 시간 정보 입력
			
			var time="";
			var html="";
			var fontType="";
			var clickEvent="";
			var chkBox="";
			var index=jQuery.inArray(i,rtime); // 시간배열에 i가 존재하는지 index값을 설정  i에 맞는 시간대가 있다면 index의 값은 무조건 0이상이된다. 
			console.log(i+"인덱스값: "+index);
			
			// 선택한 날짜와 오늘날짜가 같을 때
			if(parseInt(curTime)==parseInt(chgdate)){ 
				if(i>=curHour){
					 fontType="#000";
				
					 // 이미 예약된 값에는 체크박스가 생성되면 안된다.
					if(index > -1){
						fontType = "#D1D4DB";
						
					} 
					else {
						chkBox = "<input type='checkbox' id='rsCheck"+i+"' name='rsCheck' value="+i+">";
						
					}
				}
				else {
					// 오늘 날짜의 예약 등록 불가능한 경우
					fontType = "#D1D4DB";
				}
			}
			// 오늘 이후 날짜의 예약 등록 가능한 경우
			else if (parseInt(curTime)<parseInt(chgdate)) {
				
				fontType = "#000";

				if(index > -1){
					fontType = "#D1D4DB";
					clickEvent = "style='cursor:pointer;'";
				} else {
					chkBox = "<input type='checkbox' id='rsCheck"+i+"' name='rsCheck' value="+i+">";
					
				}
			} 
			else {
				// 오늘 이전 날짜의 예약 등록 불가능한 경우
				fontType = "#D1D4DB";
			}

			if( index > -1){

				html += "<tr><td style='vertical-align: middle;' ><label id='id_time' ><font color='"+fontType+"'>"+timeText(rtime[index])+"</label></span></td>";
				html += "<td>"+rdepartment[index]+"</td>";
				html += "<td>"+name[index]+"</td>";
				html += "<td align='left'>"+rsubject[index]+"</td>";
				
					if(fk_employeeid[index] != employeeid) {
						html += "<td></td></tr>";
					} 
					else {
						html += "<td><button id='btn_delete' class='btn btn-secondary' onclick='deleteReserve("+rsroomno[index]+")';>삭제</button></td></tr>";
					}
			
				
			} 
			else {

				html += "<tr><td style='vertical-align: middle;'>"+chkBox+"<label for='rsCheck"+i+"' id='id_time'><font color='"+fontType+"'>"+timeText(i)+"</font></label></td>";
				html += "<td></td><td></td><td></td><td></td><tr>";
			}

			$("tbody#rstimeList").append(html);
		}// end of for----
		
		
		$("button#btn_Reserve").show();
	} // end of reservationList
			
	
	// 시간대 구성
	function timeText(val){
		
		var timeText="";
		
		if(val<10){
			stime="0"+val;
		}
		else{
			stime=val;
		}
		
		ntime= parseInt(val)+1;
		console.log((typeof(ntime)));
		sntime= ntime.toString();
		
		if(ntime<10){
			sntime="0"+sntime;
		}
		
		timeText=stime+":00 ~ "+sntime+":00";
		
		return timeText;
	}
	
	// 예약 등록버튼을 눌렀을 때 발생하는 함수
	function func_reserve(){
		var checkCnt= $("input:checkbox[name=rsCheck]:checked").length;
		
		if(checkCnt<1){
			alert("시간을 선택하세요.");
			return false; // 종료
		}
		
		else{
			
			// 선택한 회의실 값 모달창에 보내주기
			var roomname = $("input#roomname").val();
			
			var chkTime="";
			
			$("input:checkbox[name=rsCheck]:checked").each(function(){
				chkTime= chkTime+ $(this).val()+",";
			});
			
			if(chkTime.length>0){
				chkTime = chkTime.substring(0,chkTime.length-1);
			}
			
			showTime(chkTime);
			
			
			$("button[id='btn_Reserve']").attr("data-target","#myModal");
			$("td#roomname").html(roomname);
			
			
		}
	}
	
	// modal 창에 선택한 시간 보여주고 input태그에 선택한 시간 값 넣어주기
	function showTime(val){
		var strTime="";
		
		var arrTime= new Array();
		
		if(val.indexOf(',')>0){
			arrTime=val.split(',');
		}
		else{
			arrTime[0]=val;
		}
		
		for(var i=0;i<arrTime.length;i++){
			strTime=strTime+timeText(arrTime[i])+", ";
		}
		console.log(strTime.length);
		strTime=strTime.substring(0,strTime.length-2);
		$("td#strtime").html(strTime);
		$("input#rtime").val(val);
	}
	
	// 예약했던 회의실 삭제하기
	function deleteReserve(rsroomno){
		
		$.ajax({
     		url:"<%= ctxPath%>/t1/rsroom/delReserveRoom.tw",
     		type:"POST",
     		data:{"rsroomno":rsroomno},
     		dataType: "json",
     		success:function(json){
     		 	alert("예약내역이 삭제되었습니다.");
     		 	location.href="javascript:history.go(0);"; 
     		}
     		, error: function(request, status, error){
	            alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
  	      }
     	});
		
	}
	
	
	
</script>

<div id="sebumenu">
	<div style="background-color: #6c757d; color: #fff;"><a href="<%= ctxPath%>/t1/rsRoom.tw">회의실 대여신청</a></div>
	<div><a href="<%= ctxPath%>/t1/rsCar.tw">차량 대여신청</a></div>
	<div><a href="<%= ctxPath%>/t1/rsGoods.tw">사무용품 대여신청</a></div>
</div>


<div id="content" style="background-color: white; width: 1180px; padding-left: 50px; ">
	<h3 style="margin-top: 20px !important;">회의실 대여신청</h3>
	
	<div>
		<div id="calendarWrapper">
			<div id="calendar" style="width: 450px; margin-top: 50px;" ></div>
		</div>
		
	  
		<div class="room" >
			<table style="width: 100%; margin-bottom: 80px;">
		            <tr><td colspan="2">회의실 목록</td></tr>
		            <c:if test="${empty requestScope.roomList}">
	            		<tr><td colspan="2">회의실이 존재하지 않습니다.</td></tr>
	            	</c:if>
	            	<c:if test="${not empty requestScope.roomList}">
	        	 		<c:forEach var="roomvo" items="${requestScope.roomList}">
				        	<tr class="selectRoom">
					            <td id="findno" style="display: none;">${roomvo.roomno}</td>
					            <td id="findname"  style="width: 30%; text-align: left; padding-left: 15px;">${roomvo.roomname}</td>
					            <td style="width: 70%; text-align: left; border-left: none; padding-left: 15px; ">수용인원: ${roomvo.cappeople}명</td>
				            </tr>
			        	</c:forEach>
	          		</c:if>
	         </table>
	         <button id="btn_show" class="btn btn-secondary">예약현황보기</button>
		</div>
	</div>
	
	<div class="middle" style="margin-top: 100px;">
		<table  style="width: 90%;">
			<colgroup>
				<col width="20%">
				<col width="20%">
				<col width="15%">
				<col>
				<col width="10%">
			</colgroup>
			
			<thead>
				<tr>
					<th colspan="5">예약시간 선택</th>
				</tr>
				<tr>
				   <th>사용시간</th>
				   <th>신청부서</th>
				   <th>담당자</th>
				   <th>목적</th>
				   <th>삭제</th>
				</tr>
			</thead>
			<tbody id="rstimeList">
				<tr>
					<td colspan="5">입력된 정보가 없습니다.</td>
				</tr>
			</tbody>
		</table>
	</div>
	
	
	<div style="margin-top: 20px; margin-left: 500px; margin-bottom: 20px;">
		<button id="btn_Reserve" class="btn btn-secondary"  data-toggle="modal" data-target="" >등록</button>
	</div>
</div>



<!-- The Modal -->
  <div class="modal fade" id="myModal">
    <div class="modal-dialog modal-dialog-centered modal-lg">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">신청등록</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
         	<form name="reserveRoom">
         		<table style="width: 100%;">
         			<tr>
         				<td style="text-align: left; ">신청회의실</td>
         				<td id="roomname" style="text-align: left; padding-left: 5px;"></td>
         			</tr>
         			<tr>
         				<td style="text-align: left;">신청시간</td>
         				<td id="strtime" style="text-align: left; padding-left: 5px;"></td>
         			</tr>
         			<tr>
         				<td style="text-align: left;">목적</td>
         				<td style="text-align: left; padding-left: 5px;"><input type="text" id="purpose" class="form-control" name="rsubject"/></td>
         			</tr>
         		</table>
         		<!-- input 값에 사번, 직원명, 부서, 신청회의실번호, 신청시간, 용도 넣어줘서 이걸 insert할 때 사용하기 -->
         		<div>
					<input type="hidden" id="roomno" value="" name="fk_roomno"/>
					<input type="hidden" id="chgdate" value="" name="rdate"/>
					<input type="hidden" name="fk_employeeid" value="${sessionScope.loginuser.employeeid}"/>
					<input type="hidden" name="name" value="${sessionScope.loginuser.name}"/>
					<input type="hidden" name="rdepartment" value="${requestScope.dname}"/>
					<input type="hidden" id="rtime" value="" name="rtime"/>
				</div>
         	</form>
        </div>
        
        <!-- Modal footer -->
        <div class="modal-footer">
        	<button type="button" id="realReserve" class="btn btn-secondary" data-dismiss="modal">예약</button>
          <button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
        </div>
        
      </div>
    </div>
  </div>
  






<!-- 예약 현황 보기 클릭시 값 넘겨줄 때 사용 -->
<div>
	<input type="hidden" id="roomno" value=""/>
	<input type="hidden" id="roomname" value=""/>
	<input type="hidden" id="chgdate" value=""/>
</div>
