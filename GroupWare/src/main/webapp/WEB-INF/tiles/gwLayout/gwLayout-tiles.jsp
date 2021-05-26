<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%
   String ctxPath = request.getContextPath();
   //     /groupware
%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>그룹웨어</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <link rel="stylesheet" type="text/css" href="<%=ctxPath %>/resources/css/gwStyle.css" />
</head>
<body>
    <tiles:insertAttribute name="header" /> 
   	<tiles:insertAttribute name="sideinfo" />    
 
  	<%-- (디자인은 gwStyle.css에 있음) --%>
   <div id="gwContent">
       <tiles:insertAttribute name="content" />
   </div>
 
</body>
</html>
  