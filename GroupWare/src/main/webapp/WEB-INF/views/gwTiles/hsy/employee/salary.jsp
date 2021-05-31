<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String ctxPath= request.getContextPath(); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">

	div#salaryContainer{
		border: solid 1px red;
		width:70%;
		margin: 0 auto;
		position: relative;
		left: -250px;
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
	
	th{border: solid 1px red;}


</style>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript">
	
	$(document).ready(function(){
		
		// 특정 직원의 입사년도부터 시작하여 현재년도까지 select option 만들기
		var currentYear=Number("${currentYear}");
		var hireYear=Number("${hireYear}");
		
		var html="";
		for(var i=currentYear; i>=hireYear; i--) {html+="<option id="+i+">"+i+"년</option>";}
		$("select#salaryYear").html(html);

	}); // end of $(document).ready(function(){-----
	
		
	// === function declaration ===
	function getSalaryDetail(){
		
		// 1) hidden form method와 actioin 설정
		var $frm= document.salaryHiddenForm;
		$frm.method="POST";
		$frm.action="<%=ctxPath%>/t1/salaryDetailForm.tw";
			
		var employeeid= "${loginuser.employeeid}";
		var passwd= prompt("비밀번호를 입력하세요"); 
		passwd=passwd.trim();
		
		// 2) 비밀번호를 입력한 후 확인 버튼을 누른 경우 => 입력한 비밀번호가 일치하는지 확인하기
		if(passwd!=null){
			$.ajax({
				url:"<%=ctxPath%>/t1/passwdCheckForSalary.tw",
		   		type:"POST",
		   		data:{"employeeid":employeeid,"passwd":passwd},
		   		dataType:"json",
		   		success:function(json){
		   			if(json.n==1){ // 비밀번호가 일치하는 경우 => 월급명세서 화면으로 이동
		   				$frm.submit();
		   			}
		   			else{
		   				alert("비밀번호가 일치하지 않습니다. 다시 입력해주세요!");
		   				getSalaryDetail();
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
		<span>검색년도</span>
		<select id="salaryYear"></select>
	</div>
	<table id="salaryListTable" style="width: 97%; margin-left: 50px;">
		<tr style="height:50px; background-color: #003d66; color:#fff; font-weight: bold; ">
			<th style="width: 10%; text-align: center;">순번</th>
			<th style="width: 25%; text-align: center;">근무기간</th>
			<th style="width: 10%; text-align: center;">소속</th>
			<th style="width: 10%; text-align: center;">직위</th>
			<th style="width: 35%; text-align: center;">급여합계</th>
			<th style="width: 10%; text-align: center;">월급명세서</th>
		</tr>
		<tr class="salaryListTr">
			<td>00000</td>
			<td>00000</td>
			<td>00000</td>
			<td>00000</td>
			<td>00000</td>
			<td>
				<a href="javascript:getSalaryDetail();" class="btn btn-primary btn-sm">
					<span class="glyphicon glyphicon-print"></span>print
				</a>
			</td>
	 	</tr>		
	</table>


	<!-- 월급명세서 출력을 위해서 넘겨줄 히든 폼 -->
	<form name="salaryHiddenForm">
		<input type="hidden" value="${loginuser.employeeid}" name="employeeid" />
	</form>

</div>