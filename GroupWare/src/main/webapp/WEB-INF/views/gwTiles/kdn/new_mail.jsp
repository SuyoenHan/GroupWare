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

<style type="text/css">

   input, textarea {border: solid #ccc 1px;}
   
   #table {border-collapse: collapse;
           width: 1024px;
          }
   #table th, #table td{padding: 5px;}
   #table th{width: 120px;}
   #table td{width: 90%;}
   
   

</style>

<script type="text/javascript">

	$(document).ready(function(){
		$("div#submenu1").show();
		
		<%-- === #167. 스마트 에디터 구현 시작 === --%>
	       
	       <%-- === 스마트 에디터 구현 끝 === --%>
	      
	      // 쓰기버튼
	      $("button#btnWrite").click(function(){
	      
	         <%-- === 스마트 에디터 구현 시작 === --%>
	          
	         <%-- === 스마트 에디터 구현 끝 === --%>
	         
	          // 글제목 유효성 검사
	         var subjectVal = $("input#subject").val().trim();
	         if(subjectVal == "") {
	            alert("글제목을 입력하세요!!");
	            return;
	         }
	         
	         // 글내용 유효성 검사(스마트에디터 사용 안 할시)
	         
	         var contentVal = $("textarea#content").val().trim();
	         if(contentVal == "") {
	            alert("글내용을 입력하세요!!");
	            return;
	         }
	        
	         
	         <%-- === 스마트에디터 구현 시작 === --%>
	         //스마트에디터 사용시 무의미하게 생기는 p태그 제거
	          
	              
	           
	           
	           // 스마트에디터 사용시 무의미하게 생기는 p태그 제거하기전에 먼저 유효성 검사를 하도록 한다.
	           // 글내용 유효성 검사 
	           
	           
	           // 스마트에디터 사용시 무의미하게 생기는 p태그 제거하기
	           
	       
	          
	        
	        
	       <%-- === 스마트에디터 구현 끝 === --%>
	         
	         // 글암호 유효성 검사
	         var pwVal = $("input#pw").val().trim();
	         if(pwVal == "") {
	            alert("글암호를 입력하세요!!");
	            return;
	         }
	         
	         // 폼(form) 을 전송(submit)
	         var frm = document.addFrm;
	         frm.method = "POST";
	         frm.action = "<%= ctxPath%>/addEnd.action";
	         frm.submit();   
	      });
		
	}); // end of $(document).ready(function(){
</script>

<div id="mail-header" style="background-color: #e6f2ff; width: 100%; height: 120px; padding: 20px;">
	 <h4 style="margin-bottom: 20px; font-weight: bold;">메일쓰기</h4>
	 <div id="left-header">
		 <button type="submit" id="btnSend">보내기</button>
		 <button type="button" id="btnCancel" onclick="javascript:history.back()">취소</button>
		 <input type="checkbox"/><span>보낸메일함에 저장</span>
	 </div>
</div>
 <form name="newMailFrm"> 
 
      <table id="table">
         <tr>
            <th>보내는사람</th>
            <td>
                <span>${loginuser.email}</span><input type="hidden" name="fk_userid" value="${loginuser.employeeid}" />
                <%-- <input type="text" name="name" value="${sessionScope.loginuser.name}" class="short" readonly /> --%>       
            </td>
         </tr>
         <tr>
            <th>받는사람</th>
            <td>
               <input type="text" name="receiver" id="receiver" style="width:90%; margin-right: 10px;"/><button type="button" id="contact">주소록</button> 
                     
            </td>
         </tr>
         <tr>
            <th>참조</th>
            <td>
               <input type="text" name="carbon-copy" id="carbon-copy" style="width:95%" />       
            </td>
         </tr>
         <tr>
            <th>제목</th>
            <td>
               <input type="text" name="subject" id="subject" style="width:95%"/>       
            </td>
         </tr>
         <%-- === #150. 파일첨부 타입 추가하기 === --%>
         <tr>
            <th>내용</th>
            <td>
               <textarea rows="10" cols="100" style="width: 95%; height: 612px;" name="content" id="content"></textarea>       
            </td>
         </tr>
         
         
         
      </table>
      
         
   </form>
   
</div>  