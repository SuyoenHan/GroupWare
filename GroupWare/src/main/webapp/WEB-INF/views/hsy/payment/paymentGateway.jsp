<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<script type="text/javascript" src="https://service.iamport.kr/js/iamport.payment-1.1.2.js"></script>

<script type="text/javascript">

$(document).ready(function() {

	var IMP = window.IMP;     // 생략가능
    IMP.init('imp41362340');  // 중요!!  아임포트에 가입시 부여받은 "가맹점 식별코드". 
	
   // 결제요청하기
   IMP.request_pay({
       pg : 'html5_inicis', // 결제방식 PG사 구분
       pay_method : 'card',	// 결제 수단
       merchant_uid : 'merchant_' + new Date().getTime(), // 가맹점에서 생성/관리하는 고유 주문번호
       name : '결제테스트(T1Works)',	 // (선택항목)원활한 결제정보 확인을 위해 입력 권장(PG사 마다 차이가 있지만) 16자 이내로 작성하기를 권장
       amount : 100,	  // Number('${requestScope.amount}')결제 금액 number 타입. 필수항목. 
       buyer_email : '',  // 구매자 email
       buyer_name : '${requestScope.buyer_name}',	  // 구매자 이름 
       buyer_tel : '${requestScope.buyer_tel}',        // 구매자 전화번호 (필수항목)
       buyer_addr : '',  
       buyer_postcode : '',
       m_redirect_url : ''  
   }, function(rsp) {

		if ( rsp.success ) { // PC 데스크탑용
			opener.location.href = "javascript:goPaymentEnd()";
		    self.close();
        } else {
        	alert("결제에 실패하였습니다.");
        	self.close();
        	location.href="<%=request.getContextPath()%>/t1/travelDetail.tw?pNo="+fk_pNo;
       }

   }); // end of IMP.request_pay()----------------------------

}); // end of $(document).ready()-----------------------------

</script>
</head>	

<body>
</body>
</html>
