<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%
   String ctxPath = request.getContextPath();
%>

<link href='<%=ctxPath %>/resources/fullcalendar/main.min.css' rel='stylesheet' />
<style>

 div#gwContent{left: 80px;}
 
 div#calendarO{
 	border: solid 1px blue;
 	width: 480px;
 	margin: 10px 0px 50px 40px;
 	height: 550px;
 }
 
 /* 달력 css */
 div#calendarWrapper{
	float: left;
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
	height: 30px;
 } 
 
 .fc-event-time{
 	color: #fff;
 }
 
 .fc-daygrid-event-dot{
 	margin-left: 25px;
 }
 
 .fc-daygrid{
 	border: solid 1px #fff;
 }
  
  .fc-button-primary{
  	background-color: #333333;
  }
  
  
 div.circle{   
 	width: 8px;
 	height: 8px;
 	border-radius: 50%;
 	display: inline-block;
 	margin-left: 5px;
 	margin-right: 10px;
 }
 
 .fc-event-time{
 	display: none;
 }
 
 .fc-event-title{
 	display: none;
 }
 
 li{
	margin-left:-10px;
 }
   
  div#myInfo{
      border:solid 1px red;
      width:250px;
      font-size:12pt;
  }
  
  span.sebuInfo{
  	display:inline-block;
  }
  
  span.sebuInfo:hover{
  	  cursor:pointer;
  	  color:blue;
  	  font-weight:bolder;
  }	
  
  <%-- quickMenu css 한수연 시작 --%>
  
  button.quickMenuBt{
	  background-color: #dfe8f3;
	  width: 120px;
	  height: 50px;
	  border: none;
	  color:#000099;
	  font-size: 11pt;
	  border-radius: 5%;
  }
  
  button.quickMenuBt:hover{
	  background-color: #0066cc;
	  color: #dfe8f3;
	  font-weight: bold;
  }
  
  span.quickCnt{
  	color: red;
  	font-weight: bold;
  	text-decoration: underline;
  }
  
  <%-- quickMenu css 한수연 끝 --%>
  
  <%-- 내통계 css 백원빈 시작 --%>
	#container {
	    height: 400px;
	}
	
	.highcharts-figure, .highcharts-data-table table {
	    min-width: 310px;
	    max-width: 800px;
	    margin: 1em auto;
	}
	
	#datatable {
	    font-family: Verdana, sans-serif;
	    border-collapse: collapse;
	    border: 1px solid #EBEBEB;
	    margin: 10px auto;
	    text-align: center;
	    width: 100%;
	    max-width: 500px;
	}
	#datatable caption {
	    padding: 1em 0;
	    font-size: 1.2em;
	    color: #555;
	}
	#datatable th {
		font-weight: 600;
	    padding: 0.5em;
	}
	#datatable td, #datatable th, #datatable caption {
	    padding: 0.5em;
	}
	#datatable thead tr, #datatable tr:nth-child(even) {
	    background: #f8f8f8;
	}
	#datatable tr:hover {
	    background: #f1f7ff;
	}
    
    span#spanData{
    	background-color:#b3ecff;
    }
    
    ul#vacationUl li{
    	line-height: 40px;
    	font-size:12pt;
    }
  <%-- 내통계 css 백원빈 끝 --%>
  
  <%-- word cloud 백원빈 시작 --%>
  .highcharts-figure, .highcharts-data-table table {
    min-width: 320px; 
    max-width: 800px;
    margin: 1em auto;
	}
	
	.highcharts-data-table table {
		font-family: Verdana, sans-serif;
		border-collapse: collapse;
		border: 1px solid #EBEBEB;
		margin: 10px auto;
		text-align: center;
		width: 100%;
		max-width: 500px;
	}
	.highcharts-data-table caption {
	    padding: 1em 0;
	    font-size: 1.2em;
	    color: #555;
	}
	.highcharts-data-table th {
		font-weight: 600;
	    padding: 0.5em;
	}
	.highcharts-data-table td, .highcharts-data-table th, .highcharts-data-table caption {
	    padding: 0.5em;
	}
	.highcharts-data-table thead tr, .highcharts-data-table tr:nth-child(even) {
	    background: #f8f8f8;
	}
	.highcharts-data-table tr:hover {
	    background: #f1f7ff;
	}
	<%-- word cloud 백원빈 끝 --%>
	
