<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<% 
	String ctxPath = request.getContextPath();
%>

<style type="text/css">
	div.section{
		margin: 30px 0px 30px 50px;
		width: 85%;
	}
	div.tab_select{
		margin-top: 10px;
	}
	a.tab_area{
		background-color: #ecf2f9;
		font-size: 12pt;
		padding: 5px;
		border-radius: 5px;
		color: black;
		text-decoration: none;
	}
	a.tab_area:hover{
		text-decoration: none;
		color: black;
	}	
	a.selected{
		background-color: #0071bd;
		color: white;
	}
	a.selected:hover{
		color: white;
	}
	span.period {
		margin-right: 10px;
	}
	span.chkbox {
		font-size: 9pt;
		border: solid 1px gray;
		color: gray;
		border-radius: 5px;
		margin-right: 2px;
		padding-left: 3px;	
	}	
	input.dateType{
		display: none;
	}	
	input.datepicker{
		width: 100px;
		height: 20px;
	}
	label{
		cursor: pointer;
		font-weight: normal !important; 
	}
	table{
		margin: 10px 5px;
		width: 90%;
	}
	div.section tr, div.section td{
		border: solid 1px #ccc;
		border-collapse: collapse;
	}
	td.th{		
		background-color: #ccd9e6;
		padding: 5px;
		font-weight: bold;
	}	
	#table th{
		background-color: #395673; 
		color: #ffffff;
		padding: 5px;
		border: solid 1px #ccc;
		border-collapse: collapse;
	}
	tr.tr_hover:hover{
		cursor: pointer;
		background-color: #eef2f7;
	}
</style>

<link rel="stylesheet" type="text/css" href="<%= ctxPath%>/resources/css/datepicker.css"/>

<script type="text/javascript" src="<%= ctxPath%>/resources/jquery-ui-1.11.4.custom/jquery-ui.min.js"></script>

