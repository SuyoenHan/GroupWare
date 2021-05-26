<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String ctxPath = request.getContextPath(); %>
<link rel="stylesheet" type="text/css" href="<%=ctxPath %>/resources/css/kdn/board.css" />

<h3>공지사항</h3>
<table class="table" style="width: 65%;">
	<thead>
		<tr class="thead">
			<th width=5% align="center" id="postNum">No.</th>
			<th width=40% align="center" id="subject">제목</th>
			<th width=10% align="center" id="writer">작성자</th>
			<th width=10% align="center" id="regDate">작성일</th>
			<th width=5% align="center" id="readCount">조회수</th>
			<th width=5% align="center" id="uploadFile">파일</th>
		</tr>
	</thead>
	<tbody>
		<tr class="tbody">
			<td>2</td>
			<td>금월 공지사항입니다.</td>
			<td>이하나</td>
			<td>21-05-20</td>
			<td>3</td>
			<td></td>
		</tr>
		<tr class="tbody">
			<td>1</td>
			<td>4월 공지사항입니다.</td>
			<td>이하나</td>
			<td>21-04-20</td>
			<td>3</td>
			<td></td>
		</tr>
	</tbody>
	
</table>


<button type="button" onclick="javascript:location.href='postupload.tw'">글쓰기</button>