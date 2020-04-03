<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/member/myPageHeader.jsp" %>
<%
	
%>



<h4><%=loginMember.getUserId()%>님께서는 아직 참여한 스터디가 없습니다</h4>
<a href="<%=request.getContextPath() %>/study/studyList">
	<p>새 스터디 보러가기!</p>
</a>


<%@ include file="/views/member/myPageFooter.jsp" %>