<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>
<%@page import="java.util.List,com.kh.study.model.vo.Study" %>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/studyView.css" type="text/css"/>

<%
	Study s=(Study)request.getAttribute("study");
%>

<section>
  <div id="intro">
  	<h2>일반 스터디<br><%=s.getStudyCategory()%>:<%=s.getStudyName() %></h2></div>
  <div class="watch">
      <p id="watchtitle"><h3><%=s.getStudyName() %></h3></p>
      <p id="detail">희망 일자 : <%=s.getStudyPossibleDay() %></br>
        스터디 지역</br>
         모집 인원 : <%=s.getMaxMember() %>명 / 현재 인원 : 3명</br>
       모집 기간 : <%=s.getMaxMember() %> 까지</p>
    <div class="video-inform">
      스터디 상세 소개
      <%=s.getStudyDetail() %>
    </div>
    <button type="button" class="basket" onclick="apply();">참여하기</button>
    <button type="button" class="basket" onclick="apply2();">이미 참여한 스터디입니다. 스터디 탈퇴하기</button>
    스터디 참여 여부 컬럼은 어떻게 줘야할까?
  </div>
</section>
  <script>

    function apply(){
    var result= confirm("스터디에 참여하시겠습니까?");
     if(result==true){
       //새로운 스터디입장시
       alert("스터디에 참가되었습니다.");
       location.href="일반스터디 상세조회.html";
       //인원수 카운트하기
     }
     else
       alert("취소되었습니다.");
     }
  </script>
 
<%@ include file="/views/common/footer.jsp"%>
