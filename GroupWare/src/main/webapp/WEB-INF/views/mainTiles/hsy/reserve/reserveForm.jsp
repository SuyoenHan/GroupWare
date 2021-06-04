<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String ctxPath= request.getContextPath(); %>

<style type="text/css">
	
	div#myReserve{
		border: solid 0px red;
		width: 60%;
		margin: 0 auto;
		margin-bottom:50px;
		position:relative;
		left: -40px;
	}

	div#myReserveTitle{
		border: solid 0px red;
		font-size: 25pt;
		color: #0071bd;
		text-align: center;
		font-weight: bold;
		margin: 10px 0px 40px 0px;
	}

	div#reserveForm{
		border: solid 0px red;
		width: 60%;
		margin: 0 auto;
		text-align: center;
		margin-bottom: 30px;
		position:relative;
		left:10px;
	}
	
	div#reserveForm label{
		display: inline-block;
		font-size: 15pt;
		width: 100px;
		height: 30px;
		border-right: solid 2px #bbbbbe;
		margin-right: 10px;
		margin-bottom:20px;
		padding: 5px 0px;
		text-align: left;
	}
	
	div#reserveForm input.funcBt{
		width: 330px;
		height: 35px;
		cursor:pointer;
		background-color:  #0071bd; 
		color: #fff;
		font-weight: bold;
		font-size: 13pt;
	}
	
	div#reserveForm input.clientInfo{
		margin-left: 10px;
		width: 210px;
		height: 30px;
	}
	
</style>

<script type="text/javascript">

	$(document).ready(function(){
		
		$("input[name=clientname]").focus();
		
		// 예약현황 확인 버튼 클릭시 유효성 검사
		$("form[name=myReserveForm]").submit(function(){
			
			var clientname= $("input[name=clientname]").val().trim();
			var clientmobile=$("input[name=clientmobile]").val().trim();
			
			// 1) 입력값 검사
			if(clientname==""){
				alert("예약자명을 입력해주세요.");
				$("input[name=clientname]").focus();
				$("input[name=clientname]").val("");
				return false; // submit 취소
			}
			
			if(clientmobile==""){
				alert("연락처를 입력해주세요.");
				$("input[name=clientmobile]").focus();
				$("input[name=clientmobile]").val("");
				return false; // submit 취소
			}
			
			var nclientmobile= parseInt(clientmobile);
			var regExp= /^[0-9]{10}$/; // 숫자만 체크하는 정규표현식
		   	var bool= regExp.test(nclientmobile);
		   	
	   		if(!bool){ // 연락처에 숫자 이외의 문자가 포함된 경우
		   		alert("연락처를 제대로 입력해주세요 (숫자만 입력)");
		   	    $("input[name=clientmobile]").val("");
		        $("input[name=clientmobile]").focus();
		        return false; // submit 취소
	   		}
			
			// 2) 유효성검사를 통과한 경우 form 정보들을 동일 url에 post방식으로 전송
			var myFrm= document.myReserveForm;
			myFrm.submit();
			
		}); // end of $("form[name=myReserveForm]").submit(function(){-----
		
		
	}); // end of $(document).ready(function(){-----


</script>
<div id="content" style="clear: both;">
	<div id="myReserve">
		<div id="myReserveTitle" style="padding-top:50px;">예약현황 확인하기</div>
		<div id="reserveForm">
			<form name="myReserveForm" action="<%=ctxPath%>/t1/myreserve.tw" method="POST">
				<label>예약자명</label><input class="clientInfo" type="text" name="clientname" placeholder="예약자명을 입력하세요"/><br/>
				<label style="margin-bottom:30px;">연락처</label><input class="clientInfo" type="text" name="clientmobile" placeholder="연락처를 입력하세요"/><br/>
				<input class="funcBt" type="submit" value="예약현황 확인" />
			</form>
		</div>
	</div>
</div>

