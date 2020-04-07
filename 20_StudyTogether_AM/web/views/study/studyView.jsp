<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>
<%@page import="java.util.List,com.kh.join.model.vo.StudyJoin,com.kh.study.model.vo.Study,java.util.Date,java.text.SimpleDateFormat" %>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/studyView.css" type="text/css"/>

<%	
	List<StudyJoin> list=(List)request.getAttribute("sList");
	Study s=(Study)request.getAttribute("study");
%>

<section>
	<div class="container" >
    	<a href="<%=request.getContextPath()%>/study/studyUpdate?no=<%=s.getStudyNo() %>" class="btn btn-info" role="button">수정</a>
    	<a href="<%=request.getContextPath() %>/study/studyDelete?no=<%=s.getStudyNo() %>" class="btn btn-info" role="button">삭제</a>
	</div>
 <input type="text" name="member" value="<%=loginMember.getUserId() %>">
 <input type="text" name="no1" value="<%=s.getStudyNo() %>">
  <div style="margin-top:100px;margin-bottom:150px;" class="watch">
      <p id="watchtitle"><h2><%=s.getStudyName() %></h2></p>
      <p id="detail">희망 일자 : <%=s.getStudyPossibleDay() %>  </p>
        스터디 지역 : <%=s.getStudyArea() %></br>
        	<p id="detail2">모집 인원 : <%=s.getMaxMember() %>명 /
         	참가 인원 : <%=list.size() %> 명</br></p><!--(nowmember추가  -->
          <input type="hidden" name="nowMember" value=""><p id="endDate"> 모집 기간 : <%=s.getEndDate() %> 까지</p>
          <input type="hidden" name="student" value="">
    <div class="video-inform">
      <%=s.getStudyDetail() %>
    </div>
      <%if(s.getMaxMember()==list.size()){ %>
     <input type="button" class="basket" onclick="" value="마감된스터디입니다."></button>
      <%}else{ %>
   <input type="button" class="basket" onclick="apply();" value="참여하기"></button>
     <%} %>
   </div>
</section>
<style>
	#detail2{
		font-size:15px;
	}
</style>
  <script>
//study참가하기 버튼을 누르면 현재인원이 카운트 된다.
   

function apply(){
    var result= confirm("스터디에 참여하시겠습니까?");
     if(result==true){
    	 location.replace('<%=request.getContextPath()%>/study/studyJoin?no=<%=s.getStudyNo()%>&userId=<%=loginMember.getUserId()%>');
     }
   else{
    	 alert("취소 되었습니다.");
    	 loaction.reload;
     }
    }
  </script>
 
<%@ include file="/views/common/footer.jsp"%>
