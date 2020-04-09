<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.kh.review.model.vo.ReviewStudy" %>
<%
	List<ReviewStudy> list = (List)request.getAttribute("list");
	String type = request.getParameter("searchType");
	String keyword = request.getParameter("searchKeyword");

%>
<%@ include file="/views/common/header.jsp"%>
   <!-- css파일 호출 -->
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/reviewBoard.css" type="text/css"/>
 
<section id="studyRev_container">
  <br>
	<div id="reviewEntry" style=background-image:url('<%=request.getContextPath() %>/images/latter2.jpg');>
	        <h1 id="reviewTitle">스터디 후기</h1>
	</div>
    <br>
   <div id="revSearch_Container">
   	<div align="center" id="revSearch">
		<select id="searchType">
			<option value="REVIEW_STU_WRITER" <%=type!=null&&type.equals("REVIEW_STU_WRITER")?"selected":"" %>>작성자</option>
			<option value="STUDY_NAME" <%=type!=null&&type.equals("STUDY_NAME")?"selected":"" %>>스터디 이름</option>
		</select>
		
		<div id="search-REVIEW_STU_WRITER">
            <form action="<%=request.getContextPath() %>/review/reviewFinder">
               <input type="hidden" name="searchType" value="REVIEW_STU_WRITER"/>
               <input type="text" name="searchKeyword" value="<%=type!=null&&type.equals("REVIEW_STU_WRITER")?keyword:"" %>" size="25" placeholder="검색할 작성자 입력"/>
               <button type="submit" id="revFindBtn">검색</button>
            </form>
         </div>
            
         <div id="search-STUDY_NAME">
            <form action="<%=request.getContextPath()%>/review/reviewFinder">
               <input type="hidden" name="searchType" value="STUDY_NAME"/>
               <input type="text" name="searchKeyword" value="<%=type!=null&&type.equals("STUDY_NAME")?keyword:"" %>" size="25" placeholder="검색할 스터디 이름 입력"/>
               <button type="submit" id="revFindBtn">검색</button>
            </form>
         </div>
         
   </div>
   </div>
     <div>
     <%if(list.isEmpty()){ %>
     		<fieldset id="reviewField">
            	<tr>
            		<hr>
            			<h4 style="text-align:center;">등록된 후기가 없습니다.</h4>
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

       		
        <%if(loginMember!=null){%>
			<input type="button" value="스터디 후기 작성" id ="rev_WriteBtn" onclick="fn_writeReviewStudy()"/>
		<%}else{ %>
			<input type="hidden" >
		<%} %>
		<br>
       <ul class="pagination" style="margin-left:580px">
       		<%if(!list.isEmpty()){ %>
				<%=request.getAttribute("pageBar") %>
			<%	}%>	
		</ul>
		
    </div>
</section>

 <script>
 $(function(){
		$("#searchType").change(()=>{
			let type=$("#searchType").val();
		
			let writer=$("#search-REVIEW_STU_WRITER");
			let allStudy=$("#search-STUDY_NAME");
			
			writer.hide();
			allStudy.hide();
			
			$("#search-"+type).css("display","inline-block");
		})
		$("#searchType").trigger("change");
	});
 
      function fn_writeReviewStudy(){
		location.href="<%=request.getContextPath()%>/review/reviewStudyWrite?writer=<%=loginMember.getUserId()%>";
	  }
 </script>
<%@ include file="/views/common/footer.jsp"%>