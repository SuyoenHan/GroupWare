<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String ctxPath = request.getContextPath(); %>
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<script type="text/javascript">

$(document).ready(function(){
	 
	
    var todayw = new Date();
    var yesterday = new Date((new Date()).valueOf() - 1000*60*60*24);
    console.log("어제:"+yesterday);
    
    // 오늘 정보
	var wyear = todayw.getFullYear();
	var wmonth = todayw.getMonth()+1;
	var wday = todayw.getDate();
	var whour = todayw.getHours();
	var wminutes = todayw.getMinutes();
	
	// 어제 정보
	var ydyear = yesterday.getFullYear();
	var ydmonth = yesterday.getMonth()+1;
	var ydday = yesterday.getDate();
	var yminutes = yesterday.getMinutes();
	
	if(whour<10){
		whour='0'+whour;
	}
	if(wmonth<10){
		wmonth='0'+wmonth;
	}
	if(wday<10){
		wday='0'+wday;
	}

	if(ydmonth<10){
		ydmonth='0'+ydmonth;
	}
	if(ydday<10){
		ydday='0'+ydday;
	}
	
	var arr =[];
	var harr = new Array('02','05','08','11','14','17','20','23');
	console.log("현재시간"+whour);
	// sky,pop,pty 정보
	for(var i=0;i<harr.length;i++){
		var h=harr[i]-whour;
		console.log("지금h"+h);
	//	console.log(h);
		if(whour!='00' && whour!='01'){
    		if(h==-1 || h==0 || h==-2){
    			var wnow=harr[i]+"00"; 			
    			wtoday = wyear+""+wmonth+""+wday;
    //			console.log("지금"+wnow);
    		}
		}
		else if(whour=='00' || h>0){
		//	alert("현재시간");
			wtoday = ydyear+""+ydmonth+""+ydday;
			console.log("밤12시"+wtoday);
			var wnow=harr[7]+"00";
			console.log("12시체크"+wnow);
		}
	}
	
	// 오늘 t1hh==0 ||
	for(var i=0;i<harr.length;i++){
		var hc=harr[i]-whour;
	//	console.log("지금h"+h);
	//	console.log(h);
		if(whour!='00' && whour!='01'){
    		if(hc==-1 || hc==-2){
    			var wcnow=harr[i]+"00";
    			wctoday = wyear+""+wmonth+""+wday;
    		//	console.log("지금"+wnow);
    		}
    		else if(hc==0){
    			
	    		if(wminutes<30){
	    			if( whour=='02'){
	    			var wcnow=harr[7]+"00";
	    			wctoday = ydyear+""+ydmonth+""+ydday;
	    		//	console.log("지금"+wcnow);
	    			}
	    			else{
	    				var wcnow=harr[i-1]+"00";
	    				wctoday = wyear+""+wmonth+""+wday;
		    		//	console.log("지금오늘1th"+wcnow);
	    			}
	    		}
	    		else{
	    			var wcnow=harr[i]+"00";
	    			wctoday = wyear+""+wmonth+""+wday;
	    		}
    		}
		}
		else if(whour=='00' || hc==1 || whour=='01'){
			wctoday = ydyear+""+ydmonth+""+ydday;
			var wcnow=harr[7]+"00";
		}
	}
	
	
	// 어제 t1h 정보
	for(var i=0;i<harr.length;i++){
		var hy=harr[i]-whour;
		console.log("hy"+hy);
		if(whour!='00' && whour!='01'){
    		if(hy==-1 ||  hy==-2){
    			yesterday = ydyear+""+ydmonth+""+ydday;
    			var ytime=harr[(i+1)]+"00";
    		//	console.log("시간"+ytime);
    		}
    		else if(hy==0){
    			if(yminutes<=60){
	    			yesterday = ydyear+""+ydmonth+""+ydday;
	    			var ytime=harr[(i)]+"59";
	    		//	console.log("시간hy0"+ytime);
    			}
    			else{
    				yesterday = ydyear+""+ydmonth+""+ydday;
	    			var ytime=harr[(i+1)]+"00";
	    		//	console.log("시간hy0"+ytime);
    			}
    		}
		}	
		else if(whour=='01' || whour=='00'|| hy>0){
			var ytime=harr[0]+"00";
			yesterday = ydyear+""+ydmonth+""+ydday;
		//	console.log("시간00"+ytime);
		}
	}
	
	console.log("진짜어제:"+yesterday);
	
	// 동네예보
	$.ajax({
        url : "<%= ctxPath%>/t1/weatherhome.tw",
        type : "get",
        timeout: 30000,
        contentType: "application/json",
        data: {"today":wtoday, "now":wnow},
        dataType : "json",
        success : function(data, status, xhr) {
            let dataHeader = data.result.response.header.resultCode;
            let data1 = data.result.response.body.items.item;
            
            if (dataHeader == "00") {
            	for (var i = 0; i < data1.length; i++) {
                    var item = data1[i];
                    switch (item.category) {
                        
                        case "POP":
                        	var pop =parseInt(item.fcstValue);
                            break;
                    }
            	}
 			

               $("#pop").html("강수확률 : "+pop+"%");
               
               $("#pty").html(pty);
			
            }
           
        },
        error : function(e, status, xhr, data) {
            console.log("error == >");
            console.log(e);
        }
    });
	
	
	// 오늘 한시간 받기
	$.ajax({
        url : "<%= ctxPath%>/t1/weathercurrent.tw",
        type : "get",
        timeout: 30000,
        contentType: "application/json",
        data: {"today":wctoday, "now":wcnow},
        dataType : "json",
        success : function(data, status, xhr) {

            let dataHeader = data.result.response.header.resultCode;
            
            if (dataHeader == "00") {

            	var t1h = parseFloat(data.result.response.body.items.item[3].obsrValue);
    
               $("#t1h").html(t1h);
               $("input#mt1h").val(t1h);
            }
            
         // 오늘 기온과 어제 기온 차이
        	$.ajax({
                url : "<%= ctxPath%>/t1/weatheryesterday.tw",
                type : "get",
                timeout: 30000,
                contentType: "application/json",
                data: {"yesterday":yesterday, "ytime":ytime},
                dataType : "json",
                success : function(data, status, xhr) {

                    let dataHeader = data.result.response.header.resultCode;
                    
                    if (dataHeader == "00") {

                       var y1h =parseFloat(data.result.response.body.items.item[3].obsrValue);
            			console.log("어제온도:"+y1h);	
    					var gap = parseFloat(t1h-y1h).toFixed(2);
    	            	if(gap>=0){
    	        			$("#gap").html("어제보다 "+Math.abs(parseFloat(gap))+" º 높아요!"); 
    	        		}
    	        		else{
    	        			$("#gap").html("어제보다 "+Math.abs(parseFloat(gap))+" º 낮아요!"); 
    	        		}
    	        		
                    }
                },
                error : function(e, status, xhr, data) {
                    console.log("error == >");
                    console.log(e);
                }
            });
        	
        },
        error : function(e, status, xhr, data) {
            console.log("error == >");
            console.log(e);
        }
    });
    
	
	$.ajax({
		  type: "GET",
		  url: "http://openapi.seoul.go.kr:8088/504543517764617936396d50484259/json/RealtimeCityAir/1/99",
		  data: {},
		  success: function(response){
				// 마포구의 미세먼지 값만 가져와보기
				let mapo = response["RealtimeCityAir"]["row"][5];
				let guName = mapo['MSRSTE_NM'];
				let guMise = mapo['PM10'];
				let guchoMise = mapo['PM25'];
				console.log(guName, guMise,guchoMise);
				
				var miseStatus="";
				if(guMise<=30){
					miseStatus="좋음" 
				}
				else if(guMise<=80){
					miseStatus="보통"
				}
				else if(guMise<=150){
					miseStatus="나쁨"
				}
				else{
					miseStatus="매우나쁨"
				}
				
				$("#miseStatus").html("미세먼지 : "+miseStatus);
				$("#mise").html("("+guMise+"㎍/m³)");
				
				var miseimage="";
				var chomiseStatus="";
				if(guchoMise<=15){
					chomiseStatus="좋음" 
					
				}
				else if(guchoMise<=35){
					chomiseStatus="보통"
					
				}
				else if(guchoMise<=75){
					chomiseStatus="나쁨"
					
				}
				else{
					chomiseStatus="매우나쁨"
					
				}
	
				if(guMise<=30 && guchoMise<=15){
					miseimage = "<%= ctxPath%>/resources/images/ody/good.png";
				}
				else if((guMise>30 && guMise<=80) || (guchoMise >15 && guchoMise<=35)){
					miseimage = "<%= ctxPath%>/resources/images/ody/normal.png";
				}
				else if((guMise>80 && guMise<=150) || (guchoMise >35 && guchoMise<=75)){
					miseimage = "<%= ctxPath%>/resources/images/ody/sad.png";
				}
				else if(guMise>150 || guchoMise>75 ){
					miseimage = "<%= ctxPath%>/resources/images/ody/devil.png";
				}
				
				$("#chomiseStatus").html("초미세먼지 : "+chomiseStatus);
				$("#chomise").html("("+guchoMise+"㎍/m³)");
				$("#miseimg").html("<img style='width: 100px; height: 100px;' src='"+ miseimage +"'/>");
		  }
		})
}); // end of 

