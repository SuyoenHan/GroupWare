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
			var obj = []; //전역변수 //이거때문은 아니겠지? 아 맞을 수도있겠다 이게 if 문 안에 들어가 있어서..함해볼게 ㅋㅋㅋㅋ 이걸 밖으로 빼니까 된거보면...
					
			//일반결재내역 문서 카테고리 라디오 클릭 이벤트
			$("input[name=ncatname]").click(function(){
				
				$("td#smarteditor").empty();
				$("td#smarteditor").html('<textarea rows="30" cols="160" name="acontent" id="acontent"></textarea>');
				
				
				 var ncat;
				 var html;
				 var html1;
				 
				$("input[name=ncatname]:checked").each(function(index,item){			
			      ncat = $(this).val();
			     
				 //alert(ncat);
			      
				});
				
				
				<%-- ===  스마트 에디터 구현 시작 === --%>
			       
			       
			       
			       //스마트에디터 프레임생성
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
				
				
				//var mdate = "<input type='date'/>"+"&nbsp;<input type='time' name='mdate'/>"+"&nbsp;~&nbsp;"+"<input type='time' name='mdate' />";
				
				if(ncat =="회의록"){
					$("tr#changeNcat").show();
					html+="<th>회의시간</th>"+
					       "<td><input type='date' name='mdate1' id='mdate1'/>"+"&nbsp;<input type='time' name='mdate2' id='mdate2'/>"+"&nbsp;~&nbsp;"+"<input type='time' name='mdate3' id='mdate3' /></td>"
					//"<td><input type='datetime' name='mdate' value='"+mdate+"</td>"
					  
						  
				}
				else if(ncat =="위임장"){
					$("tr#changeNcat").show();
					html+="<th>위임기간</th>"+
						  "<td ><input type='date' name='fk_wiimdate1' id='fk_wiimdate1'/>&nbsp;~&nbsp;<input type='date' name='fk_wiimdate2' id='fk_wiimdate2'/></td>"
					
						
				}
				
				else if(ncat =="협조공문"){
					$("tr#changeNcat").show();
					html+="<th>타회사명</th>"+
						  "<td><input type='text' name='comname' id='comname'/></td>";
						  
					  
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
			
		
			
	    // 문서 제출하기
	    $("button#insertWrite").click(function(){
		
		 
		    var ncatname ;
			$("input[name=ncatname]:checked").each(function(index,item){			
			      ncatname = $(this).val();
			     
				 //alert(ncat);
				
			});
			
			
			// 글제목 유효성 검사
	         var atitleVal = $("input#atitle").val().trim();
	         if(atitleVal == "") {
	            alert("글제목을 입력하세요!!");
	            return;
	         }
	         
		    // 회의록 회의시간 유효성 검사
		    if(ncat =="회의록"){     
		         var mdate1 = $("input#mdate1").val().trim();
		         var mdate2 = $("input#mdate2").val().trim();
		         var mdate3 = $("input#mdate3").val().trim();
		         if(mdate1 == "" || mdate2 == "" || mdate3 == "") {
		            alert("회의시간을 입력하세요!!");
		            return;
		         }
	         
		    }
		    else if(ncat =="위임장"){
		         // 위임기간 유효성 검사
		         var fk_wiimdate1 = $("input#fk_wiimdate1").val().trim();
		         var fk_wiimdate2 = $("input#fk_wiimdate2").val().trim();
		         if(fk_wiimdate1 == "" || fk_wiimdate2 == "" ) {
		            alert("위임기간을 입력하세요!!");
		            return;
		         }
	         
		    }
		    else if(ncat =="협조공문") {
	         // 타회사명 유효성 검사
		         var comname = $("input#comname").val().trim();
		         if(comname == "") {
		            alert("타회사명을 입력하세요!!");
		            return;
		         }
		    }
	  
		  var bool = confirm(ncatname+"을 제출하시겠습니까? ");
		  if(bool){
		 
	 
		 
		   <%-- === 스마트 에디터 구현 시작 === --%>
		   
        //id가 content인 textarea에 에디터에서 대입
	         obj.getById["acontent"].exec("UPDATE_CONTENTS_FIELD", []);
	         
	       <%-- === 스마트 에디터 구현 끝 === --%>
	         
	          
	
	         
		       <%-- === 스마트에디터 구현 시작 === --%>
	    // 스마트에디터 사용시 무의미하게 생기는 p태그 제거
		       var contentval = $("textarea#acontent").val();
	              
		       // === 확인용 ===
	           // alert(contentval); // content에 내용을 아무것도 입력치 않고 쓰기할 경우 알아보는것. 
	           // "<p>&nbsp;</p>" 이라고 나온다.
	           
	           // 스마트에디터 사용시 무의미하게 생기는 p태그 제거하기전에 먼저 유효성 검사를 하도록 한다.
	           // 글내용 유효성 검사 
	           if(contentval == "" || contentval == "<p>&nbsp;</p>") {
	              alert("글내용을 입력하세요!!");
	              return;
	           }
	           
	           // 스마트에디터 사용시 무의미하게 생기는 p태그 제거하기
	           contentval = $("textarea#acontent").val().replace(/<p><br><\/p>/gi, "<br>"); //<p><br></p> -> <br>로 변환
		       /*    
		                   대상문자열.replace(/찾을 문자열/gi, "변경할 문자열");
		           ==> 여기서 꼭 알아야 될 점은 나누기(/)표시안에 넣는 찾을 문자열의 따옴표는 없어야 한다는 점입니다. 
		                        그리고 뒤의 gi는 다음을 의미합니다.
		
		              g : 전체 모든 문자열을 변경 global
		              i : 영문 대소문자를 무시, 모두 일치하는 패턴 검색 ignore
		       */    
	           contentval = contentval.replace(/<\/p><p>/gi, "<br>"); //</p><p> -> <br>로 변환  
	           contentval = contentval.replace(/(<\/p><br>|<p><br>)/gi, "<br><br>"); //</p><br>, <p><br> -> <br><br>로 변환
	           contentval = contentval.replace(/(<p>|<\/p>)/gi, ""); //<p> 또는 </p> 모두 제거시
	       
	           $("textarea#acontent").val(contentval);
	           
	         <%-- === 스마트에디터 구현 끝 === --%>
	 
	 
	         // 폼(form) 을 전송(submit)
	         var frm = document.writeGFrm;
	         frm.method = "POST";
	         frm.action = "<%= ctxPath%>/t1/generalPayment_WriteEnd.tw";
	      //   frm.submit();   
	         
		  }
		  else{
			  alert(ncatname+" 제출을 취소하셨습니다.");
			  location.href="javascript:history.back()";
		  }
	  });	
		
	  //문서 임시저장하기
	  $("button#saveWrite").click(function(){
		
		  var ncatname ;
			$("input[name=ncatname]:checked").each(function(index,item){			
			      ncatname = $(this).val();
			     
				 //alert(ncat);
				
			});
	  
		 
			  <%-- === 스마트 에디터 구현 시작 === --%>
	        //id가 content인 textarea에 에디터에서 대입
		         obj.getById["acontent"].exec("UPDATE_CONTENTS_FIELD", []); 
		      <%-- === 스마트 에디터 구현 끝 === --%>
		         
	  
		         
			       <%-- === 스마트에디터 구현 시작 === --%>
       	       // 스마트에디터 사용시 무의미하게 생기는 p태그 제거
			       var contentval = $("textarea#acontent").val();
		              
			       // === 확인용 ===
		           // alert(contentval); // content에 내용을 아무것도 입력치 않고 쓰기할 경우 알아보는것.
		           // "<p>&nbsp;</p>" 이라고 나온다.
		           
		           // 스마트에디터 사용시 무의미하게 생기는 p태그 제거하기전에 먼저 유효성 검사를 하도록 한다.
		           // 글내용 유효성 검사 
		           if(contentval == "" || contentval == "<p>&nbsp;</p>") {
		              alert("글내용을 입력하세요!!");
		              return;
		           }
		           
		           // 스마트에디터 사용시 무의미하게 생기는 p태그 제거하기
		           contentval = $("textarea#acontent").val().replace(/<p><br><\/p>/gi, "<br>"); //<p><br></p> -> <br>로 변환
			       /*    
			                   대상문자열.replace(/찾을 문자열/gi, "변경할 문자열");
			           ==> 여기서 꼭 알아야 될 점은 나누기(/)표시안에 넣는 찾을 문자열의 따옴표는 없어야 한다는 점입니다. 
			                        그리고 뒤의 gi는 다음을 의미합니다.
			
			              g : 전체 모든 문자열을 변경 global
			              i : 영문 대소문자를 무시, 모두 일치하는 패턴 검색 ignore
			       */    
		           contentval = contentval.replace(/<\/p><p>/gi, "<br>"); //</p><p> -> <br>로 변환  
		           contentval = contentval.replace(/(<\/p><br>|<p><br>)/gi, "<br><br>"); //</p><br>, <p><br> -> <br><br>로 변환
		           contentval = contentval.replace(/(<p>|<\/p>)/gi, ""); //<p> 또는 </p> 모두 제거시
		       
		           $("textarea#acontent").val(contentval);
		           
		         <%-- === 스마트에디터 구현 끝 === --%>
	         
	         var bool = confirm(ncatname+"을 임시저장함에 저장하시겠습니까? ");
			  if(bool){
				  
			  
				 // 폼(form) 을 전송(submit)
		         var frm = document.writeGFrm;
		         frm.method = "POST";
		         frm.action = "<%= ctxPath%>/t1/generalPayment_saveWrite.tw";
		         frm.submit(); 
			  }
			  else{
				  alert(ncatname+" 저장을 취소하셨습니다.");
				  location.href="javascript:history.back()";
			  }
	         
	          
		  });	
			
			
		}); //end of $(document).ready(function(){
		
	 
		
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
				<td style="height:70px;"></td>
				<td></td>
				<td></td>
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
				<td ><input type="text" width="80%"name="atitle" id="atitle"></td>
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
				<td id="smarteditor"> 
				<%--   <textarea rows="30" cols="160" name="acontent" id="acontent"></textarea> --%>
				</td>
			</tr>
			
			<tr>
				<th>첨부파일</th>
				<td>
					<input type="file" name="attach"/>
				</td>
			</tr>
			
	
		</table>
		
		<button type="button" onclick="javascript:location.href='<%=ctxPath %>/t1/generalPayment_Write.tw'" >취소</button>
    	<button type="button" id="insertWrite" >제출하기</button>
    	<button type="button" id="saveWrite" >저장하기</button>
	</div>
</div>
</div>
</form>
		