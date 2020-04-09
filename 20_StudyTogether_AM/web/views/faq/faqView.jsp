<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
   
   
<%@ page import="com.kh.faq.model.vo.FAQ" %>
   
<%
   	FAQ f = (FAQ)request.getAttribute("faq");
%>
  
<%@ include file="/views/common/header.jsp"%>

<style>
	.faq-header{
		margin : 0px;
		margin-top : 50px;
		margin-bottom : 30px;
		
	}

	.tbl-container{
		font-size : 15px;
	}
	#bottons{
		float : right;
		display : flex;
	}
	#btn-list{
		float : left;
	}
	
	#faq-container{
		width : 800px;
		height : 600px;
 		margin-left : 100px;
	}
	
	.btn-container button{
		font-size : 13px;
		font-weight : bold;
	}
	
	
	
</style>



<section id = "faq-container">
<!-- 	<div class="row"> -->
<!-- 		<div class="col-1"></div> -->
		<div class="container faq-header">
			<h2>FAQ</h2>
		</div>
<!-- 	</div> -->
	
		<div class="container tbl-container">
			<table id="tbl-faq" class="table table-hover">
				<colgroup>
					<col width="150px;" />
					<col width="750px;" />
					<col/>
				</colgroup>
				<tr>
					<th>제목</th>
					<td><%=f.getFaqTitle() %></td>
				</tr>
				
				<tr>
					<th>카테고리</th>
					<td><%=f.getFaqCategory() %></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><%=f.getFaqContent() %></td>
				</tr>		
			</table>
		</div>
		
		
		<div class="container btn-container">
			<% if(loginMember!=null&&loginMember.getUserId().equals("admin")){ %>
				
				<div id="bottons">
					<div id="btn-update">
						<button class="btn btn-outline-dark" onclick="fn_updateFaq()">수 정</button>
					</div>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<div id="div-delete">
						<button class="btn btn-outline-dark" onclick="fn_deleteFaq()">삭 제</button>
					</div>
				</div>
				
			<% } %>	
		
			<div id="btn-list">
				<button class="btn btn-outline-dark" onclick="fn_backToList()">목록으로</button>			
			</div>
		</div>

</section>

<script>
	function fn_updateFaq(){
		location.replace("<%=request.getContextPath()%>/faq/updateFaq?no=<%=f.getFaqNo()%>");
	}
	
	
	function fn_deleteFaq(){
		var btn_del=confirm("삭제 하시겠습니까?");
		
		if(btn_del == true){
			 
			  location.replace("<%=request.getContextPath()%>/faq/deleteFaq?no=<%=f.getFaqNo()%>");
			  
			}
			else if(btn_del == false){
			  
			}
		
	}
	
	
	function fn_backToList(){
		location.replace("<%=request.getContextPath()%>/faq/faqList")
	}

</script>

<style>
	#tbl-faq td{
		width:500px;
	}

</style>



<%@ include file="/views/common/footer.jsp"%>
