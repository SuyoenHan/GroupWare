<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<%
   String ctxPath = request.getContextPath();
%>

<style>
   img#logo{
      display: inline-block;
      height: 60px;
      position:absolute;
      cursor:pointer;
      
   }
   
   
   div#mainMenu {
      padding-left: 100px;
   }
   
   
</style>

<span id="menu1" onclick="location.href='<%= ctxPath%>/t1/home.tw'"> <img id="logo" src="<%=request.getContextPath()%>/resources/images/login/t1works_icon.png"></span>
<div id="mainMenu"> 
   <span>${loginuser.name}
      <c:if test="${loginuser.fk_pcode eq 1}">
         사원님
      </c:if>
      <c:if test="${loginuser.fk_pcode eq 2}">
         대리님
      </c:if>
      <c:if test="${loginuser.fk_pcode eq 3}">
         부장님
      </c:if>
      <c:if test="${loginuser.fk_pcode eq 4}">
         사장님
      </c:if>환영합니다.</span>
   <span id="menu2">홈</span>
   <span id="menu3">마이페이지</span>
   <span id="menu4">일정관리</span>
   <span id="menu5">예약현황</span>
   <span id="menu6">공지사항</span>
   <span id="menu7">검색</span>
   <span id="menu7" onclick="location.href='<%= ctxPath%>/t1/logout.tw'">
   	 <c:if test="${not empty loginuser}">
           로그아웃
     </c:if>
   </span>
   
</div>

<script type="text/javascript">
   

   //Function Declaration
   function func_height1(){ // sidemenu와 content길이 맞추기
      
       var content_height= $("div#content").height();
        $("div#sidemenu").height(content_height);
    }
   
   function func_height2(){ // sebumenu와 content길이 맞추기
      
       var content_height= $("div#content").height();
        $("div#sebumenu").height(content_height);
    }
</script>