<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String ctxPath = request.getContextPath(); %>    
<style type="text/css">

table#schedule{
	border: solid 1px #CCD1D1;
	border-collapse: collapse;
}

th, td{
 	border: solid 1px #CCD1D1;
 	padding: 10px 5px;
}



</style>


<script type="text/javascript">

	$(document).ready(function(){
		
		$("select.scategory").hide();
		// === *** 달력 관련 *** === //
		
		
		// 시작시간, 종료시간		
		var html="";
		for(var i=0;i<24;i++){
			if(i<10){
				html+="<option value='0"+i+"'>0"+i+"</option>"
			}
			else{
				html+="<option value="+i+">"+i+"</option>"
			}
			
			$("select#startHour").html(html);
			$("select#endHour").html(html);
		}
		
		// 시작 종료 분 
		html="";
		for(var i=0;i<60;i=i+5){
			if(i<10){
				html+="<option value='0"+i+"'>0"+i+"</option>"
			}
			else{
				html+="<option value="+i+">"+i+"</option>"
			}
			$("select#startMin").html(html);
			$("select#endMin").html(html);
			
		}
		
		// '종일' 체크박스 클릭시
		$("input#allDay").click(function() {
			var checked = $('input#allDay:checked').val();
			if(checked == "1")	{
				$("select#startHour").val("00");
				$("select#startMin").val("00");
				$("select#endHour").val("23");
				$("select#endMin").val("55");
				$("select#startHour").prop("disabled",true);
				$("select#startMin").prop("disabled",true);
				$("select#endHour").prop("disabled",true);
				$("select#endMin").prop("disabled",true);
			} else {
				$("select#startHour").prop("disabled",false);
				$("select#startMin").prop("disabled",false);
				$("select#endHour").prop("disabled",false);
				$("select#endMin").prop("disabled",false);
			}
		});
		
		//
		
		// 캘린더 종류 선택
		$("select.calType").change(function(){
			var fk_bcno =$("select.calType").val();
			var fk_employeeid = $("input[name=fk_employeeid]").val();
				$.ajax({
					url: "<%= ctxPath%>/t1/selectCategory.tw",
					data: {"fk_bcno":fk_bcno, "fk_employeeid":fk_employeeid},
					dataType: "json",
					success:function(json){
						var html ="";
						if(json.length>0){
							
							$.each(json, function(index, item){
								html+="<option value='"+item.scno+"'>"+item.scname+"</option>"
							});
							$("select.scategory").html(html);
							$("select.scategory").show();
						}
					},
					error: function(request, status, error){
			            alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
						
					}
					
				});
			
			
		});
		
		
		
		
		$("button#register").click(function(){
			
		//	alert($("input#color").val());
			var subject = $("input#subject").val().trim();
			var calType = $("select.calType").val().trim();
			
		
			
			console.log("sdate"+sdate);
			console.log("edate"+edate);
			console.log($("select[name=scno]").val());
			// 달력 유효성 검사
			var startDate = $("input#startDate").val();	
	    	var sArr = startDate.split("-");
	    	var startDate= "";	
	    	for(var i=0;i<sArr.length;i++){
	    		startDate+=sArr[i];
	    	}
	    	var endDate = $("input#endDate").val();	
	    	var eArr = endDate.split("-");   
	     	var endDate= "";
	     	for(var i=0;i<eArr.length;i++){
	     		endDate+=eArr[i];
	     	}
			
	     	alert(startDate);
	     	var startHour= $("select#startHour").val();
	     	var endHour = $("select#endHour").val();
	     	var startMin= $("select#startMin").val();
	     	var endMin= $("select#endMin").val();
	        // 조회기간 종료일이 시작일 보다 작을 때 경고
	        if (endDate - startDate < 0){
	         	alert("종료일이 시작일 보다 작습니다."); return;
	        }
	        // 시작일과 종료일 같을 때 시간과 분에 대한 유효성 검사
	        else if(endDate==startDate){
	        	if(startHour>endHour){
	        		alert("종료일이 시작일 보다 작습니다."); return;
	        	}
	        	else if(startHour==endHour){
	        		if(startMin>endMin){
	        			alert("종료일이 시작일 보다 작습니다."); return;
	        		}
	        		else if(startMin==endMin){
	        			alert("시작일과 종료일이 동일합니다."); return;
	        		}
	        	}
	        }
	        // 제목 유효성 검사
	        if(subject==""){
				alert("제목을 입력하세요."); return;
			}
	        // 캘린더 검사
			else if(calType==""){
				alert("캘린더 종류를 선택하세요."); return;
			}
			else{
				// 달력 형태로 만들어야 한다.(시작일과 종료일)
				// 오라클에 들어갈 date 형식으로 만들기
				var sdate = startDate+$("select#startHour").val()+$("select#startMin").val()+"00";
				var edate = endDate+$("select#endHour").val()+$("select#endMin").val()+"00";
				
				alert("시작일:"+sdate);
				alert("종료일:"+edate);
		
				
				$("input[name=startdate]").val(sdate);
				$("input[name=enddate]").val(edate);
				
				
		
				var frm = document.scheduleFrm;
				frm.action="<%= ctxPath%>/t1/schedule/registerSchedule.tw";
				frm.method="post";
				frm.submit();
				
			}
		});
		
	}); // end of $(document).ready(function(){}----------------
</script>

<h3>일정 등록</h3>
<div>
	<form name="scheduleFrm">
		<table id="schedule">
			<tr>
				<th>일자</th>
				<td>
					<input type="date" id="startDate" value="${requestScope.chooseDate}"/>&nbsp; 
					<select id="startHour"></select> 시
					<select id="startMin"></select> 분
					- <input type="date" id="endDate" value="${requestScope.chooseDate}"/>&nbsp;
					<select id="endHour"></select> 시
					<select id="endMin"></select> 분
					<label for="allDay"><input type="checkbox" id="allDay" name="allDay" value="1"/><span>종일</span></label>
					<input type="text" name="startdate"/>
					<input type="text" name="enddate"/>
				</td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" id="subject" name="subject"/></td>
			</tr>
			
			<tr>
				<th>캘린더선택</th>
				<td>
				
				
					<select class="calType" name="fk_bcno">
						<c:if test="${sessionScope.loginuser.employeeid eq 'tw005' }">
							<option value="">선택하세요</option>
							<option value="1">내 캘린더</option>
							<option value="2">전체 캘린더</option>
						</c:if>
						<c:if test="${sessionScope.loginuser.employeeid ne 'tw005' }">
							<option value="">선택하세요</option>
							<option value="1">내 캘린더</option>
						</c:if>
					</select>
			
				
				&nbsp;
				<select class="scategory" name="fk_scno">
				
				</select>
				</td>
			</tr>
			<tr>
				<th>색상</th>
				<td><input type="color" id="color" name="color" value="#0071bd"/></td>
			</tr>
			<tr>
				<th>장소</th>
				<td><input type="text" name="place"/></td>
			</tr>
			
			<tr>
				<th>공유</th>
				<td><input type="text" name="joinEmp"/></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="10" cols="100" style="width: 95%; height: 200px;" name="content" id="content"></textarea></td>
			</tr>
		</table>
		<input type="hidden" value="${sessionScope.loginuser.employeeid}" name="fk_employeeid"/>
	</form>
	<button type="button" id="register">저장</button>
</div>