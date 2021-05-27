$(document).ready(function(){
	// postupload.jsp 게시판 글쓰기
      
      // === #167. 스마트 에디터 구현 시작 ===
       
       // === 스마트 에디터 구현 끝 ===
      
      // 쓰기버튼
      $("button#btnWrite").click(function(){
      
         // === 스마트 에디터 구현 시작 ===
          
         // === 스마트 에디터 구현 끝 ===
         
          // 글제목 유효성 검사
         var subjectVal = $("input#subject").val().trim();
         if(subjectVal == "") {
            alert("글제목을 입력하세요");
            return;
         }
         
         // 글내용 유효성 검사(스마트에디터 사용 안 할시)
         var contentVal = $("textarea#content").val().trim();
         if(contentVal == "") {
            alert("글내용을 입력하세요");
            return;
         }
        
         
         // === 스마트에디터 구현 시작 ===
         //스마트에디터 사용시 무의미하게 생기는 p태그 제거
          
              
           
           
           // 스마트에디터 사용시 무의미하게 생기는 p태그 제거하기전에 먼저 유효성 검사를 하도록 한다.
           // 글내용 유효성 검사 
           
           
           // 스마트에디터 사용시 무의미하게 생기는 p태그 제거하기
           
       
          
        
        
       // === 스마트에디터 구현 끝 ===
         
         // 글암호 유효성 검사
         var pwVal = $("input#pw").val().trim();
         if(pwVal == "") {
            alert("비밀번호를 입력하세요");
            return;
         }
         
         // 폼(form) 을 전송(submit)
         var frm = document.postFrm;
         frm.method = "POST";
         frm.action = "uploadComplete.tw";
         frm.submit();   
      });
           
   });// end of $(document).ready(function(){})----------------