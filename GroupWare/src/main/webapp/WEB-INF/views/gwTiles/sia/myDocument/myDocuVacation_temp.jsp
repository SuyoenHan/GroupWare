<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% 
	String ctxPath = request.getContextPath();
%>

<style type="text/css">
	div.section{
		margin: 30px 0px 30px 50px;
		width: 85%;
	}
	a.tab_area{
		background-color: #ecf2f9;
		font-size: 12pt;
		padding: 5px;
		border-radius: 5px;
		color: black;
		text-decoration: none;
	}
	a.tab_area:hover{
		text-decoration: none;
		color: black;
	}	
	a.selected{
		background-color: #0071bd;
		color: white;
	}
	a.selected:hover{
		color: white;
	}
	span.period {
		margin-right: 10px;
	}
	span.chkbox {
		font-size: 9pt;
		border: solid 1px gray;
		color: gray;
		border-radius: 5px;
		margin-right: 2px;
		padding-left: 3px;	
	}	
	input.dateType{
		display: none;
	}	
	input.datepicker{
		width: 100px;
		height: 20px;
	}
	label{
		cursor: pointer;
		font-weight: normal !important; 
	}
	table{
		margin: 10px 5px;
		width: 90%;
	}
	tr, td{
		border: solid 1px gray;
	}
	td.th{
		background-color: #e6e6e6;
		padding: 5px;
	}
</style>

<link rel="stylesheet" type="text/css" href="<%= ctxPath%>/resources/css/datepicker.css"/>

<script type="text/javascript" src="<%= ctxPath%>/resources/jquery-ui-1.11.4.custom/jquery-ui.min.js"></script>

<script type="text/javascript">
$(document).ready(function(){
	
	var today = new Date();
	var dd = today.getDate();
	var mm = today.getMonth()+1; //January is 0!
	var yyyy = today.getFullYear();

	if(dd<10) {
	    dd='0'+dd
	} 

	if(mm<10) {
	    mm='0'+mm
	} 

	today = yyyy+'-'+mm+'-'+dd;
	//console.log(today);
	
	// === 전체 datepicker 옵션 일괄 설정하기 ===  
    //     한번의 설정으로 $("input#fromDate"), $('input#toDate')의 옵션을 모두 설정할 수 있다.
	$(function() {
		// 모든 datepicker에 대한 공통 옵션 설정
		$.datepicker.setDefaults({
			dateFormat: 'yy-mm-dd'		// Input Display Format 변경
			,showOtherMonths: true		// 빈 공간에 현재월의 앞뒤월의 날짜를 표시
			,showMonthAfterYear:true	// 년도 먼저 나오고, 뒤에 월 표시
			,changeYear: true			// 콤보박스에서 년 선택 가능
			,changeMonth: true			// 콤보박스에서 월 선택 가능                
			,showOn: "both"				// button:버튼을 표시하고,버튼을 눌러야만 달력 표시 ^ both:버튼을 표시하고,버튼을 누르거나 input을 클릭하면 달력 표시  
			,buttonImage: "http://jqueryui.com/resources/demos/datepicker/images/calendar.gif" // 버튼 이미지 경로
			,buttonImageOnly: true		// 기본 버튼의 회색 부분을 없애고, 이미지만 보이게 함
		//	,buttonText: "선택"			// 버튼에 마우스 갖다 댔을 때 표시되는 텍스트                
			,yearSuffix: "년"			// 달력의 년도 부분 뒤에 붙는 텍스트
			,monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'] // 달력의 월 부분 텍스트
			,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] // 달력의 월 부분 Tooltip 텍스트
			,dayNamesMin: ['일','월','화','수','목','금','토'] // 달력의 요일 부분 텍스트
			,dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'] // 달력의 요일 부분 Tooltip 텍스트
		//	,minDate: "-1M"				// 최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전)
		//	,maxDate: "+1M"				// 최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)                    
		});

		// input을 datepicker로 선언
		$("input#fromDate").datepicker();                    
		$("input#toDate").datepicker();		
		
		if($('input#hiddendate').val() == today || $('input#hiddendate').val() == ""){
			// From의 초기값을 3개월 전으로 설정
			$('input#fromDate').datepicker('setDate', '-3M'); // (-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, +1M:한달후, +1Y:일년후)
			// To의 초기값을 오늘로 설정
			$('input#toDate').datepicker('setDate', 'today'); // (-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, +1M:한달후, +1Y:일년후)		
			}
		else{
			// From의 초기값을 3개월 전으로 설정
			$('input#fromDate').datepicker('setDate', $('input#hiddendate').val()); 
			// To의 초기값을 오늘로 설정
			$('input#toDate').datepicker('setDate', $('input#hiddendate2').val()); 
		}
		
	});
	
});
	
	//Function Declaration
	function setSearchDate(start){
		var num = start.substring(0,1);
		var str = start.substring(1,2);
	
		var today = new Date();
	
		var endDate = $.datepicker.formatDate('yy-mm-dd', today);
		$('#toDate').val(endDate);
		
		if(str == 'd'){
			today.setDate(today.getDate() - num);
		} else if (str == 'w'){
			today.setDate(today.getDate() - (num*7));
		} else if (str == 'm'){
			today.setMonth(today.getMonth() - num);
			today.setDate(today.getDate() + 1);
		}
		
		var startDate = $.datepicker.formatDate('yy-mm-dd', today);
		$('#fromDate').val(startDate);
		
		// 종료일은 시작일 이전 날짜 선택하지 못하도록 비활성화
		$("#toDate").datepicker( "option", "minDate", startDate );
		
		// 시작일은 종료일 이후 날짜 선택하지 못하도록 비활성화
		$("#fromDate").datepicker( "option", "maxDate", endDate );	
	
		
	}
