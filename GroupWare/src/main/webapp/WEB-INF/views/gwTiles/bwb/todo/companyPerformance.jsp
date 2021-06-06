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
	
	th.alignThClass {
		text-align:center;
	}
	
	td.alignTdClass {
		text-align:center;
		height: 40px;
	}
	
</style>


<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script src="https://code.highcharts.com/modules/export-data.js"></script>
<script src="https://code.highcharts.com/modules/accessibility.js"></script>

<div id="companyPerfomanceBox" style="width: 1300px; margin:0 auto;">
    <div id="container" style="float:left; margin-top:50px; border:solid 0px black;"></div>
    <div style='float:right;'>
	    <div id="selectBox" style="border:solid 0px black; width:100px; display:inline-block;  margin-top:50px; margin-left:450px; height:30px;">
			<select id="siljukYear" style="height:30px;">
				<c:forEach var="i" begin="${paraMap.minYear}" end="${paraMap.maxYear}">
					<option value="${i}">${i}</option>
				</c:forEach>
			</select>
			<select id="siljukMonth" style="height:30px;">
			</select>
		</div>
	    <div id="tableDiv" style="border:solid 0px red; margin-top: 10px; margin-left:-50px; height:400px;"></div>
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
		
		
		goChart(maxYear,maxMonth);
		
		
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
			
			goChart(selectedYear,selectedMonth);
			
		}); // end of $("select#siljukMonth").bind("change",function(){
		
		
		// 월 선택시 실적이 존재하는 월만 보여주기 및 해당년도/월에 해당하는 데이터 가져오기
		$("select#siljukMonth").bind("change",function(){
			
			var selectedYear = $("select#siljukYear").val();	
			var selectedMonth = $(this).val();

			goChart(selectedYear,selectedMonth);

		}); // end of $("select#siljukMonth").bind("change",function(){	
		
		
	}); // end of $(document).ready(function(){
	
		
	// function declaration	
	function goChart(selectedYear,selectedMonth){
		
		$.ajax({ // 1st ajax:선택한 월+년도 만들어주기
			url:"<%= ctxPath%>/t1/selectPerformanceMonth.tw",
			data:{"selectedYear":selectedYear,
				  "selectedMonth":selectedMonth},
			dataType:"json",
			success:function(json){
				
				var selectedDate = json.firstDate;
				
				var performanceDepArr = [];

				$.ajax({ // 2st ajax: 선택한 월부터 -2개월전까지 부서팀들의 데이터 뽑아오기
					url:"<%= ctxPath%>/t1/selectDepPerformance.tw",
					data:{"selectedDate":selectedDate},
					dataType:"json",
					success:function(json){
						
						var html ="";
						html += "<table id='siljukTable' style='width:100%; font-size:15pt;'>";
						html += "<thead>"; 
						html +=	"<tr>"; 
						html += "<th></th>";		
						html += "<th class='alignThClass'>실적건수</th>";
						html +=	"<th class='alignThClass'>비율</th>";
						html += "<th class='alignThClass'>전달대비 실적률</th>";
						html += "</tr>";
						html += "</thead>";
						html += "<tbody>";
						 
						// [{name:'부서1팀',y:비율,sliced:true} ]
						$.each(json,function(index,item){
							
							if(index==1){
								performanceDepArr.push({name:item.name,y:Number(item.percentage),sliced: true,selected: true});
							}
							else{
								performanceDepArr.push({name:item.name,y:Number(item.percentage)});
							}
							
							html +=	"<tr>";
							html += "<td class='alignTdClass'>"+item.name+"</td>";
							html += "<td class='alignTdClass'>"+item.DCnt+"건</td>";
							html += "<td class='alignTdClass'>"+item.percentage+"%</td>";
							html += "<td class='alignTdClass'>"+item.compareValue+"</td>";
							html += "</tr>";

						}); // end of each
						
						html +="</tbody>";
						html +="</table>";
						
						$("div#tableDiv").html(html);
						
						// 차트 구현 시작 ///	
						Highcharts.chart('container', {
						    chart: {
						        plotBackgroundColor: null,
						        plotBorderWidth: null,
						        plotShadow: false,
						        type: 'pie'
						    },
						    title: {
						        text: 'T1WORKS의 부서별 실적현황 '+selectedYear+'년'+selectedMonth+'월'
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
						        data: performanceDepArr
						    }]
						});	
						// 차트 구현 끝 ///
						
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
	

		
</script>