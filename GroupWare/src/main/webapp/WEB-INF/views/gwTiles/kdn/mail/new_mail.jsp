<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String ctxPath = request.getContextPath(); %>
<link rel="stylesheet" type="text/css" href="<%=ctxPath %>/resources/css/kdn/mail.css" />
<script src="<%=ctxPath%>/resources/js/kdn/mail.js"></script>

<div id="mail-header" style="width: 100%; height: 120px; padding: 20px;">
	 <h4 style="margin-bottom: 20px; font-weight: bold;">메일쓰기</h4>
	 <div id="left-header">
		 <button type="submit" id="btnSend">보내기</button>
		 <button type="button" id="btnCancel" onclick="javascript:history.back()">취소</button>
		 <input type="checkbox"/><span>보낸메일함에 저장</span>
	 </div>
</div>
<div id="mailForm-container" style="padding: 10px;">
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
            <th>받는사람<br><input type="checkbox" id="write-to-myself" />&nbsp;&nbsp;내게쓰기</th>
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
            <th>제목&nbsp;&nbsp;<input type="checkbox" id="starred" />&nbsp;&nbsp;중요</th>
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