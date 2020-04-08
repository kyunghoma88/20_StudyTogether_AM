<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp" %>



<!-- header페이지 불러오기 -->
<div class="container">
   	<div class="row mb-5" style="height:40px">
	    <div class="col text-center bg-light border-0 m-1 p-2" style=" cursor: pointer;" onclick="location.href='<%=request.getContextPath()%>/member/memberView';">
	      <a href="<%=request.getContextPath()%>/member/memberView" class="text-decoration-none text-reset text-decoration-none text-dark">내 계정 정보</a>
	    </div>
   	    <div class="col text-center bg-light border-0 m-1 p-2" style=" cursor: pointer;" onclick="location.href='<%=request.getContextPath()%>/member/memberLector';">
	      <a href="<%=request.getContextPath()%>/member/memberLector" class="text-decoration-none text-reset text-decoration-none text-dark">내 강좌 목록</a>
	    </div>
	    <div class="col text-center bg-light border-0 m-1 p-2" style=" cursor: pointer;" onclick="location.href='<%=request.getContextPath()%>/member/memberStudy';">
	      <a href="<%=request.getContextPath()%>/member/memberStudy" class="text-decoration-none text-reset text-decoration-none text-dark">내 스터디 목록</a>
	    </div>
	</div>