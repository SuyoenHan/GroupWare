<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<% String ctxPath = request.getContextPath(); %>

<style>
div#containerview{
	margin: 30px 0px 30px 50px;
	width: 80%;
}
div.section table, div.section th, div.section td{
	border: solid 1px #ccc;
	border-collapse: collapse;
}

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
input.btn {
	width: 70px;
	border-radius: 0;
	font-weight: bold;
}
</style>

<script type="text/javascript">
	$(document).ready(function(){
		
		// 오늘 날짜
		todayIs();
		
	});
	
	// Function Declaration	
	// 오늘 날짜 찍기
	function todayIs(){
		var today = new Date();
		var dd = today.getDate();
		var mm = today.getMonth()+1;
		var yyyy = today.getFullYear();
		
		if(dd < 10){
			dd = '0'+dd;
		}
		if(mm < 10){
			mm = '0'+mm;
		}
		
		today = yyyy + '년' + mm +'월' + dd + '일';
		$("span#date").html(today);
	}
	
	
	// 목록보기
	function goback(){
		var $myFrm= document.myFrm;
		$myFrm.method="POST";
		$myFrm.action="<%=ctxPath%>/t1/myDocuSpend_temp.tw";
		$myFrm.submit();
	}
	
	// 삭제
	function goRemove(){
		var bool = confirm("삭제하시겠습니까?");
		
		if(bool){
			var formData = $("form[name=approvalDocu]").serialize();
			
			$.ajax({
				url:"<%=ctxPath%>/t1/remove.tw",
				data:formData,
				type:"post",
				dataType:"json",
				success:function(json){		
					
					if(json.n == 1){					
						alert("삭제되었습니다");						
						location.href = "<%=ctxPath%>/t1/myDocuSpend_temp.tw";						
					}
					else{
						alert("삭제 실패했습니다");
					}					
				},
				error: function(request, status, error){
					alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
				}			
			});
		}
	}// end of function goRemove(){}--------------------
	
	
	// 저장
	function goSave(){
		var bool = confirm("저장하시겠습니까?");
		
		if(bool){
			
			var frm = document.docuFrm;
			frm.method = "POST";
			frm.action = "<%=ctxPath%>/t1/saveSpend.tw";
			frm.submit();
			
			alert("저장되었습니다");
		}
	}
	
	// 제출
	function goSubmit(){
		var bool = confirm("제출하시겠습니까?");
		
		if(bool){
			
			var frm = document.docuFrm;
			frm.method = "POST";
			frm.action = "<%=ctxPath%>/t1/submitSpend.tw";
			frm.submit();
			
			alert("제출되었습니다");
		}
	}	
	
	// 파일 삭제
	function removeFile(){
		var bool = confirm("파일을 삭제하시겠습니까?");
		
		if(bool){			
			
			$.ajax({
				url:"<%=ctxPath%>/t1/removeFile.tw",
				data:{"ano":"${requestScope.avo.ano}"},
				type:"post",
				dataType:"json",
				success:function(json){		
					
					if(json.n == 1){
						alert("삭제되었습니다");
						
						location.reload();
					}
					else{
						alert("삭제 실패했습니다");
					}
				},
				error: function(request, status, error){
					alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
				}			
			});
		}
	}
	
</script>

