<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.kh.review.model.vo.ReviewLecture" %>
<%
	List<ReviewLecture> list = (List)request.getAttribute("list");
	String type = request.getParameter("searchType");
	String keyword = request.getParameter("searchKeyword");
%>
<%@ include file="/views/common/header.jsp"%>
   <!-- css파일 호출 -->
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/reviewBoard.css" type="text/css"/>
 
<section id="lecRev_container">
<br>
	<div id="reviewEntry" style=background-image:url('<%=request.getContextPath() %>/images/latter2.jpg');>
        <h1 id="reviewTitle">강좌 후기</h1>
	</div>
	 <br>
    <div id="revSearch_Container">
    <div align="center"  id="revSearch">
		<select id="searchType">
			<option value="REVIEW_LEC_WRITER" <%=type!=null&&type.equals("REVIEW_LEC_WRITER")?"selected":"" %>>작성자</option>
			<option value="LECTURE_TITLE" <%=type!=null&&type.equals("LECTURE_TITLE")?"selected":"" %>>스터디 이름</option>
		</select>
		
		<div id="search-REVIEW_LEC_WRITER">
            <form action="<%=request.getContextPath() %>/review/reviewLectureFinder">
               <input type="hidden" name="searchType" value="REVIEW_LEC_WRITER"/>
               <input type="text" name="searchKeyword" value="<%=type!=null&&type.equals("REVIEW_LEC_WRITER")?keyword:"" %>" size="25" placeholder="검색할 작성자 입력"/>
               <button type="submit" id="revFindBtn">검색</button>
            </form>
         </div>
            
         <div id="search-LECTURE_TITLE">
            <form action="<%=request.getContextPath() %>/review/reviewLectureFinder">
               <input type="hidden" name="searchType" value="LECTURE_TITLE"/>
               <input type="text" name="searchKeyword" value="<%=type!=null&&type.equals("LECTURE_TITLE")?keyword:"" %>" size="25" placeholder="검색할 스터디 이름 입력"/>
               <button type="submit" id="revFindBtn">검색</button>
            </form>
         </div>
         
   </div>
     
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
        <%if(loginMember!=null){%>
			<input type="button" value="강좌 후기 작성" id ="rev_WriteBtn" onclick="fn_writeReviewLecture()"/>
		<%}else{ %>
			<input type="hidden" >
		<%} %>
		<br>
       <ul class="pagination" style="margin-left:580px">
			<%if(!list.isEmpty()){ %>
			<%=request.getAttribute("pageBar") %>
			<%} %>
		</ul>
    </div>

    <script>
    $(function(){
		$("#searchType").change(()=>{
			let type=$("#searchType").val();
		
			let writer=$("#search-REVIEW_LEC_WRITER");
			let allStudy=$("#search-LECTURE_TITLE");
			
			writer.hide();
			allStudy.hide();
			
			$("#search-"+type).css("display","inline-block");
		})
		$("#searchType").trigger("change");
	});
         function fn_writeReviewLecture(){
 			location.href="<%=request.getContextPath()%>/review/reviewLectureWrite?writer=<%=loginMember.getUserId()%>";
 		}
    </script>
<%@ include file="/views/common/footer.jsp"%>