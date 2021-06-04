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
	
</style>


<div id="employeeChartContainer" style="width: 70%;"></div>



<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/sankey.js"></script>
<script src="https://code.highcharts.com/modules/organization.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>

<script type="text/javascript">

Highcharts.chart('employeeChartContainer', {

	  chart: {
	    height: 500,
	    inverted: true
	  },

	  title: {
	    useHTML: true,
	    text: 'Statistics Division of United Nations'
	  },

	  series: [{
	    type: 'organization',
	    name: 'United Nations',
	    keys: ['from', 'to'],
	    data: [
	      ['사장', '기획부'],
	      ['사장', 'cs1부'],
	      ['사장', 'cs2부'],
	      ['사장', 'cs3부'],
	      ['사장', '총무부'],
	      ['기획부', '기획부장'],
	      ['기획부', '기획대리'],
	      ['cs1부', 'cs10부'],
	      ['cs1부', 'cs11부'],
	      ['cs1부', 'cs12부'],
	      ['cs2부', 'cs21부'],
	      ['cs2부', 'cs22부'],
	      ['cs2부', 'cs23부'],
	      ['cs3부', 'cs31부'],
	      ['cs3부', 'cs32부'],
	      ['cs3부', 'cs33부'],
	      ['총무부', '총무부장'],
	      ['총무부', '총무대리'],
	    ],
	   
	    nodes: [{
	      id: '사장',
	      title: null,
	      name: '사장',
	      color: "#419dc0",
	      info: "Director"
	    }, {
	      className: 'title',
	      id: '기획부',
	      title: null,
	      name: '기획부',
	      layout: 'hanging',
	      color: "#41c0a4",
	      info: "Planning and coordination of the overall Division’s work program and operation, <br/>including program management finance/budget management, <br/>human resources management, and general office administration"
	    },{
	      id: '총무부',
	      title: null,
	      name: '총무부',
	      image: null,
	      layout: 'hanging',
	      color: "#41c0a4",
	      info: "Methodological work on MDG indicators, databases; <br/>coordination of inter-agency groups for MDG global indicators, <br/>responsible for MDG global monitoring. <br/>Coordination of global gender statistics program"
	    }, {
	      id: 'cs1부',
	      title: null,
	      name: 'cs1부',
	      layout: 'hanging',
	      color: "#41c0a4",
	      info: "Application of information technologies for the collection, <br>processing and dissemination of international statistics<br> and metadata by all branches of the Statistics Division"
	    },{
	      id: 'cs2부',
	      title: null,
	      name: 'cs2부',
	      layout: 'hanging',
	      color: "#41c0a4",
	      info: "National Accounts Section"

	    }, {
	      id: 'cs3부',
	      title: null,
	      name: 'cs3부',
	      layout: 'hanging',
	      color: "#41c0a4",
	      info: "Environmental Economic Accounts Section"
	    }, {
		      id: 'cs10부',
		      title: null,
		      name: 'cs10부',
		      layout: 'hanging',
		      color: "#beef3a",
		      info: "Environmental Economic Accounts Section"
		    }
	    , {
		      id: 'cs11부',
		      title: null,
		      name: 'cs11부',
		      layout: 'hanging',
		      color: "#beef3a",
		      info: "Environmental Economic Accounts Section"
		    }
	    , {
		      id: 'cs12부',
		      title: null,
		      name: 'cs12부',
		      layout: 'hanging',
		      color: "#beef3a",
		      info: "Environmental Economic Accounts Section"
		    },
	    {
		      id: 'cs21부',
		      title: null,
		      name: 'cs21부',
		      layout: 'hanging',
		      color: "#beef3a",
		      info: "Environmental Economic Accounts Section"
		    }
	    , {
		      id: 'cs22부',
		      title: null,
		      name: 'cs22부',
		      layout: 'hanging',
		      color: "#beef3a",
		      info: "Environmental Economic Accounts Section"
		    }
	    , {
		      id: 'cs23부',
		      title: null,
		      name: 'cs23부',
		      layout: 'hanging',
		      color: "#beef3a",
		      info: "Environmental Economic Accounts Section"
		    }
	    ,
	    {
		      id: 'cs31부',
		      title: null,
		      name: 'cs31부',
		      layout: 'hanging',
		      color: "#beef3a",
		      info: "Environmental Economic Accounts Section"
		    }
	    , {
		      id: 'cs32부',
		      title: null,
		      name: 'cs32부',
		      layout: 'hanging',
		      color: "#beef3a",
		      info: "Environmental Economic Accounts Section"
		    }
	    , {
		      id: 'cs33부',
		      title: null,
		      name: 'cs33부',
		      layout: 'hanging',
		      color: "#beef3a",
		      info: "Environmental Economic Accounts Section"
		    },
	    {
		      id: '기획부장',
		      title: null,
		      name: '기획부장',
		      layout: 'hanging',
		      color: "#beef3a",
		      info: "Environmental Economic Accounts Section"
		    }
	    , {
		      id: '기획대리',
		      title: null,
		      name: '기획대리',
		      layout: 'hanging',
		      color: "#beef3a",
		      info: "Environmental Economic Accounts Section"
		    },
		    {
			      id: '총무부장',
			      title: null,
			      name: '총무부장',
			      layout: 'hanging',
			      color: "#beef3a",
			      info: "Environmental Economic Accounts Section"
			    }
		    , {
			      id: '총무대리',
			      title: null,
			      name: '총무대리',
			      layout: 'hanging',
			      color: "#beef3a",
			      info: "Environmental Economic Accounts Section"
			    }
	    ],
	    colorByPoint: false,
	    color: '#007ad0',
	    dataLabels: {
	      color: 'white',
	    },
	    borderColor: 'white',
	    nodeWidth: 30,
	    nodePadding: 2
	  }],

	  tooltip: {
	    outside: true,
	    formatter: function() {
	      return this.point.info;
	    }
	  },

	  exporting: {
	    allowHTML: true,
	    sourceWidth: 800,
	    sourceHeight: 600
	  }
	});


	$("div#employeeChartContainer div").bind('click',function(){
		
			if($(this).children("h4").text().trim()!=""){
				alert("안녕");
			}
			return false;		
	}); 
	

</script>
