<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>

<%@ page import="java.util.List,com.kh.faq.model.vo.FAQ" %>    
<%
	List<FAQ> list=(List)request.getAttribute("list");
%>
<table>
	<tr>
		<th>카테고리</th>
		<th>제 목</th>
	</tr>
	
	<%for(FAQ f : list){ %>
		<tr>
			<td><%= f.getFaqCategory() %></td>				
			<td><a href="<%= request.getContextPath() %>/faq/faqView?no=<%=f.getFaqNo() %>">
					<%= f.getFaqTitle() %>
				</a>
			</td>
		</tr>
	
	<%} %>
	
</table>




<div id="pagebar">
	<ul class="pagination">
		<%=request.getAttribute("pageBar") %>
	</ul>
</div>
