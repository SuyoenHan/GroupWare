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
		var obj = []; //전역변수 
		
		//일반결재내역 문서 카테고리 라디오 클릭 이벤트
		$("input[name=scatname]").click(function(){
			
			$("input#atitle").val("");
			$("input#aconent").val("");
			
			$("td#smarteditor").empty();
			$("td#smarteditor").html('<textarea rows="30" cols="160" name="acontent" id="acontent"></textarea>');
			
			
			 var scatname;
			 var html1;
			 var html2;
			 var html3;
			 
			$("input[name=scatname]:checked").each(function(index,item){			
				scatname = $(this).val();
		     
			 //alert(ncat);
			
			});
				
			
			<%-- ===  스마트 에디터 구현 시작 ===  --%>
		       
		       
		       
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
			
				
				
				if(scatname =="지출결의서"){
					$("tr.changeScat").show();
					html1+="<th>지출일자</th>"+
					       "<td colspan='3'>&nbsp;<input type='date' name='exdate' id='exdate'/></td>";
					       
					      
				    html2+="<th>지출금액</th>"+
					       "<td colspan='3'><input type='number'  name ='exprice' id='exprice'/>원</td>";
					       
					  
						  
				}
				else if(scatname =="법인카드사용신청서"){
					$("tr.changeNcat").show();
					html1+="<th>사용예정일</th>"+
						  "<td colspan='3'>&nbsp;<input type='date' name='codate' id='codate'/></td>";
						  
						  
						  
					html2+= "<th>카드번호</th>"+
						    "<td colspan='3'><input type='hidden' name='cocardnum' value='[경영]111-222-333'/>[경영]111-222-333</td>";
						  
						  
				    html3+= 
						  "<th>예상금액</th>"+
						  "<td><input type='number'  name ='coprice' id='coprice'/>원</td>"+
						  "<th>지출목적</th>"+
						  "<td><select class='myselect' name='copurpose' id='exprice'> "+
			           		"	<option value='1'>교통비</option> "+
			           		"	<option value='2'>사무비품</option> "+
			           		"	<option value='3'>주유비</option> "+
			           		"	<option value='4'>출장비</option> "+
			           		"	<option value='5'>식비</option> "+
			           		"	<option value='6' selected>기타</option> "+
		           		"</select></td>";
				}
				
				
				if(scatname =="지출결의서"){
					$("tr#changeScat3").hide();
					$("tr#changeScat1").html(html1);
					$("tr#changeScat2").html(html2);
				}
				else{
					$("tr#changeScat3").show();
					$("tr#changeScat1").html(html1);
					$("tr#changeScat2").html(html2);
					$("tr#changeScat3").html(html3);
				}
				$("h3#scat").html(scat);
				$("div#containerview").show();
				
			}); //$("input[name=ncat]").click(function(){})------------------------------------
			
			
		 // 문서 제출하기
		$("button#insertWrite").click(function(){
		 
		    $("input[name=scatname]:checked").each(function(index,item){			
			  scatname = $(this).val();
			
			});
		 
		    //글제목 유효성검사
		    var atitleVal = $("input#atitle").val().trim();
            if(atitleVal == "") {
	            alert("글제목을 입력하세요!!");
	            return false;
	         }
		 
		    // 지출결의서 유효성 검사
		    if(scatname =="지출결의서"){     
		         var exdate = $("input#exdate").val(); //지출일자
		         var exprice = $("input#exprice").val().trim();// 지출금액
		         if(exdate == "" ) {
		            alert("지출일자를 입력하세요!!");
		            return false;
		         }
		         else if(exprice == ""){
		        	 alert("지출금액을 입력하세요!!");
			         return false;
		         }
		     
		    }
		    else if(scatname =="법인카드사용신청서"){
		        // 법인카드사용신청서 유효성 검사
		         var codate = $("input#codate").val(); //사용예정일
		         var coprice = $("select#coprice").val().trim(); //예상금액
		         if(codate == "" ) {
		            alert("사용예정일을 입력하세요!!");
		            return false;
		         }
		         else if(coprice == ""){
		        	 alert("예상금액을 입력하세요!!");
			         return false;
		         }
		    }    
		         
		    var bool = confirm(scatname+"을 제출하시겠습니까? ");
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
		         frm.action = "<%= ctxPath%>/t1/expApproval_WriteEnd.tw";
		         frm.submit();   
				}	
				  else{
					  alert(scatname+" 제출을 취소하셨습니다.");
					  location.href="javascript:history.back()";
				  }
		  });	// 문서제출 -----------------------------
			
		
		//문서 임시저장하기
		 $("button#saveWrite").click(function(){
			  
			  var scatname ;
				$("input[name=scatname]:checked").each(function(index,item){			
				      scatname = $(this).val();			
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
		          
		});	//임시보관함 ------------------------------------------
			
     }); //end of $(document).ready(function(){})----------------------
	
		
