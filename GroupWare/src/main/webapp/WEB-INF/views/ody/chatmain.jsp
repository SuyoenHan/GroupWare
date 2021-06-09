<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.net.InetAddress"%>
<% String ctxPath = request.getContextPath(); 

	// === #172. (웹채팅관련3) === 
	// === 서버 IP 주소 알아오기(사용중인 IP주소가 유동IP 이라면 IP주소를 알아와야 한다.) ===
	InetAddress inet = InetAddress.getLocalHost(); 
	String serverIP = inet.getHostAddress();
	
  // System.out.println("serverIP : " + serverIP);
 // serverIP : 211.238.142.72
 // serverIP : 192.168.219.104	
	// String serverIP = "211.238.142.72"; 만약에 사용중인 IP주소가 고정IP 이라면 IP주소를 직접입력해주면 된다.
	
	// === 서버 포트번호 알아오기   ===
	int portnumber = request.getServerPort();
 //  System.out.println("portnumber : " + portnumber);
 // portnumber : 9090
	
	String serverName = "http://"+serverIP+":"+portnumber; 
  //System.out.println("serverName : " + serverName);
 // serverName : http://211.238.142.72:9090 
 // serverName : http://192.168.219.104:9090
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<meta charset="UTF-8">

<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
<title>T1works Talk</title>
<style type="text/css">

tr.selectEmp td{
	padding: 10px 0;
}

</style>


<script type="text/javascript">
	
	$(document).ready(function(){
	
		$("span.showEmp").hide();
		
		$("div.department").click(function(){
			
			  if($(this).next().css("display") == "none"){ 
				  $(this).next().show();
					$(this).find("span.showEmp").hide();
					$(this).find("span.hideEmp").show();
			  }
			  else{
				  $(this).next().hide();
				  $(this).find("span.showEmp").show();
				  $(this).find("span.hideEmp").hide();
			  }
		});
		
		
		
		$("tr.selectEmp").dblclick(function(){
			var empId = $(this).children(".employeeid").text();
			
			$("input#to").val(empId);
			
			var frm = document.message;
						
		    openChild=window.open("", "openChild", "left=100px, top=100px, width=500px, height=600px");
	   
		    frm.target = "openChild";
		    frm.action = "<%= serverName%><%= ctxPath%>/t1/chatting/chatwith.tw?to="+empId;
		    frm.submit();
		    //	openChild.document.getElementById('empId').value = document.getElementById('destination').value;
	   	//	openChild.document.getElementById('empId').value = document.getElementById('destination').value;
		
		});
		
		$("p#boss").dblclick(function(){
			var empId = $(this).prev().text();
			$("input#to").val(empId);
			var frm = document.message;
			openChild=window.open("", "openChild", "left=100px, top=100px, width=500px, height=700px");
			   
		    frm.target = "openChild";
		    frm.action = "<%= serverName%><%= ctxPath%>/t1/chatting/chatwith.tw?to="+empId;
		    frm.submit();
		    
		});
	});// end of $(document).ready(function(){})-----------
	
</script>




<div style="width: 500px; height: 700px; border: solid 1px gray; padding-left: 40px;">
<h2>T1works Talk</h2>


<c:forEach var="mvo" items="${employeeList}">
		<c:if test="${mvo.fk_pcode == 4}">
		<span id="boss" style="display: none;">${mvo.employeeid}</span><p id="boss">${mvo.name} 사장님</p>
</c:if>

</c:forEach>
<c:forEach var="dvo" items="${departmentList}">
	<div style="border-top: solid 1px gray;" class="department">${dvo.dname}
		<span class="hideEmp"><i class='fas fa-angle-up' style='font-size:24px'></i></span>
		<span class="showEmp"><i class='fas fa-angle-down' style='font-size:24px'></i></span>
	</div>	
	

	<table style="width: 500px" id="empInfo">
		<c:forEach var="mvo" items="${employeeList}">
			<c:if test="${dvo.dcode == mvo.fk_dcode}">
				<tr class="selectEmp">
					<td class="employeeid" style="display: none;">${mvo.employeeid}</td>
					<td style="width: 10%;" class="employeename">${mvo.name}</td> 
					<td style="text-align: left;"><c:if test="${mvo.fk_pcode == 1}"> 사원 </c:if>
					<c:if test="${mvo.fk_pcode == 2}"> 대리님 </c:if>
					<c:if test="${mvo.fk_pcode == 3}"> 부장님 </c:if>
				</td></tr>	
				</c:if>
		</c:forEach>
	</table>
	
	
</c:forEach>
</div>

<form name="message">
<input type="hidden" id="to" name="to"/>
</form>