<script type="text/javascript">
$(document).ready(function(){
	
	// 특정 문서를 클릭하면 그 문서의 상세 정보를 보여주도록 한다.
	$(document).on('click','tr.docuInfo',function(){
		var checkArr = new Array();	
		$("input[name=ncat]:checked").each(function(index,item){			
			var ncat = $(item).val();
			checkArr.push(ncat);			
		});
		
		var checkArres = checkArr.join();	
		
		var ano = $(this).children(".ano").text();
		var ncatname = $(this).children(".ncatname").text();
		var ncat = checkArres;
		var sort= $("select#sort").val();		
		var searchWord= $("input#searchWord").val();
		
		goView(ano, ncatname, ncat, sort, searchWord);
	});
	
	if("${sort}"=="" && "${searchWord}"=="" && "${ncat}"==""){
		goSearch(1);
	}
	else {
		goSearch2('${currentShowPageNo}');
				
		$("select#sort").val("${sort}");
		$("input#searchWord").val("${searchWord}");
		
		var n = "${ncat}".split(',');		
		
		for(var i=0; i<n.length; i++){
			$("input:checkbox[id='chx"+n[i]+"']").prop("checked", true);
		}		
	}
	
	$("input#searchWord").bind("keydown", function(event){
		if(event.keyCode == 13){
			// 엔터를 했을 경우
			goSearch(1);
		}
	});	
	
});// end of $(document).ready(function(){})--------------------
		
		
	// Function Declaration
	
	// 페이지 로딩 시 해당하는 내역 전체 보여주기(페이징처리)
	function goSearch(currentShowPageNo){			
		var checkArr = new Array();	
		$("input[name=ncat]:checked").each(function(index,item){			
			var ncat = $(item).val();
			checkArr.push(ncat);			
		});
		// console.log(checkArr);
		
		var checkArres = checkArr.join();
		var ncat= checkArres;
		var sort= $("select#sort").val();
		var searchWord= $("input#searchWord").val();
		
		$.ajax({			
			url:"<%= ctxPath%>/t1/norm_templist.tw",
			data:{"ncat": checkArres
				, "sort":$("select#sort").val()
				, "searchWord":$("input#searchWord").val()
				, "currentShowPageNo":currentShowPageNo},
			dataType:"json",
			success:function(json){				
				
				var html = "";
				
				if(json.length > 0){
					$.each(json, function(index, item){						
						
						html += "<tr class='tr_hover docuInfo'>";
						html += "<td align='center' style='padding: 5px;'>"+ item.rno +"</td>";
						html += "<td>&nbsp;"+ item.atitle +"</td>";
						html += "<td class='ncatname' align='center'>"+ item.ncatname +"</td>";
						html += "<td class='ano' align='center'>"+ item.ano +"</td>";
						html += "</tr>";
					});
				}
				else{
					html += "<tr>";
					html += "<td colspan='4' align='center' style='padding: 5px;'>해당하는 글이 없습니다</td>";
					html += "</tr>";
				}
				
				$("tbody#approvalDisplay").html(html);
				
				// 페이지바 함수 호출
				makeApprovalPageBar(currentShowPageNo);
			},
			error: function(request, status, error){
				alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
			}
		});		
	}// end of function goSearch(){}--------------------
	
	
	function goSearch2(currentShowPageNo){	
		
		$.ajax({
			url:"<%= ctxPath%>/t1/norm_templist.tw",
			data:{"ncat": "${ncat}"
				, "sort":"${sort}"
				, "searchWord":"${searchWord}"
				, "currentShowPageNo":currentShowPageNo},
			dataType:"json",
			success:function(json){				
				
				var html = "";
				
				if(json.length > 0){
					$.each(json, function(index, item){
						
						html += "<tr class='tr_hover docuInfo'>";
						html += "<td align='center' style='padding: 5px;'>"+ item.rno +"</td>";
						html += "<td>&nbsp;"+ item.atitle +"</td>";
						html += "<td class='ncatname' align='center'>"+ item.ncatname +"</td>";
						html += "<td class='ano' align='center'>"+ item.ano +"</td>";						
						html += "</tr>";
					});
				}
				else{
					html += "<tr>";
					html += "<td colspan='4' align='center' style='padding: 5px;'>해당하는 글이 없습니다</td>";
					html += "</tr>";
				}
				
				$("tbody#approvalDisplay").html(html);				
				
				// 페이지바 함수 호출
				makeApprovalPageBar(currentShowPageNo);	
			},
			error: function(request, status, error){
				alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
			}
		});		
	}// end of function goSearch2(){}--------------------
	
	
	// 페이지바 Ajax로 만들기
	function makeApprovalPageBar(currentShowPageNo){
		
		$("input[name=currentShowPageNo]").val(currentShowPageNo);
		
		var checkArr = new Array();	
		$("input[name=ncat]:checked").each(function(index,item){			
			var ncat = $(item).val();
			checkArr.push(ncat);			
		});
		// console.log(checkArr);
		
		var checkArres = checkArr.join();		
		
		// totalPage 수 알아오기
		$.ajax({
			url:"<%= ctxPath%>/t1/getNormTempTotalPage.tw",
			data:{"ncat": checkArres
				, "sort":$("select#sort").val()
				, "searchWord":$("input#searchWord").val()
				, "sizePerPage":"10"},
			type:"get",
			dataType:"json",
			success:function(json){
				
				if(json.totalPage > 0){
					// 글이 있는 경우
					
					var totalPage = json.totalPage;
					
					var pageBarHTML = "<ul class='pagination' style='list-style: none;'>";
					
					var blockSize = 5;
					
					var loop = 1;
					
					if(typeof currentShowPageNo == "string"){
						currentShowPageNo = Number(currentShowPageNo);
					}
					
					var pageNo = Math.floor((currentShowPageNo - 1)/blockSize) * blockSize + 1;
					
					// === [맨처음][이전] 만들기 ===
					if(pageNo != 1) {
						pageBarHTML += "<li><a href='javascript:goSearch(\"1\")'>First</a></li>";
						pageBarHTML += "<li><a href='javascript:goSearch(\""+(pageNo-1)+"\")'>Prev</a></li>";
					}
					
					while(!(loop > blockSize || pageNo > totalPage)) {
						
						if(pageNo == currentShowPageNo) {
							pageBarHTML += "<li class='active'><a>"+pageNo+"</a></li>";
						}
						else {
							pageBarHTML += "<li><a href='javascript:goSearch(\""+pageNo+"\")'>"+pageNo+"</a></li>";
						}

						loop++;
						pageNo++;
					}// end of while--------------------
					
					// === [다음][마지막] 만들기 ===
					if(pageNo <= totalPage) {
						pageBarHTML += "<li><a href='javascript:goSearch(\""+pageNo+"\")'>Next</a></li>";
						pageBarHTML += "<li><a href='javascript:goSearch(\""+totalPage+"\")'>Last</a></li>";
					}
					
					pageBarHTML += "</ul>";
					
					$("div#pageBar").html(pageBarHTML);
					
				}				
			},
			error: function(request, status, error){
				alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
			}
		});
	}
	
	
	function goView(ano, ncatname, ncat, sort, searchWord){
		var frm = document.goViewFrm;
		
		frm.ano.value = ano;
		frm.sort.value = sort;
		frm.ncatname.value = ncatname;
		frm.searchWord.value = searchWord;
		frm.ncat.value = ncat;
		
		frm.method = "get";
		frm.action = "<%= ctxPath%>/t1/myDocuNorm_temp_detail.tw";
		frm.submit();
	} 
