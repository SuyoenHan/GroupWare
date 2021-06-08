<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<meta charset="UTF-8">
<title>T1Works Talk</title>

<style type="text/css">

html{
	background-color: #9bbad8;
}
input.btn_normal{
	background-color: #0071bd;
	border: none;
	color: white;
	width: 60px;
	height: 30px;
	font-size: 12pt;
	padding: 3px 0px;
	margin-top: 3px;
}


  
div#sendmsg{
	position: fixed; bottom: 10px; width: 100%;

}
</style>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<script type="text/javascript">
	
	$(document).ready(function(){
		
		
		var url = window.location.host; // 웹브라우저의 주소창의 포트까지 가져옴
		// alert("url : "+url);
			// 결과값 url : 192.168.219.104:9090
			
			var pathname = window.location.pathname; // '/'부터 오른쪽에 있는 모든 경로
	    //  alert("pathname : " + pathname);
	    //  결과값  pathname : /board/chatting/multichat.action
			
			var appCtx = pathname.substring(0, pathname.lastIndexOf("/"));  // "전체 문자열".lastIndexOf("검사할 문자");   
	    //  alert("appCtx : " + appCtx);
	    //  결과값  appCtx : /board/chatting
	    
	    	var root = url+appCtx;
	    //   alert("root : " + root);
	    //  결과값   root : 192.168.219.104:9090/board/chatting
	    
	    	var wsUrl = "ws://"+root+"/chatwithStart.tw";  
	     // 웹소켓통신을 하기위해서는 http:// 을 사용하는 것이 아니라 ws:// 을 사용해야 한다. 
	     // /multichatstart.action은 컨트롤러가 아닌 xml에서 작성해야함
	     
	      	var websocket = new WebSocket(wsUrl);       
	        // === 웹소켓에 최초로 연결이 되었을 경우에 실행되어지는 콜백함수 정의하기 ===  
	        websocket.onopen = function(){
	      	// alert("웹소켓 연결됨!!"); 
	      	 $("div#chatStatus").text("정보 : 웹소켓에 연결이 성공됨!!");
	        
	      	 messageObj = { message : ""
	               			, type : "one"     // all은 전체, one은 한사람애개먼
	              			 , to : "all" };  // 자바스크립트에서 객체의 데이터값 초기화  
	      	 
	              			 
	            websocket.send(JSON.stringify(messageObj));
	  	          // JSON.stringify(자바객체) 는 자바객체를 JSON 표기법의 문자열(String)로 변환한다
	  	          /*
	  	             JSON.stringify({});                  // '{}'
	  		         JSON.stringify(true);                // 'true'
	  		         JSON.stringify('foo');               // '"foo"'
	  		         JSON.stringify([1, 'false', false]); // '[1,"false",false]'
	  		         JSON.stringify({ x: 5 });            // '{"x":5}'
	  	          */ 
	        };
	       if($("input#toname").val() != $("span.loginuserid").text()){

	       }
	        
	        if($("span.ip").text()!=""){
	        	$("input#to").val($("span.ip").text());
	        }
	        // === 메시지 수신시 콜백함수 정의하기 === 
	        websocket.onmessage = function(event) {
	                                 // 자음 ㄴ 임
	          if(event.data.substr(0,1)=="「" && event.data.substr(event.data.length-1)=="」") { 
	             $("div#connectingUserList").html(event.data);
	          }
	          else {
	               $("div#chatMessage").append(event.data);
	               $("div#chatMessage").append("<br/>");
	               $("div#chatMessage").scrollTop(99999999);
	          }
	        };
	        
	        
	     // === 웹소캣 연결 해제시 콜백함수 정의하기 === 
	        websocket.onclose = function() {
	            // websocket.send("채팅을 종료합니다.");
	        }
	        
	     // 메시지 입력후 엔터하기 
	        $("input#message").keyup(function (key) {
	             if (key.keyCode == 13) {
	                $("input#btnSendMessage").click();
	             }
	          }); 
	     
	     
	     // 메시지 보내기
	        $("input#btnSendMessage").click(function() {
	            if( $("input#message").val() != "") {
	                
	               // ==== 자바스크립트에서 replace를 replaceAll 처럼 사용하기 ====
	                // 자바스크립트에서 replaceAll 은 없다.
	                // 정규식을 이용하여 대상 문자열에서 모든 부분을 수정해 줄 수 있다.
	                // 수정할 부분의 앞뒤에 슬래시를 하고 뒤에 gi 를 붙이면 replaceAll 과 같은 결과를 볼 수 있다.
	                var messageVal = $("input#message").val();
	                messageVal = messageVal.replace(/<script/gi, "&lt;script"); 
	                // 스크립트 공격을 막으려고 한 것임.
	               
	                messageObj = {};
	                messageObj.message = messageVal;
	                messageObj.type = "all";
	                messageObj.to = "all";
	                 
	                var to = $("input#to").val();
	              
	                if ( to != "" ) {
	                    messageObj.type = "one";
	                    messageObj.to = to;
	                }
	                
	                var d = new Date();
	                websocket.send(JSON.stringify(messageObj));
	                // JSON.stringify() 는 값을 그 값을 나타내는 JSON 표기법의 문자열로 변환한다
	                
	                $("div#chatMessage").append("<div style='float: right;'><span style='font-size: 10pt;'>"+d.getHours() + "시" + d.getMinutes() +"분</span>&nbsp;<span style='background-color: yellow; padding: 3px 2px;'>" + messageVal + "</span></div><br/><br/>");
	                $("div#chatMessage").scrollTop(99999999); // 스크롤이 맨위로 간다
	                 
	                $("input#message").val("");
	            }
	        });
	        
		
		
		
	});

</script>





<div id="body">
 
  <div id="name"></div>	
 
  <div id="chatMessage" style="overFlow: auto; max-height: 550px;"></div>
  
  <div id="sendmsg">
	<input type="text" id="message" size="54" style="height: 35px;"  placeholder="메시지 내용"/>
    <input type="button" id="btnSendMessage" class="btn_normal" value="보내기" />
  </div>
</div>
<input type="hidden" id="to" />
<input type="hidden" id="toname" value="${empId}"/>