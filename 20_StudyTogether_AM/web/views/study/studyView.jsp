<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>
<%@page import="java.util.List,com.kh.study.model.vo.Study,java.util.Date,java.text.SimpleDateFormat" %>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/studyView.css" type="text/css"/>

<%
	Study s=(Study)request.getAttribute("study");
%>

<section>
	<div class="container" >
    	<a href="<%=request.getContextPath()%>/study/studyUpdate?no=<%=s.getStudyNo() %>" class="btn btn-info" role="button">수정</a>
    	<a href="<%=request.getContextPath() %>/study/studyDelete?no=<%=s.getStudyNo() %>" class="btn btn-info" role="button">삭제</a>
	</div>

  <div id="intro">
  	<h2>일반 스터디<br><%=s.getStudyCategory()%>:<%=s.getStudyName() %></h2></div>
  <div class="watch">
      <p id="watchtitle"><h3><%=s.getStudyName() %></h3></p>
      <p id="detail">희망 일자 : <%=s.getStudyPossibleDay() %>  </p>
        스터디 지역</br>
        	<p>모집 인원 : <%=s.getMaxMember() %>명 / 
         	참가 인원 : <%=s.getNowMember() %>명</br></p><!--(nowmember추가  -->
          <input type="hidden" name="nowMember" value="<%=s.getNowMember() %>">      <p id="endDate"> 모집 기간 : <%=s.getEndDate() %> 까지</p>
          <input type="hidden" name="student" value="">
    <div class="video-inform">
      스터디 상세 소개
      <%=s.getStudyDetail() %>
    </div>
    <button type="button" class="basket" onclick="apply();">참여하기</button>
  </div>
</section>
  <script>

    function apply(){
    var result= confirm("스터디에 참여하시겠습니까?");
     if(result==true){
       //새로운 스터디입장시
       alert("스터디에 참가되었습니다."); + 현재인원을 카운트하게해야함
       location.href="<%=request.getContextPath()%>/study/studyJoin";
       //인원수 카운트하기
     }
   /*   else if{//else if(로그인이 되어있지만 ,studyJoin!=null 이미 가입된 스터디입니다.
       alert("이미 가입된 스터디 입니다..");
     } */else{
    	 alert("로그인 후 이용 가능합니다.")
     }
    
   
    
    
    
    
  </script>
 
<%@ include file="/views/common/footer.jsp"%>
