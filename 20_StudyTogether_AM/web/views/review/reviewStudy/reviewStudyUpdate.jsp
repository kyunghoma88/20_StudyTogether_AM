<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/reviewUpdate.css" type="text/css"/>
<%@ page import="com.kh.review.model.vo.ReviewStudy" %>
<%
	ReviewStudy revS = (ReviewStudy)request.getAttribute("reviewStudy");
%>

<form action="<%=request.getContextPath() %>/review/reviewStudyUpdateEnd">
<br>
<h2 style="text-align:center"><%=revS.getStudyName() %><input type="hidden" name="allStudy" value="<%=revS.getStudyName()%>" readonly required></h2>
<br>
<input type="hidden" name="no" value=<%=revS.getReviewStuNo() %>>
   <table class="revWrite">
      <tr>
        <td style="text-align: center;">작성자</td>
        <td><%=revS.getReviewStuWriter() %><input type="hidden" name="writer" value="<%=loginMember.getUserId()%>" readonly required/></td>
       </tr>
       
        <tr>
          <td style="text-align: center;">분야</td>
          <td> <%=revS.getReviewStuCategory() %><input type="hidden" name="field" value="<%=revS.getReviewStuCategory() %>" readonly required/> </td>
        </tr>
        <tr>
            <td style="text-align: center;">만족도</td>
           <input type="hidden" id="starCnt" name="starCnt">
            <td><p id="star_grade" name="star">
                <a href="#">★</a>
                <a href="#">★</a>
                <a href="#">★</a>
                <a href="#">★</a>
                <a href="#">★</a>
             </p></td>
      </tr>
        <tr>
           <td style="text-align: center;">내용</td>
           <td style="height:auto"><textarea name="content" cols=55 rows=10 style="resize:none;"><%=revS.getReviewStuContent() %></textarea></td>
        </tr>
          
   </table>
   <br>		
     <div style="text-align:center">
	    	 <button type="button" onclick ="cancelChk()" id="revWCancelBtn" >취소</button>
	    	  <button type="submit" id="revUpdate">수정 완료</button>
	</div>	
</form>
	  
 <script>
		 var starCnt=0;
		 $('#star_grade a').click(function(){
		     $(this).parent().children("a").removeClass("on");  /* 별점의 on 클래스 전부 제거 */ 
		     $(this).addClass("on").prevAll("a").addClass("on"); /* 클릭한 별과, 그 앞 까지 별점에 on 클래스 추가 */
		     starCnt =  $(this).addClass("on").prevAll("a").addClass("on").length+1;
				//별 개수 jsp로 보내기
		     var form = $("#letureWrite");            
		     $("#starCnt").attr({type:'hidden',name:'starCnt',value:starCnt}).appendTo(form);
		
		 });
          function cancelChk(){
             if (confirm("정말 취소하시겠습니까??") == true){    //확인
                   location.replace("<%=request.getContextPath()%>/review/reviewStudyView?no=<%=revS.getReviewStuNo()%>");
               }else{   //취소
                  return false;
               }
           }
          
      </script>
<script>
</script>
<%@ include file="/views/common/footer.jsp"%>