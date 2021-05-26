// 상단메뉴
$(document).ready(function(){
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
});

// 사이드바 
$(document).ready(function(){
	$("div#contentLeft").hide();	
	
	$("p.menu-btn").click(function(){
		$(this).css("border-left","solid 3px #0071bd");
		$("p.menu-btn").not($(this)).css("border-left","solid 3px #333333");
		showSubmenu($(this).index());
	});
	
	
	$("i.closebtn").click(function(){
		$("p.menu-btn").css("border-left","solid 3px #333333");
		$("div#contentLeft").animate({
	        width: "hide"
	    }, 100, "linear");
	});
});
	
function showSubmenu(thismenu){
	var submenuIndex = $("div#contentLeft").children().eq(thismenu).index();
	$("div#contentLeft").animate({
	        width: "show"
	 }, 100, "linear");
	$("div#contentLeft").children().hide();
	$("div#contentLeft").children().eq(submenuIndex).show();
}