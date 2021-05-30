<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String ctxPath= request.getContextPath(); %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style type="text/css">
	
	div#productDetailOuterBox{
		border: solid 0px red;
		width: 90%;
		margin: 0 auto;
		overflow: hidden;
		padding: 50px 0px 30px 0px;
	}

	div#productInfo div{
		padding-top: 20px;
	}

	span#reserveBt{
		display: inline-block;
		width: 200px; 	
		height: 40px;
		background-color: #5d5f5d;
		color: #fff;
		font-weight: bold;
		font-size: 15pt;
		cursor:pointer;
		text-align: center;
		padding-top:10px;
	}
	
	div#reserveInfo ul{
		list-style: none;
		border: solid 2px #8b8d8b;
		padding-left:0px;
	}
	
	div#reserveInfo li{
		border-bottom: solid 1px #8b8d8b;
		padding: 10px;
	}
	
	div#reserveInfo label{
		display: inline-block;
		padding-left: 20px;
		width: 120px;
		border-right: solid 1px #8b8d8b;
	}
	
	div#reserveInfo span{
		margin-left: 25px;
		border: solid 0px red;
	} 
	
	div#reserveInfo input{
		margin-left: 25px;
		width: 250px;
	}
	
	div#reserveInfo{
		width: 60%;
		margin: 30px 0px 20px 450px;
	}
	
	span.hideBt{
		display: inline-block;
		width: 100px; 	
		height: 25px;
		background-color: #5d5f5d;
		color: #fff;
		font-weight: bold;
		font-size: 12pt;
		cursor:pointer;
		text-align: center;
		padding-top:5px;
	}
	
	div#goList span{
		cursor: pointer;
		color: #9e9e9e;
	}
	
	div#goList span:hover{
		color: #333;
		font-weight: bolder;
	}
	
	span.productState{
		background-color: #0071bd;;
		color:#fff;
		font-weight: bold;
		font-size:15pt;
		display: inline-block;
		height: 28px;
		width: 100px;
		text-align: center;
		margin-left: 20px;
	}
</style>

