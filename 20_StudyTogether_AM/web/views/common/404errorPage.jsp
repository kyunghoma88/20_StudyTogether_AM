<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- header페이지 불러오기 -->
<%@ include file="/views/common/header.jsp" %>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/section.css" type="text/css"/>

<!-- Page Content -->
<div class="container">
  <div class="card border-0 shadow my-5">
    <div class="card-body p-5">
    	<div class="text-center justify-content-center">
	      <h1 class="font-weight-light">404에러페이지</h1>
     	  <img style="height:300px;"src="<%=request.getContextPath() %>/images/logo.png">      
	      <p class="lead">요청하신 페이지를 찾을 수 없습니다</p>
	      <p class="lead">지속적으로 이 페이지가 보이면 관리자에게 문의해 주세요!</p>
	     </div>
  </div>
</div>

<!-- footer페이지 불러오기 -->
<%@ include file="/views/common/footer.jsp" %>