<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,com.kh.cart.model.vo.Cart"%>
<%
	String name=(String)request.getAttribute("name");
	String email=(String)request.getAttribute("email");
	String phone=(String)request.getAttribute("phone");
	String address=(String)request.getAttribute("address");
	String postcode=(String)request.getAttribute("postcode");	
	String stotalPrice=(String)request.getAttribute("totalPrice");
	int totalPrice=Integer.parseInt(stotalPrice);
	
	List<Cart> list=(List)request.getAttribute("list");
	
	session.setAttribute("cartList", list);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>I'mPort Payment Test</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
</head>
<body>
<script>
	var IMP = window.IMP; // 생략해도 괜찮습니다.
	IMP.init("imp38249937"); // "imp00000000" 대신 발급받은 "가맹점 식별코드"를 사용합니다.
	$(function(){
        var IMP = window.IMP; // 생략가능
        IMP.init('imp38249937'); // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용
        
        IMP.request_pay({ // param
        	pg: "nice",
            pay_method : 'card',
            merchant_uid : 'merchant_' + new Date().getTime(),
			name : '<%=name%>님의 강좌 주문', 
			amount : <%=totalPrice%>, 
			buyer_email : '<%=email%>', 
			buyer_name : '<%=name%>', 
			buyer_tel : '<%=phone%>',
			buyer_addr : '<%=address%>',
            buyer_postcode : '<%=postcode%>',
          }, function (rsp) { // callback
        	  var msg="";
        	  if (rsp.success) {
            	  msg = '결제가 완료되었습니다.';
                  msg += '\n고유ID : ' + rsp.imp_uid;
                  msg += '\n상점 거래ID : ' + rsp.merchant_uid;
                  msg += '\n결제 금액 : ' + rsp.paid_amount;
				  alert(msg);
		  		  buySuccess();
              } else {
            	  msg = '결제에 실패하였습니다.';
                  msg += '\n에러내용 : ' + rsp.error_msg;
                  //실패시 이동할 페이지
	             alert(msg);
             	 history.go(-1);
             }	
          });
  	  
	  	  function buySuccess(){
	  		  let newForm = document.createElement('form');
	  		  newForm.method = 'POST';
	  		  newForm.action = '<%=request.getContextPath()%>/buy/buySuccess';
	  		  newForm.name = 'newForm';
	  		  document.body.appendChild(newForm);
	  		  newForm.submit();
	  	  }
      });
	  
	  
</script>

</body>
</html>