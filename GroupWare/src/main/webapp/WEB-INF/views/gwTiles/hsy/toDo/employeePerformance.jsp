<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String ctxPath= request.getContextPath(); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>

	.highcharts-figure, .highcharts-data-table table {
	    min-width: 310px; 
	    max-width: 800px;
	    margin: 1em auto;
	    display: none;
	}
	
	#container {
	    height: 400px;
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
	
	div#employeePerfBox{
		border: solid 0px red;
		width: 1400px;
		margin-bottom: 150px;
		margin-left: 100px;
		overflow: hidden;
	}
	
	div#employeePerfChartContainer{
		float: left;
		border: solid 0px red;
		width: 50%;
		height: 500px;
		margin-right: 100px;
	}

	table#employeePerfTable{
		border: solid 2px #c2c2c7;
		border-collapse: collapse;
		width: 100%;
	}

	table#employeePerfTable th, table#employeePerfTable td{
		border: solid 1px #c2c2c7;
		border-left: solid 2px #c2c2c7;
		text-align: center;
		height: 40px;
	}
	
	table#employeePerfTable th.tableTop {border-bottom: solid 2px #c2c2c7;}
	
</style>

<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script src="https://code.highcharts.com/modules/export-data.js"></script>
<script src="https://code.highcharts.com/modules/accessibility.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<script type="text/javascript">
	
	$(document).ready(function(){
		
		// 특정 직원의 입사년도부터 시작하여 현재년도까지 select option 만들기
		var currentYear=Number("${currentYear}");
		var hireYear=Number("${hireYear}");
		var html="";
		for(var i=currentYear; i>=hireYear; i--) {html+="<option value="+i+">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+i+"년</option>";}
		$("select#searchYear").html(html);
		
		// 선택년도에 존재하는 월 select option 만들기 (입사년도인 경우 입사월부터, 현재년도인 경우 현재월 까지)=> default는 현재년월
		monthOptionByYear(currentYear);
		$("select#searchYear").val("${currentYear}");
		$("select#searchMonth").val("${currentMonth}");
		
		// 차트와 테이블 데이터 가져오는 ajax 함수 => default는 현재년월
		EmployeePerformanceInfo('${currentYear}','${currentMonth}');
		
		
		// 검색년도 change 이벤트 구현
		$("select#searchYear").bind('change',function(){
			
			// 1) 년도가 변할때 해당 년도에 맞는 월옵션 제공
			var selectedYear= $(this).val();
			monthOptionByYear(selectedYear);
			
			// 2) 년도 바꿀때마다 차트 및 테이블 데이터 ajax로 새롭게 갱신 => 년도가 바뀔때 월은 해당 년도의 첫 월이 된다
			var year= $(this).val();
			var month= $(this).next().val();
			EmployeePerformanceInfo(year,month);
			
		}); // end of $("select#monthOptionByYear").bind('change',function(){-----
		
			
		// 검색월 change 이벤트 구현	
		$("select#searchMonth").bind('change',function(){
			
			// 월이 바꿀때마다 차트 및 테이블 데이터 ajax로 새롭게 갱신 => 월이 바뀔때 년도는 선택되어진 년도가 된다
			var year= $(this).prev().val();
			var month= $(this).val();
			EmployeePerformanceInfo(year,month);
			
		}); // end of $("select#searchMonth").bind('change',function(){---------
			
			
	}); // end of $(document).ready(function(){-------------------

		
	// ==== function declaration ====
	// 선택년도에 존재하는 월 select option 만들기 (입사년도인 경우 입사월부터, 현재년도인 경우 현재월 까지)		
	function monthOptionByYear(year){ 
		
		var currentYear=Number("${currentYear}");
		var hireYear=Number("${hireYear}");
		var currentMonth= Number("${currentMonth}");
		var hireMonth= Number("${hireMonth}");
		var hireDay= Number("${hireDay}");
		year= Number(year); // 년도 숫자로 변경
	   			
	    var html="";
	    
	   	if(currentYear==year){ // 1) 현재년도인경우 현재월 전월 까지만 선택옵션 제공
	   		for(var i=1; i<=currentMonth; i++){
	   			if(i<10){html+="<option value="+i+">&nbsp;&nbsp;0"+i+"월</option>";} // 월이 한자리인 경우 앞에 0 표시 
	   			else{html+="<option value="+i+">&nbsp;&nbsp;"+i+"월</option>";} // 월이 두자리인 경우 앞에 0 표시 안함
	   		}
		}
	    else if(hireYear==year){ // 2) 입사년도인경우 입사월부터 선택옵션 제공
		    for(var i=hireMonth; i<=12; i++){
		    	if(i<10){html+="<option value="+i+">&nbsp;&nbsp;0"+i+"월</option>";} // 월이 한자리인 경우 앞에 0 표시 
	   			else{html+="<option value="+i+">&nbsp;&nbsp;"+i+"월</option>";} // 월이 두자리인 경우 앞에 0 표시 안함
		    }	
		}
	   	else{ // 3) 1월부터 12월까지 모든 선택옵션 제공
	   		for(var i=1; i<=12; i++){
	   			if(i<10){html+="<option value="+i+">&nbsp;&nbsp;0"+i+"월</option>";} // 월이 한자리인 경우 앞에 0 표시 
	   			else{html+="<option value="+i+">&nbsp;&nbsp;"+i+"월</option>";} // 월이 두자리인 경우 앞에 0 표시 안함
	   		}
	   	}		
		
	   	$("select#searchMonth").html(html);
	   	
	} // end of function monthOptionByYear(year){---------
		
		
		
		
</script>


<div id="employeePerfBox">
	
	<div style="margin: 70px 0px 80px 65px; font-size: 22pt; font-weight: bold;" >${sessionScope.loginuser.name}님의&nbsp;실적현황</div>
	<div id="employeePerfChartContainer"></div>
	<div id="employeePerfTableContainer" style="float: right; width: 42%; padding-top: 80px; border: solid 0px red;">
		<div style="position:relative; top: -70px;" align="right">
			<span style="font-size: 13pt; margin-right:15px; position:relative; top: 2.5px;">검색날짜</span>
			<select id="searchYear" style="height: 30px; width: 120px; cursor:pointer;"></select>
			<select id="searchMonth" style="height: 30px; width: 80px; cursor:pointer;"></select>
		</div>
		
		<table id="employeePerfTable"></table>
		
		<label style="width: 200px; border-right:solid 1px red;">처리 업무 건 수가 가장 많은달</label>
		<input type="text" value="2021년 10월" readOnly />		
		<label style="width: 200px; border-right:solid 1px red;">담당 고객 수가 가장 많은달</label>
		<input type="text" value="2021년 10월" readOnly />
	
	</div>
</div>

 
<script type="text/javascript">
	
	// == function declaration ==

	function EmployeePerformanceInfo(year,month){
		//선택한 년월에 따라 차트와 테이블 데이터 바꾸기	
		// 1) 선택한 년월부터 6개월 이전의 차트 데이터 가져오기 => 입사일로부터 5개월 이내의 날짜 선택 시 입사 이전 날짜는 데이터 건수 0 처리 	
		$.ajax({
		
			url:"<%=ctxPath%>/t1/getEmployeePerformance.tw", 
			type:"POST",
			data:{"year":year,"month":month,"employeeid":"${loginuser.employeeid}"},
			dataType: "JSON",
			success: function(json){
				
				// 차트에 필요한 배열 생성
				var xAxisArr= [];
				var perfCntArr= [];
				var clientCntArr= [];
				
				// 테이블에 들어 갈 데이터
				var html= "<tr>"+
							  "<th class='tableTop'></th>"+
							  "<th style='height: 30px;' class='tableTop'>처리 업무 건 수</th>"+
							  "<th class='tableTop'>담당 고객 수</th>"+
							  "<th class='tableTop'></th>"+
						  "</tr>";
				
				$.each(json,function(index, item){
					
					// 차트에 필요한 배열에 값 넣기
					xAxisArr.push(item.specificDate);
					perfCntArr.push(Number(item.perfNumber));
					clientCntArr.push(Number(item.clienNumber));
					
					// 테이블에 필요한 값 넣기
					html+= "<tr>"+
							   "<th>"+item.specificDate+"</th>";
							   
					if(item.perfNumber=='-' || item.clienNumber=='-'){
						html+="<td>"+item.perfNumber+"&nbsp;</td>"+
						   	  "<td>"+item.clienNumber+"&nbsp;</td>";
					}
					else{
						html+="<td>"+item.perfNumber+"&nbsp;건</td>"+
					   	  	  "<td>"+item.clienNumber+"&nbsp;명</td>";
					}
					
					html+= 	  "<td><span class='showPerfDetail'>자세히 보기&nbsp;&nbsp;</span><span class='glyphicon glyphicon-search' ></span></td>"+
					       "</tr>";				
					
							   
						 	  
				}); // end of $.each(json,function(index, item){----
		
				
				$("table#employeePerfTable").html(html);
					
				// 차트 코드 수정 (하이차트) 
				Highcharts.chart('employeePerfChartContainer', {
				    chart: {
				        zoomType: 'xy'
				    },
				    title: {
				        text: '<span style="font-size: 15pt; font-weight: bold;">실적현황 그래프</span>'
				    },
				    xAxis: [{
				        categories: xAxisArr,
				        crosshair: true
				    }],
				    yAxis: [{ // Primary yAxis
				        labels: {
				            format: '{value}명',
				            style: {
				                color: Highcharts.getOptions().colors[1]
				            }
				        },
				        title: {
				            text: '담당 고객 수',
				            style: {
				                color: Highcharts.getOptions().colors[1]
				            }
				        }
				    }, { // Secondary yAxis
				        title: {
				            text: '처리 업무 건 수',
				            style: {
				                color: Highcharts.getOptions().colors[0]
				            }
				        },
				        labels: {
				            format: '{value}건',
				            style: {
				                color: Highcharts.getOptions().colors[0]
				            }
				        },
				        opposite: true
				    }],
				    tooltip: {
				        shared: true
				    },
				    legend: {
				        layout: 'vertical',
				        align: 'left',
				        x: 120,
				        verticalAlign: 'top',
				        y: 50,
				        floating: true,
				        backgroundColor:
				            Highcharts.defaultOptions.legend.backgroundColor || // theme
				            'rgba(255,255,255,0.25)'
				    },
				    series: [{
				        name: '처리 업무 건 수',
				        type: 'column',
				        yAxis: 1,
				        data: perfCntArr,
				        tooltip: {
				            valueSuffix: ' 건'
				        }
				
				    }, {
				        name: '담당 고객 수',
				        type: 'line',
				        data: clientCntArr,
				        tooltip: {
				            valueSuffix: ' 명'
				        }
				    }]
				}); // end of Highcharts.chart('employeePerfChartContainer', {--------
				
			},
			error: function(request, status, error){
		        alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
		    }
				
		}); // end of $.ajax({-------------------------	
	} // end of function EmployeePerformanceInfo(year,month){---
</script>

