<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String ctxPath = request.getContextPath(); %>    
<link rel="stylesheet" type="text/css" href="<%=ctxPath %>/resources/css/kdn/board.css" />

<style type="text/css">

   #table {border-collapse: collapse;
           width: 1024px;
          }
   #table th, #table td{padding: 5px;}
   #table th{width: 120px; background-color: #DDDDDD;}
   #table td{width: 880px;}
   .long {width: 470px;}
   .short {width: 120px;}

</style>

<script type="text/javascript">
   $(document).ready(function(){
	   var categnum = $("input[name=fk_categnum]").val();
	   $("select#categ").val(categnum).prop("selected",true);
	   
	   $("select#categ").change(function(){
		  $(this).prop('selected',true);
		  var changedCategnum = $(this).val();
		  $("input[name=fk_categnum]").val(changedCategnum);
	   console.log(typeof changedCategnum);
	   });

	   
	   $("input[name=attach]").change(function(){
		   if($("input[name=attach]").val() != null){
			   $("span#displayFileName").hide();
		   }
		   if($("input[name=attach]").val() == ""){
			   $("span#displayFileName").show();
		   }
		   console.log($("input[name=attach]").val());
		   
	   });
	   
	   
      // 완료버튼
      $("button#btnUpdate").click(function(){
      
         
          // 글제목 유효성 검사
         var subjectVal = $("input#subject").val().trim();
         if(subjectVal == "") {
            alert("글제목을 입력하세요!!");
            return;
         }
         
         // 글내용 유효성 검사(스마트에디터 사용 안 할시)
         
         var contentVal = $("textarea#content").val().trim();
         if(contentVal == "") {
            alert("글내용을 입력하세요!!");
            return;
         }
        
         
         // 글암호 유효성 검사
         var pwVal = $("input#pw").val().trim();
         if(pwVal == "") {
            alert("글암호를 입력하세요!!");
            return;
         }
         
         // 폼(form) 을 전송(submit)
         var frm = document.editFrm;
         frm.method = "POST";
         frm.action = "<%= ctxPath%>/t1/noticeEditEnd.tw";
         frm.submit();   
      });
           
   });// end of $(document).ready(function(){})----------------
</script>

<div id="board-container">
	 <i class="fas fa-bullhorn fa-lg"></i>&nbsp;<span style="display: inline-block; font-size:22px; margin-bottom: 20px;">공지사항</span>
	
	 <form name="editFrm" enctype="multipart/form-data"><!-- 파일첨부가 있는 글쓰기 -->
       	 <input type="hidden" name="seq" value="${requestScope.boardvo.seq}" />
	    <table id="post-table" class="table">
	       <tr class="thead">
	          <th>성명</th>
	          <td>
	              <input type="hidden" name="name" class="short" value="${sessionScope.loginuser.name}" readonly />
	              <span>${sessionScope.loginuser.name}</span>
	          </td>
	       </tr>
	       <tr class="thead">
	          <th>구분</th>
	          <td>
	              <input type="hidden" name="fk_employeeid" value="${sessionScope.loginuser.employeeid}" />
	              <input type="hidden" name="fk_categnum" value="${requestScope.boardvo.fk_categnum}" /> 
	              <select id="categ" style="width: 100px; border-color: #ccc;">
	              	<option value="1">전체공지</option>
	              	<option value="2">총무공지</option>
	              	<option value="3">경조사</option>
	              </select>
	          </td>
	       </tr>
	       <tr class="thead">
	          <th>제목</th>
	          <td>
	             <input type="text" name="subject" id="subject" style="width: 100%;" value="${requestScope.boardvo.subject}" />       
	          </td>
	       </tr>
	       <tr class="thead">
         	<th>파일첨부</th>
         	<td>
         		<span id="displayFileName">첨부된 파일 : ${requestScope.boardvo.orgFilename}</span>
         		<input type="file" name="attach" />
         	</td>
           </tr>
	       <tr class="thead">
	          <th>내용</th>
	          <td>
	             <textarea rows="10" cols="100" style="width: 100%; height: 100%;" name="content" id="content">${requestScope.boardvo.content}</textarea>       
	          </td>
	       </tr>
	       <tr class="thead">
	          <th>비밀번호</th>
	          <td>
	             <input type="password" name="pw" id="pw" class="short" />       
	          </td>
	       </tr>
	    </table>
	      
	    <div id="btn-container" class="float-right">
	       <button type="button" id="btnUpdate" class="btn-style"><span style="color: #ffffff;">등록</span></button>
	       <button type="button" class="btn-style" onclick="javascript:history.back()"><span style="color: #ffffff;">취소</span></button>
	    </div>
	 </form>
</div>   