// ------ 기상청 날씨정보 공공API XML데이터 호출하기 -------- //
<%--
function showWeather(){
	   $.ajax({
		  url:"<%= request.getContextPath()%>/opendata/weatherXML.action",
		  type: "GET",
		  dataType: "XML",
		  success: function(xml){
			  var rootElement = $(xml).find(":root");
			  
		      var localArr = $(rootElement).find("local");
		  	   console.log("지역개수:"+ localArr.length);
		  //	   지역개수: 95
		      var html = "발표시각 : <span style='font-weight:bold;'>"+updateTime+"</span>&nbsp;";
			              html += "<span style='color:blue; cursor:pointer; font-size:9pt;' onClick='javascript:showWeather();'>업데이트</span><br/>";
			              html += "<table class='table table-hover' align='center'>";
			              html += "<tr>";
			              html += "<th>지역</th>";
			              html += "<th>날씨</th>";
			              html += "<th>기온</th>";
			              html += "</tr>";
			              
			       
			            
			              for(var i=0;i<localArr.length;i++){
			            	  var local = $(localArr).eq(i);
			            	  /* .eq(index) 는 선택된 요소들을 인덱스 번호로 찾을 수 있는 선택자이다. 
					                           마치 배열의 인덱스(index)로 값(value)를 찾는 것과 같은 효과를 낸다.
					          */
					          console.log($(local).text()+" stn_id:"+$(local).attr("stn_id")+" icon:"+$(local).attr("icon")+" desc: "+$(local).attr("desc")+" ta"+$(local).attr("ta"));
					          
					          /*
					          	밀양 stn_id:288 icon:04 desc: 흐림 ta24.8
								산청 stn_id:289 icon:03 desc: 구름많음 ta22.9
								거제 stn_id:294 icon:04 desc: 흐림 ta24.1
								남해 stn_id:295 icon:03 desc: 구름많음 ta21.7
								춘천 stn_id:101 icon: desc: - ta23.8
					          */
					          var icon = $(local).attr("icon");
					          if(icon ==""){
					        	  icon="없음";
					          }
					          
					          html += "<tr>";
					          html += "<td>"+$(local).text()+"</td><td><img src='/board/resources/images/weather/"+icon+".png'/>"+$(local).attr("desc")+"</td><td>"+$(local).attr("ta")+"</td>";
					          html += "</tr>";
					          
					      
			              } // end of for-------------------------
			              
			              
			              html += "</table>";
		            	  $("div#displayWeather").html(html);
			              
		            	
         
         ///////////////////////////////////////////////////                 
		                                    
		  }, // end of success: function(xml){}----------------
		  error: function(request, status, error){
	            alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
	      }
	   });--%>
} // end of function showWeather(){}----------




</script>
<div id="today-w" style="float: left; width: 50%; height: 100%; border-right: solid 1px blue;">
 	<span style="font-weight: bold;">오늘의 날씨</span><br><br>
 	<span  id="sky"></span>
 	<div style="padding-top: 30px; display: inline-block; float: right; margin-right: 20px;"><span id="t1h" style="font-size: 25pt; font-weight: bold; margin-left: 10px; "></span><span style="font-size: 25pt; font-weight: bold; ">ºC</span><br>
 	<span style="margin-left: 10px;" id="pop"></span><br><span id="gap"></span></div>
 	</div>
<div style="float: right; width: 50%; height: 100%; " >
 		<span style="text-align: left; font-weight: bold;">미세먼지 농도</span><br>
 		<div align="center" style="margin-top: 20px;">
 		<span id="miseimg"></span><br><br>
 		<span id="miseStatus" ></span><span id="mise"></span><br>
 		<span id="chomiseStatus"></span><span id="chomise"></span>
 		</div>
 	</div>