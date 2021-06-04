<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<% String ctxPath = request.getContextPath(); %>

<style>
div#containerview{
	margin: 30px 0px 30px 50px;
	width: 80%;
}
table, th, td {border: solid 1px gray;}

#table1 {
	float: right; 
	width: 300px; 
	border-collapse: collapse;
	margin-right: 200px;
	margin-bottom: 50px;
}

#table1 th, #table1 td{
	text-align: center;
}
#table1 th {
	background-color: #395673; 
	color: #ffffff;
}
#table2 th, #table3 th, #table4 th, #table5 th {
	width: 150px;
}

th{
	background-color: #ccd9e6;
	padding: 7px;
}
td{
	padding: 7px;
}

#table2 {
	width: 70%;
	margin: 50px auto;
}
#table3 {
	width: 70%;
	margin: 10px auto;
}
#table4 {
	width: 70%;
	margin: 10px auto;
}
#table5 {
	width: 70%;
	margin: 10px auto;
}
input.btn {
	width: 70px;
	border-radius: 0;
	font-weight: bold;
}

td.opinion{
	border: solid 1px white;
}
</style>

<script type="text/javascript">
	
	$(document).ready(function(){
	/* 	
		if(${requestScope.avo.astatus == 0}){
			$("span.img").hide();
		}
		 
		if(${requestScope.avo.astatus == 1 and sessionScope.loginuser.fk_pcode == 2}){
			$("span.img").hide();
			$("span#img_approval_1").show();
		}
	*/
		goViewOpinion();
		
	});
	
	
	// Function Declaration	
	// 의견보기
	function goViewOpinion(){
		$.ajax({
			url:"<%= ctxPath%>/t1/opinionList.tw",
			data:{"parentAno":"${requestScope.avo.ano}"},
			dataType:"json",
			success:function(json){				
								
				var html = "";
				
				if(json.length > 0){
					$.each(json, function(index, item){						
						html += "<div>▶"+item.dname+"&nbsp;<span style='font-weight: bold;'>"+item.name+"</span>&nbsp;"+item.pname+"&nbsp;&nbsp;["+item.odate+"]</div>";
						html += "<div>"+ item.ocontent +"</div>";
						html += "<hr style='margin: 2px;'>";
					});
				}
				else{
					html += "<div>의견이 존재하지 않습니다.</div>";
				}
				
				$("td#opinionDisplay").html(html);
				
			},
			error: function(request, status, error){
				alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
			}
		});
	}// end of function goViewOpinion(){}--------------------
	
	
	// 목록보기
	function goback(){
		var $myFrm= document.myFrm;
		$myFrm.method="POST";
		$myFrm.action="<%=ctxPath%>/t1/myDocuVacation_complete.tw";
		$myFrm.submit();
	}
	
	// 결재로그 보기
	function goViewApproval(){
		$.ajax({
			url:"<%= ctxPath%>/t1/approvalList.tw",
			data:{"parentAno":"${requestScope.avo.ano}"},
			dataType:"json",
			success:function(json){				
								
				var html = "";
				
				if(json.length > 0){
					$.each(json, function(index, item){						
						html += "<div>["+item.odate+"]&nbsp;"+item.dname+"&nbsp;<span style='font-weight: bold;'>"+item.name+"</span>&nbsp;"+item.pname+"&nbsp;&nbsp;<span style='color: red;'>"+item.astatus+"</span></div>";
					});
				}
				
				$("span#approvalDisplay").html(html);
				
			},
			error: function(request, status, error){
				alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
			}
		});
	}// end of function goViewOpinion(){}--------------------
	
</script>

