<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.kh.review.model.vo.ReviewStudy" %>
<%
	List<ReviewStudy> list = (List)request.getAttribute("list");
%>
<%@ include file="/views/common/header.jsp"%>
   <!-- css파일 호출 -->
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/reviewBoard.css" type="text/css"/>
 

  
<div id="reviewEntry" style=background-image:url('<%=request.getContextPath() %>/images/latter2.jpg');>
        <h1 id="reviewTitle">스터디 후기</h1>
</div>
    <div>
    <br><br>
   
    <div id="selectReview" style="font-size:15px">
        <a href="<%=request.getContextPath()%>/review/reviewLecture/reviewLectureList">강좌 후기</a>
    </div>
    
    <br>
    <%if(list.isEmpty()){ %>
     		<fieldset id="reviewField">
            	<tr>
            		<hr>
            			<h4 style="align:center;">등록된 후기가 없습니다.</h4>
            		<hr>
            	</tr>
            	</fieldset>
    <%}else{ %>
    <%for(ReviewStudy revS : list) {%>
        <fieldset id="reviewField">
         <hr>
            <td><%=revS.getReviewStuCategory()%></td> 
            <td><span style="float:right;"><%=revS.getReviewStuDate() %></span></td>
             <a href="<%=request.getContextPath()%>/review/reviewStudyView?no=<%=revS.getReviewStuNo()%>">
	                       <h2><%=revS.getStudyName() %></h2>
	         </a>
           
            <%if(revS.getReviewStuStar()==1){%>
            	<td>★</td>
            <%}else if(revS.getReviewStuStar()==2){ %>
            	<td>★★</td>
            <%}else if(revS.getReviewStuStar()==3){ %>
            	<td>★★★</td>
            <%}else if(revS.getReviewStuStar()==4){ %>
          		<td>★★★★</td>
          	<%}else if(revS.getReviewStuStar()==5){ %>
          		<td>★★★★★</td>
          	<%} %>
          	<br><br>
            <h4><%=revS.getReviewStuContent() %></h4>
        
            <td><span style="float:right;"><%=revS.getReviewStuWriter() %></span></td>
                <br>
             <hr>
            </fieldset>
           
	<%}
    } %>

       		<ul class="pagination" style="margin-left:670px">
       		<%if(!list.isEmpty()){ %>
				<%=request.getAttribute("pageBar") %>
			<%} %>
			</ul>
        <%if(loginMember!=null){%>
			<input type="button" value="스터디 후기 작성" id ="rev_WriteBtn" onclick="fn_writeReviewStudy()"/>
		<%}else{ %>
			<input type="hidden" >
		<%} %>
       
    </div>

    <script>
         function fn_writeReviewStudy(){
 				location.replace("<%=request.getContextPath()%>/review/reviewStudyWrite?writer=<%=loginMember.getUserId()%>");
	
 		}
    </script>
<%@ include file="/views/common/footer.jsp"%>