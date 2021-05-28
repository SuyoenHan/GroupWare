<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String ctxPath = request.getContextPath(); %>
<link rel="stylesheet" type="text/css" href="<%=ctxPath%>/resources/css/kdn/board.css"/>

<div id="board-container">
	<i class="fas fa-bullhorn fa-lg"></i>&nbsp;<span style="display: inline-block; font-size:22px;">공지사항</span>
	<!-- 조건검색&보기개수 선택 -->
	<div id="searchform">
		<select style="float:right;">
			<option value="10">10개씩보기</option>
			<option value="20">20개씩보기</option>
			<option value="30">30개씩보기</option>
		</select>
		<form name="searchFrm" style="float:right;">
			<select name="searchType" id="searchType">
				<option value="subject">제목</option>
				<option value="name">작성자</option>
				<option value="content">본문</option>
			</select>
			<input type="text" name="searchWord" id="searchWord" autocomplete="off" />
			<button type="button">검색</button>
		</form>
	</div>
	<!-- 게시판 글목록 -->
	<div id="board-table-div">
		<table id="board-table" class="table">
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
			<c:forEach items="${requestScope.boardList}" >
				<tr class="tbody">
					<td>2</td>
					<td>금월 공지사항입니다.</td>
					<td>이하나</td>
					<td>21-05-20</td>
					<td>3</td>
					<td></td>
				</tr>
			</c:forEach>
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

		<button type="button" class="btn-style float-right" onclick="javascript:location.href='noticePostUpload.tw'"><span style="color: #ffffff;">글쓰기</span></button>
	</div>
</div>