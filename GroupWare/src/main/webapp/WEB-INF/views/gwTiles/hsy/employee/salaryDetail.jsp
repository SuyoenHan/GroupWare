<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String ctxPath= request.getContextPath(); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style type="text/css">
	div#salaryDetailContainer{
		border: solid 1px red;
		width:1400px;
		margin: 0 auto;
		position: relative;
		left: -80px;
	}

	div#salaryDetailTitle{
		border: solid 0px red;
		margin: 70px 0px 30px 50px;
		font-size: 22pt;
		font-weight: bold;
	}
	
	div#salaryDetailOption label{
		font-size: 13pt;
		margin-left: 10px;
		cursor: pointer;
	}
	
	div#salaryDetailOption input{cursor: pointer;}
	
	div#orderOption select{
		width: 100px;
		height: 30px;
		margin-right: 2px;
	}
	
	table#salaryDetailTable{
		border: solid 2px #a5a5a7;
		border-collapse: collapse;
		width: 85%;
		margin: 30px 0px 30px 50px;
	}
	
	table#salaryDetailTable th, table#salaryDetailTable td{
		border: solid 1px #a5a5a7;
		text-align: center;
		border-right: solid 2px #a5a5a7;
		font-size: 11pt;
	}
	
	table#salaryDetailTable tr.tableTop {border-bottom: solid 2px #a5a5a7; }
	
	table#salaryDetailTable th{
		background-color: #003d66;
		color: #fff;
		font-weight: bold;
		height: 40px;
		padding: 5px;
		vertical-align: center;
	}
	
	table#salaryDetailTable td {height: 80px;}
	
</style>

<script type="text/javascript">


	$(document).ready(function(){
		
		// default값은 성과금내역 보기
		getBonusList();
		
		// 성과금 내역 또는 야근수당 내역 라디오 버튼을 클릭한 경우 이벤트
		$("input[name=salaryDetailOption]").click(function(){
			
			// 기존의 테이블 비우기
			$("div#tableLocation").html("");
			
			var selctedOption= $(this).prop('id');
			if(selctedOption=='radio1'){ // 성과금 내역을 클릭한 경우
				getBonusList(); // 성과금내역 함수 호출
			}
			else{ // 야근수당 내역을 클릭한 경우
			
			}
			
		}); // end of $("input[name=salaryDetailOption]").click(function(){-----
		
		
	}); // end of $(document).ready(function(){-----

	// === function declaration ===
	function getBonusList(){
		
		$.ajax({
			url:"<%=ctxPath%>/t1/getBonusList.tw",
	   		type:"POST",
	   		data:{"employeeid":"${loginuser.employeeid}"},
	   		dataType:"json",
	   		success:function(json){
	   			
	   			var html= "<table id='salaryDetailTable'>"+
				   			"<tr>"+
				   				"<th rowspan='2' style='border-top: solid 2px #003d66; border-left: solid 2px #003d66;'>일시</th>"+
				   				"<th colspan='3' style='border-top: solid 2px #003d66;'>성과내역</th>"+
				   				"<th rowspan='2' style='border-top: solid 2px #003d66; border-right: solid 2px #003d66;'>성과금</th>"+
				   			"</tr>"+
				   			"<tr class='tableTop'>"+
				   				"<th>목표건</th>"+
				   				"<th>달성건</th>"+
				   				"<th>달성률</th>"+
				   			"</tr>";
				 
				 if(json.length==0){ // 실적이 존재하지 않거나, 실적이 존재하지만 성과금이 존재하지 않는 경우
					 html+= "<tr>"+
					 	   	 	"<td colspan='5'>성과금 내역이 존재하지 않습니다.</td>"+
				 		    "</tr>";
				 }
				 else{ // 성과금 내역이 존재하는 경우
				 
					 $.each(json,function(index,item){
						
						 html+= "<tr>"+
					   				"<td>"+item.date+"</td>"+
					   				"<td>"+
					   					item.goalCnt+
					   					"<br/><span>목표건=전월실적("+item.prevCnt+"건)&nbsp;+&nbsp;2건</span>"+
					   				"</td>"+
					   				"<td>"+item.doneCnt+"</td>"+
					   				"<td>"+
					   					item.achievementRate+
					   					"<br/><span>달성률= 달성건("+item.doneCnt+")/목표건("+item.goalCnt+")*100</span>"+
					   				"</td>"+
					   				"<td>"+item.bonus+"</td>"+
				   				"</tr>";
					 
					 }); // end of $.each(json,function(index,item){--------
				 } // end of else----------------------
				 
	   			html+="</table>";
	   			
	   			$("div#tableLocation").html(html);
	   		
	   		},
	   		error: function(request, status, error){
	        	alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
	        }
		}); // end of $.ajax({-------------- 		
		
	} // end of function getBonusList(){

</script>


<div id="salaryDetailContainer">
	
	<div id="salaryDetailTitle">${loginuser.name}님의 성과금/야근수당 내역</div>
	<div id="salaryDetailOption" style="margin: 50px 0px 30px 50px; border: solid 1px red;">
		<input type="radio" id="radio1" name="salaryDetailOption" checked /><label for="radio1" style="margin-right: 20px;">성과금&nbsp;내역</label>
		<input type="radio" id="radio2" name="salaryDetailOption" /><label for="radio2">야근수당&nbsp;내역</label>
	</div>
	<div id="orderOption" style="border:solid 0px blue; width:88.5%;" align="right">
		<span style="margin-right:15px; font-size: 12pt; color: #4c4c4d;">정렬기준</span>
		<select>
			<option>&nbsp;&nbsp;오름차순</option>
			<option>&nbsp;&nbsp;내림차순</option>
		</select>
		<select style="width: 80px;">
			<option>&nbsp;&nbsp;&nbsp;날짜</option>
			<option>&nbsp;&nbsp;&nbsp;금액</option>
		</select>
	</div>
	<div id="tableLocation"></div>
	
	
</div>
