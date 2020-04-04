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
 		<% if(loginMember!=null&&loginMember.getUserId().equals("admin")){ %>
 
 			<button type="button" id="btn-faq-write" onclick="fn_faqWrite();">FAQ 작성</button>
 		<% } %> 




		<div id="category-container">
			<button type="button" id="btn-faq-category" onclick="fn_btn(1,'전체보기');">전체보기</button>
			<button type="button" id="btn-faq-category" onclick="fn_btn(1,'회원');" >회 원</button>
			<button type="button" id="btn-faq-category" onclick="fn_btn(1,'스터디개설');">스터디 개설</button>
			<button type="button" id="btn-faq-category" onclick="fn_btn(1,'결제');">결 제</button>
			<button type="button" id="btn-faq-category" onclick="fn_btn(1,'기타');">기 타</button>
		</div>
		<div id="table-container"></div>

		<script>
			function fn_btn(cPage, category){
				$.ajax({
					url:"<%=request.getContextPath()%>/faq/faqListAjax",
					type:"post",
					data:{cPage:cPage,
						 "category":category},
					success:function(data){
						//console.log(data);
						//alert(category);
						$("#tbl-faq, #pageBar").css("display","none"); 
						
						
						
						$('#table-container').html(data);
					}
					
				})
			}

		</script>



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
