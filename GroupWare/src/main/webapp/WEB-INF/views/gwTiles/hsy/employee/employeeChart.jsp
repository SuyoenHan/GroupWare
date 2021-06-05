<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String ctxPath= request.getContextPath(); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style type="text/css">

	@import 'https://code.highcharts.com/css/highcharts.css';
	#container {
	  min-width: 300px;
	  margin: 1em auto;
	 /* border: 1px solid silver;*/
	}
	
	#container h4 {
	  text-transform: none;
	  font-size: 14px;
	  font-weight: normal;
	}
	
	#container p {
	  font-size: 13px;
	  line-height: 16px;
	}
	
	h4 {
	  font-size: 9.5px !important;
	}
	
	@media (min-width: 576px) {
	  h4 {
	    font-size: 12px !important;
	  }
	}
	
	@media (min-width: 768px) {
	  h4 {
	    font-size: 12.5px !important;
	  }
	}
	
	@media (min-width: 992px) {
	  h4 {
	    font-size: 15px !important;
	  }
	}
	
	@media (min-width: 1200px) {
	  h4 {
	    font-size: 18px !important;
	  }
	
	div#employeeChartBox{
		width: 1400px; 
		height: 750px;
		border: solid 0px red; 
		overflow: hidden;
		position: relative;
		left: 100px;
		top: 50px;
	}
	
	div.employeeInfo{
		border: solid 0px red;
		font-size: 13pt;
	}
	
	div#orgList{
		border: solid 2px #d9d9de;
		padding: 30px 50px 15px 50px;
	}
	
	div.clickedTarget{
		color: #cc0000;
		font-style: italic;
		font-weight: bold;
	}
	
</style>

<div id="employeeChartBox">
	<div id="employeeChartContainer" style="width: 60%; float:left; border: solid 0px blue;"></div>
	<div style="float: right; border: solid 0px blue; padding-top: 15px;">
		<div id="orgList">
			<c:forEach var="mvo" items="${mvoList}">
				<c:if test="${empty mvo.dname}"> <%-- 사장인 경우 --%>
					<div class="employeeInfo ${mvo.dname} ${mvo.pname}">${mvo.name}&nbsp;${mvo.pname}&nbsp;(&nbsp;${mvo.cmobile}&nbsp;)</div>
				</c:if>
				<c:if test="${not empty mvo.dname and mvo.fk_pcode eq '2' }"> <%-- 부장인 경우 --%>
					<div class="employeeInfo ${mvo.dname} ${mvo.dname}${mvo.pname}" style="margin-top:10px;"><span style='padding-left:${(mvo.fk_pcode)*10}px; font-weight: bold;'>└</span>&nbsp;${mvo.name}&nbsp;${mvo.pname}&nbsp;(&nbsp;${mvo.cmobile}&nbsp;)</div>
				</c:if>
				<c:if test="${not empty mvo.dname and mvo.fk_pcode ne '2' }"> <%-- 대리/사원인 경우 --%>
					<div class="employeeInfo ${mvo.dname} ${mvo.dname}${mvo.pname}" style="padding-top:4px;"><span style='padding-left:${(mvo.fk_pcode)*20}px; font-weight: bold;'>└</span>&nbsp;${mvo.name}&nbsp;${mvo.pname}&nbsp;(&nbsp;${mvo.cmobile}&nbsp;)</div>
				</c:if>
			</c:forEach>
		</div>
	</div>
</div>

<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/sankey.js"></script>
<script src="https://code.highcharts.com/modules/organization.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>