</script>

<div class="section">
<img src="<%= ctxPath%>/resources/images/sia/document.png" width="26px;">
<span style="font-size: 14pt; font-weight: bold;">임시저장함</span>
	<div class="tab_select">
		<a href="<%= ctxPath%>/t1/myDocuNorm_temp.tw" class="tab_area selected">일반 결재 문서</a> 
		<a href="<%= ctxPath%>/t1/myDocuSpend_temp.tw" class="tab_area">지출 결재 문서</a>
		<a href="<%= ctxPath%>/t1/myDocuVacation_temp.tw" class="tab_area">근태/휴가 결재 문서</a>			
	</div>

	<form name="searchFrm">	
		<table>					
			<tr>			
				<td width="20%" class="th">일반 결재 문서</td>
				<td width="80%">&nbsp;&nbsp;
					<label for="chx1"><input type="checkbox" name="ncat" id="chx1" value="1"> 회의록</label>&nbsp;&nbsp;
					<label for="chx2"><input type="checkbox" name="ncat" id="chx2" value="2"> 위임장</label>&nbsp;&nbsp;
					<label for="chx3"><input type="checkbox" name="ncat" id="chx3" value="3"> 외부공문</label>&nbsp;&nbsp;
					<label for="chx4"><input type="checkbox" name="ncat" id="chx4" value="4"> 협조공문</label>&nbsp;&nbsp;
				</td>
			</tr>			
			<tr>
				<td class="th">문서검색</td>
				<td>&nbsp;&nbsp;
					<select name="sort" id="sort">
						<option value="">전체보기</option>
						<option value="atitle">제목</option>
						<option value="ano">문서번호</option>												
					</select>&nbsp;
					<input type="text" name="searchWord" id="searchWord" style="height: 20px;"/> <button type="button" onclick="goSearch(1)">검색</button>
				</td>
			</tr>	
		</table>
		
		<br>
		
		<table id="table">
			<thead>
			<tr>
				<th style="width: 70px; text-align: center;">번호</th>
				<th style="width: 300px; text-align: center;">제목</th>
				<th style="width: 100px; text-align: center;">문서분류</th>
				<th style="width: 100px; text-align: center;">문서번호</th>
			</tr>
			</thead>
			<tbody id="approvalDisplay"></tbody>		
		</table>
		
		<div id="pageBar" style="width: 90%; margin-left: 42%;"></div>
	</form>
	
	<form name="goViewFrm">
		<input type="hidden" name="ano" value="" />
		<input type="hidden" name="ncatname" value="" />		
		<input type="hidden" name="ncat" value="" />
		<input type="hidden" name="sort" value="" />
		<input type="hidden" name="searchWord" value="" />
		<input type="hidden" name="currentShowPageNo" value="" />
	</form>
</div>
