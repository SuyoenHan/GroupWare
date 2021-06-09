<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String ctxPath = request.getContextPath(); %>
<link rel="stylesheet" type="text/css" href="<%=ctxPath %>/resources/css/kdn/mail.css" />

<script type="text/javascript">
$(document).ready(function(){
	
	var arrEmailSeq = [];
	var str_arrEmailSeq = ""; 
	// 체크박스 전체선택/전체해제
	$("input#selectAll").change(function(){
		if($("input#selectAll").prop("checked")){
			$("input[name=thisEmail]").prop('checked',true);
			var arrThisEmail = document.getElementsByName("thisEmail");
			for(var i=0; i<arrThisEmail.length; i++){
				var thisEmailSeq = arrThisEmail[i].value;
				/* console.log("thisEmailSeq : "+thisEmailSeq); */
				/* console.log("배열 값유무 확인 : "+arrEmailSeq.indexOf(thisEmailSeq)); */
				if(arrEmailSeq.indexOf(thisEmailSeq) == -1){
					/* arrEmailSeq.splice(arrEmailSeq.indexOf(thisEmailSeq),1); */
					arrEmailSeq.push(thisEmailSeq);
				}
			}//end of for --------------------
				/* console.log("최종 배열 값 :"+arrEmailSeq); */
			
		} else {
			$("input[name=thisEmail]").prop('checked',false);
			arrEmailSeq = [];
			/* console.log(arrEmailSeq); */
		}
	});
	
	$("input[name=thisEmail]").change(function(){
		check_checkbox();
		var thisEmailSeq = $(this).val();
		console.log("개별선택시 thisEmailSeq"+thisEmailSeq);
		if($(this).prop("checked") == false){
			arrEmailSeq.splice(arrEmailSeq.indexOf(thisEmailSeq),1);
		}
		if($(this).prop("checked") == true){
			arrEmailSeq.push(thisEmailSeq);
		}
		console.log(arrEmailSeq);
	});
	
	$("button#delImmed").click(function(){
		
		str_arrEmailSeq = arrEmailSeq.toString();
		/* console.log("최종 배열 :"+str_arrEmailSeq); */
		
		location.href="<%=ctxPath%>/t1/delImmed.tw?mailBoxNo=2&str_arrEmailSeq="+str_arrEmailSeq;
		
	});
	
	
	
	
});

//체크박스 체크유무검사
function check_checkbox(){
	var arrChckbox = document.getElementsByName("thisEmail");
	var bFlag = false;
	
	for(var i=0; i<arrChckbox.length; i++){
		if(!arrChckbox[i].checked){
			bFlag = true;		
			break;
		}
	}//end of for --------------------
	
	if(bFlag){	//하위 체크박스 중 1개라도 체크가 해제된 경우
		document.getElementById("selectAll").checked = false;
	}
	else {	//하위 체크박스가 모두 체크된 경우
		document.getElementById("selectAll").checked = true;
	}
}// end of function func_chinaCheck()---------------------------------------

function goView(seq){
		console.log(seq);		
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
	 <h4 style="margin-bottom: 20px; font-weight: bold;">보낸메일함</h4>
	 <div id="left-header">
		 <button id="delImmed" type="button">삭제</button>
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
 				<th width=3%><input type="checkbox" name="selectAll" id="selectAll" style="margin-left: 10px;"/></th>
 				<th width=3%>첨부파일</th>
 				<th width=13%>받는사람</th>
 				<th width=67%>제목</th>
 				<th width=20%>발신일시</th>
 			</tr>
 		</thead>
 		<tbody>
 		<c:if test="${not empty requestScope.emailList}"> 		
	 		<c:forEach var="evo" items="${requestScope.emailList}" varStatus="status">
	 			<tr>
	 				<td><input type="checkbox" name="thisEmail" id="thisEmail" value="${evo.seq}" style="margin-left: 10px;"/></td>
	 				<td>
	 					<c:if test="${not empty evo.fileName}">
	 						<i class="fas fa-paperclip"></i>
	 					</c:if>
	 				</td>
	 				<td>${evo.receiverName}&lt;${evo.receiverEmail}&gt;</td>
	 				<td>
	 				<input type="hidden" name="seq" value="${evo.seq}" />
	 				<a href="javascript:goView('${evo.seq}')" class="anchor-style">${evo.subject}</a></td>
	 				<td>${evo.sendingDate}</td>
	 			</tr>
	 		</c:forEach>
 		</c:if>
 		<c:if test="${empty requestScope.emailList}">
 			<tr>
 				<td colspan="6" align="center"> 저장된 보낸메일이 없습니다. </td>
 			</tr>
 		</c:if>
 		</tbody>
 	</table>
 	
 	<%-- 페이지 바 보여주기 --%>
 	<div align="center" style="width: 70%; margin: 20px auto;">
     	${requestScope.pageBar}
     </div>
 	
 	
 	 <form name="goViewFrm">
 	 	<input type="hidden" name="mailBoxNo" value="2">
    	<input type="hidden" name="seq"/>
    	<input type="hidden" name="gobackURL" value="${requestScope.gobackURL}"/>
    	<input type="hidden" name="searchType" />
      	<input type="hidden" name="searchWord" />
 	</form>
 
 </div>
