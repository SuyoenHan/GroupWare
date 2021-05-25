<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String ctxPath = request.getContextPath(); %>
<link rel="stylesheet" type="text/css" href="<%=ctxPath %>/resources/css/kdn.css" />
<!-- Font Awesome -->
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
<!-- Bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script type="text/javascript">

</script>

<div id="mail-header" style="background-color: #e6f2ff; width: 100%; height: 120px; padding: 20px;">
	 <h4 style="margin-bottom: 20px; font-weight: bold;">보낸메일함</h4>
	 <div id="left-header">
		 <button type="button">삭제</button>
		 <button type="button">완전삭제</button>
		 <select name="readMark">
		 	<option value="">읽음표시</option>
		 	<option value="read">읽음</option>
		 	<option value="unread">읽지않음</option>
		 </select>
	 <div id="right-header" style="float: right;">
		 <select name="mailSearch">
		 	<option value="">선택</option>
		 	<option value="subject">제목</option>
		 	<option value="sender">받는사람</option>
		 	<option value="content">내용</option>
		 </select>
		<input type="text" />
	 	<button type="submit">검색</button>
	 	<select name="numberOfEmails">
		 	<option value="10">10개보기</option>
		 	<option value="20">20개보기</option>
		 	<option value="30">30개보기</option>
		 </select>
	 </div>
	 </div>
</div>

 <div id="mail-list" style="height: 100%;">
 	<table class="table" >
 		<thead>
 			<tr>
 				<th width=3%><input type="checkbox" name="selectAll" style="margin-left: 10px;"/></th>
 				<th width=3%>중요</th>
 				<th width=3%>첨부<br>파일</th>
 				<th width=13%>보낸사람+이메일주소</th>
 				<th width=70%>제목</th>
 				<th width=20%>발신일시</th>
 			</tr>
 		</thead>
 		<tbody>
 		<!-- 메일목록은 c:forEach문 사용할 것 -->
 			<tr>
 				<th><input type="checkbox" name="selectThis" style="margin-left: 10px;"/></th>
 				<td>&star;</td>
 				<td></td>
 				<td>이순신(leess@t1works.com)</td>
 				<td>테스트 메일입니다.</td>
 				<td>2021-05-22 16:03</td>
 			</tr>
 			<tr>
 				<th><input type="checkbox" name="selectThis" style="margin-left: 10px;"/></th>
 				<td>&star;</td>
 				<td><i class="fas fa-paperclip"></i></td>
 				<td>박보영(parkby@t1works.com)</td>
 				<td>입사를 축하합니다.</td>
 				<td>2021-05-20 09:28</td>
 			</tr>
 		</tbody>
 	</table>
 
 </div>
