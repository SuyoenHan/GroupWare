<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String ctxPath = request.getContextPath();
%>
<script type="text/javascript">

	$(document).ready(function(){
		
		// sidemenu와 content길이 맞추기
		func_height1();
		
		// sebumenu와 content길이 맞추기
		func_height2();
		
		// div#sebuInfo2 감추기
		$("div#sebuInfo2").hide();
		
		$("div#sebumenu2").click(function(){
			$("div#sebuInfo2").show();
			$("div#sebuInfo1").hide();
		})
		
		$("div#sebumenu1").click(function(){
			$("div#sebuInfo1").show();
			$("div#sebuInfo2").hide();
		})
		
		
		
	}); // end of $(document).ready(function(){

</script>


<div id="sebumenu">
	<div id="sebumenu1">인사관리</div>
	<div id="sebumenu2">신입사원등록</div>
</div>


<div id="content">
	<div id="sebuInfo1">
		zz
	
	
	</div>
	<div id="sebuInfo2">호호호</div>
</div>