<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String ctxPath= request.getContextPath(); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">

	div#salaryContainer{
		border: solid 0px red;
		width:70%;
		margin: 0 auto;
		position: relative;
		left: -150px;
	}

	div#salaryTitle{
		border: solid 0px red;
		margin: 70px 0px 30px 50px;
		font-size: 22pt;
		font-weight: bold;
	}
	
	table#salaryListTable tr{
		border-top: solid 1px #003d66;
		border-bottom: solid 1px #003d66;
		text-align: center;
		height: 50px;
	}
	
	select#salaryYear {
		cursor: pointer;
		width: 120px;
		height: 30px;
	}

</style>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript">
	
	$(document).ready(function(){
		
		// 특정 직원의 입사년도부터 시작하여 현재년도까지 select option 만들기
		var currentYear=Number("${currentYear}");
		var hireYear=Number("${hireYear}");
		
		var html="";
		for(var i=currentYear; i>=hireYear; i--) {html+="<option value="+i+">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+i+"년</option>";}
		$("select#salaryYear").html(html);

		// 선택한 년도의 월급이 존재하는 월들만 행들로 나타내주는 함수 호출 (default 현재년도)
		salaryListInfoByYear(currentYear);
		
		// 검색년도 change 이벤트 구현
		$("select#salaryYear").bind('change',function(){
			var selectedYear= $(this).val();
			salaryListInfoByYear(selectedYear);
		}); // end of $("select#salaryYear").bind('change',function(){-----
		
	}); // end of $(document).ready(function(){-----
	
		
	// === function declaration ===
	
	// 선택한 년도의 월급이 존재하는 월들만 행들로 나타내주는 함수 (default 현재년도)
	function salaryListInfoByYear(year){ 
		
		var currentYear=Number("${currentYear}");
		var hireYear=Number("${hireYear}");
		var currentMonth= Number("${currentMonth}");
		var hireMonth= Number("${hireMonth}");
		var hireDay= Number("${hireDay}");
		year= Number(year); // 년도 숫자로 변경
		
		
		$.ajax({
			url:"<%=ctxPath%>/t1/salaryListInfoByYear.tw",
	   		type:"POST",
	   		data:{"employeeid":"${loginuser.employeeid}"},
	   		dataType:"json",
	   		success:function(json){
	   			
	   			var html= '<tr style="height:50px; background-color: #003d66; color:#fff; font-weight: bold; ">'+
							  '<th style="width: 10%; text-align: center; border: solid 0px red;">순번</th>'+
							  '<th style="width: 30%; text-align: center; border: solid 0px red;">근무기간</th>'+
							  '<th style="width: 20%; text-align: center; border: solid 0px red;">소속부서</th>'+
							  '<th style="width: 10%; text-align: center; border: solid 0px red;">직위</th>'+
							  '<th style="text-align: center; border: solid 0px red;">급여명세서</th>'+
						  '</tr>'+
	   					  '<tr class="salaryListTr">';
	   			var cnt=1;  // 순번 표시를 위함
	   			
	   			if(currentYear==year){ // 1) 현재년도인경우 현재월 전월 까지만 행 표시
	   				
	   				for(var i=1;i<currentMonth;i++){
	   					html+= "<td>"+cnt+"</td>";
	   					 
			   			if(i==2){ // 2월 윤달 고려하여 근무기간 설정
			   				if(year%4==0) html+="<td>"+year+"년&nbsp;0"+i+"월&nbsp;01일&nbsp;&nbsp;-&nbsp;&nbsp;"+year+"년&nbsp;0"+i+"월&nbsp;29일</td>"; // 윤달인 경우
			   				else html+="<td>"+year+"년&nbsp;0"+i+"월&nbsp;01일&nbsp;&nbsp;-&nbsp;&nbsp;"+year+"년&nbsp;0"+i+"월&nbsp;28일</td>"; // 윤달이 아닌 경우
			   			}		 
			   			else if(i==4 || i==6 || i==9 ||i==11){ // 30일까지 존재하는 달
			   				if(i<10) html+="<td>"+year+"년&nbsp;0"+i+"월&nbsp;01일&nbsp;&nbsp;-&nbsp;&nbsp;"+year+"년&nbsp;0"+i+"월&nbsp;30일</td>"; // 월이 2자리가 아닌 경우 앞에 0 표시
			   				else html+="<td>"+year+"년&nbsp;"+i+"월&nbsp;01일&nbsp;&nbsp;-&nbsp;&nbsp;"+year+"년&nbsp;"+i+"월&nbsp;30일</td>";  // 월이 2자리인 경우 그대로 표시
			   			}		 
			   			else{ // 31일까지 존재하는 달
			   				if(i<10) html+="<td>"+year+"년&nbsp;0"+i+"월&nbsp;01일&nbsp;&nbsp;-&nbsp;&nbsp;"+year+"년&nbsp;0"+i+"월&nbsp;31일</td>"; // 월이 2자리가 아닌 경우 앞에 0 표시
			   				else html+="<td>"+year+"년&nbsp;"+i+"월&nbsp;01일&nbsp;&nbsp;-&nbsp;&nbsp;"+year+"년&nbsp;"+i+"월&nbsp;31일</td>"; // 월이 2자리인 경우 그대로 표시
			   			}
			   			
				   	   	html+=     "<td>"+json.dname+"</td>"+
				   				   "<td>"+json.pname+"</td>"+
				   				   "<td>"+
				   						"<a href='javascript:getSalaryDetail("+year+","+i+");' class='btn btn-primary btn-sm' style='width:50% !important;'>"+
				   						 	 "<span class='glyphicon glyphicon-print'></span>&nbsp;&nbsp;&nbsp;명세서&nbsp;출력"+
				   						"</a>"+
				   				   "</td>"+
			   				   "</tr>";
			   			cnt++;
					} // end of for---------------
	   			} // end of if------------------
	   			else if(hireYear==year){ // 2) 입사년도인경우 입사월부터 행 표시
	   					
	   				for(var i=hireMonth;i<13;i++){
	   					html+= "<td>"+cnt+"</td>";
	   					
	   					if(cnt==1){  // 입사년도 첫 달인경우 입사일로 찍어주기
	   						
	   						if(hireDay<10){ // 입사일이 두자리가 아닌 경우 앞에 0 붙여주기
	   							if(i==2){ // 2월 윤달 고려하여 근무기간 설정
					   				if(year%4==0) html+="<td>"+year+"년&nbsp;0"+i+"월&nbsp;0"+hireDay+"일&nbsp;&nbsp;-&nbsp;&nbsp;"+year+"년&nbsp;0"+i+"월&nbsp;29일</td>"; // 윤달인 경우
					   				else html+="<td>"+year+"년&nbsp;0"+i+"월&nbsp;0"+hireDay+"일&nbsp;&nbsp;-&nbsp;&nbsp;"+year+"년&nbsp;0"+i+"월&nbsp;28일</td>"; // 윤달이 아닌 경우
					   			}		 
					   			else if(i==4 || i==6 || i==9 ||i==11){ // 30일까지 존재하는 달
					   				if(i<10) html+="<td>"+year+"년&nbsp;0"+i+"월&nbsp;0"+hireDay+"일&nbsp;&nbsp;-&nbsp;&nbsp;"+year+"년&nbsp;0"+i+"월&nbsp;30일</td>"; // 월이 2자리가 아닌 경우 앞에 0 표시
					   				else html+="<td>"+year+"년&nbsp;"+i+"월&nbsp;0"+hireDay+"일&nbsp;&nbsp;-&nbsp;&nbsp;"+year+"년&nbsp;"+i+"월&nbsp;30일</td>";  // 월이 2자리인 경우 그대로 표시
					   			}		 
					   			else{ // 31일까지 존재하는 달
					   				if(i<10) html+="<td>"+year+"년&nbsp;0"+i+"월&nbsp;0"+hireDay+"일&nbsp;&nbsp;-&nbsp;&nbsp;"+year+"년&nbsp;0"+i+"월&nbsp;31일</td>"; // 월이 2자리가 아닌 경우 앞에 0 표시
					   				else html+="<td>"+year+"년&nbsp;"+i+"월&nbsp;0"+hireDay+"일&nbsp;&nbsp;-&nbsp;&nbsp;"+year+"년&nbsp;"+i+"월&nbsp;31일</td>"; // 월이 2자리인 경우 그대로 표시
					   			}	
	   						} // end of if----------------------------
	   						else{ // 입사일이 두자리인 경우 그대로 표시
	   							if(i==2){ // 2월 윤달 고려하여 근무기간 설정
					   				if(year%4==0) html+="<td>"+year+"년&nbsp;0"+i+"월&nbsp;"+hireDay+"일&nbsp;&nbsp;-&nbsp;&nbsp;"+year+"년&nbsp;0"+i+"월&nbsp;29일</td>"; // 윤달인 경우
					   				else html+="<td>"+year+"년&nbsp;0"+i+"월&nbsp;"+hireDay+"일&nbsp;&nbsp;-&nbsp;&nbsp;"+year+"년&nbsp;0"+i+"월&nbsp;28일</td>"; // 윤달이 아닌 경우
					   			}		 
					   			else if(i==4 || i==6 || i==9 ||i==11){ // 30일까지 존재하는 달
					   				if(i<10) html+="<td>"+year+"년&nbsp;0"+i+"월&nbsp;"+hireDay+"일&nbsp;&nbsp;-&nbsp;&nbsp;"+year+"년&nbsp;0"+i+"월&nbsp;30일</td>"; // 월이 2자리가 아닌 경우 앞에 0 표시
					   				else html+="<td>"+year+"년&nbsp;"+i+"월&nbsp;01일&nbsp;&nbsp;-&nbsp;&nbsp;"+year+"년&nbsp;"+i+"월&nbsp;30일</td>";  // 월이 2자리인 경우 그대로 표시
					   			}		 
					   			else{ // 31일까지 존재하는 달
					   				if(i<10) html+="<td>"+year+"년&nbsp;0"+i+"월&nbsp;"+hireDay+"일&nbsp;&nbsp;-&nbsp;&nbsp;"+year+"년&nbsp;0"+i+"월&nbsp;31일</td>"; // 월이 2자리가 아닌 경우 앞에 0 표시
					   				else html+="<td>"+year+"년&nbsp;"+i+"월&nbsp;"+hireDay+"일&nbsp;&nbsp;-&nbsp;&nbsp;"+year+"년&nbsp;"+i+"월&nbsp;31일</td>"; // 월이 2자리인 경우 그대로 표시
					   			}	
	   						}
	   					} // end of if---------------------------------
	   					else{
	   						if(i==2){ // 2월 윤달 고려하여 근무기간 설정
				   				if(year%4==0) html+="<td>"+year+"년&nbsp;0"+i+"월&nbsp;01일&nbsp;&nbsp;-&nbsp;&nbsp;"+year+"년&nbsp;0"+i+"월&nbsp;29일</td>"; // 윤달인 경우
				   				else html+="<td>"+year+"년&nbsp;0"+i+"월&nbsp;01일&nbsp;&nbsp;-&nbsp;&nbsp;"+year+"년&nbsp;0"+i+"월&nbsp;28일</td>"; // 윤달이 아닌 경우
				   			}		 
				   			else if(i==4 || i==6 || i==9 ||i==11){ // 30일까지 존재하는 달
				   				if(i<10) html+="<td>"+year+"년&nbsp;0"+i+"월&nbsp;01일&nbsp;&nbsp;-&nbsp;&nbsp;"+year+"년&nbsp;0"+i+"월&nbsp;30일</td>"; // 월이 2자리가 아닌 경우 앞에 0 표시
				   				else html+="<td>"+year+"년&nbsp;"+i+"월&nbsp;01일&nbsp;&nbsp;-&nbsp;&nbsp;"+year+"년&nbsp;"+i+"월&nbsp;30일</td>";  // 월이 2자리인 경우 그대로 표시
				   			}		 
				   			else{ // 31일까지 존재하는 달
				   				if(i<10) html+="<td>"+year+"년&nbsp;0"+i+"월&nbsp;01일&nbsp;&nbsp;-&nbsp;&nbsp;"+year+"년&nbsp;0"+i+"월&nbsp;31일</td>"; // 월이 2자리가 아닌 경우 앞에 0 표시
				   				else html+="<td>"+year+"년&nbsp;"+i+"월&nbsp;01일&nbsp;&nbsp;-&nbsp;&nbsp;"+year+"년&nbsp;"+i+"월&nbsp;31일</td>"; // 월이 2자리인 경우 그대로 표시
				   			}
	   					} // end of else----------------------------------
			   			
	   					html+=     "<td>"+json.dname+"</td>"+
		   				   		   "<td>"+json.pname+"</td>"+
		   				   		   "<td>"+
				   				   		"<a href='javascript:getSalaryDetail("+year+","+i+");' class='btn btn-primary btn-sm' style='width:50% !important;'>"+
			   						 	 	"<span class='glyphicon glyphicon-print'></span>&nbsp;&nbsp;&nbsp;명세서&nbsp;출력"+
			   							"</a>"+
		   				   		   "</td>"+
	   				   			"</tr>";
	   					cnt++;			
					} // end of for---------------
	   			} // end of else if-----------------
	   			else{ // 3) 1월부터 12월까지 모든 월 행 표시
	   				
	   				for(var i=1;i<13;i++){
	   					html+= "<td>"+cnt+"</td>";
	   					 
	   					if(i==2){ // 2월 윤달 고려하여 근무기간 설정
			   				if(year%4==0) html+="<td>"+year+"년&nbsp;0"+i+"월&nbsp;01일&nbsp;-&nbsp;"+year+"년&nbsp;0"+i+"월&nbsp;29일</td>"; // 윤달인 경우
			   				else html+="<td>"+year+"년&nbsp;0"+i+"월&nbsp;01일&nbsp;-&nbsp;"+year+"년&nbsp;0"+i+"월&nbsp;28일</td>"; // 윤달이 아닌 경우
			   			}		 
			   			else if(i==4 || i==6 || i==9 ||i==11){ // 30일까지 존재하는 달
			   				if(i<10) html+="<td>"+year+"년&nbsp;0"+i+"월&nbsp;01일&nbsp;-&nbsp;"+year+"년&nbsp;0"+i+"월&nbsp;30일</td>"; // 월이 2자리가 아닌 경우 앞에 0 표시
			   				else html+="<td>"+year+"년&nbsp;"+i+"월&nbsp;01일&nbsp;-&nbsp;"+year+"년&nbsp;"+i+"월&nbsp;30일</td>";  // 월이 2자리인 경우 그대로 표시
			   			}		 
			   			else{ // 31일까지 존재하는 달
			   				if(i<10) html+="<td>"+year+"년&nbsp;0"+i+"월&nbsp;01일&nbsp;-&nbsp;"+year+"년&nbsp;0"+i+"월&nbsp;31일</td>"; // 월이 2자리가 아닌 경우 앞에 0 표시
			   				else html+="<td>"+year+"년&nbsp;"+i+"월&nbsp;01일&nbsp;-&nbsp;"+year+"년&nbsp;"+i+"월&nbsp;31일</td>"; // 월이 2자리인 경우 그대로 표시
			   			}
			   			
	   					html+=     "<td>"+json.dname+"</td>"+
				   				   "<td>"+json.pname+"</td>"+
				   				   "<td>"+
						   				"<a href='javascript:getSalaryDetail("+year+","+i+");' class='btn btn-primary btn-sm' style='width:50% !important;'>"+
			   						 		 "<span class='glyphicon glyphicon-print'></span>&nbsp;&nbsp;&nbsp;명세서&nbsp;출력"+
			   							"</a>"+
				   				   "</td>"+
	   				  		 "</tr>";
	   					cnt++;	 
					} // end of for---------------
	   			} // end of else--------------------
	   			
	   			$("table#salaryListTable").html(html);
	   			
	   		},
	   		error: function(request, status, error){
	        	alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
	        }
		}); // end of $.ajax({------ 
		
	} // end of function salaryListInfoByYear(){---------
	
		
	
	// 월급명세서 출력버튼 클릭 시 작동하는 함수
	function getSalaryDetail(year, month){

		// 1) hidden form method와 actioin 설정
		var $frm= document.salaryHiddenForm;
		$frm.year.value=year;
		$frm.month.value=month;
			
		var employeeid= "${loginuser.employeeid}";
		var passwd= prompt("비밀번호를 입력하세요","비밀번호를 입력하세요"); 
		passwd=passwd.trim();
		
		// 2) 비밀번호를 입력한 후 확인 버튼을 누른 경우 => 입력한 비밀번호가 일치하는지 확인하기
		if(passwd!=null){
			$.ajax({
				url:"<%=ctxPath%>/t1/passwdCheckForSalary.tw",
		   		type:"POST",
		   		data:{"employeeid":employeeid,"passwd":passwd},
		   		dataType:"json",
		   		success:function(json){
		   			if(json.n==1){ // 비밀번호가 일치하는 경우 => 월급명세서 화면으로 이동 (새창으로 띄우기)
		   				
		   				window.open("", "salary_window", "width=1200px, height=600px, top=50px, left=50px, scrollbars=yes");
		   				$frm.method="POST";	
		   				$frm.target="salary_window"	
		   				$frm.action="<%=ctxPath%>/t1/salaryDetailForm.tw";   			
		   				$frm.submit();
		   			
		   			}
		   			else{
		   				alert("비밀번호가 일치하지 않습니다. 다시 입력해주세요!");
		   				getSalaryDetail(year, month);
		   			}
		   		},
		   		error: function(request, status, error){
		        	alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
		        }
			}); // end of $.ajax({----------- 
		} // end of if(passwd!=null){-------
		
	} // end of function getSalaryDetail(){--------
	
</script>


<div id="salaryContainer">
	
	<div id="salaryTitle">${loginuser.name}님의 월급 내역 조회</div>
	<div style="margin: 30px 0px 30px 50px;">
		<span style="padding-top:20px; font-size:12pt; margin-right:10px;">검색년도</span>
		<select id="salaryYear"></select>
	</div>
	
	<%-- ajax로 값 넣어주는 곳 --%>
	<table id="salaryListTable" style="width: 97%; margin-left: 50px;"></table>
	
	<%-- margin-bottom을 주기 위한 div --%>
	<div style="margin-bottom:200px;"></div>
	
	<!-- 월급명세서 출력을 위해서 넘겨줄 히든 폼 -->
	<form name="salaryHiddenForm">
		<input type="hidden" value="${loginuser.employeeid}" name="employeeid" />
		<input type="hidden" value="" name="year" />
		<input type="hidden" value="" name="month" />
	</form>

</div>