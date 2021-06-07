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
			$("input[name=scatname]").click(function(){
				
				 var scat;
				 var html1;
				 var html2;
				 var html3;
				 
				$("input[name=scatname]:checked").each(function(index,item){			
			      scat = $(this).val();
			     
				 //alert(ncat);
				
				});
				
				
				
				if(scat =="지출결의서"){
					$("tr.changeScat").show();
					html1+="<th>지출일자</th>"+
					       "<td colspan='3'>&nbsp;<input type='date' name='exdate'/></td>";
					       
					      
				    html2+="<th>지출금액</th>"+
					       "<td colspan='3'><input type='number' min='1' max='50000000' name ='exprice'/>원</td>";
					       
					  
						  
				}
				else if(scat =="법인카드사용신청서"){
					$("tr.changeNcat").show();
					html1+="<th>사용예정일</th>"+
						  "<td colspan='3'>&nbsp;<input type='date' name='codate'/></td>";
						  
						  
						  
					html2+= "<th>카드번호</th>"+
						    "<td colspan='3'><input type='hidden' name='cocardnum' value='[경영]111-222-333'/>[경영]111-222-333</td>";
						  
						  
				    html3+= 
						  "<th>예상금액</th>"+
						  "<td><input type='number' min='1' max='50000000' name ='coprice'/>원</td>"+
						  "<th>지출목적</th>"+
						  "<td><select class='myselect' name='copurpose'> "+
			           		"	<option value='1'>교통비</option> "+
			           		"	<option value='2'>사무비품</option> "+
			           		"	<option value='3'>주유비</option> "+
			           		"	<option value='4'>출장비</option> "+
			           		"	<option value='5'>식비</option> "+
			           		"	<option value='6' selected>기타</option> "+
		           		"</select></td>";
						  
		           		
						  
						  
				}
				
				
				
				$("tr#changeScat1").html(html1);
				$("tr#changeScat2").html(html2);
				if(scat =="지출결의서"){
					$("tr#changeScat3").hide();
				}
				else{
					$("tr#changeScat3").show();
					$("tr#changeScat3").html(html3);
				}
				$("h3#scat").html(scat);
				$("div#containerview").show();
				
			}); //$("input[name=ncat]").click(function(){})------------------------------------
			
			
			
			
			
		}); //end of $(document).ready(function(){
		
	 // 문서 제출하기
		function insertWrite(){
	         // 폼(form) 을 전송(submit)
	         var frm = document.writeGFrm;
	         frm.method = "POST";
	         frm.action = "<%= ctxPath%>/t1/expApproval_WriteEnd.tw";
	         frm.submit();   
			}	
		
	  //문서 임시저장하기
		function saveWrite(){
		  
		  var scatname ;
			$("input[name=scatname]:checked").each(function(index,item){			
			      scatname = $(this).val();
			     
				 //alert(scat);
				
			});
	  
		  var bool = confirm(scatname+"를 임시저장함에 저장하시겠습니까? ");
		  if(bool){
		  
			 // 폼(form) 을 전송(submit)
	         var frm = document.writeGFrm;
	         frm.method = "POST";
	         frm.action = "<%= ctxPath%>/t1/expApproval_saveWrite.tw";
	         frm.submit(); 
		  }
		  else{
			  alert(scatname+" 저장을 취소하셨습니다.");
			  location.href="javascript:history.back()";
		  }
	         
	          
			}	
		
</script>

<form name="writeGFrm"  enctype="multipart/form-data">

<div id="containerall">
	<div id=radio>
 		<span style="border:solid 2px gray; width:400px; height:100px; font-size:20px; padding:10px 10px;">전자결재문서작성</span>
				<span style="margin-left:20px;">&nbsp;&nbsp;
					<label for="expense"><input type="radio" name="scatname" id="expense" value="지출결의서"> 지출결의서</label>&nbsp;&nbsp;
					<label for="cocard"><input  type="radio" name="scatname" id="cocard" value="법인카드사용신청서"> 법인카드사용신청서</label>&nbsp;&nbsp;
					<label for="ex1"><input type="radio" name="scatname" id="ex1" value="출장명세서"> 출장명세서(EX)</label>&nbsp;&nbsp;
					<label for="ex2"><input type="radio" name="scatname" id="ex2" value="퇴직금정산신청서"> 퇴직금정산신청서(EX)</label>&nbsp;&nbsp;
				</span>
 	</div>
	<div id="containerview">
 	
	<hr class="hr">
		<h3 id="scat" align="center"></h3>
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
				<td style="height:70px;"></td>
				<td></td>
				<td></td>
			</tr>
		</table>
		
		
		<table id="table2">
			<tr>
				<th style="width:200px;">수신참조</th>
				<td colspan="3"><input type="hidden" name="arecipient1" value="${requestScope.write_view.managerid}"/>${requestScope.write_mview.name} ${requestScope.write_mview.pname} (${requestScope.write_view.dname})</td>
				
			</tr>
			
			<tr>
				<th>제목</th>
				<td colspan="3"><input type="text" width="80%"name="atitle"></td>
			</tr>
			
			<tr class="changeScat" id="changeScat1">
			
			</tr>
			<tr class="changeScat" id="changeScat2">
			
			</tr>
			<tr class="changeScat" id="changeScat3">
			
			</tr>
			
			
			<tr>
				<th>문서상태</th>
				<td colspan='3'><input type="hidden" name="astatus" value="0"/>작성중..</td>
			</tr>
			
			
			
			<tr>
				<th style="height:350px;">글내용</th>
				<td colspan='3'><textarea rows="30" cols="160" name="acontent"></textarea></td>
			</tr>
			
			<tr>
				<th>첨부파일</th>
				<td colspan='3'>
					<input type="file" name="attach"/>
				</td>
			</tr>
			
	
		</table>
		
		<button type="button" onclick="javascript:location.href='<%=ctxPath %>/t1/expApproval_Write.tw'" >취소</button>
    	<button type="button" onclick="insertWrite();" >제출하기</button>
    	<button type="button" onclick="saveWrite();" >저장하기</button>
	</div>
</div>
</div>

<input type="hidden" name="fk_employeeid" value="${sessionScope.loginuser.employeeid}" />
</form>
		