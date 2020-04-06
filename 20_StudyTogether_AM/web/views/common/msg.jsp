<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String msg=(String)request.getAttribute("msg");
	String loc=(String)request.getAttribute("loc");
	String script=(String)request.getAttribute("script");
	
%>
<!--Request attribute에 있는 msg와 loc을 가져올수 있다. -->


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<script>
		alert('<%=msg%>');
		<%=script!=null?script:""%>
		location.replace("<%=request.getContextPath()%><%=loc%>")
	</script>
</body>
</html>





