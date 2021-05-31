<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String ctxPath= request.getContextPath(); %>    
    
<style type="text/css">
	
	div.outerBox{
		border: solid 0px blue;
		width: 30%;
		height: 400px;
		float:left;
		margin: 50px 0px 0px 60px;
		cursor: pointer;
	}
	
	div.outerBox:hover{opacity: 0.5;}
	
	div.outerBox img:hover{box-shadow:5px 10px 5px #333;}
	
	div.imgBox img{
		width: 100%;
		height: 300px;
		margin-bottom: 10px;
	}
	
	div.productTitle{
		border: solid 0px blue;
		text-align: center;
		margin-bottom: 10px;
		font-size: 13pt;
		color: #0071bd;
		font-weight: bold; 
	}
	
	div.productDate{
		border: solid 0px red;
		text-align: center;
	}
	
	span.productState{
		background-color: red;
		color:#fff;
		font-weight: bold;
		text-align: center;
		padding-top:8px;
		font-size: 15pt;
	}
	
	span.state{
		display: inline-block;
		width: 110px;
		height: 40px;
		position:relative;
		top: 48px;
		left: 295px;
	}
	
	span.blinkState {opacity:0;}
	
	div#productA{
		margin:50px 0px; 
		font-size:13pt;
	}
	
	div#productA a{
		text-decoration: none;
		color: #696969;
	}
	
</style>

<script type="text/javascript">

	$(document).ready(function(){
		
		// 마감임박인 예약상품에는 마감임박이라는 문구 추가해주기 
		$("div.productTitle").each(function(index,item){
			
			var remainCnt= Number($(item).prop('id'));
			if(remainCnt<=6){ // 예약가능 인원이 6명 이하인 경우 마감임박 상품
				$(item).prev().find("span.state").addClass("productState");
				$(item).prev().find("span.state").text("마감임박");
			}
			else{
				$(item).prev().find("span.state").removeClass("productState");
			}
		}); // end of $("div.productTitle").each(function(index,item){------------- 
		
		//  마감임박이라는 문구 깜박이 효과주기
		$("span.productState").mouseover(function(event){
			if($(this).css("opacity")=="1"){
				$(this).addClass("blinkState");	
			}
			else{
				$(this).removeClass("blinkState");
			}
		});
		
		var blink= setInterval(function(){
			$('span.productState').trigger('mouseover',350); //이벤트 자동 발생
	  		},350);
		
	}); // end of $(document).ready(function(){---------------


	// === function declaration === 
	function goProductDetail(pNo){
		
		var goBackUrl= '${requestScope.goBackURL}';
		location.href="<%=ctxPath%>/t1/travelDetail.tw?pNo="+pNo+"&goBackUrl="+goBackUrl;
	}// end of function goProductDetail(pNo){
		
</script>

<div id="content">
	<c:if test="${not empty pvoList}">
		<c:forEach var="pvo" items="${pvoList}" varStatus="status">
			<c:if test="${status.count%3 eq 1}">
				<div class="outerBox" style="margin-left:0px;" onclick="goProductDetail('${pvo.pNo}')">
					<div class="imgBox">
						<span class="state"></span>
						<img src="<%=ctxPath%>/resources/images/productHsy/${pvo.pImage}" width="100" height="100" />
					</div>
					<div class="productTitle" id="${pvo.remainCnt}">${pvo.pName}</div>
					<div class="productDate">
						${pvo.startDate}&nbsp;-&nbsp;${pvo.endDate}<br>
						[${pvo.period}]
					</div>
				</div>
			</c:if>
			
			<c:if test="${status.count%3 ne 1}">
				<div class="outerBox" onclick="goProductDetail(${pvo.pNo})">
					<div class="imgBox">
						<span class="state"></span>
						<img src="<%=ctxPath%>/resources/images/productHsy/${pvo.pImage}" width="100" height="100" />
					</div>
					<div class="productTitle" id="${pvo.remainCnt}">${pvo.pName}</div>
					<div class="productDate">
						${pvo.startDate}&nbsp;-&nbsp;${pvo.endDate}<br>
						[${pvo.period}]
					</div>
				</div>
			</c:if>	
		</c:forEach>
	</c:if>
	<c:if test="${empty pvoList}">
		<div align="center" style="margin: 50px 0px 0px 60px;">상품 준비 중 입니다!!</div>
	</c:if>
	
	<!-- float처리 제거를 위함 -->
	<div style="clear:both; margin-bottom:100px;"></div>
	
	<div align="center" id="productA">${pageBar}</div>
	
	
</div>