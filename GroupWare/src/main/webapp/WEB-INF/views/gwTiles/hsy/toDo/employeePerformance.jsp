<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String ctxPath= request.getContextPath(); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
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
	
	table#employeePerfTable tr.tableTop {border-bottom: solid 2px #c2c2c7;}
	table#employeePerfTable tr.tablebottom {border-top: solid 2px #c2c2c7;}
	
	table#employeePerfTable tr.selectedTr{
		background-color: #ffff99;
		font-weight: bold;
	}
	
	span.showPerfDetail{cursor: pointer;}
	span.showPerfDetail:hover {
		color: #009999;
		font-weight: bold;
	}
	
	div.modalLocation {margin-top:70px;}
	
	Table.modalTable{
		border: solid 1px #96979c;
		border-collapse: collapse;
		width: 90%;
		margin: 0 auto;
		margin-bottom: 15px;
	}
	
	Table.modalTable th, Table.modalTable td {
		border: solid 1px #96979c;
		text-align: center;
		font-size: 10pt;
		padding: 4px 0px;
		vertical-align: middle;
	}
	
	Table.modalTable th{
		background-color: #002266;
		color:#fff;
		font-weight: bold;
		border: solid 1px #002266;
		text-align: center;
		border-right: solid 1px #96979c;
	}
	
	
</style>

<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script src="https://code.highcharts.com/modules/export-data.js"></script>
<script src="https://code.highcharts.com/modules/accessibility.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

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
		
		
		// 자세히 보기 클릭 이벤트 구현 
		$(document).on('click','span.showPerfDetail',function(){
			var certainDate= $(this).prop('id');
			getPerfClientInfoForModal(certainDate);
			$("div.modal").modal();
		});	
			
			
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

		
	// 자세히 보기 클릭 시 해당년월에 대한 처리업무와 담당고객 정보를 모달에 넣어주기 위한 함수
	function getPerfClientInfoForModal(certainDate){
			
		$.ajax({
			url:"<%=ctxPath%>/t1/getPerfClientInfoForModal.tw", 
			type:"POST",
			data:{"certainDate":certainDate,"employeeid":"${loginuser.employeeid}"},
			dataType: "JSON",
			success: function(json){
				
				var html="";
				
				if(json.length==0){ // 특정 년월에 끝난 업무가 한 건도 없는 경우
					html="<div align='center' style='padding: 30px 0px; font-size: 13pt; color: red;'>실적이 존재하지 않습니다.</div>";
				}
				else{
					
					
					$.each(json,function(index,item){
						
						html+="<div style='border: solid 2px #bfbfc0; padding-top: 20px; width: 90%; margin: 0 auto; margin-bottom: 50px;'>"+
								"<table class='modalTable'>"+
									"<tr>"+
									   "<th style='width:15%;'>순번</th>"+
									   "<td style='width:15%;'>"+item.rno+"&nbsp;번</td>"+
									   "<th style='width:15%;'>상품명</th>"+
									   "<td style='width:55%;'>"+item.pName+"</td>"+
						   			"</tr>"
						   	 	"</table>";
						
						html+=	"<table class='modalTable'>"+
						   			"<tr>"+
									   "<th>업무배정일</th>"+
									   "<th>업무시작일</th>"+
									   "<th>업무종료일</th>"+
									   "<th style='border-right: solid 1px #002266;'>담당 고객 수</th>"+
						   			"</tr>"+
						   			"<tr>"+
									   "<td>"+item.dueDate+"</td>"+
									   "<td>"+item.startDate+"</td>"+
									   "<td>"+item.endDate+"</td>"+
									   "<td>"+item.nowNo+"&nbsp;명</td>"+
						   			"</tr>"+
								  "</table>"+
							  "</div>";
					}); // end of $.each(json,function(){--------
					
						
				} // end of else-----------------------------
				
				$("h4.modal-title").text("  "+certainDate+" 실적 상세내용");
				$("div.modal-body").html(html);
			},
			error: function(request, status, error){
		        alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
		    }
		}); // end of $.ajax({-------- 
		
	} // end of function getPerfClientInfoForModal(-------
		
	
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
	</div>
	
	<!-- 해당년월 상세정보 modal로 보여주기 -->
	<div class="modal fade modalLocation" id="layerpop" >
  		<div class="modal-dialog modal-lg">
	    	<div class="modal-content">
		      	<!-- header -->
		      	<div class="modal-header">
		        	<!-- 닫기(x) 버튼 -->
		        	<button type="button" class="close" data-dismiss="modal">×</button>
		        	<!-- header title -->
		        	<h4 class="modal-title"></h4>
		      	</div>
		      	<!-- body (ajax로 값이 들어온다) -->
		      	<div class="modal-body" style='margin-top: 30px;'></div>
		      	<!-- Footer -->
		      	<div class="modal-footer">
		        	<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
		      	</div>
	    	</div>
  		</div>
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
				var html= "<tr class='tableTop'>"+
							  "<th></th>"+
							  "<th style='height: 30px;'>처리 업무 건 수</th>"+
							  "<th>담당 고객 수</th>"+
							  "<th>상세 정보</th>"+
						  "</tr>";
				
				// 3) 6개월 동안 총 처리 업무 건수 추출 변수
				var totalPerfCnt=0;
				// 4) 6개월 동안 총 담당 고객 수 추출 변수
				var totalClientCnt=0;	
				
				$.each(json,function(index, item){
					
					// 차트에 필요한 배열에 값 넣기
					xAxisArr.push(item.specificDate);
					perfCntArr.push(Number(item.perfNumber));
					clientCntArr.push(Number(item.clienNumber));
					
					if(item.perfNumber!='-') totalPerfCnt+=Number(item.perfNumber);
					if(item.clienNumber!='-') totalClientCnt+=Number(item.clienNumber);
					
					// 2) 테이블에 필요한 값 넣기
					
					// 검색조건에서 선택한 날짜에 배경색 추가
					if(index==5) html+= "<tr class='selectedTr'>";
					else html+= "<tr>";
							   
					html+= "<th>"+item.specificDate+"</th>";
							   
					if(item.perfNumber=='-' || item.clienNumber=='-'){
						html+="<td>"+item.perfNumber+"&nbsp;</td>"+
						   	  "<td>"+item.clienNumber+"&nbsp;</td>"+
						   	  "<td>-</td>";
					}
					else{
						html+=	"<td>"+item.perfNumber+"&nbsp;건</td>"+
					   	  	  	"<td>"+item.clienNumber+"&nbsp;명</td>"+
					   	  	  	"<td><span class='showPerfDetail' id='"+item.specificDate+"'>자세히 보기&nbsp;&nbsp;<span class='glyphicon glyphicon-search' ></span></span></td>"+
				          	"</tr>";	
					}
					
						 	  
				}); // end of $.each(json,function(index, item){----
		
				
				html+= "<tr class='tablebottom'>"+
						   "<th style='color: #0000e6;'>합계</th>"+
						   "<td style='color: #0000e6; font-weight:bold;'>"+totalPerfCnt+"&nbsp;건</td>"+
						   "<td style='color: #0000e6; font-weight:bold;'>"+totalClientCnt+"&nbsp;명</td>"+
						   "<td></td>"+
					   "</tr>";
					
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

