<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
   
   
<%@ page import="com.kh.faq.model.vo.FAQ" %>
   
<%
   	FAQ f = (FAQ)request.getAttribute("faq");
   %>
   

<%@ include file="/views/common/header.jsp"%>


<div id = "faq-container">

	<h2> FAQ 상세화면</h2>
		<table id="tbl-faq">
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
		
		<% if(loginMember!=null&&loginMember.getUserId().equals("admin")){ %>
			<div id="btn-update">
				<button onclick="fn_updateFaq()">수 정</button>
			</div>
			
			<div id="div-delete">
				<button onclick="fn_deleteFaq()">삭 제</button>
			</div>
		<% } %>	
		
		</br>
		<div id="btn-list">
			<button onclick="fn_backToList()">목록으로</button>			
		</div>

</div>

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
