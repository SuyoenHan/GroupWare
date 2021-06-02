<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String ctxPath= request.getContextPath(); %>  

<style type="text/css">

   div#title {
      font-size: 25pt;
      border: solid 0px red;
      padding: 12px 0;
      text-align: center;
      width:95%;
      margin: 10px 0px 30px 0px;
      font-weight: bold;
      color: #0071bd;
   }
   
   div.mycontent {
        width: 200px;
        padding: 5px 3px;
     }
     
    div.mycontent>.title {
       font-size: 12pt;
       font-weight: bold;
       background-color:  #0071bd;
       color: #fff;
       text-align: center;
    }
    
    div.mycontent>.title>a {
       text-decoration: none;
       color: #fff;
    }
    
    
    div.mycontent>.desc {
     /* border: solid 1px red; */
       padding: 10px 0 0 0;
       color: #000;
       font-weight: normal;
       font-size: 9pt;
    }
    
    div.mycontent img {
       width: 150px;
       height: 70px;
       margin-left:22px;
    }
    
    div.mycontent span{
   	margin-left:22px;
    }

	div#goHome span{
		cursor: pointer;
		color: #9e9e9e;
		font-size:15pt;
	}
	
	div#goHome span:hover{
		color: #333;
		font-weight: bolder;
	}
	
	div#goHome{
		width:95%;
		text-align: center;
		margin: 50px 0px 50px 0px;
	}
	
</style>


<div id="content" style="clear: both;">
	<div id="title" style="padding-top:50px;">티원투어 지점 위치 안내</div>
	<div id="map" style="width:60%; height:500px; margin-left:270px; margin-bottom: 20px;"></div>
	<div id="goHome"><span onclick="location.href='<%=ctxPath%>/t1/travelAgency.tw'">홈페이지로 이동</span></div>
</div>

