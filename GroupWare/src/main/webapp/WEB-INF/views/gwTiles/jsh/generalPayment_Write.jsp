<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<% String ctxPath = request.getContextPath(); %>

<style>

table, th, td {border: solid 1px gray;}

#containerall{
color:#666;
}
#table1 {
    float:right; 
    width: 300px; 
    border-collapse: collapse;
    margin-right: 200px;
    margin-bottom: 80px;
}
    
th{
    background-color: #DDD;
}

#table2 {
	width: 80%;
	height: 900px;
	margin: 50px auto;
}

.log {
	width: 80%;
	height: 100px;
	margin: 50px auto;
}

#radio{
	width: 80%;
	height: 150px;
	margin-top:30px;
	margin: 50px auto;
	font-size: 20px;

}


hr.hr{
   border: solid 2px gray;
   width:80%;
}

#containerall{
	padding:50px 50px;
}
</style>

<script type="text/javascript">
		$(document).ready(function(){
			//사이드바 세부메뉴 나타내기 
			$("div#submenu3").show();
			
			$("div#containerview").hide();
			
			
			//일반결재내역 문서 카테고리 라디오 클릭 이벤트
			$("input[name=ncatname]").click(function(){
				
				 var ncat;
				 var html;
				 
				$("input[name=ncatname]:checked").each(function(index,item){			
			      ncat = $(this).val();
			     
				 //alert(ncat);
				
				});
				
				//var mdate = "<input type='date'/>"+"&nbsp;<input type='time' name='mdate'/>"+"&nbsp;~&nbsp;"+"<input type='time' name='mdate' />";
				
				if(ncat =="회의록"){
					$("tr#changeNcat").show();
					html+="<th>회의시간</th>"+
					       "<td><input type='date' name='mdate1'/>"+"&nbsp;<input type='time' name='mdate2'/>"+"&nbsp;~&nbsp;"+"<input type='time' name='mdate3' /></td>"
					//"<td><input type='datetime' name='mdate' value='"+mdate+"</td>"
						  
						  
						  
				}
				else if(ncat =="위임장"){
					$("tr#changeNcat").show();
					html+="<th>위임기간</th>"+
						  "<td ><input type='date' name='fk_wiimdate1'/>&nbsp;~&nbsp;<input type='date' name='fk_wiimdate2'/></td>"
				}
				
				else if(ncat =="협조공문"){
					$("tr#changeNcat").show();
					html+="<th>타회사명</th>"+
						  "<td><input type='text' name='comname'/></td>"
				}
				
				//외부공문이 아닐때에만  <tr> 행을 추가시킨다.
				if(ncat !="외부공문"){
					$("tr#changeNcat").html(html);
				}
				//외부공문일 때에는 <tr>행을 숨겨준다.
				else{
					$("tr#changeNcat").hide();
				}
				
				
				$("h3#ncat").html(ncat);
				$("div#containerview").show();
				
			}); //$("input[name=ncat]").click(function(){})------------------------------------
			
			
			
			
			
		}); //end of $(document).ready(function(){
		
	 // 문서 제출하기
		function insertWrite(){
	         // 폼(form) 을 전송(submit)
	         var frm = document.writeGFrm;
	         frm.method = "POST";
	         frm.action = "<%= ctxPath%>/t1/generalPayment_WriteEnd.tw";
	         frm.submit();   
			}	
		
	  //문서 임시저장하기
		function saveWrite(){
		  
		  var ncatname ;
			$("input[name=ncatname]:checked").each(function(index,item){			
			      ncatname = $(this).val();
			     
				 //alert(ncat);
				
			});
	  
		  var bool = confirm(ncatname+"을 임시저장함에 저장하시겠습니까? ");
		  if(bool){
		  
			 // 폼(form) 을 전송(submit)
	         var frm = document.writeGFrm;
	         frm.method = "POST";
	         frm.action = "<%= ctxPath%>/t1/generalPayment_saveWrite.tw";
	         frm.submit(); 
		  }
		  else{
			  alert(" 저장을 취소하셨습니다.");
			  location.href="javascript:history.back()";
		  }
	         
	          
			}	
		
</script>

<form name="writeGFrm"  enctype="multipart/form-data">

<div id="containerall">
	<div id=radio>
 		<span style="border:solid 2px gray; width:400px; height:100px; font-size:20px; padding:10px 10px;">일반결재문서작성</span>
				<span style="margin-left:20px;">&nbsp;&nbsp;
					<label for="minutes"><input type="radio" name="ncatname" id="minutes" value="회의록"> 회의록</label>&nbsp;&nbsp;
					<label for="wiimjang"><input  type="radio" name="ncatname" id="wiimjang" value="위임장"> 위임장</label>&nbsp;&nbsp;
					<label for="exofficial"><input type="radio" name="ncatname" id="exofficial" value="외부공문"> 외부공문</label>&nbsp;&nbsp;
					<label for="coofficial"><input type="radio" name="ncatname" id="coofficial" value="협조공문"> 협조공문</label>&nbsp;&nbsp;
				</span>
 	</div>
	<div id="containerview">
 	
	<hr class="hr">
		<h3 id="ncat" align="center"></h3>
	<hr class="hr">
	<br>
	<div id="astatus" >
		<table id="table1">
		
			<tr>
				<th style="width:100px; height:70px; ">대리</th>
				<th>부장</th>
				<th>사장</th>
			</tr>
			
			<tr>
				<td style="height:70px;">d</td>
				<td>d</td>
				<td>d</td>
			</tr>
		</table>
		
		
		<table id="table2">
			<tr>
				<th style="width:200px;">수신참조</th>
				<td><input type="hidden" name="arecipient1" value="${requestScope.write_view.managerid}"/>${requestScope.write_mview.name} ${requestScope.write_mview.pname} (${requestScope.write_view.dname})</td>
				<%-- <td><button type="button" >수신자찾기</button><span id="arecipient1" name="arecipient1"></span></td>--%>
			</tr>
			
			<tr>
				<th>제목</th>
				<td ><input type="text" width="80%"name="atitle"></td>
			</tr>
		
			
			<tr>
				<th>작성자</th>
				<td><input type="hidden" name="fk_employeeid" value="${sessionScope.loginuser.employeeid}" />${sessionScope.loginuser.name}</td>
				
				
			</tr>
		
			<tr>
				<th>소속</th>
				<td>${requestScope.write_view.dname}</td>
			</tr>
			
			<tr id="changeNcat">
			
			</tr>
			
			
			<tr>
				<th>문서상태</th>
				<td><input type="hidden" name="astatus" value="0"/>작성중..</td>
			</tr>
			
			
			
			<tr>
				<th style="height:350px;">글내용</th>
				<td><textarea rows="30" cols="160" name="acontent"></textarea></td>
			</tr>
			
			<tr>
				<th>첨부파일</th>
				<td>
					<input type="file" name="attach"/>
				</td>
			</tr>
			
	
		</table>
		
		<button type="button" onclick="javascript:location.href='<%=ctxPath %>/t1/generalPayment_Write.tw'" >취소</button>
    	<button type="button" onclick="insertWrite();" >제출하기</button>
    	<button type="button" onclick="saveWrite();" >저장하기</button>
	</div>
</div>
</div>
</form>
		