</script>

<div id="containerview">
	<hr style="border: 2px gray;">
	<h3 align="center">${requestScope.vcatname}</h3>
<hr>
<br>
<div id="astatus" >
	<table id="table1">
	
		<tr>
			<th style="width:100px; height:70px; ">대리</th>
			<th>부장</th>
			<th>사장</th>
		</tr>
		
		<tr>
			<td style="height:70px;">
				<c:if test="${not empty requestScope.alogList}">
					<c:forEach var="lvo" items="${requestScope.alogList}">
						<c:if test="${lvo.pcode == '2' && lvo.logstatus == '0'}">
							미결재
						</c:if>
						<c:if test="${lvo.pcode == '2' && lvo.logstatus == '1'}">
							승인
						</c:if>
						<c:if test="${lvo.pcode == '2' && lvo.logstatus == '2'}">
							반려
						</c:if>
					</c:forEach>
				</c:if>
			</td>
			<td>
				<c:if test="${not empty requestScope.alogList}">
					<c:forEach var="lvo" items="${requestScope.alogList}">
						<c:if test="${lvo.pcode == '3' && lvo.logstatus == '0'}">
							미결재
						</c:if>
						<c:if test="${lvo.pcode == '3' && lvo.logstatus == '1'}">
							승인
						</c:if>
						<c:if test="${lvo.pcode == '3' && lvo.logstatus == '2'}">
							반려
						</c:if>
					</c:forEach>
				</c:if>
			</td>
			<td>
				<c:if test="${not empty requestScope.alogList}">
					<c:forEach var="lvo" items="${requestScope.alogList}">
						<c:if test="${lvo.pcode == '4' && lvo.logstatus == '0'}">
							미결재
						</c:if>
						<c:if test="${lvo.pcode == '4' && lvo.logstatus == '1'}">
							승인
						</c:if>
						<c:if test="${lvo.pcode == '4' && lvo.logstatus == '2'}">
							반려
						</c:if>
					</c:forEach>
				</c:if>
			</td>
		</tr>
	</table>
		
	<table id="table2">
		<tr>
			<th>문서상태</th>
			<td style="width: 35%;">
				<c:if test="${requestScope.epvo.astatus eq '0'}">제출</c:if>
				<c:if test="${requestScope.epvo.astatus eq '1'}">결재진행중</c:if>
				<c:if test="${requestScope.epvo.astatus eq '2'}">반려</c:if>
				<c:if test="${requestScope.epvo.astatus eq '3'}">승인완료</c:if>					
			</td>
			<th>문서번호</th>
			<td>${requestScope.epvo.ano}</td>
		</tr>
		<tr>
			<th>제목</th>
			<td colspan="3">${requestScope.epvo.atitle}</td>
		</tr>
		
		<c:if test="${requestScope.epvo.vcatname eq '병가'}"> 
			<tr>
				<th>요청기간</th>
				<td colspan="3">${requestScope.epvo.slstart} - ${requestScope.epvo.slend}&nbsp;&nbsp;&nbsp;[사용일수: <span style="color: blue; font-weight: bold;">${requestScope.epvo.sldates}</span>일]</td>
			</tr>				
		</c:if>
		<c:if test="${requestScope.epvo.vcatname eq '반차'}">
			<tr>
				<th>요청기간</th>
				<td colspan="3">${requestScope.avo.afdate}&nbsp;&nbsp;
				<span style="color: blue; font-weight: bold;"><c:if test="${requestScope.epvo.afdan eq '1'}">오전</c:if><c:if test="${requestScope.epvo.afdan eq '2'}">오후</c:if></span>반차
				</td>
			</tr>
		</c:if>
		<c:if test="${requestScope.epvo.vcatname eq '연차'}">
			<tr>
				<th>요청기간</th>
				<td colspan="3">${requestScope.epvo.daystart} - ${requestScope.epvo.dayend}&nbsp;&nbsp;&nbsp;[사용일수: <span style="color: blue; font-weight: bold;">${requestScope.epvo.daydates}</span>일]</td>
			</tr>
		</c:if>
		<c:if test="${requestScope.epvo.vcatname eq '경조휴가'}">
			<tr>
				<th>요청기간</th>
				<td colspan="3">${requestScope.epvo.congstart} - ${requestScope.epvo.congend}&nbsp;&nbsp;&nbsp;[사용일수: <span style="color: blue; font-weight: bold;">${requestScope.epvo.congdates}</span>일]</td>
			</tr>
		</c:if>
		<c:if test="${requestScope.epvo.vcatname eq '출장'}">
			<tr>
				<th>출장기간</th>
				<td colspan="3">${requestScope.epvo.bustart} - ${requestScope.epvo.buend}</td>
			</tr>
			<tr>
				<th>출장지</th>
				<td>${requestScope.epvo.buplace}</td>				
				<th>출장인원</th>
				<td>${requestScope.epvo.bupeople}</td>
			</tr>
		</c:if>
		<c:if test="${requestScope.epvo.vcatname eq '추가근무'}">
			<tr>
				<th>요청시간</th>
				<td colspan="3">${requestScope.epvo.ewdate}시간</td>
			</tr>
		</c:if>	
		
		<tr>
			<th style="height:250px;">글내용</th>
			<td colspan="3">${requestScope.epvo.acontent}</td>
		</tr>
			
		<tr>
		<th>첨부파일</th>
			<td colspan="3">
				<c:if test="${not empty epvo.orgFilename}">
				   <a href ="<%= ctxPath%>/download1.tw?ano=${requestScope.epvo.ano}">${requestScope.epvo.orgFilename}</a>
				   
				</c:if>
				<c:if test="${empty epvo.orgFilename}">
				   <div align="center">첨부파일이 존재하지 않습니다.</div>
				</c:if>
			</td>
	    </tr>
    </table>
			
	<div align="center">상기와 같은 내용으로 <span style="font-weight: bold;">${requestScope.epvo.vcatname}계</span> 을(를) 제출하오니 재가바랍니다.</div>
	<div align="right" style="margin: 4px 0; margin-right: 15%;">기안일: ${requestScope.epvo.asdate}</div>
	<div align="right" style="margin-right: 15%;">신청자: ${requestScope.epvo.dname} ${requestScope.epvo.name} ${requestScope.epvo.pname}</div>
		
		
		
	
	
	</table>
		
	<table class="log">
			<tr>
				<th style="width:20%;">결재로그</th>
				<td>
					<c:if test="${not empty requestScope.alogList}">
						<c:forEach var="lvo" items="${requestScope.alogList}">
							<c:if test="${lvo.logstatus == '0'}">
							  	<div>${lvo.logdate}&nbsp;${lvo.dname}&nbsp;${lvo.name} &nbsp;${lvo.pname}<span style="color:red;"> [제출]</span></div>
							  </c:if>
							  <c:if test="${lvo.logstatus == '1'}">
							  	<div>${lvo.logdate}&nbsp;${lvo.dname}&nbsp;${lvo.name} &nbsp;${lvo.pname}<span style="color:red;"> [승인]</span></div>
							  </c:if>
							  <c:if test="${lvo.logstatus == '2'}">
							  	<div>${lvo.logdate}&nbsp;${lvo.dname}&nbsp;${lvo.name} &nbsp;${lvo.pname}<span style="color:red;"> [반려]</span></div>
							  </c:if>
						  
						</c:forEach>
					</c:if>
					<c:if test="${empty requestScope.alogList}">
					  <div> 결재 로그가 존재하지 않습니다. </div>
					</c:if>
				</td>
			</tr>
		</table>
		<table class="log">
			<tr>
				<th style="width:20%;">결재의견</th>
				<td>
					<c:if test="${not empty requestScope.opinionList}">
						<c:forEach var="ovo" items="${requestScope.opinionList}">
						  <div>${ovo.dname}&nbsp;${ovo.name}&nbsp;${ovo.pname}<span>[</span>${ovo.odate}<span>]</span></div>
						  <div>${ovo.ocontent}</div>
						</c:forEach>
					</c:if>
					
					<c:if test="${empty requestScope.opinionList}">
					  <div> 의견이 존재하지 않습니다. </div>
					</c:if>
				</td>
			</tr>
		</table>
		
	</div>

  
	<button type="button" onclick="javascript:location.href='<%=ctxPath %>/t1/expApproval_List.tw'" >전체목록보기</button>
     <button type="button" onclick="javascript:location.href='<%=ctxPath %>/${requestScope.gobackURL}'" >검색결과목록보기</button>

</div>
		