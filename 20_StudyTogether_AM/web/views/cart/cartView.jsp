<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,com.kh.cart.model.vo.Cart" %>
<%
	List<Cart> list = (List)request.getAttribute("list");
%>
<%@ include file="/views/common/header.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/cartView.css" type="text/css"/>
<section>
	<form action="#" method="post">
		<table id="cartTbl">
			<tr>
				<th>번호</th>
				<th>강좌명</th>
				<th>강사</th>
				<th>카테고리</th>
				<th>가격</th>
			</tr>
			<%if(list.isEmpty()) {%>
				<tr>
	            	<td colspan='5'>장바구니가 비었습니다.</td>
	            </tr>
			<%} else{ %>
			<% for(Cart c : list) {%>
				<tr>
					<td><%=c.getCartNo() %></td>
					<td><%=c.getLectorTitle() %></td>
					<td><%=c.getLectorWriter() %></td>
					<td><%=c.getLectorCategory() %></td>
					<td><%=c.getLectorPrice() %></td><input type="hidden" value="<%=c.getLectorPrice()%>">
				</tr>
			<%}
			}%>
		</table>
	</form>
</section>
<%@ include file="/views/common/footer.jsp"%>