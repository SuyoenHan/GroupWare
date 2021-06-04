<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String ctxPath = request.getContextPath();%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<style type="text/css">

div#reserveList{
	clear: both;
	margin-top: 150px;
}

.table th {
	color: #fff;
	background-color: #395673;
	text-align: center;
}

.table td{
	text-align: center;
	vertical-align: middle;
}

select{
	height: 25px;
}

button.btn_normal{
	background-color: #0071bd;
	border: none;
	color: white;
	width: 60px;
	height: 30px;
	font-size: 12pt;
	padding: 3px 0px;
}

button.btn_r{
	border: solid 1px #ccc;
	width:60px;
	height: 30px;
	font-size: 12pt;
	padding: 1px 0px;
}
</style>

<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript" src="<%= ctxPath%>/resources/jquery-ui-1.11.4.custom/jquery-ui.min.js"></script>
<script type="text/javascript">

	$(document).ready(function(){
		
		// 검색할 때 필요한 datepicker
		  //모든 datepicker에 대한 공통 옵션 설정
		    $.datepicker.setDefaults({
		        dateFormat: 'yy-mm-dd' //Input Display Format 변경
		        ,showOtherMonths: true //빈 공간에 현재월의 앞뒤월의 날짜를 표시
		        ,showMonthAfterYear:true //년도 먼저 나오고, 뒤에 월 표시
		        ,changeYear: true //콤보박스에서 년 선택 가능
		        ,changeMonth: true //콤보박스에서 월 선택 가능                
		        ,monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'] //달력의 월 부분 텍스트
		        ,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 Tooltip 텍스트
		        ,dayNamesMin: ['일','월','화','수','목','금','토'] //달력의 요일 부분 텍스트
		        ,dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'] //달력의 요일 부분 Tooltip 텍스트             
		    });
		
		    //input을 datepicker로 선언
		    $("input#fromDate").datepicker();                    
		    $("input#toDate").datepicker();
		    
		    $('#fromDate').datepicker("option", "maxDate", $("#toDate").val());
		    $('#fromDate').datepicker("option", "onClose", function ( selectedDate ) {
		        $("#toDate").datepicker( "option", "minDate", selectedDate );
		    });

		    $('#toDate').datepicker("option", "minDate", $("#fromDate").val());
		    $('#toDate').datepicker("option", "onClose", function ( selectedDate ) {
		    $("#fromDate").datepicker( "option", "maxDate", selectedDate );
		    });
		    
		    //From의 초기값을 오늘 날짜로 설정
		    $('input#fromDate').datepicker('setDate', '-1M'); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, +1M:한달후, +1Y:일년후)
		    
		    //To의 초기값을 3일후로 설정
		    $('input#toDate').datepicker('setDate', 'today'); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, +1M:한달후, +1Y:일년후)
		  
		 	
		    // 검색 할 때 엔터를 친 경우
		    $("input#searchWord").keydown(function(event){
					if(event.keyCode == 13){ 
						goSearch();
					}
		    });
		   
		    var startdate = "${requestScope.paraMap.startdate}";
		    var enddate = "${requestScope.paraMap.enddate}";
		    
		    if(startdate != "" && enddate != "") {
		    	$("input#fromDate").val(startdate);
	    	    $("input#toDate").val(enddate);
		    } 
				    
		    if(${not empty requestScope.paraMap}){
				$("select[name=cno]").val("${requestScope.paraMap.cno}");
				$("select[name=rstatus]").val("${requestScope.paraMap.rstatus}");
				$("input#searchWord").val("${requestScope.paraMap.searchWord}");
			}
		    
		    
	
		    
	}); // end of $(document).ready(function(){})----------------
	
	
	function goSearch(){
		
		var frm = document.searchRoom;
        frm.method="get";
        frm.action="<%= ctxPath%>/t1/myReservedCar.tw";
        frm.submit();
				

	}
	
	function delMyrscar(rscno, rcdate, rctime, carname){
		if(confirm(rcdate+" , "+rctime +"시에 예약된 "+carname+" 내역을 취소하시겠습니까?")){
			$.ajax({
				url: "<%= ctxPath%>/t1/delmyReservedCar.tw",
				type:"post",
				data:{"rscno": rscno},
				dataType:"json",
				success:function(){
					alert("예약내역이 삭제되었습니다.");
					location.href="javascript:history.go(0);"; 
				}, 
				error: function(request, status, error){
		            alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
		  	    }
			});
		}
	}
	
