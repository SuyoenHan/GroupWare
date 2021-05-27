<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String ctxPath= request.getContextPath(); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style type="text/css">

	div#gwContent{left: 80px;}
	
	div#tabMenu span{
		background-color: #0071bd;
		display: inline-block;
		width:150px;
		height: 40px;
		color:#fff;
		font-weight: bold;
		padding-top:13px;
		cursor: pointer;
		margin-left:10px;
		text-align: center;
	}
	
	div#tabMenu span:hover{
		box-shadow: 2px 2px 2px #333;
	}
	
	div#toDoBox{
		border: solid 1px blue;
		margin:0 auto;
		padding-left:10px;
		padding-right:10px;
		margin-top: 10px;
		margin-left: 10px;
		background-color:#f2f2f2;
		width:85%;
	}
	
	div.eachtoDoList{
		height:50px;
		border-bottom: solid 2px #cecece;
		padding-top: 17px;
	}

	div.ajaxEachList {
		height:70px;
		padding-top: 25px;
	}
	
	div#toDoBox span{
		border: solid 1px red;
		margin-left: 15px;
		display: inline-block;
		text-align: center;
	}
	
	div#toDoTitle span{
		text-align: center;
		font-weight: bold;
	}
	
	button.state{
		background-color: #737373;
		color: #fff;
		font-weight:bold;
		width:120px;
		height: 40px;
		margin: -10px 0px 0px 20px;
		padding-top:5px;
		border: none;
	}
	
	button.goWork:hover{
		box-shadow: 2px 2px 2px #333;
		background-color:#4d4d4d;
	}
	
	span.hurry{
		background-color: #ff3300;
		color: #fff;
		font-weight:bold;
	}
	
	select.ingDetail{
		width:120px;
		height: 25px;
		margin: -10px 0px 0px 15px;
	}
	
	select.delayState{
		background-color: #750099;
		color:#fff;
		font-weight: bold;
	}
	
</style>

