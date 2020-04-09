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
<%if(loginMember!=null && loginMember.getUserId().equals("admin")||loginMember.getUserId().equals(s.getStudyWriter())){ %>
	<div class="container" >
    	<a href="<%=request.getContextPath()%>/study/studyUpdate?no=<%=s.getStudyNo() %>" class="btn btn-info" role="button">수정</a>
    	<a href="<%=request.getContextPath() %>/study/studyDelete?no=<%=s.getStudyNo() %>" class="btn btn-info" role="button">삭제</a>
	</div>
<%} %>
<%--  <input type="text" name="member" value="<%=loginMember.getUserId() %>">
 <input type="text" name="no1" value="<%=s.getStudyNo() %>"> --%>
  
  <div style="margin-top:100px;margin-bottom:150px;" class="watch">
      <p id="watchtitle"><h1>스터디명 : <%=s.getStudyName() %></h1></p>
      
      <p id="detail"> 개설자:<%=s.getStudyWriter() %>	<br>희망 일자 : <%=s.getStudyPossibleDay() %>
      </p>
        <p id="detail2" style="font-size:30px;">
        	스터디 지역 : <%=s.getStudyArea() %></br>
        	모집 인원 : <%=s.getMaxMember() %>명 /
         	현재 인원 : <%=list.size() %> 명</br>
         	<%SimpleDateFormat sdf=new SimpleDateFormat("yy-MM-dd"); %>
    	모집마감&nbsp;~<%=sdf.format(sdf.parse(s.getEndDate()))%>  까지</p>
    	<br><br>
         
          <input type="hidden" name="nowMember" value="">
       
		
		
        <input type="hidden" name="student" value="">
    	
    	<div class="video-inform">
      <%=s.getStudyDetail() %>
    </div>
      <%if(s.getMaxMember()==list.size()){ %>
     <input type="button" class="basket" onclick="" value="마감된스터디입니다."></button>
      <%}else if(s.getStudyWriter().equals(loginMember.getUserId())){ %>
     <input type="button" class="basket" onclick="apply2();" value="참여하기"></button>
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
    
function apply2(){
 alert("개설자는 이미 참여 되었습니다.");
 location.reload;
}
  </script>
 
<%@ include file="/views/common/footer.jsp"%>
