<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String ctxPath= request.getContextPath(); %>
<meta charset="UTF-8">

<style type="text/css">

	div#mainHomeTitle{
		float: left;
		width: 80%;
		border: solid 0px red;
		padding-top: 10px;
		text-align: center;
		margin-bottom: 50px;
	}
	
	div#mainHomeTitle span{
		font-style: italic; 
		font-weight: bold;
		cursor: pointer;
		font-size: 30pt;
		margin-left: 200px;
	}
	
	div#mainHomebt {
		float: right;
		width: 18%;
		padding-top: 10px;
	}
	
	div#mainHomebt span{
		cursor: pointer;
		color: #9e9e9e;
	}
	
	div#mainHomebt span:hover{
		color: #333;
		font-weight: bolder;
	}
	
	div#mainHomeTitle img{
		position: relative;
		top: 15px;
		left: 200px;
		cursor:pointer;
	}
	 
</style>

<script type="text/javascript">
	
	// function declaration
	function goTravelHome(){
		location.href="<%=ctxPath%>/t1/travelAgency.tw";
	}
	
	
</script>

	
<div id="mainHomeTitle">
	<img src="<%=ctxPath%>/resources/images/login/t1works_icon.png"  width="70px" height="70px" onclick="javascript:goTravelHome();"/>
	<span onclick="javascript:goTravelHome();">T1Works 여행사</span>
</div>

<div id="mainHomebt">
	<span>나의 예약현황</span>
	&nbsp;&nbsp;|&nbsp;&nbsp;
	<span>회사 위치</span>
</div>
