<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	String ctxPath = request.getContextPath();
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>

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
	
	input[type="number"] {
		min-width: 50px;
	}
	
	table#siljukTable{
		padding-left : 50px;
		border-collapse: collapse;
		width: 500px;
	}
	
	table#siljukTable th{
		border:solid 1px black;
	}
	
	table#siljukTable td{
		border:solid 1px black;
	}
</style>


<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script src="https://code.highcharts.com/modules/export-data.js"></script>
<script src="https://code.highcharts.com/modules/accessibility.js"></script>

<div id="companyPerfomanceBox" style="width: 1300px; margin:0 auto;">
    <div id="container" style="float:left; margin-top:50px; border:solid 1px black;"></div>
    <div style='float:right;'>
	    <div id="selectBox" style="border:solid 1px black; width:100px; display:inline-block;  margin-top:50px; margin-left:450px;">
			<select id="siljukYear">
				<c:forEach var="i" begin="${paraMap.minYear}" end="${paraMap.maxYear}">
					<option value="${i}">${i}</option>
				</c:forEach>
			</select>
			<select id="siljukMonth">
			</select>
			
		</div>
	    <div style="border:solid 1px red; margin-left:-50px;">
			<table id="siljukTable" style="width:100%;">
				<thead> 
					<tr> 
						<th></th>
						<th>실적건수</th>
						<th>비율</th>
						<th>전달대비 실적률</th>
					</tr>
				</thead>
				<tbody>
					<tr> 
						<td>xx부서</td>
						<td>15건</td>
						<td>20%</td>
						<td>110%</td>
					</tr>
							
				</tbody>
				
			</table>
		</div>
	</div>
</div>

<script type="text/javascript">


	$(document).ready(function(){
		
		var now = new Date();
		var year = now.getFullYear();
		var month = now.getMonth()+1;        // 자바스크립에서 월은 0부터 시작
		
		var minMonth = Number("${paraMap.minMonth}");
		var minYear = "${paraMap.minYear}";
		var maxMonth = Number("${paraMap.maxMonth}");
		var maxYear = "${paraMap.maxYear}";
		
		// 문서 초기 로딩 시 select태그에 최근 년에 해당하는 월 선택값 넣어주기
		$("select#siljukYear").val(maxYear);
		
		var html ="";
		for(var i=1; i<(maxMonth+1); i++){
			html+="<option value='"+i+"'>"+i+"</option>";
		}
		
		
		$("select#siljukMonth").html(html);
		$("select#siljukMonth").val(maxMonth);
		
		
		
		// 년도 선택시 실적이 존재하는 월만 보여주기 및 해당년도/월에 해당하는 데이터 가져오기
		$("select#siljukYear").bind("change",function(){
			
			var selectedYear = $(this).val();
			 
			
			// 월 select태그 값 정해주기
			if(selectedYear==minYear){// 실적이 존재하는 가장 오래된 년도 선택시
				var html ="";
				for(var i=minMonth; i<13; i++){
					html+="<option value='"+i+"'>"+i+"</option>";
				}
				
				$("select#siljukMonth").html(html);
				
			}
			else if(selectedYear==maxYear){
				var html ="";
				for(var i=1; i<(maxMonth+1); i++){
					html+="<option value='"+i+"'>"+i+"</option>";
				}
				// $("select#siljukMonth option:selected").val(maxMonth);
				// $("select#siljukMonth").val("${paraMap.maxMonth}");
				
				$("select#siljukMonth").html(html);
				$("select#siljukMonth").val(maxMonth);
			}
			else{
				var html ="";
				for(var i=1; i<13; i++){
					html+="<option value='"+i+"'>"+i+"</option>";
				}
				$("select#siljukMonth").html(html);
			}
			
			var selectedMonth = $("select#siljukMonth").val();
			
			
			
		}); // end of $("select#siljukMonth").bind("change",function(){
		
		
		// 월 선택시 실적이 존재하는 월만 보여주기 및 해당년도/월에 해당하는 데이터 가져오기
		$("select#siljukMonth").bind("change",function(){
			
			var selectedYear = $("select#siljukYear").val();	
			var selectedMonth = $(this).val();

	

		}); // end of $("select#siljukMonth").bind("change",function(){	
		
		
	}); // end of $(document).ready(function(){
	
		
	// function declaration	
	function goChart(selectedYear,selectedMonth){
		
		$.ajax({ // 1st ajax:선택한 월부터 -2개월 전까지 년-월 뽑아오기
			url:"<%= ctxPath%>/t1/selectPerformanceMonth.tw",
			data:{"selectedYear":selectedYear,
				  "selectedMonth":selectedMonth},
			dataType:"json",
			success:function(json){
				
				var performanceWhenArr = [];
				performanceWhenArr.push(json.lastDate);
				performanceWhenArr.push(json.middleDate);
				performanceWhenArr.push(json.firstDate);
				 
				var performanceWhenArres = performanceWhenArr.join();
				
				var performanceWhoArr = [];
				var performanceDataArr = [];
				$.ajax({ // 2st ajax: 선택한 월부터 -2개월전까지 데이터 뽑아오기
					url:"<%= ctxPath%>/t1/selectDepPerformance.tw",
					data:{"performanceWhenArres":performanceWhenArres},
					dataType:"json",
					success:function(json){
						
						
						
					},
			    	error: function(request, status, error){
			            alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
			        }
				});// end of 2st $.ajax({
					
			},
	    	error: function(request, status, error){
	            alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
	        }

			
		}); // end of 1st $.ajax({
		
	}// end of function goChart(){
	
		
	Highcharts.chart('container', {
	    chart: {
	        plotBackgroundColor: null,
	        plotBorderWidth: null,
	        plotShadow: false,
	        type: 'pie'
	    },
	    title: {
	        text: 'Browser market shares in January, 2018'
	    },
	    tooltip: {
	        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
	    },
	    accessibility: {
	        point: {
	            valueSuffix: '%'
	        }
	    },
	    plotOptions: {
	        pie: {
	            allowPointSelect: true,
	            cursor: 'pointer',
	            dataLabels: {
	                enabled: true,
	                format: '<b>{point.name}</b>: {point.percentage:.1f} %'
	            }
	        }
	    },
	    series: [{
	        name: 'Brands',
	        colorByPoint: true,
	        data: [{
	            name: 'Chrome',
	            y: 61.41,
	            sliced: true,
	            selected: true
	        }, {
	            name: 'Internet Explorer',
	            y: 11.84
	        }]
	    }]
	});	
		
</script>