</style>

<!-- full calendar에 관련된 script -->
<script src='<%=ctxPath %>/resources/fullcalendar/main.min.js'></script>
<script src='<%=ctxPath %>/resources/fullcalendar/ko.js'></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>

<!-- 퀵메뉴에 쓰이는 차트 script -->
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

<%-- 내통계에 쓰이는 차트 script --%>
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/data.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script src="https://code.highcharts.com/modules/accessibility.js"></script>
 
<%-- word Cloud 차트 script --%>
<script src="https://code.highcharts.com/modules/wordcloud.js"></script>
<script src="https://code.highcharts.com/modules/export-data.js"></script>


<script type="text/javascript">
   $(document).ready(function(){
      
      // div#sidemenu와 div#content길이 맞추기
      // func_height1();
      
      // 시계 보여주기
      func_loopDate();
      
      
      $("div#vacation").hide();
      
      // 휴가정보 클릭시
      $("span#vacationInfo").click(function(){
         $("div#vacation").show();
         $("div#indolence").hide();
      });
      
      // 근태정보 클릭 시
      $("span#indolenceInfo").click(function(){
         $("div#vacation").hide();
         $("div#indolence").show();
      });
      
      
      var intime = $("span#intime").text();
      var todayDate = func_todayDate(); // 2020-01-01 hh24
     
      // 출근을 찍고 나서 다시 로그인을 했을때
      $.ajax({
        url:"<%=ctxPath %>/t1/selectIntime.tw",
        type:"get",
        data:{"fk_employeeid":"${loginuser.employeeid}",
             "gooutdate":todayDate},
        dataType:"json",
        success:function(json){
           
              if(json.intime != ""){
                 if(json.latenoTime=='1'){
                     $("span#intime").text(json.intime+'(지각)');
                  }
                  else{
                     $("span#intime").text(json.intime+'(정상출근)');
                  }
              }else{
                 $("span#intime").text("(미출근)");
              }
         },
         error: function(request, status, error){
               alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
           }
        
     });// end of ajax(insert)
      
      // 출근 버튼 클릭 시(출근을 안찍은 상황)
      $("span#intimeButton").click(function(){
         
         var intime = $("span#intime").text();
         if(intime=="(미출근)"){
         
            // 출퇴근기록 테이블에 insert하기
            $.ajax({
               url:"<%=ctxPath %>/t1/insertSelectIntime.tw",
               type:"post",
               data:{"fk_employeeid":"${loginuser.employeeid}",
                    "gooutdate":todayDate},
               dataType:"json",
               success:function(json){
                  
                     if(json.latenoTime=='1'){
                        $("span#intime").text(json.intime+'(지각)');
                     }
                     else{
                        $("span#intime").text(json.intime+'(정상출근)');
                     }
                  
               },
               error: function(request, status, error){
                     alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
                 }
               
            });// end of ajax(insert)
         }
         else{
            alert("이미 출근버튼을 클릭하셨습니다.");
         }
         
      }); // end of $("span#intime").click(function(){
      
         
         
      // 퇴근을 찍고 나서 다시 로그인을 했을때
       $.ajax({
        url:"<%=ctxPath %>/t1/selectOuttime.tw",
        type:"get",
        data:{"fk_employeeid":"${loginuser.employeeid}",
             "gooutdate":todayDate},
        dataType:"json",
        success:function(json){
           
              if(json.outtime != ""){
                 
                  $("span#outtime").text(json.outtime);

              }else{
                 $("span#outtime").text("(미퇴근)");
              }
         },
         error: function(request, status, error){
               alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
           }
        
     });// end of ajax(insert)     
         
         
      // 퇴근버튼을 클릭 했을때      
      $("span#outtimeButton").click(function(){   
         
      
         var intime = $("span#intime").text();
         var outtime = $("span#outtime").text();
         
         if(intime!="(미출근)"){ // 출근시간이 찍혀 있을때
            
            if(outtime=="(미퇴근)"){
   
               // 출퇴근기록 테이블에 insert하기
               $.ajax({
                  url:"<%=ctxPath %>/t1/insertSelectOuttime.tw",
                  type:"post",
                  data:{"fk_employeeid":"${loginuser.employeeid}",
                       "gooutdate":todayDate},
                  dataType:"json",
                  success:function(json){
                     
                        $("span#outtime").text(json.outtime); // 16:25:46
                        
                        var lateTime = json.outtime;
                        var lateHH = Number(lateTime.substring(0,2));
                        var lateMM = Number(lateTime.substring(3,5));
                        
                        if(lateHH == 19 && lateMM>=45){
 							location.href='<%= ctxPath%>/t1/salaryDetail.tw?from=homepage';
                        }
                        else if(lateHH>20){
                        	location.href='<%= ctxPath%>/t1/salaryDetail.tw?from=homepage';
                        }
                        
                  },
                  error: function(request, status, error){
                        alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
                    }
                  
               });// end of ajax(insert)
            }
            else{
               alert("이미 퇴근버튼을 클릭하셨습니다.");
            }
         }// end of if(intime!="(미출근)"){
         else{// 출근시간이 안 찍혀 있을때
            alert("출근버튼을  먼저 클릭하세요.");     
         }
      }); // end of $("span#outtime").click(function(){
      
      
    	<%--  달력 생성(오다윤)  --%>   
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
 	      dayMaxEventRows: true, // for all non-TimeGrid views
 	     	views: {
 	        	timeGrid: {
 	        		 dayMaxEventRows: 1// adjust to 6 only for timeGridWeek/timeGridDay
 	      		}
 	    	 },
 	       events:function(info, successCallback, failureCallback){
 	    	 
 	       
 			   $.ajax({
 			   		url: "<%= ctxPath%>/t1/showMyCalendarHome.tw",
 			   		data: {"employeeid":"${loginuser.employeeid}"},
 			   		type: "get",
 			   		dataType: "json",
 			   		success:function(json){
			   			var events =[];
			   			if(json!=null){
			   				$.each(json, function(index, item) {
			   				 var startdate=moment(item.startdate).format('YYYY-MM-DD HH:mm:ss');
			                 var enddate=moment(item.enddate).format('YYYY-MM-DD HH:mm:ss');
			   					
			                 events.push({
	 			   					id: item.sdno,
	 			   					title: item.subject,
	 			   					start: startdate,
	 			   					end: enddate,
	 			   					color: "#333333"
	                      		}); // events.push
	 			   		
	 			   			
			   				}); //each
			   			}
			   			console.log(events);     
			   			successCallback(events); 
 			   		}
 			   }); // end of $.ajax
 			   
 		   }, //events:function end	       	  
           dateClick: function(info) {
         	//  alert('Date: ' + info.dateStr);
         	    $(".fc-day").css('background','none'); // 현재 날짜 배경색 없애기
         	    info.dayEl.style.backgroundColor = '#fff';
         	    date=info.dateStr;
         	    showMyList(date); // 해당 날짜에 대한 캘린더 정보를 불러오는 함수 
           }
         });

         calendar.render();
         calendar.setOption('height', 450);
    	
         
         $("div#infoCalendar").hide();
         
    	 // 오늘 날짜와 관련된 일정 보여주기
         $.ajax({
 			url: "<%= ctxPath%>/t1/todayMyCal.tw",
 			data: {"employeeid": "${loginuser.employeeid}"},
 			type: "post",
 			dataType: "json",
 			success:function(json){
 				
 				var html ="";
 				
 				if(json.length==0){
 					html+="<tr><td>등록된 일정이 없습니다</td></tr>";
 				}
 				if(json.length>0){
 					
 					html="<table class='table'>";
 	   				$.each(json, function(index, item) {
 	   					html+="<tr><td>"+item.startdate+" ~ "+item.enddate+"</td>";
 	   					html+="<tr><td style='padding-left: 10px; font-size: 10pt; border-top: solid 0px;'>"+item.stime+"~"+item.etime+"<div class='circle' style='background-color:"+item.color+"'></div><a href='<%= ctxPath%>/t1/detailSchedule.tw?sdno="+item.sdno+"'>"+item.subject+"</a></td></tr>";
 	   				}); //each
 	   				hrml="</table>";
 				}
 				$("div#todayCal").html(html);
 				$("div#todayCal").show();
 				$("div#infoCalendar").hide();
 				
 			},
 			error: function(request, status, error){
                alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
            }
 		});  
    	
    	 
        <%-- 퀵메뉴에 쓰이는 차트 script 시작 한수연 ---%>
    	$.ajax({
    		url: "<%= ctxPath%>/t1/quickMenuInfo.tw",
 			data: {"employeeid": "${loginuser.employeeid}"},
 			type: "POST",
 			dataType: "JSON",
 			success:function(json){
 				
 				var emptyCnt=0;
 				
 				// 읽지않은 메일, 결재 진행 중 문서, 최근 결재완료 문서가 모두 0건인 경우에도 차트를 표시해 주기 
 				if(json.notReadCnt==0 && json.ingDocuCnt==0 && json.doneDocuCnt==0){
 					emptyCnt=1;
 				}
 				
 				// ========== 퀵메뉴 차트 코드 시작
 				google.charts.load("current", {packages:["corechart"]});
 		        google.charts.setOnLoadCallback(drawChart);
 		        function drawChart() {
	 		        var data = google.visualization.arrayToDataTable([
			 		             ['quickMenu', 'cnt'],
			 		             ['읽지 않은 메일', json.notReadCnt],
			 		             ['결재 진행 중 문서', json.ingDocuCnt],
			 		             ['최근 결재 완료 문서', json.doneDocuCnt],
			 		             ['해당 사항 없음', emptyCnt]
	 		          		   ]);
	
	 		        var options = {
				 		             pieHole: 0.4,
				 		             chartArea:{left:0,top:70,width:'100%',height:'70%'},
				 		             colors: ["#006680", "#00b8e6", "#0099ff","#c0bfbf"],
				 		             legend: 'none',
				 		             backgroundColor: "transparent"
	 		            		   };
	
	 		         var chart = new google.visualization.PieChart(document.getElementById('donutchart'));
	 		         chart.draw(data, options);
 		         }
 		     	// ========== 퀵메뉴 차트 코드 끝
 		     	
 		     	// legend에 각 값 넣어주기
 		     	$("span#notReadCnt").text(json.notReadCnt);
 		     	$("span#ingDocuCnt").text(json.ingDocuCnt);
 		     	$("span#doneDocuCnt").text(json.doneDocuCnt);
			 			
 			},
 			error: function(request, status, error){
                alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
            }
    	}); // end of $.ajax({-------------------------------- 
         
        <%-- 퀵메뉴에 쓰이는 차트 script 끝 한수연 ---%> 
    	 
    	<%-- 백원빈 내통계 작업시작 --%>
    	$("table#datatable").hide();
	
        Highcharts.chart('container', {
            data: {
                table: 'datatable'
            },
            chart: {
                type: 'column'
            },
            title: {
                text: '<div style=color:red;><span id=spanData style=font-size:10pt;>내 근로시간 : 주 ${totalhour}/52시간</span></div>'
            },
            yAxis: {
                allowDecimals: false,
                title: {
                    text: '시간'
                },
                max:14,
                min:0
            },
            tooltip: {
                formatter: function () {
                    return '<b></b><br/>' +
                        this.point.y + '시간 ';
                }
            }
        });
        <%-- 백원빈 내통계 작업 끝 --%>
    	
        <%-- 백원빈 word-cloud 작업 시작 --%>
        var text = '${word}';
        var lines = text.split(/[,\. ]+/g),
            data = Highcharts.reduce(lines, function (arr, word) {
                var obj = Highcharts.find(arr, function (obj) {
                    return obj.name === word;
                });
                if (obj) {
                    obj.weight += 1;
                } else {
                    obj = {
                        name: word,
                        weight: 1
                    };
                    arr.push(obj);
                }
                return arr;
            }, []);

        Highcharts.chart('container2', {
            accessibility: {
                screenReaderSection: {
                    beforeChartFormat: '<h5>{chartTitle}</h5>' +
                        '<div>{chartSubtitle}</div>' +
                        '<div>{chartLongdesc}</div>' +
                        '<div>{viewTableButton}</div>'
                }
            },
            series: [{
                type: 'wordcloud',
                data: data,
                name: 'Occurrences'
            }],
            title: {
                text: 'T1works 검색어 현황'
            }
        });
        	
        <%-- 백원빈 word-cloud 작업 끝 --%>
        
   }); // end of $(document).ready(function(){
      
   // Function Declaration
   function func_currentDate(){
      
      var now = new Date();
      var year = now.getFullYear();
      var month = now.getMonth()+1;
      
      if(month<10){
          month = "0"+month;
       }
      
      var date = now.getDate();  // 현재일
      if(date<10){
    	  date = "0"+date;
       } 
      
       var hours = now.getHours(); // 현재시각
       
       if(hours<10){
          hours = "0"+hours;
       }
       
       var minutes = now.getMinutes(); // 현재분
       
       if(minutes<10){
          minutes = "0"+minutes;
       }
       
       var seconds = now.getSeconds(); // 현재초
       
       if(seconds<10){
          seconds = "0"+seconds;
       }
       
       var day = now.getDay(); // 현재요일명 (0~6)
         
       var dayName ="";
       
       switch (day) {
      case 0:
         dayName = "일요일"
         break;
         
      case 1:
         dayName = "월요일"
         break;
         
      case 2:
         dayName = "화요일"
         break;
         
      case 3:
         dayName = "수요일"
         break;
         
      case 4:
         dayName = "목요일"
         break;
         
      case 5:
         dayName = "금요일"
         break;
         
      case 6:
         dayName = "토요일"
         break;

      }
   
      var today = year+"-"+month+"-"+date+" "+dayName+" "+hours+":"+minutes+":"+seconds;
      
      return today;
   }// end of function func_currentDate(){
      
   function func_loopDate(){
          $("div#timer").html(func_currentDate());
          setTimeout('func_loopDate()',1000);
   }
   
   // 오늘 년도-월-일만 뽑아오는 함수
   function func_todayDate(){
      var now = new Date();
      var year = now.getFullYear();
      var month = now.getMonth()+1;
      
      if(month<10){
         month = "0"+month;
       }
      
      var date = now.getDate();  // 현재일

      var todayDate =  year+"-"+month+"-"+date;
      return todayDate;
   }
   
	// 달력에서 클릭한 날짜에 대한 캘린더 정보를 불러오는 함수 
   function showMyList(date){
	   $.ajax({
			url: "<%= ctxPath%>/t1/myCalendarInfo.tw",
			data: {"employeeid": "${loginuser.employeeid}"
				  ,"date":date},
			type: "post",
			dataType: "json",
			success:function(json){
				
				var html ="";
				
				if(json.length==0){
					html+="<tr><td>등록된 일정이 없습니다</td></tr>";
				}
				if(json.length>0){
					
					html="<table class='table'>";
	   				$.each(json, function(index, item) {
	   					html+="<tr><td>"+item.startdate+" ~ "+item.enddate+"</td>";
	   					html+="<tr><td style='padding-left: 10px; font-size: 10pt; border-top: solid 0px;'>"+item.stime+"~"+item.etime+"<div class='circle' style='background-color:"+item.color+"'></div><a href='<%= ctxPath%>/t1/detailSchedule.tw?sdno="+item.sdno+"'>"+item.subject+"</a></td></tr>";
	   				}); //each
	   				hrml="</table>";
				}
				$("div#todayCal").hide();	
 				$("div#infoCalendar").show();	
				$("div#infoCalendar").html(html);
				
			},
			error: function(request, status, error){
               alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
           }
		});
   }
	
