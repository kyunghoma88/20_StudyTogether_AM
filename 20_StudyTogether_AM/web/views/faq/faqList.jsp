<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>

<%@ page import="java.util.List,com.kh.faq.model.vo.FAQ" %>

<%
	List<FAQ> list = (List)request.getAttribute("list");
%>	

<script>
	function fn_faqWrite(){
		location.replace("<%=request.getContextPath()%>/faq/faqWrite");
	}
</script>

<section id="faq-container">
	<h2>FAQ</h2>
<%-- 		<% if(loginMember!=null&&loginMember.getUserId().equals("admin")){ %> --%>
<!-- 			<!-- null && 로 안하면 nullpoint exception 뜨겠지 --> 
 			<button type="button" id="btn-faq-write" onclick="fn_faqWrite();">FAQ 작성</button>
<%-- 		<% } %> --%>




		<div id="category-container">
			<button type="button" id="btn-faq-category" onclick="fn_all();">전체보기</button>
			<button type="button" id="btn-faq-category" onclick="fn_member();">회 원</button>
			<button type="button" id="btn-faq-category" onclick="fn_registStudy();">스터디 개설</button>
			<button type="button" id="btn-faq-category" onclick="fn_payment();">결 제</button>
			<button type="button" id="btn-faq-category" onclick="fn_etc();">기 타</button>
		</div>
		<div id="tableContainer"></div>







	<table id="tbl-faq">
		<tr>
			<th>카테고리</th>
			<th>제목</th>			
		</tr>
	<% if(list.isEmpty()){ %>
		<tr>
			<td colspan='2'> 검색된 FAQ가 없습니다!</td>
		</tr>
	<% } else{ %>
		<%	for(FAQ f : list){ %>
		<tr>
			<td><%= f.getFaqCategory() %></td>				
			<td><a href="<%= request.getContextPath() %>/faq/faqView?no=<%=f.getFaqNo() %>">
					<%= f.getFaqTitle() %>
				</a>
			</td>
		</tr>
		<%} %>
	<%} %>
	</table>
	
	<div id="pageBar">
		<%= request.getAttribute("pageBar") %>
	</div>

</section>


<%@ include file="/views/common/footer.jsp"%>
