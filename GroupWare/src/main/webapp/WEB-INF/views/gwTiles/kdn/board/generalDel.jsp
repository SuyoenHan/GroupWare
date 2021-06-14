<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String ctxPath = request.getContextPath(); %>
<link rel="stylesheet" type="text/css" href="<%=ctxPath%>/resources/css/kdn/board.css"/>    

<style type="text/css">

#table {border-collapse: collapse;
          width: 250px;
          }
#table th, #table td{padding: 5px;}

</style>
<script type="text/javascript">
   $(document).ready(function(){
      
	// 완료버튼
    $("button#btnDelete").click(function(){
       
       // 글암호 유효성 검사
       var pwVal = $("input#pw").val().trim();
       if(pwVal == "") {
          alert("비밀번호를 입력하세요");
          return;
       }
       
       // 폼(form)을 전송(submit)
       var frm = document.delFrm;
       frm.method = "POST";
       frm.action = "<%= ctxPath%>/t1/generalDelEnd.tw";
       frm.submit();
    });
	
    $("input#pw").keydown(function(){
		if(event.keyCode == 13){
			var frm = document.delFrm;
		       frm.method = "POST";
		       frm.action = "<%= ctxPath%>/t1/generalDelEnd.tw";
		       frm.submit();
		}
	});
            
   });// end of $(document).ready(function(){})----------------
   
</script>

<div>
  <form name="delFrm">
      <table id="table" style="display: block; margin: 15% auto 0 auto;">
         <tr>
            <td align="center">비밀번호를 입력하세요</td>
         </tr>
         <tr>
            <td align="center">
               <input type="password" name="pw" id="pw" class="short" /> 
               <input type="hidden" name="seq" value="${requestScope.seq}" />       
            </td>
         </tr>
         <tr>
         	<td align="center">
	         <button type="button" id="btnDelete" class="btn-style">완료</button>
	         <button type="button" onclick="javascript:history.back()" class="cmnt-btn-style">취소</button>
         	</td>
         </tr>
      </table>
   </form>
</div>