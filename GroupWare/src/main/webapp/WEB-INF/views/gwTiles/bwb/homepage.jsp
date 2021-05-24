<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%
	String ctxPath = request.getContextPath();
%>
<script type="text/javascript">
   $(document).ready(function(){
      
      
      // div#sidemenu와 div#content길이 맞추기
      func_height1();
      
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
    		  
	    	  if(outtime==""){
	
		    	  // 출퇴근기록 테이블에 insert하기
		    	  $.ajax({
		    		  url:"<%=ctxPath %>/t1/insertSelectOuttime.tw",
		    		  type:"post",
		    		  data:{"fk_employeeid":"${loginuser.employeeid}",
		    			    "gooutdate":todayDate},
		    		  dataType:"json",
		    		  success:function(json){
							
	    			  		$("span#outtime").text(json.outtime);
	
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
   
</script>

<style>
	
	div#myInfo{
		border:solid 1px red;
	}

</style>

<div id="content">
  <div id="myInfo">
  	<div id="image"><img src=""></div>
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
	         CS1팀
	    </c:if>
	    <c:if test="${loginuser.fk_dcode eq 2}">
        	 CS2팀
	    </c:if>
	    <c:if test="${loginuser.fk_dcode eq 3}">
        	 CS3팀
	    </c:if>
	    <c:if test="${loginuser.fk_dcode eq 4}">
        	  인사팀
	    </c:if>
	    <c:if test="${loginuser.fk_dcode eq 5}">
        	 총무팀
	    </c:if>
  	</span>
  	<div id="loginIp">
  		${loginip}
  	</div>
  	<div id="timer"></div>
  	<span id="indolenceInfo">근태정보</span>
  	<span id="vacationInfo">휴가정보</span>
  	<div id="indolence">
  		<div>출퇴근시간</div>
  		<span id="intimeButton">출근</span> <span id="intime"></span>
  		<span id="outtimeButton">퇴근</span> <span id="outtime"></span>
  		<div>
  			<span>월별근태현황</span>
  			<c:if test="${loginuser.fk_pcode eq 3}">
	         <span>부서근태현황</span>
	    	</c:if>
  		</div>
  		
  	</div>
  	<div id="vacation">
  		<div id="hiredate">입사일자 : ${fn:substring(loginuser.hiredate,0,10)}</div>
  		<div id="offcnt">총 연차일수 : XX일</div>
  		<div id="useOffcnt">사용 연차 일수 : XX일</div>
  		<div id="leftOffcnt">남은 연차 일수 : XX일</div>
  	</div>
  	
  </div>
</div>
