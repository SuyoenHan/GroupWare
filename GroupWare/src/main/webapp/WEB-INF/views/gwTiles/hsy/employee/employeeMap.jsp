<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% String ctxPath= request.getContextPath(); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style type="text/css">

	div#employeeMap {
		background-color: #0071bd;
		color: #fff;
		font-weight: bold;
	}

	div#content{
		width: 80%;
		padding: 30px;
	}
	
	div#employeeMapTitle{
		margin-bottom:30px;
	}
	
</style>

<script type="text/javascript">

	$(document).ready(function(){
		
		func_height1(); // sidemenu와 content길이 맞추는 메소드
		
	}); // end of $(document).ready(function(){----------

</script>

<div id="content" style="background-color: #fff;">
	
	<div id="employeeMapTitle">t1works 주소록</div>
	
	<div style="border:solid 1px red; float:left;">
		<button type="button">전체보기</button>
		<button type="button">전체닫기</button>
	</div>
	
	<div style="border:solid 1px red; float:right;">
		<select>
			<option>선택하세요</option>
			<option>직원명</option>
			<option>부서명</option>
			<option>직위</option>
			<option>이메일</option>
		</select>
		<input type="text" placeholder="검색어를 입력하세요."/>
		<button type="button">확인</button>
	</div>
	
	<div style="clear:both;"></div>
	
	<div style="border: solid 1px red; float:left;">
		<div>t1works</div>
		<c:forEach var="departmentVO" items="${departmentList}">
			<div>${deartmentVO.dname}</div>
		</c:forEach>
		
	</div>
	
	<div style="border: solid 1px red; float:right;">
		<div>직원목록</div>
		<table>
			<tr>
				<th>사번</th>
				<th>직원명</th>
				<th>직위</th>
				<th>소속</th>
				<th>전화번호</th>
				<th>연락처</th>
				<th>이메일</th>
			</tr>
		</table>
	</div>
	
	
</div>