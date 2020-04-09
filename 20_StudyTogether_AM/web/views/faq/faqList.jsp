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
	#faq-detail{
		font-size : 11px;
		
	}
	

	.faq-header{
		margin : 0px;
		margin-left : 50px;
		margin-top : 50px;
		
	}
	
	#category-container{
		margin-top : 0;
		margin-bottom : 0;
	}
	
	.search-container{
		margin-top : 20px;
		margin-left : 95px;
	}
	
	.search-container *{
		display : inline-block !important;
	}
	
	
	#search-btn{
		margin-bottom : 3px;
	}
	
	.form-control{
		width : 350px !important;
	}
	
	.btn-faq-category{
		font-size : 13px;
	}
	
	.tbl-container{
		font-size : 15px;
	}
	
	#tbl-faq tr>th{
		text-align : center;
	}
	
	.faq-category{
		text-align : center;
	}
	
	.tbl-faq tr>th{
		text-align : center;
	}
	
	#btn-write{
		float : right;
	}
	
	.pageBar-container{
		margin-left : 250px;
	}
	
</style>


<section id="faq-container" style="height:600px;">
	
		<div class="container faq-header">
			<h2 style="display:inline-block;">FAQ</h2>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<p id="faq-detail" style="display:inline-block;">자주 묻는 질문입니다. 더 궁금하신 사항은 카카오톡 문의를 이용해 주세요.</p>
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
		
		<div class="search-container">

	 				<input type="text" name="faqTitle" class="form-control" autocomplete="off"/>
	 				<button type="button" id="search-btn" class="btn btn-outline-secondary" onclick="fn_search_btn(1);">검색</button>

		</div>

		<div id="table-container" class="container tbl-container">
			
			
			<div>
				<table id="tbl-faq" class="tbl-faq table table-hover">
					<colgroup>
						<col width="150px;" />
						<col width="750px;" />
						<col/>
					</colgroup>
				
					<tr>
						<th>카테고리</th>
						<th>제 목</th>	
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
			
		
		</div>

		<script>
			function fn_btn(cPage, category){
				
				$.ajax({
					url:"<%=request.getContextPath()%>/faq/faqListAjax",
					type:"post",
					data:{cPage:cPage,
						 "category":category},
					success:function(data){
						$("#tbl-faq, #pageBar").hide(); 					
						$('#table-container').html(data);
					}
					
				})
			}
			$("input[name=faqTitle]").keyup((e)=>{
				if(e.key=='Enter'){
					fn_search_btn(1);
				}
			})
			function fn_search_btn(cPage){
				var title = $("input[name=faqTitle]").val();
				$.ajax({
					url:"<%=request.getContextPath()%>/faq/faqSearchListAjax",
					type:"post",
					data:{cPage:cPage,
						 "title":title},
					success:function(data){
						$("#tbl-faq, #pageBar").hide(); 
						
						$('#table-container').html(data);
					}
					
				})
			}
			

		</script>


	
</section>


<%@ include file="/views/common/footer.jsp"%>
