<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List,com.kh.cart.model.vo.Cart"%>
<%
	List<Cart> list = (List) request.getAttribute("list");
%>
<%@ include file="/views/common/header.jsp"%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/cartView.css" type="text/css" />
<section>
	<form name="mapping" action="<%=request.getContextPath()%>/test" method="post">
		<table id="cartTbl">
			<tr id="cartHead">
				<!-- <th>번호</th> -->
				<th>
					<!-- <input type="checkbox" id="checkall" name="allselect">&nbsp;&nbsp;&nbsp; -->강좌명
				</th>
				<th>강사</th>
				<th>카테고리</th>
				<th>가격</th>
			</tr>
			<%-- <%
				if (list.isEmpty()) {
			%>
			<tr>
				<td>장바구니가 비었습니다.</td>
			</tr>
			<%
				} else {
			%>
			<%
				for (Cart c : list) {
			%>
			<tr>
				<td>
				<input name="list" type="checkbox" value="<%=list%>">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=c.getLectorTitle()%></td>
				<td><%=c.getLectorWriter()%></td>
				<td><%=c.getLectorCategory()%></td>
				<td><%=c.getLectorPrice()%></td>
				<input type="hidden" name="cartNo" value="<%=c.getCartNo()%>">
				<input type="hidden" name="test" value="<%=list.get(1)%>">
			</tr>
			<%
				}
			}
			%> --%>
			<%if(list.isEmpty()) {%>
				<tr>
					<td colspan='4'>장바구니가 비었습니다.</td>
				</tr>
			<%}else { %>
			<%for(int i = 0; i < list.size(); i++) {%>
				<%if(list.get(i).getStatus().equals("N")){ %>
				<tr>
					<td>
						<input type="checkbox" name="cartList" value="<%=list.get(i) %>" onclick="itemSum();">
						<%=list.get(i).getLectorTitle() %>
					</td>
					<td><%=list.get(i).getLectorWriter() %></td>
					<td><%=list.get(i).getLectorCategory() %></td>
					<td><%=list.get(i).getLectorPrice() %></td>
					<td></td>
				</tr>
				<%} %>
			<%}
			}%>
		</table>
		<tr>
			<td>
				<div id="totalSpan">
					총 결제금액 : &nbsp;<input name="total_sum" type="text" id="totalSum"
						size="20" readonly>
				</div>
			</td>
		</tr>
		<br><span><input type="button" value="결제하기" id="subBtn" onclick="mappingAction('payment');"></span>
			<span><input type="button" value="삭제" id="subBtn" onclick="mappingAction('delete');"></span>
	</form>
	<script>
		function itemSum() {
			var sum = 0;
			var totalSum = document.getElementById("totalSum")
			<%for(int i = 0; i < list.size(); i++) {%>
				if (frm.cartList[i].checked == true) {
					sum += <%=list.get(i).getLectorPrice()%>;
				}
			<%} %>
			totalSum.value = sum;
			console.log(sum);
		}
		
		function mappingAction(val){
			var form = document.mapping;
			
			if(val == "payment"){
				form.action = "<%=request.getContextPath()%>/buy/buyForm";
			} else if(val == "delete"){
				form.action = "<%=request.getContextPath()%>/cart/deleteCart";
			}
			form.submit();
		}
		

		
	</script>
</section>
<%@ include file="/views/common/footer.jsp"%>