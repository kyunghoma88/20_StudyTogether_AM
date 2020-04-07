<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp" %>



<!-- header페이지 불러오기 -->
<div class="container">
	<div name="nav" class="container-fluid mt-3 mb-3">
        <div class="row">
            <nav>
                <ul class="navbar-nav flex-row">
                    <li class="nav-item ml-5"><a class="text-decoration-none text-dark" href="<%=request.getContextPath()%>/member/memberView">내 계정 정보</a></li>
                    <li class="nav-item ml-5"><a class="text-decoration-none text-dark" href="<%=request.getContextPath()%>/member/memberStudy">내 스터디 목록</a></li>
                    <li class="nav-item ml-5"><a class="text-decoration-none text-dark" href="<%=request.getContextPath()%>/member/memberLector">내 강좌 목록</a></li>
                    <li class="nav-item ml-5"><a class="text-decoration-none text-dark" href="<%=request.getContextPath()%>/buy/buyForm">구매Test</a></li>
                </ul>
            </nav>
        </div>
    </div>