</script>

<div class="section">
<h3 style="font-size: 22pt; font-weight: bold;">임시저장함</h3>
	<div class="tab_select">
		<a href="<%= ctxPath%>/t1/myDocuNorm_temp.tw" class="tab_area">일반 결재 문서</a> 
		<a href="<%= ctxPath%>/t1/myDocuSpend_temp.tw" class="tab_area">지출 결재 문서</a>
		<a href="<%= ctxPath%>/t1/myDocuVacation_temp.tw" class="tab_area selected">근태/휴가 결재 문서</a>			
	</div>
	
	<table>
		<tr>
			<td width="20%" class="th">결재상태</td>
			<td width="80%">&nbsp;&nbsp;
				<select name="status" id="status">
					<option>전체보기</option>
					<option value="0">제출</option>
					<option value="1">결재진행중</option>
					<option value="2">반려</option>
					<option value="3">승인완료</option>
				</select>
			</td>
		</tr>
		<tr>
			<td class="th">작성일자</td>
			<td>&nbsp;&nbsp;
				<span class="period">
					<span class="chkbox">
						<input type="radio" class="dateType" id="dateType1" onclick="setSearchDate('0d')"/>
						<label for="dateType1">오늘</label>
					</span>
					<span class="chkbox">
						<input type="radio" class="dateType" id="dateType2" onclick="setSearchDate('1w')"/>
						<label for="dateType2">1주일</label>
					</span>
					<span class="chkbox">
						<input type="radio" class="dateType" id="dateType3" onclick="setSearchDate('1m')"/>
						<label for="dateType3">1개월</label>
					</span>
					<span class="chkbox">
						<input type="radio" class="dateType" id="dateType4" onclick="setSearchDate('3m')"/>
						<label for="dateType4">3개월</label>
					</span>
					<span class="chkbox">
						<input type="radio" class="dateType" id="dateType5" onclick="setSearchDate('6m')"/>
						<label for="dateType5">6개월</label>
					</span>
				</span>
				<input type="hidden" value="${fromDate}" id="hiddendate"/>
				<input type="hidden" value="${toDate}" id="hiddendate2"/>
				<input type="text" class="datepicker" id="fromDate" name="fromDate" autocomplete="off" > - <input type="text" class="datepicker" id="toDate" name="toDate" autocomplete="off">					
			</td>
		</tr>
		<tr>
			<td class="th">일반 결재 문서</td>
			<td>&nbsp;&nbsp;
				<label for="chx1"><input type="checkbox" id="chx1" value="1"> 병가</label>&nbsp;&nbsp;
				<label for="chx2"><input type="checkbox" id="chx2" value="2"> 반차</label>&nbsp;&nbsp;
				<label for="chx3"><input type="checkbox" id="chx3" value="3"> 연차</label>&nbsp;&nbsp;
				<label for="chx4"><input type="checkbox" id="chx4" value="4"> 경조휴가</label>&nbsp;&nbsp;
				<label for="chx5"><input type="checkbox" id="chx5" value="5"> 출장</label>&nbsp;&nbsp;
				<label for="chx6"><input type="checkbox" id="chx6" value="6"> 추가근무</label>&nbsp;&nbsp;
			</td>
		</tr>			
		<tr>
			<td class="th">문서검색</td>
			<td>&nbsp;&nbsp;
				<select name="sort" id="sort">
					<option value="0">제목</option>
					<option value="1">문서번호</option>												
				</select>&nbsp;
				<input type="text" style="height: 20px;"/> <button type="button">검색</button>
			</td>
		</tr>
	
	</table>
</div>
