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
	// 전역변수
	var obj = [];
	
	$(document).ready(function(){
		
		// 오늘 날짜
		todayIs();
		
		<%-- === #167. 스마트 에디터 구현 시작 === --%>
		// 스마트에디터 프레임생성
		nhn.husky.EZCreator.createInIFrame({
			oAppRef: obj,
			elPlaceHolder: "acontent",
			sSkinURI: "<%= request.getContextPath() %>/resources/smarteditor/SmartEditor2Skin.html",
			htParams : {
				// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
				bUseToolbar : true,            
				// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
				bUseVerticalResizer : true,    
				// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
				bUseModeChanger : true,
			}
		});
		<%-- === 스마트 에디터 구현 끝 === --%>	
		
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
		$myFrm.action="<%=ctxPath%>/t1/myDocuVacation_temp.tw";
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
						location.href = "<%=ctxPath%>/t1/myDocuVacation_temp.tw";						
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
		<%-- === 스마트 에디터 구현 시작 === --%>
		// id가 content인 textarea에 에디터에서 대입
		obj.getById["acontent"].exec("UPDATE_CONTENTS_FIELD", []);
		<%-- === 스마트 에디터 구현 끝 === --%>
		
		// 유효성 검사
		var subjectVal = $("input#atitle").val().trim();
		if(subjectVal == "") {
			alert("글제목을 입력하세요!!");
			return;
		}
		
		
		
		
		<%-- === 스마트 에디터 구현 시작 === --%>
		// 스마트에디터 사용시 무의미하게 생기는 p태그 제거
		var contentval = $("textarea#acontent").val();
		
		// 스마트에디터 사용시 무의미하게 생기는 p태그 제거하기
		contentval = $("textarea#acontent").val().replace(/<p><br><\/p>/gi, "<br>"); //<p><br></p> -> <br>로 변환
		
		contentval = contentval.replace(/<\/p><p>/gi, "<br>"); //</p><p> -> <br>로 변환  
		contentval = contentval.replace(/(<\/p><br>|<p><br>)/gi, "<br><br>"); //</p><br>, <p><br> -> <br><br>로 변환
		contentval = contentval.replace(/(<p>|<\/p>)/gi, ""); //<p> 또는 </p> 모두 제거시
		
		$("textarea#acontent").val(contentval);
		
		<%-- === 스마트 에디터 구현 끝 === --%>		
		
		var bool = confirm("저장하시겠습니까?");
		
		if(bool){
			
			var frm = document.docuFrm;
			frm.method = "POST";
			frm.action = "<%=ctxPath%>/t1/saveVacation.tw";
			frm.submit();
			
			alert("저장되었습니다");
		}
	}
	
	// 제출
	function goSubmit(){		
		<%-- === 스마트 에디터 구현 시작 === --%>
		// id가 content인 textarea에 에디터에서 대입
		obj.getById["acontent"].exec("UPDATE_CONTENTS_FIELD", []);
		
		// 스마트에디터 사용시 무의미하게 생기는 p태그 제거
		var contentval = $("textarea#acontent").val();
		
		// 스마트에디터 사용시 무의미하게 생기는 p태그 제거하기
		contentval = $("textarea#acontent").val().replace(/<p><br><\/p>/gi, "<br>"); //<p><br></p> -> <br>로 변환
		
		contentval = contentval.replace(/<\/p><p>/gi, "<br>"); //</p><p> -> <br>로 변환  
		contentval = contentval.replace(/(<\/p><br>|<p><br>)/gi, "<br><br>"); //</p><br>, <p><br> -> <br><br>로 변환
		contentval = contentval.replace(/(<p>|<\/p>)/gi, ""); //<p> 또는 </p> 모두 제거시
		
		$("textarea#acontent").val(contentval);
		
		<%-- === 스마트 에디터 구현 끝 === --%>
		
		var bool = confirm("제출하시겠습니까?");
		
		if(bool){
			
			var frm = document.docuFrm;
			frm.method = "POST";
			frm.action = "<%=ctxPath%>/t1/submitVacation.tw";
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
	<h2 style="font-size: 20pt; font-weight: bold;" align="center">${requestScope.avo.vcatname}계</h2>
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
		<input type="hidden" name="vcatname" value="${requestScope.avo.vcatname}"/>
			<table id="table2">
				<tr>
					<th>수신자</th>
					<td style="width: 35%;"></td>
					<th>문서번호</th>
					<td>${requestScope.avo.ano}</td>
				</tr>
				<tr>
					<th>제목</th>
					<td colspan="3"><input type="text" name="atitle" id="atitle" style="width: 370px;" value="${requestScope.avo.atitle}"/></td>
				</tr>
				
				<c:if test="${requestScope.avo.vno eq '1'}"> 
					<tr>
						<th>요청기간</th>
						<td colspan="3"><input type="date" name="slstart" id="slstart" value="${requestScope.avo.slstart}"/> - <input type="date" name="slend" id="slend" value="${requestScope.avo.slend}"/>&nbsp;&nbsp;&nbsp;[사용일수: <span style="color: blue; font-weight: bold;">${requestScope.avo.sldates}</span>일]</td>
					</tr>				
				</c:if>
				<c:if test="${requestScope.avo.vno eq '2'}">
					<tr>
						<th>요청기간</th>
						<td colspan="3"><input type="date" name="afdate" id="afdate" value="${requestScope.avo.afdate}">&nbsp;&nbsp;
						<span style="color: blue; font-weight: bold;"><c:if test="${requestScope.avo.afdan eq '1'}">오전</c:if><c:if test="${requestScope.avo.afdan eq '2'}">오후</c:if></span>반차
						</td>
					</tr>
				</c:if>
				<c:if test="${requestScope.avo.vno eq '3'}">
					<tr>
						<th>요청기간</th>
						<td colspan="3"><input type="date" name="daystart" id="daystart" value="${requestScope.avo.daystart}"/> - <input type="date" name="dayend" id="dayend" value="${requestScope.avo.dayend}"/>&nbsp;&nbsp;&nbsp;[사용일수: <span style="color: blue; font-weight: bold;">${requestScope.avo.daydates}</span>일]</td>
					</tr>
				</c:if>
				<c:if test="${requestScope.avo.vno eq '4'}">
					<tr>
						<th>요청기간</th>
						<td colspan="3"><input type="date" name="congstart" id="congstart" value="${requestScope.avo.congstart}"/> - <input type="date" name="congend" id="congend" value="${requestScope.avo.congend}"/>&nbsp;&nbsp;&nbsp;[사용일수: <span style="color: blue; font-weight: bold;">${requestScope.avo.congdates}</span>일]</td>
					</tr>
				</c:if>
				<c:if test="${requestScope.avo.vno eq '5'}">
					<tr>
						<th>출장기간</th>
						<td colspan="3"><input type="date" name="bustart" id="bustart" value="${requestScope.avo.bustart}"/> - <input type="date" name="buend" id="buend" value="${requestScope.avo.buend}"/></td>
					</tr>
					<tr>
						<th>출장지</th>
						<td><input type="text" style="width: 300px;" name="buplace" id="buplace" value="${requestScope.avo.buplace}"/></td>				
						<th>출장인원</th>
						<td><input type="text" style="width: 300px;" name="bupeople" id="bupeople" value="${requestScope.avo.bupeople}"/>명</td>
					</tr>
				</c:if>
				<c:if test="${requestScope.avo.vno eq '6'}">
					<tr>
						<th>요청시간</th>
						<td colspan="3"><input type="text" name="ewdate" id="ewdate" style="width: 370px;" value="${requestScope.avo.ewdate}"/>시간</td>
					</tr>
				</c:if>	
				
				<tr>
					<th style="height:250px;">글내용</th>
					<td colspan="3"><textarea name="acontent" id="acontent" rows="10" style="width: 100%;">${requestScope.avo.acontent}</textarea></td>
				</tr>
					
				<tr>
					<th>첨부파일</th>
					<td colspan="3">
					<c:if test="${requestScope.avo.orgFilename == null}">
						<input type="file" name="attach" value="${requestScope.avo.orgFilename}"/>
					</c:if>
					<c:if test="${requestScope.avo.orgFilename != null}">
						<a href="<%= ctxPath%>/t1/download.tw?ano=${requestScope.avo.ano}">${requestScope.avo.orgFilename}</a>&nbsp;&nbsp;<input type="button" style="width: 40px; font-size: 9pt; font-weight: bold; color: white; background-color: #d9534f; border: none;" onclick="removeFile();" value="삭제"/>
					</c:if>
					</td>	
				</tr>
			</table>
		</form>
			
		<div align="center">상기와 같은 내용으로 <span style="font-weight: bold;">${requestScope.avo.vcatname}계</span> 을(를) 제출하오니 재가바랍니다.</div>
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