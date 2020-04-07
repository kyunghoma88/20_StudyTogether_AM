<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>
    <div class="boardContainer">
		<div class="aside">
	        <span class="aside_title">커뮤니티</span>
	        <ul class="aside_list">
	            <li><a style="text-decoration:none; color:black;" href="<%=request.getContextPath()%>/board/boardList?category=qna">묻고 답하기</a></li>
			</ul>
		</div>
