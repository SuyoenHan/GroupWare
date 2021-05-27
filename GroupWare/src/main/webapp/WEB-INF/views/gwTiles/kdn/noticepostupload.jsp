<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String ctxPath = request.getContextPath(); %>    
<link rel="stylesheet" type="text/css" href="<%=ctxPath %>/resources/css/kdn/board.css" />
<script src="<%=ctxPath%>/resources/js/kdn/board.js"></script>

<div id="board-container">
	 <i class="fas fa-bullhorn fa-lg"></i>&nbsp;<span style="display: inline-block; font-size:22px; margin-bottom: 20px;">공지사항</span>
	
	 <form name="postFrm"> 
	    <table id="table">
	       <tr>
	          <th>성명</th>
	          <td>
	              <input type="text" name="name" class="short" value="${sessionScope.loginuser.name}" readonly />
	              <span>${sessionScope.loginuser.name}</span>
	          </td>
	       </tr>
	       <tr>
	          <th>구분</th>
	          <td>
	              <input type="hidden" name="fk_employeeid" value="${sessionScope.loginuser.employeeid}" />
	              <select name="fk_categnum" style="width: 100px; border-color: #ccc;">
	              	<option value="1">전체공지</option>
	              	<option value="2">총무공지</option>
	              	<option value="3">경조사</option>
	              </select>
	          </td>
	       </tr>
	       <tr>
	          <th>제목</th>
	          <td>
	             <input type="text" name="subject" id="subject" style="width: 100%;" />       
	          </td>
	       </tr>
	       <tr>
	          <th>내용</th>
	          <td>
	             <textarea rows="10" cols="100" style="width: 100%; height: 100%;" name="content" id="content"></textarea>       
	          </td>
	       </tr>
	       
	       <%-- === #150. 파일첨부 타입 추가하기 === --%>
	       
	       
	       <tr>
	          <th>비밀번호</th>
	          <td>
	             <input type="password" name="pw" id="pw" class="short" />       
	          </td>
	       </tr>
	    </table>
	      
	    <div id="btn-container" class="float-right">
	       <button type="button" id="btnWrite" class="btn-style"><span style="color: #ffffff;">등록</span></button>
	       <button type="button" class="btn-style" onclick="javascript:history.back()"><span style="color: #ffffff;">취소</span></button>
	    </div>
	 </form>
</div>   