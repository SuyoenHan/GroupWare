<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String ctxPath = request.getContextPath(); %>
<link rel="stylesheet" type="text/css" href="<%=ctxPath %>/resources/css/kdn/mail.css" />

<script type="text/javascript">
function goView(seq){
	<%-- location.href="<%=ctxPath%>/view.action?seq="+seq; --%>
		// === #124. 페이징 처리되어진 후 특정 글제목을 클릭하여 상세내용을 본 이후 사용자가 목록보기 버튼을 클릭했을때 돌아갈 페이지를 알려주기 위해 현재 페이지 주소를 뷰단으로 넘겨준다. ===
		var frm = document.goViewFrm;
		frm.seq.value = seq;
		frm.searchType.value = "${requestScope.paraMap.searchType}";
	    frm.searchWord.value = "${requestScope.paraMap.searchWord}";
	    
		frm.method="get";
		frm.action="<%=ctxPath%>/t1/viewMail.tw";
		frm.submit();
		
	}//end of function goView('${boardvo.seq}') ---------------
</script>
<div id="mail-header" style="background-color: #e6f2ff; width: 100%; height: 120px; padding: 20px;">
	 <h4 style="margin-bottom: 20px; font-weight: bold;"><i class="fas fa-mailbox"></i>받은메일함</h4>
	 <div id="left-header">
		 <button type="button">삭제</button>
		 <button type="button">완전삭제</button>
		 <select name="readMark">
		 	<option value="">읽음표시</option>
		 	<option value="read">읽음</option>
		 	<option value="unread">읽지않음</option>
		 </select>
	 <div id="right-header" style="float: right;">
		 <select name="searchType">
		 	<option value="">선택</option>
		 	<option value="subject">제목</option>
		 	<option value="sender">보낸사람</option>
		 	<option value="content">내용</option>
		 </select>
		<input type="text" name="searchWord" />
	 	<button type="submit">검색</button>
	 	<select name="numberOfEmails">
		 	<option value="10">10개보기</option>
		 	<option value="20">20개보기</option>
		 	<option value="30">30개보기</option>
		 </select>
	 </div>
	 </div>
</div>

 <div id="mail-list">
 	<table class="table" >
 		<thead>
 			<tr>
 				<th width=3%><input type="checkbox" name="selectAll" style="margin-left: 10px;"/></th>
 				<th width=3%>중요</th>
 				<th width=3%>첨부<br>파일</th>
 				<th width=13%>보낸사람</th>
 				<th width=70%>제목</th>
 				<th width=20%>수신일시</th>
 			</tr>
 		</thead>
 		<tbody>
		<c:if test="${not empty requestScope.emailList}"> 		
	 		<c:forEach var="evo" items="${requestScope.emailList}" varStatus="status">
	 			<tr>
	 				<td><input type="checkbox" name="selectThis" style="margin-left: 10px;"/></td>
	 				<td >
	 					<c:if test="${evo.checkImportant eq '0'}">
	 						<i class="far fa-star"></i>
	 					</c:if>
	 					<c:if test="${evo.checkImportant eq '1'}">
	 						<i class="fas fa-star"></i>
	 					</c:if>
	 				</td>
	 				<td>
	 					<c:if test="${not empty evo.fileName}">
	 						<i class="fas fa-paperclip"></i>
	 					</c:if>
	 				</td>
	 				<td>${evo.senderName}&lt;${evo.senderEmail}&gt;</td>
	 				<td>
	 				<input type="hidden" name="seq" value="${evo.seq}" />
	 				<a href="javascript:goView('${evo.seq}')" class="anchor-style">${evo.subject}</a></td>
	 				<td>${evo.sendingDate}</td>
	 			</tr>
	 		</c:forEach>
 		</c:if>
 		<c:if test="${empty requestScope.emailList}">
 			<tr>
 				<td colspan="6" align="center"> 받은 메일이 없습니다. </td>
 			</tr>
 		</c:if>
 		</tbody>
 	</table>
 
 <form name="goViewFrm">
    	<input type="hidden" name="seq"/>
    	<input type="hidden" name="gobackURL" value="${requestScope.gobackURL}"/>
    	<input type="hidden" name="searchType" />
      	<input type="hidden" name="searchWord" />
 </form>
 
 
 
 
 
 </div>
