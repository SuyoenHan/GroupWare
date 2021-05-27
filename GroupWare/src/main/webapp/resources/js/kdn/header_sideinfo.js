$(document).ready(function(){
	// 상단메뉴
	$("td.home").click(function(){
		location.href="home.tw";
	});
	$("td.mypage").click(function(){
		location.href="mypage.tw";
	});
	$("td.schedule").click(function(){
		location.href="schedule.tw";
	});
	$("td.notice").click(function(){
		location.href="employeeBoard.tw";
	});
	$("td.rsv").click(function(){
		location.href="travelschedule.tw";
	});
	
	// 사이드바 
	$("div#contentLeft").hide();	
	
	var menuClicked = false;	
	var contentWidth = $("div#gwContent").css('width');
	$("p.menu-btn").click(function(){
		$(this).css("border-left","solid 3px #0071bd");
		$("p.menu-btn").not($(this)).css("border-left","solid 3px #333333");
		showSubmenu($(this).index());
		$("div#sideinfo-content-container").addClass('content-open-style');
		
		/*
		contentWidth = $("div#sideinfo-content-container").css('width', '118.5em');
		contentMarginLeft = $("div#sideinfo-content-container").css('margin-left', '265px');
		$("div#sideinfo-content-container").css('width',contentWidth);
		$("div#sideinfo-content-container").css('margin-left',contentMarginLeft); */
	});
	
	$("i.closebtn").click(function(){
		$("p.menu-btn").css("border-left","solid 3px #333333");
		$("div#contentLeft").animate({
	        width: "hide"
	    }, 100, "linear");
		$("div#sideinfo-content-container").removeClass('content-open-style');
	});
	
	// content
	if(menuClicked){
	}	
	
});

function showSubmenu(thismenu){
	var submenuIndex = $("div#contentLeft").children().eq(thismenu).index();
	$("div#contentLeft").animate({
	        width: "show"
	 }, 100, "linear");
	$("div#contentLeft").children().hide();
	$("div#contentLeft").children().eq(submenuIndex).show();
}

// content