<div id="containerview">	
	<h2 style="font-size: 20pt; font-weight: bold;" align="center">${requestScope.avo.scatname}</h2>
	<hr style="background-color: #395673; height: 1.5px; width: 80%;">
	<br>
	<div class="section">
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
		
		<form name="docuFrm" enctype="multipart/form-data">
		<input type="hidden" name="scatname" value="${requestScope.avo.scatname}"/>
		<table id="table2">
			<tr>
				<th>수신자</th>
				<td style="width: 35%;">${requestScope.mng.name} ${requestScope.mng.pname} (${requestScope.mng.dname})</td>
				<th>문서번호</th>
				<td>${requestScope.avo.ano}</td>
			</tr>
			
			<tr>
				<th>제목</th>
				<td colspan="3"><input type="text" style="width: 370px;" value="${requestScope.avo.atitle}"/></td>
			</tr>
			
			<c:if test="${requestScope.avo.scat eq '1'}">
				<tr>
					<th>지출일자</th>
					<td colspan="3"><input type="date" value="${requestScope.avo.exdate}"/></td>
				</tr>
				<tr>
					<th>지출금액</th>
					<td colspan="3"><input type="text" style="width: 370px;" value="${requestScope.avo.exprice}"/></td>
				</tr>
			</c:if>
			<c:if test="${requestScope.avo.scat eq '2'}">
				<tr>
					<th>사용예정일</th>
					<td colspan="3"><input type="date" value="${requestScope.avo.codate}"/></td>
				</tr>
				<tr>
					<th>카드번호</th>
					<td colspan="3"><input type="text" style="width: 370px;" value="${requestScope.avo.cocardnum}"/></td>
				</tr>
				<tr>
					<th>예상금액</th>
					<td><input type="text" style="width: 370px;" value="${requestScope.avo.coprice}"/></td>					
					<th>지출목적</th>
					<td>
						<select name="copurpose">
							<option>선택</option>
							<option value="1">교통비</option>
							<option value="2">사무비품</option>
							<option value="3">주유비</option>
							<option value="4">출장비</option>
							<option value="5">식비</option>
							<option value="6">기타</option>
						</select>
					</td>
				</tr>
			</c:if>
			
			<tr>
				<th style="height:250px;">글내용</th>
				<td colspan="3"><textarea name="acontent" rows="10" style="width: 100%;">${requestScope.avo.acontent}</textarea></td>
			</tr>
				
			<tr>
				<th>첨부파일</th>
				<td colspan="3"><input type="file" name="attach"/></td>
			</tr>
		</table>
		</form>
		<div align="center">상기와 같은 내용으로 <span style="font-weight: bold;">${requestScope.avo.scatname}</span> 을(를) 제출하오니 재가바랍니다.</div>
		<div align="right" style="margin: 4px 0; margin-right: 15%;">기안일: <span id="date"></span></div>
		<div align="right" style="margin-right: 15%;">신청자: ${requestScope.avo.dname} ${requestScope.avo.name} ${requestScope.avo.pname}</div>
		
		
		
		<div style="margin-top: 20px;">
			<span style="margin-left: 15%">
				<input type="button" class="btn" onclick="goback();" value="목록"/>
			</span>
			<span style="margin-left: 50%;">				
				<input type="button" class="btn btn-danger" onclick="goRemove();" value="삭제"/>
				<input type="button" class="btn btn-warning" onclick="goSave();" value="저장"/>
				<input type="button" class="btn btn-primary" onclick="goSumit();" value="제출"/>
			</span>
		</div>
		
		<br><br>
	</div>
	
	<form name="approvalDocu">
		<input type="hidden" name="ano" value="${ano}"/>
		<input type="hidden" name="astatus" value="${requestScope.avo.astatus}"/>
		<input type="hidden" name="arecipient1" value="${requestScope.avo.arecipient1}"/>
		<input type="hidden" name="arecipient2" value="${requestScope.avo.arecipient2}"/>
		<input type="hidden" name="arecipient3" value="${requestScope.avo.arecipient3}"/>
		<input type="hidden" name="employeeid" value="${sessionScope.loginuser.employeeid}" />
		<input type=hidden name="fk_pcode" value="${sessionScope.loginuser.fk_pcode}" />		
	</form>
	
	
	<form name="myFrm">
		<input type="hidden" name="ano" value="${ano}" />
		<input type="hidden" name="scatname" value="${scatname}" />		
		<input type="hidden" name="scat" value="${scat}" />
		<input type="hidden" name="sort" value="${sort}" />
		<input type="hidden" name="searchWord" value="${searchWord}" />
		<input type="hidden" name="currentShowPageNo" value="${currentShowPageNo}" />
	</form>
</div>