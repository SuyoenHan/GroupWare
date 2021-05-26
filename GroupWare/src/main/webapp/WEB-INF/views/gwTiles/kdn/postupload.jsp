<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String ctxPath = request.getContextPath(); %>    
<link rel="stylesheet" type="text/css" href="<%=ctxPath %>/resources/css/kdn/board.css" />
<script src="<%=ctxPath%>/resources/js/kdn/board.js"></script>

<div style="padding-left: 10%;">
 <h1>글쓰기</h1>

 <form name="postFrm"> 
 
      <table id="table">
         <tr>
            <th>성명</th>
            <td>
                <input type="hidden" name="fk_userid" value="${sessionScope.loginuser.employeeid}" />
                <input type="text" name="name" class="short" value="${sessionScope.loginuser.name}" readonly />
            </td>
         </tr>
         <tr>
            <th>제목</th>
            <td>
               <input type="text" name="subject" id="subject" class="long" />       
            </td>
         </tr>
         <tr>
            <th>내용</th>
            <td>
               <textarea rows="10" cols="100" style="width: 95%; height: 612px;" name="content" id="content"></textarea>       
            </td>
         </tr>
         
         <%-- === #150. 파일첨부 타입 추가하기 === --%>
         
         
         <tr>
            <th>글암호</th>
            <td>
               <input type="password" name="pw" id="pw" class="short" />       
            </td>
         </tr>
      </table>
      
      <div style="margin: 20px;">
         <button type="button" id="btnWrite">쓰기</button>
         <button type="button" onclick="javascript:history.back()">취소</button>
      </div>
      
      
         
   </form>
   
</div>   