<div id="containerview">		
	<h2 style="font-size: 20pt; font-weight: bold;" align="center">${requestScope.avo.vcatname}계</h2>
	<hr style="background-color: #395673; height: 1.5px; width: 80%;">
	<br>
	<div id="astatus">
		<table id="table1">		
			<tr>
				<th style="width:100px; height:40px;">대리</th>
				<th>부장</th>
				<th>사장</th>
			</tr>			
			<tr>
				<td id="img_approval_1" style="height:70px;">
				</td>
				
				<td id="img_approval_2" style="height:70px;">
					
				</td>
				<td id="img_approval_3" style="height:70px;">
					
				</td>
			</tr>
		</table>
		
		<table id="table2">
			<tr>
				<th>문서상태</th>
				<td style="width: 35%;">
					<c:if test="${requestScope.avo.astatus eq '0'}">제출</c:if>
					<c:if test="${requestScope.avo.astatus eq '1'}">결재진행중</c:if>
					<c:if test="${requestScope.avo.astatus eq '2'}">반려</c:if>
					<c:if test="${requestScope.avo.astatus eq '3'}">승인완료</c:if>					
				</td>
				<th>문서번호</th>
				<td>${requestScope.avo.ano}</td>
			</tr>
			<tr>
				<th>제목</th>
				<td colspan="3">${requestScope.avo.atitle}</td>
			</tr>
			
			<c:if test="${requestScope.avo.vno eq '1'}"> 
				<tr>
					<th>요청기간</th>
					<td colspan="3">${requestScope.avo.slstart} - ${requestScope.avo.slend}&nbsp;&nbsp;&nbsp;[사용일수: <span style="color: blue; font-weight: bold;">${requestScope.avo.sldates}</span>일]</td>
				</tr>				
			</c:if>
			<c:if test="${requestScope.avo.vno eq '2'}">
				<tr>
					<th>요청기간</th>
					<td colspan="3">${requestScope.avo.afdate}&nbsp;&nbsp;
					<span style="color: blue; font-weight: bold;"><c:if test="${requestScope.avo.afdan eq '1'}">오전</c:if><c:if test="${requestScope.avo.afdan eq '2'}">오후</c:if></span>반차
					</td>
				</tr>
			</c:if>
			<c:if test="${requestScope.avo.vno eq '3'}">
				<tr>
					<th>요청기간</th>
					<td colspan="3">${requestScope.avo.daystart} - ${requestScope.avo.dayend}&nbsp;&nbsp;&nbsp;[사용일수: <span style="color: blue; font-weight: bold;">${requestScope.avo.daydates}</span>일]</td>
				</tr>
			</c:if>
			<c:if test="${requestScope.avo.vno eq '4'}">
				<tr>
					<th>요청기간</th>
					<td colspan="3">${requestScope.avo.congstart} - ${requestScope.avo.congend}&nbsp;&nbsp;&nbsp;[사용일수: <span style="color: blue; font-weight: bold;">${requestScope.avo.congdates}</span>일]</td>
				</tr>
			</c:if>
			<c:if test="${requestScope.avo.vno eq '5'}">
				<tr>
					<th>출장기간</th>
					<td colspan="3">${requestScope.avo.bustart} - ${requestScope.avo.buend}</td>
				</tr>
				<tr>
					<th>출장지</th>
					<td>${requestScope.avo.buplace}</td>				
					<th>출장인원</th>
					<td>${requestScope.avo.bupeople}</td>
				</tr>
			</c:if>
			<c:if test="${requestScope.avo.vno eq '6'}">
				<tr>
					<th>요청시간</th>
					<td colspan="3">${requestScope.avo.ewdate}시간</td>
				</tr>
			</c:if>	
			
			<tr>
				<th style="height:250px;">글내용</th>
				<td colspan="3">${requestScope.avo.acontent}</td>
			</tr>
				
			<tr>
				<th>첨부파일</th>
				<td colspan="3">${requestScope.avo.fileName}</td>
			</tr>
		</table>
			
		<div align="center">상기와 같은 내용으로 <span style="font-weight: bold;">${requestScope.avo.vcatname}계</span> 을(를) 제출하오니 재가바랍니다.</div>
		<div align="right" style="margin: 4px 0; margin-right: 15%;">기안일: ${requestScope.avo.asdate}</div>
		<div align="right" style="margin-right: 15%;">신청자: ${requestScope.avo.dname} ${requestScope.avo.name} ${requestScope.avo.pname}</div>
		
		
		<table id="table3">
			<tr>
				<th>결재로그</th>
				<td>
					[${requestScope.avo.asdate}] ${requestScope.avo.dname} ${requestScope.avo.name} ${requestScope.avo.pname} <span style="color: red; font-weight: bold;">제출</span>
					<span id="approvalDisplay"></span>
				</td>
			</tr>
		</table>
		<table id="table4">
			<tr>
				<th>결재의견</th>
				<td id="opinionDisplay"></td>
			</tr>
		</table>	
		
		
		<div style="margin-top: 20px;">
			<span style="margin-left: 15%">
				<input type="button" class="btn" onclick="goback();" value="목록"/>
			</span>
		</div>
		
		<br><br>
	</div>
	
	
	<form name="myFrm">
		<input type="hidden" name="ano" value="${ano}" />
		<input type="hidden" name="vcatname" value="${vcatname}" />		
		<input type="hidden" name="astatus" value="${astatus}" />
		<input type="hidden" name="fromDate" value="${fromDate}" />
		<input type="hidden" name="toDate" value="${toDate}" />
		<input type="hidden" name="vno" value="${vno}" />
		<input type="hidden" name="sort" value="${sort}" />
		<input type="hidden" name="searchWord" value="${searchWord}" />
		<input type="hidden" name="currentShowPageNo" value="${currentShowPageNo}" />
	</form>
</div>