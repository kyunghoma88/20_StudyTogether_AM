<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="java.util.List,com.kh.lector.model.vo.Lector,com.kh.lector.model.vo.LectorChannel,java.util.List" %>
//엄마강좌View!!
<%
	Lector l=(Lector)request.getAttribute("l");
	List<LectorChannel> clist=(List)request.getAttribute("clist");
 	int cPage=(int)request.getAttribute("cPage");
%>

<%@ include file="/views/common/header.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/lectorWatch.css" type="text/css"/>

<section>
 <div class="container" >
<%--     <a href="<%=request.getContextPath() %>/lectorWatch/lectorInsert" class="btn btn-info" role="button">강좌 추가</a>
 --%> <a href="<%=request.getContextPath()%>/lector/lectorChannelOpen?cNo=<%=l.getLectorNo() %>" class="btn btn-info" role="button">강좌 추가</a>
    <a href="<%=request.getContextPath()%>/lector/lectorUpdate?pNo=<%=l.getLectorNo() %>" class="btn btn-info" role="button">강좌 수정</a>
    <a href="<%=request.getContextPath() %>/lector/lectorDelete?pNo=<%=l.getLectorNo() %>" class="btn btn-info" role="button">강좌 삭제</a><!--관리자만 삭제  -->
  </div>
<style>
.btn btn-info{
background-color:#ffc107;
}
</style>

<!--분기처리해서 강좌개설자&admin에게만 보일수 있는 강좌추가,수정,(삭제는 관리자페이지에서만가능) 버튼  -->
<div id="intro">
<%-- <form action="<%=request.getContextPath()%>/cart/cartAdd" method="post">
 --%>
	  <h2><<%=l.getLectorCategory() %>&nbsp;강좌><h2>
	  	<p>제목:&nbsp;&nbsp;<%=l.getLectorTitle() %><br>
	  	강사:&nbsp;&nbsp;<%=l.getLectorWriter() %><br>
	  	금액:&nbsp;&nbsp;<%=l.getLectorPrice() %>&nbsp;&nbsp;원</p>
	  	<input type="hidden" name="userId" value="<%=loginMember.getUserId() %>">
	  	<input type="hidden" name="lectorNo" value="<%=l.getLectorNo()%>">
	 </div>
	  <div class="watch">
	    <div class="video">
	      <!-- 855*481 -->
	     <%if(l!=null&&l.getLectorOriginalVideo()!=null) {%>
		<video src="<%=request.getContextPath() %>/upload/lector/<%=l.getLectorOriginalVideo() %>" id="video" controls width="855px" height="481px"></video>
	    <%}%>
	 	</div>
	    <div id="video-inform">
	    <%=l.getLectorDetail() %>
	    </div>
	    

    <input type="button" class="basket" onclick="apply();" value="구매하기">

  </div>
  
<p id="list"><img src="<%=request.getContextPath() %>/images/list.svg" width="30px" height="auto">&nbsp;&nbsp;강의목록</p>
<div class="container">
<%if(!clist.isEmpty()){ %>
  <div class="list-group">
  	  <a href="<%=request.getContextPath() %>/lector/lectorView?pNo=<%=l.getLectorNo() %>" class="list-group-item list-group-item-action"><%=l.getLectorTitle() %></a>
    <%for(LectorChannel lc:clist){ %>
    <a href="<%=request.getContextPath() %>/lector/channelView?pNo=<%=l.getLectorNo() %>&cNo=<%=lc.getChannelNo() %>" class="list-group-item list-group-item-action"><%=lc.getChannelTitle() %></a>
  <%}
	}%>
  </div>
</div>

<%if(!clist.isEmpty()){ %>
 	<ul class="pagination">
		<%=request.getAttribute("pageBar") %>
	</ul> 
 <%} %>
 
 
<style>
	.pagination{
		margin-left:600px;
		margin-top:100px;
		margin-bottom:100px;	
	}
</style>

</section>

 <script>
//study참가하기 버튼을 누르면 현재인원이 카운트 된다.
   function apply(){
   var result= confirm("구매하시겠습니까?");
    if(result==true){
   	 location.replace('<%=request.getContextPath()%>/cart/cartAdd?pNo=<%=l.getLectorNo()%>&userId=<%=loginMember.getUserId()%>');
    }
  else{
   	 alert("취소 되었습니다.");
   	 loaction.reload;
    }
   }
 </script>
 

<%@ include file="/views/common/footer.jsp"%>
