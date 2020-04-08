<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.kh.review.model.vo.ReviewLecture" %>
<%
	List<ReviewLecture> list = (List)request.getAttribute("list");
%>
<%@ include file="/views/common/header.jsp"%>
   <!-- css파일 호출 -->
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/reviewBoard.css" type="text/css"/>
 

  <br>
<div id="reviewEntry" style=background-image:url('<%=request.getContextPath() %>/images/latter2.jpg');>
        <h1 id="reviewTitle">강좌 후기</h1>
</div>
    <div>
    <br>
     
<%-- <div id="selectReview" style="font-size:15px">
<a href="<%=request.getContextPath()%>/review/reviewStudy/reviewStudyList">스터디 후기</a>
</div> --%>
    
    <%if(list.isEmpty()){ %>
     		<fieldset id="reviewField">
            	<tr>
            		<hr>
            			<h4 style="align:center;">등록된 후기가 없습니다.</h4>
            		<hr>
            	</tr>
            	</fieldset>
      <%}else{ %>       	
    	<%for(ReviewLecture revL : list) {%>
        <fieldset id="reviewField">
         <hr>
            <td><%=revL.getReviewLecCategory()%></td> 
            <td><span style="float:right;"><%=revL.getReviewLecDate() %></span></td>
           <a href="<%=request.getContextPath()%>/review/reviewLectureView?no=<%=revL.getReviewLecNo()%>">
	                       <h2><%=revL.getLectureName() %></h2>
	         </a>
           <%if(revL.getReviewLecStar()==1){%>
            	<td>★</td>
            <%}else if(revL.getReviewLecStar()==2){ %>
            	<td>★★</td>
            <%}else if(revL.getReviewLecStar()==3){ %>
            	<td>★★★</td>
            <%}else if(revL.getReviewLecStar()==4){ %>
          		<td>★★★★</td>
          	<%}else if(revL.getReviewLecStar()==5){ %>
          		<td>★★★★★</td>
          	<%} %>
          	<br><br>
            <h4><%=revL.getReviewLecContent() %></h4>
        
            <td><span style="float:right;"><%=revL.getReviewLecWriter() %></span></td>
                <br>
             <hr>
            </fieldset>
           
	<%} 
	}%>

       		<ul class="pagination" style="margin-left:580px">
			<%if(!list.isEmpty()){ %>
			<%=request.getAttribute("pageBar") %>
			<%} %>
			</ul>
        <%if(loginMember!=null){%>
			<input type="button" value="강좌 후기 작성" id ="rev_WriteBtn" onclick="fn_writeReviewLecture()"/>
		<%}else{ %>
			<input type="hidden" >
		<%} %>
       
    </div>

    <script>
      
         function fn_writeReviewLecture(){
 			location.replace("<%=request.getContextPath()%>/review/reviewLectureWrite?writer=<%=loginMember.getUserId()%>");
 		}
    </script>
<%@ include file="/views/common/footer.jsp"%>