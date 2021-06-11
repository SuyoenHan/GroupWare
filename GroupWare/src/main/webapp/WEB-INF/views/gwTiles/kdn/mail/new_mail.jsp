<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String ctxPath = request.getContextPath(); %>
<link rel="stylesheet" type="text/css" href="<%=ctxPath %>/resources/css/kdn/mail.css" />
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<style type="text/css">

</style>

<script type="text/javascript">
$(document).ready(function(){
	
	// 주소록 페이지에서 받는사람 이메일 주소 넘겨받은경우
	var arrReceiverEmail = [];
	var addrsBookEmail = "${requestScope.addrsBookEmail}";
	console.log(addrsBookEmail);
	if(addrsBookEmail != null && addrsBookEmail != ""){
	 // alert ('주소록에서 이메일 가져왓다');
	  $('#receiver').append('<span class="email-ids">'+ addrsBookEmail +' <span class="cancel-email" style="color:gray;"><i class="far fa-window-close"></i></span></span>');
	  arrReceiverEmail.push(addrsBookEmail);
	  // console.log("주소록에서 메일받아온 후 배열:"+addrsBookEmail);
	}
	
	// 회신메일 쓰는 경우
	var replySubject = "${requestScope.subject}";
	// console.log("회신용 메일제목: "+replySubject);
	var preInputRplEmail = "${requestScope.replyEmail}";
	var preInputRplName = "${requestScope.replyToName}";
	if(replySubject != null && replySubject != ""){
		//alert ('회신메일 페이지입니다');
		$('#receiver').append('<span class="email-ids">'+preInputRplName+'&lt;'+preInputRplEmail+'&gt;'+' <span class="cancel-email" style="color:gray;"><i class="far fa-window-close"></i></span></span>');
		//console.log('회신메일 preinput Email : '+preInputRplEmail);
		$("input#subject").val("RE: "+replySubject);
		arrReceiverEmail.push(preInputRplEmail);		
		//console.log("회신이메일 넣은 배열 값: "+arrReceiverEmail);
	}
	
	
	//보낸메일함 저장여부
    $("input[name=saveSentMail]").val("0");
    $("input#saveSentMail").change(function(){
		if($(this).is(":checked") == true) {
			$("input[name=saveSentMail]").val("1");		
		} else {
			$("input[name=saveSentMail]").val("0");
		}
    });
	
	
	// 이메일주소 자동완성을 위한 주소록 목록 가져오기
 	var availableEmail = ${requestScope.emailList};
 	
 	// 받는사람 이메일주소 자동완성
    $("#to-input").autocomplete({
      source: availableEmail
    });
 	
 	// 받는사람 이메일 입력
 	
    $("#to-input").keydown(function (e) {
   	  if (e.keyCode == 13 || e.keyCode == 32) {
   		 var getValue = $(this).val();
   		 console.log("getValue : "+getValue);
   		 var emailStartIdx = getValue.indexOf("<")+2;
   		 var emailEndIdx = getValue.indexOf(">")-1;
   		 var emailOnly = getValue.substring(emailStartIdx,emailEndIdx);
   		 if(getValue.trim() != null && getValue.trim() != ""){
	   		 $('#receiver').append('<span class="email-ids" name="email-container">'+ getValue +' <span class="cancel-email" name="cancel-btn" style="color:gray;"><i class="far fa-window-close"></i></span></span>');
	   		 arrReceiverEmail.push(emailOnly);
	    	 console.log(arrReceiverEmail);
   		 }
   		 //$('#receiver-email').append('<input type="text" name="receiverEmail" value="'+emailOnly+'" />');
    	 
   		 $(this).val('');
   	  }
   	  // 백스페이스 눌렀을때 이메일박스 지우기
   	  if(e.keyCode == 8){
   		  console.log('백스페이스 눌렀다');
   	  }
   	});

    
    // 참조 이메일주소 자동완성
    $("#cc-input").autocomplete({
      source: availableEmail
    });
    
    // 참조이메일 입력
    var arrCcEmail = [];
    $("#cc-input").keydown(function (e) {
   	  if (e.keyCode == 13 || e.keyCode == 32) {
   		 var getValue = $(this).val();
   		 console.log("getValue : "+getValue);
  		 var ccStartIndex = getValue.indexOf("<")+2;
  		 var ccEndIndex = getValue.indexOf(">")-1;
  		 var emailOnly = getValue.substring(ccStartIndex,ccEndIndex);
  		 if(getValue.trim() != null && getValue.trim() != ""){
	   		 $('#cc').append('<span class="email-ids">'+ getValue +' <span class="cancel-ccemail" style="color:gray;"><i class="far fa-window-close"></i></span></span>');
	   		 arrCcEmail.push(emailOnly);
	    	 console.log(arrCcEmail);
   		 }
   		 $(this).val('');
   	  }
   	});
    
   	/// Cancel 
	//받는메일주소 취소클릭시
   	$(document).on('click','[name=cancel-btn]',function(){
	      $(this).parent().remove();
	      $("input[name=receiver]").val("");
	      
	      console.log("이 취소버튼의 인덱스 번호 :"+$(this).index());
	      console.log("취소 후 배열값: "+arrReceiverEmail);
	      
	      /* 
	      for(let i = 0; i < arr.length; i++) {
	    	  if(arr[i] === 'b')  {
	    	    arr.splice(i, 1);
	    	    i--;
	    	  }
	    	} */
	      
	});
    
  //참조메일주소 취소클릭시
   	$(document).on('click','.cancel-ccemail',function(){
	      $(this).parent().remove();
	      $("input#cc-input").val("");
	});
    
	
	//내게쓰기
    $("input#write-to-myself").change(function(){
		if($("input#write-to-myself").is(":checked") == true) {
			var myEmail="${loginuser.email}";
			$('#receiver').append('<span class="email-ids write-to-myself">'+myEmail+' <span class="cancel-email" style="color:gray;"><i class="far fa-window-close"></i></span></span>');
			arrReceiverEmail.push(myEmail);
			console.log(arrReceiverEmail);
/* 			if($("input[name=receiver]").value == null ){
				$('#receiver-email').append('<input type="text" name="receiverEmail" value="'+myEmail+'" />');
			} else {
				$("input[name=receiver]").append(myEmail);
			} */
		} else {
			$("span.write-to-myself").remove();
			arrReceiverEmail.splice(arrReceiverEmail.indexOf(myEmail),1);
			console.log(arrReceiverEmail);
			$("input[name=receiverEmail]").val("");
		}
    });
	
	//중요표시 여부
    $("input[name=checkImportant]").val("0");
    $("input#starred").change(function(){
		if($(this).is(":checked") == true) {
			$("input[name=checkImportant]").val("1");		
		} else {
			$("input[name=checkImportant]").val("0");
		}
    });
	
 	// 이메일 주소 부분입력시 자동 완성
    
    
    // 쓰기버튼
    $("button#btnSend").click(function(){
       
       //var emailVal = $("input[name=receiverEmail]").val().trim();
       
       var find = $("div#receiver").find('span').val();
       console.log("find: "+find);
       
       if($("div#receiver").find('span').val() == null){
          alert("받는사람 이메일주소를 입력하세요");
          return;
       }
       
       var str_arrReceiverEmail = arrReceiverEmail.join(',');
       console.log("str_arrReceiverEmail: "+str_arrReceiverEmail);
       
       if(arrCcEmail != null){
    	   var str_arrCcEmail = arrCcEmail.join();
	       console.log("str_arrCcEmail: "+str_arrCcEmail);
       }
       
       
       
        // 글제목 유효성 검사
       var subjectVal = $("input#subject").val().trim();
       if(subjectVal == "") {
          alert("제목을 입력하세요");
          return;
       }
       
       // 글내용 유효성 검사(스마트에디터 사용 안 할시)
       
       var contentVal = $("textarea#content").val().trim();
       if(contentVal == "") {
          alert("내용을 입력하세요");
          return;
       }
       
       // 폼(form) 을 전송(submit)
       var frm = document.newMailFrm;
       frm.receiverEmail.value = str_arrReceiverEmail;
       frm.ccEmail.value = str_arrCcEmail;
       frm.method = "POST";
       frm.action = "<%= ctxPath%>/t1/sendSuccess.tw";
       frm.submit();   
    });
      
 	$("#btnCancel").click(function(){
 		if (confirm("입력하신 내용은 저장되지 않습니다. 메일쓰기를 취소하시겠습니까?") == true){    //확인
			location.href="<%=ctxPath%>/t1/mail.tw";
		 }else{   //취소
		     return false;
		 }
 	});
 	
 });// end of $(document).ready(function(){})----------------
 
 // 보낸메일함 저장하기
 function checkSaveSentMail(){
	 
	 if($("input#saveSentMail").is(":checked") == false) {
		 	$("input#saveSentMail").prop('checked',true);
			$("input[name=saveSentMail]").val("1");		
		} else {
		 	$("input#saveSentMail").prop('checked',false);
			$("input[name=saveSentMail]").val("0");
		}
 }
 
 
