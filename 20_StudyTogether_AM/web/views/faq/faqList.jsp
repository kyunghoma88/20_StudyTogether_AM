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

<style>
	.faq-header{
		margin : 0px;
		margin-top : 50px;
		
	}
	
	.btn-faq-category{
		font-size : 13px;
	}
	
	.tbl-container{
		font-size : 15px;
	}
	
	#btn-faq-write{
		
	}
	
	#tbl-faq tr>th{
		text-align : center;
	}
	
	.faq-category{
		text-align : center;
	}
	
	/* #tbl-faq tr td{
		text-align : center;
	} */
	
	.tbl-faq tr>th{
		text-align : center;
	}
	
	#btn-write{
		float : right;
	}
	
	.pageBar-container{
		margin-left : 400px;
	}
	
</style>


<section id="faq-container" style="height:800px;">
	<div class="row">
		<div class="col-2"></div>
		<div class="container faq-header col-10">
			<h2>FAQ</h2>
		</div>
	</div>
	<br>
	
		<div id="category-container" class="container">
			<button type="button" id="btn-faq-category" class="btn-faq-category btn btn-outline-dark" onclick="fn_btn(1,'전체보기');">전체보기</button>
			<button type="button" id="btn-faq-category" class="btn-faq-category btn btn-outline-dark" onclick="fn_btn(1,'회원');" >회 원</button>
			<button type="button" id="btn-faq-category" class="btn-faq-category btn btn-outline-dark" onclick="fn_btn(1,'스터디개설');">스터디 개설</button>
			<button type="button" id="btn-faq-category" class="btn-faq-category btn btn-outline-dark" onclick="fn_btn(1,'결제');">결 제</button>
			<button type="button" id="btn-faq-category" class="btn-faq-category btn btn-outline-dark" onclick="fn_btn(1,'기타');">기 타</button>
 		<% if(loginMember!=null&&loginMember.getUserId().equals("admin")){ %>
 
 			<button type="button" class="btn-faq-category btn btn-dark" id="btn-faq-write" onclick="fn_faqWrite();">FAQ 작성</button>
 		<% } %> 
		</div>




		<div id="table-container" class="container tbl-container"></div>

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


	<div class="container tbl-container">
		<table id="tbl-faq" class="table table-hover">
			<colgroup>
				<col width="150px;" />
				<col width="750px;" />
				<col/>
			</colgroup>
			
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
				<td class="faq-category"><%= f.getFaqCategory() %></td>				
				<td><a href="<%= request.getContextPath() %>/faq/faqView?no=<%=f.getFaqNo() %>">
						<%= f.getFaqTitle() %>
					</a>
				</td>
			</tr>
			<%} %>
		<%} %>
		</table>
		
		<div id="pageBar" class="container pageBar-container">
			<ul class="pagination">
				<%= request.getAttribute("pageBar") %>
			</ul>	
		</div>
	</div>
</section>


<%@ include file="/views/common/footer.jsp"%>
