<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<% String ctxPath = request.getContextPath(); %>

<style type="text/css">
   table, th, td {border: solid 1px gray;}

    #table {width: 970px; border-collapse: collapse;}
    #table th, #table td {padding: 5px;}
    #table th {background-color: #DDD;}
     
    .subjectStyle {font-weight: bold;
                   color: navy;
                   cursor: pointer;} 
</style>

<script type="text/javascript">
		$(document).ready(function(){
			
			
		});
</script>

<div style="padding-left: 3%;">

   <h2 style="margin-bottom: 30px;">일반결재내역</h2>
   
   
   
   <%-- === #101. 글검색 폼 추가하기 : 카테고리로 검색을 하도록 한다. === --%>
   <form name="searchFrm" style="margin-bottom: 10px;">
      <select name="searchType" id="searchType" style="height: 26px;">
         <option value="">카테고리</option>
         <option value="subject">회의록</option>
         <option value="name">외부공문</option>
         <option value="name">협조공문</option>
         <option value="name">위임장</option>
      </select>
      <input type="text" name="searchWord" id="searchWord" size="40" autocomplete="off" /> 
      <button type="button" onclick="goSearch()">검색</button>
   </form>
   
   <table id="table">
      <tr>
         <th style="width: 60px;  text-align: center;">NO.</th>
         <th style="width: 80px; text-align: center;">일반결재문서</th>
         <th style="width: 360px;  text-align: center;">제목</th>
         <th style="width: 150px; text-align: center;">작성자</th>
         <th style="width: 70px;  text-align: center;">날짜</th>
      </tr>
      
        <c:forEach var="evo" items="${requestScope.electronList}" varStatus="status">
         <tr>
         <td align="center">${status.index+1}</td>
         <td align="left">
              <span class="subject" onclick="goView('${evo.ano}')">${evo.gpvo.ncatname}</span>
          </td>
         <td align="center">${evo.atitle}</td>
         <td align="center">${evo.mvo.getName()}</td>
        
         <td align="center">${evo.asdate}</td>         
         </tr>      
      </c:forEach>  
   </table>    
              
     
   
   
   
</div>           
              
              