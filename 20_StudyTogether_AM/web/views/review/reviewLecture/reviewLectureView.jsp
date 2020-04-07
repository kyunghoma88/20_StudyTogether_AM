<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.kh.review.model.vo.ReviewLecture" %>
<%
	ReviewLecture revL = (ReviewLecture)request.getAttribute("reviewLecture");
%>
<%@ include file="/views/common/header.jsp"%>
<style>
#revWTitle{
	text-align:center;
	height:50px;
	border:1px solid #ccc;
}
table.revWrite{
        border-collapse: separate;
        border-spacing: 1px;
        text-align: left;
        line-height: 2;
        border-top: 1px solid #ccc;
        margin : 0 auto;
        width:500px;
        cellpadding:"2";
}
table.revWrite tr {
        width: 100px;
        padding: 10px;
        font-weight: bold;
        vertical-align: top;
        border-bottom: 1px solid #ccc;
}
table.revWrite td {
         font-size: 15px;
         width: 100px;
         padding: 5px;
         vertical-align: top;
         border-bottom: 1px solid #ccc;
}
#revUpdate,#revDelete,#backBtn{
	 color: black;
    padding: 12px 20px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    display: inline-block; 
    margin: 0px auto; 
    width: 10%;
    align: center;
}
</style>

<form>
<br>
<h2 style="text-align:center"><%=revL.getLectureName() %></h2>
<br>
   <table class="revWrite">
      <tr>
        <td style="text-align: center;">작성자</td>
        <td><%=revL.getReviewLecWriter() %></td>
       </tr>
       
        <tr>
          <td style="text-align: center;">분야</td>
          <td><%=revL.getReviewLecCategory() %></td>
        </tr>
        <tr>
            <td style="text-align: center;">만족도</td>
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
        </tr>
         <tr><td></td><td></td></tr>
        <tr>
           <!-- <td style="text-align: center;">내용</td> -->
           <td colspan="2" style="height:auto"><%=revL.getReviewLecContent() %></td>
        </tr>
          
   </table>
   <br>			
</form>
<%if(loginMember!=null && (loginMember.getUserId().equals("admin") || loginMember.getUserId().equals(revL.getReviewLecWriter()))){ %>
	        
	        <div style="text-align:center">
	     		   <button type="button" onclick="location.replace('<%=request.getContextPath()%>/review/reviewLecture/reviewLectureList')" id="backBtn">목록으로</button>
	     		 	<!--  <a href="<%=request.getContextPath()%>/review/reviewStudy/reviewStudyList">목록</a>-->
	              <button onclick="updateChk()" name="revUpdate" id="revUpdate">수정</button>
       			  <button onclick="deleteChk()" name="revDelete" id="revDelete">삭제</button>
	        </div>
	     <%}else{ %>
			<div style="text-align:center">
				<button type="button" onclick="location.replace('<%=request.getContextPath()%>/review/reviewLecture/reviewLectureList')" id="backBtn">목록으로</button>
			</div>
			
		<%} %>
<script>
	function updateChk(){
		location.replace("<%=request.getContextPath()%>/review/reviewLectureUpdate?no=<%=revL.getReviewLecNo()%>")
	}
	function deleteChk(){
		 if (confirm("정말 삭제 하시겠습니까??") == true){    //확인
			 location.replace("<%=request.getContextPath()%>/review/reviewLectureDelete?no=<%=revL.getReviewLecNo()%>")
         }else{   //취소
            return false;
         }
		
	}
</script>
<%@ include file="/views/common/footer.jsp"%>