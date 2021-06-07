<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<% String ctxPath = request.getContextPath(); %>    
<link rel="stylesheet" type="text/css" href="<%=ctxPath %>/resources/css/kdn/board.css" />

<script type="text/javascript">
$(document).ready(function(){
	
	    var privatePost = $("input[name=privatePost]").val();
	    if(privatePost == 1){
	   		$("input#checkPrivate").prop("checked",true);
    	}
	    
	   /*  if(privatePost == null){
	    	$("input[name=privatePost]").val("0");
	    	$("input#checkPrivate").prop("checked",false);
	    } */
	
    /* if($(this).is(":checked")){
		 $("input[name=privatePost]").val("1");
	 } else {
		 $("input[name=privatePost]").val("0");
	 } */
	    
	$("input#checkPrivate").change(function(){
		 if($(this).is(":checked") == true){
			 $("input[name=privatePost]").val("1");
		 } else {
			 $("input[name=privatePost]").val("0");
		 }
	})
	
	
      // 쓰기버튼
      $("button#btnWrite").click(function(){
      
          // 글제목 유효성 검사
         var subjectVal = $("input#subject").val().trim();
         if(subjectVal == "") {
            alert("글제목을 입력하세요");
            return;
         }
         
         // 글내용 유효성 검사(스마트에디터 사용 안 할시)
         var contentVal = $("textarea#content").val().trim();
         if(contentVal == "") {
            alert("글내용을 입력하세요");
            return;
         }
        
         
         
         // 글암호 유효성 검사
         var pwVal = $("input#pw").val().trim();
         if(pwVal == "") {
            alert("비밀번호를 입력하세요");
            return;
         }
         
         
         // 폼(form) 을 전송(submit)
         var frm = document.postFrm;
         frm.method = "POST";
         frm.action = "suggUploadComplete.tw";
         frm.submit();   
      });
      
      

 });// end of $(document).ready(function(){})----------------

</script>



<div id="board-container">
	 <a href="javascript:location.href='suggestionBoard.tw'" style="text-decoration:none; color: black;"><i class="fas fa-exclamation fa-lg"></i>&nbsp;&nbsp;<span style="display: inline-block; font-size:22px;">건의사항</span></a>
	
	 <form name="postFrm" enctype="multipart/form-data"><!-- 파일첨부가 있는 글쓰기 --> 
	    <table id="table" class="table">
	       <tr>
	          <th>성명</th>
	          <td>
	              <input type="hidden" name="name" class="short" value="${sessionScope.loginuser.name}" readonly />
	              <input type="hidden" name="fk_employeeid" value="${sessionScope.loginuser.employeeid}" />
	              <span>${sessionScope.loginuser.name}</span>
	          </td>
	       </tr>
	       <tr>
	          <th>제목</th>
	          <td>
	          	<c:if test="${not empty requestScope.subject}">
	             	<input type="text" name="subject" id="subject" style="width: 100%;" value="&rdca;&nbsp;Re:&nbsp;${requestScope.subject}"/> 
	          	</c:if>
	          	<c:if test="${empty requestScope.subject}">
	             	<input type="text" name="subject" id="subject" style="width: 100%;" /> 
	             </c:if>      
	          </td>
	       </tr>
	       <tr>
	       	  <th>파일첨부</th>
	          <td>
	        	 <input type="file" name="attach" />
	          </td>
	       </tr>
	       <tr>
	          <th>내용</th>
	          <td>
	             <textarea rows="10" cols="100" style="width: 100%; height: 100%;" name="content" id="content"></textarea>       
	          </td>
	       </tr>
	       <tr>
	          <th>비밀번호</th>
	          <td>
	             <input type="password" name="pw" id="pw" class="short" />
		         <input type="hidden" name="privatePost" value="${requestScope.privatePost}"/>
		         <input type="checkbox" id="checkPrivate" />&nbsp;비밀글
	          </td>
	       </tr>
	    </table>
	      
	  <input type="hidden" name="parentSeq" value="${requestScope.parentSeq}"/>
	     <input type="hidden" name="groupno" value="${requestScope.groupno}"/>
	     <input type="hidden" name="depthno" value="${requestScope.depthno}"/>
	      
	      
	    <div id="btn-container" class="float-right">
	       <button type="button" id="btnWrite" class="btn-style"><span style="color: #ffffff;">등록</span></button>
	       <button type="button" class="btn-style" onclick="javascript:history.back()"><span style="color: #ffffff;">취소</span></button>
	    </div>
	 </form>
</div>   