<script type="text/javascript">

	// 조직도 차트 (하이차트 수정)
	Highcharts.chart('employeeChartContainer', {
	
		  chart: {
		    height: 640,
		    inverted: true
		  },
	
		  title: {
		    useHTML: true,
		    text: '<span style="font-size: 18pt; font-weight: bold;"><span style="color:#000099;">T1 Works</span> 조직도</span>'
		  },
	
		  series: [{
		    type: 'organization',
		    name: 'T1 Works',
		    keys: ['from', 'to'],
		    data: [
		      ['사장', '인사부'],
		      ['사장', 'cs1부'],
		      ['사장', 'cs2부'],
		      ['사장', 'cs3부'],
		      ['사장', '총무부'],
		      ['인사부', '인사부장'],
		      ['인사부', '인사대리'],
		      ['cs1부', 'cs1부장'],
		      ['cs1부', 'cs1대리'],
		      ['cs1부', 'cs1사원'],
		      ['cs2부', 'cs2부장'],
		      ['cs2부', 'cs2대리'],
		      ['cs2부', 'cs2사원'],
		      ['cs3부', 'cs3부장'],
		      ['cs3부', 'cs3대리'],
		      ['cs3부', 'cs3사원'],
		      ['총무부', '총무부장'],
		    ],
		   
		    nodes: [{
		      id: '사장',
		      title: null,
		      name: '사장',
		      color: "#800000",
		    }, {
		      className: 'title',
		      id: '인사부',
		      title: null,
		      name: '인사부',
		      layout: 'hanging',
		      color: "#000080",
		    },{
		      id: '총무부',
		      title: null,
		      name: '총무부',
		      image: null,
		      layout: 'hanging',
		      color: "#000080",
		    }, {
		      id: 'cs1부',
		      title: null,
		      name: 'CS1부',
		      layout: 'hanging',
		      color: "#000080",
		    },{
		      id: 'cs2부',
		      title: null,
		      name: 'CS2부',
		      layout: 'hanging',
		      color: "#000080",
		    }, {
		      id: 'cs3부',
		      title: null,
		      name: 'CS3부',
		      layout: 'hanging',
		      color: "#000080",
		    }, {
		      id: 'cs1부장',
		      title: null,
		      name: 'CS1 부장',
		      layout: 'hanging',
		      color: "#006622",
		    }, {
		      id: 'cs1대리',
		      title: null,
		      name: 'CS1 대리',
		      layout: 'hanging',
		      color: "#006622",
		    }, {
		      id: 'cs1사원',
		      title: null,
		      name: 'CS1 사원',
		      layout: 'hanging',
		      color: "#006622",
		    }, {
		      id: 'cs2부장',
		      title: null,
		      name: 'CS2 부장',
		      layout: 'hanging',
		      color: "#006622",
		    }, {
		      id: 'cs2대리',
		      title: null,
		      name: 'CS2 대리',
		      layout: 'hanging',
		      color: "#006622",
		    }, {
		      id: 'cs2사원',
		      title: null,
		      name: 'CS2 사원',
		      layout: 'hanging',
		      color: "#006622",
		    }, {
		      id: 'cs3부장',
		      title: null,
		      name: 'CS3 부장',
		      layout: 'hanging',
		      color: "#006622",
		    }, {
		      id: 'cs3대리',
		      title: null,
		      name: 'CS3 대리',
		      layout: 'hanging',
		      color: "#006622",
		    }, {
		      id: 'cs3사원',
		      title: null,
		      name: 'CS3 사원',
		      layout: 'hanging',
		      color: "#006622",
		    }, {
		      id: '인사부장',
		      title: null,
		      name: '인사 부장',
		      layout: 'hanging',
		      color: "#006622",
		      info: "인사부 부장",
		    }, {
		      id: '인사대리',
		      title: null,
		      name: '인사 대리',
		      layout: 'hanging',
		      color: "#006622",
		    }, {
		      id: '총무부장',
		      title: null,
		      name: '총무 부장',
		      layout: 'hanging',
		      color: "#006622",
		    }],
		    colorByPoint: false,
		    color: '#007ad0',
		    dataLabels: {
		      color: 'white',
		    },
		    borderColor: 'white',
		    nodeWidth: 50,
		    nodePadding: 2
		  }],
		  exporting: {
		    allowHTML: true,
		    sourceWidth: 800,
		    sourceHeight: 600
		  }
	});


	// 조직도의 특정 항목 클릭시 이에 해당되는 직원 강조하기
	$("div#employeeChartContainer div").bind('click',function(){
		
		$("div.employeeInfo").removeClass("clickedTarget");
		
		var targetText= $(this).children("h4").text();
		var len= targetText.length;
		
		if(targetText.trim()!=""){  // 공백이 아닌 div만 다루기 
			
			if(targetText=="사장"){ // 1) 사장을 클릭한 경우 
				$("div."+targetText).addClass("clickedTarget");		
			}
			else if(targetText.substr(len-1,1)=="부"){ // 2) 부서명을 클릭한 경우   
		    	
				targetText= targetText.substr(0,len-1)+"팀";
				$("div."+targetText).addClass("clickedTarget");		
			}
			else{ // 3) 부서명의 특정 직위를 클릭한 경우

				targetText= targetText.replace(" ","팀");
				$("div."+targetText).addClass("clickedTarget");	
			}
			
		}
		return false; // 버블현상 방지 (이벤트 종료)
		
	}); // end of $("div#employeeChartContainer div").bind('click',function(){------
	
	
	$(document).ready(function(){
		
		// 조직도의 특정 항목 클릭시 이에 해당되는 직원 강조하기 => default 사장
		$("div.사장").addClass("clickedTarget");		
	
	}); // end of document.ready(function(){----------------
	
	
	

</script>
