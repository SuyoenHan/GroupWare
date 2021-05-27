<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String ctxPath = request.getContextPath(); %>

<style type="text/css">
a{
    color: #000;
    text-decoration: none;
    cursor: pointer;
}

a:hover {
    color: #000;
    cursor: pointer;
    text-decoration: none;
	font-weight: bold;
}
th {
	vertical-align: middle;
}
</style>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<script type="text/javascript">


// 일정 삭제하기
function delSchedule(sdno){
	
	if(confirm("일정을 삭제하시겠습니까?")){
		$.ajax({
			url: "<%= ctxPath%>/t1/schedule/deleteSchedule.tw",
			type: "post",
			data: {"sdno":sdno},
			dataType: "json",
			success:function(json){
				if(json.n==1){
					alert("일정을 삭제하였습니다.");
					location.href="<%= ctxPath%>/t1/schedule.tw";
				}
			},
			error: function(request, status, error){
	            alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
	        }
		});
	}
	else{
		return false; 
	}
}

</script>



<h3 style="display: inline-block;">일정 상세보기</h3>&nbsp;<a  href="<%= ctxPath%>/t1/schedule.tw"><span>◀캘린더로 돌아가기</span></a>
<div>
		<table id="schedule" class="table table-bordered">
			<tr>
				<th style="width: 160px; vertical-align: middle;" >일자</th>
				<td>
					<span>${svo.startdate}</span>&nbsp;~&nbsp;<span>${svo.enddate}</span>
				</td>
			</tr>
			<tr>
				<th style="vertical-align: middle;">제목</th>
				<td>${svo.subject}</td>
			</tr>
			
			<tr>
				<th style="vertical-align: middle;">캘린더선택</th>
				<td>
				<c:if test="${svo.fk_bcno eq 2}">
					전체 캘린더
				</c:if>
				<c:if test="${svo.fk_bcno eq 1}">
					<!-- join한 값 가져와야함 -->
					${svo.scvo.scname}
				</c:if></td>
			</tr>
			<tr>
				<th style="vertical-align: middle;">장소</th>
				<td>${svo.place}</td>
			</tr>
			
			<tr>
				<th style="vertical-align: middle;">공유</th>
				<td>${svo.joinemployee}</td>
			</tr>
			<tr>
				<th style="vertical-align: middle;">내용</th>
				<td><textarea id="content" rows="10" cols="100" style="width: 95%; height: 200px;" readonly>${svo.content}</textarea></td>
			</tr>
		</table>

	<c:set var="bcno" value="${svo.fk_bcno}"/>
	<c:set var="employeeid" value="${sessionScope.loginuser.employeeid}"/>
	<c:choose>
		<c:when test="${bcno ==2 && employeeid=='tw005'}">
			<button type="button" id="edit" class="btn" onclick="editSchedule('${svo.sdno}')">수정</button>
			<button type="button" class="btn" onclick="delSchedule('${svo.sdno}')">삭제</button>
		</c:when>
		<c:when test="${bcno ==1}">
			<button type="button" id="edit" class="btn" onclick="editSchedule('${svo.sdno}')">수정</button>
			<button type="button" class="btn" onclick="delSchedule('${svo.sdno}')">삭제</button>
		</c:when>
	</c:choose>
	<button type="button" id="cancle" class="btn" onclick="javascript:location.href='<%= ctxPath%>/t1/schedule.tw'">취소</button>
</div>