<script type="text/javascript">

	$(document).ready(function(){
	   
		$("div#submenu7").show();
		
		// 선택된 업무유형에 따라 버튼색깔 다르게 표시하기=> "0":신규등록업무, "1":진행중업무, "2":진행완료업무
		if("${requiredState}"=="0"){
			$("span#newTodo").css("background-color","#003d66");
		}
		else if("${requiredState}"=="1"){
			$("span#ingTodo").css("background-color","#003d66");
			// 진행중업무의 경우 메뉴 1개가 더 추가되므로 너비 조절해주기
			$("div#toDoBox").css("width","95%");
			$("div#pageBar").css("width","92%");
		}
		else{
			$("span#doneTodo").css("background-color","#003d66");
			// 진행완료 업무인 경우 프로젝트명의 margin이 달리지므로 이에따라 다른 margin도 조절
			$("span#pname").css("margin-left","30px");
			$("div#toDoBox").css("width","83%");
			$("div#pageBar").css("width","80%");
		}

		
		// 페이지가 로드될때 진행중업무 상태에 맞는 상태값 유지시켜주기
		$("select.postpone").each(function(index,item){
			var state= $(this).prop("name");
			$(this).val(state);
		}); // end of $("select.postpone").each(function(index,item){
		
	
		// 지연상태인 경우 select태그 배경색 변경하기  => 페이지가 로드될때 적용
		$("select.delay").each(function(index,item){
			if($(item).val()=="1"){
				$(item).addClass("delayState");
			}
		}); // end of $("select.delay").each(function(index,item){
			
			
		// 진행중인 경우 상태변경(진행중 또는 보류)에 따라 update하기
		$("select.postpone").change(function(){
			var fk_pNo= $(this).prop('id');	
			var ingDetail= $(this).val();
			
			if($(this).val()!="2"){  // 진행완료를 클릭한 경우는 제외
				$.ajax({
					url:"<%=ctxPath%>/t1/updateIngDetail.tw",
					type:"POST",
					data:{"fk_pNo":fk_pNo,"ingDetail":ingDetail},
					dataType: "JSON",
					success: function(json){
						if(json.n==1){ // update 성공한 경우
							alert("상태가 변경되었습니다.");
						}
					},
					error: function(request, status, error){
			            alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
			        }
				}); // end of $.ajax({--------------
			}
		}); // end of $("select.ingDetail").change(function(){--------- 	
		
			
		// 진행완료버튼 클릭시 진행중업무에서 진행완료업무로 이동시키기
		$("select.ingDetail").change(function(){
			
			if($(this).val()=="2"){  // 진행완료를 클릭한 경우
				var productName= $(this).parent().find("span.productName").text();
				var fk_pNo= $(this).prop('id');	
				var fullState= $(this).parent().find("span.fullState").prop("id");
				
				if(fullState=="0"){
					alert(productName+" 상품은 예약인원이 부족하여 진행완료 처리가 불가능합니다.");
					window.location.reload(true);
				}
				else{
					goWorkDone(fk_pNo,productName);
				}
			}
		})// end of $("select.ingDetail").change(function(){----------- 	
		
			
			
			
	}); // end of $(document).ready(function(){-----
	
		
		
	// ===== function declaration =====
		
	// requiredState에 따라 해당정보를 가져오기위해  동일 url로 보내는 메소드
	function getEmployeeTodo(requiredState){  // "0":신규등록업무, "1":진행중업무, "2":진행완료업무
		location.href="<%=ctxPath%>/t1/employeeTodo.tw?requiredState="+requiredState;
	} // end of function getEmployeeTodo(requiredState){-----
	
	
	// 진행시작버튼 클릭시 실행되는 함수 => 진행시작버튼 클릭시 진행중업무로 이동
	function goWorkStart(fk_pNo,pName){
		var bool= confirm('"'+pName+'" 에 대해서 업무를 시작하시겠습니까?');
		if(bool){  // 업무시작처리
			$.ajax({
				url:"<%=ctxPath%>/t1/goWorkStart.tw",
				type:"POST",
				data:{"fk_pNo":fk_pNo},
				dataType: "JSON",
				success: function(json){
					if(json.n==1){ // 업무시작에 성공한 경우
						alert("업무 시작처리 되었습니다. [진행중 업무]에서 확인 가능합니다.")
						window.location.reload(true);
					}
					else{ // 업무시작에 실패한 경우
						alert("시스템 오류로 업무시작에 실패했습니다. 관리자에게 문의바랍니다.");
					}
				},
				error: function(request, status, error){
		            alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
		        }
			}); // end of $.ajax({-------
		}
	} // end of function goWorkStart(fk_pNo,pName){-----
	
		
	// 진행완료버튼 클릭시 실행되는 함수 => 진행중업무에서 진행완료업무로 이동
	function goWorkDone(fk_pNo,productName){
		var bool= confirm('"'+productName+'" 업무를 완료처리 하시겠습니까?');
		if(bool){  // 업무완료처리
			$.ajax({
				url:"<%=ctxPath%>/t1/goWorkDone.tw",
				type:"POST",
				data:{"fk_pNo":fk_pNo},
				dataType: "JSON",
				success: function(json){
					if(json.n==1){ // 업무시작에 성공한 경우
						alert("업무가 완료처리 되었습니다. [진행완료 업무]에서 확인 가능합니다.")
						window.location.reload(true);
					}
					else{ // 업무시작에 실패한 경우
						alert("시스템 오류로 업무완료에 실패했습니다. 관리자에게 문의바랍니다.");
					}
				},
				error: function(request, status, error){
		            alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
		        }
			}); // end of $.ajax({-------
		}
	} // end of function goWorkDone(fk_pNo,pName){--------
	
		
</script>