<%-- 카카오맵 스크립트 --%>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=969f39a6821f4d401c06072d53bf1845"></script>
<script type="text/javascript">

	$(document).ready(function(){
		
		// 지도를 담을 영역의 DOM(document object model) 레퍼런스
		var mapContainer= document.getElementById('map');
		
		// 지도를 생성할때 필요한 기본 옵션
		var options= {
			<%-- center값  : 위도(latitude), 경도(longitude)이다. --%>
			center: new kakao.maps.LatLng(37.52288807751557, 126.96791088797227), 
			level: 8 <%-- 지도의 레벨(확대, 축소 정도) --%>
		};
		
		// 지도 생성 및 생성된 지도객체 리턴
		var mapobj = new kakao.maps.Map(mapContainer, options);
		
		// 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성함.    
		var mapTypeControl = new kakao.maps.MapTypeControl();
		
		// 지도 타입 컨트롤을 지도에 표시함 (오른쪽위)
	    mapobj.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);
		
	    // 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성함.   
	    var zoomControl = new kakao.maps.ZoomControl();

	    // 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 지도에 표시함 (오른쪽)
	    mapobj.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
		
	     if (navigator.geolocation) { // HTML5의 geolocation으로 사용할 수 있는 경우 
	         
	         // 웹페이지에 접속한 사용자의 현재 위치를 지도의 중앙에 위치시킴
	         navigator.geolocation.getCurrentPosition(function(position) {
	        	 var latitude = position.coords.latitude;   // 현위치의 위도
	             var longitude = position.coords.longitude; // 현위치의 경도

	             // 마커가 표시될 위치를 geolocation으로 얻어온 현위치의 위.경도 좌표로 함   
	             var locPosition = new kakao.maps.LatLng(latitude, longitude);
	        	
	             // 마커이미지를 기본이미지를 사용하지 않고 다른 이미지로 사용할 경우의 이미지 주소 
	             var imageSrc = '<%=ctxPath%>/resources/images/mapHsy/pointerBlue.png'; 
	          
	             // 마커이미지의 크기 
	             var imageSize = new kakao.maps.Size(34, 39);
	             
	             // 마커이미지의 옵션. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정한다. 
	             var imageOption = {offset: new kakao.maps.Point(15, 39)};
	          
	             // 마커의 이미지정보를 가지고 있는 마커이미지를 생성한다. 
	             var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);
	          
	          	// == 마커 생성하기 == //
	             var marker = new kakao.maps.Marker({ 
	                  map: mapobj, 
	                  position: locPosition, // locPosition 좌표에 마커를 생성 
	                  image: markerImage     // 마커이미지 설정
	             }); 
	          	
	             marker.setMap(mapobj); // 지도에 마커를 표시
	             
	             // === 인포윈도우(텍스트를 올릴 수 있는 말풍선 모양의 이미지) 생성하기 === //
	             // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능함.
	             var iwContent = "<div style='padding:5px; font-size:9pt; margin-left:27px;'>나의 현재위치<br/><a href='https://map.kakao.com/link/map/현위치(약간틀림),"+latitude+","+longitude+"' style='color:blue;' target='_blank'>큰지도</a> <a href='https://map.kakao.com/link/to/현위치(약간틀림),"+latitude+","+longitude+"' style='color:blue' target='_blank'>길찾기</a></div>";
	             
	             // 인포윈도우 표시 위치 (마커위의 내용물)
	             var iwPosition = locPosition;
	             
	             // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됨
	             var iwRemoveable = true; 
	             
	          	 // == 인포윈도우를 생성하기 == 
	             var infowindow = new kakao.maps.InfoWindow({
	                 position : iwPosition, 
	                 content : iwContent,
	                 removable : iwRemoveable
	             });
	             
	             // == 마커 위에 인포윈도우를 표시하기 == //
	             infowindow.open(mapobj, marker);
	             
	             // == 지도의 센터위치를 locPosition로 변경한다.(사이트에 접속한 클라이언트 컴퓨터의 현재의 위.경도로 변경)
	             // mapobj.setCenter(locPosition);
	         });
	         
	     }
	     else{ // HTML5의 geolocation으로 사용할 수 없는 경우
	   
	         var locPosition = new kakao.maps.LatLng(37.556513150417395, 126.91951995383943);     
	         
	        // 지도의 센터위치를 위에서 정적으로 입력한 위.경도로 변경한다.
	          mapobj.setCenter(locPosition);
	         
	     } // end of if-else ------------------------------------ 
	     
	     
	     // ================== 지도에 클릭 이벤트를 등록하기 시작======================= //
	     // 지도를 클릭하면 클릭한 위치에 마커를 표시하면서 위,경도를 보여주도록 한다.
	     
	     // == 마커 생성하기 == //
	     // 1. 마커이미지 변경
	     var imageSrc = 'http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_red.png';       
	             
	     // 2. 마커이미지의 크기 
	      var imageSize = new kakao.maps.Size(34, 39);   
	              
	      // 3. 마커이미지의 옵션. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정한다. 
	      var imageOption = {offset: new kakao.maps.Point(15, 39)};   
	        
	      // 4. 이미지정보를 가지고 있는 마커이미지를 생성한다. 
	      var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);
	            
	      var movingMarker = new kakao.maps.Marker({ 
	      	  map: mapobj, 
	          image: markerImage  // 마커이미지 설정
	     });
	      
	   	  // === 인포윈도우(텍스트를 올릴 수 있는 말풍선 모양의 이미지) 생성하기 === //
	      var movingInfowindow = new kakao.maps.InfoWindow({
	          removable : false
	        //removable : true   // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됨
	      }); 
	      
	      kakao.maps.event.addListener(mapobj, 'click', function(mouseEvent) {
	    	  
	    	  // 클릭한 위도, 경도 정보를 가져온다
	          var latlng = mouseEvent.latLng;
	    	  
	          // 마커 위치를 클릭한 위치로 옮긴다.
	          movingMarker.setPosition(latlng);
	    	  
	          // 인포윈도우의 내용물 변경하기 
	          movingInfowindow.setContent("<div style='padding:5px; font-size:9pt; margin-left:27px;' >선택하신   위치<br/><a href='https://map.kakao.com/link/map/여기,"+latlng.getLat()+","+latlng.getLng()+"' style='color:blue;' target='_blank'>큰지도</a> <a href='https://map.kakao.com/link/to/여기,"+latlng.getLat()+","+latlng.getLng()+"' style='color:blue' target='_blank'>길찾기</a></div>");
	          
	          // == 마커 위에 인포윈도우를 표시하기 == //
	          movingInfowindow.open(mapobj, movingMarker);
	          
	          /*
	              var htmlMessage = '클릭한 위치의 위도는 ' + latlng.getLat() + ' 이고, '; 
	              htmlMessage += '경도는 ' + latlng.getLng() + ' 입니다';
	          
	              var resultDiv = document.getElementById("latlngResult"); 
	              resultDiv.innerHTML = htmlMessage;
	          */
	      }); 
	    // ================== 지도에 클릭 이벤트를 등록하기 끝======================= //
	      
	    
	   // ================== 지도에 매장위치 마커 보여주기 시작 ====================== //
	  
	   // 매장 마커를 표시할 위치와 내용을 가지고 있는 객체 배열 
   	   var positionArr = [];
	   
	   $.ajax({
		  url:"<%=ctxPath%>/t1/travel/twLocationAjax.tw", 
		  async:false, // 지도는 비동기 통신이 아닌 동기 통신으로 해야 함
		  dataType:"json",
		  success:function(json){
			  $.each(json, function(index, item){
		            var position = {};
		            
		            position.content = "<div class='mycontent'>"+ 
		                               "  <div class='title'>"+ 
		                               "    <strong>"+item.storeName+"</strong></a>"+  
		                               "  </div>"+
		                               "  <div class='desc'>"+ 
		                               "    <img src='<%=ctxPath%>/resources/images/mapHsy/"+item.storeImg+"'><br>"+  
		                               "    <span class='address' style='display:inline-block; width:150px;'>"+item.storeAddress+"</span>"+ 
		                               "  </div>"+ 
		                               "</div>";
		                             
		             position.latlng = new kakao.maps.LatLng(item.lat, item.lng);
		             position.zIndex = item.zindex;
		           
		             positionArr.push(position);
		         });
		  },
		  error: function(request, status, error){
		         alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
		  }
	   });
	   
	   
	   // infowindowArr 은 인포윈도우를 가지고 있는 객체 배열의 용도이다. 
	   var infowindowArr = new Array();
	   
	  // === 객체 배열 만큼 마커 및 인포윈도우를 생성하여 지도위에 표시한다. === //
	   for(var i=0; i<positionArr.length; i++) {
	      
	      // == 마커 생성하기 == //
	      var marker = new kakao.maps.Marker({
	         map: mapobj,
	         position: positionArr[i].latlng
	      });
	      
	      // 지도에 마커를 표시
	      marker.setMap(mapobj);
	      
	      // == 인포윈도우를 생성하기 == 
	      var infowindow = new kakao.maps.InfoWindow({
	         content: positionArr[i].content,
	         removable: true,
	         zIndex : i+1
	      });
	      
	      // 인포윈도우를 가지고 있는 객체배열에 넣기 
	      infowindowArr.push(infowindow);
	      
	      // == 마커 위에 인포윈도우를 표시하기 == //
	      // infowindow.open(mapobj, marker);
	      
	      // == 마커 위에 인포윈도우를 표시하기 == //
	      // 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
	      // 이벤트 리스너로는 클로저(closure => 함수 내에서 함수를 정의하고 사용하도록 만든것)를 만들어 등록합니다 
	      // for문에서 클로저(closure => 함수 내에서 함수를 정의하고 사용하도록 만든것)를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
	      kakao.maps.event.addListener(marker, 'mouseover', makeOverListener(mapobj, marker, infowindow, infowindowArr));
	      
	   }// end of for----------------------------------------------
	   
	   // ================== 지도에 매장위치 마커 보여주기 끝 ====================== //  
	   
	   
	   // !! 인포윈도우를 표시하는 클로저(closure => 함수 내에서 함수를 정의하고 사용하도록 만든것)를 만드는 함수(카카오에서 제공해준것) // 
	  function makeOverListener(mapobj, marker, infowindow, infowindowArr) {
	       return function() {
	          // alert("infowindow.getZIndex()-1:"+ (infowindow.getZIndex()-1));
	          
	          for(var i=0; i<infowindowArr.length; i++) {
	             if(i == infowindow.getZIndex()-1) {
	                infowindowArr[i].open(mapobj, marker);
	             }
	             else{
	                infowindowArr[i].close();
	             }
	          }
	       };
	   }
	   
	   
	}); // end of $(document).ready(function(){---------------------

</script>
	