</script>




<div id="content" style="width: 1580px;">
  
  <div id="myInfo" style="margin: 50px 0px 50px 50px; width: 380px; height: 820px; float:left;">
  	 <div style="padding-left:130px;"><img src="<%= ctxPath%>/resources/images/bwb/person.jpg" style="width:120px; height:120px;"></div>
     <div id="nameDep" style="border-bottom:solid 1px black; text-align:center; margin-top:10px; margin-bottom:10px;">
     <span id="name">${loginuser.name}
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
       </c:if>
     </span>
     <span id="department">
        <c:if test="${loginuser.fk_dcode eq 1}">
            (CS1팀)
       </c:if>
       <c:if test="${loginuser.fk_dcode eq 2}">
            (CS2팀)
       </c:if>
       <c:if test="${loginuser.fk_dcode eq 3}">
            (CS3팀)
       </c:if>
       <c:if test="${loginuser.fk_dcode eq 4}">
             (인사팀)
       </c:if>
       <c:if test="${loginuser.fk_dcode eq 5}">
            (총무팀)
       </c:if>
     </span>
     </div>
     <div id="loginIp" style="font-size:10pt; text-align:center; margin-top:10px;">
        	접속IP : ${loginip}
     </div>
     <div id="timer" style="text-align:center; margin-top:15px;"></div>
     <div id="sebuMenu" style="border-bottom:solid 1px black; border-top:solid 1px black; height:50px; margin-top:20px; padding-top:10px;">
	     <span id="indolenceInfo" style="border-bottom:solid 1px black; font-size:15pt; margin-right:90px; margin-left:15px; ">근태정보</span>
	     <span id="vacationInfo"  style="border-bottom:solid 1px black; font-size:15pt;">휴가정보</span>
     </div>
     <div id="indolence">
        <div style="margin-top:10px; margin-left:7px;">출퇴근시간</div>
        <div id="buttonDiv" style="margin-top:15px; border:solid 1px black; height:50px; font-size:16pt; padding-top:10px;">
	        <span id="intimeButton" style="background-color: #cce6ff;">출근</span> <span id="intime" style="margin-right:60px; font-size:12pt;"></span>
	        <span id="outtimeButton" style="background-color: #cce6ff;">퇴근</span> <span id="outtime" style="font-size:12pt;"></span>
        </div>
        <div style="margin-top:20px; border-top:solid 1px black;">
           <span style="margin-left:15px; font-size:15pt;" onclick="location.href='<%= ctxPath%>/t1/myMonthIndolence.tw'">월별근태현황</span>
           <c:if test="${loginuser.fk_pcode eq 3}">
            <span style="margin-left:65px; font-size:15pt;" onclick="location.href='<%= ctxPath%>/t1/depMonthIndolence.tw'">부서근태현황</span>
           </c:if>
        </div>
       	<figure class="highcharts-figure">
    	<div id="container2" style="width:330px; height:300px; overflow:hidden;"></div>
		</figure>
     </div>
     <div id="vacation" style="margin-top:5px;">
     	<ul id="vacationUl">
     		<li>입사일자 : ${fn:substring(loginuser.hiredate,0,4)}년 ${fn:substring(loginuser.hiredate,5,7)}월 ${fn:substring(loginuser.hiredate,8,10)}일</li>
     		<li>총 연차일수 : ${offMap.totalOffCnt}일</li>
     		<li>사용 연차 일수 : ${offMap.useOffCnt}일</li>
     		<li>남은 연차 일수 : ${offMap.leftOffCnt}일</li>
     	</ul>
     </div>
     
  </div><%-- end of div(myInfo) 백원빈 --%>
  
  
  
  <%-- =================== 백원빈 내통계+word-cloud 시작 =================== --%>
  <div id="myStatic" style="border: solid 1px green; float: left; margin: 50px 0px 40px 40px; width: 450px; height: 280px; ">
  	<figure class="highcharts-figure">
    <div id="container" style="height:250px"></div>

    <table id="datatable">
        <tbody>
            <tr>
                <th></th>
                <td>근무시간</td>
            </tr>
            <c:forEach var="hourMap" items="${hourList}">
            	<tr>
	                <th>${hourMap.gooutdate}</th>
	                <td>${hourMap.doneHour}</td>
           		</tr>	
            </c:forEach>  
        </tbody>
	    </table>
	</figure>

  </div>
  <%-- =================== 백원빈 내통계+word-cloud 끝 =================== --%>
  
  
  <%-- =================== 한수연 시작 =================== --%>
  <div id="quickMenu" style="border: solid 2px #bbc3c3; float: left; margin: 50px 0px 40px 40px; width: 600px; height: 280px;">
  	<div style="border: solid 2px #0071bd; background-color:#0071bd; color:#fff; font-size:15pt; width:200px; height:35px; text-align: center; padding-top: 2px; ">
  		My Quick Menu
  	</div>
  	<div id="donutchart" style="width: 300px; height: 280px; float:left; border: solid 0px red; position:relative; top:-40px;"></div>
	<div style='font-size:13pt; float:left; border: solid 0px red;'>
		<div style='margin-bottom: 18px;'>
			<button type="button" class='quickMenuBt' onclick='location.href="<%=ctxPath%>/t1/mail.tw"'>받은메일함</button>
			<button type="button" class='quickMenuBt' style='margin-left:20px;' onclick='location.href="<%=ctxPath%>/t1/myDocuNorm_send.tw"'>문서 발신함</button>
		</div>
		<div>
			<span style='color:#006680; font-size: 22pt; padding-left:5px;'>■</span>
			&nbsp;&nbsp;읽지 않은 메일 <span class="quickCnt" id="notReadCnt"></span>&nbsp;건
		</div>
		<div>
			<span style='color:#00b8e6; font-size: 22pt; padding-left:5px;'>■</span>
			&nbsp;&nbsp;결재 진행 중 문서 <span class="quickCnt" id="ingDocuCnt"></span>&nbsp;건
		</div>
		<div>
			<span style='color:#0099ff; font-size: 22pt; padding-left:5px;'>■</span>
			&nbsp;&nbsp;최근 결재 완료 문서 <span class="quickCnt" id="doneDocuCnt"></span>&nbsp;건
		</div>
	</div> 
  </div>
  <%-- =================== 한수연 끝 =================== --%>
  
  
  
  <%-- 위의 float에서 한칸 아래로 내려오기 위한 빈 div --%>
  <div style="float: left; width: 800px;"></div>
  
  <div id="simpleNotice" style="border: solid 1px gray; float: left; width: 580px; height: 230px; margin: 10px 0px 20px 40px;">
  	다님님 파트(공지사항)
  </div>
  
  <%-- start of div(calendar) 오다윤 --%>
  <div id="calendarO" style="float: right;">
		<div id="calendar"></div>
		<div id="todayCal" style="margin-top:10px; padding-left: 10px;"></div>
		<div id="infoCalendar" style="margin-top:10px; padding-left: 10px;"></div>
  </div>

  <%-- end of div(calendar) 오다윤 --%>
  
  <div id="weather" style="border: solid 1px blue; float: left; margin: 10px 0px 50px 40px; width: 580px; height: 230px; ">
 	 다윤님 파트 (날씨)
  </div>
  
</div>