<div style="border: solid 1px green; width:90%; margin:0 auto; padding-left: 140px;">
	<div id="tabMenu" style="padding-top:30px;">
		<span id="newTodo" onclick="getEmployeeTodo('0')">신규등록업무&nbsp;(${newTodoCnt}건)</span>
		<span id="ingTodo" onclick="getEmployeeTodo('1')">진행중업무&nbsp;(${ingTodoCnt}건)</span>
		<span id="doneTodo" onclick="getEmployeeTodo('2')">진행완료업무&nbsp;(${doneTodoCnt}건)</span>
	</div>
	
	<div id="toDoBox">
		<div id="toDoTitle" class="eachtoDoList">
			<span style="margin-left:40px; width:50px;">순번</span>
			<span style="margin-left:58px; width:250px;" id="pname">프로젝트명</span>
			<span style="width:70px;">배정자</span>
			<span style="width:110px;">배정일</span>
			<span style="width:110px;">시작일</span>
			<c:if test="${requiredState ne '2'}">  <%-- 신규등록업무와 진행중 업무에만 존재 --%>
				<span style="width:110px;">기한일</span> 
			</c:if>
			<c:if test="${requiredState eq '1'}">  <%-- 진행중 업무에만 존재 --%>
				<span style="width:110px;">예약현황</span> 
			</c:if>
			<c:if test="${requiredState eq '2'}">  <%-- 진행완료 업무에만 존재 --%>
				<span style="width:110px;">완료일</span> 
			</c:if>
			<span style="width:120px;">상태</span>
		</div>

		<div id="toDoList">
			<c:if test="${not empty tvoList}">
				<c:forEach var="tvo" items="${tvoList}">
					<div class="eachtoDoList ajaxEachList">
						<span style="margin-left:40px; width:50px;">${tvo.rno}</span>
						
						<c:if test="${tvo.hurryno eq '1' and requiredState ne '2'}">
							<span class="hurry">&nbsp;긴급&nbsp;</span>
							<span style="width:250px; margin-left:2px; color:red;" class="productName">${tvo.pName}</span>
						</c:if>
						<c:if test="${tvo.hurryno eq '0' and requiredState ne '2'}">
							<span style="width:250px; margin-left:58px;" class="productName">${tvo.pName}</span>
						</c:if>
						<c:if test="${requiredState eq '2'}">
							<span style="width:250px; margin-left:30px;" class="productName">${tvo.pName}</span>
						</c:if>
						
						<span style="width:70px;">${tvo.name}</span>
						<span style="width:110px;">${tvo.assignDate}</span>
						<span style="width:110px;">${tvo.startDate}</span>
						
						<c:if test="${requiredState ne '2'}">
							<span style="width:110px;">${tvo.dueDate}</span>  <%-- 신규등록업무와 진행중 업무에만 존재 --%>
						</c:if>
						
						<c:if test="${requiredState eq '1'}">  <%-- 진행중 업무에만 존재 --%>
							<c:if test="${tvo.fullState eq'0'}"> <%-- 예약인원 미충족 --%>
								<span style="width:110px;" class="fullState" id="${tvo.fullState}">${tvo.nowNo}명&nbsp;/&nbsp;${tvo.maxNo}명</span> 
							</c:if>
							<c:if test="${tvo.fullState eq'1'}"> <%-- 최소예약인원 충족 --%>
								<span style="width:110px; color:blue;" class="fullState" id="${tvo.fullState}">${tvo.nowNo}명&nbsp;/&nbsp;${tvo.maxNo}명</span> 
							</c:if>
							<c:if test="${tvo.fullState eq'2'}"> <%-- 최대예약인원 충족 --%>
								<span style="width:110px; color:red;" class="fullState" id="${tvo.fullState}">${tvo.nowNo}명&nbsp;/&nbsp;${tvo.maxNo}명</span> 
							</c:if>
						</c:if>
						
						<c:if test="${requiredState eq '2'}">  <%-- 진행완료 업무에만 존재 --%>
							<span style="width:110px;">${tvo.endDate}</span> 
						</c:if>
						
						<c:if test="${requiredState eq '0'}">
							<button type="button" class="state goWork" onclick="goWorkStart('${tvo.fk_pNo}','${tvo.pName}')">진행시작하기</button> <%-- 신규등록 업무에만 존재 --%>
						</c:if>
						<c:if test="${requiredState eq '1'}"> <%-- 진행중 업무에만 존재 --%>
						  	<c:if test="${tvo.ingDetail eq '0' or tvo.ingDetail eq '-1' }"> <%-- 지연된 업무가 아닌 경우 --%>
								<select class="ingDetail postpone" id="${tvo.fk_pNo}" name="${tvo.ingDetail}">
									<option value="0">&nbsp;진행중</option> 
									<option value="-1">&nbsp;보류</option>
									<option value="2">&nbsp;진행완료</option>
								</select>
							</c:if>
							<c:if test="${tvo.ingDetail ne '0' and tvo.ingDetail ne '-1' }"> <%-- 지연된 업무인 경우 --%>
								<select class="ingDetail delay" id="${tvo.fk_pNo}">
									<option value="1" style="background-color: #730099; color: #fff; font-weight: bold;">&nbsp;지연 &nbsp;(&nbsp;+${tvo.ingDetail}일&nbsp;)</option> 
									<option value="2" style="background-color: #fff; color: black; font-weight: normal;">&nbsp;진행완료</option>
								</select>
							</c:if>
						</c:if>
						
						<c:if test="${requiredState eq '2'}">
							<button type="button" class="state" style="cursor:context-menu;">진행완료</button> <%-- 진행완료 업무에만 존재 --%>
						</c:if>
					</div>
				</c:forEach>
			</c:if>
			
			<c:if test="${empty tvoList}">
				<div class="eachtoDoList ajaxEachList" align="center" style="font-weight: bold;">업무내역이 없습니다.</div>
			</c:if>
		</div>
	</div>
	<div align="right" style="margin: 30px 0px; width:82%; font-size:14pt;" id="pageBar">${pageBar}</div>
</div>