</script>

<div id="mail-header" style="width: 100%; height: 120px; padding: 20px;">
	 <i class="far fa-paper-plane fa-lg"></i>&nbsp;&nbsp;<span style="display: inline-block; font-size:22px; margin-bottom: 20px;">메일쓰기</span>
	 <div id="left-header">
		 <button type="submit" id="btnSend" class="btn-style float-right">보내기</button>
		 <button type="button" id="btnCancel" class="btn-style float-right">취소</button>
		 <input type="checkbox" id="saveSentMail" />&nbsp;&nbsp;<a href="javascript:checkSaveSentMail()" style="text-decoration:none; color:black;">보낸메일함에 저장</a>
	 </div>
</div>
<div id="mailForm-container" style="padding: 10px; border: solid 1px gray;">
 <form name="newMailFrm" enctype="multipart/form-data"> 
	  <input type="hidden" name="saveSentMail" />
      <table id="table">
         <tr>
            <th>보내는사람</th>
            <td>
                <span>${sessionScope.loginuser.name}&lt;${loginuser.email}&gt;</span>
                <input type="hidden" name="senderEmail" value="${sessionScope.loginuser.email}" class="short" readonly />     
            </td>
         </tr>
         <tr>
            <th><label for="receiver">받는사람&nbsp;&nbsp;&nbsp;&nbsp;</label><input type="checkbox" id="write-to-myself" />&nbsp;&nbsp;내게쓰기</th>
            <td>
               <div id="receiver" style="width:95%; border: solid 1px #ccc; display: inline-block;"><input type="text" id="to-input" style="width:300px; margin-right: 10px; display: inline-block; border: none; outline:none; overflow:hidden;"/></div><button type="button" id="contact" class="btn-style" style="display:inline-block;">주소록</button> 
               <input type="hidden" name="receiverEmail" />
            </td>
         </tr>
         <tr>
            <th><label for="cc">참조</label></th>
            <td>
            	<div id="cc" style="width:95%; border: solid 1px #ccc; display: inline-block;"><input type="text" id="cc-input" style="width:300px; margin-right: 10px; display: inline-block; border: none; outline:none;"/></div>
	            <input type="hidden" name="ccEmail" />
            </td>
         </tr>
         <tr>
            <th>
            <input type="hidden" name="checkImportant" />
            	<span>제목&nbsp;&nbsp;<input type="checkbox" id="starred" />&nbsp;&nbsp;중요 <i class="fas fa-star" style="color: #ffcc00"></i></span></th>
            <td>
               <input type="text" name="subject" id="subject" style="width:100%"/>       
            </td>
         </tr>
         <tr>
         	<th>파일첨부</th>
         	<td>
         		<input type="file" name="attach" style="width: 100%;"/>
         	</td>
           </tr>
         <tr>
            <th>내용</th>
            <td>
               <textarea rows="10" cols="100" style="width: 100%; height: 100%;" name="content" id="content"></textarea>       
            </td>
         </tr>
      </table>
      
      
      <!-- 답메일 쓰기 기능용 -->
      <input type="hidden" name="parentSeq" value="${requestScope.parentSeq}"/>
      <input type="hidden" name="groupno" value="${requestScope.groupno}"/>
      <input type="hidden" name="depthno" value="${requestScope.depthno}"/>
      
   </form>
</div>  
