<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<% String ctxPath = request.getContextPath(); %>    
<link rel="stylesheet" type="text/css" href="<%=ctxPath %>/resources/css/kdn/board.css" />

<script type="text/javascript">
$(document).ready(function(){

	
      // === #167. 스마트 에디터 구현 시작 ===
       
       // === 스마트 에디터 구현 끝 ===
      
      // 쓰기버튼
      $("button#btnWrite").click(function(){
      
         // === 스마트 에디터 구현 시작 ===
          
         // === 스마트 에디터 구현 끝 ===
         
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
        
         
         // === 스마트에디터 구현 시작 ===
         //스마트에디터 사용시 무의미하게 생기는 p태그 제거
          
           
           // 스마트에디터 사용시 무의미하게 생기는 p태그 제거하기전에 먼저 유효성 검사를 하도록 한다.
           // 글내용 유효성 검사 
           
           
           // 스마트에디터 사용시 무의미하게 생기는 p태그 제거하기
           
        
       // === 스마트에디터 구현 끝 ===
         
         // 글암호 유효성 검사
         var pwVal = $("input#pw").val().trim();
         if(pwVal == "") {
            alert("비밀번호를 입력하세요");
            return;
         }
         
         
         
         // 폼(form) 을 전송(submit)
         var frm = document.postFrm;
         frm.method = "POST";
         frm.action = "genUploadComplete.tw";
         frm.submit();   
      });
      
      

 });// end of $(document).ready(function(){})----------------

</script>



<div id="board-container">
	 <a href="javascript:location.href='generalBoard.tw'" style="text-decoration:none; color: black;"><i class="far fa-comments fa-lg"></i>&nbsp;&nbsp;<span style="display: inline-block; font-size:22px;">자유게시판</span></a>
	
	 <form name="postFrm" enctype="multipart/form-data"><!-- 파일첨부가 있는 글쓰기 -->  
	    <table id="table" class="table" style="margin-top: 50px;">
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
	             <input type="text" name="subject" id="subject" style="width: 100%;" />       
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
	          </td>
	       </tr>
	    </table>
	      
	    <div id="btn-container" class="float-right">
	       <button type="button" id="btnWrite" class="btn-style"><span style="color: #ffffff;">등록</span></button>
	       <button type="button" class="btn-style" onclick="javascript:history.back()"><span style="color: #ffffff;">취소</span></button>
	    </div>
	 </form>
</div>   