</script>

<div id="myReserved"  style="margin-left: 80px;">
	
	<h3 style="margin-top: 20px !important;">나의 예약 내역</h3>
	

	<div style="float: left; margin-top: 50px;">
		<ul class="nav nav-pills">
			 <li><a href="<%= ctxPath%>/t1/myReservedRoom.tw">회의실 예약 내역</a></li>
			 <li class="active"><a href="<%= ctxPath%>/t1/myReservedCar.tw">차량 예약 내역</a></li>
			 <li><a href="<%= ctxPath%>/t1/myReservedGoods.tw">사무용품 예약 내역</a></li>
		</ul>
	</div>

    <div style="float: right; margin-top: 70px;  margin-right: 175px;">
    	<form name="searchRoom">
			<input type="text" id="fromDate" name="startdate" style="width: 90px;" readonly="readonly">&nbsp;&nbsp; 
		     -&nbsp;&nbsp; <input type="text" id="toDate" name="enddate" style="width: 90px;" readonly="readonly">&nbsp;&nbsp;
		     <select name="cno">
		     	<option value="0">차량명</option>
		     	<c:forEach var="car" items="${requestScope.carList}">	
		     		<option value="${car.cno}">${car.carname}</option>
		     	</c:forEach>
		     </select>
		     <select name="rstatus">
		     	<option value="-1">승인여부</option>
		     	<option value="0">미승인</option>
		     	<option value="1">승인완료</option>
		     </select>
			<input type="text" name="searchWord" id="searchWord" placeholder="목적을 입력하세요."/>		
		<button type="button" class="btn_normal" onclick="goSearch()">검색</button>	
		</form>
	</div>



	<div id="reserveList" >
		<table class="table" style="width: 90%;">
			<thead>
				<tr>
					<th style="width: 12%">예약날짜</th>
					<th style="width: 12%">시간</th>
					<th style="width: 12%">차량명</th>
					<th style="width: 10%">도착지</th>
					<th style="width: 10%">탑승자</th>
					<th style="width: 20%">목적</th>
					<th style="width: 10%">승인여부</th>
					<th style="width: 10%">취소</th>
				</tr>
			</thead>		
			
			<c:if test="${empty myCarList}">
				<tr><td colspan="8">차량 예약 내역이 없습니다.</td></tr>
			</c:if>
			
			<c:if test="${not empty myCarList}">
				<c:forEach var="myCar" items="${requestScope.myCarList}">	
					<tr>
						<td style="vertical-align: middle;">${myCar.rcdate}</td>
						<c:if test="${myCar.rctime<10}">
						<td style="vertical-align: middle;">0${myCar.rctime}:00 ~ ${myCar.rctime+1}:00</td>
						</c:if>
						<c:if test="${myCar.rctime >=10}">
						<td style="vertical-align: middle;">${myCar.rctime}:00 ~ ${myCar.rctime+1}:00</td>
						</c:if>
						<td style="vertical-align: middle;">${myCar.carname}</td>
						<td style="vertical-align: middle;">${myCar.rdestination}</td>
						<td style="vertical-align: middle;">${myCar.rcpeople}</td>
						<td style="vertical-align: middle;">${myCar.rcsubject}</td>
						<c:if test="${myCar.cstatus == 0}">
							<td style="vertical-align: middle;">미승인</td>
						</c:if>
						<c:if test="${myCar.cstatus == 1}">
							<td style="vertical-align: middle;">승인 완료</td>
						</c:if>
						<td><button type="button" class='btn_r' onclick="delMyrscar('${myCar.rscno}','${myCar.rcdate}','${myCar.rctime}','${myCar.carname}')">취소</button></td>
					</tr>
				</c:forEach>
			</c:if>	
			
		</table>	
	
	</div>

	<div align="center">${pageBar}</div>

</div>