<script type="text/javascript">
	$(document).ready(function(){
		
		// 예약창 숨기기
		$("div#reserveInfo").hide();
		
		// 예약하기 버튼 클릭시 예약정보 입력창 보이기
		$("span#reserveBt").click(function(){
			$("div#reserveInfo").slideDown(1000);
			$("input[name=clientname]").focus();
			
			// 결제가격에 기본으로 넣어주는 1명 가격
			$("span#totalPrice").text(pricePerOne.toLocaleString('en')+"원");
		}); // end of $("sapn#reserveBt").click(function(){----
			
		// 가격 콤마처리
		var pricePerOne= Number("${pvo.price}");
		$("div#pricePerOne").text("가격:  "+pricePerOne.toLocaleString('en')+"원");
		
		// 인원수 선택시 직접 입력한 경우 유효성 검사 (blur 이벤트)
		$("input[name=cnumber]").blur(function(){
			 
			 var cnt= $(this).val();
			 cnt= parseInt(cnt);
			 
			 var regExp= /^[0-9]+$/; // 숫자만 체크하는 정규표현식
		   	 var bool= regExp.test(cnt);
		   	
	   		if(!bool){ // 문자로 입력한 경우
		   		alert("인원수를 제대로 입력해주세요.");
		   	    $(this).val("1")
		        $(this).focus();
		   	    
		   		// 결제가격에 기본으로 넣어주는 1명 가격
				$("span#totalPrice").text(pricePerOne.toLocaleString('en')+"원");
		        return; 
	   		}
	   		
	        if(cnt < 1 || cnt > 300) {
	           alert("인원수는 최소 1명 이상 300명 이하만 선택 가능합니다.");
	           $(this).val("1")
		       $(this).focus();
	           
	           // 결제가격에 기본으로 넣어주는 1명 가격
			   $("span#totalPrice").text(pricePerOne.toLocaleString('en')+"원");
		       return;
	        }
		 }); // $("input[name=cnumber]").blur(function(){
		
			 
		// 인원수 선택시 직접 입력한 경우 유효성 검사 (keyDown 이벤트)
		$("input[name=cnumber]").keydown(function(event){
			 
			if(event.keyCode==13){
				 var cnt= $(this).val();
				 cnt= parseInt(cnt);
				 
				 var regExp= /^[0-9]+$/; // 숫자만 체크하는 정규표현식
			   	 var bool= regExp.test(cnt);
			   	
		   		if(!bool){ // 문자로 입력한 경우
			   		alert("인원수를 제대로 입력해주세요.");
			   	    $(this).val("1")
			        $(this).focus();
			   	    
			   		// 결제가격에 기본으로 넣어주는 1명 가격
					$("span#totalPrice").text(pricePerOne.toLocaleString('en')+"원");
			        return; 
		   		}
		   		
		        if(cnt < 1 || cnt > 300) {
		           alert("인원수는 최소 1명 이상 300명 이하만 선택 가능합니다.");
		           $(this).val("1")
			       $(this).focus();
		           
		           // 결제가격에 기본으로 넣어주는 1명 가격
				   $("span#totalPrice").text(pricePerOne.toLocaleString('en')+"원");
			       return;
		        }
			}
		 }); // $("input[name=cnumber]").keydown(function(event){ 
		 
		// 예약인원수에 따른 최종결제금액 보여주기 이벤트
		$("input[name=cnumber]").change(function(event){
			
			var cnumber= $(this).val().trim();
			totalPrice= cnumber * pricePerOne;
			$("span#totalPrice").text(totalPrice.toLocaleString('en')+"원");
		}); // end of $("input.reserve").keyUp(function(event){----- 
		
			
		// 여행인원수에 따라 상품상태 표시 (확정 등)
		if(Number('${pvo.nowNo}')>=Number('${pvo.miniNo}')){
			$("span#productState").addClass("productState");
			$("span#productState").text("여행확정");	
		}
			
	}); // end of $(document).ready(function(){--------------
	
		
	// === function declaration ===
	
	// 결제하기 버튼 클릭시 작동하는 메소드
	function goPayTravel(){
		
		var pricePerOne= Number("${pvo.price}");
		var cnumber= $("input[name=cnumber]").val().trim();
		totalPrice= cnumber * pricePerOne;
		
		var clientname= $("input[name=clientname]").val().trim();
		var clientmobile= $("input[name=clientmobile]").val().trim();
		var fk_pNo= $("input[name=fk_pNo]").val();
		
		// 1) 입력값 검사 => cnumber은 입력하지 않아도 1로 잡힌다
		if(clientname==""){
			alert("예약자명을 입력해주세요.");
			$("input[name=clientname]").focus();
			$("input[name=clientname]").val("");
			return;
		}
		
		if(clientmobile==""){
			alert("연락처를 입력해주세요.");
			$("input[name=clientmobile]").focus();
			$("input[name=clientmobile]").val("");
			return;
		}
		
		var nclientmobile= parseInt(clientmobile);
		var regExp= /^[0-9]{10}$/; // 숫자만 체크하는 정규표현식
	   	var bool= regExp.test(nclientmobile);
	   	
   		if(!bool){ // 연락처에 숫자 이외의 문자가 포함된 경우
	   		alert("연락처를 제대로 입력해주세요 (숫자만 입력)");
	   	    $("input[name=clientmobile]").val("");
	        $("input[name=clientmobile]").focus();
	        return; 
   		}
		
		// 2) 예약이 가능한 인원수 인지 검사하고 예약가능한 인원수라면 결제창으로 이동
		var formData= $("form[name=reserveInfo]").serialize();
		
		$.ajax({
			url:"<%=ctxPath%>/t1/checkClientAjax.tw",
			data:formData,
			type:"POST",
			dataType:"JSON",
			success:function(json){
				
				if(json.n==1){ // 예약가능한 인원수인 경우
					
					// 3) 해당제품에 특정고객의 예약이 존재하는지 확인
					$.ajax({
						url:"<%=ctxPath%>/t1/isExistClientAjax.tw",
						data:{"fk_pNo":fk_pNo,"clientmobile":clientmobile},
						type:"POST",
						dataType:"JSON",
						success:function(json){
							if(json.n==0){ // 예약현황이 없는 경우 => 결제진행
								 // 아임포트 결제 팝업창 띄우기
					             var url = "<%=ctxPath%>/t1/orderPayment.tw?amount="+totalPrice+"&buyer_name="+clientname
					            		   +"&buyer_tel="+clientmobile+"&fk_pNo="+fk_pNo;
					             window.open(url, "orderPayment","left=350px, top=100px, width=800px, height=570px");    		
							}
							else{ // 이미 예약현황이 존재하는 경우
								alert("해당 예약자명으로 예약된 상품입니다. \n"
									 +"확인 및 변경은 나의 예약현황을 이용해주시기 바랍니다.");
								$("input[name=clientname]").val("");
								$("input[name=clientmobile]").val("");
								$("input[name=cnumber]").val("1");
							}
						},
						error: function(request, status, error){
				        	alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
				        }
					}); // end of $.ajax({-------- 
				}
				else{ // 예약불가능한 인원수인 경우
					alert("해당 상품의 현재 예약가능한 인원은 "+json.count+"명 입니다.\n"
						 +"인원수를 변경해주세요.");
					$("input[name=cnumber]").val("1");
				}
			},
			error: function(request, status, error){
	        	alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
	        }
		}); // end of $.ajax({-------------- 
			
	} // end of function goPayTravel(){-----------------
	
		
	// 결제 성공시 실행되는 메소드
	function goPaymentEnd(){
	
		var formData= $("form[name=reserveInfo]").serialize();
		var clientname= $("input[name=clientname]").val().trim();
		var cnumber= $("input[name=cnumber]").val().trim();
		var pName= '${pvo.pName}';
		var clientmobile= $("input[name=clientmobile]").val().trim();
		
		var $frm= document.reserveInfo
		
		$.ajax({
			url:"<%=ctxPath%>/t1/insertUpdateClientAjax.tw",
			data:formData,
			type:"POST",
			dataType:"JSON",
			success:function(json){
				if(json.result==1){ // 트랜잭션처리 성공한 경우		
				
					// 예약고객에게 문자보내기
					$.ajax({
						url:"<%=ctxPath%>/t1/sendSms.tw",
						type:"POST",   // 받는사람의 번호와 문자 내용물
						data:{"mobile":clientmobile,"smsContent":"[연습용] 고객님의 여행 예약이 완료되었습니다 - T1Works"},
						dataType:"json",
						success:function(json){
							
							if(json.success_count==1){
								alert(clientname+"님의 "+ pName+" 예약이 완료되었습니다.\n"+
								      "예약내역이 문자로 전송되었습니다. 또한 "+
									  "상세내역은 나의예약현황 보기에서 확인 가능합니다.");
								$frm.submit();
							}
						},
						error: function(request, status, error){
				               alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
				        }
					}); // end of $.ajax({-----------------------
				}
				else{ // 트랜잭션처리 실패한 경우
					alert("시스템 오류로 예약에 실패했습니다. 관리자에게 문의바랍니다.");
					$("input[name=clientname]").val("");
					$("input[name=clientmobile]").val("");
					$("input[name=cnumber]").val("1");
				}
			},
			error: function(request, status, error){
	        	alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
	        }
		}); // end of $.ajax({-------------- 
		
	} // end of function goPaymentEnd(){-----------------
	
		
	// 목록으로 (goBackUrl)
	function goProductList(){
		
		var goBackURL= "${goBackUrl}";
		goBackURL= goBackURL.replace(/ /gi, "&"); // 모든 공백을 &로 바꾸기 
		
		location.href= "<%=ctxPath%>/"+goBackURL;
	}
	
	// 취소버튼 클릭한 경우 작동하는 함수
	function cancelReserveTravel(){
		$("input[name=clientname]").val("");
		$("input[name=clientmobile]").val("");
		$("input[name=cnumber]").val("1");
	}
	
