<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String ctxPath = request.getContextPath(); %>
<meta charset="UTF-8">
<title>날씨</title>

<script defer src="https://use.fontawesome.com/releases/v5.15.2/js/all.js" integrity="sha384-vuFJ2JiSdUpXLKGK+tDteQZBqNlMwAjhZ3TvPaDfN9QmbPb7Q8qUpbSNapQev3YF" crossorigin="anonymous"></script>


<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<script type="text/javascript">

$(document).ready(function(){

/*
		let weatherIcon = { 
				'01' : 'fas fa-sun', 
				'02' : 'fas fa-cloud-sun',
				'03' : 'fas fa-cloud', 
				'04' : 'fas fa-cloud-meatball',
				'09' : 'fas fa-cloud-sun-rain', 
				'10' : 'fas fa-cloud-showers-heavy', 
				'11' : 'fas fa-poo-storm', '13' : 
					'far fa-snowflake', '50' : 'fas fa-smog' }; 
		
		$.ajax({ 
			    url:'http://api.openweathermap.org/data/2.5/weather?q=seoul&APPID=dc25fbf4150b73587bbf9b69171035b7&units=metric',
				dataType:'json',
				type:'GET', 
				success:function(data){ 
					var $Icon = (data.weather[0].icon).substr(0,2); 
					var $Temp = Math.floor(data.main.temp) + 'ºC'; 
					var $city = data.name; $('.CurrIcon').append('<i class="' + weatherIcon[$Icon] +'"></i>'); 
					$('.CurrTemp').prepend($Temp); 
					$('.City').append($city); 
					} 
		}) ;

*/
	var today = new Date();
	var year = today.getFullYear();
	var month = today.getMonth()+1;
	var day = today.getDate();
	var hour = today.getHours();
	var minutes = today.getMinutes();
	var arr =[];
	var harr = new Array('02','05','08','11','14','17','20','23');
	
	for(var i=0;i<harr.length;i++){
		var h=harr[i]-hour;
		console.log(h);
		if(h==-1 || h==0 || h==-2){
			var now=harr[i]+"00";
			console.log("지금"+now);
		}
		if(hour=='00'){
			var now=harr[7]+"00";
		}
	}
	
	if(hour<10){
		hour='0'+hour;
	}
	if(month<10){
		month='0'+month;
	}
	if(day<10){
		day='0'+day;
	}
	
	today = year+""+month+""+day;
	
	// 동네예보
	$.ajax({
        url : "<%= ctxPath%>/t1/weatherhome.tw",
        type : "get",
        timeout: 30000,
        contentType: "application/json",
        data: {"today":today, "now":now},
        dataType : "json",
        success : function(data, status, xhr) {

            let dataHeader = data.result.response.header.resultCode;
            
            if (dataHeader == "00") 
	    {
               console.log("success == >");
               console.log(data);
               var pop =  data.result.response.body.items.item[0].fcstValue;
               var pty=  data.result.response.body.items.item[1].fcstValue;
               var sky = data.result.response.body.items.item[3].fcstValue;
 
               
               $("#pop").html(pop);
               
               $("#pty").html(pty);
				
               if(sky==1){
            	   var image = "<%= ctxPath%>/resources/images/ody/pop1.png";
            	   $("#sky").html("<img src='"+ image +"'/>");
               }
    
               // TMN : 아침최저기온
               // TMX : 낮 최고기온
               // T3H: 3시간 기온
               // SKY: 하늘 상태  - 1: 맑음/ 3: 구름많음/ 4: 흐림
               // POP: 강수확률 
               // PTY: 강수 형태없음(0), 비(1), 비/눈(2), 눈(3), 소나기(4), 빗방울(5), 빗방울/눈날림(6), 눈날림(7)
				// 여기서 비/눈은 비와 눈이 섞여 오는 것을 의미 (진눈개비)
                //빗방울(5), 빗방울/눈날림(6), 눈날림(7) 


             //  $("div.CurrTemp").html(temp);
            }
            else 
	    {
               console.log("fail == >");
               console.log(data);               
            }
        },
        error : function(e, status, xhr, data) {
            console.log("error == >");
            console.log(e);
        }
    });
	
	
	// 한시간 받기
	$.ajax({
        url : "<%= ctxPath%>/t1/weathercurrent.tw",
        type : "get",
        timeout: 30000,
        contentType: "application/json",
        data: {"today":today, "now":now},
        dataType : "json",
        success : function(data, status, xhr) {

            let dataHeader = data.result.response.header.resultCode;
            
            if (dataHeader == "00") 
	    {
               console.log("success == >");
               console.log(data);
             
               var t1h = data.result.response.body.items.item[3].obsrValue;
     

               $("#t1h").html(t1h+"ºC");

    
               // TMN : 아침최저기온
               // TMX : 낮 최고기온
               // T3H: 3시간 기온
               // SKY: 하늘 상태  - 1: 맑음/ 3: 구름많음/ 4: 흐림
               // POP: 강수확률 
               // PTY: 강수 형태없음(0), 비(1), 비/눈(2), 눈(3), 소나기(4), 빗방울(5), 빗방울/눈날림(6), 눈날림(7)
				// 여기서 비/눈은 비와 눈이 섞여 오는 것을 의미 (진눈개비)
                //빗방울(5), 빗방울/눈날림(6), 눈날림(7) 


             //  $("div.CurrTemp").html(temp);
            }
            else 
	    {
               console.log("fail == >");
               console.log(data);               
            }
        },
        error : function(e, status, xhr, data) {
            console.log("error == >");
            console.log(e);
        }
    });
	
});



</script>


강수확률 <span id="pop"></span>%
강수형태 <span id="pty"></span>
하늘상태  <span id="sky"></span>
1시간 기온  <span id="t1h"></span>
