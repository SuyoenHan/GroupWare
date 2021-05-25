<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<style type="text/css">
</style>


<script type="text/javascript">

	$(document).ready(function(){
		
		$("input#allday").click(function(){
			
			if($("input#allday").prop("checked",true){
			var Stime=$("input#starttime").val('00:00:00');
			var Etime = $("input#endtime").val('23:59:00');
			console.log("시작시간"+Stime.val());
			console.log("시작시간"+Etime.val());
			}
		});
		
		$("button#register").click(function(){
			alert($("input#starttime").val());
			
		});
		
	}); // end of $(document).ready(function(){}----------------
</script>

<h3>일정 등록</h3>
<div>
	<form name="insertFrm">
		<table>
			<tr>
				<td>일자</td>
				<td>
					<input type="date" id="startdate" value="${requestScope.chooseDate}"/>&nbsp;<input type="time" id="starttime" /> ~ 
					<input type="date" id="enddate" value="${requestScope.chooseDate}"/>&nbsp;<input type="time" id="endtime" />
					<label for="allday"><input type="checkbox" id="allday" name="allday"/><span>종일</span></label>
				</td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" name="subject"/></td>
			</tr>
			
			<tr>
				<td>캘린더선택</td>
				<td>
				
				<c:if test="${sessionScope.loginuser.employeeid eq 'tw005' }">
					<select class="calType">
						<option value="">선택하세요</option>
						<option value="1">내 캘린더</option>
						<option value="2">전체 캘린더</option>
					</select>
				</c:if>
				<c:if test="${sessionScope.loginuser.employeeid ne 'tw005' }">
					<select class="calType">
						<option value="">선택하세요</option>
						<option value="1">내 캘린더</option>
					</select>
				</c:if>
				</td>
			</tr>
			<tr>
				<td>색상</td>
				<td><input type="color" id="hideAfterPaletteSelect"/></td>
			</tr>
			<tr>
				<td>장소</td>
				<td><input type="text" name="place"/></td>
			</tr>
			
			<tr>
				<td>공유</td>
				<td><input type="text" name="share"/></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea rows="10" cols="100" style="width: 95%; height: 200px;" name="content" id="content"></textarea></td>
			</tr>
		</table>
	</form>
	<button type="button" id="register">저장</button>
</div>