</script>

<div id="content">
	
	<div id="productDetailOuterBox">
		<div style="float:left; padding-left: 130px;" >
			<img src="<%=ctxPath%>/resources/images/productHsy/${pvo.pImage}" width="350" height="350" />
		</div>
		<div style="float:left; margin-left:50px;" id="productInfo">
			<div style="color:#0071bd; font-weight: bold; font-size: 20pt; padding-top: 40px;">
				${pvo.pName}
				<span id="productState"></span>
			</div>
			<div style="font-size: 15pt;">기간:&nbsp;${pvo.startDate}&nbsp;-&nbsp;${pvo.endDate}&nbsp;[${pvo.period}]</div>
			<div style="font-size: 15pt;" id="pricePerOne"></div>
			<div style="font-size: 15pt;">예약현황:&nbsp;${pvo.nowNo}명&nbsp;/&nbsp;${pvo.maxNo}명</div>
			<div style="font-size: 10pt;">* 해당상품은 최소 ${pvo.miniNo}명인 경우 확정됩니다.</div>
			<div><span id="reserveBt">예약하기</span></div>
		</div>
	</div>
	
	<div id="reserveInfo">
		<form name="reserveInfo" method="post" action="<%=request.getContextPath()%>/t1/myreserve.tw">
			<ul>
				<li>
					<label>예약상품명</label>
					<span>${pvo.pName}</span>
				</li>
				<li>
					<label>여행기간</label>
					<span>${pvo.startDate}&nbsp;-&nbsp;${pvo.endDate}&nbsp;[${pvo.period}]</span>
				</li>
				<li>
					<label>예약자명</label>
					<input type="hidden" name="fk_pNo" value="${pvo.pNo}">
					<input type="text" class="reserve" name="clientname" placeholder="  예약자명을 입력해주세요" />
				</li>
				<li>
					<label>연락처</label>
					<input type="text" class="reserve" name="clientmobile" placeholder="  연락처를 입력해주세요" />
				</li>
				<li>
					<label>인원수</label>
					<input type="number" class="reserve" name="cnumber" value="1" min="1" max="300" style="width: 70px;" />
				</li>
				<li style="border:none;">
					<label>최종결제금액</label>
					<span id="totalPrice" style="color:red; display:inline-block; width: 200px;"></span>
					<span class="hideBt" style="margin-left: 100px; width: 170px;" onclick="javascript:goPayTravel();">결제하기</span>
					<span class="hideBt" style="margin-left: 10px;" onclick="javascript:cancelReserveTravel();">취소</span>
				</li>
			</ul>
		</form>
	</div>
	
	<div id="goList" style="margin: 50px 0px 40px 0px;" align="center"><span onclick="javascript:goProductList();">목록으로</span></div>
</div>