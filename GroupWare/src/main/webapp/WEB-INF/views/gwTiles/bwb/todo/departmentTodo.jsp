<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	String ctxPath = request.getContextPath();
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>

</style>

<script type="text/javascript">

	$(document).ready(function(){
		
		// 검색하기 
		$("button#whatSearch").click(function(){

			var period = $("input:radio[name=periodChoice]:checked").val();
			var statusChoiceArr = new Array();
			
			$("input:checkbox[name=statusChoice]:checked").each(function(index,item){
				
				var statusChoice = $(this).val();
				statusChoiceArr.push(statusChoice);
				
			}); // end of $("input:radio[name=statusChoice]").each(function(index,item){
			
			var statusChoice_es = statusChoiceArr.join();
			
			var searchProject = $("input#searchProject").val();
			var searchWhoCharge = $("input#searchWhoCharge").val();
			
			location.href="<%= ctxPath%>/t1/departmentTodo.tw?period="+period+"&statusChoice_es="+statusChoice_es+"&searchProject="+searchProject+"&searchWhoCharge="+searchWhoCharge;

			
		});
		
	})// end of $(document).ready(function(){

</script>
<div id="content" style="border: solid 1px black;">
	<h2>XX부서의 업무현황</h2>
	<div id="DepartmentMenu">
		<form name="whatSearch">
		<div id="period">
			<span>기간(기한일)</span>
			<label id="period1"><input name="periodChoice" type="radio" id="period1" value="7">1주일</label>
			<label id="period2"><input name="periodChoice" type="radio" id="period2" value="30">1개월</label>
			<label id="period3"><input name="periodChoice" type="radio" id="period3" value="90">3개월</label>
			<label id="period4"><input name="periodChoice" type="radio" id="period4" value="-1">전체</label>
		</div>
		<div id="doStatus">
			<label id="status1"><input name="statusChoice" type="checkbox" id="status1" value="1">미배정</label>
			<label id="status2"><input name="statusChoice" type="checkbox" id="status2" value="2">미시작</label>
			<label id="status3"><input name="statusChoice" type="checkbox" id="status3" value="3">진행중</label>
			<label id="status4"><input name="statusChoice" type="checkbox" id="status4" value="4">보류</label>
			<label id="status5"><input name="statusChoice" type="checkbox" id="status5" value="5">지연</label>
			<label id="status6"><input name="statusChoice" type="checkbox" id="status6" value="6">완료</label>
		</div>
		<div id="projectName">
			<span>프로젝트명</span>
			<input type="text" id="searchProject" name="searchProject" placeholder="내용을 입력해주세요."  />
		</div>
		<div id="whoAssigned">
			<span>담당자명</span>
			<input type="text" id="searchWhoCharge" name="searchWhoCharge" placeholder="내용을 입력해주세요."  />
		</div>	
		<button type="button" id="whatSearch">검색</button>
		<button type="reset">초기화</button>
		</form> 
	</div>
	
	
	<div id="DepartmentInfo">
		<div id="infoMenu">
			<span>순번</span>
			<span>프로젝트명</span>
			<span>배정자</span>
			<span>배정일</span>
			<span>시작일</span>
			<span>기한일</span>
			<span>완료일</span>
			<span>담당자</span>
			<span>상태</span>
		</div>
	
		
		<div id="infoContent">
			<c:if test="${not empty productList}">
				<c:forEach var="product" items="${productList}">
					<div>
					<span>${product.rno}</span>
					<span>${product.pName}</span>
					<span>${product.name}</span>
					<span>${product.assignDate}</span>
					<span>${product.startDate}</span>
					<span>${product.dueDate}</span>
					<span>${product.endDate}</span>
					<span>${product.employeeName}</span>
					<c:choose>
						<c:when test="${product.ingdetail eq '0'}"><button class="ingdetail">진행중</button></c:when>
						<c:when test="${product.ingdetail eq '-1'}"><button class="ingdetail">보류</button></c:when>
						<c:when test="${product.startDate == '-'}">
							<c:if test="${product.assignDate == '-'}">
								<button class="ingdetail">미배정</button>
							</c:if>
							<c:if test="${product.assignDate != '-'}">
								<button class="ingdetail">미시작</button>
							</c:if>
						</c:when>
						<c:when test="${product.endDate != '-'}"><button class="ingdetail">완료</button></c:when>
						<c:otherwise><button class="ingdetail">지연</button></c:otherwise>
					</c:choose>
					</div>
				</c:forEach>
			</c:if>
			<c:if test="${empty productList}">
				${loginuser.name}님의 부서업무가 존재하지 않습니다.
			</c:if>
		</div>
		
		<div id="pageBar">${pageBar}</div>
	</div>
</div>